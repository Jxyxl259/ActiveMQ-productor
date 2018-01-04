package com.amq.producer;

import com.alibaba.fastjson.JSONObject;
import com.amq.producer.entity.MailParam;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class MQProducer {

    @Resource(name="activeMqJMSTemplate")
    private JmsTemplate activeMqJMSTemplate;

    public void sendMassage(final MailParam mailParam){
        activeMqJMSTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(mailParam));
            }
        });
    }


}
