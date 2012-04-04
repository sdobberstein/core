package core.packet.history;

import java.util.Date;

public class MessageDateHistoryEvent extends DateHistoryEvent {

	private String message;
	
	public MessageDateHistoryEvent(String eventMessage) {
		super(new Date());
		this.message = eventMessage;
	}
	
	public MessageDateHistoryEvent(Date date) {
		super(date);
		this.message = "N/A";
	}

	@Override
	public String getDisplayValue() {
		StringBuilder rval = new StringBuilder("[\"");
		rval.append(sdf.format(date));
		rval.append("\" : \"");
		rval.append(message);
		rval.append("\"]");
		return rval.toString();
	}
}
