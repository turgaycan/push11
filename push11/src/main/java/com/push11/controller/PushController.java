package com.push11.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.push11.domain.User;

@RestController
@RequestMapping("/push11")
public class PushController {

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public @ResponseBody User getUserById(@PathVariable String id) {
		
		User user = new User();
		user.setId("123");
		return user;
	}

}
