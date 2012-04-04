package core.process;

import java.util.List;

import core.packet.Packet;
import core.process.result.ProcessingResult;

public interface FailureProcessor<D,M> {
	
	public ProcessingResult onFail(Packet<D,M> packet, List<Throwable> exceptions);
}
