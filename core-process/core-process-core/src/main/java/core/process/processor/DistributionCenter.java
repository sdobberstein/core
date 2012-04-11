package core.process.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.distributor.Distributor;
import core.packet.Packet;
import core.process.Processor;

public class DistributionCenter implements Processor {

	private static final Log LOG = LogFactory.getLog(DistributionCenter.class);
	
	private final List<Distributor> distributors;
	private final boolean breakOnFailure;
	
	public DistributionCenter(List<Distributor> distributors) {
		this(distributors, false);
	}
	
	public DistributionCenter(List<Distributor> distributors, boolean breakOnFailure) {
		if (distributors == null) {
			this.distributors = new ArrayList<Distributor>();
		} else {
			this.distributors = distributors;			
		}
		
		this.breakOnFailure = breakOnFailure;
	}
	
	@Override
	public Packet process(Packet packet) {
		if (this.distributors.size() > 0) {
			distributePacket(packet);
		} else {
			if (LOG.isWarnEnabled()) {
				LOG.warn("No distributors set in DistributionCenter!");
			}
		}
		
		return packet;
	}

	private void distributePacket(Packet packet) {
		for (Distributor distributor : distributors) {
			if (distributor.isSupported(packet)) {
				try {
					distributor.distribute(packet);						
				} catch (Throwable t) {
					if (isBreakOnFailure()) {
						throw new RuntimeException(t.getMessage(), t);
					} else {
						if (LOG.isWarnEnabled()) {
							LOG.warn("Failed distributing packet in " + distributor.getClass().getName());
						}
					}
				}
			}
		}
	}

	public List<Distributor> getDistributors() {
		return distributors;
	}

	public boolean isBreakOnFailure() {
		return breakOnFailure;
	}
}