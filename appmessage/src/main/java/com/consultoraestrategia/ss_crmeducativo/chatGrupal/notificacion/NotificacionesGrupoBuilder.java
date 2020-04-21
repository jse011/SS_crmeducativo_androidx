package com.consultoraestrategia.ss_crmeducativo.chatGrupal.notificacion;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.ChatGrupalActivity;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
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

public class NotificacionesGrupoBuilder {
    private static final String GROUP_KEY = "Messenger";
    private static final String NOTIFICATION_ID = "com.stylingandroid.nougat.NOTIFICATION_ID";
    private static final int SUMMARY_ID = 0;

    private final Context context;
    private final NotificationManager notificationManager;
    private final SharedPreferences sharedPreferences;
    private final String channelId;
    private String TAG = NotificacionesGrupoBuilder.class.getSimpleName();
    private HashMap<Integer,MessageBuilder> messageUi2List = new HashMap<>();

    public static NotificacionesGrupoBuilder newInstance(Context context) {
        Context appContext = context.getApplicationContext();
        Context safeContext = ContextCompat.createDeviceProtectedStorageContext(appContext);
        if (safeContext == null) {
            safeContext = appContext;
        }
        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(safeContext);
        return new NotificacionesGrupoBuilder(safeContext, notificationManager, sharedPreferences);
    }

    private NotificacionesGrupoBuilder(Context context,
                                       NotificationManager notificationManager,
                                SharedPreferences sharedPreferences) {
        this.context = context.getApplicationContext();
        this.notificationManager = notificationManager;
        this.sharedPreferences = sharedPreferences;
        channelId = context.getString(R.string.default_notification_channel_id);
    }

    public void sendBundledNotification(Map<String, String> data) {

        Gson gson = new Gson();
        MessageUi2 messageUi2 = gson.fromJson(data.get("messageGrupo"), MessageUi2.class);
        Log.d(TAG, "Sala tipo: " + messageUi2.getSalaTipo());

        SessionUser sessionUser = SessionUser.getCurrentUser();
        int personaid = sessionUser.getPersonaId();

        if(personaid==messageUi2.getEmisorId()||messageUi2.getEmisorId()==0){
            return;
        }

        if(!messageUi2.getSalaTipo().equals(TipoSalaEnum.SALON_GENERAL.getNombre())&&
                !messageUi2.getSalaTipo().equals(TipoSalaEnum.CURSO_GENERAL.getNombre())&&
                !messageUi2.getSalaTipo().equals(TipoSalaEnum.LISTA_GENERAL.getNombre())){
            return;
        }

        if(messageUi2.getSalaTipo().equals(TipoSalaEnum.LISTA_GENERAL.getNombre())){
            GrupoEquipoC grupoEquipoC = SQLite.select()
                    .from(GrupoEquipoC.class)
                    .where(GrupoEquipoC_Table.key.eq(messageUi2.getGrupoEquipoId()))
                    .and(GrupoEquipoC_Table.estado.notEq(GrupoEquipoC.ELIMINADO))
                    .querySingle();

            if(grupoEquipoC==null)return;
        }

        Message message = SQLite.select()
                .from(Message.class)
                .where(Message_Table.id.eq("Notify_"+messageUi2.getId()))
                .querySingle();

        String salaId = ChatGrupalActivity.salaId;

        if(message==null && !messageUi2.getSalaId().equals(TextUtils.isEmpty(salaId)?"":salaId)) {
            message = new Message();
            message.setId("Notify_"+messageUi2.getId());
            message.setMensaje(messageUi2.getMensaje());
            message.setCargaAcademicaId(messageUi2.getCargaAcademicaId());
            message.setCargaCursoId(messageUi2.getCargaCursoId());
            //message.setEstado(messageUi2.getEstado());
            message.setChatId(messageUi2.getSalaId());
            message.setPersonaId(messageUi2.getEmisorId());
            message.setLastdate(new Date(messageUi2.getDataTime()));
            message.setNombreChat(messageUi2.getNombreGrupo());
            message.save();

            AppMessengetNotification.start(message.getId());

            /*String nombre = "Desconocido";
            String foto = null;
            Persona persona = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.eq(messageUi2.getEmisorId()))
                    .querySingle();
            if (persona != null) {
                nombre = Utils.capitalize(persona.getFirstName()) + " " + Utils.capitalize(persona.getApellidoPaterno());
                foto = persona.getFoto();
            } else {
                Directivos directivos = SQLite.select()
                        .from(Directivos.class)
                        .where(Directivos_Table.personaId.eq(messageUi2.getEmisorId()))
                        .querySingle();
                if (directivos != null) {
                    nombre = Utils.capitalize(Utils.getFirstWord(directivos.getNombre())) + " " + Utils.capitalize(directivos.getApellidoPaterno());
                    foto = directivos.getFoto();
                }
            }

            MessageBuilder messageBuilder = new MessageBuilder();
            messageBuilder.setSender(nombre);
            messageBuilder.setMessage(messageUi2.getMensaje());
            messageBuilder.setTimestamp(messageUi2.getDataTime());
            messageBuilder.setImage(foto);
            messageBuilder.setGroup(messageUi2.getNombreGrupo());
            messageBuilder.setGroupId(messageUi2.getGrupoEquipoId());
            Notification notification = buildNotification(messageBuilder, GROUP_KEY);
            Notification summary = buildSummary(messageBuilder, GROUP_KEY);

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }

            notificationManager.notify(getNotificationId(), notification);
            notificationManager.notify(SUMMARY_ID, summary);*/
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
                .setSubText("\uD83D\uDC65 "+message.getGroup())
                .setWhen(message.getTimestamp())
                .setSmallIcon(R.drawable.ic_messenger)
                .setStyle(style)
                .setGroup(message.getGroupId())
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
        private String group;
        private String groupId;

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

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupId() {
            return groupId;
        }
    }
}
