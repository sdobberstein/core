package core.packet;

public interface Packet<D,M> {

	public String getPacket();
	
	public D getData();
	
	public String getMediaType();
	
	public M getMetaData();
	
	public History getHistory();
}