package core.distributor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.write.FileWriter;

public class BatchedFileDistributor implements BatchedDistributor {

	private static final Log LOG = LogFactory.getLog(BatchedFileDistributor.class);
	
	private final List<Packet> packets;
	
	private final FileWriter fileWriter;
	private final int batchSize;
	
	public BatchedFileDistributor(FileWriter fileWriter, int batchSize) {
		this.fileWriter = fileWriter;
		this.batchSize = batchSize;
		this.packets = new ArrayList<Packet>();
	}
	
	@Override
	public void distribute(Packet packet) {
		// ADD THE PACKET TO THE LIST INITIALLY
		this.packets.add(packet);
		
		// CHECK IF WE HAVE REACHED THE BATCH SIZE.
		if (this.packets.size() >= this.batchSize) {
			
			// WE'VE REACHED THE BATCH SIZE, WRITE THEM TO DISK
			this.flush();
		} else {
			
			// WE HAVEN'T REACHED THE BATCH SIZE, KEEP WAITING.
			if (LOG.isDebugEnabled()) {
				LOG.debug("Waiting for more files to distribute.  "
			            + "Current: (" + this.packets.size() + ") "
			            + "BatchSize: (" + this.batchSize + ")");
			}
		}
	}

	@Override
	public boolean isSupported(Packet packet) {
		return (packet.getData() != null);
	}

	/**
	 * Immediately distributes all packets that are queued up.  Should
	 * be called when shutting down the system to ensure that all packets
	 * that are waiting to be distributed end up being distributed.
	 */
	@Override
	public void flush() {
		for (Packet batchedPacket : this.packets) {
			fileWriter.writePacket(batchedPacket);
		}
		
		// CLEAR THE LIST
		this.packets.clear();
	}
}