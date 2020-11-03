package com.consultoraestrategia.ss_crmeducativo.chatJse.data.source;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.local.ChatLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.remote.ChatRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;

import java.util.Date;
import java.util.List;

public class ChatRepository implements ChatDataSource {

    private ChatLocalDataSource localDataSource;
    private ChatRemoteDataSource chatRemoteDataSource;

    public ChatRepository(ChatLocalDataSource localDataSource, ChatRemoteDataSource chatRemoteDataSource) {
        this.localDataSource = localDataSource;
        this.chatRemoteDataSource = chatRemoteDataSource;
    }

    @Override
    public PersonaUi getPersona(int personaId) {
        return localDataSource.getPersona(personaId);
    }

    @Override
    public void saveMensaje(MessageUi2 messageUi2, MessageCallback messageCallback) {
        chatRemoteDataSource.saveMensaje(messageUi2,messageCallback);
    }

    @Override
    public ListenerFirebase getListaMessage(int emisor, int reseptor, ListaMessageCallback callback) {
        return chatRemoteDataSource.getListaMessage(emisor, reseptor, callback);
    }

    @Override
    public ListenerFirebase getListlastMessage(int emisor, int reseptor, Date date, ListaMessageCallback messageCallback) {
        return chatRemoteDataSource.getListlastMessage(emisor, reseptor, date, messageCallback);
    }

    @Override
    public void saveKeyPress(int person, int personaExterna) {

    }

    @Override
    public ListenerFirebaseImpl getKeyPressEmisor(int personaId, int personaExternaId, CallbackKeyPressEmisor callback) {
        return null;
    }

    @Override
    public void changeEstado(List<MessageUi2> messageUi2List, Callback<List<MessageUi2>> listCallback) {
        this.changeEstado(messageUi2List, listCallback);
    }

    @Override
    public RetrofitCancel sendNotificacion(MessageUi2 messageUi2, String token, MessageCallback messageCallback) {
        return this.chatRemoteDataSource.sendNotificacion(messageUi2, token, messageCallback);
    }

    @Override
    public void getTokensSala(String salaId, Callback<String> stringCallback) {
        this.chatRemoteDataSource.getTokensSala(salaId, stringCallback);
    }

    @Override
    public void changeEstadoEliminado(List<MessageUi2> messageUi2List) {
        this.chatRemoteDataSource.changeEstadoEliminado(messageUi2List);
    }

    @Override
    public void saveImageMensaje(List<MessageUi2> messageUi2, MessageImageCallback callback) {
        this.chatRemoteDataSource.saveImageMensaje(messageUi2, callback);
    }

}
