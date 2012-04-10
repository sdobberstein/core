package core.process;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.Packets;
import core.process.database.ColumnConfiguration;
import core.process.database.InsertCall;
import core.process.database.SimpleDatabaseConfiguration;

public class SimpleDatabaseConfigurationTest {

	private SimpleDatabaseConfiguration configuration;
	
	@Before
	public void setUp() {
        Map<Integer, ColumnConfiguration> columnConfigurations = new HashMap<Integer, ColumnConfiguration>();
		
		ColumnConfiguration nameColumnConfiguration = new MockColumnConfiguration("name", Types.VARCHAR, "name");
		ColumnConfiguration ageColumnConfiguration = new MockColumnConfiguration("age", Types.INTEGER, "age");
		
		columnConfigurations.put(new Integer(1), nameColumnConfiguration);
		columnConfigurations.put(new Integer(2), ageColumnConfiguration);
		configuration = new SimpleDatabaseConfiguration("example", columnConfigurations);
	}
	
	@Test
	public void testInsertCall() {
		String xml = "<persons><person><name>sean</name><age>24</age></person></persons>";
		Properties properties = new Properties();
		properties.put("name", "sean");
		properties.put("age", "24");
		
		Packet packet = Packets.getInstance("000", "xml", xml, properties);
		
		InsertCall insertCall = configuration.getInsertCall(packet);
		
		Assert.assertEquals("INSERT INTO EXAMPLE (NAME, AGE) VALUES (?, ?)", insertCall.getInsertSql());
	}
	
	@Test
	public void testGetParameters() {
		String xml = "<persons><person><name>sean</name><age>24</age></person></persons>";
		Properties properties = new Properties();
		properties.put("name", "sean");
		properties.put("age", 24);
		
		Packet packet = Packets.getInstance("000", "xml", xml, properties);
		
		InsertCall insertCall = configuration.getInsertCall(packet);
		
		// ACTUAL RESULTS
		Object[] actual = insertCall.getParameters();
		
		// EXPECTED RESULTS
		Object[] expected = new Object[2];
		expected[0] = "sean";
		expected[1] = 24;
		
		Assert.assertEquals(expected[0], actual[0]);
		Assert.assertEquals(expected[1], actual[1]);
	}
	
	@Test
	public void testHasParameters() {
		String xml = "<persons><person><name>sean</name><age>24</age></person></persons>";
		Properties properties = new Properties();
		properties.put("name", "sean");
		properties.put("age", 24);
		
		Packet packet = Packets.getInstance("000", "xml", xml, properties);
		
		InsertCall insertCall = configuration.getInsertCall(packet);
		
		Assert.assertTrue(insertCall.hasParameters());
	}
	
	private class MockColumnConfiguration implements ColumnConfiguration {

		private String columnName;
		private int columnType;
		private String packetProperty;
		
		public MockColumnConfiguration(String columnName, int columnType, String packetProperty) {
			this.columnName = columnName;
			this.columnType = columnType;
			this.packetProperty = packetProperty;
		}
		
		@Override
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		@Override
		public String getColumnName() {
			return columnName;
		}

		@Override
		public void setColumnType(int type) {
			this.columnType = type;
		}

		@Override
		public int getColumnType() {
			return columnType;
		}

		@Override
		public void setPacketProperty(String propertyName) {
			this.packetProperty = propertyName;
		}

		@Override
		public String getPacketProperty() {
			return this.packetProperty;
		}
	}
}
