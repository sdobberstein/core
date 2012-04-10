package core.process.database;

import java.util.Map;

import core.packet.Packet;

public interface DatabaseConfiguration {
	
	public String getTableName();
	
	public Map<Integer, ColumnConfiguration> getColumnConfigurations();
	
	public InsertCall getInsertCall(Packet packet);
}
