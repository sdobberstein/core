package core.process.processor.failure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.FailureProcessor;
import core.process.ProcessingExceptions;

public class FailurePacketFlowLogger implements FailureProcessor {

    private static final Log LOG = LogFactory.getLog(FailurePacketFlowLogger.class);
    
    @Override
    public Packet onFail(Packet packet, ProcessingExceptions exceptions) {
        
        StringBuilder output = new StringBuilder();
        output.append(packet.getId());
        output.append("|");
        output.append(packet.getMediaType());

        LOG.error(output.toString());
        
        return null;
    }

}
