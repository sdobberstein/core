package core.thruster;

import java.io.File;
import java.util.Properties;

import core.packet.Packet;
import core.packet.factory.PacketFactory;
import core.process.read.FileReader;
import core.thruster.utils.FileToString;

public class FileThruster implements Thruster {

	private FileReader fileReader;
	private PacketFactory packetFactory;
	
	@Override
	public Packet thrust() {
		File file = fileReader.read();
		
		return packetFactory.createWithNewId("unknown", FileToString.fileToString(file), new Properties());
	}

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public PacketFactory getPacketFactory() {
        return packetFactory;
    }

    public void setPacketFactory(PacketFactory packetFactory) {
        this.packetFactory = packetFactory;
    }
}
