package com.push11.controller;

import com.push11.domain.Application;
import com.push11.model.request.GetEventKeysRequestModel;
import com.push11.model.request.GetEventValuesForKeyRequestModel;
import com.push11.model.request.SendPushRequest;
import com.push11.util.Push11EndpointPaths;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//TODO turgay : isimler değişmeli ama öncesinde cenk ve onur la konuş
@RestController
@RequestMapping("/event")
public class EventController {

	@RequestMapping(value = "/all/tags", method = RequestMethod.POST)
	public @ResponseBody List<String> getAllTags(@RequestBody Application app) {
//		return service.getAllTags(app);
		return null;
	}

	@RequestMapping(value = "/all/events", method = RequestMethod.POST)
	public @ResponseBody List<String> getAllEvents(@RequestBody Application app) {
//		return service.getAllEvents(app);
		return null;
	}

	@RequestMapping(value = "/event/keys", method = RequestMethod.POST)
	public @ResponseBody List<String> getEventKeys(@RequestBody GetEventKeysRequestModel request) {
//		return service.getEventKeys(request.getApplication(), request.getName());
		return null;
	}

	@RequestMapping(value = "/event/values/key", method = RequestMethod.POST)
	public @ResponseBody List<String> getEventValuesForKey(@RequestBody GetEventValuesForKeyRequestModel request) {
//		return service.getEventValuesForKey(request.getApplication(), request.getName(), request.getEventKey());
		return null;
	}

	@RequestMapping(value = "/push/devices", method = RequestMethod.POST)
		 public @ResponseBody Map<String, Boolean> getEventValuesForKey(@RequestBody SendPushRequest request) {
//		return service.sendPushToDeviceList(request.getUserIdList(), request.getContent());
		return null;
	}

	@RequestMapping(value = Push11EndpointPaths.NEW, method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> newEvent(@RequestBody SendPushRequest sendPushRequest) {
		return null;
	}

}
