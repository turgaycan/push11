package com.push11.data.service;

import com.google.common.collect.Lists;
import com.push11.data.repository.UserRepository;
import com.push11.domain.User;
import com.push11.model.request.ReqUpdateMemberIdModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public List<User> findUsersListByBuyerIds(List<String> userIdList) {
        return Lists.newArrayList(userRepository.findUsersByBuyerId(userIdList));
    }

    public List<User> getUsersFindByApp(String appId) {
        return userRepository.getUsersFindByApp(appId);
    }

    public List<String> convertUserToRegistrationIds(List<User> users) {
        List<String> registrationIds = Lists.newArrayList();
        users.forEach(each -> registrationIds.add(each.getRegistrationId()));
        return registrationIds;
    }
}
