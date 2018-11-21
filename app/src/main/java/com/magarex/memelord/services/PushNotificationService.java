package com.magarex.memelord.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.magarex.memelord.data.UserRepository;

import javax.inject.Inject;

/**
 * Created by sahil on 19/11/18.
 **/
public class PushNotificationService extends FirebaseMessagingService {

    @Inject
    UserRepository userRepository;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        userRepository.updateMyFCMToken(token);
    }

}
