package core.process.write;

import core.packet.Packet;

public class SimpleFileWriterConfiguration implements FileWriterConfiguration {

	private String baseDirectory;
	private boolean dataWritable = true;
	private boolean propertiesWritable = true;
	
	public SimpleFileWriterConfiguration(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
	
	@Override
	public boolean isDataWritable() {
		return dataWritable;
	}
	
	@Override
	public void setDataWritable(boolean dataWritable) {
		this.dataWritable = dataWritable;
	}
	
	@Override
	public boolean isPropertiesWritable() {
		return propertiesWritable;
	}
	
	@Override
	public void setPropertiesWritable(boolean propertiesWritable) {
		this.propertiesWritable = propertiesWritable;
	}

	@Override
	public String generateFolderName(Packet packet) {
		return null;
	}

	@Override
	public String generateFileName(Packet packet) {
		return packet.getId();
	}

	@Override
	public String getBaseDirectory() {
		return baseDirectory;
	}
}
