package com.push11.data.repository;

import com.push11.data.repository.core.AbstractIntegration;
import com.push11.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest extends AbstractIntegration<User> {

	private User user1, user2;

	public UserRepositoryTest() {
		super(User.class);
	}

	@Autowired
	private UserRepository repository;

	@Before
	public void init() {
		prepareUsers();
	}

	@Test
	public void shouldSaveUser() {
		User userDb1 = repository.save(user1);
		User userDb2 = repository.save(user2);

		assertTrue(userDb1.getRegId().equals((user1.getRegId())));
		assertThat(userDb2.getRegId(), equalTo(user2.getRegId()));
	}

	@Test
	public void shouldFindUserByRegId() {
		repository.save(user1);

		User user = repository.findUserByRegId("regId1");
		assertThat(user.getBuyerId(), equalTo(user1.getBuyerId()));
	}

	private void prepareUsers() {
		user1 = new User();
		user1.setBuyerId("buyer1");
		user1.setDeviceBrand("android");
		user1.setRegId("regId1");

		user2 = new User();
		user2.setBuyerId("buyer2");
		user2.setDeviceBrand("android");
		user2.setRegId("regId2");
	}

}
