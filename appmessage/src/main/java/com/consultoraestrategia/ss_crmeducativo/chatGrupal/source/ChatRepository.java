package com.consultoraestrategia.ss_crmeducativo.chatGrupal.source;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.local.ChatLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.remote.ChatRemoteDataSource;
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
    public void changeEstadoEliminado(List<MessageUi2> messageUi2List) {
        chatRemoteDataSource.changeEstadoEliminado(messageUi2List);
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
    public ListenerFirebase getListaMessage(String salaId, final int personaId, ListaMessageCallback callback) {
        return chatRemoteDataSource.getListaMessage(salaId, personaId, callback);
    }

    @Override
    public void getListlastMessage(String salaId, final int personaId, Date date, Callback<List<Object>> callback) {
         chatRemoteDataSource.getListlastMessage(salaId,personaId, date,callback);
    }

    @Override
    public void saveKeyPress(int person, int personaExterna) {

    }

    @Override
    public ListenerFirebaseImpl getKeyPressEmisor(int personaId, int personaExternaId, CallbackKeyPressEmisor callback) {
        return null;
    }

    @Override
    public SalaUi getSala(int cargaAcademicaId, int cargaCursoId, String grupoEquipoId, TipoSalaEnum tipo) {
        return localDataSource.getSala(cargaAcademicaId, cargaCursoId, grupoEquipoId, tipo);
    }

    @Override
    public PersonaUi getPersonaChatMessage(int emisorId) {
        return localDataSource.getPersonaChatMessage(emisorId);
    }

    @Override
    public RetrofitCancel sendNotificacion(MessageUi2 messageUi2, List<String> tokens, MessageCallback messageCallback) {
        return chatRemoteDataSource.sendNotificacion(messageUi2, tokens, messageCallback);
    }

    @Override
    public void getDatosChat(MessageUi2 messageUi2) {
        localDataSource.getDatosChat(messageUi2);
    }

    @Override
    public void getTokensSala(String salaId, Callback<List<String>> callback) {
        chatRemoteDataSource.getTokensSala(salaId, callback);
    }

    @Override
    public void updateTaken(String salaId, String personaId) {
        chatRemoteDataSource.updateTaken(salaId, personaId);
    }

    @Override
    public void getSalaIntegrantes(String salaId, Callback<List<PersonaUi>> listCallback) {
        chatRemoteDataSource.getSalaIntegrantes(salaId, listCallback);
    }

    @Override
    public void saveImageMensaje(List<MessageUi2> messageUi2, MessageImageCallback messageImageCallback) {
        chatRemoteDataSource.saveImageMensaje(messageUi2, messageImageCallback);
    }

}
