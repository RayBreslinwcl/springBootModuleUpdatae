package cn.abel.service.prehandle;

import cn.abel.message.OrderMessage;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.plugin2.message.Message;
import sun.plugin2.message.Serializer;

import java.io.IOException;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/15 22:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaProducerServiceTest extends TestCase {

    @Autowired
    KafkaProducerService kafkaProducerService;


    @Test
    public void testSendMessage() {
        kafkaProducerService.sendMessage(new OrderMessage("12","test2"));
    }
}