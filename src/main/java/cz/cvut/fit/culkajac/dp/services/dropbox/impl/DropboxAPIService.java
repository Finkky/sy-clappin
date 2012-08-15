package cz.cvut.fit.culkajac.dp.services.dropbox.impl;

import javax.inject.Singleton;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;

import cz.cvut.fit.culkajac.dp.Constants;

@Singleton
// @Named
public class DropboxAPIService {

	private static final String APP_KEY = Constants.Dropbox.APP_KEY;
	private static final String APP_SECRET = Constants.Dropbox.APP_SECRET;
	private static final AppKeyPair ACCESS_KEY_PAIR = new AppKeyPair(APP_KEY, APP_SECRET);
	private static final AccessTokenPair ACCESS_TOKEN_PAIR = new AccessTokenPair(Constants.Dropbox.USER_TOKEN_KEY,
			Constants.Dropbox.USER_TOKEN_SECRET);

	private DropboxAPI<WebAuthSession> service;

	public DropboxAPIService() {
		this.service = new DropboxAPI<WebAuthSession>(new WebAuthSession(ACCESS_KEY_PAIR,
				AccessType.APP_FOLDER,
				ACCESS_TOKEN_PAIR));
	}

	public DropboxAPIService(WebAuthSession was) {
		this.service = new DropboxAPI<WebAuthSession>(was);
	}

	public DropboxAPI<WebAuthSession> getService() {
		return this.service;
	}
}
