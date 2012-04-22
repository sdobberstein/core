package core.process;

import core.packet.Packet;
import core.thruster.Thruster;

public class Delegator implements Runnable {

    private Broker broker;
    private Thruster thruster;

    public Delegator() {}
    
    public Delegator(Broker broker, Thruster thruster) {
        this.broker = broker;
        this.thruster = thruster;
    }

    @Override
    public void run() {
        while (true) {
            Packet packet = thruster.thrust();

            if (packet != null) {
                broker.onMessage(packet);
            }
        }
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Thruster getThruster() {
        return thruster;
    }

    public void setThruster(Thruster thruster) {
        this.thruster = thruster;
    }

}
