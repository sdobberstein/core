package core.process.runnable;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.FailureProcessor;
import core.process.Processor;
import core.process.exception.ProcessingFilterException;

public class RunnableProcessor<D,M> implements Runnable {

	private static final Log LOG = LogFactory.getLog(RunnableProcessor.class);
	
	private List<Processor<D,M>> processors = new ArrayList<Processor<D,M>>();
	private List<FailureProcessor<D,M>> failureProcessors = new ArrayList<FailureProcessor<D,M>>();
	
	private Packet<D,M> packet;
	private boolean initialized;
	
	// Allows us to create prototype RunnableProcessors, must call initialize before calling run.
	public RunnableProcessor() {
		this.initialized = false;
	}
	
	public RunnableProcessor(Packet<D,M> packet) {
		this.packet = packet;
		this.initialized = true;
	}
	
	public void initialize(Packet<D,M> packet) {
		this.packet = packet;
		this.initialized = true;
	}
	
	public void run() {
		try {
			// If the packet hasn't been initialized, then there is nothing to do...skip it.
			if (isInitialized()) {
				for (Processor<D,M> processor : getProcessors()) {
					processor.process(packet);
				}
			}
		} catch (ProcessingFilterException pfe) {
			if (LOG.isInfoEnabled()) {
				LOG.debug("[FILTER] Packet filtered: " + pfe.getMessage());
			}
		} catch (Throwable t) {
			for (FailureProcessor<D, M> failureProcessor : getFailureProcessors()) {
				try {
					failureProcessor.onFail(packet, t);
				} catch (ProcessingFilterException pfe) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("[FILTER] Packet filtered from FailureProcessors: " + pfe.getMessage());
					}
					
					break;
				}
			}
		}
	}
	
	public List<Processor<D,M>> getProcessors() {
		return processors;
	}
	
	public void setProcessors(List<Processor<D,M>> processors) {
		this.processors = processors;
	}	

	public List<FailureProcessor<D,M>> getFailureProcessors() {
		return failureProcessors;
	}

	public void setFailureProcessors(List<FailureProcessor<D,M>> failureProcessors) {
		this.failureProcessors = failureProcessors;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public Packet<D,M> getPacket() {
		return packet;
	}
}
