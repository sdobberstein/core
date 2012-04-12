package core.process;

import core.packet.Packet;

public interface FailureProcessor {

	public Packet onFail(Packet packet, ProcessingExceptions exceptions);
}
