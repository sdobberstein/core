package core.process;

import core.packet.Packet;

/**
 * The ForkedProcessor is an abstract class that allows you to decide which 
 * Processor to call based on some condition being met.  All implementations
 * must override the 'checkCondition(Packet packet)' method, which is used
 * to determine which Processor to call.
 * 
 * Because the ForkedProcessor implements Processor you can plug it in anywhere
 * a normal Processor can be used.  This can lead to varying configurations.  For
 * example, one could hook up a ForkedProcessor with two ChainedProcessors, effectively
 * creating two completely different processing stacks based on some condition being met.
 */
public abstract class ForkedProcessor implements Processor {

	private final Processor trueProcessor;
	private final Processor falseProcessor;
	
	/**
	 * Creates a new instance of a ForkedProcessor.
	 * 
	 * @param trueProcessor
	 *                    The processor to use when the packet passes the required condition.
	 * @param falseProcessor
	 *                    The processor to use when the packet fails the required condition.
	 */
	public ForkedProcessor(Processor trueProcessor, Processor falseProcessor) {
		if (trueProcessor == null) {
			throw new IllegalArgumentException("The TRUE Processor cannot be null!");
		}
		
		if (falseProcessor == null) {
			throw new IllegalArgumentException("The FALSE Processor cannot be null!");
		}
		
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

	/**
	 * This method is required to be implemented by all ForkedProcessor implementations.  The result
	 * of this method determines which processor to use.
	 * 
	 * @param packet
	 *              The packet to check whether it meets the required condition.
	 * @return
	 *              True if the packet meets the condition, or false otherwise.
	 */
	abstract protected boolean checkCondition(Packet packet);
}
