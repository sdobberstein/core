package core.process.database;

import java.util.List;

import core.packet.Packet;

public interface DatabaseConfiguration {
	
	public String getTableName();
	
	public String getInsertSql();
	
	public List<ColumnConfiguration> getColumnConfigurations();
	
	public InsertCall getInsertCall(Packet packet);
}
