package com.push11.manager;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Push11NotificationManagerTest {

    @Test
    public void shouldBuildMessage(){
        Push11NotificationManager manager = new Push11NotificationManager();
        Map<String, String> params = new HashMap();
        params.put("title", "title");
        params.put("message", "message");
        params.put("json", "json");
//
//        Message message = manager.buildMessage(params);
//
//        Map<String, String> data = message.getData();
//
//        assertTrue(data.containsKey("CONTENT_TITLE"));
//        assertThat(data.get("CONTENT_TITLE"), is("title"));
//        assertTrue(data.containsKey("CONTENT_MESSAGE"));
//        assertThat(data.get("CONTENT_MESSAGE"), is("message"));
//        assertTrue(data.containsKey("CONTENT_JSON"));
//        assertThat(data.get("CONTENT_JSON"), is("json"));
    }

}