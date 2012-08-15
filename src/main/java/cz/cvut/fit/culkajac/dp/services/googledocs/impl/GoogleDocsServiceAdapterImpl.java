package cz.cvut.fit.culkajac.dp.services.googledocs.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.google.common.collect.Lists;
import com.google.gdata.client.DocumentQuery;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.media.ResumableGDataFileUploader;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.ServiceForbiddenException;
import com.google.gdata.util.common.base.Pair;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.FileQueryDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.googledocs.GoogleDocsServiceAdapter;

@Service(GoogleDocsServiceAdapter.class)
@SuppressWarnings("rawtypes")
public class GoogleDocsServiceAdapterImpl implements GoogleDocsServiceAdapter {

	protected static final Logger LOGGER = LoggerFactory.getLogger(GoogleDocsServiceAdapterImpl.class);
	protected static final String SERVICE_UID = Constants.GoogleDocs.SERVICE_UID;
	protected static final URL FULL_URL;
	protected static final String DEFAULT_EXTENSION = "txt";

	private LookupStrategy _latestFS;
	private LookupStrategy _uniqueOnlyFS;

	static {
		try {
			FULL_URL = new URL("https://docs.google.com/feeds/default/private/full");
		} catch (MalformedURLException e) {
			LOGGER.error(e.toString());
			throw new RuntimeException();
		}
	}

	protected DocsService service;

	@SuppressWarnings("unused")
	@Inject
	private void initDocsService(GoogleDocsAPIService gds) {
		this.service = gds.getService();
	}

	@Override
	public OperationStatusDTO<FileDTO> retrieveSafe(FileDescriptorDTO fd) {
		FileDTO f = null;
		try {
			f = retrieve(fd);
			return new OperationStatusDTO<FileDTO>(HttpStatus.SC_OK, SERVICE_UID, f, FileDTO.class);
		} catch (FileServiceException e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
			return new OperationStatusDTO<FileDTO>(500, SERVICE_UID);
		}
	}

	@Override
	public OperationStatusDTO<FileDTO> retrieveLatestSafe(FileDescriptorDTO fd) {
		FileDTO f = null;
		try {
			f = retrieveLatest(fd);
			return new OperationStatusDTO<FileDTO>(HttpStatus.SC_OK, SERVICE_UID, f, FileDTO.class);
		} catch (FileServiceException e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
			return new OperationStatusDTO<FileDTO>(500, SERVICE_UID);
		}
	}

	@Override
	public FileDTO retrieveLatest(FileDescriptorDTO fd) throws FileServiceException {
		return retrieveBase(fd, this.getLatestFS());
	}

	@Override
	public FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException {
		return retrieveBase(fd, this.getUniqueOnlyFS());
	}

	private FileDTO retrieveBase(FileDescriptorDTO fd, LookupStrategy ls) throws FileServiceException {

		InputStream inStream = null;
		ByteArrayOutputStream outStream = null;

		try {
			DocumentListEntry docEntry = this.service.getEntry(ls.resourceLookup(fd).getFirst(), DocumentListEntry.class);

			String fileExtension = DEFAULT_EXTENSION;
			if (fd.getTitle().lastIndexOf(".") > 0) {
				fileExtension = fd.getTitle().substring(fd.getTitle().lastIndexOf(".") + 1);
			}

			String exportUrl = ((MediaContent) docEntry.getContent()).getUri() + "&exportFormat=" + fileExtension;

			MediaContent mc = new MediaContent();
			mc.setUri(exportUrl);

			MediaSource ms = this.service.getMedia(mc);

			inStream = ms.getInputStream();
			outStream = new ByteArrayOutputStream();

			IOUtils.copy(inStream, outStream);

			// fd.setContainerServiceDescriptor(SERVICE_UID);
			fd.setSourceService(SERVICE_UID);

			return new FileDTO(fd, outStream.toByteArray());

		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw new FileServiceException(e);
		} finally {
			IOUtils.closeQuietly(inStream);
			IOUtils.closeQuietly(outStream);
		}
	}

	@Override
	public OperationStatusDTO<FileDescriptorDTO> storeSafe(FileDTO f) {
		try {
			return new OperationStatusDTO<FileDescriptorDTO>(200, SERVICE_UID, store(f), FileDescriptorDTO.class);
		} catch (FileServiceException e) {
			e.printStackTrace();
			LOGGER.warn(e.getMessage());
			return new OperationStatusDTO<FileDescriptorDTO>(500, SERVICE_UID);
		}
	}

