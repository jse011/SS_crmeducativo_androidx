package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.holders.PersonasDestinoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.FirstColumn;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.HeaderTable;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.ListCells;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.RubroUIFIrebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @stevecampos on 15/08/2017.
 */

public class FirebaseMessage {

    private static final String TAG = FirebaseMessage.class.getSimpleName();

    interface GetContactCallback {
        void onContactLoaded(Contact contact);

        void onDataNotAvailable();
    }

    public interface SendMessageCallback {
        void onSuccess(ChatMessage message);

        void onError(ChatMessage message, String error);
    }

    private FirebaseDatabase database;

    public FirebaseMessage() {
        database = FirebaseDatabase.getInstance();
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void sendOfficialMessage(String phoneNumberFrom,
                                    final String phoneNumberTo,
                                    final String messageText,
                                    String asunto,
                                    String titulo,
                                    String referencia1,
                                    String referencia2,
                                    String referencia3,
                                    String referencia4,
                                    String importantReference,
                                    String officialEmisorName,
                                    String actionType,
                                    int state,
                                    final SendMessageCallback sendMessageCallback) {
        sendOfficialMessage(phoneNumberFrom,
                phoneNumberTo,
                messageText,
                new OfficialMessage(null, asunto, titulo, referencia1, referencia2, referencia3, referencia4, importantReference, officialEmisorName, actionType, state),
                sendMessageCallback);
    }

    public void sendOfficialMessage(String phoneNumberFrom,
                                    final String phoneNumberTo,
                                    final String messageText,
                                    final OfficialMessage officialMessage,
                                    final SendMessageCallback sendMessageCallback) {

        if (TextUtils.isEmpty(phoneNumberFrom) ||
                TextUtils.isEmpty(phoneNumberTo) ||
                officialMessage == null) {

            if (sendMessageCallback != null) {
                sendMessageCallback.onError(null, "values can't be null");
            }
        }


        ChatMessage message = new ChatMessage();
        //message.setEmisor(emisor);
        //message.setReceptor(receptor);
        message.setMessageText(messageText);
        message.setMessageStatus(ChatMessage.STATUS_WRITED);
        message.setMessageType(ChatMessage.TYPE_TEXT_OFFICIAL);
        message.setMessageUri("");
        message.setTimestamp(new Date().getTime());
        //message.setChatKey(chat.getChatKey());
        /*OfficialMessage officialMessage = new OfficialMessage(
                "id",
                "Autorización de Salida",
                "Viaje de Estudias a Ica",
                "Hijo: Russel M",
                "Fecha: 20/08/207\nHora: 02:30 PM",
                "",
                "",
                "5to Año\nSección A",
                "Atte.\nProfesor Guillermo Mamani A.",
                OfficialMessage.ACTION_TYPE_CONFIRM,
                OfficialMessage.STATE_WAITING
        );*/
        message.setOfficialMessage(officialMessage);
        sendOfficialMessage(phoneNumberFrom, phoneNumberTo, message, sendMessageCallback);
    }

    private void sendOfficialMessage(String phoneNumberFrom,
                                     final String phoneNumberTo,
                                     final ChatMessage message,
                                     final SendMessageCallback sendMessageCallback) {

        sendMessageWithPhoneNumbers(phoneNumberFrom, phoneNumberTo, message, sendMessageCallback);
    }


    private void sendMessageWithPhoneNumbers(String phoneNumberFrom, final String phoneNumberTo, final ChatMessage message, final SendMessageCallback sendMessageCallback) {
        getContactFromPhoneNumber(phoneNumberFrom, new GetContactCallback() {
            @Override
            public void onContactLoaded(final Contact from) {

                getContactFromPhoneNumber(phoneNumberTo, new GetContactCallback() {
                    @Override
                    public void onContactLoaded(Contact to) {
                        sendMessage(from, to, message, sendMessageCallback);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        if (sendMessageCallback != null) {
                            sendMessageCallback.onError(message, "¡Contact receptor no disponible!");
                        }
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                if (sendMessageCallback != null) {
                    sendMessageCallback.onError(message, "¡Contact emisor no disponible!");
                }
            }
        });
    }

    private String getKeyMessage(Contact from, Contact to) {
        String emisorUid = from.getUid();
        String receptorUid = to.getUid();
        String[] sortUids = sortAlphabetical(emisorUid, receptorUid);
        String uidChat = sortUids[0] + "_" + sortUids[1];
        return getDatabase().getReference("chats-messages").child(uidChat).push().getKey();
    }

    private static String[] sortAlphabetical(String key1, String key2) {
        String temp;
        int compare = key1.compareTo(key2);//Comparing strings by their alphabetical order
        if (compare > 0) {
            temp = key2;
            key2 = key1;
            key1 = temp;
        }
        return new String[]{key1, key2};
    }


    private void sendMessage(Contact from, Contact to, ChatMessage chatMessage, final SendMessageCallback sendMessageCallback) {
        Log.d(TAG, "sendMessage");
        String keyMessage = getKeyMessage(from, to);
        chatMessage.setKeyMessage(keyMessage);
        chatMessage.setEmisor(from);
        chatMessage.setReceptor(to);

        if (chatMessage.getOfficialMessage() != null) {
            OfficialMessage officialMessage = chatMessage.getOfficialMessage();
            officialMessage.setId(chatMessage.getKeyMessage());
            chatMessage.setOfficialMessage(officialMessage);
        }

        final ChatMessage message = chatMessage;

        getDatabase().getReference()
                .child("users")
                .child(to.getUid())
                .child("lastConnection")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            Connection connection = dataSnapshot.getValue(Connection.class);
                            if (connection != null) {
                                sendMessage(connection.isOnline(), message.getMessageStatus(), message, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        if (databaseError != null) {
                                            if (sendMessageCallback != null) {
                                                sendMessageCallback.onError(message, databaseError.getMessage());
                                            }
                                            return;
                                        }

                                        if (sendMessageCallback != null) {
                                            sendMessageCallback.onSuccess(message);
                                        }
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        sendMessageCallback.onError(message, databaseError.getMessage());
                    }
                });
    }

    private void sendMessage(boolean isOnlineReceptor, int messageStatus, ChatMessage message, DatabaseReference.CompletionListener listener) {
        Contact from = message.getEmisor();
        Contact to = message.getReceptor();

        String emisorUid = from.getUid();
        String receptorUid = to.getUid();

        String[] sortUids = sortAlphabetical(emisorUid, receptorUid);
        String uidChat = sortUids[0] + "_" + sortUids[1];

        message.setMessageStatus(messageStatus);
        message.setChatKey(uidChat);

        Map<String, Object> map = buildOfficialMessageMap(message, isOnlineReceptor);

        getDatabase().getReference().updateChildren(map, listener);
    }


    private Map<String, Object> buildOfficialMessageMap(ChatMessage message, boolean online) {
        String uidChat = message.getChatKey();
        String keyMessage = message.getKeyMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("/chats-messages/" + uidChat + "/" + keyMessage, message.toMap());
        map.put("/users-messages/" + message.getEmisor().getUid() + "/" + keyMessage, message.toMap());
        map.put("/users-messages/" + message.getReceptor().getUid() + "/" + keyMessage, message.toMap());
        if (!online) {
            map.put("/notifications/" + keyMessage, message.toMap());
        }

        if (message.getMessageType().equals(ChatMessage.TYPE_TEXT_OFFICIAL)) {
            map.put("/official-messages/" + keyMessage, message.toMap());
        }
        return map;
    }


    private void getContactFromPhoneNumber(String phoneNumber, final GetContactCallback callback) {
        getDatabase().getReference("phoneNumbers").child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "getContactFromPhoneNumber onDataChange: " + dataSnapshot);
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    String uid = dataSnapshot.getValue(String.class);
                    getContact(uid, callback);
                } else {
                    onContactNotAvaliable(callback);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onContactNotAvaliable(callback);
            }
        });
    }

    public static void getContactFromPhoneNumberExits(String phoneNumber, final PersonasDestinoViewHolder.VerifiContacFirebase callback) {

        if (!phoneNumber.isEmpty()) {
            FirebaseDatabase.getInstance().getReference("phoneNumbers").child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "getContactFromPhoneNumber onDataChange: " + dataSnapshot);
                    if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                        callback.onContacExist();
                    } else {
                        callback.onContacDontExist();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    callback.onContacDontExist();

                }
            });
        } else {
            callback.onContacDontExist();
        }
    }

    private void onContactNotAvaliable(GetContactCallback callback) {
        if (callback != null) {
            callback.onDataNotAvailable();
        }
    }

    private void getContact(String uid, final GetContactCallback callback) {
        getDatabase()
                .getReference()
                .child("users")
                .child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "getContact onDataChange");
                        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                            Contact contact = dataSnapshot.getValue(Contact.class);
                            if (contact != null) {
                                Log.d(TAG, "contact : " + contact.toString());
                                callback.onContactLoaded(contact);
                            }
                        } else {
                            onContactNotAvaliable(callback);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        onContactNotAvaliable(callback);
                    }
                });
    }

    public void sendReporteMessage(String nombreRubro, String phonenumberFrom, String phonenumberTo, List<String> cuadros, String nombreCurso, String nombre, String apellidos, String puntos, String desempenio, String logro, String programa, String urlProfile, int columns, String periodo, String seccion, List<FirstColumn> nombresRubros, List<HeaderTable> headerTableList, List<ListCells> listCellsParent, SendMessageCallback callback) {


        int[] sizes = calcularSizes(cuadros, columns);
        int width = sizes[0];
        int height = sizes[1];


        int cantidad = (int)(Double.parseDouble(desempenio) * 100);
        String desempenioPercet = String.valueOf( cantidad + "%");

        RubroUIFIrebase rubroUIFIrebase = new RubroUIFIrebase();
        rubroUIFIrebase.setNombreCursoGradoSeccion(programa + " - " + nombreCurso + " : " + periodo + " - " + seccion);
        rubroUIFIrebase.setNombreRubrica(nombreRubro);
        rubroUIFIrebase.setNombre(nombre);
        rubroUIFIrebase.setApellido(apellidos);
        rubroUIFIrebase.setUrlImg(urlProfile);
        rubroUIFIrebase.setPuntos(puntos);
        rubroUIFIrebase.setNota("");
        rubroUIFIrebase.setDesempenio(desempenioPercet);
        rubroUIFIrebase.setLogro(logro);
        rubroUIFIrebase.setColumna(nombresRubros);
        rubroUIFIrebase.setFila(headerTableList);
        rubroUIFIrebase.setCells(listCellsParent);
        Gson gson = new Gson();
        String json = gson.toJson(rubroUIFIrebase);

        sendScreenshot(
                phonenumberFrom,
                phonenumberTo,
                json,
//                constructImgTableUrl(title, nombre, puntos, desempenio, logro, periodo, seccion, programa, urlProfile, columns, constructTableString(cuadros)),
                720,
                650
        );
    }

    public void sendScreenshot(String from, String to, String url, int width, int height) {

        Map<String, Object> map = new ArrayMap<>();
        map.put("from", from);
        map.put("to", to);
        map.put("url", url);
        map.put("width", width);
        map.put("height", height);
        getDatabase().getReference()
                .child("send-screenshot")
                .push()
                .updateChildren(map);
    }


