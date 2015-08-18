package com.push11.data.service;

import com.push11.data.repository.ActionRepository;
import com.push11.data.repository.EventRepository;
import com.push11.data.repository.UserRepository;
import com.push11.domain.Action;
import com.push11.domain.Event;
import com.push11.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientApiService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ActionRepository actionRepository;

	@Autowired
	public EventRepository eventRepository;

	public void registerUser(User user) {
		userRepository.save(user);
	}

	public void updateUserMemberId(String pushId, String memberId) {
		User user = userRepository.findUserByRegId(pushId);
		user.setBuyerId(memberId);
		userRepository.save(user);
	}

	public void saveEvent(String pushId, Event event) {
		User user = userRepository.findUserByRegId(pushId);
		event.setUser(user);
		eventRepository.save(event);
	}

	//TODO turgay: sor aşağıdaki metod duplike
	public void addTags(String pushId, String... tags) {
		User user = userRepository.findUserByRegId(pushId);
		user = prepareTagListForUser(user, tags);
		userRepository.save(user);
	}

	private User prepareTagListForUser(User user, String... tags) {
		List<String> tagList = user.getTagList();
		if (CollectionUtils.isNotEmpty(tagList)) {
			tagList = new ArrayList<>();
		}

		for (String tag : tags) {
			tagList.add(tag);
		}
		user.setTagList(tagList);
		return user;
	}

	//TODO turgay: sor aşağıdaki metod duplike
	public void setTasgs(String pushId, String... tags) {
		User user = userRepository.findUserByRegId(pushId);

		List<String> taglist = user.getTagList();
		if (taglist == null) {
			taglist = new ArrayList<String>();
		} else {
			taglist.clear();
		}

		for (String tag : tags) {
			taglist.add(tag);
		}

		user.setTagList(taglist);
		userRepository.save(user);
	}

	public void notifyPushResult(String actionId) {
		Action action = actionRepository.findOne(actionId);
		action.setOpened(true);
		actionRepository.save(action);
	}

	public void sendPush(String content, String... pushIds) {
		for (String pushId : pushIds) {
			//false ise neden değişkene atanmış
			boolean isSucceed = false;
			User user = userRepository.findUserByRegId(pushId);
			Action action = new Action();
			action.setActionType("push_type");
			action.setContent(content);
			action.setCreateDate(new Date());
			action.setOpened(false);
			action.setSucceed(isSucceed);
			action.setUser(user);
			actionRepository.save(action);
		}
	}

}
