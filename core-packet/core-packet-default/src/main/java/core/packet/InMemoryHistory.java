package core.packet;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistory implements History {

	private List<String> history;
	
	public InMemoryHistory() {
		history = new ArrayList<String>();
	}
	
	public void addEvent(String event) {
		history.add(event);
	}

	public List<String> getHistory() {
		return history;
	}
}
