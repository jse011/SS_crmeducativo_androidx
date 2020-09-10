package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.OfficialMessageWrapper;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.ChatMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.FirebaseMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.OfficialMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.RubroUIFIrebase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.SendMessageRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.local.SendMessageRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.remote.SendMessageRubroRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetNotasRubrosStringUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.ui.entities.DataImportantMessageUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by irvinmarin on 16/07/2018.
 */

public class SendMessageRubroPresenterImpl extends SendMessageBasePresenterImpl {

    private static final String TAG = SendMessageRubroPresenterImpl.class.getSimpleName();
    private final TelephonyManager telephonyManager;
    private final GetDataImportantMessageUseCase getDataImportantMessageUseCase;
    private Listener<List<OfficialMessageWrapper>> actualListener;

    public SendMessageRubroPresenterImpl(UseCaseHandler handler, Resources res, GetPersonasRelacionasUseCase getPersonasRelacionasUseCase, GetIntencionListUseCase getIntencionListUseCase, TelephonyManager telephonyManager, GetDataImportantMessageUseCase getDataImportantMessageUseCase) {
        super(handler, res, getPersonasRelacionasUseCase, getIntencionListUseCase);
        this.telephonyManager = telephonyManager;
        this.getDataImportantMessageUseCase = getDataImportantMessageUseCase;
    }


    @Override
    protected void getOfficialMessages(List<PersonaRelacionesUI> personaRelacionesIndividual, Listener<List<OfficialMessageWrapper>> listener) {

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
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = RubroEvaluacionProcesoC.getById(idRubroProtected);
        String nombreRubro = "";
        if (rubroEvaluacionProcesoC != null) nombreRubro = rubroEvaluacionProcesoC.getTitulo();

        sizeList = personasGlobalSendMessageSelected.size();

        if (personaRelacionesIndividual == null) {
            for (PersonaRelacionesUI personaRelaciones : personasGlobalSendMessageSelected) {
                if (!personaRelaciones.getPersona().getCelular().isEmpty()) {
                    String phoneNumberTo = Utils.formatPhoneNumber(telephonyManager, personaRelaciones.getPersona().getCelular());
                    if (phoneNumberTo != null) {
                        getNotasRubros(personaRelaciones.getPersonaPrincipal().getPersonaId(), phoneNumberTo, phoneNumberFrom, personaDoncente, personaRelaciones, nombreRubro, 0);
                    }
                }

            }
            showFinalMessage("Mensajes Enviados a todos los Destinos Disponibles");

        } else {
            for (PersonaRelacionesUI personaRelaciones : personaRelacionesIndividual) {
                if (!personaRelaciones.getPersona().getCelular().isEmpty()) {
                    String phoneNumberTo = Utils.formatPhoneNumber(telephonyManager, personaRelaciones.getPersona().getCelular());
                    if (phoneNumberTo != null) {
                        getNotasRubros(personaRelaciones.getPersonaPrincipal().getPersonaId(), phoneNumberTo, phoneNumberFrom, personaDoncente, personaRelaciones, nombreRubro, 1);
                    }
                }

            }
        }
    }

    private int countlistafinal;
    private int sizeList;


    private FirebaseMessage firebaseMessageRubro;

