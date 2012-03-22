package core.process;

import java.util.ArrayList;
import java.util.List;

import core.packet.Packet;
import core.process.exception.ProcessingFilterException;

public abstract class FailureProcessor<D,M> {

	private List<FailureProcessor<D,M>> failureProcessors = new ArrayList<FailureProcessor<D,M>>();
	
	public void onFail(Packet<D,M> packet, Throwable cause) {
		try {
			handleException(packet, cause);
		} catch (ProcessingFilterException pfe) {
			throw pfe;
		} catch (Throwable t) {
			for (FailureProcessor<D,M> failureProcessor : getFailureProcessors()) {
				failureProcessor.onFail(packet, t);
			}
		}
	}

	abstract protected void handleException(Packet<D, M> packet, Throwable cause);

	public List<FailureProcessor<D,M>> getFailureProcessors() {
		return failureProcessors;
	}

	public void setFailureProcessors(List<FailureProcessor<D,M>> failureProcessors) {
		this.failureProcessors = failureProcessors;
	}
}
