package core.process;

import core.packet.Packet;

public interface Processor<D,M> {

	public void process(Packet<D,M> packet);
}
