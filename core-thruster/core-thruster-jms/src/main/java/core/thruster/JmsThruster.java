package core.thruster;

import java.util.Properties;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import core.packet.Packet;
import core.thruster.AbstractThruster;

@Component
public class JmsThruster extends AbstractThruster {

    private static final Log LOG = LogFactory.getLog(JmsThruster.class);
    
    private JmsTemplate jmsTemplate;

    @Override
    public Packet thrust() {
        
        Message message = jmsTemplate.receive();
        
        if (message instanceof TextMessage) {
            try {
                return packetFactory.createWithNewId(mediaType, ((TextMessage) message).getText(), new Properties());
                
            } catch (Throwable t) {
                LOG.error("Unable to create packet", t);
                t.printStackTrace();
            }
        }
        
        return null;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
}
