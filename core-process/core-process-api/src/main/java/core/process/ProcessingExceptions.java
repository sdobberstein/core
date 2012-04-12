package core.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessingExceptions {

	private final Throwable originalException;
	private final String originalMessage;
	
	private List<Throwable> throwables;
	
	public ProcessingExceptions(Throwable originalException) {
		if (originalException == null) {
			throw new IllegalArgumentException("Original Exception cannot be null!");
		}
		
		this.originalException = originalException;
		this.originalMessage = this.originalException.getMessage();
		this.throwables = new ArrayList<Throwable>();
		this.add(originalException);
	}
	
	public void add(Throwable throwable) {
		this.throwables.add(throwable);
	}

	public Throwable getOriginalException() {
		return originalException;
	}

	public String getOriginalMessage() {
		return originalMessage;
	}
	
	public Throwable getPreviousException() {
		// If there is only one exception, the original, 
		// then we can just return that one.
		if (this.throwables.size() == 1) {
			return getLastException();
		} else {
			return this.throwables.get(this.size() - 2);
		}
	}
	
	public Throwable getLastException() {
		// The list should always contain one element, so
		// this should not throw an ArrayIndexOutOfBounds.
		return this.throwables.get(this.size() - 1);		
	}
	
	public List<Throwable> getThrowables() {
		// Return back a copy of this list, not the
		// actual list itself.
		return Collections.unmodifiableList(this.throwables);
	}
	
	public int size() {		
		return this.throwables.size();
	}
}