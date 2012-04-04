package core.packet.history;

import java.util.List;

public interface History {

	public void addEvent(HistoryEvent event);
	
	public List<HistoryEvent> getHistory();
}
