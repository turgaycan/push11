package com.push11.data.service;

import com.push11.data.repository.ActionRepository;
import com.push11.domain.Action;
import com.push11.domain.User;
import com.push11.model.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public void save(Action action) {
        actionRepository.save(action);
    }

    public Action getActionFindByActionAndRegId(String actionGroupId, String regId) {
        return actionRepository.getActionFindByActionAndRegId(actionGroupId, regId);
    }

    public void saveActions(List<Action> actionList) {
        actionRepository.save(actionList);
    }

    public void saveActions(User user, Map<String, Boolean> resultMap, Map<String, String> content) {
        Action action = newAction(user, resultMap, content);
        save(action);
    }

    private Action newAction(User user, Map<String, Boolean> resultMap, Map<String, String> content) {
        Action action = new Action();
        action.setActionType(ActionType.PUSH);
        action.setContent(content);
        action.setUser(user);
        action.setSucceed(resultMap.get(user.getRegistrationId()));
        return action;
    }


}
