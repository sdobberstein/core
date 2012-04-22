package core.process.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import core.packet.Packet;
import core.process.Broker;

public class BrokerService implements Broker {

	private final ExecutorService executorService;
	private final CallablePacketFactory callablePacketFactory;
	
	public BrokerService(ExecutorService executorService, CallablePacketFactory callablePacketFactory) {
		this.executorService = executorService;
		this.callablePacketFactory = callablePacketFactory;
	}
	
	public void onMessage(Packet packet) {
		// CREATE A NEW CALLABLE
		Callable<Packet> callable = callablePacketFactory.getInstance(packet);
		
		// SEND PACKET OFF INTO ITS OWN THREAD
		executorService.submit(callable);
	}
}
