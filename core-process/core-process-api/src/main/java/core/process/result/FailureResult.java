package core.process.result;

public class FailureResult extends AbstractProcessingResult {

	private final Throwable exception;
	
	public FailureResult(Throwable exception) {
		super(false);
		this.exception = exception;
	}

	public Throwable getException() {
		return exception;
	}
}
