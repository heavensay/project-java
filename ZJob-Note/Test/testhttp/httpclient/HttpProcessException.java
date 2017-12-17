package testhttp.httpclient;



/** 
 * 
 */
public class HttpProcessException  extends Exception {

	/**
	 * 
	 */

	public HttpProcessException(Exception e){
		super(e);
	}

	/**
	 * @param string
	 */
	public HttpProcessException(String msg) {
		super(msg);
	}
	
	/**
	 * @param message
	 * @param e
	 */
	public HttpProcessException(String message, Exception e) {
		super(message, e);
	}
	
}
