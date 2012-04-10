package core.process.database;

public interface InsertCall {

	public String getInsertSql();
	
	public boolean hasParameters();
	
	public Object[] getParameters();
}
