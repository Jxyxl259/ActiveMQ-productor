package com.amq.producer.impl.SessionAwareMessageListenerTest;

import com.alibaba.fastjson.JSONObject;
import com.amq.producer.MQProducer;
import com.amq.producer.entity.Student;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Service
public class MQSessionAwareMessageListenerProducer implements MQProducer{


    @Resource(name="amqJMSTemplate_02")
    private JmsTemplate amqJMSTemplate_02;

    @Override
    public void sendMassage(final Object obj) {
        amqJMSTemplate_02.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(obj));
            }
        });
    }

    @Override
    public void sendMassage(Destination testAdapterDestination, Object obj) {

    }


    public void sendStuInfoMassage(final Student stu) {
        amqJMSTemplate_02.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(stu));
            }
        });
        System.out.println("消息已发送");
    }
}
