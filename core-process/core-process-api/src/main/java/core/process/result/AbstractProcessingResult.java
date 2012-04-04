package core.process.result;

public abstract class AbstractProcessingResult implements ProcessingResult {

	private boolean success;
	
	public AbstractProcessingResult() {
		this.success = false;
	}
	
	public AbstractProcessingResult(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
}
