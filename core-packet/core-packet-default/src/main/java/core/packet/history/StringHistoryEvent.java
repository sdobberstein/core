package core.packet.history;

import core.packet.history.HistoryEvent;

public class StringHistoryEvent implements HistoryEvent {

	private String event;
	
	public StringHistoryEvent(String event) {
		this.event = event;
	}
	
	public String getDisplayValue() {
		return event;
	}
}
