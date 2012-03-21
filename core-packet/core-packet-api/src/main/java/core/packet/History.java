package core.packet;

import java.util.List;

public interface History {

	public void addEvent(String event);
	
	public List<String> getHistory();
}
