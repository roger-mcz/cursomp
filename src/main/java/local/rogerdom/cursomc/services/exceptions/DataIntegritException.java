package local.rogerdom.cursomc.services.exceptions;

public class DataIntegritException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegritException(String msg) {
		super(msg);
	}

	public DataIntegritException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
