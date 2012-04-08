package core.distributor;

import core.packet.Packet;
import core.process.write.FileWriter;
import core.process.write.FileWriterConfiguration;

public class XmlFileDistributor implements Distributor {

	private static final String XML_EXTENSION = "xml";
	
	private final FileWriter fileWriter;
	private final FileWriterConfiguration fileWriterConfiguration;
	
	public XmlFileDistributor(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
		this.fileWriterConfiguration = null;
	}
	
	public XmlFileDistributor(FileWriter fileWriter, FileWriterConfiguration fileWriterConfiguration) {
		this.fileWriter = fileWriter;
		this.fileWriterConfiguration = fileWriterConfiguration;
	}
	
	@Override
	public boolean distribute(Packet packet) {
		if (this.fileWriterConfiguration == null) {
			return fileWriter.writePacket(packet);
		} else {
			return fileWriter.writePacket(packet, this.fileWriterConfiguration);			
		}
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
