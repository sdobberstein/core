package core.distributor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import core.packet.Packet;
import core.packet.Packets;
import core.process.database.ColumnConfiguration;
import core.process.database.DatabaseConfiguration;
import core.process.database.InsertCall;
import core.process.database.SimpleDatabaseConfiguration;

public class SpringJdbcDatabaseDistributorTest {

	private SpringJdbcDatabaseDistributor distributor;
	
	@Before
	public void setUp() {
		Map<Integer, ColumnConfiguration> columnConfigurations = new HashMap<Integer, ColumnConfiguration>();
		
		ColumnConfiguration nameColumnConfiguration = new MockColumnConfiguration("name", Types.VARCHAR, "name");
		ColumnConfiguration ageColumnConfiguration = new MockColumnConfiguration("age", Types.INTEGER, "age");
		
		columnConfigurations.put(new Integer(1), nameColumnConfiguration);
		columnConfigurations.put(new Integer(2), ageColumnConfiguration);
		
		DatabaseConfiguration configuration = new SimpleDatabaseConfiguration("example", columnConfigurations);
		
		// GET JDBCTEMPLATE FROM SPRING FILE
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring.database.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		
		distributor = new SpringJdbcDatabaseDistributor(configuration, jdbcTemplate);
	}
	
	@Test
	public void testDistribute() {
		String xml = "<persons><person><name>sean</name><age>24</age></person></persons>";
		Properties properties = new Properties();
		properties.put("name", "sean");
		properties.put("age", "24");
		
		Packet packet = Packets.getInstance("000", "xml", xml, properties);
		
		InsertCall insertCall = distributor.getConfiguration().getInsertCall(packet);
		
		System.out.println(insertCall.getInsertSql());
		System.out.println(Arrays.asList(insertCall.getParameters()));
		
		distributor.distribute(packet);
		
		List<Person> people = distributor.getJdbcTemplate().query("SELECT * FROM EXAMPLE", new RowMapper<Person>() {

			@Override
			public Person mapRow(ResultSet rs, int row) throws SQLException {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				
				return new Person(name, age);
			}
			
		});
		
		Assert.assertEquals(1, people.size());
		Assert.assertEquals("sean", people.get(0).getName());
		Assert.assertEquals(24, people.get(0).getAge());
	}
	
	private class Person {
		private final String name;
		private final int age;
		
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getAge() {
			return this.age;
		}
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