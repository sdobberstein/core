package core.thruster;

import java.util.Properties;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;
import core.process.Broker;
import core.thruster.AbstractThruster;

public class AsyncJmsThruster extends AbstractThruster implements MessageListener {

    private static final Log LOG = LogFactory.getLog(AsyncJmsThruster.class);
    
    private Broker broker;
    
    @Override
    public void onMessage(Message message) {        
        if (message instanceof TextMessage) {
            try {
                broker.onMessage(packetFactory.createWithNewId(mediaType, ((TextMessage) message).getText(), new Properties()));
                
            } catch (Throwable t) {
                LOG.error("Unable to create packet", t);
                t.printStackTrace();
            }
        }
    }

    @Override
    public Packet thrust() {
        throw new RuntimeException("Don't call thrust on asynchronous thruster");
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

}
