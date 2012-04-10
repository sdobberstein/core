package core.distributor;

import core.packet.Packet;
import core.process.write.FileWriter;

public class XmlFileDistributor implements Distributor {

	private static final String XML_EXTENSION = "xml";
	
	private final FileWriter fileWriter;
	
	public XmlFileDistributor(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	@Override
	public void distribute(Packet packet) {
		fileWriter.writePacket(packet);
	}

	@Override
	public boolean isSupported(String mediaType) {
		return mediaType.toLowerCase().endsWith(XML_EXTENSION);
	}

	@Override
	public boolean isSupported(Packet packet) {
		return isSupported(packet.getMediaType());
	}
}
