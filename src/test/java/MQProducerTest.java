import com.amq.producer.MQProducer;
import com.amq.producer.entity.ClassRoom;
import com.amq.producer.entity.MailParam;
import com.amq.producer.entity.Student;
import com.amq.producer.impl.MQMessageListenerProducer;
import com.amq.producer.impl.MQProducerImpl;
import com.amq.producer.impl.MessageListenerAdapterTest.MQMessageListenerAdapterProducer;
import com.amq.producer.impl.SessionAwareMessageListenerTest.MQSessionAwareMessageListenerProducer;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Destination;

public class MQProducerTest {

    private static ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext( "classpath:/Spring-context-test.xml");

    public static void main(String[] args) {
        try {
            ioc.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试异步发送邮件
     */
    @Test
    public void asyncMailTest() {
        try {
            ioc.start();
            MQProducer producer = ioc.getBean(MQProducerImpl.class);

            producer.sendMassage(new MailParam("376699923@qq.com","ActiveMQ测试","这是ActiveMQ测试邮件！！"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 测试三种消息监听器之MessageListaner
     */
    @Test
    public void messageListenerTest(){
        try {
            ioc.start();
            MQProducer producer = ioc.getBean(MQMessageListenerProducer.class);

            ClassRoom cr = new ClassRoom();
            for(int i = 0;i < 5 ; i++) {
                Student s = new Student("姜_"+i,20+i,i%2==0?"男":"女");
                cr.getStus().add(s);
            }

            producer.sendMassage(cr.getStus());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 测试三种消息监听器之SessionAwareMessageListaner
     * consumer端打印学生消息
     * producer端打印回调消息
     */
    @Test
    public void sessionAwareMessageListenerTest(){
        try {
            ioc.start();
            MQSessionAwareMessageListenerProducer producer = ioc.getBean(MQSessionAwareMessageListenerProducer.class);
            producer.sendStuInfoMassage(new Student("jiangBUG",25,"男"));
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }






    /**
     * 测试三种消息监听器之 MessageListanerAdapter
     */
    @Test
    public void messageListenerAdapterTest(){
        try {
            ioc.start();
            Destination destination = (Destination)ioc.getBean("messageListenerAdapterTestDestination");
            MQProducer producer = ioc.getBean(MQMessageListenerAdapterProducer.class);
            producer.sendMassage(destination, "hello MessageListenerAdapter!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
