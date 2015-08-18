package com.push11.data.repository.core;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
@Configuration
public abstract class AbstractIntegration<T> {

	@Autowired
	private MongoOperations mongoOperations;

	private final Class<T> clazz;

	public AbstractIntegration(final Class<T> clazz) {
		this.clazz = clazz;
	}

	protected Class getClazz() {
		return clazz;
	}

	public void deleteCollection() {
		if (mongoOperations.collectionExists(clazz.getSimpleName().toLowerCase())) {
			mongoOperations.dropCollection(clazz);
		}
	}

	@After
	public void reset() {
		deleteCollection();
	}

}
