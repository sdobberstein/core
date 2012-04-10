package core.thruster;

import java.io.File;

import core.packet.Packet;
import core.process.read.FileReader;

public class FileThruster implements Thruster {

	private FileReader fileReader;
	
	@Override
	public Packet thrust() {
		File file = fileReader.read();
		
		// Create a packet from the file
		return null;
	}
}
