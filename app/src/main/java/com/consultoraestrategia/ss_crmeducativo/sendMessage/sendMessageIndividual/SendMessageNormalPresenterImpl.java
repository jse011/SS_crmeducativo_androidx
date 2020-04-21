package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual;

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
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.ChatMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.FirebaseMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.OfficialMessage;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.ui.entities.DataImportantMessageUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.BaseSendMessageActivity.EXTRA_ID_RUBRO;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class SendMessageNormalPresenterImpl extends SendMessageBasePresenterImpl {

    private static final String TAG = SendMessageNormalPresenterImpl.class.getSimpleName();
    private final TelephonyManager telephonyManager;
    private final GetDataImportantMessageUseCase getDataImportantMessageUseCase;
    private Listener<List<OfficialMessageWrapper>> actualListener;

    public SendMessageNormalPresenterImpl(UseCaseHandler handler, Resources res, GetPersonasRelacionasUseCase getPersonasRelacionasUseCase, GetIntencionListUseCase getIntencionListUseCase, TelephonyManager telephonyManager, GetDataImportantMessageUseCase getDataImportantMessageUseCase) {
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
        if (TextUtils.isEmpty(phoneNumberFrom)) {
            actualListener.onError(new Exception("getCelular is null!!"));
            return;
        }

        if (personaRelacionesIndividualList == null) {
            for (PersonaRelacionesUI personaRelaciones : personasGlobalSendMessageSelected) {

                if (!personaRelaciones.getPersona().getCelular().isEmpty()) {
                    String phoneNumberTo = Utils.formatPhoneNumber(telephonyManager, personaRelaciones.getPersona().getCelular());
                    if (phoneNumberTo != null)
                        if (!TextUtils.isEmpty(phoneNumberTo)) {
                            sendMessageOfficailNormal(phoneNumberFrom, phoneNumberTo, personaDoncente, personaRelaciones.getPersonaPrincipal());
                        }
                }
            }
        } else {
            for (PersonaRelacionesUI personaRelaciones : personaRelacionesIndividualList) {
                if (!personaRelaciones.getPersona().getCelular().isEmpty()) {
                    String phoneNumberTo = Utils.formatPhoneNumber(telephonyManager, personaRelaciones.getPersona().getCelular());
                    if (phoneNumberTo != null)
                        if (!TextUtils.isEmpty(phoneNumberTo)) {
                            sendMessageOfficailNormal(phoneNumberFrom, phoneNumberTo, personaDoncente, personaRelaciones.getPersonaPrincipal());
                        }
                }
            }
        }
        showFinalMessage("Mensajes Enviados a todos los Destinos Disponibles");
    }

    private FirebaseMessage firebaseMessageNormal;


    private void sendMessageOfficailNormal(final String phoneNumberFrom, final String phoneNumberTo, Persona personaEmisor, final Persona personaAlumno) {
        String actionType;
        int state;

        Log.d(TAG, "sendMessageOfficailNormal: needResponseMessageSelected : " + this.needResponseMessageSelected);
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
                        "Mensaje", //asunto
                        "Asunto : " + titulo, //titulo
                        "", //referencia 1
                        "Fecha : " + Utils.getDatePhone(), //referencia2
                        "",//referencia3
                        "Curso : " + nombreCurso, //referencia4
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
                        cambiarEstadoRubricaMensaje(personaAlumno);
                    }

                    @Override
                    public void onError(ChatMessage message, String error) {
                        showMessage("Mensaje no enviado");
                        Log.d(TAG, "onError: " + error);
                    }
                }
        );
    }

    private void cambiarEstadoRubricaMensaje(Persona personaAlumno) {
        EvaluacionProcesoC evaluacionProcesoC = EvaluacionProcesoC.getRubroAlumnRubroId(idRubroProtected, personaAlumno.getPersonaId());

        if (evaluacionProcesoC != null) {
            evaluacionProcesoC.setMsje(1);
            evaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            evaluacionProcesoC.save();
            Log.d(TAG, "cambiarEstadoRubricaMensaje: " + evaluacionProcesoC.toString());
        }

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        idRubroProtected = extras.getString(EXTRA_ID_RUBRO);
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


    @Override
    public void setTareUIActual(Parcelable parcelableExtra) {

    }
}
