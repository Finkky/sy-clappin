package cz.cvut.fit.culkajac.dp;

public class FileServiceException extends Exception {

	private static final long serialVersionUID = 583273371422922049L;

	public FileServiceException(String msg) {
		super(msg);
	}

	public FileServiceException(Exception e) {
		super(e);
	}

	public FileServiceException() {
	}
}
