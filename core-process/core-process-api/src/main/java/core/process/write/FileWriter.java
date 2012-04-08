package core.process.write;

import core.packet.Packet;

public interface FileWriter {

	public boolean writePacket(Packet packet);
	
	public boolean writePacket(Packet packet, FileWriterConfiguration configuration);
}
