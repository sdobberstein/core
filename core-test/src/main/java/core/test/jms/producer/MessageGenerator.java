package core.test.jms.producer;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageGenerator {
    
    private static final Log LOG = LogFactory.getLog(MessageGenerator.class);

    private JmsTemplate jmsTemplate = null;
    private int messageCount = 50;
    private long delayBetweenMessages;
    private MessageCreator messageCreator;

    public void generateMessages() throws JMSException {
        LOG.info("Generating " + messageCount + " messages");
        
        for (int i = 0; i < messageCount; i++) {
            
            jmsTemplate.send(messageCreator);
            
            try {
                Thread.sleep(delayBetweenMessages);
            } catch (InterruptedException ie) {}
        }
        
        LOG.info("Done");
    }
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
    
    public long getDelayBetweenMessages() {
        return delayBetweenMessages;
    }

    public void setDelayBetweenMessages(long delayBetweenMessages) {
        this.delayBetweenMessages = delayBetweenMessages;
    }

    public MessageCreator getMessageCreator() {
        return messageCreator;
    }

    public void setMessageCreator(MessageCreator messageCreator) {
        this.messageCreator = messageCreator;
    }
}