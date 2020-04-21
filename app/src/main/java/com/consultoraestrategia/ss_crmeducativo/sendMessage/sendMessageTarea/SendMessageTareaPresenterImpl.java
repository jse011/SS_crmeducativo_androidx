package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.OfficialMessageWrapper;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.ChatMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.FirebaseMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.OfficialMessage;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.ui.entities.DataImportantMessageUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class SendMessageTareaPresenterImpl extends SendMessageBasePresenterImpl {

    private static final String TAG = SendMessageTareaPresenterImpl.class.getSimpleName();
    private final TelephonyManager telephonyManager;
    private final GetDataImportantMessageUseCase getDataImportantMessageUseCase;
    private Listener<List<OfficialMessageWrapper>> actualListener;

    public SendMessageTareaPresenterImpl(UseCaseHandler handler, Resources res, GetPersonasRelacionasUseCase getPersonasRelacionasUseCase, GetIntencionListUseCase getIntencionListUseCase, TelephonyManager telephonyManager, GetDataImportantMessageUseCase getDataImportantMessageUseCase) {
        super(handler, res, getPersonasRelacionasUseCase, getIntencionListUseCase);
        this.telephonyManager = telephonyManager;
        this.getDataImportantMessageUseCase = getDataImportantMessageUseCase;
    }


    @Override
    protected void getOfficialMessages(List<PersonaRelacionesUI> personaRelacionesIndividualList, Listener<List<OfficialMessageWrapper>> listener) {

        this.actualListener = listener;

        SessionUser user = SessionUser.getCurrentUser();
        if (user == null) {
            actualListener.onError(new Exception("Session user is null!!"));
            return;
        }
        Persona personaDoncente = Persona.getPersona(user.getPersonaId());
        if (personaDoncente == null) {
            actualListener.onError(new Exception("Person is null!!"));
            return;
        }
        String phoneNumberFrom = Utils.formatPhoneNumber(telephonyManager, personaDoncente.getCelular());
        Log.d("RubroPresenterImpl", "getOfficialMessages: phoneNumberFrom " + phoneNumberFrom);
        if (TextUtils.isEmpty(phoneNumberFrom)) {
            actualListener.onError(new Exception("getCelular is null!!"));
            return;
        }
        if (personaRelacionesIndividualList == null || personaRelacionesIndividualList.isEmpty())
            for (PersonaRelacionesUI personaRelaciones : personasGlobalSendMessageSelected) {

                if (!personaRelaciones.getPersona().getCelular().isEmpty()) {
                    String phoneNumberTo = Utils.formatPhoneNumber(telephonyManager, personaRelaciones.getPersona().getCelular());
                    Log.d(TAG, "getOfficialMessages: nroTo - " + personaRelaciones.getPersona().getCelular());
                    Log.d(TAG, "getOfficialMessages: phoneNumberTo - " + phoneNumberTo);
                    if (phoneNumberTo != null)
                        if (!TextUtils.isEmpty(phoneNumberTo)) {
                            sendMessageOfficailTarea(phoneNumberFrom, phoneNumberTo, personaDoncente);
                        }
                }
            }
        else {
            for (PersonaRelacionesUI personaRelaciones : personaRelacionesIndividualList) {

                if (!personaRelaciones.getPersona().getCelular().isEmpty()) {
                    String phoneNumberTo = Utils.formatPhoneNumber(telephonyManager, personaRelaciones.getPersona().getCelular());
                    Log.d(TAG, "getOfficialMessages: nroTo - " + personaRelaciones.getPersona().getCelular());
                    Log.d(TAG, "getOfficialMessages: phoneNumberTo - " + phoneNumberTo);
                    if (phoneNumberTo != null)
                        if (!TextUtils.isEmpty(phoneNumberTo)) {
                            sendMessageOfficailTarea(phoneNumberFrom, phoneNumberTo, personaDoncente);
                        }
                }
            }
        }
        showFinalMessage("Mensajes Enviados a todos los Destinos Disponibles");


    }

    private FirebaseMessage firebaseMessageNormal;


    private void sendMessageOfficailTarea(final String phoneNumberFrom, final String phoneNumberTo, Persona personaEmisor) {
        String actionType;
        int state;

        Log.d(TAG, "sendMessageOfficailTarea: needResponseMessageSelected : " + this.needResponseMessageSelected);
        if (!this.needResponseMessageSelected) {
            actionType = OfficialMessage.ACTION_TYPE_NO_ACTION;
            state = OfficialMessage.STATE_NO_ACTION;
        } else {
            actionType = OfficialMessage.ACTION_TYPE_CONFIRM;
            state = OfficialMessage.STATE_WAITING;
        }
        String nombreCargaAcademica = "";
        String nombreCurso = "";

        if (dataImportantMessageLoaded != null) {
            nombreCargaAcademica = dataImportantMessageLoaded.getNombreCargaAcademica();
            nombreCurso = dataImportantMessageLoaded.getNombreCurso();
        }

        OfficialMessageWrapper wrapper = new OfficialMessageWrapper(
                phoneNumberFrom,
                phoneNumberTo,
                new OfficialMessage(
                        "someId",
                        "Mensaje Tareas", //asunto
                        "Asunto : " + titulo, //titulo
                        "", //referencia 1
                        "Fecha : " + Utils.getDatePhone(), //referencia2
                        "",//referencia3
                        "Curso : \n" + nombreCurso, //referencia4
                        nombreCargaAcademica, //importantReference
                        "Atte.\n" + personaEmisor.getNombreCompleto(), //officialEmisorName
                        actionType, //actionType
                        state
                ),
                contenido
        );
        if (firebaseMessageNormal == null) {
            firebaseMessageNormal = new FirebaseMessage();
        }

        firebaseMessageNormal.sendOfficialMessage(
                wrapper.getPhonenumberFrom(),
                wrapper.getPhonenumberTo(),
                wrapper.getText(),
                wrapper.getMessage(),
                new FirebaseMessage.SendMessageCallback() {
                    @Override
                    public void onSuccess(ChatMessage message) {
                        Log.d(TAG, "onSuccess: message " + message);
                        showMessage("Mensaje Enviado");
                        TareasC tareas = TareasC.getTareaById(tareasUI.getKeyTarea());
                        if (tareas != null) {
                            tareas.setEstadoId(264);
                            tareas.save();
                        }
                    }

                    @Override
                    public void onError(ChatMessage message, String error) {
                        showMessage("Mensaje No enviado ");
                        Log.d(TAG, "onError: message null");
                        Log.d(TAG, "onError: " + error);
                    }
                }
        );
    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);

        if (idCargaCursoProtected != 0) {
            getDataImportanToMessage();
        }

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    private DataImportantMessageUI dataImportantMessageLoaded = null;

    private void getDataImportanToMessage() {


        handler.execute(getDataImportantMessageUseCase,
                new GetDataImportantMessageUseCase.RequestValues(idCargaCursoProtected, idRubroProtected),
                new UseCase.UseCaseCallback<GetDataImportantMessageUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetDataImportantMessageUseCase.ResponseValue response) {
                        dataImportantMessageLoaded = response.getDataImportantMessageUI();
                    }

                    @Override
                    public void onError() {
//                        showMessage(res.getString(R.string.ImcompleteDataToCargaAcademica));
                    }
                }

        );

    }

    private TareasUI tareasUI;

    @Override
    public void setTareUIActual(Parcelable parcelableExtra) {
        tareasUI = Parcels.unwrap(parcelableExtra);
    }
}
