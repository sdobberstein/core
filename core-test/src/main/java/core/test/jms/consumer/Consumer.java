package core.test.jms.consumer;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) {
        
        DOMConfigurator.configure("src/main/resources/log4j.consumer.xml");
        
        String[] springFiles = new String[] {"spring.consumer.jms.xml", "spring.consumer.xml"};

        new ClassPathXmlApplicationContext(springFiles);
    }
}
