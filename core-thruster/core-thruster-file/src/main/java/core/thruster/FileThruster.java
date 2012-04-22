package core.thruster;

import java.io.File;
import java.util.Properties;

import core.packet.Packet;
import core.process.read.FileReader;
import core.thruster.utils.ThrusterUtils;

public class FileThruster extends AbstractThruster {

	private FileReader fileReader;
	
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
}
