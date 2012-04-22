package core.process;

import core.packet.Packet;

public interface Broker {

    public void onMessage(Packet packet);
}
