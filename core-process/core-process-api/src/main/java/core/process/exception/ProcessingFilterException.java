package core.process.exception;

public class ProcessingFilterException extends RuntimeException {

	private static final long serialVersionUID = -5210218913060321422L;

	public ProcessingFilterException() {
		super();
	}
	
	public ProcessingFilterException(String message) {
		super(message);
	}
	
	public ProcessingFilterException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ProcessingFilterException(Throwable cause) {
		super(cause);
	}
}
