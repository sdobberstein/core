package core.packet;

public class StringHistoryEvent implements HistoryEvent {

	private String event;
	
	public StringHistoryEvent(String event) {
		this.event = event;
	}
	
	public String getDisplayValue() {
		return event;
	}
}
