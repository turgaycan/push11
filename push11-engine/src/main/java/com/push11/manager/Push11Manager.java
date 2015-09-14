package com.push11.manager;


import java.util.List;
import java.util.Map;

public interface Push11Manager {

    Map<String, Boolean> pushAndroid(List<String> regIdList, Map<String, String> content);
    Map<String, Boolean> pushIOS(List<String> regIdList, Map<String, String> content);
    Map<String, Boolean> pushAllPlatforms(Map<String, List<String>> deviceMap, Map<String, String> content);

}
