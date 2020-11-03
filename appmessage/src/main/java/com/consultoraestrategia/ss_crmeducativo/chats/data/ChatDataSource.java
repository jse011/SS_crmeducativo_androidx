package com.consultoraestrategia.ss_crmeducativo.chats.data;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UserUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.List;

public interface ChatDataSource {

    interface Callback<T> {
        void onLoad(boolean success, T item);
    }
    interface SuccessCallback{
        void onLoad(boolean success);
    }

    void getSenderInformation(int idSender, Callback<UserUi> chatUiCallback);
    void getChats(int senderId,  int type,Callback<List<ChatUi>>callback);
    void getChatsGroups(int senderId, Callback<List<ChatUi>>callback);
    void getContacts(int senderId, Callback<List<Object>>callback);
    List<Object> getGroups(int personaId);
    void getListFilterGroups(int senderId,Callback<List<Object>> listCallback );
    UsuarioUi getUsuarioDefault();
    ListenerFirebase getPersonaChats(int personaId, Callback<List<ChatUi>> listCallback);
    RetrofitCancel getDatosChat(int personaId, List<ChatUi> chatUiList, Callback<List<ChatUi>> listCallback);
    ListenerFirebase getGrupoChats(int docenteId, int personaId, Callback<List<ChatUi>> listCallback);
    RetrofitCancel sincronizarInformacion(SuccessCallback callBack);
}
