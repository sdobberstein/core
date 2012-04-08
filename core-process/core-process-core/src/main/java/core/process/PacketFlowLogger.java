package core.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;

public class PacketFlowLogger implements Processor {

	private static final Log LOG = LogFactory.getLog(PacketFlowLogger.class);
	
	private final List<String> properties;
	private final boolean useProperties;
	
	public PacketFlowLogger() {
		this.useProperties = false;
		this.properties = new ArrayList<String>();
	}
	
	public PacketFlowLogger(List<String> properties) {
		this.useProperties = true;
		this.properties = properties;
	}
	
	@Override
	public Packet process(Packet packet) {
		if (isUseProperties()) {
			useProperties(packet);
		} else {
			useDefault(packet);
		}
		
		return packet;
	}

	private void useProperties(Packet packet) {
		if (LOG.isInfoEnabled()) {
			Iterator<String> iter = properties.iterator();
			
			StringBuilder output = new StringBuilder();
			while (iter.hasNext()) {
				output.append(packet.getProperties().get(iter.next()));
				
				if (iter.hasNext()) {
					output.append("|");
				}
			}
			
			LOG.info(output.toString());
		}
	}

	private void useDefault(Packet packet) {
		if (LOG.isInfoEnabled()) {
			StringBuilder output = new StringBuilder();
			output.append(packet.getId());
			output.append("|");
			output.append(packet.getMediaType());

			LOG.info(output.toString());
		}
	}

	public boolean isUseProperties() {
		return useProperties;
	}

	public List<String> getProperties() {
		return properties;
	}
}
