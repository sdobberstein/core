package core.distributor;

import core.packet.Packet;
import core.process.write.FileWriter;

public class GenericFileDistributor implements Distributor {

    private final FileWriter fileWriter;
	
	public GenericFileDistributor(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	@Override
	public void distribute(Packet packet) {
		fileWriter.writePacket(packet);
	}

	@Override
	public boolean isSupported(Packet packet) {
		// AS LONG AS THERE IS DATA, THEN ITS SUPPORTED.
		return (packet.getData() != null);
	}
}
