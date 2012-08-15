package cz.cvut.fit.culkajac.dp.services.googlephotos.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.google.gdata.client.Query;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.media.MediaByteArraySource;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.PhotoFeed;

import cz.cvut.fit.culkajac.dp.Constants;
import cz.cvut.fit.culkajac.dp.FileServiceException;
import cz.cvut.fit.culkajac.dp.dto.FileDTO;
import cz.cvut.fit.culkajac.dp.dto.FileDescriptorDTO;
import cz.cvut.fit.culkajac.dp.dto.OperationStatusDTO;
import cz.cvut.fit.culkajac.dp.services.googlephotos.GooglePhotosServiceAdapter;

@Service(GooglePhotosServiceAdapter.class)
@SuppressWarnings("rawtypes")
public class GooglePhotosServiceAdapterImpl implements GooglePhotosServiceAdapter {

	protected static final String FILE_TITLE_FORMAT = "%1$s_%2$s";
	protected static final Logger LOGGER = LoggerFactory.getLogger(GooglePhotosServiceAdapterImpl.class);
	protected static final String SERVICE_UID = Constants.Picasa.SERVICE_UID;
	protected static final URL FULL_URL;
	protected static final String DEFAULT_EXTENSION = "jpeg";
	protected static final String GP_USERNAME = "cz.cvut.fit.culkajac.dp";

	static {
		try {
			FULL_URL = new URL(String.format("https://picasaweb.google.com/data/feed/api/user/%s", GP_USERNAME));
		} catch (MalformedURLException e) {
			LOGGER.error(e.toString());
			throw new RuntimeException();
		}
	}

	protected PicasawebService service;

	private LookupStrategy _latestFS;
	private LookupStrategy _uniqueOnlyFS;

	@SuppressWarnings("unused")
	@Inject
	private void initDocsService(GooglePhotoAPIService gds) {
		this.service = gds.getService();
	}

	@Override
	public OperationStatusDTO<FileDTO> retrieveSafe(FileDescriptorDTO fd) {
		FileDTO f = null;
		try {
			f = retrieve(fd);
			return new OperationStatusDTO<FileDTO>(HttpStatus.SC_OK, SERVICE_UID, f, FileDTO.class);
		} catch (FileServiceException e) {
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
			return new OperationStatusDTO<FileDTO>(500, SERVICE_UID);
		}
	}

	@Override
	public FileDTO retrieve(FileDescriptorDTO fd) throws FileServiceException {
		return retrieveBase(fd, this.getUniqueOnlyFS());
	}

	@Override
	public FileDTO retrieveLatest(FileDescriptorDTO fd) throws FileServiceException {
		return retrieveBase(fd, this.getLatestFS());
	}

	private FileDTO retrieveBase(FileDescriptorDTO fd, LookupStrategy ls) throws FileServiceException {

		InputStream inStream = null;
		ByteArrayOutputStream outStream = null;

		try {
			GphotoEntry<?> gphotoEntry = ls.resourceLookup(fd);

			String fileExtension = DEFAULT_EXTENSION;
			if (fd.getTitle().lastIndexOf(".") > 0) {
				fileExtension = fd.getTitle().substring(fd.getTitle().lastIndexOf(".") + 1);
			}

			String exportUrl = ((MediaContent) gphotoEntry.getContent()).getUri() + "&exportFormat=" + fileExtension;

			LOGGER.info(exportUrl);

			MediaContent mc = new MediaContent();
			mc.setUri(exportUrl);

			MediaSource ms = this.service.getMedia(mc);

			inStream = ms.getInputStream();
			outStream = new ByteArrayOutputStream();

			IOUtils.copy(inStream, outStream);

			//fd.setContainerServiceDescriptor(SERVICE_UID);
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
			FileDescriptorDTO fd = store(f);
			return new OperationStatusDTO<FileDescriptorDTO>(200, SERVICE_UID, fd, FileDescriptorDTO.class);
		} catch (FileServiceException e) {
			return new OperationStatusDTO<FileDescriptorDTO>(500, SERVICE_UID);
		}
	}

	@Override
	public FileDescriptorDTO store(FileDTO f) throws FileServiceException {

		MediaSource ms = new MediaByteArraySource(f.getData(), "image/jpeg");

		PhotoEntry newPhoto = new PhotoEntry();

		int extPos = f.getMetadata().getTitle().lastIndexOf('.');

		String title = f.getMetadata().getTitle().substring(0, extPos);
		String ext = f.getMetadata().getTitle().substring(extPos + 1);
		newPhoto.setTitle(new PlainTextConstruct(String.format(FILE_TITLE_FORMAT, title, ext)));
		newPhoto.setDescription(new PlainTextConstruct(f.getMetadata().getTitle()));
		newPhoto.setClient("Clappin");
		newPhoto.setMediaSource(ms);

		try {
			URL postUrl = new URL(String.format("https://picasaweb.google.com/data/feed/api/user/%s", GP_USERNAME));
			PhotoEntry storedPhoto = this.service.insert(postUrl, newPhoto);
			FileDescriptorDTO fd = new FileDescriptorDTO(storedPhoto.getTitle().getPlainText());
			//fd.setContainerServiceDescriptor(SERVICE_UID);
			fd.setSourceService(SERVICE_UID);
			fd.setFileInternalId(storedPhoto.getGphotoId());

			fd.setEtag(storedPhoto.getEtag());
			return fd;

		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw new FileServiceException(e);
		}
	}

