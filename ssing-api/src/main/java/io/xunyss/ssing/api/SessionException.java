package io.xunyss.ssing.api;

/**
 * 
 * @author XUNYSS
 */
public class SessionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4892134787969546186L;

	public SessionException() {
		super();
	}

	public SessionException(String message) {
		super(message);
	}

	public SessionException(Throwable cause) {
		super(cause);
	}
	
	public SessionException(String message, Throwable cause) {
		super(message, cause);
	}
}
