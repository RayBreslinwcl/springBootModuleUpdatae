package cn.abel.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/11 11:46
 */
@Service
public class KafkaComsumer {
    @KafkaListener(topics = "testall", groupId = "your_group_id")
    public void listen(String message) {
        // Do something with the message
        System.out.println("Received message: " + message);
    }
}
