package com.consultoraestrategia.ss_crmeducativo.chatJse.data.source;

import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;

import java.util.Date;
import java.util.List;

public interface ChatDataSource {

    interface MessageCallback{
        void onLoad(boolean success, MessageUi2 messageUi);
    }
    interface MessageImageCallback{
        void onLoad(boolean success, MessageUi2 messageUi);
        void onProgres(int progres);
    }
    interface ListaMessageCallback{
        void onLoad(boolean success, String chatId, List<Object> item);
        void onRecivedMessage(List<MessageUi2> messageUis);
    }
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    interface CallbackKeyPressEmisor{
        void onKeyPress(boolean estado);
    }

    PersonaUi getPersona(int personaId);
    void saveMensaje(MessageUi2 messageUi2, MessageCallback messageCallback);
    ListenerFirebase getListaMessage(int emisor, int reseptor, ListaMessageCallback callback);
    ListenerFirebase getListlastMessage(final int emisor, final int reseptor, Date date, final ListaMessageCallback messageCallback);
    void saveKeyPress(int person, int personaExterna);
    ListenerFirebaseImpl getKeyPressEmisor(int personaId, int personaExternaId, CallbackKeyPressEmisor callback);
    void changeEstado(List<MessageUi2> messageUi2List, Callback<List<MessageUi2>> listCallback);
    RetrofitCancel sendNotificacion(MessageUi2 messageUi2, String token, MessageCallback messageCallback);
    void getTokensSala(String salaId, Callback<String> stringCallback);
    void changeEstadoEliminado(List<MessageUi2> messageUi2List);
    void saveImageMensaje(List<MessageUi2> messageUi2, MessageImageCallback callback);
}
