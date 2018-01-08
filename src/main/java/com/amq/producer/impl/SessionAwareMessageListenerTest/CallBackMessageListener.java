package com.amq.producer.impl.SessionAwareMessageListenerTest;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * producer生产一个消息，放到消息队列中，
 * consumer端设置消息监听器（SessionAwareMessageListener），及消息监听容器，来监听消息，
 * SessionAwareMessageListener是Spring为我们提供的一个非标准JMS MessageListener
 * 其作用是在消息消费端收到消息之后，再往回调消息队列发送一条消息（告知producer端，我收到消息了），
 * producer这边再设置一个消息监听，监听回调队列，即可接收消费端的回调消息
 */
@Component
public class CallBackMessageListener implements MessageListener{


    @Override
    public void onMessage(Message message) {
        //首先保证SessionAwareMessageListener往回调消息队列中放置的消息是纯文本消息；
        //才可向下转型
        TextMessage textMessage = (TextMessage)message;
        try {
            System.out.println("producer收到来自Consumer的回调消息，消息内容为："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
