package core.process;

import core.packet.Packet;
import core.process.result.ProcessingResult;

public interface Processor<D,M> {

	public ProcessingResult process(Packet<D,M> packet);
}