	@Override
	public OperationStatusDTO delete(FileDescriptorDTO fd) throws FileServiceException {

		try {

			GphotoEntry<?> p = this.getUniqueOnlyFS().resourceLookup(fd);
			LOGGER.info(p.toString());
			p.delete();

			return new OperationStatusDTO(200, SERVICE_UID);

		} catch (Exception e) {
			LOGGER.error(e.toString());
			throw new FileServiceException(e);
		}

	}

	@Override
	public OperationStatusDTO deleteAllSafe(FileDescriptorDTO fd) {

		try {
			Query query = new Query(FULL_URL);

			int extPos = fd.getTitle().lastIndexOf('.');

			String title = fd.getTitle().substring(0, extPos);
			String ext = fd.getTitle().substring(extPos + 1);

			query.setFullTextQuery(String.format(FILE_TITLE_FORMAT, title, ext));
			query.setStrict(true);
			query.setStringCustomParameter("kind", "photo");

			PhotoFeed feed = this.service.getFeed(query, PhotoFeed.class);

			for (GphotoEntry<?> entry : feed.getEntries()) {
				entry.delete();
			}

			return new OperationStatusDTO(200, SERVICE_UID);

		} catch (Exception e) {
			LOGGER.warn(e.toString());
			return new OperationStatusDTO(500, SERVICE_UID);
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

	private abstract class LookupStrategy {
		GphotoEntry<?> resourceLookup(FileDescriptorDTO fd) throws FileServiceException {
			GphotoEntry<?> entry;

			try {
				if (fd.getTargetService().equals(SERVICE_UID) && !fd.getFileInternalId().isEmpty()) {
					entry = GooglePhotosServiceAdapterImpl.this.service.getEntry(slashJoinUrl(FULL_URL,
							"photoid/" + fd.getFileInternalId()),
							PhotoEntry.class);
				} else {
					Query query = new Query(FULL_URL);

					int extPos = fd.getTitle().lastIndexOf('.');

					String title = fd.getTitle().substring(0, extPos);
					String ext = fd.getTitle().substring(extPos + 1);

					query.setFullTextQuery(String.format(FILE_TITLE_FORMAT, title, ext));
					query.setStrict(true);
					query.setMaxResults(10);
					query.setStringCustomParameter("kind", "photo");

					PhotoFeed feed = GooglePhotosServiceAdapterImpl.this.service.getFeed(query, PhotoFeed.class);

					entry = entrySelectionStrategy(feed);
				}
			} catch (Exception e) {
				LOGGER.error(e.toString());
				throw new FileServiceException(e);
			}

			return entry;
		}

		protected abstract GphotoEntry<?> entrySelectionStrategy(PhotoFeed feed) throws FileServiceException;
	}

	private class GetUniqueOnlyLS extends LookupStrategy {

		@SuppressWarnings("synthetic-access")
		protected GetUniqueOnlyLS() {
		}

		@Override
		protected GphotoEntry<?> entrySelectionStrategy(PhotoFeed feed) throws FileServiceException {
			if (feed.getEntries().isEmpty()) {
				throw new FileServiceException("Nothing found");
			} else if (feed.getEntries().size() > 1) {
				throw new FileServiceException(String.format("Ambiguous request [%s]", feed.getEntries().size()));
			} else {
				return feed.getEntries().get(0);
			}
		}
	}

	private class GetLatestLS extends LookupStrategy {

		@SuppressWarnings("synthetic-access")
		protected GetLatestLS() {
		}

		@Override
		protected GphotoEntry<?> entrySelectionStrategy(PhotoFeed feed) throws FileServiceException {
			if (feed.getEntries().isEmpty()) {
				throw new FileServiceException("Nothing found");
			} else {
				return Collections.max(feed.getEntries(), new Comparator<GphotoEntry>() {
					@Override
					public int compare(GphotoEntry o1, GphotoEntry o2) {
						if (o1.getPublished() != null)
							return o1.getPublished().compareTo(o2.getPublished());
						return 0;
					}
				});
			}
		}
	}

	public LookupStrategy getLatestFS() {
		if (this._latestFS == null) {
			this._latestFS = this.new GetLatestLS();

		}
		return this._latestFS;
	}

	public LookupStrategy getUniqueOnlyFS() {
		if (this._uniqueOnlyFS == null) {
			this._uniqueOnlyFS = this.new GetUniqueOnlyLS();

		}
		return this._uniqueOnlyFS;
	}

}
