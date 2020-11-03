package com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.remote;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.entities.Message;
import com.consultoraestrategia.ss_crmeducativo.entities.Message_Table;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.ReferenceFirestore;
import com.consultoraestrategia.ss_crmeducativo.utils.UtilsAppMessenger;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChatRemoteDataSource implements ChatDataSource {
    private static final int GUARDADO_LOCAL = 4,GUARDADO = 1,ENVIADO = 2, VISTO = 3, ELIMINADO = 0;
    private static final String IMAGEN = "img", TEXTO = "text", STICKER = "sticker";

    //private CollectionReference collectionReferenceMessage= ReferenceFirestore.getMessage();
    private CollectionReference collectionReferenceChat= ReferenceFirestore.getInstanceChat();
//    private CollectionReference collectionReferencePerson= ReferenceFirestore.getPersons();


    private final static String TAG = "ChatRemoteDataSourceTAG";
    private int LIMIT_LIST = 50;

    @Override
    public void changeEstadoEliminado(List<MessageUi2> messageUi2List) {
        WriteBatch batch = FirebaseFirestore.getInstance().batch();

        for (MessageUi2 messageUi2 : messageUi2List){
            batch.update(collectionReferenceChat.document(messageUi2.getSalaId()).collection("message").document(messageUi2.getId()), "state", ELIMINADO);
        }

        // Commit the batch
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    @Override
    public PersonaUi getPersona(int personaId) {
        return null;
    }


    private Map<String, Object> getMessage(MessageUi2 messageUi, String tipo){
        Map<String, Object> message = new HashMap<>();
        message.put("date", messageUi.getFecha());
        message.put("idsender",messageUi.getEmisorId());
        message.put("idreceiver",0);
        message.put("idclassroom", messageUi.getSalaId());
        message.put("typeclassroom", messageUi.getSalaTipo());
        message.put("cargaCursoId", messageUi.getCargaCursoId());
        message.put("cargaAcademicaId", messageUi.getCargaAcademicaId());
        message.put("grupoEquipoId", messageUi.getGrupoEquipoId());
        message.put("docenteId", messageUi.getDocenteId());
        message.put("message",messageUi.getMensaje());
        message.put("reference",messageUi.getReferencia());
        message.put("idchat",messageUi.getSalaId());//No es nesacario
        message.put("view",false);
        message.put("typePerson",0);
        message.put("state", GUARDADO_LOCAL);
        message.put("imageFcm", messageUi.getImagenFcm());
        message.put("imageType", tipo);
        message.put("messageReplik",messageUi.getMensajeReplick());
        message.put("messageIdReplik",messageUi.getMensajeReplickId());
        message.put("messageImagenReplik",messageUi.getImagenReplick());
        message.put("messageEmisorReplik",messageUi.getPersonaReplick());
        message.put("messageEmisorIdReplik",messageUi.getPersonaIdReplick());

        return message;
    }

    private Map<String, Object> getChat(MessageUi2 messageUi){
        Map<String, Object> chat = new HashMap<>();
        chat.put("idsender", messageUi.getEmisorId());
        chat.put("idreceiver", 0);
        chat.put("idclassroom", messageUi.getSalaId());
        chat.put("typeclassroom", messageUi.getSalaTipo());
        chat.put("cargaCursoId", messageUi.getCargaCursoId());
        chat.put("cargaAcademicaId", messageUi.getCargaAcademicaId());
        chat.put("grupoEquipoId", messageUi.getGrupoEquipoId());
        chat.put("docenteId", messageUi.getDocenteId());
        chat.put("reference", messageUi.getReferencia());
        chat.put("lastmessage", messageUi.getMensaje());
        chat.put("state", GUARDADO);
        chat.put("lastdate", messageUi.getFecha());
        chat.put("typeChatGroup",false );
        chat.put("idmessage", messageUi.getId());
        return chat;
    }
    @Override
    public void saveMensaje(final MessageUi2 messageUi, final MessageCallback messageCallback) {

        collectionReferenceChat.document(messageUi.getSalaId()).collection("message").add(getMessage(messageUi, (messageUi.getTipo()!= MessageUi2.TIPO.STICKER)?TEXTO:STICKER)).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "create chat success  "+ task.isSuccessful());
                if(task.isSuccessful()){
                    messageUi.setId(task.getResult()!=null?task.getResult().getId():"0");
                    Map<String, Object> chat =  getChat(messageUi);
                    collectionReferenceChat.document(messageUi.getSalaId()).set(chat);

                    messageCallback.onLoad(true, messageUi);
                }else {
                    messageCallback.onLoad(false, messageUi);
                }
            }

        });
    }

    @Override
    public ListenerFirebase getListaMessage(final String salaId, final int personaId, final ListaMessageCallback callback) {

        final List<ListenerFirebase> listenerFirebaseList = new ArrayList<>();
        Query query  = collectionReferenceChat.document(salaId).collection("message")
                //.whereEqualTo("reference", salaId)
                //.whereEqualTo("idchat", emisor+"_"+reseptor)
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(LIMIT_LIST);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    if(queryDocumentSnapshots!=null){
                        List<MessageUi2> messageUiList = getListaMessage(personaId, queryDocumentSnapshots,callback);
                        Collections.reverse(messageUiList);

                        Set<Object> objectList = new LinkedHashSet<>();
                        for(MessageUi2 messageUi: messageUiList)
                        {
                            objectList.add(Utils.getDateChat(messageUi.getFecha()));
                            objectList.add(messageUi);
                        }
                        callback.onLoad(true, "",new ArrayList<Object>(objectList));
                    }else {
                        callback.onLoad(false, null,null);
                    }
                }else {
                    callback.onLoad(false, null,null);
                }

                Query queryChatReciver = collectionReferenceChat.document(salaId).collection("message")
                        //.whereArrayContains("reference", salaId)
                        //.whereEqualTo("idreceiver", emisor)
                        .whereEqualTo("view", false)
                        .orderBy("date", Query.Direction.DESCENDING);

                Log.d(TAG, "MesListenerFirebaseImpl");
                listenerFirebaseList.add(new ListenerFirebaseImpl(queryChatReciver.addSnapshotListener(MetadataChanges.INCLUDE,new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            e.printStackTrace();
                            callback.onLoad(false, null, null);
                            return;
                        }
                        Log.d(TAG, "queryDocumentSnapshots");

                        if (queryDocumentSnapshots != null) {
                            String source2 = queryDocumentSnapshots.getMetadata().hasPendingWrites()
                                    ? "Local" : "Server";
                            Log.d(TAG, "source2 " + source2);
                            Log.d(TAG, "messageUi source2"+ source2);
                            List<MessageUi2> messageUiList = getListaMessage(personaId, queryDocumentSnapshots,callback);
                            Collections.reverse(messageUiList);
                            callback.onRecivedMessage(messageUiList);

                        }

                    }
                })));
            }
        });

        return new ListenerFirebase() {
            @Override
            public void onStop() {
                for (ListenerFirebase listenerFirebase : listenerFirebaseList)listenerFirebase.onStop();
            }
        };
    }

    @Override
    public void getListlastMessage(String salaId, final int personaId, Date date, final Callback<List<Object>> callback) {

        Query query  = collectionReferenceChat.document(salaId).collection("message")
                //.whereArrayContains("reference", salaId)
                //.whereEqualTo("idchat", emisor+"_"+reseptor)
                .whereLessThan("date", date)
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(LIMIT_LIST);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    if (queryDocumentSnapshots != null) {
                        List<MessageUi2> messageUiList = getListaMessage(personaId, queryDocumentSnapshots, null);
                        Collections.reverse(messageUiList);

                        Set<Object> objectList = new LinkedHashSet<>();
                        for (MessageUi2 messageUi : messageUiList) {
                            objectList.add(Utils.getDateChat(messageUi.getFecha()));
                            objectList.add(messageUi);
                        }
                        callback.onLoad(true, new ArrayList<Object>(objectList));
                    } else {
                        callback.onLoad(false, null);
                    }

                } else {
                    callback.onLoad(false, null);
                }
            }
        });
    }


    private List<String> cache = new ArrayList<>();

    private List<MessageUi2> getListaMessage(int emisor, QuerySnapshot queryDocumentSnapshots, final ListaMessageCallback callback) {
        boolean changeVisto = false;
        // Get a new write batch
        WriteBatch batch = FirebaseFirestore.getInstance().batch();
        final HashMap<MessageUi2, MessageUi2.ESTADO> messageIntegerList = new HashMap<>();
        List<MessageUi2> messageUis= new ArrayList<>();
        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

            try {
                //   Log.d(TAG, " => " + doc.get("idchat") +  "  "+ doc.get("message"));
                final MessageUi2 messageUi= new MessageUi2();
                messageUi.setId(doc.getId());
                messageUi.setFecha(doc.getDate("date"));
                messageUi.setMensaje(TextUtils.isEmpty(doc.getString("message"))?"":doc.getString("message"));
                messageUi.setEmisorId(doc.getLong("idsender").intValue());
                messageUi.setSalaId(doc.getString("idclassroom"));
                messageUi.setSalaTipo(doc.getString("typeclassroom"));
                messageUi.setCargaCursoId(doc.getLong("cargaCursoId").intValue());
                messageUi.setCargaAcademicaId(doc.getLong("cargaAcademicaId").intValue());
                messageUi.setGrupoEquipoId(doc.getString("grupoEquipoId"));
                messageUi.setDocenteId((List<Long>) doc.get("docenteId"));
                messageUi.setReferencia((List<String>) doc.get("reference"));
                int state = doc.getLong("state")!=null?doc.getLong("state").intValue():0;
                messageUi.setPendingWrites(queryDocumentSnapshots.getMetadata().hasPendingWrites());
                messageUi.setView(doc.getBoolean("view"));
                messageUi.setImagenFcm(doc.getString("imageFcm"));
                String tipo = TextUtils.isEmpty(doc.getString("imageType"))?TEXTO:doc.getString("imageType");

                if(TEXTO.equals(tipo)){
                    messageUi.setTipo(MessageUi2.TIPO.TEXTO);
                }else if(IMAGEN.equals(tipo)){
                    messageUi.setTipo(MessageUi2.TIPO.IMAGEN);
                }else if(STICKER.equals(tipo)){
                    messageUi.setTipo(MessageUi2.TIPO.STICKER);
                }

                messageUi.setMensajeReplick(doc.getString("messageReplik"));
                messageUi.setMensajeReplickId(doc.getString("messageIdReplik"));
                messageUi.setImagenReplick(doc.getString("messageImagenReplik"));
                messageUi.setPersonaReplick(doc.getString("messageEmisorReplik"));
                messageUi.setPersonaIdReplick(UtilsFirebase.convert(doc.get("messageEmisorIdReplik"),0));

                switch (state){
                    case GUARDADO_LOCAL:
                        messageUi.setEstado(MessageUi2.ESTADO.CREADO);
                        if(!messageUi.getPendingWrites()){
                            if(emisor == messageUi.getEmisorId()){
                                String estadoCache = doc.getId()+"_state"+GUARDADO;
                                int position = cache.indexOf(estadoCache);
                                if(position==-1){
                                    cache.add(estadoCache);
                                    // Update the population of 'SF'
                                    batch.update(collectionReferenceChat.document(messageUi.getSalaId()).collection("message").document(doc.getId()), "state", GUARDADO);
                                    messageIntegerList.put(messageUi, MessageUi2.ESTADO.GUARDADO);
                                    messageUi.setEnviarNotificacion(true);

                                }

                            }

                        }

                        break;
                    case GUARDADO:

                        messageUi.setEstado(MessageUi2.ESTADO.GUARDADO);
                        break;
                    case ENVIADO:
                        messageUi.setEstado(MessageUi2.ESTADO.ENVIADO);
                        break;
                    case VISTO:
                        if(!messageUi.getPendingWrites()&&!messageUi.getView()){
                            if (emisor == messageUi.getEmisorId()) {
                                String estadoCache = doc.getId()+"_viewtrue";
                                int position = cache.indexOf(estadoCache);
                                if(position==-1) {
                                    batch.update(collectionReferenceChat.document(messageUi.getSalaId()).collection("message").document(doc.getId()), "view", true);
                                }
                                messageUi.setEstado(MessageUi2.ESTADO.VISTO);
                            }
                        }

                        break;
                    default:
                        messageUi.setEstado(MessageUi2.ESTADO.ELIMINADO);
                        break;
                }

                messageUis.add(messageUi);

            }catch (Exception ex){
                ex.printStackTrace();
            }


        }

        if(!messageUis.isEmpty()){
            MessageUi2 messageUi2 = messageUis.get(0);
            SQLite.update(Message.class)
                    .set(Message_Table.estado.eq(1))
                    .where(Message_Table.chatId.eq(messageUi2.getSalaId()))
                    .execute();
        }

        String source = queryDocumentSnapshots.getMetadata().isFromCache() ?
                "local cache" : "server";
        Log.d(TAG, "Data fetched from " + source);
        if(!messageIntegerList.isEmpty()){
            // Commit the batch
            batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    for (Map.Entry<MessageUi2, MessageUi2.ESTADO> entry : messageIntegerList.entrySet()){
                        entry.getKey().setEstado(entry.getValue());
                    }
                    if(callback!=null)callback.onChangeEstado();
                }
            });
        }
        return messageUis;
    }

    @Override
    public void saveKeyPress(int person, int personaExterna) {
        List<String> reference = new ArrayList<>();
        reference.add(String.valueOf(person));
        reference.add(String.valueOf(personaExterna));
        reference.add(personaExterna+"_"+person);
        reference.add(person+"_"+personaExterna);

        Map<String, Object> chat = new HashMap<>();
        chat.put("idsender", person);
        chat.put("idreceiver", personaExterna);
        chat.put("reference", reference);
        chat.put("lastmessage", "Escribiendo...");
        chat.put("state", GUARDADO);
        chat.put("lastdate", new Date());
        chat.put("typeChatGroup",false );

        String id = "";
        if(person>personaExterna){
            id = person+"_"+personaExterna;
        }else {
            id = personaExterna+"_"+person;
        }

        //collectionReferenceChat.document(id).collection("keyboard").set();
    }

    @Override
    public ListenerFirebaseImpl getKeyPressEmisor(final int personaId, final int personaExternaId, final CallbackKeyPressEmisor callback) {

        String id = "";
        if(personaId>personaExternaId){
            id = personaId+"_"+personaExternaId;
        }else {
            id = personaExternaId+"_"+personaId;
        }

        List<String> reference = new ArrayList<>();
        reference.add(String.valueOf(personaId));
        reference.add(String.valueOf(personaExternaId));
        reference.add(personaExternaId+"_"+personaId);
        reference.add(personaId+"_"+personaExternaId);

        Map<String, Object> emisor = new HashMap<>();
        emisor.put("date", new Date());

        collectionReferenceChat.document(id).collection("keyboard").document(String.valueOf(19)).set(emisor);

        Log.d(TAG, "MesListenerFirebaseImpl");
        return new ListenerFirebaseImpl(collectionReferenceChat.document(id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    e.printStackTrace();
                    callback.onKeyPress(false);
                    return;
                }
                Log.d(TAG, "queryDocumentSnapshots");

                if (documentSnapshot != null) {
                    String source2 = documentSnapshot.getMetadata().hasPendingWrites()
                            ? "Local" : "Server";
                    Log.d(TAG, "source2 " + source2);
                    Log.d(TAG, "messageUi source2"+ source2);



                    if(!documentSnapshot.getMetadata().hasPendingWrites()){
                        try {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.MILLISECOND, 0);
                            calendar.set(Calendar.SECOND, 0);
                            Calendar calendarChat = Calendar.getInstance();
                            calendarChat.set(Calendar.MILLISECOND, 0);
                            calendarChat.set(Calendar.SECOND, 0);
                            calendarChat.setTime(documentSnapshot.getDate("lastdate"));
                            if(personaId!=documentSnapshot.getLong("idsender").intValue()
                                    &&calendar.compareTo(calendarChat)==0){
                                callback.onKeyPress(true);
                            }else {
                                callback.onKeyPress(false);
                            }

                        }catch (Exception e1){
                            e1.printStackTrace();
                        }


                    }

                }

            }
        }));
    }

    @Override
    public SalaUi getSala(int cargaAcademicaId, int cargaCursoId, String grupoEquipoId, TipoSalaEnum tipo) {
        return null;
    }

    @Override
    public PersonaUi getPersonaChatMessage(int emisorId) {
        return null;
    }

    @Override
    public RetrofitCancel sendNotificacion(final MessageUi2 messageUi2, List<String> tokens, final MessageCallback callback) {
        Gson gson = new Gson();

        JsonObject jsonData = new JsonObject();
        jsonData.add("messageGrupo", gson.toJsonTree(messageUi2));


        JsonObject jsonObject = new JsonObject();
        jsonObject.add("to", gson.toJsonTree("/topics/cargaAcademica_"+ messageUi2.getCargaAcademicaId()));
        jsonObject.add("data", jsonData);

        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("registration_ids", gson.toJsonTree(tokens));
        jsonObject2.add("data", jsonData);


        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(60,60,60, TimeUnit.SECONDS);


        RetrofitCancel<String> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_Notificacion(jsonObject2));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<String>() {
            @Override
            public void onResponse(String response) {
                if(response == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response usuario null");
                }else {

                    Log.d(TAG,"response usuario true");
                    callback.onLoad(true, messageUi2);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false,null);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_Notificacion(jsonObject));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<String>() {
            @Override
            public void onResponse(String response) {
                if(response == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response usuario null");
                }else {

                        Log.d(TAG,"response usuario true");
                        callback.onLoad(true, messageUi2);

                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false,null);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        return retrofitCancel;
    }

    @Override
    public void getDatosChat(MessageUi2 messageUi2) {

    }

    @Override
    public void getTokensSala(String salaId, final Callback<List<String>> callback) {
        collectionReferenceChat.document(salaId).collection("menbers")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            e.printStackTrace();
                            callback.onLoad(false, null);
                            return;
                        }
                        Log.d(TAG, "queryDocumentSnapshots");

                        if (queryDocumentSnapshots != null) {
                            String source2 = queryDocumentSnapshots.getMetadata().hasPendingWrites()
                                    ? "Local" : "Server";
                            Log.d(TAG, "source2 " + source2);
                            Log.d(TAG, "messageUi source2"+ source2);
                            List<String> tokensList = new ArrayList<>();
                            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                                tokensList.add(doc.getString("token"));
                            }
                            callback.onLoad(true, tokensList);
                        }else {
                            callback.onLoad(false, null);
                        }
                    }
                });

    }

    @Override
    public void updateTaken(final String salaId, final String personaId) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        InstanceIdResult instanceIdResult = task.getResult();
                        // Get new Instance ID token
                        String token = instanceIdResult!=null ? instanceIdResult.getToken():"";

                        Log.d(TAG,"token: " + token);
                        Map<String, Object> menber = new HashMap<>();
                        menber.put("idreceiver", personaId);
                        menber.put("token", token);
                        menber.put("date", new Date());

                        collectionReferenceChat.document(salaId).collection("menbers")
                                .document(personaId).set(menber);

                    }
                });
    }

    @Override
    public void getSalaIntegrantes(String salaId, Callback<List<PersonaUi>> listCallback) {
        collectionReferenceChat.document(salaId).collection("menbers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        if(queryDocumentSnapshots!=null){
                            List<PersonaUi> personaUiList = new ArrayList<>();
                            /*for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                                Map<String, Object> menber = new HashMap<>();
                                menber.put("idreceiver", emisor);
                                menber.put("state", VISTO);
                                menber.put("date", new Date());
                                PersonaUi personaUi = new PersonaUi();
                                personaUi.setId();
                            }*/
                        }
                    }
                });
    }

    @Override
    public void saveImageMensaje(List<MessageUi2> messageUiList, final MessageImageCallback messageImageCallback) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        for (final MessageUi2 item : messageUiList){

            File file = UtilsAppMessenger.compressImage2(item.getImagen());
            Uri myUri = Uri.fromFile(file);

            final StorageReference SavestorageReference = storageReference.child("AppMessenger").child("icrmedu_docente").child("Grupal").child(String.valueOf(item.getSalaId())).child(file.getName());
            SavestorageReference.putFile(myUri)
               .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //track the progress of = our upload
                            int currentProgress = (int) ((double) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount()));
                            Log.d("SaveImgen", "currentProgress: " + currentProgress);
                            messageImageCallback.onProgres(currentProgress);
                        }
                    }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {

                            }
                            return SavestorageReference.getDownloadUrl();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            item.setImagenFcm(uri.toString());

                            collectionReferenceChat.document(item.getSalaId()).collection("message").add(getMessage(item, IMAGEN)).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Log.d(TAG, "create chat success  "+ task.isSuccessful());
                                    if(task.isSuccessful()){
                                        item.setId(task.getResult()!=null?task.getResult().getId():"0");
                                        Map<String, Object> chat =  getChat(item);
                                        collectionReferenceChat.document(item.getSalaId()).set(chat);

                                        messageImageCallback.onLoad(true, item);
                                    }else {
                                        messageImageCallback.onLoad(false, item);
                                    }
                                }

                            });

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            messageImageCallback.onLoad(false, item);
                        }
                    });

        }

    }

}
