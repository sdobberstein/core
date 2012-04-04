package core.process.result;

/**
 * Centralizes the creation of ProcessingResults.
 */
public final class ProcessingResults {

	private static final ProcessingResult SUCCESS = new AbstractProcessingResult(true) {};
	private static final ProcessingResult FAILURE = new AbstractProcessingResult(false) {};
	
	private ProcessingResults() {}
	
	public static ProcessingResult success() {
		return SUCCESS;
	}
	
	public static ProcessingResult failure() {
		return FAILURE;
	}
	
	public static ProcessingResult failure(Throwable cause) {
		return new FailureResult(cause);
	}
}