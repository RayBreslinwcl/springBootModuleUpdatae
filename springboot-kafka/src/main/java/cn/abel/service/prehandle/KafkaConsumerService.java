package cn.abel.service.prehandle;

import cn.abel.dto.PeopleMappingParam;
import cn.abel.service.PeopleMappingService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yyb on 2018/12/10.
 */

@Component
@Slf4j
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

    @Autowired
    PeopleMappingService peopleMappingService;
    /**
     * processMessage2和processMessage一样都是可以消费对应topic的消息
     * 生产类似模块
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
        try {
            if (CollectionUtils.isEmpty(events)) {
                consumer.commitAsync();
                return;
            }
            List<PeopleMappingParam> peopleMappingParamList = events.stream()
                    .map(event -> JSON.parseObject(event, PeopleMappingParam.class))
                    .collect(Collectors.toList());
            peopleMappingService.realtimeProcess(peopleMappingParamList);
            consumer.commitAsync();
        } catch (Exception ex) {
            log.error("listen error: ", ex);
        }
    }
}
