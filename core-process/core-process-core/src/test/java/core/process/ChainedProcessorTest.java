package core.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.Packets;

public class ChainedProcessorTest {

	private ChainedProcessor chainedProcessor;
	
	@Before
	public void setUp() {
		List<Processor> processors = new ArrayList<Processor>();
		processors.add(new FirstProcessor());
		processors.add(new SecondProcessor());
		processors.add(new ThirdProcessor());
		chainedProcessor = new ChainedProcessor(processors);
	}
	
	@Test
	public void testChain() {
		Packet packet = Packets.getInstance("111", "xml", "", new Properties());
		
		chainedProcessor.process(packet);
		
		Assert.assertEquals(3, packet.getProperties().size());
		Assert.assertEquals(1, packet.getProperties().get("first"));
		Assert.assertEquals(2, packet.getProperties().get("second"));
		Assert.assertEquals(3, packet.getProperties().get("third"));
	}
	
	private class FirstProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			packet.getProperties().put("first", 1);
			return packet;
		}
	}
	
	private class SecondProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			packet.getProperties().put("second", 2);
			return packet;
		}
	}
	
	private class ThirdProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {
			packet.getProperties().put("third", 3);
			return packet;
		}
	}
}
