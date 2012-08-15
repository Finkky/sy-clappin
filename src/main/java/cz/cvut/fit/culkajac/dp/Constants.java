package cz.cvut.fit.culkajac.dp;

public interface Constants {

	@SuppressWarnings("hiding")
	public static interface AmazonS3 {
		public static final String SERVICE_UID = "AmazonS3";
		public static final String ROUTE_ID = SERVICE_UID;
		/*----------------------------------------------------------------------*/
		public static final String BUCKET = "clappin";
		//
		public static final String ACCESS_KEY = "_______";
		public static final String SECRET_ACCESS_KEY = "_______";

		public static interface SOAP {
			public static final String ROUTE_ID = "AmazonS3SOAP";
		}

		public static interface API {
			public static final String ROUTE_ID = "AmazonS3API";
		}
	}

	public static interface Dropbox {
		public static final String SERVICE_UID = "Dropbox";
		public static final String ROUTE_ID = SERVICE_UID;
		/*----------------------------------------------------------------------*/
		public static final String APP_KEY = "_______";
		public static final String APP_SECRET = "_______";
		/*----------------------------------------------------------------------*/
		public static final String USER_TOKEN_KEY = "_______";
		public static final String USER_TOKEN_SECRET = "_______";
		//
		public static final String CURSOR_FILE_NAME = ".clappin";
	}

	public static interface GoogleDocs {
		public static final String SERVICE_UID = "GoogleDocs";
		public static final String ROUTE_ID = SERVICE_UID;
		/*----------------------------------------------------------------------*/
		public static final String USER_LOGIN = "_______@gmail.com";
		public static final String USER_PASSWORD = "_______";
		//
		public static final String APP_NAME = "culka-cloudAppIntegration-0.1";
	}

	public static interface Picasa {
		public static final String SERVICE_UID = "GooglePhotos";
		public static final String ROUTE_ID = SERVICE_UID;
		/*----------------------------------------------------------------------*/
		public static final String USER_LOGIN = "_______@gmail.com";
		public static final String USER_PASSWORD = "_______";
		//
		public static final String APP_NAME = "culka-cloudAppIntegration-0.1";
	}
}
