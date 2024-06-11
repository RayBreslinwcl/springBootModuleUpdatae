package cn.abel.service.prehandle;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yyb on 2018/12/10.
 */

@Component
public class KafkaConsumerService {

    @Autowired
    private SplitService splitService;

    @KafkaListener(topics = "${log.statistical.kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage(List<ConsumerRecord<?, ?>> records) {
//        public void receive(List<String> events, Consumer<String, String> consumer) {
        for (ConsumerRecord<?, ?> record : records) {
            System.out.println("=====================processMessage=====================");
            String message = (String) record.value();
            splitService.saveAndSplitLog(message);
        }
    }

    /**
     * processMessage2和processMessage一样都是可以消费对应topic的消息
     * @param events
     * @param consumer
     */
    @KafkaListener(topics = "${log.statistical.kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage2(List<String> events, Consumer<String, String> consumer) {
//        public void receive(List<String> events, Consumer<String, String> consumer) {
        for (String event : events) {
            System.out.println("=====================processMessage2=====================");
            System.out.println(event);
        }
    }
}
