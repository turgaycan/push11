//package com.push11.kafka.messaging;
//
//import com.push11.kafka.messaging.config.KafkaConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
//import org.springframework.integration.dsl.kafka.Kafka;
//import org.springframework.integration.dsl.kafka.KafkaHighLevelConsumerMessageSourceSpec;
//import org.springframework.integration.dsl.support.Consumer;
//import org.springframework.integration.kafka.support.ZookeeperConnect;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
//
//@Component
//public class ConsumerConfiguration {
//
//    @Autowired
//    private KafkaConfig kafkaConfig;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfiguration.class);
//
//    @Bean
//    IntegrationFlow consumer() {
//
//        LOGGER.info("starting consumer..");
//
//        KafkaHighLevelConsumerMessageSourceSpec messageSourceSpec = Kafka.inboundChannelAdapter(
//                new ZookeeperConnect(this.kafkaConfig.getZookeeperAddress()))
//                .consumerProperties(props ->
//                        props.put("auto.offset.reset", "smallest")
//                                .put("auto.commit.interval.ms", "100"))
//                .addConsumer("myGroup", metadata -> metadata.consumerTimeout(100)
//                        .topicStreamMap(m -> m.put(this.kafkaConfig.getTopic(), 1))
//                        .maxMessages(10)
//                        .valueDecoder(String::new));
//
//        Consumer<SourcePollingChannelAdapterSpec> endpointConfigurer = e -> e.poller(p -> p.fixedDelay(100));
//
//        return IntegrationFlows
//                .from(messageSourceSpec, endpointConfigurer)
//                .<Map<String, List<String>>>handle((payload, headers) -> {
//                    payload.entrySet().forEach(e -> LOGGER.info(e.getKey() + '=' + e.getValue()));
//                    return null;
//                })
//                .get();
//    }
//}
