package core.packet;

import java.util.Properties;

public final class DataPacket implements Packet {

	private final String id;
	private final String mediaType;
	private final String data;
	private final Properties properties;
	
	public DataPacket(String id, String mediaType, String data) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be null.");
		} else if (mediaType == null) {
			throw new IllegalArgumentException("MediaType cannot be null.");
		}
		
		this.id = id;
		this.mediaType = mediaType;
		this.data = data;
		this.properties = new Properties();
	}
	
	public String getId() {
		return id;
	}

	public String getMediaType() {
		return mediaType;
	}

	public String getData() {
		return data;
	}

	public Properties getProperties() {
		return properties;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DataPacket)) {
			return false;
		}
		
		DataPacket otherPacket = (DataPacket) other;
		return ((this.id.equals(otherPacket.getId()))
				&& (this.mediaType.equals(otherPacket.getMediaType()))
				&& (this.data == null ? otherPacket.getData() == null : this.data.equals(otherPacket.getData()))
				&& (this.properties.equals(otherPacket.getProperties())));
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 17;
		
		result = PRIME * result + this.id.hashCode();
		result = PRIME * result + this.mediaType.hashCode();
		result = PRIME * result + (this.data == null ? 0 : this.data.hashCode());
		
		return result;
	}
}
