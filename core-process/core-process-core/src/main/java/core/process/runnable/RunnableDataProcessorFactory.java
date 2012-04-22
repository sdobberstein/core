package core.process.runnable;

import core.packet.Packet;
import core.process.FailureProcessor;
import core.process.Processor;

public class RunnableDataProcessorFactory implements RunnablePacketFactory {

    private Processor processor;
    private FailureProcessor failureProcessor;
    
    public RunnableDataProcessorFactory() {}
    
    public RunnableDataProcessorFactory(Processor processor, FailureProcessor failureProcessor) {
        this.processor = processor;
        this.failureProcessor = failureProcessor;
    }
    
    @Override
    public RunnableDataProcessor newInstance(Packet packet) {
        return new RunnableDataProcessor(packet, processor, failureProcessor);
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public FailureProcessor getFailureProcessor() {
        return failureProcessor;
    }

    public void setFailureProcessor(FailureProcessor failureProcessor) {
        this.failureProcessor = failureProcessor;
    }
}
