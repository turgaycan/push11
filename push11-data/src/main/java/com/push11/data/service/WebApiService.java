package com.push11.data.service;

import com.push11.domain.Application;

import java.util.List;
import java.util.Map;

public interface WebApiService {
	
	public List<String> getAllTags(Application app);

	public List<String> getAllEvents(Application app);

	public List<String> getEventKeys(Application app, String eventName);

	public List<String> getEventValuesForKey(Application app, String eventName, String eventKey);
	
	public Map<String, Boolean> sendPushToDeviceList(List<String> deviceList, Map<String, String> content);

}
