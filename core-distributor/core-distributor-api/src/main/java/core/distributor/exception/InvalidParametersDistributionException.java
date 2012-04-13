package core.distributor.exception;

public class InvalidParametersDistributionException extends	DistributionException {

	private static final long serialVersionUID = -1664823435146835384L;

	public InvalidParametersDistributionException() {
		super();
	}
	
	public InvalidParametersDistributionException(String message) {
		super(message);
	}
	
	public InvalidParametersDistributionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidParametersDistributionException(Throwable cause) {
		super(cause);
	}
}
