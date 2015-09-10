package com.push11.service;

import com.push11.manager.Push11Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Push11ManagerServiceTest {

    @InjectMocks
    private Push11ManagerService service;

    @Mock
    private Push11Manager push11Manager;

    @Test
    public void shouldPushNotificationToAndroidDevicesSuccessful(){
        HashMap<String, Boolean> returnMap = new HashMap<>();
        returnMap.put("success", Boolean.TRUE);
        returnMap.put("failure", Boolean.FALSE);
        when(push11Manager.pushAndroid(anyList(), anyMap())).thenReturn(returnMap);

        Map<String, Boolean> actualMap = service.pushAndroid(anyList(), anyMap());

        assertTrue(actualMap.get("success"));
        assertFalse(actualMap.get("failure"));
    }

    @Test
    public void shouldNotPushNotificationToAndroidDevicesSuccessfulAsAResultOfRetries(){
        HashMap<String, Boolean> returnMap = new HashMap<>();
        returnMap.put("success", Boolean.FALSE);
        returnMap.put("failure", Boolean.TRUE);
        when(push11Manager.pushAndroid(anyList(), anyMap())).thenReturn(returnMap);

        Map<String, Boolean> actualMap = service.pushAndroid(anyList(), anyMap());

        assertFalse(actualMap.get("success"));
        assertTrue(actualMap.get("failure"));
    }

    @Test
    public void shouldPushNotificationToIOSdDevicesSuccessful(){
        HashMap<String, Boolean> returnMap = new HashMap<>();
        returnMap.put("success", Boolean.TRUE);
        returnMap.put("failure", Boolean.FALSE);
        when(push11Manager.pushIOS(anyList(), anyMap())).thenReturn(returnMap);

        Map<String, Boolean> actualMap = service.pushIOS(anyList(), anyMap());

        assertTrue(actualMap.get("success"));
        assertFalse(actualMap.get("failure"));
    }

    @Test
    public void shouldNotPushNotificationToIOSDevicesSuccessfulAsAresultOfRetries(){
        HashMap<String, Boolean> returnMap = new HashMap<>();
        returnMap.put("success", Boolean.FALSE);
        returnMap.put("failure", Boolean.TRUE);
        when(push11Manager.pushIOS(anyList(), anyMap())).thenReturn(returnMap);

        Map<String, Boolean> actualMap = service.pushIOS(anyList(), anyMap());

        assertFalse(actualMap.get("success"));
        assertTrue(actualMap.get("failure"));
    }

}