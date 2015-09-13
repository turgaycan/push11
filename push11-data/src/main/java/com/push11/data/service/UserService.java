package com.push11.data.service;

import com.push11.data.repository.UserRepository;
import com.push11.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveEntity(User user) {
        return userRepository.save(user);
    }

    public User findById(String userId){
        return userRepository.findOne(userId);
    }
}
