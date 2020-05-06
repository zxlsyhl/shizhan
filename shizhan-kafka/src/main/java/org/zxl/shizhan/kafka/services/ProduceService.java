package org.zxl.shizhan.kafka.services;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduceService {
    private Logger logger = LoggerFactory.getLogger(ProduceService.class);
    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    public void sendMsg(String subscribe, String key, String value){
        //实例化 回调方法Callback 实现了发送消息的ack机制
//        KafkaProducer<String,String> prodecer = new KafkaProducer<String,String>(prop);
        kafkaProducer.send(new ProducerRecord<String, String>(subscribe, key, value), new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (metadata!=null) {
                    logger.debug("topic:{};offset:{};partition:{}",metadata.topic(),metadata.offset(),metadata.partition());
                }
            }
        });

    }

}
