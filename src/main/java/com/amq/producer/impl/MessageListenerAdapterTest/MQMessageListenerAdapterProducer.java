package com.amq.producer.impl.MessageListenerAdapterTest;

import com.alibaba.fastjson.JSONObject;
import com.amq.producer.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class MQMessageListenerAdapterProducer implements MQProducer{

    @Resource(name="amqJMSTemplate_03")
    private JmsTemplate amqJMSTemplate_03;

    @Resource(name="adapterCallBackDestination")
    private Destination adapterCallBackDestination;

    @Override
    public void sendMassage(Object obj) {

    }

    @Override
    public void sendMassage(Destination testAdapterDestination, final Object obj) {
        amqJMSTemplate_03.send(testAdapterDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(JSONObject.toJSONString(obj));
                //设置MessageListenerAdapter回调消息的地址
                textMessage.setJMSReplyTo(adapterCallBackDestination);
                return textMessage;
            }
        });
    }
}
