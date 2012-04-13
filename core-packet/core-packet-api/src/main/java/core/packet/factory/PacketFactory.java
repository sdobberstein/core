package core.packet.factory;

import java.util.Properties;

import core.packet.Packet;

public interface PacketFactory {
	
	public Packet createWithNewId(String mediaType, String data, Properties properties);

	public Packet create(String id, String mediaType, String data, Properties properties);
	
	public Packet valueOf(Packet packet);
}
