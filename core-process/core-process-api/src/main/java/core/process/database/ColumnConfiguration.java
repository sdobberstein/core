package core.process.database;

public interface ColumnConfiguration {

	public void setColumnName(String columnName);
	
	public String getColumnName();
	
	public void setColumnType(int type);
	
	public int getColumnType();
	
	public void setPacketProperty(String propertyName);
	
	public String getPacketProperty();
}
