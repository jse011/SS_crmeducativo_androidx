package com.consultoraestrategia.ss_crmeducativo.chatJse.notificacion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chatJse.ChatActivity;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.entities.Message;
import com.consultoraestrategia.ss_crmeducativo.entities.Message_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.utils.AppMessengetNotification;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NotificacionesBuilder {
    private static final String GROUP_KEY = "Messenger";
    private static final String NOTIFICATION_ID = "com.stylingandroid.nougat.NOTIFICATION_ID";
    private static final int SUMMARY_ID = 0;

    private final Context context;
    private final NotificationManager notificationManager;
    private final SharedPreferences sharedPreferences;
    private final String channelId;
    private String TAG = NotificacionesBuilder.class.getSimpleName();
    private HashMap<Integer,MessageBuilder> messageUi2List = new HashMap<>();

    public static NotificacionesBuilder newInstance(Context context) {
        Context appContext = context.getApplicationContext();
        Context safeContext = ContextCompat.createDeviceProtectedStorageContext(appContext);
        if (safeContext == null) {
            safeContext = appContext;
        }
        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(safeContext);
        return new NotificacionesBuilder(safeContext, notificationManager, sharedPreferences);
    }

    private NotificacionesBuilder(Context context,
                                  NotificationManager notificationManager,
                                  SharedPreferences sharedPreferences) {
        this.context = context.getApplicationContext();
        this.notificationManager = notificationManager;
        this.sharedPreferences = sharedPreferences;
        channelId = context.getString(R.string.default_notification_channel_id);
    }

    public void sendBundledNotification(Map<String, String> data) {

        Gson gson = new Gson();
        MessageUi2 messageUi2 = gson.fromJson(data.get("message"), MessageUi2.class);

        SessionUser sessionUser = SessionUser.getCurrentUser();
        int personaid = sessionUser.getPersonaId();

        if(personaid==messageUi2.getEmisorId()||messageUi2.getEmisorId()==0){
            return;
        }


        Message message = SQLite.select()
                .from(Message.class)
                .where(Message_Table.id.eq("Notify_"+messageUi2.getId()))
                .querySingle();

        String salaId = ChatActivity.salaId;

        String messageSalaId = "";
        if(messageUi2.getEmisorId()>messageUi2.getReceptorId()){
            messageSalaId = messageUi2.getEmisorId()+"_"+messageUi2.getReceptorId();
        }else {
            messageSalaId = messageUi2.getReceptorId()+"_"+messageUi2.getEmisorId();
        }
        Log.d("FirebaseMessagingSere", "salaId "  + salaId);
        if(message==null && !messageSalaId.equals(TextUtils.isEmpty(salaId)?"":salaId)) {
            message = new Message();
            message.setId("Notify_"+messageUi2.getId());
            message.setMensaje(messageUi2.getMensaje());
            //message.setEstado(messageUi2.getEstado());
            message.setChatId(messageSalaId);
            message.setPersonaId(messageUi2.getEmisorId());
            message.setLastdate(new Date(messageUi2.getDataTime()));
            message.save();
            Log.d("FirebaseMessagingSere", "message "  + message.getId());
            AppMessengetNotification.start(message.getId());
        }
    }

    private Notification buildNotification(MessageBuilder message, String groupKey) {
        Person.Builder builder = new Person.Builder();

        if(message.getImage()!=null && !message.getImage().isEmpty()) {
            try {
                URL appImgUrlLink = new URL(message.getImage());
                builder.setIcon(IconCompat.createWithBitmap(BitmapFactory.decodeStream(appImgUrlLink.openConnection().getInputStream())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Person user = builder.setName(message.getSender()).build();
        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle(user)
                .addMessage(message.getMessage(), message.getTimestamp(), user)
                //.addMessage(messages[2].getText(), messages[2].getTime(), messages[2].getPerson())
                .setGroupConversation(true);

        return new NotificationCompat.Builder(context, channelId)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setWhen(message.getTimestamp())
                .setSmallIcon(R.drawable.ic_messenger)
                .setStyle(style)
                .setShowWhen(true)
                .setGroup(groupKey)
                .build();
    }

    private Notification buildSummary(MessageBuilder message, String groupKey) {
        return new NotificationCompat.Builder(context, channelId)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setWhen(message.getTimestamp())
                .setSmallIcon(R.drawable.ic_messenger)
                .setShowWhen(true)
                .setGroup(groupKey)
                .setGroupSummary(true)
                .build();
    }

    private int getNotificationId() {
        int id = sharedPreferences.getInt(NOTIFICATION_ID, SUMMARY_ID) + 1;
        while (id == SUMMARY_ID) {
            id++;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(NOTIFICATION_ID, id);
        editor.apply();
        return id;
    }

    public class MessageBuilder{
        private String sender;
        private String message;
        private String image;
        private long timestamp;

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public CharSequence getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    private void simpleNotification( NotificationCompat.Builder builder) {
        Person jd = new Person.Builder()
                .setName("JournalDev")
                .setImportant(true)
                .build();

        new NotificationCompat.MessagingStyle(jd)
                .addMessage("Check me out", new Date().getTime(), jd)
                .setBuilder(builder);


        this.notificationManager.notify(1, builder.build());
    }

    private void notificationWithIcon(NotificationCompat.Builder builder) {
        Person anupam = new Person.Builder()
                .setName("Anupam")
                .setIcon(IconCompat.createWithResource(context, R.drawable.happy))
                .setImportant(true)
                .build();

        new NotificationCompat.MessagingStyle(anupam)
                .addMessage("Check out my latest article!", new Date().getTime(), anupam)
                .setBuilder(builder);


        notificationManager.notify(2, builder.build());
    }

    private void notificationWithImage(NotificationCompat.Builder builder) {
        Person bot = new Person.Builder()
                .setName("Bot")
                .setImportant(true)
                .setBot(true)
                .build();


        Uri uri = Uri.parse("https://www.nationalgeographic.com.es/medio/2019/03/08/elizabeth-scarrott-estados-unidos-iphone-8-plus_84d101bd_816x1088.jpg");

        NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message("Check out my latest article!", new Date().getTime(), bot);
        message.setData("image/*",uri);


        new NotificationCompat.MessagingStyle(bot)
                .addMessage(message)
                .setGroupConversation(true)
                .setBuilder(builder);


        notificationManager.notify(3, builder.build());
    }

    private void notificationWithGroupConvo(NotificationCompat.Builder builder)
    {

        Person jd = new Person.Builder()
                .setName("JournalDev")
                .build();

        Person anupam = new Person.Builder()
                .setName("Anupam")
                .setIcon(IconCompat.createWithResource(context, R.drawable.happy))
                .setImportant(true)
                .build();


        Person bot = new Person.Builder()
                .setName("Bot")
                .setBot(true)
                .build();


        Uri uri = Uri.parse("https://www.nationalgeographic.com.es/medio/2019/03/08/elizabeth-scarrott-estados-unidos-iphone-8-plus_84d101bd_816x1088.jpg");

        NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message("", new Date().getTime(), bot);
        message.setData("image/*",uri);



        new NotificationCompat.MessagingStyle(bot)
                .addMessage("Hi. How are you?", new Date().getTime(), anupam)
                .addMessage(message)
                .addMessage("Does this image look good?", new Date().getTime(), bot)
                .addMessage("Looks good!", new Date().getTime(), jd)
                .setGroupConversation(true)
                .setConversationTitle("Sample Conversation")
                .setBuilder(builder);


        notificationManager.notify(4, builder.build());

    }

    private void notificationSemantic(NotificationCompat.Builder builder)
    {

        Person jd = new Person.Builder()
                .setName("JournalDev")
                .build();

        Person anupam = new Person.Builder()
                .setName("Anupam")
                .setIcon(IconCompat.createWithResource(context, R.drawable.photo))
                .setImportant(true)
                .build();


        Person bot = new Person.Builder()
                .setName("Bot")
                .setBot(true)
                .build();


        Uri uri = Uri.parse("https://www.nationalgeographic.com.es/medio/2019/03/08/elizabeth-scarrott-estados-unidos-iphone-8-plus_84d101bd_816x1088.jpg");

        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("hi","Notifications were read");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);



        NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message("", new Date().getTime(), bot);
        message.setData("image/*",uri);

        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.happy,
                        "MARK READ",
                        pendingIntent)
                        .setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ)
                        .build();




        NotificationCompat.Builder separateBuilder = builder;
        separateBuilder.addAction(replyAction);

        new NotificationCompat.MessagingStyle(bot)
                .addMessage("Hi. How are you?", new Date().getTime(), anupam)
                .addMessage(message)
                .addMessage("Does this image look good?", new Date().getTime(), bot)
                .addMessage("Looks good!", new Date().getTime(), jd)
                .setGroupConversation(true)
                .setConversationTitle("Sample Conversation")
                .setBuilder(separateBuilder);


        notificationManager.notify(5, separateBuilder.build());

    }
}
