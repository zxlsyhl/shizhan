package org.zxl.shizhan.kafka.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.zxl.shizhan.kafka.properties.KafkaProperties;

import java.util.Properties;

@Configuration
public class KafkaConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
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

}
