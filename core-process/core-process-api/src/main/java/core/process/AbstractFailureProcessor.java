package core.process;

import java.util.List;

import core.packet.Packet;
import core.process.exception.ProcessingFilterException;

public abstract class AbstractFailureProcessor implements FailureProcessor {

	private final List<FailureProcessor> failureProcessors;
	
	public AbstractFailureProcessor(List<FailureProcessor> failureProcessors) {
		this.failureProcessors = failureProcessors;
	}
	
	@Override
	public Packet onFail(Packet packet, List<Throwable> throwables) {
		try {
			// TRY TO HANDLE THE EXCEPTION YOURSELF
			packet = handleException(packet, throwables);
		} catch (Throwable t) {
			// YOU FAILED TO HANDLE THE EXCEPTION SO GO TO YOUR FAILURE PROCESSORS
			throwables.add(t);
			for (FailureProcessor failureProcessor : failureProcessors) {
				try {
					packet = failureProcessor.onFail(packet, throwables);					
				} catch (ProcessingFilterException pfe) {
					throw pfe;
				}
			}
		}
		
		return packet;
	}

	abstract protected Packet handleException(Packet packet, List<Throwable> throwables);
}
