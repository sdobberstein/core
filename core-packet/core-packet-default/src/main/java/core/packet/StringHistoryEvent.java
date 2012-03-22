package core.packet;

public class StringHistoryEvent implements HistoryEvent<String> {

	private String event;
	
	public StringHistoryEvent(String event) {
		this.event = event;
	}
	
	public String getEvent() {
		return event;
	}

}
