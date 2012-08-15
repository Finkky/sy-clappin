package cz.cvut.fit.culkajac.dp.services.dropbox.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.DeltaEntry;
import com.dropbox.client2.DropboxAPI.DeltaPage;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.ProgressListener;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.WebAuthSession;
import com.google.common.collect.Lists;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.FileQueryDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.dropbox.DropboxServiceAdapter;

@SuppressWarnings("rawtypes")
@Service(DropboxServiceAdapter.class)
public class DropboxServiceAdapterImpl implements DropboxServiceAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(DropboxServiceAdapterImpl.class);
	private static final String SERVICE_NAME = Constants.Dropbox.SERVICE_UID;
	private static final String CURSOR_FILE = Constants.Dropbox.CURSOR_FILE_NAME;
	
	DropboxAPI<WebAuthSession> dropboxService;

	@Inject
	private void initDocsService(DropboxAPIService gds) {
		this.dropboxService = gds.getService();
	}

	@Override
	public OperationStatusDTO<FileDTO> retrieveSafe(FileDescriptorDTO fd) {
		FileDTO f = null;
		try {
			f = retrieve(fd);
			return new OperationStatusDTO<FileDTO>(HttpStatus.SC_OK, SERVICE_NAME, f, FileDTO.class);
		} catch (FileServiceException e) {
			return new OperationStatusDTO<FileDTO>(500, SERVICE_NAME);
		}
	}

	@Override
	public FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException {

		try {
			String filePath = fd.getTitle();
			String revision = null;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ProgressListener pl = null;
			this.dropboxService.getFile(filePath, revision, baos, pl);
			fd.setSourceService(SERVICE_NAME);
			return new FileDTO(fd, baos.toByteArray());
		} catch (DropboxException e) {
			LOGGER.warn(e.getMessage());
			throw new FileServiceException(e);
		}
	}

	public String retrieveCurrentDeltaCursor() {
		try {
			return new String(this.retrieve(new FileDescriptorDTO(CURSOR_FILE)).getData());
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			return "";
		}
	}

	public void storeCurrentDeltaCursor(String hash) throws FileServiceException {
		FileDescriptorDTO fd = new FileDescriptorDTO(CURSOR_FILE);
		FileDTO f = new FileDTO(fd, hash.getBytes());
		this.storeOverwrite(f);
	}

	@Override
	public Collection<FileDescriptorDTO> retrieveCurrentDelta() throws FileServiceException {
		try {
			String cursor = retrieveCurrentDeltaCursor();

			DeltaPage<Entry> delta = this.dropboxService.delta(cursor);

			Collection<FileDescriptorDTO> fileDescriptors = Lists.newArrayList();

			System.out.println(delta.entries.size());

			int validDeltas = 0;

			for (DeltaEntry<Entry> e : delta.entries) {
				/**
				 * Take only files from the root folder
				 */
				if (!(e.metadata == null) && !e.metadata.isDeleted && !e.metadata.isDir && "/".equals(e.metadata.parentPath())
						&& !e.metadata.fileName().startsWith(".")) {
					FileDescriptorDTO fd = new FileDescriptorDTO(e.metadata.fileName());
					fd.setContentLength(e.metadata.bytes);
					fd.setSourceService(SERVICE_NAME);
					fileDescriptors.add(fd);
					validDeltas++;
				}
			}

			if (validDeltas > 0) {
				this.storeCurrentDeltaCursor(delta.cursor);
			}

			return fileDescriptors;
		} catch (Exception e) {
			throw new FileServiceException(e);
		}
	}

	@Override
	public OperationStatusDTO storeOverwriteSafe(FileDTO f) {
		try {
			return storeOverwrite(f);
		} catch (FileServiceException e) {
			return new OperationStatusDTO<FileDTO>(500, SERVICE_NAME);
		}
	}

	
	@Override
	public OperationStatusDTO storeOverwrite(FileDTO f) throws FileServiceException {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(f.getData());
			ProgressListener pl = null;
			this.dropboxService.putFileOverwrite(f.getMetadata().getTitle(), bais, f.getData().length, pl);
			return new OperationStatusDTO(HttpStatus.SC_OK, SERVICE_NAME);
		} catch (DropboxException e) {
			LOGGER.warn(e.getMessage());
			throw new FileServiceException(e);
		}
	}

	@Override
	public OperationStatusDTO deleteSafe(FileDescriptorDTO fd) {
		try {
			return delete(fd);
		} catch (FileServiceException fse) {
			return new OperationStatusDTO(500, SERVICE_NAME);
		}

	}

	@Override
	public OperationStatusDTO delete(FileDescriptorDTO fd) throws FileServiceException {
		try {
			this.dropboxService.delete(fd.getTitle());
			return new OperationStatusDTO(HttpStatus.SC_OK, SERVICE_NAME);
		} catch (DropboxException e) {
			LOGGER.warn(e.getMessage());
			throw new FileServiceException(e);
		}
	}

	@Override
	public List<FileDescriptorDTO> searchAll(FileQueryDTO sq) throws FileServiceException {
		try {
			final boolean includeDeleted = false;
			final int queryLimit = 0;
			final String root = "";

			List<Entry> entries = this.dropboxService.search(root, sq.getTitle(), queryLimit, includeDeleted);
			List<FileDescriptorDTO> fdList = new ArrayList<FileDescriptorDTO>(entries.size());
			for (Entry entry : entries) {
				FileDescriptorDTO fd = new FileDescriptorDTO(entry.fileName());
				fd.setSourceService(SERVICE_NAME);
				fdList.add(fd);
			}
			return fdList;
		} catch (DropboxException e) {
			LOGGER.warn(e.getMessage());
			throw new FileServiceException(e);
		}
	}
}
