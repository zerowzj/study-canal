package test.study.canal.kafka;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import study.canal.kafka.support.SpringBootCfg;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootCfg.class})
public class KafkaTest {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Test
    public void test() {
        String topic = "example";
        String data = "this is data";
        ListenableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}");
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("kafka sendMessage  topic = {}, data = {}");
            }
        });
    }
}
