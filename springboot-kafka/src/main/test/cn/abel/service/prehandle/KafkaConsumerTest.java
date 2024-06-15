package cn.abel.service.prehandle;

//import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaConsumerTest {

    private static final String TOPIC_NAME = "test-topic";

    private static final String GROUP_ID = "group-id";

    private static final String MESSAGE = "test-message";

    private static final String PARTITION_ID = "0";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaConsumer<String, String> kafkaConsumer;

    private CountDownLatch latch;

//    @Autowired
    public void setKafkaConsumer(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @Before
    public void setUp() {
        latch = new CountDownLatch(1);
    }

    @Test
    public void testConsumer() throws InterruptedException {
        kafkaTemplate.send(TOPIC_NAME, PARTITION_ID, MESSAGE);
        latch.await(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        kafkaConsumer.close();
    }

    @KafkaListener(topics = TOPIC_NAME, containerFactory = "kafkaListenerContainerFactory", groupId = GROUP_ID)
    public void consume(String message) {
        System.out.printf("#### -> Consumed message -> %s%n", message);
        latch.countDown();
    }
}