	@Override
	public FileDescriptorDTO store(FileDTO f) throws FileServiceException {
		try {
			return storeResumable(f);
			//return storeSimple(f);
		} catch (FileServiceException fse) {
			if (fse.getCause() instanceof ServiceForbiddenException) {
				LOGGER.info("Trying resumable upload...");
				return storeResumable(f);
			}
			throw fse;
		}
	}

	private FileDescriptorDTO storeSimple(FileDTO f) throws FileServiceException {
		DocumentListEntry newDocument = new DocumentListEntry();
		String mediaMimeType = DocumentListEntry.MediaType.fromFileName(f.getMetadata().getTitle()).getMimeType();
		MediaSource ms = new MediaByteArraySource(f.getData(), mediaMimeType);
		newDocument.setMediaSource(ms);
		newDocument.setTitle(new PlainTextConstruct(f.getMetadata().getTitle()));

		try {
			DocumentListEntry storedDocument = this.service.insert(FULL_URL, newDocument);
			FileDescriptorDTO fd = new FileDescriptorDTO(storedDocument.getTitle().getPlainText());
			// fd.setContainerServiceDescriptor(SERVICE_UID);
			fd.setSourceService(SERVICE_UID);
			fd.setFileInternalId(storedDocument.getResourceId());
			fd.setEtag(storedDocument.getEtag());
			return fd;

		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw new FileServiceException(e);
		}
	}

	private FileDescriptorDTO storeResumable(FileDTO f) throws FileServiceException {

		try {

			String contentType = DocumentListEntry.MediaType.fromFileName(f.getMetadata().getTitle()).getMimeType();
			File temp = File.createTempFile(f.getMetadata().getBaseTitle(), f.getMetadata().getExtension());
			temp.deleteOnExit();
			System.out.println(temp.getName());
			FileOutputStream fos = new FileOutputStream(temp);
			IOUtils.copy(new ByteArrayInputStream(f.getData()), fos);
			IOUtils.closeQuietly(fos);

			MediaFileSource mfs = new MediaFileSource(temp, contentType);

			DocumentListEntry dle = new DocumentListEntry();
			dle.setTitle(TextConstruct.plainText(f.getMetadata().getTitle()));
			URL createUploadUrl = new URL("https://docs.google.com/feeds/upload/create-session/default/private/full");
			ResumableGDataFileUploader uploader = new ResumableGDataFileUploader.Builder(this.service, createUploadUrl, mfs, dle)
					.build();
			// if (true)
			// throw new RuntimeException();
			uploader.start();
			// wait for uploads to complete
			int to = 10000;
			while (!uploader.isDone()) {
				try {

					Thread.sleep(100);
				} catch (InterruptedException ie) {
				}
				if (--to == 0)
					break;
			}

			IOUtils.closeQuietly(mfs.getInputStream());
			temp.delete();

			if (to == 0)
				throw new TimeoutException();

			return f.getMetadata();
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			throw new FileServiceException(e);
		}

	}

