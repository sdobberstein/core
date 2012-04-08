package core.process.write;

import core.packet.Packet;

public interface FileWriterConfiguration {

	public String generateFolderName(Packet packet);
	
	public String generateFileName(Packet packet);
	
	public String getBaseDirectory();
	
	public boolean isDataWritable();
	
	public void setDataWritable(boolean dataWritable);
	
	public boolean isPropertiesWritable();
	
	public void setPropertiesWritable(boolean propertiesWritable);
}