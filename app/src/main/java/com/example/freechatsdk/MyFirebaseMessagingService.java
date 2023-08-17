package com.example.freechatsdk;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatNotificationConfig;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Freshchat.getInstance(this).setPushRegistrationToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);
        if (Freshchat.isFreshchatNotification(message)) {
            FreshchatNotificationConfig notificationConfig = new FreshchatNotificationConfig().setNotificationSoundEnabled(true)
                    .setSmallIcon(R.drawable.ic_launcher_background).setLargeIcon(R.drawable.ic_launcher_background)
                    .launchActivityOnFinish(MainActivity.class.getName()).setPriority(NotificationCompat.PRIORITY_HIGH);
            Freshchat.getInstance(getApplicationContext()).setNotificationConfig(notificationConfig);
            Freshchat.handleFcmMessage(this, message);
        }else {
            //Handle Here
        }
    }
}