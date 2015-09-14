package com.push11.data.service;

import com.push11.data.repository.UserRepository;
import com.push11.domain.User;
import com.push11.model.request.ReqUpdateMemberIdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveEntity(User user) {
        return userRepository.save(user);
    }

    public User findById(String userId) {
        return userRepository.findOne(userId);
    }

    public User findUserByRegistrationId(String registrationId) {
        return userRepository.findUserByRegistrationId(registrationId);
    }

    public void updateUser(ReqUpdateMemberIdModel reqUpdateMemberIdModel) {
        User user = findUserByRegistrationId(reqUpdateMemberIdModel.getRegistrationId());
        user.setBuyerId(reqUpdateMemberIdModel.getBuyerId());
        saveEntity(user);
    }

}
