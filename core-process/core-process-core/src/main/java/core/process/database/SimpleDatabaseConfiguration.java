package core.process.database;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import core.packet.Packet;

public class SimpleDatabaseConfiguration implements DatabaseConfiguration {

	private final String tableName;
	private final Map<Integer, ColumnConfiguration> columnConfigurations;
	
	public SimpleDatabaseConfiguration(String tableName, Map<Integer, ColumnConfiguration> columnConfigurations) {
		this.tableName = tableName;
		this.columnConfigurations = columnConfigurations;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public Map<Integer, ColumnConfiguration> getColumnConfigurations() {
		return columnConfigurations;
	}

	@Override
	public InsertCall getInsertCall(Packet packet) {
		return new BasicInsertCall(packet, this.tableName, this.columnConfigurations);
	}

	private class BasicInsertCall implements InsertCall {

		private final Packet packet;
		private final String tableName;
		private final Map<Integer, ColumnConfiguration> columnConfigurations;
		
		private String sql;
		
		public BasicInsertCall(Packet packet, String tableName, Map<Integer, ColumnConfiguration> columnConfigurations) {
			this.packet = packet;
			this.tableName = tableName;
			this.columnConfigurations = columnConfigurations;
		}
		
		@Override
		public String getInsertSql() {
			
			// CHECK IF IT'S ALREADY BEEN CREATED BEFORE
			if (this.sql != null) {
				return this.sql;
			}
			
			StringBuilder sql = new StringBuilder("INSERT INTO ");
			sql.append(this.tableName.toUpperCase());
			sql.append(" (");
			
			Collection<ColumnConfiguration> configs = this.columnConfigurations.values();
			Iterator<ColumnConfiguration> iter = configs.iterator();
			
			while (iter.hasNext()) {
				sql.append(iter.next().getColumnName().toUpperCase());
				
				if (iter.hasNext()) {
					sql.append(", ");
				}
			}
			
			sql.append(") VALUES (");
			
			// RESET ITERATOR
			iter = configs.iterator();
			
			while (iter.hasNext()) {
				sql.append('?');
				iter.next();
				
				if (iter.hasNext()) {
					sql.append(", ");
				}
			}
			
			sql.append(")");
			this.sql = sql.toString();
			
			return this.sql;
		}

		@Override
		public boolean hasParameters() {
			return getInsertSql().contains("?");
		}

		@Override
		public Object[] getParameters() {
			Object[] parameters = new Object[this.columnConfigurations.size()];
			
			Iterator<ColumnConfiguration> iter = this.columnConfigurations.values().iterator();
			
			int i = 0;
			while (iter.hasNext()) {
				ColumnConfiguration config = iter.next();
				parameters[i] = this.packet.getProperties().get(config.getPacketProperty());
				i++;
			}
			
			return parameters;
		}
	}
}