//    public boolean existPhoneNumber(String phoneNumber) {
//        boolean existContact = false;
//
//        getDatabase().getReference("phoneNumbers").child(phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String userKey = null;
//                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
//                    userKey = dataSnapshot.getValue().toString();
//                    if (userKey != null && !userKey.isEmpty()) {
//                        existContact = true;
//
//                    } else {
//                        existContact = true;
//                        onContactNotAvaliable(callback);
//                    }
//                } else {
//                    onContactNotAvaliable(callback);
//                    return false;
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }


    public void sendImageMessage(String phoneNumberFrom, final String phoneNumberTo, String url, int width, int height, SendMessageCallback callback) {
        if (TextUtils.isEmpty(phoneNumberFrom) ||
                TextUtils.isEmpty(phoneNumberTo) ||
                TextUtils.isEmpty(url)) {
            callback.onError(null, "phoneNumberFrom || phoneNumberTo || url can't be empty!!!");
            return;
        }
        Log.d(TAG, "sendImageMessage: withd " + width);
        Log.d(TAG, "sendImageMessage: height " + height);

        MediaFile mediaFile = new MediaFile(Uri.parse(url), url, "image/jpeg", height, width, -1, -1, url, 576 * 496);

        ChatMessage message = new ChatMessage();
        message.setMessageText("");
        message.setMessageStatus(ChatMessage.STATUS_WRITED);
        message.setMessageType(ChatMessage.TYPE_IMAGE);
        message.setMessageUri(url);
        message.setMediaFile(mediaFile);
        message.setTimestamp(new Date().getTime());

        sendMessageWithPhoneNumbers(phoneNumberFrom, phoneNumberTo, message, callback);
    }

    public static String removeNonAlphanumeric(String s) {
        if (TextUtils.isEmpty(s)) return null;
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String constructImgTableUrl(String title, String nombre, String puntos, String desempenio, String logro, String periodo, String seccion, String programa, String urlProfile, int columns, String tableValues) {

        String url = "https://crmepruebas.firebaseapp.com/api/table?";
        url += "values=" + tableValues;
        if (columns >= 1) {
            url += "&columns=" + columns;
        }
        if (!TextUtils.isEmpty(title)) {
            url += "&title=" + replaceSpace(title);
        }
        if (!TextUtils.isEmpty(nombre)) {
            url += "&nombre=" + replaceSpace(nombre);
        }
        if (!TextUtils.isEmpty(puntos)) {
            url += "&puntos=" + replaceSpace(puntos);
        }
        if (!TextUtils.isEmpty(desempenio)) {
            url += "&desempenio=" + replaceSpace(desempenio);
        }
        if (!TextUtils.isEmpty(logro)) {
            url += "&logro=" + replaceSpace(logro);
        }
        if (!TextUtils.isEmpty(programa)) {
            url += "&programa=" + replaceSpace(programa);
        }
        if (!TextUtils.isEmpty(periodo)) {
            url += "&grado=" + replaceSpace(periodo);
        }
        if (!TextUtils.isEmpty(seccion)) {
            url += "&seccion=" + replaceSpace(seccion);
        }
        if (!TextUtils.isEmpty(urlProfile)) {
            url += "&img_alumno=" + replaceSpace(urlProfile);
        }

        Log.d(TAG, "constructImgTableUrl: url " + url);
        return url;
    }

    public static String replaceSpace(String str) {
        return str.replace(" ", "%20");
    }

    public static String constructTableString(List<String> items) {
        StringBuilder table = new StringBuilder("[");
        if (items != null && !items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                String text = items.get(i);
                String cleanedText = replaceSpace(text);
                table.append("\"").append(cleanedText).append("\"");
                if (i < items.size() - 1) {
                    table.append(",");
                }
            }
        }
        table.append("]");
        return table.toString();
    }

    private int[] calcularSizes(List<String> values, int columns) {
        int rows = values.size() / columns;
        int unit = 72;
        int firstColumnUnits = 4;

        int hHeight = unit * 3;
        int fHeight = unit * 4;

        int tWidth = (columns + (firstColumnUnits - 1)) * unit;//La primera columna será el triple de largo que las demás
        int tHeight = unit * rows;

        int[] sizeCalculated = new int[2];
        sizeCalculated[0] = tWidth;
        sizeCalculated[1] = hHeight + tHeight + fHeight;
        return sizeCalculated;
    }
}
