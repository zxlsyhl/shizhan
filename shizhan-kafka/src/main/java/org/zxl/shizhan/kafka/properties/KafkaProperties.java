package org.zxl.shizhan.kafka.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
//@PropertySource(value = "config.properties")
public class KafkaProperties {
    private String bootstrapServers; //192.168.1.105:9092 #服务器地址指定
    private String groupId; //: g2 #配置消费者组
    private String enableAutoCommit; //: false #配置是否自动确认offset;true自动提交，false手动提交。
    private String keyDeserializer;
    private String valuDeserializer;
    private String autoOffsetReset;
    private int timeOutMs;
    private String maxPollRecords; //poll消息最大数量

    private String keySerializer; //key和value 序列化
    private String valueSerializer;
    private String acks; //: all #发送消息是否应答
    private String retries; //: 0 #配置发送消息失败重试
    private String batchSize; //: 10241 #配置批量处理消息大小
    private String lingerMs; //: 5 #配置批量处理数据延迟
    private String bufferMemory; //: 1234321 #配置内存缓冲大小

    private String partitionAssignmentStrategy; //分组消费再平衡策略

    public String getPartitionAssignmentStrategy() {
        return partitionAssignmentStrategy;
    }

    public void setPartitionAssignmentStrategy(String partitionAssignmentStrategy) {
        this.partitionAssignmentStrategy = partitionAssignmentStrategy;
    }

    public String getMaxPollRecords() {
        return maxPollRecords;
    }

    public void setMaxPollRecords(String maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
    }

    public int getTimeOutMs() {
        return timeOutMs;
    }

    public void setTimeOutMs(int timeOutMs) {
        this.timeOutMs = timeOutMs;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEnableAutoCommit() {
        return enableAutoCommit;
    }

    public void setEnableAutoCommit(String enableAutoCommit) {
        this.enableAutoCommit = enableAutoCommit;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValuDeserializer() {
        return valuDeserializer;
    }

    public void setValuDeserializer(String valuDeserializer) {
        this.valuDeserializer = valuDeserializer;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public String getRetries() {
        return retries;
    }

    public void setRetries(String retries) {
        this.retries = retries;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public String getLingerMs() {
        return lingerMs;
    }

    public void setLingerMs(String lingerMs) {
        this.lingerMs = lingerMs;
    }

    public String getBufferMemory() {
        return bufferMemory;
    }

    public void setBufferMemory(String bufferMemory) {
        this.bufferMemory = bufferMemory;
    }
}
