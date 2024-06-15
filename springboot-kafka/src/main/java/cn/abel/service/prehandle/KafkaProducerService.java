package cn.abel.service.prehandle;

import cn.abel.message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Value("${log.statistical.kafka.topic}")
    private String topic;

    @Autowired
    @Qualifier("kafkaTemplate")
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage( OrderMessage orderMessage) {
        kafkaTemplate.send(topic, orderMessage.getOrderId(), orderMessage);
    }

}