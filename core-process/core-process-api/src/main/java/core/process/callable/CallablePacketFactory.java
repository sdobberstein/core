package core.process.callable;

import java.util.concurrent.Callable;

import core.packet.Packet;

public interface CallablePacketFactory {

	public Callable<Packet> getInstance(Packet packet);
}
