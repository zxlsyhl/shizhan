package org.zxl.shizhan.kafka.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;
import org.zxl.shizhan.kafka.bo.OrderInfo;
import org.zxl.shizhan.kafka.dao.OrderDAO;
import org.zxl.shizhan.kafka.entity.TOrder;
import org.zxl.shizhan.kafka.entity.TOrderError;
import org.zxl.shizhan.kafka.entity.TOrderItem;
import org.zxl.shizhan.kafka.properties.KafkaProperties;
import org.zxl.shizhan.kafka.tool.ErrorType;
import org.zxl.shizhan.kafka.tool.OrderSd;
import org.zxl.shizhan.kafka.tool.SnowFlakeUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class ConsumerService {
    private Logger logger = LoggerFactory.getLogger(ConsumerService.class);
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
//            logger.debug("records is empty:{}",records.isEmpty());
//            if(!records.isEmpty()){
////                for (ConsumerRecord<String,String> consumerRecord:records.partitions()){
////
////                }
//                //遍历消息
//                for(ConsumerRecord<String,String> record:records) {
//                    logger.debug("partition:{}----topic:{}------value:{}-----offset:{}-----key:{}",record.partition(),record.topic(),record.value(),record.offset(),record.key());
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
