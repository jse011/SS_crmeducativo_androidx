package com.consultoraestrategia.ss_crmeducativo.chatGrupal.notificacion;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationService {
    private static AtomicInteger c = new AtomicInteger();
    private final static String GROUP_NAME = "my_group_name";
    private static final String CHANNEL_ID = "my_notification_channel";
    private static final String CHANNEL_ID_MUTED = "my_notification_channel_muted";

    public void AddNotification(Context appContext)
    {
        this.createNotification(appContext);
    }

    private void createNotification(Context appContext)
    {
        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        int id = getNextNotificationID();

        //Use Notification Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext, CHANNEL_ID);
        NotificationCompat.Builder builderMuted = new NotificationCompat.Builder(appContext, CHANNEL_ID_MUTED);

        String title = "Javier Cantos";
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque metus nisl, feugiat non vulputate et, dictum et odio. Morbi malesuada ultrices semper";

        //create the notification
        Notification notification = builder
                //.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_messenger)
                .setTicker(title)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setGroup(GROUP_NAME)
                .setAutoCancel(true)
                .build();

        //show the notification
        notificationManager.notify(id, notification);

        int numberNotifications = getNumberNotifications(GROUP_NAME, appContext);
        if (numberNotifications > 1)
        {
            text = GROUP_NAME + " (" + numberNotifications + ")";

            //create the summary notification
            Notification notificationSummary = builderMuted
                    //.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.ic_messenger)
                    .setStyle(new NotificationCompat.BigTextStyle().setSummaryText(text))
                    .setSound(null)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setGroupSummary(true)
                    .setGroup(GROUP_NAME)
                    .setShortcutId("summary_" + GROUP_NAME)
                    .setAutoCancel(true)
                    .build();

            //show the notification
            int idSumm = getIdSummaryNotification(GROUP_NAME, appContext);
            notificationManager.notify(idSumm, notificationSummary);
        }

    }

    private int getNextNotificationID()
    {
        return c.getAndIncrement();
    }


    private int getNumberNotifications(String groupName, Context appContext)
    {
        int reply = 0;

        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        StatusBarNotification[] activeNotifications = new StatusBarNotification[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            activeNotifications = notificationManager.getActiveNotifications();
        }

        for (StatusBarNotification notification : activeNotifications)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notification.isGroup() && notification.getGroupKey().contains(groupName) && !notification.getNotification().getShortcutId().equals("summary_" + GROUP_NAME))
                    reply++;
            }
        }

        return reply;
    }


    private int getIdSummaryNotification(String groupName, Context appContext)
    {
        int reply = 0;

        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        StatusBarNotification[] activeNotifications = new StatusBarNotification[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            activeNotifications = notificationManager.getActiveNotifications();
        }

        for (StatusBarNotification notification : activeNotifications)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notification.getNotification().getShortcutId().equals("summary_" + GROUP_NAME))
                {
                    reply = notification.getId();
                    break;
                }
            }
        }

        if (reply == 0)
            reply = getNextNotificationID();

        return reply;
    }
}
