package core.process;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.exception.ProcessingFilterException;

public abstract class AbstractFailureProcessor implements FailureProcessor {

	private static final Log LOG = LogFactory.getLog(AbstractFailureProcessor.class);
	
	private List<FailureProcessor> exceptionHandlers = new ArrayList<FailureProcessor>();
	
	@Override
	public Packet onFail(Packet packet, List<Throwable> exceptions) {
		if (LOG.isErrorEnabled() && exceptions.size() > 0) {
			int length = exceptions.size() - 1;
			Throwable throwable = exceptions.get(length);
			LOG.error(throwable.getMessage(), throwable);
		}
		
		try {
			packet = handleException(packet, exceptions);
		} catch (ProcessingFilterException pfe) {
			throw pfe;
		} catch (Throwable t) {
			exceptions.add(t);
			for (FailureProcessor exceptionHandler : exceptionHandlers) {
				packet = exceptionHandler.onFail(packet, exceptions);
			}
		}
		
		return packet;
	}
	
	abstract protected Packet handleException(Packet packet, List<Throwable> exceptions);

	public List<FailureProcessor> getExceptionHandlers() {
		return exceptionHandlers;
	}

	public void setExceptionHandlers(List<FailureProcessor> exceptionHandlers) {
		this.exceptionHandlers = exceptionHandlers;
	}
}