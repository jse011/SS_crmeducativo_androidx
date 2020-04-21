package com.consultoraestrategia.ss_crmeducativo.chatGrupal.source;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebaseImpl;

import java.util.Date;
import java.util.List;

public interface ChatDataSource {
    void changeEstadoEliminado(List<MessageUi2> messageUi2List);

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
        void onChangeEstado();
    }
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    interface CallbackKeyPressEmisor{
        void onKeyPress(boolean estado);
    }

    PersonaUi getPersona(int personaId);
    void saveMensaje(MessageUi2 messageUi2, MessageCallback messageCallback);
    ListenerFirebase getListaMessage(String salaId, final int personaId, ListaMessageCallback callback);
    void getListlastMessage(String salaId, final int personaId, Date date, Callback<List<Object>> callback);
    void saveKeyPress(int person, int personaExterna);
    ListenerFirebaseImpl getKeyPressEmisor(int personaId, int personaExternaId, CallbackKeyPressEmisor callback);
    SalaUi getSala(int cargaAcademicaId, int cargaCursoId, String grupoEquipoId, TipoSalaEnum tipo);
    PersonaUi getPersonaChatMessage(int emisorId);
    RetrofitCancel sendNotificacion(MessageUi2 messageUi2, List<String> tokens, MessageCallback messageCallback);
    void getDatosChat(MessageUi2 messageUi2);
    void getTokensSala(String salaId, Callback<List<String>> callback);
    void updateTaken(String salaId, String personaId);
    void getSalaIntegrantes(String salaId, Callback<List<PersonaUi>> listCallback);
    void saveImageMensaje(List<MessageUi2> messageUi2, MessageImageCallback messageImageCallback);
}
