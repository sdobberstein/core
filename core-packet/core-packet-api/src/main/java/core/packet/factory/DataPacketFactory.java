package core.packet.factory;

import java.util.Properties;
import java.util.UUID;

import core.packet.DataPacket;
import core.packet.Packet;

public class DataPacketFactory implements PacketFactory {

	@Override
	public Packet createWithNewId(String mediaType, String data, Properties properties) {
		UUID uuid = UUID.randomUUID();
		return new DataPacket(uuid.toString(), mediaType, data, properties);
	}

	@Override
	public Packet create(String id, String mediaType, String data, Properties properties) {
		return new DataPacket(id, mediaType, data, properties);
	}

	@Override
	public Packet valueOf(Packet packet) {
		return new DataPacket(packet.getId(), packet.getMediaType(), packet.getData(), packet.getProperties());
	}
}
