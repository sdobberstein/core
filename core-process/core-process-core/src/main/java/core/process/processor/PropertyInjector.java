package core.process.processor;

import java.util.Map;

import core.packet.Packet;
import core.process.Processor;

public class PropertyInjector implements Processor {

	private final Map<String, String> properties;
	
	public PropertyInjector(Map<String, String> properties) {
		this.properties = properties;
	}
	
	@Override
	public Packet process(Packet packet) {
		for (String property : properties.keySet()) {
			packet.getProperties().put(property, properties.get(property));
		}
		
		return packet;
	}
}
