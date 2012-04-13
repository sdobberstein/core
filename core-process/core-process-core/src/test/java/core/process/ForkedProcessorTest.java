package core.process;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.Packets;

public class ForkedProcessorTest {

	private ForkedProcessor processor;
	
	@Before
	public void setUp() {		
		processor = new StubForkedProcessor(new StubTrueProcessor(), new StubFalseProcessor());
	}
	
	@Test
	public void testTrueCondition() {
		Properties properties = new Properties();
		properties.put("test", "blah blah");
		Packet packet = Packets.getInstance("111", "xml", "aaaa", properties);
		
		boolean success = processor.checkCondition(packet);
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void testFalseCondition() {
		Properties properties = new Properties();
		Packet packet = Packets.getInstance("111", "xml", "aaaa", properties);
		
		boolean success = processor.checkCondition(packet);
		
		Assert.assertTrue(!success);
	}
	
	@Test
	public void testTrueProcessor() {
		Properties properties = new Properties();
		properties.put("test", "blah blah");
		Packet packet = Packets.getInstance("111", "xml", "aaaa", properties);
		
		processor.process(packet);
		
		Assert.assertTrue(packet.getProperties().containsKey("AAA"));
		Assert.assertEquals("passed", packet.getProperties().get("AAA"));
	}
	
	@Test
	public void testFalseProcessor() {
		Properties properties = new Properties();
		Packet packet = Packets.getInstance("111", "xml", "aaaa", properties);
		
		processor.process(packet);
		
		Assert.assertTrue(packet.getProperties().containsKey("BBB"));
		Assert.assertEquals("failed", packet.getProperties().get("BBB"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullTrueProcessorThrowsException() {
		processor = new StubForkedProcessor(null, new StubFalseProcessor());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullFalseProcessorThrowsException() {
		processor = new StubForkedProcessor(new StubTrueProcessor(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullProcessorsThrowsException() {
		processor = new StubForkedProcessor(null, null);
	}
	
	private class StubFalseProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			packet.getProperties().put("BBB", "failed");
			return packet;
		}
		
	}
	
	private class StubTrueProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			packet.getProperties().put("AAA", "passed");
			return packet;
		}
		
	}
	
	private class StubForkedProcessor extends ForkedProcessor {

		public StubForkedProcessor(Processor trueProcessor,	Processor falseProcessor) {
			super(trueProcessor, falseProcessor);
		}

		@Override
		protected boolean checkCondition(Packet packet) {
			return packet.getProperties().containsKey("test");
		}
	}
}
