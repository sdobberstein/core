package core.packet;

import java.util.Map.Entry;
import java.util.Properties;

public class Packets {

	private Packets() {
	}
	
	public static DataPacket newDataPacket(String id, String mediaType, String data, Properties properties) {
		DataPacket dataPacket = new DataPacket(id, mediaType, data);
		
		for (Entry<Object, Object> entry : properties.entrySet()) {
			dataPacket.getProperties().put(entry.getKey(), entry.getValue());
		}
		
		return dataPacket;
	}
}
