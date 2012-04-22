package core.thruster;

import core.packet.factory.PacketFactory;


public abstract class AbstractThruster implements Thruster {

    protected PacketFactory packetFactory;
    protected String mediaType = "text/unknown";
    
    public PacketFactory getPacketFactory() {
        return packetFactory;
    }

    public void setPacketFactory(PacketFactory packetFactory) {
        this.packetFactory = packetFactory;
    }
    
    public String getMediaType() {
        return mediaType;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

}
