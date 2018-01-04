import com.amq.producer.MQProducer;
import com.amq.producer.entity.MailParam;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MQProducerTest {

    public static void main(String[] args) {

        try {
            ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext( "classpath:/Spring-context.xml");

            ioc.start();

            MQProducer producer = ioc.getBean(MQProducer.class);

            producer.sendMassage(new MailParam("376699923@qq.com","ActiveMQ测试","这是ActiveMQ测试邮件！！"));
        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}
