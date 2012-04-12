package core.process.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.FailureProcessor;
import core.process.ProcessingExceptions;
import core.process.Processor;
import core.process.exception.ProcessingFilterException;

public class DataProcessor implements Callable<Packet> {

	private static final Log LOG = LogFactory.getLog(DataProcessor.class);
	
	private final Packet packet;
	private final List<Processor> processors;
	private final List<FailureProcessor> failureProcessors;
	
	public DataProcessor(Packet packet, List<Processor> processors, List<FailureProcessor> failureProcessors) {
		if (packet == null) {
			throw new IllegalArgumentException("Packet cannot be null.");
		}
		
		this.packet = packet;
		
		if (processors == null) {
			this.processors = new ArrayList<Processor>();
		} else {
			this.processors = processors;
		}
		
		if (failureProcessors == null) {
			this.failureProcessors = new ArrayList<FailureProcessor>();
		} else {
			this.failureProcessors = failureProcessors;
		}
	}
	
	@Override
	public Packet call() throws Exception {
		Packet result = this.packet;
		
		try {
			for (Processor processor : getProcessors()) {
				result = processor.process(result);
			}
		} catch (ProcessingFilterException pfe) {
			if (LOG.isInfoEnabled()) {
				LOG.debug("[FILTER] Packet filtered: " + pfe.getMessage());
			}
		} catch (Throwable t) {
			ProcessingExceptions exceptions = new ProcessingExceptions(t);
			for (FailureProcessor failureProcessor : getFailureProcessors()) {
				try {
					result = failureProcessor.onFail(result, exceptions);
				} catch (ProcessingFilterException pfe) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("[FILTER] Packet filtered from FailureProcessors: " + pfe.getMessage());
					}
					
					// TODO:  Should we return a different result if it's filtered out?					
					break;
				}
			}
		}
		
		return result;
	}

	public Packet getPacket() {
		return packet;
	}

	public List<Processor> getProcessors() {
		return processors;
	}

	public List<FailureProcessor> getFailureProcessors() {
		return failureProcessors;
	}
}
