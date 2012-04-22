package core.test.jms.producer;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Producer {

    private static final Log LOG = LogFactory.getLog(Producer.class);
    
    public static void main(String[] args) throws InterruptedException, JMSException {
        
        DOMConfigurator.configure("src/main/resources/log4j.producer.xml");
        
        String[] springFiles = new String[] {"spring.producer.jms.xml"};

        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(springFiles);
        MessageGenerator messageGenerator = (MessageGenerator) appCtx.getBean("uuidTextMessageGenerator");
        
        long startTime = System.nanoTime();
        messageGenerator.generateMessages();
        long finishTime = System.nanoTime();
        
        LOG.info("Run time: " + ((finishTime - startTime) / 1000000000.0) + " seconds");
    }
}