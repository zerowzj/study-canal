package study.canal.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import study.canal.kafka.common.JsonUtils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class SimpleListener {

    @KafkaListener(topics = {"example"})
    public void onMessage(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("record= {}", record);
            Map data = JsonUtils.fromJson(message.toString(), Map.class);
            System.out.println(JsonUtils.toJson(data));
        }
    }
}
