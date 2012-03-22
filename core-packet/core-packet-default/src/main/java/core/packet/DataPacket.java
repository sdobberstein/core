package core.packet;

import java.util.Properties;

public class DataPacket implements Packet<Object, Properties>, MediaType {

	private String packet;
	private Object data;
	private String mediaType;
	private Properties metaData;
	private History history;
	
	public DataPacket(String packet) {
		this(packet, null, null);
	}
	
	public DataPacket(String packet, Object data, String mediaType) {
		this(packet, data, mediaType, new Properties());
	}
	
	public DataPacket(String packet, Object data, String mediaType, Properties metaData) {
		this(packet, data, mediaType, metaData, new InMemoryHistory());
	}
	
	public DataPacket(String packet, Object data, String mediaType, Properties metaData, History history) {
		this.packet = packet;
		this.data = data;
		this.mediaType = mediaType;
		this.metaData = metaData;
		this.history = history;
	}
	
	public String getPacket() {
		return packet;
	}

	public Object getData() {
		return data;
	}
	
	public String getMediaType() {
		return mediaType;
	}

	public Properties getMetaData() {
		return metaData;
	}

	public History getHistory() {
		return history;
	}

}
