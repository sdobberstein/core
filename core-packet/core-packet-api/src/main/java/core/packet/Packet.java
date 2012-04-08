package core.packet;

import java.util.Properties;

/**
 * A generic object that can be used to carry data through a system.
 * 
 * @author Sean Dobberstein
 *
 */
public interface Packet {

	/**
	 * A value that uniquely identifies this packet from all other packets in the system.
	 */
	public String getId();
	
	/**
	 * An Internet MIME value that describes what type of data can be found in this packet.
	 */
	public String getMediaType();
	
	/**
	 * The physical contents of the packet.
	 */
	public String getData();
	
	/**
	 * Metadata associated with this packet, or extracted from the packet.  Allows us to cache
	 * commonly used attributes.
	 */
	public Properties getProperties();
}
