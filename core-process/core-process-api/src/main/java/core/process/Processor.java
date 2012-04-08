package core.process;

import core.packet.Packet;

public interface Processor {

	public Packet process(Packet packet);
}
