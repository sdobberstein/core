package core.distributor;

public interface BatchedDistributor extends Distributor {

	/**
	 * Immediately distributes all packets that are queued up.  Should
	 * be called when shutting down the system to ensure that all packets
	 * that are waiting to be distributed end up being distributed.
	 */
	public void flush();
}
