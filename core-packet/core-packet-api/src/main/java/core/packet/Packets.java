package core.packet;

import java.util.Properties;

/**
 * Collection of general methods that apply to Packets.
 */
public class Packets {

	private Packets() {
		// This constructor should never be called.
		throw new AssertionError();
	}
	
	/**
	 * Creates a new Packet from the input parameters.
	 * 
	 * @param id
	 *              The value that uniquely identifies this packet.  Cannot be null.
	 * @param mediaType
	 *              The type of data this packet contains.  Ex. xml, json, etc.
	 *              Cannot be null.
	 * @param data
	 *              The actual data itself.
	 * @param properties
	 *              Values associated with this data.
	 * @return
	 *              A new packet representing the input.
	 * @throws
	 *              IllegalArgumentException - if Id or MediaType are null.
	 */
	public static Packet getInstance(String id, String mediaType, String data, Properties properties) {
		return new GenericPacket(id, mediaType, data, properties);
	}
	
	private static class GenericPacket implements Packet {

		private final String id;
		private final String mediaType;
		private final String data;
		private final Properties properties;
		
		public GenericPacket(final String id, final String mediaType, final String data, final Properties properties) {
			if (id == null) {
				throw new IllegalArgumentException("Id cannot be null.");
			} else if (mediaType == null) {
				throw new IllegalArgumentException("Id cannot be null.");
			}
			
			this.id = id;
			this.mediaType = mediaType;
			this.data = data;
			
			if (properties == null) {
				this.properties = new Properties();
			} else {
				this.properties = properties;
			}
		}
		
		public String getId() {
			return id;
		}

		public String getMediaType() {
			return mediaType;
		}

		public String getData() {
			return data;
		}

		public Properties getProperties() {
			return properties;
		}
	}
}
