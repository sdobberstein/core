package core.process;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ProcessingExceptionsTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNullExceptionThrowsException() {
		new ProcessingExceptions(null);
	}
	
	@Test
	public void testGetOriginalException() {
		// Create the original exception
		Throwable t = new RuntimeException("test");
		
		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);
		
		// Try to get the exception back.
		Throwable orig = exceptions.getOriginalException();
		
		Assert.assertEquals(t, orig);
	}
	
	@Test
	public void testGetOriginalMessage() {
		// Create the original exception
		Throwable t = new RuntimeException("test");
		
		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);
		
		// Try to get the message back.
		String orig = exceptions.getOriginalMessage();
		
		Assert.assertEquals("test", orig);
	}
	
	@Test
	public void testSize() {
		// Create the original exception
		Throwable t = new RuntimeException("test");

		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);

		// Check the size.  There should only be one.
		Assert.assertEquals(1, exceptions.size());
		
		// Add another.
		exceptions.add(new RuntimeException());
		
		// Make sure the size went up.
		Assert.assertEquals(2, exceptions.size());
	}
	
	@Test
	public void testGetLastExceptionReturnsOriginalForSizeOneList() {
		// Create the original exception
		Throwable t = new RuntimeException("test");
		
		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);
		
		// Try to get the exception back.
		Throwable orig = exceptions.getLastException();
		
		Assert.assertEquals(t, orig);
	}
	
	@Test
	public void testGetPreviousExceptionReturnsOriginalForSizeOneList() {
		// Create the original exception
		Throwable t = new RuntimeException("test");
		
		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);
		
		// Try to get the exception back.
		Throwable orig = exceptions.getPreviousException();
		
		Assert.assertEquals(t, orig);
	}
	
	@Test
	public void testGetPreviousExceptionReturnsOriginalForSizeTwoList() {
		// Create the original exception
		Throwable t = new RuntimeException("test");
		
		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);
		
		// Add a second exception.
		exceptions.add(new RuntimeException("next"));
		
		// Try to get the exception back.
		Throwable orig = exceptions.getPreviousException();
		
		Assert.assertEquals(t, orig);
		Assert.assertEquals("test", orig.getMessage());
	}
	
	@Test
	public void testGetLastExceptionReturnsLatestExceptionForSizeTwoList() {
		// Create the original exception
		Throwable t = new RuntimeException("test");
		
		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);
		
		// Add a second exception.
		RuntimeException re = new RuntimeException("next");
		exceptions.add(re);
		
		// Try to get the exception back.
		Throwable last = exceptions.getLastException();
		
		Assert.assertEquals(re, last);
	}
	
	@Test
	public void testGetList() {
		// Create the original exception
		Throwable t = new RuntimeException("test");

		// Add it to the processing exceptions
		ProcessingExceptions exceptions = new ProcessingExceptions(t);

		// Add a second exception.
		RuntimeException re = new RuntimeException("next");
		exceptions.add(re);

		// Try to get the list of exceptions
		List<Throwable> throwables = exceptions.getThrowables();

		Assert.assertEquals(2, throwables.size());
	}
}