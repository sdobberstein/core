package core.distributor;

import core.packet.Packet;

public interface Distributor<D,M> {

	public boolean distribute(Packet<D,M> packet);
	
	public boolean isSupported(D data);
}
