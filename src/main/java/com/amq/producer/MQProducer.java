package com.amq.producer;

import javax.jms.Destination;

public interface MQProducer {

    void sendMassage(Object obj);

    void sendMassage(Destination testAdapterDestination, final Object obj);
}
