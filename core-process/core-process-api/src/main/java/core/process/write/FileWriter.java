package core.process.write;

import core.packet.Packet;

public interface FileWriter {

	public void writePacket(Packet packet);
	
	public void writePacket(Packet packet, FileWriterConfiguration configuration);
}
