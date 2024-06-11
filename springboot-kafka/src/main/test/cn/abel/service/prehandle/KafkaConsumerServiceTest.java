package cn.abel.service.prehandle;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/11 11:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaConsumerServiceTest extends TestCase {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    public void testProcessMessage() {
//        kafkaConsumerService.
//        kafkaConsumerService.processMessage();
    }
}