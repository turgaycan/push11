//package com.push11.kafka.messaging.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaConfig {
//
//
//    @Value("${kafka.topic}")
//    private String topic;
//
//    @Value("${kafka.address}")
//    private String brokerAddress;
//
//    @Value("${zookeeper.address}")
//    private String zookeeperAddress;
//
//    KafkaConfig() {
//    }
//
//    public KafkaConfig(String topic, String brokerAddress, String zookeeperAddress) {
//        this.topic = topic;
//        this.brokerAddress = brokerAddress;
//        this.zookeeperAddress = zookeeperAddress;
//    }
//
//    public String getTopic() {
//        return topic;
//    }
//
//    public String getBrokerAddress() {
//        return brokerAddress;
//    }
//
//    public String getZookeeperAddress() {
//        return zookeeperAddress;
//    }
//
//
//}
