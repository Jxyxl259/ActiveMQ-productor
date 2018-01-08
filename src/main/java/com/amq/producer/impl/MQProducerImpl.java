package com.amq.producer.impl;

import com.alibaba.fastjson.JSONObject;
import com.amq.producer.MQProducer;
import com.amq.producer.entity.MailParam;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 测试异步发送邮件的ActiveMQ的消息生产者
 */
@Service
public class MQProducerImpl implements MQProducer{

    @Resource(name="activeMqJMSTemplate")
    private JmsTemplate activeMqJMSTemplate;

    @Override
    public void sendMassage(final Object mailParam) {
        activeMqJMSTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(mailParam));
            }
        });
    }

    @Override
    public void sendMassage(Destination testAdapterDestination, Object obj) {

    }


}
