package core.packet.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import core.packet.history.History;
import core.packet.history.HistoryEvent;

public class InMemoryHistory implements History {

	private Map<String, HistoryEvent> historyEvents;
	private List<HistoryEvent> history;
	private int counter;
	
	public InMemoryHistory() {
		history = new ArrayList<HistoryEvent>();
		historyEvents = new HashMap<String, HistoryEvent>();
		counter = 0;
	}
	
	public void addEvent(HistoryEvent event) {
		history.add(event);
		
		counter++;
		historyEvents.put((counter < 10 ? "0" + Integer.toString(counter) : Integer.toString(counter)), event);
	}

	public List<HistoryEvent> getHistory() {
		return history;
	}
	
	@Override
	public String toString() {
		StringBuilder rval = new StringBuilder("\n");
		Iterator<String> iter = historyEvents.keySet().iterator();
		
		while (iter.hasNext()) {
			String next = iter.next();
			rval.append("\t");
			rval.append("Event ");
			rval.append(next);
			rval.append(": ");
			rval.append(historyEvents.get(next).getDisplayValue());
			
			if (iter.hasNext()) {
				rval.append("\n");
			}
		}
		
		return rval.toString();
	}
}
