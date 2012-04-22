package core.process.runnable;

import core.packet.Packet;
import core.process.FailureProcessor;
import core.process.ProcessingExceptions;
import core.process.Processor;

public class RunnableDataProcessor implements Runnable {

    private final Packet packet;
    private final Processor processor;
    private final FailureProcessor failureProcessor;
    
    public RunnableDataProcessor(Packet packet, Processor processor, FailureProcessor failureProcessor) {
        this.packet = packet;
        this.processor = processor;
        this.failureProcessor = failureProcessor;
    }
    
    @Override
    public void run() {
        try {
            processor.process(packet);
        } catch (Throwable t) {
            ProcessingExceptions exceptions = new ProcessingExceptions(t);
            failureProcessor.onFail(packet, exceptions);
        }
    }

}
