package core.process;

import java.util.List;

import core.packet.Packet;

public interface FailureProcessor {
	
	public Packet onFail(Packet packet, List<Throwable> exceptions);
}
