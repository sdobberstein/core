package core.process;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import core.packet.Packet;
import core.packet.Packets;
import core.process.exception.ProcessingFilterException;

public class ErrorHandlingProcessorTest {

	private ErrorHandlingProcessor errorHandlingProcessor;
	
	@Test
	public void testHandledByFailureProcessor() {
		Packet packet = Packets.getInstance("111", "xml", "", new Properties());
		
		errorHandlingProcessor = new ErrorHandlingProcessor(new ThrowsExceptionProcessor(), new FailureProcessorImpl());
		errorHandlingProcessor.process(packet);
		
		Assert.assertEquals(1, packet.getProperties().size());
		Assert.assertTrue(packet.getProperties().containsKey("FAILED"));
		Assert.assertEquals("true", packet.getProperties().get("FAILED"));
	}
	
	@Test
	public void testHandledByProcessorSuccessfully() {
		Packet packet = Packets.getInstance("111", "xml", "", new Properties());
		
		errorHandlingProcessor = new ErrorHandlingProcessor(new ProcessorImpl(), new FailureProcessorImpl());
		errorHandlingProcessor.process(packet);
		
		Assert.assertEquals(1, packet.getProperties().size());
		Assert.assertTrue(!packet.getProperties().containsKey("FAILED"));
		Assert.assertTrue(packet.getProperties().containsKey("SUCCESS"));
		Assert.assertEquals("true", packet.getProperties().get("SUCCESS"));
	}
	
	@Test(expected=ProcessingFilterException.class)
	public void testThrowProcessingFilterException() {
		Packet packet = Packets.getInstance("111", "xml", "", new Properties());

		errorHandlingProcessor = new ErrorHandlingProcessor(new ProcessingFilterExceptionProcessor(), new FailureProcessorImpl());
		errorHandlingProcessor.process(packet);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullProcessorThrowsException() {
		errorHandlingProcessor = new ErrorHandlingProcessor(null, new FailureProcessorImpl());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullFailureProcessorThrowsException() {
		errorHandlingProcessor = new ErrorHandlingProcessor(new ProcessorImpl(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullValuesThrowsException() {
		errorHandlingProcessor = new ErrorHandlingProcessor(null, null);
	}
	
	private class ProcessorImpl implements Processor {

		@Override
		public Packet process(Packet packet) {
			packet.getProperties().put("SUCCESS", "true");
			return packet;
		}
	}
	
	private class ProcessingFilterExceptionProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			throw  new ProcessingFilterException();
		}
	}
	
	private class ThrowsExceptionProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			throw  new RuntimeException();
		}
	}
	
	private class FailureProcessorImpl implements FailureProcessor {

		@Override
		public Packet onFail(Packet packet, ProcessingExceptions exceptions) {
			System.out.println(exceptions.getLastException());
			packet.getProperties().put("FAILED", "true");
			return packet;
		}
		
	}
}