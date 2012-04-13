package core.thruster;

import java.io.File;
import java.util.Properties;

import core.packet.Packet;
import core.packet.factory.PacketFactory;
import core.process.read.FileReader;
import core.thruster.utils.ThrusterUtils;

public class FileThruster implements Thruster {

	private FileReader fileReader;
	private PacketFactory packetFactory;
	private String mediaType = "text/unknown";
	
	@Override
	public Packet thrust() {
		File file = fileReader.read();
		
		if (file == null) {
		    return null;
		} else {
		    return packetFactory.createWithNewId(mediaType, ThrusterUtils.fileToString(file), new Properties());
		}
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
    
    public String getMediaType() {
        return mediaType;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
