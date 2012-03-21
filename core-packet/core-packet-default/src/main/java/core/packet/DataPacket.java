package core.packet;

import java.util.Properties;

public class DataPacket implements Packet<Object, Properties> {

	private String packet;
	private Object data;
	private Properties metaData;
	private History history;
	
	public DataPacket(String packet) {
		this(packet, null);
	}
	
	public DataPacket(String packet, Object data) {
		this(packet, data, new Properties());
	}
	
	public DataPacket(String packet, Object data, Properties metaData) {
		this(packet, data, metaData, new InMemoryHistory());
	}
	
	public DataPacket(String packet, Object data, Properties metaData, History history) {
		this.packet = packet;
		this.data = data;
		this.metaData = metaData;
		this.history = history;
	}
	
	public String getPacket() {
		return packet;
	}

	public Object getData() {
		return data;
	}

	public Properties getMetaData() {
		return metaData;
	}

	public History getHistory() {
		return history;
	}

}
