package org.zxl.shizhan.kafka.runner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.zxl.shizhan.kafka.config.KafkaConfig;
import org.zxl.shizhan.kafka.properties.KafkaProperties;
import org.zxl.shizhan.kafka.tool.KafkaTool;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ConsumerRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(ConsumerRunner.class);
    @Autowired
    private KafkaProperties kafkaProperties;

    private KafkaTool kafkaTool = new KafkaTool();
    private String topicName = "zhangmuyan";

    public KafkaConsumer<String, String> kafkaConsumer(){
        //1.配置消费者属性
        Properties prop = new Properties();
        //配置属性
        //服务器地址指定
        prop.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        //配置消费者组
        prop.put("group.id", kafkaProperties.getGroupId());
        //配置是否自动确认offset;true自动提交，false手动提交。
        prop.put("enable.auto.commit", kafkaProperties.getEnableAutoCommit());
        //序列化
        prop.put("key.deserializer", kafkaProperties.getKeyDeserializer());
        prop.put("value.deserializer", kafkaProperties.getValuDeserializer());
        //
        prop.put("auto.offset.reset", kafkaProperties.getAutoOffsetReset());

        prop.put("max.poll.records", kafkaProperties.getMaxPollRecords());
//        prop.put("session.timeout.ms","30000");

        prop.put("partition.assignment.strategy",kafkaProperties.getPartitionAssignmentStrategy());

        prop.put("max.poll.interval.ms", 5000);

        //2.实例消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        return consumer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("runner run start!!!!");
        for(int i=0;i<2;i++){
            Thread thread = new Thread(new ConsumerThread());
            thread.start();
        }
        logger.debug("runner run end!!!!");
    }

    class ConsumerThread implements Runnable{
        @Override
        public void run() {
            KafkaConsumer<String, String> kafkaConsumer = kafkaConsumer();
            kafkaConsumer.subscribe(Arrays.asList(topicName));
            ConsumerRecords<String,String> records = null;
            try {
                while (true){
                    records= kafkaConsumer.poll(kafkaProperties.getTimeOutMs());
                    logger.debug("records is empty:{}",records.isEmpty());
                    if(!records.isEmpty()){
                        //遍历消息
                        for(ConsumerRecord<String,String> record:records) {
                            logger.debug("partition:{}----topic:{}------value:{}-----offset:{}-----key:{}",record.partition(),record.topic(),record.value(),record.offset(),record.key());
                        }
                    }
                    //手动提交
                    kafkaConsumer.commitAsync();
                    Thread.sleep(30000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
