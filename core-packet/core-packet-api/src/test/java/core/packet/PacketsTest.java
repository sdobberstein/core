package core.packet;

import org.junit.Assert;
import org.junit.Test;

public class PacketsTest {
	
	@Test
	public void testDifferentDataPacket() {
		DataPacket dataPacket = new DataPacket("id", "mediaType", "data");
		dataPacket.getProperties().put("test01", "testValue");
		dataPacket.getProperties().put(Object.class, new Object());
		
		Packet newPacket = Packets.getInstance(dataPacket.getId(), dataPacket.getMediaType(), 
				                               "otherdata", dataPacket.getProperties());
		
		Assert.assertTrue(!dataPacket.equals(newPacket));
	}
}
