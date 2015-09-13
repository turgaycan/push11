package com.push11.data.repository.core;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
@Configuration
public abstract class AbstractIntegration {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${mongo.db.name}")
    private String dbName;

    @Before
    public void resetBefore() {
        dropDb();
    }

    @After
    public void dropAfter() {
        dropDb();
    }

    private void dropDb() {
        mongoTemplate.getDb().dropDatabase();
    }

}
