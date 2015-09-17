package com.push11.auth.filter;

import com.push11.auth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

//only check
@Component
public class Push11PermissionEvaluator implements PermissionEvaluator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11PermissionEvaluator.class);
    private static final Map<String, List<String>> testPermissionMap;

    static {
        Map<String, List<String>> map = new HashMap();
        ArrayList<String> whiteList = new ArrayList<String>();
        whiteList.add("11111");
        whiteList.add("22222");
        whiteList.add("33333");
        whiteList.add("44444");
        ArrayList<String> blackList = new ArrayList<String>();
        blackList.add("99999");
        blackList.add("XXXXX");
        map.put("212389921", whiteList);
        map.put("999999999", blackList);
        testPermissionMap = Collections.unmodifiableMap(map);
    }

    public boolean hasPermission(Authentication authentication, Object targetDomainObject,
                                 Object permission) {
        return true;
    }

    ;

    public boolean hasPermission(Authentication authentication, Serializable targetId,
                                 String targetType, Object permission) {
        return authorize((User) authentication.getPrincipal(), (String) targetId);
    }

    ;

    public boolean authorize(User user, String thingId) {
        boolean allowed = false;
        LOGGER.info("Authorizing " + user.getUsername() + "...");
        if (testPermissionMap.get(user.getUsername()) != null &&
                testPermissionMap.get(user.getUsername()).contains(thingId)) {
            allowed = true;
            System.out.println(user.getUsername() + " authorized!");
        }
        return allowed;
    }

}
