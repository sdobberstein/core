package core.distributor;

import core.packet.Packet;

public interface Distributor {

	public void distribute(Packet packet);
	
	public boolean isSupported(String mediaType);
	
	public boolean isSupported(Packet packet);
}
