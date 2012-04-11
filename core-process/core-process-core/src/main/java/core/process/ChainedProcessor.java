package core.process;

import java.util.List;

import core.packet.Packet;

public class ChainedProcessor implements Processor {

	private final List<Processor> processors;
	
	public ChainedProcessor(List<Processor> processors) {
		this.processors = processors;
	}
	
	@Override
	public Packet process(Packet packet) {
		for (Processor processor : processors) {
			packet = processor.process(packet);
		}
		
		return packet;
	}
}
