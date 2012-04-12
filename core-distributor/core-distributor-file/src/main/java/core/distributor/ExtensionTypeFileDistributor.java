package core.distributor;

import core.packet.Packet;
import core.process.write.FileWriter;

public class ExtensionTypeFileDistributor extends GenericFileDistributor {

	private final String EXTENSION;
	
	public ExtensionTypeFileDistributor(FileWriter fileWriter, String extension) {
		super(fileWriter);
		this.EXTENSION = extension.toLowerCase();
	}
	
	@Override
	public boolean isSupported(Packet packet) {
		return packet.getMediaType().toLowerCase().endsWith(EXTENSION);
	}
}