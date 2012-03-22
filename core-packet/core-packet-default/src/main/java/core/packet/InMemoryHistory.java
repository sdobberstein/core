package core.packet;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistory implements History {

	private List<HistoryEvent<?>> history;
	
	public InMemoryHistory() {
		history = new ArrayList<HistoryEvent<?>>();
	}
	
	public void addEvent(HistoryEvent<?> event) {
		history.add(event);
	}

	public List<HistoryEvent<?>> getHistory() {
		return history;
	}
}
