package core.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.exception.ProcessingFilterException;
import core.process.result.ProcessingResult;
import core.process.result.ProcessingResults;

public abstract class AbstractFailureProcessor<D,M> implements FailureProcessor<D,M> {

	private static final Log LOG = LogFactory.getLog(AbstractFailureProcessor.class);
	
	private List<FailureProcessor<D,M>> exceptionHandlers = new ArrayList<FailureProcessor<D,M>>();
	
	@Override
	public ProcessingResult onFail(Packet<D,M> packet, List<Throwable> exceptions) {
		if (LOG.isErrorEnabled() && exceptions.size() > 0) {
			int length = exceptions.size();
			Throwable throwable = exceptions.get(length);
			LOG.error(throwable.getMessage(), throwable);
		}
		
		ProcessingResult result = null;
		
		try {
			result = handleException(packet, exceptions);
		} catch (ProcessingFilterException pfe) {
			throw pfe;
		} catch (Throwable t) {
			exceptions.add(t);
			for (FailureProcessor<D,M> exceptionHandler : exceptionHandlers) {
				result = exceptionHandler.onFail(packet, exceptions);
			}
		}
		
		if (result == null && exceptions.size() > 0) {
			result = ProcessingResults.failure(exceptions.get(0));
		} else {
			result = ProcessingResults.failure();
		}
		
		return result;
	}
	
	abstract protected ProcessingResult handleException(Packet<D,M> packet, List<Throwable> exceptions);

	public List<FailureProcessor<D,M>> getExceptionHandlers() {
		return exceptionHandlers;
	}

	public void setExceptionHandlers(List<FailureProcessor<D,M>> exceptionHandlers) {
		this.exceptionHandlers = exceptionHandlers;
	}
}
