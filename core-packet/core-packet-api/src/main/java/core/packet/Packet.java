package core.packet;

public interface Packet<D,M> {

	public String getPacket();
	
	public D getData();
	
	public M getMetaData();
	
	public History getHistory();
}