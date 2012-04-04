package core.process.result;

import junit.framework.Assert;

import org.junit.Test;

public class ProcessingResultsTest {

	@Test
	public void testSuccessSameObject() {
		ProcessingResult result01 = ProcessingResults.success();
		ProcessingResult result02 = ProcessingResults.success();
		
		Assert.assertSame(result01, result02);
	}
	
	@Test
	public void testFailureSameObject() {
		ProcessingResult result01 = ProcessingResults.failure();
		ProcessingResult result02 = ProcessingResults.failure();
		
		Assert.assertSame(result01, result02);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCanAccessFailureResultThrowable() throws Throwable {
		ProcessingResult result03 = ProcessingResults.failure(new RuntimeException());
		
		Assert.assertTrue(!result03.isSuccess());
		throw ((FailureResult) result03).getException();
	}
}
