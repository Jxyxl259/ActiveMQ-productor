package com.amq.producer.impl;

import com.alibaba.fastjson.JSONObject;
import com.amq.producer.MQProducer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


/**
 * 测试ActiveMQ三种消息监听器之MessageListener 的消息生产者
 * 仅仅是将一个ClassRoom班级类当作一个消息，供消费方进行消费
 */
@Service
public class MQMessageListenerProducer implements MQProducer {

    @Resource(name="amqJMSTemplate_01")
    private JmsTemplate amqJMSTemplate_01;

    @Override
    public void sendMassage(final Object obj) {

        amqJMSTemplate_01.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(obj));
            }
        });

    }

    @Override
    public void sendMassage(Destination testAdapterDestination, Object obj) {

    }
}
