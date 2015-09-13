package com.push11.kafka.messaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

    public static final String TEST_TOPIC_ID = "event-stream";

    @Value("${kafka.topic:" + TEST_TOPIC_ID + "}")
    private String topic;

    @Value("${kafka.address:localhost:9092}")
    private String brokerAddress;

    @Value("${zookeeper.address:localhost:2181}")
    private String zookeeperAddress;

    KafkaConfig() {
    }

    public KafkaConfig(String topic, String brokerAddress, String zookeeperAddress) {
        this.topic = topic;
        this.brokerAddress = brokerAddress;
        this.zookeeperAddress = zookeeperAddress;
    }

    public String getTopic() {
        return topic;
    }

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public String getZookeeperAddress() {
        return zookeeperAddress;
    }


}
