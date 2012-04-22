package core.process.runnable;

import core.packet.Packet;

public interface RunnablePacketFactory {

    public Runnable newInstance(Packet packet);
}
