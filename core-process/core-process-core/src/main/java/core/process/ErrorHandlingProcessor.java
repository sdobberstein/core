package core.process;

import core.packet.Packet;
import core.process.exception.ProcessingFilterException;

public class ErrorHandlingProcessor implements Processor {

	private final Processor processor;
	private final FailureProcessor failureProcessor;
	
	public ErrorHandlingProcessor(Processor processor, FailureProcessor failureProcessor) {
		if (processor == null) {
			throw new IllegalArgumentException("Processor cannot be null!");
		}
		
		if (failureProcessor == null) {
			throw new IllegalArgumentException("FailureProcessor cannot be null!");
		}
		
		this.processor = processor;
		this.failureProcessor = failureProcessor;
	}
	
	@Override
	public Packet process(Packet packet) {
		try {
			packet = processor.process(packet);
		} catch (ProcessingFilterException pfe) {
			throw pfe;
		} catch (Throwable t) {
			packet = failureProcessor.onFail(packet, new ProcessingExceptions(t));
		}
		
		return packet;
	}

}