	@Override
	public OperationStatusDTO delete(FileDescriptorDTO fd) throws FileServiceException {

		try {
			Pair<URL, FileDescriptorDTO> p = this.getUniqueOnlyFS().resourceLookup(fd);
			LOGGER.info(p.getFirst().toString());
			this.service.delete(p.getFirst(), "*");
			return new OperationStatusDTO(200, SERVICE_UID);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.toString());
			throw new FileServiceException(e);
		}

	}

	@Override
	public OperationStatusDTO deleteAllSafe(FileDescriptorDTO fd) {

		try {
			DocumentQuery query = new DocumentQuery(FULL_URL);
			query.setTitleQuery(fd.getTitle());
			query.setTitleExact(true);

			DocumentListFeed feed = this.service.getFeed(query, DocumentListFeed.class);

			for (DocumentListEntry entry : feed.getEntries()) {
				this.service.delete(slashJoinUrl(FULL_URL, entry.getResourceId()), "*");
			}

			return new OperationStatusDTO(200, SERVICE_UID);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warn(e.toString());
			return new OperationStatusDTO(500, SERVICE_UID);
		}

	}

	private abstract class LookupStrategy {

		protected Pair<URL, FileDescriptorDTO> resourceLookup(FileDescriptorDTO fd) throws FileServiceException {

			DocumentListEntry entry;
			try {
				if (fd.getTargetService().equals(SERVICE_UID) && !fd.getFileInternalId().isEmpty()) {
					entry = GoogleDocsServiceAdapterImpl.this.service.getEntry(slashJoinUrl(FULL_URL, fd.getFileInternalId()),
							DocumentListEntry.class);
				} else {
					DocumentQuery query = new DocumentQuery(FULL_URL);
					query.setTitleQuery(fd.getTitle());
					query.setTitleExact(true);
					// query.setMaxResults(2);

					DocumentListFeed feed;
					feed = GoogleDocsServiceAdapterImpl.this.service.getFeed(query, DocumentListFeed.class);

					entry = entrySelectionStrategy(feed);
				}
			} catch (Exception e) {
				LOGGER.error(e.toString());
				throw new FileServiceException(e);
			}

			fd.setFileInternalId(entry.getResourceId());
			fd.setEtag(entry.getEtag());
			LOGGER.info(fd.getEtag());

			return new Pair<URL, FileDescriptorDTO>(slashJoinUrl(FULL_URL, fd.getFileInternalId()), fd);
		}

		protected abstract DocumentListEntry entrySelectionStrategy(DocumentListFeed feed) throws FileServiceException;
	}

	private class GetUniqueOnlyFS extends LookupStrategy {
		@SuppressWarnings("synthetic-access")
		public GetUniqueOnlyFS() {
		}

		@Override
		protected DocumentListEntry entrySelectionStrategy(DocumentListFeed feed) throws FileServiceException {
			if (feed.getEntries().isEmpty()) {
				throw new FileServiceException("Nothing found");
			} else if (feed.getEntries().size() > 1) {
				throw new FileServiceException(String.format("Ambiguous request [%s]", feed.getEntries().size()));
			} else {
				return feed.getEntries().get(0);
			}
		}
	}

	private class GetLatestFS extends LookupStrategy {
		@SuppressWarnings("synthetic-access")
		public GetLatestFS() {
		}

		@Override
		protected DocumentListEntry entrySelectionStrategy(DocumentListFeed feed) throws FileServiceException {
			if (feed.getEntries().isEmpty()) {
				throw new FileServiceException("Nothing found");
			} else {
				return Collections.max(feed.getEntries(), new Comparator<DocumentListEntry>() {
					@Override
					public int compare(DocumentListEntry o1, DocumentListEntry o2) {
						if (o1.getPublished() != null)
							return o1.getPublished().compareTo(o2.getPublished());
						return 0;
					}
				});
			}
		}
	}

	protected static URL slashJoinUrl(URL url, String resource) throws FileServiceException {
		try {
			return new URL(String.format("%s/%s", url.toString(), resource));
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
			throw new FileServiceException(e);
		}
	}

	@Override
	public List<FileDescriptorDTO> searchAll(FileQueryDTO sq) throws FileServiceException {
		DocumentListFeed feed;
		DocumentQuery query = new DocumentQuery(FULL_URL);
		query.setTitleQuery(sq.getTitle());
		query.setTitleExact(sq.isExact());

		try {
			feed = this.service.getFeed(query, DocumentListFeed.class);
		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw new FileServiceException(e);
		}

		List<FileDescriptorDTO> fdList = Lists.newArrayList();

		for (DocumentListEntry entry : feed.getEntries()) {
			FileDescriptorDTO fd = new FileDescriptorDTO(feed.getTitle().getPlainText());
			// fd.setContainerServiceDescriptor(SERVICE_UID);
			fd.setSourceService(SERVICE_UID);
			fd.setFileInternalId(entry.getResourceId());
			fdList.add(fd);
		}

		return fdList;
	}

	public LookupStrategy getLatestFS() {
		if (this._latestFS == null) {
			this._latestFS = this.new GetLatestFS();
		}
		return this._latestFS;
	}

	public LookupStrategy getUniqueOnlyFS() {
		if (this._uniqueOnlyFS == null) {
			this._uniqueOnlyFS = this.new GetUniqueOnlyFS();
		}
		return this._uniqueOnlyFS;
	}
}
