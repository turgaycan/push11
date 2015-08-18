package com.push11.manager;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Push11NotificationManager implements Push11Manager {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11NotificationManager.class);

    /**
     * @param deviceList
     * @param content
     * @return if (true) return resultMap;
     */
    public Map<String, Boolean> pushAndroid(List<String> deviceList, Map<String, String> content) {
        Sender sender = new Sender(Push11NotificationConstants.GCM_API_KEY);
        Message gcmMessage = new Message.Builder().setData(content).build();
        MulticastResult result;
        try {
            result = sender.send(gcmMessage, deviceList, 5);
            return prepareResult(result, deviceList);
        } catch (Exception e) {
            LOGGER.error("Exception occurs when sending push {}", e);
        }
        return null;

    }

    private Map<String, Boolean> prepareResult(MulticastResult gcmResult, List<String> deviceList) {
        Map<String, Boolean> resultMap = new HashMap();
        int index = 0;
        for (Result result : gcmResult.getResults()) {
            resultMap.put(deviceList.get(index), StringUtils.isNotBlank(result.getMessageId()));
            index++;
        }
        return resultMap;
    }

    /**
     * @param deviceList
     * @param content
     * @return if (true) return resultMap;
     */
    public Map<String, Boolean> pushIOS(List<String> deviceList, Map<String, String> content) {

        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        try {
            PushNotificationPayload payload = preparePayload();
            List<PushedNotification> notifications = Push.payload(payload, Push11NotificationConstants.APNS_KEYSTORE, Push11NotificationConstants.APNS_PASSWORD, false, deviceList);

            for (PushedNotification notification : notifications) {
                if (notification.isSuccessful()) {
                    resultMap.put(notification.getDevice().getToken(), true);
                    LOGGER.info("Push notification sent successfully to {} " + notification.getDevice().getToken());
                } else {
                    resultMap.put(notification.getDevice().getToken(), false);
                    String invalidToken = notification.getDevice().getToken();
                    //TODO turgay: IMPORTANT : Add code here to remove invalidToken from your database
                    LOGGER.info("Push notification exception {}, for invalidToken : {}" + notification.getException(), invalidToken);
                    ResponsePacket responsePacket = notification.getResponse();
                    if (responsePacket != null) {
                        LOGGER.info("If the problem was an error-response packet returned by Apple, get it {} ", responsePacket.getMessage());
                    }
                }
            }
        } catch (KeystoreException e) {
            LOGGER.error("A critical problem occurred while trying to use your keystore {} ", e);
        } catch (CommunicationException e) {
            LOGGER.error("A critical communication error occurred while trying to contact Apple Serves {} ", e);
        }
        return resultMap;

    }

    private PushNotificationPayload preparePayload() {
        PushNotificationPayload payload = PushNotificationPayload.complex();
        try {
            /* Customize the payload */
            payload.addAlert("Hello World!");
            payload.addCustomDictionary("mykey1", "");
            payload.addCustomDictionary("mykey2", "");
            /* Push your custom payload */
        } catch (JSONException e) {
            LOGGER.error("JSON exception occues {} ", e);
        }
        return payload;
    }

    /**
     * @param deviceMap
     * @param content
     * @return if (true) return resultMap;
     */
    public Map<String, Boolean> pushAllPlatforms(Map<String, List<String>> deviceMap, Map<String, String> content) {
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        resultMap.putAll(pushAndroid(deviceMap.get(Push11NotificationConstants.PLATFORM_ANDROID), content));
        resultMap.putAll(pushIOS(deviceMap.get(Push11NotificationConstants.PLATFORM_IOS), content));
        return resultMap;

    }

}
