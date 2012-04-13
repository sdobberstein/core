package core.process.database;

public class SimpleColumnConfiguration implements ColumnConfiguration {
	
	private String columnName;
	private int columnType;
	private String packetProperty;
	
	public SimpleColumnConfiguration(String columnName, int columnType, String packetProperty) {
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
