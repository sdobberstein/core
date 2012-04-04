package core.packet;

import core.packet.history.History;

public interface Packet<D,M> {

	public String getPacket();
	
	public D getData();
	
	public M getMetaData();
	
	public History getHistory();
}