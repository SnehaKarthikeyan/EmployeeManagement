package com.inzeph.EmployeeManagement.Service;

import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inzeph.EmployeeManagement.Model.NotificationParameter;
import com.inzeph.EmployeeManagement.Model.PushNotificationRequest;
import com.inzeph.EmployeeManagement.ServiceInterface.PushNotificationServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService implements PushNotificationServiceInterface {
    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);

    public void sendPushNotification(PushNotificationRequest request, String token) {
        try {
            sendMessageToToken(request, token);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendMessageToToken(PushNotificationRequest request, String token) throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request, token);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + token + ", " + response+ " msg "+jsonOutput);
    }

    public String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    public AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
    }

    public ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    public Message getPreconfiguredMessageToToken(PushNotificationRequest request,String token) {
        return getPreconfiguredMessageBuilder(request).setToken(String.valueOf(token))
                .build();
    }

    public Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }
}
