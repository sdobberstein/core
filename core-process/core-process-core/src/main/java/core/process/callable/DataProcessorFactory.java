package core.process.callable;

import java.util.List;
import java.util.concurrent.Callable;

import core.packet.Packet;
import core.process.FailureProcessor;
import core.process.Processor;

public class DataProcessorFactory implements CallablePacketFactory {

	private final List<Processor> processors;
	private final List<FailureProcessor> failureProcessors;
	
	public DataProcessorFactory(List<Processor> processors, List<FailureProcessor> failureProcessors) {
		this.processors = processors;
		this.failureProcessors = failureProcessors;
	}
	
	@Override
	public Callable<Packet> getInstance(Packet packet) {
		return new DataProcessor(packet, processors, failureProcessors);
	}
}
