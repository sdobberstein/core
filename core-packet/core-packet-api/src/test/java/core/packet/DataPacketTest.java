package core.packet;

import org.junit.Assert;
import org.junit.Test;

public class DataPacketTest {

	@Test
	public void testAccessId() {
		DataPacket dataPacket = new DataPacket("id", "mediaType", "data");
		
		Assert.assertEquals("id", dataPacket.getId());
	}
	
	@Test
	public void testAccessMediaType() {
		DataPacket dataPacket = new DataPacket("id", "mediaType", "data");
		
		Assert.assertEquals("mediaType", dataPacket.getMediaType());
	}
	
	@Test
	public void testAccessData() {
		DataPacket dataPacket = new DataPacket("id", "mediaType", "data");
		
		Assert.assertEquals("data", dataPacket.getData());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIdCannotBeNull() {
		@SuppressWarnings("unused")
		DataPacket dataPacket = new DataPacket(null, "mediaType", "data");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMediaTypeCannotBeNull() {
		@SuppressWarnings("unused")
		DataPacket dataPacket = new DataPacket("id", null, "data");
	}
	
	@Test
	public void testAddProperty() {
		DataPacket dataPacket = new DataPacket("id", "mediaType", "data");
		dataPacket.getProperties().put("test", "testValue");
		
		Assert.assertEquals("testValue", dataPacket.getProperties().get("test"));
	}
	
	@Test
	public void testEquals() {
		DataPacket dataPacket01 = new DataPacket("id", "mediaType", "data");
		DataPacket dataPacket02 = new DataPacket("id", "mediaType", "data");
		
		Assert.assertTrue(dataPacket01.equals(dataPacket02));
	}
	
	@Test
	public void testNotEqualsDifferentId() {
		DataPacket dataPacket01 = new DataPacket("id", "mediaType", "data");
		DataPacket dataPacket02 = new DataPacket("ID", "mediaType", "data");
		
		Assert.assertTrue(!dataPacket01.equals(dataPacket02));
	}
	
	@Test
	public void testNotEqualsDifferentMediaType() {
		DataPacket dataPacket01 = new DataPacket("id", "mediaType", "data");
		DataPacket dataPacket02 = new DataPacket("id", "MEDIATYPE", "data");
		
		Assert.assertTrue(!dataPacket01.equals(dataPacket02));
	}
	
	@Test
	public void testNotEqualsDifferentData() {
		DataPacket dataPacket01 = new DataPacket("id", "mediaType", "data");
		DataPacket dataPacket02 = new DataPacket("id", "mediaType", "DATA");
		
		Assert.assertTrue(!dataPacket01.equals(dataPacket02));
	}
	
	@Test
	public void testSameHashcode() {
		DataPacket dataPacket01 = new DataPacket("id", "mediaType", "data");
		DataPacket dataPacket02 = new DataPacket("id", "mediaType", "data");
		
		Assert.assertEquals(dataPacket01.hashCode(), dataPacket02.hashCode());
	}
	
	@Test
	public void testDifferentHashcode() {
		DataPacket dataPacket01 = new DataPacket("id", "mediaType", "data");
		DataPacket dataPacket02 = new DataPacket("id", "mediaType", "DATA");
		
		Assert.assertTrue(dataPacket01.hashCode() != dataPacket02.hashCode());
	}
}