    private void getNotasRubros(int idAlumno, final String phoneNumberTo, final String phoneNumberFrom, final Persona personaDocente, final PersonaRelacionesUI personaRelaciones, final String nombreRubro, final int tipoMensajeInsividual) {
        final AtomicReference<String> phoneNumberToLocal = new AtomicReference<>(phoneNumberTo);
        final AtomicReference<String> phoneNumberFromLocal = new AtomicReference<>(phoneNumberFrom);

        SendMessageRubroRepository repositoryRubro = SendMessageRubroRepository.getInstance(
                new SendMessageRubroLocalDataSource(),
                new SendMessageRubroRemoteDataSource());

        Log.d(TAG, "getNotasRubros: "+ phoneNumberTo + "phoneNumberFrom: " + phoneNumberFrom);

        handler.execute(new GetNotasRubrosStringUseCase(repositoryRubro),
                new GetNotasRubrosStringUseCase.RequestValues(idAlumno, idCargaCursoProtected, idRubroProtected),
                new UseCase.UseCaseCallback<GetNotasRubrosStringUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(final GetNotasRubrosStringUseCase.ResponseValue response) {
                        Log.d(TAG, "onSuccess: ");
                        sendMessageOfficaiRubro(response, phoneNumberFromLocal.get(), phoneNumberToLocal.get(), personaDocente, personaRelaciones, nombreRubro, tipoMensajeInsividual);
                        if (tipoMensajeInsividual == 1)
                            view.showSuccessSendMessageIndividual("Mensaje Enviadao a : " + personaRelaciones.getPersona().getNombreCompleto());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError: ");
                        if (tipoMensajeInsividual == 1)
                            view.showSuccessSendMessageIndividual("Mensaje no enviado a : " + personaRelaciones.getPersona().getNombreCompleto() + "");
                    }
                });
//
    }


    private void sendMessageOfficaiRubro(final GetNotasRubrosStringUseCase.ResponseValue response, final String phoneNumberFrom, final String phoneNumberTo, Persona persona, final PersonaRelacionesUI personaRelaciones, String nombreRubro, int tipoMensajeInsividual) {
        String actionType;
        int state;

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

        int cantidad = (int) (response.getDesempenio() * 100);
        String desempenioPercet = String.valueOf(cantidad + "%");

        RubroUIFIrebase rubroUIFIrebase = new RubroUIFIrebase();
        rubroUIFIrebase.setNombreCursoGradoSeccion(response.getPrograma() + " - " + nombreCurso + " : " + response.getPeriodo() + " - " + response.getSeccion());
        rubroUIFIrebase.setNombreRubrica(nombreRubro);
        rubroUIFIrebase.setNombre(response.getNombreAlumno());
        rubroUIFIrebase.setApellido(response.getApellidos());
        rubroUIFIrebase.setUrlImg(response.getUrlProfile());
        rubroUIFIrebase.setPuntos(response.getPuntos());
        rubroUIFIrebase.setNota("");
        rubroUIFIrebase.setDesempenio(desempenioPercet);
        rubroUIFIrebase.setLogro(response.getLogro());
        rubroUIFIrebase.setColumna(response.getNombresRubros());
        rubroUIFIrebase.setFila(response.getHeaderTableList());
        rubroUIFIrebase.setCells(response.getListCellsParent());
        Gson gson = new Gson();
        String json = gson.toJson(rubroUIFIrebase);
        Log.d(TAG, "rubrojson: " + json);

        OfficialMessageWrapper wrapper = new OfficialMessageWrapper(
                phoneNumberFrom,
                phoneNumberTo,
                new OfficialMessage(
                        "someId",
                        "Nota Rubro", //asunto
                        "Asunto : " + titulo, //titulo
                        "", //referencia 1
                        "Fecha : " + Utils.getDatePhone(), //referencia2
                        json,//referencia3
                        "Curso : " + nombreCurso, //referencia4
                        nombreCargaAcademica, //importantReference
                        "Atte.\n" + persona.getNombreCompleto(), //officialEmisorName
                        actionType, //actionType
                        state
                ),
                contenido

        );
        if (firebaseMessageRubro == null) {
            firebaseMessageRubro = new FirebaseMessage();
        }

        firebaseMessageRubro.sendOfficialMessage(
                wrapper.getPhonenumberFrom(),
                wrapper.getPhonenumberTo(),
                wrapper.getText(),
                wrapper.getMessage(),
                new FirebaseMessage.SendMessageCallback() {
                    @Override
                    public void onSuccess(ChatMessage message) {
                        Log.d(TAG, "onSuccess: message " + message);
                        showMessage("enviado a : " + message.getReceptor().getName());
                        countlistafinal++;
                        if (sizeList - 1 == countlistafinal) {
                            view.closeActivity();
                        }

                    }

                    @Override
                    public void onError(ChatMessage message, String error) {
                        showMessage("no enviado");
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
                        showMessage(res.getString(R.string.ImcompleteDataToCargaAcademica));
                    }
                }
        );
    }


    @Override
    public void setTareUIActual(Parcelable parcelableExtra) {

    }
}
