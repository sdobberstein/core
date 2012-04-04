package core.packet;

import org.junit.Test;

import core.packet.history.InMemoryHistory;
import core.packet.history.MessageDateHistoryEvent;

public class InMemoryHistoryTest {

	@Test
	public void testToString() {
		InMemoryHistory history = new InMemoryHistory();
		history.addEvent(new MessageDateHistoryEvent("first"));
		history.addEvent(new MessageDateHistoryEvent("second"));
		history.addEvent(new MessageDateHistoryEvent("third"));
		
		System.out.println(history);
	}
}
