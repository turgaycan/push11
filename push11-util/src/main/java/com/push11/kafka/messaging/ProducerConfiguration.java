//package com.push11.kafka.messaging;
//
//import com.push11.kafka.messaging.config.KafkaConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.integration.IntegrationMessageHeaderAccessor;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.kafka.Kafka;
//import org.springframework.integration.dsl.kafka.KafkaProducerMessageHandlerSpec;
//import org.springframework.integration.dsl.support.Consumer;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.GenericMessage;
//import org.springframework.stereotype.Component;
//
//import static org.springframework.integration.dsl.kafka.KafkaProducerMessageHandlerSpec.ProducerMetadataSpec;
//
//@Component
//public class ProducerConfiguration {
//
//    @Autowired
//    private KafkaConfig kafkaConfig;
//
//    private static final String OUTBOUND_ID = "outbound";
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerConfiguration.class);
//
//    @Bean
//    @DependsOn(OUTBOUND_ID)
//    CommandLineRunner kickOff(
//            @Qualifier(OUTBOUND_ID + ".input") MessageChannel in) {
//        return args -> {
//            for (int i = 0; i < 1000; i++) {
//                in.send(new GenericMessage<>("#" + i));
//                LOGGER.info("sending message #" + i);
//            }
//        };
//    }
//
//    @Bean(name = OUTBOUND_ID)
//    IntegrationFlow producer() {
//
//        LOGGER.info("starting producer flow..");
//        return flowDefinition -> {
//
//            Consumer<ProducerMetadataSpec> spec =
//                    metadata -> {
//                        metadata.async(true)
//                                .batchNumMessages(10)
//                                .valueClassType(String.class)
//                                .<String>valueEncoder(String::getBytes);
//                    };
//
//            KafkaProducerMessageHandlerSpec messageHandlerSpec =
//                    Kafka.outboundChannelAdapter(
//                            props -> props.put("queue.buffering.max.ms", "15000"))
//                            .messageKey(m -> m.getHeaders().get(IntegrationMessageHeaderAccessor.SEQUENCE_NUMBER))
//                            .addProducer(this.kafkaConfig.getTopic(),
//                                    this.kafkaConfig.getBrokerAddress(), spec);
//            flowDefinition
//                    .handle(messageHandlerSpec);
//        };
//    }
//}
