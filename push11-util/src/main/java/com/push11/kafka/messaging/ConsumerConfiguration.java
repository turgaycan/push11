package com.push11.kafka.messaging;

import com.push11.kafka.messaging.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
import org.springframework.integration.dsl.kafka.Kafka;
import org.springframework.integration.dsl.kafka.KafkaHighLevelConsumerMessageSourceSpec;
import org.springframework.integration.dsl.support.Consumer;
import org.springframework.integration.kafka.support.ZookeeperConnect;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.List;
import java.util.Map;


@Configuration
public class ConsumerConfiguration {

    @Autowired
    private KafkaConfig kafkaConfig;

    private static final String OUTBOUND_ID = "outbound";

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerConfiguration.class);

    @Bean
    @DependsOn(OUTBOUND_ID)
    CommandLineRunner kickOff(
            @Qualifier(OUTBOUND_ID + ".input") MessageChannel in) {
        return args -> {
            for (int i = 0; i < 1000; i++) {
                in.send(new GenericMessage<>("#" + i));
                LOGGER.info("sending message #" + i);
            }
        };
    }

    @Bean
    IntegrationFlow consumer() {

        LOGGER.info("starting consumer..");

        KafkaHighLevelConsumerMessageSourceSpec messageSourceSpec = Kafka.inboundChannelAdapter(
                new ZookeeperConnect(this.kafkaConfig.getZookeeperAddress()))
                .consumerProperties(props ->
                        props.put("auto.offset.reset", "smallest")
                                .put("auto.commit.interval.ms", "100"))
                .addConsumer("myGroup", metadata -> metadata.consumerTimeout(100)
                        .topicStreamMap(m -> m.put(this.kafkaConfig.getTopic(), 1))
                        .maxMessages(10)
                        .valueDecoder(String::new));

        Consumer<SourcePollingChannelAdapterSpec> endpointConfigurer = e -> e.poller(p -> p.fixedDelay(100));

        return IntegrationFlows
                .from(messageSourceSpec, endpointConfigurer)
                .<Map<String, List<String>>>handle((payload, headers) -> {
                    payload.entrySet().forEach(e -> LOGGER.info(e.getKey() + '=' + e.getValue()));
                    return null;
                })
                .get();
    }
}
