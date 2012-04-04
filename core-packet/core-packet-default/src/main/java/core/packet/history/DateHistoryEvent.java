package core.packet.history;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHistoryEvent implements HistoryEvent {

	protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	protected Date date;
	
	public DateHistoryEvent(Date date) {
		this.date = new Date(date.getTime());
	}
	
	public String getDisplayValue() {
		return sdf.format(date);
	}

}
