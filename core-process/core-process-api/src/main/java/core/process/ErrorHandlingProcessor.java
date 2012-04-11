package core.process;

import java.util.ArrayList;
import java.util.List;

import core.packet.Packet;
import core.process.exception.ProcessingFilterException;

public class ErrorHandlingProcessor implements Processor {

	private Processor processor;
	private FailureProcessor failureProcessor;
	
	@Override
	public Packet process(Packet packet) {
		try {
			packet = processor.process(packet);
		} catch (ProcessingFilterException pfe) {
			throw pfe;
		} catch (Throwable t) {
			List<Throwable> throwables = new ArrayList<Throwable>();
			throwables.add(t);
			packet = failureProcessor.onFail(packet, throwables);
		}
		
		return packet;
	}

}
