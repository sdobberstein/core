package core.process.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.factory.DataPacketFactory;
import core.packet.factory.PacketFactory;
import core.process.Processor;

public class BrokerServiceTest {

	private BrokerService brokerService;
	
	@Before
	public void setUp() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Processor> processors = new ArrayList<Processor>();
		processors.add(new PrintProcessor());
		
		CallablePacketFactory callablePacketFactory = new DataProcessorFactory(processors, null);
		
		brokerService = new BrokerService(executorService, callablePacketFactory);
	}
	
	@Test
	public void test() {
		PacketFactory factory = new DataPacketFactory();
		Packet packet01 = factory.createWithNewId("string", "data01", new Properties());
		Packet packet02 = factory.createWithNewId("string", "data02", new Properties());
		Packet packet03 = factory.createWithNewId("string", "data03", new Properties());
		Packet packet04 = factory.createWithNewId("string", "data04", new Properties());
		Packet packet05 = factory.createWithNewId("string", "data05", new Properties());
		Packet packet06 = factory.createWithNewId("string", "data06", new Properties());
		
		brokerService.onMessage(packet01);
		brokerService.onMessage(packet02);
		brokerService.onMessage(packet03);
		brokerService.onMessage(packet04);
		brokerService.onMessage(packet05);
		brokerService.onMessage(packet06);
	}
	
	private class PrintProcessor implements Processor {

		@Override
		public Packet process(Packet packet) {			
			System.out.println(packet.getId() + " in Thread-" + Thread.currentThread().getName());
			return packet;
		}
		
	}
}
