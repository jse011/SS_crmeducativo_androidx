package com.consultoraestrategia.ss_crmeducativo.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos;
import com.consultoraestrategia.ss_crmeducativo.entities.Directivos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Message;
import com.consultoraestrategia.ss_crmeducativo.entities.Message_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AppMessengetNotification extends Worker {

    public static void start(String id) {
        Log.d("FirebaseMessagingSere", "Start");
        Data data = new Data.Builder()
                .putString("messageId", id)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(AppMessengetNotification.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }
    public AppMessengetNotification(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {

        try {
            String messageId = getInputData().getString("messageId");

            SessionUser sessionUser = SessionUser.getCurrentUser();
            int personaid = sessionUser.getPersonaId();

            List<Message> chatMessageList = SQLite.select()
                    .from(Message.class)
                    .where(Message_Table.estado.eq(0))
                    .and(Message_Table.personaId.notEq(personaid))
                    .and(Message_Table.chatId.isNotNull())
                    .and(Message_Table.chatId.notEq(""))
                    .groupBy(Message_Table.chatId)
                    .orderBy(Message_Table.lastdate.desc())
                    .queryList();

            List<Chat> chatList = new ArrayList<>();
            for (Message chatMesage : chatMessageList){

                List<Message> messageList = SQLite.select()
                        .from(Message.class)
                        .where(Message_Table.estado.eq(0))
                        .and(Message_Table.personaId.notEq(personaid))
                        .and(Message_Table.chatId.eq(chatMesage.getChatId()))
                        .orderBy(Message_Table.lastdate.desc())
                        .limit(6)
                        .queryList();

                List<Integrante> integranteList = new ArrayList<>();

                for (Message message : messageList){

                    Log.d(this.getClass().getSimpleName(),message.toString());
                    String nombre = "Desconocido";
                    String foto = null;
                    Persona persona = SQLite.select()
                            .from(Persona.class)
                            .where(Persona_Table.personaId.eq(message.getPersonaId()))
                            .querySingle();
                    if (persona != null) {
                        nombre = Utils.capitalize(persona.getFirstName()) + " " + Utils.capitalize(persona.getApellidoPaterno());
                        foto = persona.getFoto();
                    } else {
                        Directivos directivos = SQLite.select()
                                .from(Directivos.class)
                                .where(Directivos_Table.personaId.eq(message.getPersonaId()))
                                .querySingle();
                        if (directivos != null) {
                            nombre = Utils.capitalize(Utils.getFirstWord(directivos.getNombre())) + " " + Utils.capitalize(directivos.getApellidoPaterno());
                            foto = directivos.getFoto();
                        }
                    }

                    if(TextUtils.isEmpty(chatMesage.getNombreChat()))chatMesage.setNombreChat(nombre);

                    integranteList.add(new Integrante(String.valueOf(message.getPersonaId()),nombre, message.getLastdate()!=null?message.getLastdate().getTime(): new Date().getTime() , message.getMensaje(),foto, message.getId()));
                }

                chatList.add(new Chat(chatMesage.getChatId(),chatMesage.getNombreChat(), integranteList));
            }


            for (Chat chat : chatList){
                notificationWithGroupConvo(messageId,chat);
            }
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure();
        }
    }

    private class Chat{
        private String key;
        private String nombre;
        private List<Integrante> integrantes;

        public Chat(String key, String nombre, List<Integrante> integrantes) {
            this.key = key;
            this.nombre = nombre;
            this.integrantes = integrantes;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public List<Integrante> getIntegrantes() {
            return integrantes;
        }

        public void setIntegrantes(List<Integrante> integrantes) {
            this.integrantes = integrantes;
        }
    }

    public class Integrante{
        private String nombre;
        private long time;
        private String mensaje;
        private String foto;
        private Person person;
        private String id;
        private String mensajeId;

        public Integrante(String id, String nombre, long time, String mensaje, String foto,String mensajeId) {
            this.nombre = nombre;
            this.time = time;
            this.mensaje = mensaje;
            this.foto = foto;
            this.id = id;
            this.mensajeId = mensajeId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMensajeId() {
            return mensajeId;
        }

        public void setMensajeId(String mensajeId) {
            this.mensajeId = mensajeId;
        }

        @Override
        public String toString() {
            return "Integrante{" +
                    "nombre='" + nombre + '\'' +
                    ", time=" + time +
                    ", mensaje='" + mensaje + '\'' +
                    ", foto='" + foto + '\'' +
                    ", person=" + person +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    private void notificationWithGroupConvo(String messageId, Chat chat)
    {

        String channelId = getApplicationContext().getString(R.string.default_notification_channel_id);


        List<Integrante> integranteList = new ArrayList<>(chat.getIntegrantes());
        Collections.reverse(integranteList);
        for (Integrante integrante : integranteList){
            Person.Builder personBuilder = new Person.Builder()
                    .setName(integrante.getNombre());

            if(!TextUtils.isEmpty(integrante.getFoto())){
                try {
                    URL appImgUrlLink = new URL(integrante.getFoto());
                    personBuilder.setIcon(IconCompat.createWithBitmap(BitmapFactory.decodeStream(appImgUrlLink.openConnection().getInputStream())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            integrante.setPerson(personBuilder
                    .setKey(integrante.getId())
                    .setImportant(true)
                    .build());
        }

        /*Uri uri = Uri.parse("https://www.nationalgeographic.com.es/medio/2019/03/08/elizabeth-scarrott-estados-unidos-iphone-8-plus_84d101bd_816x1088.jpg");
        NotificationCompat.MessagingStyle.Message message = new NotificationCompat.MessagingStyle.Message("", new Date().getTime(), bot);
        message.setData("image/*",uri);*/

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId).setSmallIcon(R.drawable.ic_messenger);
        builder.setAutoCancel(true);

        NotificationCompat.MessagingStyle notificationCompat = new NotificationCompat.MessagingStyle(chat.getIntegrantes().get(0).getPerson());

        boolean notify = false;
        for (Integrante integrante : integranteList){
            Log.d(this.getClass().getSimpleName(),integrante.toString());
            notificationCompat.addMessage(integrante.getMensaje(), integrante.getTime(), integrante.getPerson());
            if(messageId.equals(integrante.getMensajeId()))notify=true;
        }

        NotificationManager notificationManager;
        if(notify){
            Log.d(this.getClass().getSimpleName(),"PRIORITY_HIGH " + chat.getNombre());
            builder.setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE);

            CharSequence name = "Canal messenger";
            String Description = "Canal principal";
             notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(channelId, name, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);
                notificationManager.createNotificationChannel(mChannel);
            }

        }else {
            CharSequence name = "Canal messenger 2";
            String Description = "Canal secundario";
             notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            Log.d(this.getClass().getSimpleName(),"PRIORITY_LOW " + chat.getNombre());
            builder.setPriority(NotificationCompat.PRIORITY_LOW);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String CHANNEL_ID = "my_channel_01";
                int importance = NotificationManager.IMPORTANCE_NONE;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(Description);
                notificationManager.createNotificationChannel(mChannel);
            }

        }

        notificationCompat
                .setGroupConversation(true)
                .setConversationTitle(chat.nombre)
                .setBuilder(builder);

        BigInteger bigInt = new BigInteger(chat.getKey().getBytes());
        System.out.println(bigInt.toString());

        notificationManager.notify(bigInt.intValue(), builder.build());

      //  notificationWithGroupConvo(builder, notificationManager);
    }


}
