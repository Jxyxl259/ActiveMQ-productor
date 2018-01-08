package com.amq.producer.impl.MessageListenerAdapterTest;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class AdapterCallBackMessageListener implements MessageListener{


    @Override
    public void onMessage(Message message) {
        TextMessage adapterCallBackText = (TextMessage) message;
        try {
            System.out.println("MLAdapter CallBcak content :"+adapterCallBackText.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
