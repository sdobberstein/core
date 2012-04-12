package core.process;

import java.util.List;

import core.packet.Packet;

/**
 * The ChainedProcessor allows you to force groups of related Processor's
 * to run in a set order.  This implementation does not contain error handling
 * logic, so any errors thrown by the Processor's which propagate up to this level
 * will continue upwards without hindrance.
 * 
 *  Because the ChainedProcessor implements Processor, it can be plugged in anywhere
 *  that a normal Processor can.  This can lead to all sorts of configurations, such
 *  as hierarchical chaining, chaining on forks, etc.
 *
 */
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