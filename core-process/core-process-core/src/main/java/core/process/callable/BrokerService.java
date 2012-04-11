package core.process.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import core.packet.Packet;

public class BrokerService {

	private final ExecutorService executorService;
	private final CallablePacketFactory callablePacketFactory;
	
	public BrokerService(ExecutorService executorService, CallablePacketFactory callablePacketFactory) {
		this.executorService = executorService;
		this.callablePacketFactory = callablePacketFactory;
	}
	
	public void execute(Packet packet) {
		// CREATE A NEW CALLABLE
		Callable<Packet> callable = callablePacketFactory.getInstance(packet);
		
		// SEND PACKET OFF INTO ITS OWN THREAD
		executorService.submit(callable);
	}
}
