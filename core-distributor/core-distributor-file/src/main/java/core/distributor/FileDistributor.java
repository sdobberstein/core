package core.distributor.file;

import java.util.Properties;

import core.distributor.DataPacketDistributor;
import core.packet.Packet;

public class FileDistributor implements DataPacketDistributor {

	public boolean distribute(Packet<Object, Properties> packet) {
		String data = (String) packet.getData();
		Properties properties = packet.getMetaData();
		
		// write out the data and (optionally) the properties to disk.
		
		return false;
	}

	public boolean isSupported(Object data) {
		return (data instanceof String);
	}
}
