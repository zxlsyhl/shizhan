package org.zxl.shizhan.kafka.tool;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.zxl.shizhan.kafka.properties.KafkaProperties;

import java.util.Properties;

public class KafkaTool {
    @Autowired
    private KafkaProperties kafkaProperties;

    public KafkaProducer<String, String> kafkaProducer(){
        Properties prop = new Properties();
        //1.配置kafka节点地址
        prop.put("bootstrap.servers", kafkaProperties.getBootstrapServers());
        //2.发送消息是否应答
        prop.put("acks",kafkaProperties.getAcks());
        //3.配置发送消息失败重试
        prop.put("retries",kafkaProperties.getRetries());
        //4.配置批量处理消息大小
        prop.put("batch.size" ,kafkaProperties.getBatchSize());
        //5.配置批量处理数据延迟
        prop.put("linger.ms",kafkaProperties.getLingerMs());
        //6.配置内存缓冲大小
        prop.put("buffer.memory",kafkaProperties.getBufferMemory());
        //7.信息发送前必须序列化
        prop.put("key.serializer",kafkaProperties.getKeySerializer());
        prop.put("value.serializer",kafkaProperties.getValueSerializer());
        //实例化
        KafkaProducer<String,String> prodecer = new KafkaProducer(prop);
        return prodecer;
    }

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
}
