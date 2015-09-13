package com.push11.data.service;

import com.push11.data.repository.ApplicationTagRepository;
import com.push11.domain.Application;
import com.push11.domain.ApplicationTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationTagService {

    @Autowired
    private ApplicationTagRepository applicationTagRepository;

    public List<ApplicationTag> findApplicationTagsByApplication(Application application){
        return applicationTagRepository.findApplicationTagsByApplication(application);
    }
}
