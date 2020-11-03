package com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.remote;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;
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
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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
    private static final int GUARDADO = 1,ENVIADO = 2, VISTO = 3, ELIMINADO = 0;
    private static final String IMAGEN = "img", TEXTO = "text", STICKER = "sticker";
    //private CollectionReference collectionReferenceMessage= ReferenceFirestore.getMessage();
    private CollectionReference collectionReferenceChat= ReferenceFirestore.getInstanceChat();
//    private CollectionReference collectionReferencePerson= ReferenceFirestore.getPersons();


    private final static String TAG = "ChatRemoteDataSourceTAG";
    private int LIMIT_LIST = 50;

    @Override
    public PersonaUi getPersona(int personaId) {
        return null;
    }

    @Override
    public void saveMensaje(final MessageUi2 messageUi, final MessageCallback messageCallback) {
        String salaId = "";
        if(messageUi.getEmisorId()>messageUi.getReceptorId()){
            salaId = messageUi.getEmisorId()+"_"+messageUi.getReceptorId();
        }else {
            salaId = messageUi.getReceptorId()+"_"+messageUi.getEmisorId();
        }


        Map<String, Object> message = getMessage(messageUi, (messageUi.getTipo()!= MessageUi2.TIPO.STICKER)?TEXTO:STICKER);

        final String finalSalaId = salaId;

        collectionReferenceChat.document(salaId).collection("message").add(message)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Log.d(TAG, "create chat success  "+ task.isSuccessful());
                if(task.isSuccessful()){
                    messageUi.setId(task.getResult()!=null?task.getResult().getId():"0");
                    Map<String, Object> chat = getChat(messageUi);
                    collectionReferenceChat.document(finalSalaId).set(chat);

                    messageCallback.onLoad(true, messageUi);
                }else {
                    messageCallback.onLoad(false, messageUi);
                }
            }
        });


    }

    private Map<String, Object> getMessage(MessageUi2 messageUi, String tipo){
        Map<String, Object> message = new HashMap<>();
        message.put("date", FieldValue.serverTimestamp());
        message.put("idsender",messageUi.getEmisorId());
        message.put("idreceiver",messageUi.getReceptorId());
        message.put("message",messageUi.getMensaje());
        message.put("reference",messageUi.getReferencia());
        message.put("idchat",messageUi.getEmisorId()+"_"+messageUi.getReceptorId());//No es nesacario
        message.put("view",false);
        message.put("typePerson",0);
        message.put("state", GUARDADO);
        message.put("tokenFcm", messageUi.getTokenFcm());
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
        chat.put("idreceiver", messageUi.getReceptorId());
        chat.put("reference", messageUi.getReferencia());
        chat.put("lastmessage", messageUi.getMensaje());
        chat.put("nameReceiver", messageUi.getNameChat());
        chat.put("imageReceiver", messageUi.getImagenChat());
        chat.put("state", GUARDADO);
        chat.put("lastdate",  FieldValue.serverTimestamp());
        chat.put("typeChatGroup",false );
        chat.put("idmessage", messageUi.getId());
        return chat;
    }

   List<ListenerFirebase> listenerFirebaseList = new ArrayList<>();
    private void realtimeMessage(final String salaId , final int emisor, List<MessageUi2> messageUiList, final ListaMessageCallback messageCallback){
        Query queryChatReciver = null;

        if(!messageUiList.isEmpty()){
            queryChatReciver = collectionReferenceChat.document(salaId).collection("message")

                    .whereGreaterThanOrEqualTo("date", messageUiList.get(0).getFecha())
                    //.whereArrayContains("reference", emisor+"_"+reseptor)
                    //.whereEqualTo("idreceiver", emisor)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .limit(2000);
        }else {
            queryChatReciver = collectionReferenceChat.document(salaId).collection("message")
                    //.whereGreaterThan("date", messageUiList.get(0).getFecha().getTime())
                    //.whereArrayContains("reference", emisor+"_"+reseptor)
                    //.whereEqualTo("idreceiver", emisor)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .limit(2000);
        }

        Log.d(TAG, "MesListenerFirebaseImpl");
        listenerFirebaseList.add(new ListenerFirebaseImpl(queryChatReciver.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                Log.d(TAG, "queryDocumentSnapshots");

                if (queryDocumentSnapshots != null) {
                    String source2 = queryDocumentSnapshots.getMetadata().hasPendingWrites()
                            ? "Local" : "Server";
                    Log.d(TAG, "source2 " + source2);
                    Log.d(TAG, "messageUi source2" + source2);
                    List<MessageUi2> messageUiList = getListaMessage(salaId, emisor, queryDocumentSnapshots);
                    Collections.reverse(messageUiList);
                    messageCallback.onRecivedMessage(messageUiList);
                }

            }
        })));
    }
    @Override
    public ListenerFirebase getListaMessage(final int emisor, final int reseptor, final ListaMessageCallback messageCallback) {
        String salaId = "";
        if(emisor>reseptor){
            salaId = emisor+"_"+reseptor;
        }else {
            salaId = reseptor+"_"+emisor;
        }

        Query query  =  collectionReferenceChat.document(salaId).collection("message")
                //.whereArrayContains("reference", emisor+"_"+reseptor)
                //.whereEqualTo("idchat", emisor+"_"+reseptor)
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(LIMIT_LIST);

        final String finalSalaId = salaId;
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<MessageUi2> messageUiList = new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    if (queryDocumentSnapshots != null) {
                        messageUiList.addAll(getListaMessage(finalSalaId, emisor, queryDocumentSnapshots));
                        Collections.reverse(messageUiList);
                        Log.d(TAG, "messageUiList: " + messageUiList.size());
                        Set<Object> objectList = new LinkedHashSet<>();
                        for (MessageUi2 messageUi : messageUiList) {
                            objectList.add(Utils.getDateChat(messageUi.getFecha()));
                            objectList.add(messageUi);
                        }
                        messageCallback.onLoad(true, "", new ArrayList<Object>(objectList));
                    } else {
                        messageCallback.onLoad(false, null, null);
                    }

                } else {
                    messageCallback.onLoad(false, null, null);
                }
                realtimeMessage(finalSalaId, emisor,messageUiList, messageCallback);
            }
        });

        return new ListenerFirebase() {
            @Override
            public void onStop() {
                for (ListenerFirebase firebaseFirestore : listenerFirebaseList)firebaseFirestore.onStop();
                listenerFirebaseList.clear();
            }
        };

    }

    private List<String> cache = new ArrayList<>();

    private List<MessageUi2> getListaMessage(String salaId, int emisor, QuerySnapshot queryDocumentSnapshots) {
        boolean changeVisto = false;

        // Get a new write batch
        final WriteBatch batch = FirebaseFirestore.getInstance().batch();
        final HashMap<MessageUi2,MessageUi2.ESTADO> messageIntegerList = new HashMap<>();
        List<MessageUi2> messageUis= new ArrayList<>();
        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

            try {
                Date date = doc.getDate("date");
                Object o = doc.get("date");
                //   Log.d(TAG, " => " + doc.get("idchat") +  "  "+ doc.get("message"));
                final MessageUi2 messageUi= new MessageUi2();
                messageUi.setId(doc.getId());
                messageUi.setFecha(doc.getDate("date"));
                messageUi.setMensaje(TextUtils.isEmpty(doc.getString("message"))?"":doc.getString("message"));
                messageUi.setEmisorId(doc.getLong("idsender").intValue());
                messageUi.setReceptorId(doc.getLong("idreceiver").intValue());
                messageUi.setReferencia((List<String>) doc.get("reference"));
                int state = doc.getLong("state")!=null?doc.getLong("state").intValue():0;
                messageUi.setPendingWrites(queryDocumentSnapshots.getMetadata().hasPendingWrites());
                messageUi.setView(doc.getBoolean("view"));
                messageUi.setTokenFcm(doc.getString("tokenFcm"));
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
                    /*case GUARDADO_LOCAL:
                        messageUi.setEstado(MessageUi2.ESTADO.CREADO);
                        if(!messageUi.getPendingWrites()){
                            if(emisor == messageUi.getEmisorId()){
                                // Update the population of 'SF'
                                String estadoCache = doc.getId()+"_state"+GUARDADO;
                                int position = cache.indexOf(estadoCache);
                                if(position==-1){
                                    cache.add(estadoCache);
                                    batch.update(collectionReferenceChat.document(salaId).collection("message").document(doc.getId()), "state", GUARDADO);
                                    Log.d(TAG, "message 1");
                                    messageUi.setEnviarNotificacion(true);
                                    messageIntegerList.put(messageUi,MessageUi2.ESTADO.GUARDADO);

                                    messageUi.setEstado(MessageUi2.ESTADO.GUARDADO);
                                }
                            }
                        }

                        if(!messageUi.getPendingWrites()){
                            if(emisor == messageUi.getReceptorId()){
                                String estadoCache = doc.getId()+"_state"+VISTO;
                                int position = cache.indexOf(estadoCache);
                                if(position==-1){
                                    cache.add(estadoCache);
                                    batch.update(collectionReferenceChat.document(salaId).collection("message").document(doc.getId()), "state", VISTO);
                                    changeVisto = true;
                                    Log.d(TAG, "message 2");
                                    messageIntegerList.put(messageUi,MessageUi2.ESTADO.VISTO);
                                }
                            }
                        }

                        break;*/
                    case GUARDADO:
                        if(!messageUi.getPendingWrites()){
                            if(emisor == messageUi.getReceptorId()){
                                String estadoCache = doc.getId()+"_state"+VISTO;
                                int position = cache.indexOf(estadoCache);
                                if(position==-1){
                                    cache.add(estadoCache);
                                    batch.update(collectionReferenceChat.document(salaId).collection("message").document(doc.getId()), "state", VISTO);
                                    changeVisto = true;
                                    Log.d(TAG, "message 3");
                                    messageIntegerList.put(messageUi,MessageUi2.ESTADO.VISTO);
                                }
                            }
                        }

                        messageUi.setEstado(MessageUi2.ESTADO.GUARDADO);
                        break;
                    case ENVIADO:
                        messageUi.setEstado(MessageUi2.ESTADO.ENVIADO);
                        break;
                    case VISTO:
                        if(!messageUi.getPendingWrites()){
                            if(emisor == messageUi.getEmisorId()&&!messageUi.getView()){
                                if(emisor == messageUi.getReceptorId()){
                                    String estadoCache = doc.getId()+"_viewtrue";
                                    int position = cache.indexOf(estadoCache);
                                    if(position==-1){
                                        cache.add(estadoCache);
                                        batch.update(collectionReferenceChat.document(salaId).collection("message").document(doc.getId()),"view",true);
                                        Log.d(TAG, "message 4");
                                    }
                                }

                            }
                        }
                        messageUi.setEstado(MessageUi2.ESTADO.VISTO);
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

        SQLite.update(Message.class)
                .set(Message_Table.estado.eq(1))
                .where(Message_Table.chatId.eq(salaId))
                .execute();

        String source = queryDocumentSnapshots.getMetadata().isFromCache() ?
                "local cache" : "server";
        Log.d(TAG, "Data fetched from " + source);

        if(changeVisto){
            batch.update(collectionReferenceChat.document(salaId),"state", VISTO);
            Log.d(TAG, "message 5");
        }

        if(!messageIntegerList.isEmpty()){
            batch.commit()
                    .addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d(TAG, "message Succes");

                }
            });

        }


        return messageUis;
    }

    @Override
    public ListenerFirebase getListlastMessage(final int emisor, final int reseptor, Date date, final ListaMessageCallback messageCallback) {
        String salaId = "";
        if(emisor>reseptor){
            salaId = emisor+"_"+reseptor;
        }else {
            salaId = reseptor+"_"+emisor;
        }

        Query query  = collectionReferenceChat.document(salaId).collection("message")
                //.whereArrayContains("reference", emisor+"_"+reseptor)
                //.whereEqualTo("idchat", emisor+"_"+reseptor)
                .whereLessThan("date", date)
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(LIMIT_LIST);

        final String finalSalaId = salaId;
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<MessageUi2> messageUiList = new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    if (queryDocumentSnapshots != null) {
                        messageUiList.addAll(getListaMessage(finalSalaId,emisor, queryDocumentSnapshots));
                            Collections.reverse(messageUiList);

                            Set<Object> objectList = new LinkedHashSet<>();
                            for (MessageUi2 messageUi : messageUiList) {
                                objectList.add(Utils.getDateChat(messageUi.getFecha()));
                                objectList.add(messageUi);
                            }
                            messageCallback.onLoad(true, finalSalaId,new ArrayList<Object>(objectList));
                    } else {
                        messageCallback.onLoad(false, finalSalaId,null);
                    }

                } else {
                    messageCallback.onLoad(false, finalSalaId,null);
                }

                realtimeMessage(finalSalaId, emisor,messageUiList, messageCallback);
            }
        });

        return new ListenerFirebase() {
            @Override
            public void onStop() {
                for (ListenerFirebase firebaseFirestore : listenerFirebaseList)firebaseFirestore.onStop();
                listenerFirebaseList.clear();
            }
        };
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
    public void changeEstado(List<MessageUi2> messageUi2List, Callback<List<MessageUi2>> listCallback) {

    }

    @Override
    public void getTokensSala(final String salaId, final Callback<String> listCallback) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            listCallback.onLoad(false,null);
                        }else {
                            InstanceIdResult instanceIdResult = task.getResult();
                            // Get new Instance ID token
                            String token = instanceIdResult!=null ? instanceIdResult.getToken():"";
                            listCallback.onLoad(true,token);
                        }

                    }
                });
    }

    @Override
    public void changeEstadoEliminado(List<MessageUi2> messageUi2List) {
        WriteBatch batch = FirebaseFirestore.getInstance().batch();
        for (MessageUi2 messageUi2 : messageUi2List){
            String salaId = "";
            if(messageUi2.getReceptorId()>messageUi2.getEmisorId()){
                salaId = messageUi2.getReceptorId()+"_"+messageUi2.getEmisorId();
            }else {
                salaId = messageUi2.getEmisorId()+"_"+messageUi2.getReceptorId();
            }
            batch.update(collectionReferenceChat.document(salaId).collection("message").document(messageUi2.getId()), "state", ELIMINADO);
        }
        // Commit the batch
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }

    @Override
    public void saveImageMensaje(List<MessageUi2> messageUiList, final MessageImageCallback callback) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        for (final MessageUi2 item : messageUiList){

            String salaId = "";
            if(item.getEmisorId()>item.getReceptorId()){
                salaId = item.getEmisorId()+"_"+item.getReceptorId();
            }else {
                salaId = item.getReceptorId()+"_"+item.getEmisorId();
            }
            final String finalSalaId = salaId;

            File file = UtilsAppMessenger.compressImage2(item.getImagen());
            Uri myUri = Uri.fromFile(file);

            final StorageReference SavestorageReference = storageReference.child("AppMessenger").child("icrmedu_docente").child("Grupal").child(String.valueOf(salaId)).child(file.getName());
            SavestorageReference.putFile(myUri)
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //track the progress of = our upload
                            int currentProgress = (int) ((double) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount()));
                            Log.d("SaveImgen", "currentProgress: " + currentProgress);
                            callback.onProgres(currentProgress);
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

                    collectionReferenceChat.document(finalSalaId).collection("message").add(getMessage(item, IMAGEN)).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Log.d(TAG, "create chat success  "+ task.isSuccessful());
                            if(task.isSuccessful()){
                                item.setId(task.getResult()!=null?task.getResult().getId():"0");
                                Map<String, Object> chat =  getChat(item);
                                collectionReferenceChat.document(finalSalaId).set(chat);

                                callback.onLoad(true, item);
                            }else {
                                callback.onLoad(false, item);
                            }
                        }

                    });

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    callback.onLoad(false, item);
                }
            });

        }
    }

    @Override
    public RetrofitCancel sendNotificacion(final MessageUi2 messageUi2, String tokens, final MessageCallback callback) {
        Gson gson = new Gson();

        JsonObject jsonData = new JsonObject();
        jsonData.add("message", gson.toJsonTree(messageUi2));


        JsonObject jsonObject = new JsonObject();
        jsonObject.add("to", gson.toJsonTree("/topics/persona_"+ messageUi2.getReceptorId()));
        jsonObject.add("data", jsonData);
        RetrofitCancel<String> retrofitCancel = null;
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(60,60,60, TimeUnit.SECONDS);

        if(!TextUtils.isEmpty(tokens)){
            List<String> stringList = new ArrayList<>();
            stringList.add(tokens);
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.add("registration_ids", gson.toJsonTree(stringList));
            jsonObject2.add("data", jsonData);

            retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_Notificacion(jsonObject2));
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
        }


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



}
