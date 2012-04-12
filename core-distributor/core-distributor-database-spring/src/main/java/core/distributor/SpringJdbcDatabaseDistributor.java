package core.distributor;

import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;

import core.distributor.exception.DistributionException;
import core.packet.Packet;
import core.process.database.ColumnConfiguration;
import core.process.database.DatabaseConfiguration;
import core.process.database.InsertCall;

public class SpringJdbcDatabaseDistributor implements Distributor {
	
	private final DatabaseConfiguration configuration;
	private final JdbcTemplate jdbcTemplate;
	
	public SpringJdbcDatabaseDistributor(DatabaseConfiguration configuration, JdbcTemplate jdbcTemplate) {
		this.configuration = configuration;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void distribute(Packet packet) {
		if (hasParameters(packet)) {
			InsertCall insertCall = this.configuration.getInsertCall(packet);
			this.jdbcTemplate.update(insertCall.getInsertSql(), insertCall.getParameters());			
		} else {
			throw new DistributionException(this.getClass().getName());
		}
	}
	
	private boolean hasParameters(Packet packet) {
		// EACH CONFIGURATION HAS A GROUP OF COLUMN FIELDS THAT IT WILL LOOK FOR, HERE WE ARE JUST
		// GETTING THOSE VALUES
		Collection<ColumnConfiguration> configs = this.configuration.getColumnConfigurations();
		
		// NOW FOR EACH COLUMNCONFIGURATION WE NEED TO CHECK IF THE COLUMN PROPERTY IS IN THE
		// PACKET PROPERTIES OR NOT, IF ONE VALUE IS MISSING WE FAIL FAST BEFORE SENDING IT TO THE
		// DISTRIBUTOR
		for (ColumnConfiguration config : configs) {
			if (!packet.getProperties().containsKey(config.getPacketProperty())) {
				// IF ANY PROPERTY THAT IS REQUIRED IS NOT IN THE PROPERTIES MAPPING, THEN FAIL FAST.
				return false;
			}
		}
		
		// ALL REQUIRED PROPERTIES ARE PRESENT
		return true;
	}

	public boolean isSupported(String mediaType) {
		return true;
	}

	public boolean isSupported(Packet packet) {
		return true;
	}

	public DatabaseConfiguration getConfiguration() {
		return this.configuration;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
}
