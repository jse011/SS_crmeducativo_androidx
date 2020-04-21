package com.consultoraestrategia.ss_crmeducativo.chats.data;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UserUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.List;

public class ChatRepository  implements ChatDataSource {

    private ChatDataRemoteSource chatDataRemoteSource;
    private ChatDataLocalSource chatDataLocalSource;

    public ChatRepository( ChatDataRemoteSource chatDataRemoteSource, ChatDataLocalSource chatDataLocalSource) {

        this.chatDataRemoteSource = chatDataRemoteSource;
        this.chatDataLocalSource=chatDataLocalSource;
    }


    @Override
    public void getSenderInformation(int idSender, Callback<UserUi> chatUiCallback) {
        chatDataRemoteSource.getSenderInformation(idSender, chatUiCallback);
    }

    @Override
    public void getChats(int senderId, int type, Callback<List<ChatUi>> callback) {
        chatDataRemoteSource.getChats(senderId, type,callback);
    }

    @Override
    public void getChatsGroups(int senderId, Callback<List<ChatUi>> callback) {
        chatDataRemoteSource.getChatsGroups(senderId, callback);
    }

    @Override
    public void getContacts(int senderId, Callback<List<Object>> callback) {
        chatDataLocalSource.getContacts(senderId, callback);
    }

    @Override
    public List<Object> getGroups(int personaId) {
        return chatDataLocalSource.getGroups(personaId);
    }

    @Override
    public void getListFilterGroups(int senderId, Callback<List<Object>> listCallback) {
        chatDataLocalSource.getListFilterGroups(senderId, listCallback);
    }

    @Override
    public UsuarioUi getUsuarioDefault() {
        return chatDataLocalSource.getUsuarioDefault();
    }

    @Override
    public ListenerFirebase getPersonaChats(int personaId, Callback<List<ChatUi>> listCallback) {
        return chatDataRemoteSource.getPersonaChats(personaId, listCallback);
    }

    @Override
    public RetrofitCancel getDatosChat(int personaId, List<ChatUi> chatUiList, Callback<List<ChatUi>> listCallback) {
        chatDataLocalSource.getDatosChat(personaId, chatUiList,listCallback);
        return chatDataRemoteSource.getDatosChat(personaId, chatUiList,listCallback);
    }

    @Override
    public ListenerFirebase getGrupoChats(int docenteId, int personaId, Callback<List<ChatUi>> listCallback) {
        return chatDataRemoteSource.getGrupoChats(docenteId, personaId, listCallback);
    }


}
