package org.zxl.shizhan.kafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zxl.shizhan.kafka.properties.KafkaProperties;

import java.util.Arrays;

@Service
public class Consumer2Service {
    private Logger logger = LoggerFactory.getLogger(Consumer2Service.class);
//    @Autowired
//    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private KafkaProperties kafkaProperties;

    /**
     * 拉取指定主题和数量的消息
     * @param subscribe
     * @param commitFlag
     * @return
     */
    public void poolMsg(String subscribe, int commitFlag){
//        //4.释放资源 线程安全
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//
//            public void run() {
//            }
//        }));
        //订阅消息主题
//        kafkaConsumer.subscribe(Arrays.asList(subscribe)); //"nginxlog"
//        ConsumerRecords<String,String> records = null;
//        try {
//            records= kafkaConsumer.poll(kafkaProperties.getTimeOutMs());
//            logger.debug("sssssssssss:{}",records.isEmpty());
//            if(!records.isEmpty()){
//                //遍历消息
//                for(ConsumerRecord<String,String> record:records) {
//                    logger.debug("{}------{}-----{}-----{}",record.topic(),record.value(),record.offset(),record.key());
//                }
//            }
//            if(commitFlag ==0){
//                //手动提交
//                kafkaConsumer.commitAsync();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
////            if(kafkaConsumer != null) {
////                kafkaConsumer.close();
////            }
//        }
    }
}
