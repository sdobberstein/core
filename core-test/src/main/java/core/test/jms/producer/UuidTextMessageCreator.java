package core.test.jms.producer;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.MessageCreator;

public class UuidTextMessageCreator implements MessageCreator {
    
    private static final Log LOG = LogFactory.getLog(UuidTextMessageCreator.class);
    
    public Message createMessage(Session session) throws JMSException {
        String text = UUID.randomUUID().toString();
        TextMessage message = session.createTextMessage(text);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Sending message: " + text);
        }
        
        return message;
    }
}
