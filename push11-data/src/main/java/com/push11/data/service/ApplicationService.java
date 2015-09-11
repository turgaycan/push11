package com.push11.data.service;

import com.push11.data.repository.ApplicationRepository;
import com.push11.domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public Application saveEntity(Application application) {
		return applicationRepository.save(application);
	}

	public Application findById(String id){
		return applicationRepository.findOne(id);
	}
}
