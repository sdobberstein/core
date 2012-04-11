package core.distributor.exception;

public class DistributionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DistributionException() {
		super();
	}
	
	public DistributionException(String message) {
		super(message);
	}
	
	public DistributionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DistributionException(Throwable cause) {
		super(cause);
	}
}
