package core.process;

import core.packet.Packet;

public abstract class ForkedProcessor implements Processor {

	private final Processor trueProcessor;
	private final Processor falseProcessor;
	
	public ForkedProcessor(Processor trueProcessor, Processor falseProcessor) {
		this.trueProcessor = trueProcessor;
		this.falseProcessor = falseProcessor;
	}
	
	@Override
	public Packet process(Packet packet) {
		boolean passed = checkCondition(packet);
		
		if (passed) {
			return trueProcessor.process(packet);
		} else {
			return falseProcessor.process(packet);
		}
	}

	abstract protected boolean checkCondition(Packet packet);
}
