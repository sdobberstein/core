package core.distributor;

import java.io.File;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.Packets;
import core.process.write.SimpleXmlFileWriter;

public class BatchedFileDistributorTest {

	private String baseDirectory;	
	private BatchedFileDistributor distributor;
	
	@Before
	public void setUp() {
		baseDirectory = new File(".").getAbsolutePath() + "src" + File.separator + "test" + File.separator + "output";
	}
	
	@Test
	public void testBatchSize2() {
		distributor = new BatchedFileDistributor(new SimpleXmlFileWriter(baseDirectory), 2);
		
		// DISTRIBUTE FIRST PACKET
		distributePacket("sean", "24", "000");
		
		// MAKE SURE THE FILES DOESN'T EXIST
		assertDoesntExists("000");
		
		try {
			// SLEEP FOR ONE SECONDS
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			
		}
		
		// DISTRIBUTE THE SECOND PACKET.  WE SHOULD REACH THE BATCH LIMIT.
		distributePacket("dan", "27", "001");
		
		// MAKE SURE THE FILES EXIST
		assertExistsAndDelete("000");
		assertExistsAndDelete("001");
	}
	
	@Test
	public void testBatchSize4() {
        distributor = new BatchedFileDistributor(new SimpleXmlFileWriter(baseDirectory), 2);
		
		// DISTRIBUTE FIRST PACKET
		distributePacket("sean", "24", "000");
		
		// MAKE SURE THE FILES DOESN'T EXIST
		assertDoesntExists("000");
		
		try {
			// SLEEP FOR ONE SECONDS
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			
		}
		
		// DISTRIBUTE THE SECOND PACKET.  WE SHOULD REACH THE BATCH LIMIT.
		distributePacket("dan", "27", "001");
		
		// MAKE SURE THE FILES EXIST
		assertExistsAndDelete("000");
		assertExistsAndDelete("001");
		
		// DISTRIBUTE FIRST PACKET
		distributePacket("vu", "31", "002");

		// MAKE SURE THE FILES DOESN'T EXIST
		assertDoesntExists("002");

		try {
			// SLEEP FOR ONE SECONDS
			Thread.sleep(1000);
		} catch (InterruptedException ie) {

		}

		// DISTRIBUTE THE SECOND PACKET. WE SHOULD REACH THE BATCH LIMIT.
		distributePacket("jack", "30", "003");

		// MAKE SURE THE FILES EXIST
		assertExistsAndDelete("002");
		assertExistsAndDelete("003");
	}
	
	private void distributePacket(String name, String age, String id) {
		String xml = "<persons><person><name>" + name + "</name><age>" + age + "</age></person></persons>";
		Properties properties = new Properties();
		properties.put("name", name);
		properties.put("age", age);
		
		Packet packet = Packets.getInstance(id, "xml", xml, properties);
		
		// DISTRIBUTE THE FIRST PACKET
		distributor.distribute(packet);
	}
	
	private void assertDoesntExists(String id) {
		// MAKE SURE THE FILES DOESN'T EXIST
		File xmlFile = new File(baseDirectory + File.separator + id + ".xml");
		File propertiesFile = new File(baseDirectory + File.separator + id + ".properties");
		Assert.assertTrue(!xmlFile.exists());
		Assert.assertTrue(!propertiesFile.exists());
	}
	
	private void assertExistsAndDelete(String id) {
		// MAKE SURE THE FILES EXIST
		File xmlFile = new File(baseDirectory + File.separator + id + ".xml");
		File propertiesFile = new File(baseDirectory + File.separator + id + ".properties");
		Assert.assertTrue(xmlFile.exists());
		Assert.assertTrue(propertiesFile.exists());
		
		xmlFile.delete();
		propertiesFile.delete();
	}
}
