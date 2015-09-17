package com.push11.data.config;

import com.push11.auth.config.Push11Security;
import com.push11.domain.config.Push11DomainConfig;
import com.push11.manager.Push11Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com.push11"})
@EnableMongoRepositories(basePackages = {"com.push11.domain.document", "com.push11.data.repository"})
@EnableAutoConfiguration(exclude = {MongoRepositoriesAutoConfiguration.class})
@ConditionalOnMissingBean(Push11Manager.class)
@Import(value = {Push11Security.class, Push11DomainConfig.class})
public class Push11Config {

    public static void main(String[] args) {
        SpringApplication.run(Push11Config.class, args);
    }
}
