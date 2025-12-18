package com.inzeph.EmployeeManagement.ServiceInterface;

import com.google.firebase.messaging.*;
import com.inzeph.EmployeeManagement.Model.PushNotificationRequest;

import java.util.concurrent.ExecutionException;

public interface PushNotificationServiceInterface {
    void sendPushNotification(PushNotificationRequest request, String token);
    void sendMessageToToken(PushNotificationRequest request, String token) throws InterruptedException, ExecutionException;
    String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException;
    AndroidConfig getAndroidConfig(String topic);
    ApnsConfig getApnsConfig(String topic);
    Message getPreconfiguredMessageToToken(PushNotificationRequest request,String token);
    Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request);
}
