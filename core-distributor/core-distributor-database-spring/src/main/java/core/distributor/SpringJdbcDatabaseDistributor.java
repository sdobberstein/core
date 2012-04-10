package core.distributor;

import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import core.packet.Packet;
import core.process.database.ColumnConfiguration;
import core.process.database.DatabaseConfiguration;
import core.process.database.InsertCall;

public class SpringJdbcDatabaseDistributor extends JdbcDaoSupport implements Distributor {
	
	private final DatabaseConfiguration configuration;
	
	public SpringJdbcDatabaseDistributor(DatabaseConfiguration configuration, JdbcTemplate jdbcTemplate) {
		this.configuration = configuration;
		this.setJdbcTemplate(jdbcTemplate);
	}
	
	public void distribute(Packet packet) {
		InsertCall insertCall = this.configuration.getInsertCall(packet);
		getJdbcTemplate().update(insertCall.getInsertSql(), insertCall.getParameters());
	}

	public boolean isSupported(String mediaType) {
		throw new UnsupportedOperationException("Use isSupported(Packet packet) instead!");
	}

	public boolean isSupported(Packet packet) {
		Collection<ColumnConfiguration> configs = this.configuration.getColumnConfigurations().values();
		
		for (ColumnConfiguration config : configs) {
			if (!packet.getProperties().containsKey(config.getPacketProperty())) {
				// IF ANY PROPERTY THAT IS REQUIRED IS NOT IN THE PROPERTIES MAPPING, THEN FAIL FAST.
				return false;
			}
		}
		
		return true;
	}

	public DatabaseConfiguration getConfiguration() {
		return configuration;
	}
}
