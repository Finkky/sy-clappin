package cz.cvut.fit.culkajac.dp.services.googlephotos.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.inject.Singleton;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gdata.client.Query;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.IEntry;
import com.google.gdata.data.IFeed;
import com.google.gdata.data.media.IMediaContent;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import cz.cvut.fit.culkajac.dp.Constants;

class ExpBackOffPicasawebService extends PicasawebService {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ExpBackOffPicasawebService.class);

	public ExpBackOffPicasawebService(String applicationName) {
		super(applicationName);
	}

	@Override
	public <E extends IEntry> E getEntry(URL entryUrl, Class<E> entryClass) throws IOException, ServiceException {
		E e = null;
		for (int i = 0; i < 5; i++) {
			try {
				e = super.getEntry(entryUrl, entryClass);
				break;
			} catch (Exception ex) {
				expWait(i, "GET ENTRY");
			}
		}
		return e;
	}

	@Override
	public <F extends IFeed> F getFeed(Query query, Class<F> feedClass) throws IOException, ServiceException {
		F e = null;
		for (int i = 0; i < 5; i++) {
			try {
				e = super.getFeed(query, feedClass);
				break;
			} catch (Exception ex) {
				expWait(i, "GET FEED");
			}
		}
		return e;
	}

	@Override
	public MediaSource getMedia(IMediaContent mediaContent) throws IOException, ServiceException {
		MediaSource e = null;
		for (int i = 0; i < 5; i++) {
			try {
				e = super.getMedia(mediaContent);
				break;
			} catch (Exception ex) {
				expWait(i, "GET MEDIA");
			}
		}
		return e;
	}

	@Override
	public void delete(URI resourceUri, String etag) throws IOException, ServiceException {
		for (int i = 0; i < 5; i++) {
			try {
				super.delete(resourceUri, etag);
				return;
			} catch (Exception ex) {
				expWait(i, "DELETE");
			}
		}
	}

	private static void expWait(int exp, String methodName) {
		long bf = 1000 * (long) (Math.pow(2, exp)) + RandomUtils.nextInt(1000);
		LOGGER.info("BACKING OFF EXPONENTIALLY [" + exp + "] : " + methodName);
		try {
			Thread.sleep(bf);
		} catch (InterruptedException e1) {
		}
	}
}

@Singleton
// @Logged
public class GooglePhotoAPIService {

	private static final String GP_USER_LOGIN = Constants.Picasa.USER_LOGIN;
	private static final String GP_USER_PASSWORD = Constants.Picasa.USER_PASSWORD;
	private static final String GP_APP_NAME = Constants.Picasa.APP_NAME;
	private static final Logger LOGGER = LoggerFactory.getLogger(GooglePhotoAPIService.class);

	private PicasawebService service;

	public GooglePhotoAPIService() throws AuthenticationException {
		this.service = new ExpBackOffPicasawebService(GP_APP_NAME);
		this.service.setUserCredentials(GP_USER_LOGIN, GP_USER_PASSWORD);
	}

	public PicasawebService getService() {
		return this.service;
	}
}
