package com.consultoraestrategia.ss_crmeducativo.SendMessage_base;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageBaseRepository;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.local.SendMessageBaseLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.remote.SendMessageBaseRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.ChatMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.utils.FirebaseMessage;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.CreateMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.BaseSendMessageActivity.EXTRA_ID_CARGA_CURSO;
import static com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.BaseSendMessageActivity.EXTRA_ID_RUBRO;
import static com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.BaseSendMessageActivity.EXTRA_PERSON_LIST;


/**
 * Created by irvinmarin on 12/07/2018.
 */

public abstract class SendMessageBasePresenterImpl extends BasePresenterImpl<SendMessageBaseView> implements SendMessageBasePresenter, FirebaseMessage.SendMessageCallback {

    public static final String RV_HIJOS = "Hijos";
    public static final String RV_PADRES = "Padres";
    private final String TAG = SendMessageBasePresenterImpl.class.getSimpleName();
    private GetPersonasRelacionasUseCase getPersonasRelacionasUseCase;
    private GetIntencionListUseCase getIntencionListUseCase;
    protected String titulo;
    protected String contenido;
    protected boolean needResponseMessageSelected = true;
    protected int idCargaCursoProtected;
    protected String idRubroProtected;
    protected List<PersonaRelacionesUI> personasGlobalSendMessageSelected = new ArrayList<>();
    protected List<PersonaRelacionesUI> personasConRelacionesDataBase = new ArrayList<>();
    private List<PersonaRelacionesUI> personasHijosToSendMessageSelected = new ArrayList<>();
    private List<PersonaRelacionesUI> personasPadresToSendMessageSelected = new ArrayList<>();

    public SendMessageBasePresenterImpl(UseCaseHandler handler, Resources res, GetPersonasRelacionasUseCase getPersonasRelacionasUseCase, GetIntencionListUseCase getIntencionListUseCase) {
        super(handler, res);
        this.getPersonasRelacionasUseCase = getPersonasRelacionasUseCase;
        this.getIntencionListUseCase = getIntencionListUseCase;

    }

    @Override
    public void updateNeedResponse(boolean checked) {
        needResponseMessageSelected = checked;
    }

    @Override
    public void onResume() {
        super.onResume();
        getIntencionUIList();

    }

    @Override
    public void showMensajePredSelected() {
        view.showMensajePredSelecetd(intencionUISelected.getId());
    }

    private void getIntencionUIList() {
        handler.execute(getIntencionListUseCase, new GetIntencionListUseCase.RequestValues(),
                new UseCase.UseCaseCallback<GetIntencionListUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetIntencionListUseCase.ResponseValue response) {
                        setListIntencionToView(response.getIntencionUIList());
                    }

                    @Override
                    public void onError() {
                        showMessage(res.getString(R.string.empty_intenciones));
                    }
                });
    }

    private void setListIntencionToView(List<IntencionUI> intencionUIList) {
        if (view != null) {
            view.showTypeIntencionList(intencionUIList);
        }
    }


    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }



    @Override
    public void onMultiItemsSelectedImc(List<CharSequence> charSequenceList, boolean isInit) {
        List<PersonaRelacionesUI> personaRelacionesUIListSelecteds = new ArrayList<>();

        boolean padreSelected = false;
        boolean madreSeleceted = false;
        boolean apoderadoSelected = false;
        for (CharSequence charSequence : charSequenceList) {
            if (charSequence == "Apoderado")
                apoderadoSelected = true;
            if (charSequence == "Padres")
                padreSelected = true;
            if (charSequence == "Madres")
                madreSeleceted = true;
        }

        //*****************************************************************************************

        if (apoderadoSelected && !padreSelected && !madreSeleceted)
            personaRelacionesUIListSelecteds.addAll(getPersonsTipoRelacion(1));
        if (!apoderadoSelected && madreSeleceted && !padreSelected)
            personaRelacionesUIListSelecteds.addAll(getPersonsTipoRelacion(182));
        if (!apoderadoSelected && !madreSeleceted && padreSelected)
            personaRelacionesUIListSelecteds.addAll(getPersonsTipoRelacion(181));

        //*************************************************************
        if (apoderadoSelected && madreSeleceted && !padreSelected) {
            Log.d(TAG, "onMultiItemsSelectedImc: apoderado madre True");

            List<PersonaRelacionesUI> personaFull = new ArrayList<>();
            personaFull.addAll(getPersonsTipoRelacion(1));
            personaFull.addAll(getPersonsTipoRelacion(182));
            personaRelacionesUIListSelecteds.addAll(removeItemsRepeats(personaFull));

        }
        if (!apoderadoSelected && madreSeleceted && padreSelected) {
            Log.d(TAG, "onMultiItemsSelectedImc: padre madre True");

            personaRelacionesUIListSelecteds.addAll(getPersonsTipoRelacion(181));
            personaRelacionesUIListSelecteds.addAll(getPersonsTipoRelacion(182));
        }
        if (apoderadoSelected && !madreSeleceted && padreSelected) {
            Log.d(TAG, "onMultiItemsSelectedImc: apoderado padre True");

            List<PersonaRelacionesUI> personaFull = new ArrayList<>();
            personaFull.addAll(getPersonsTipoRelacion(1));
            personaFull.addAll(getPersonsTipoRelacion(181));

            personaRelacionesUIListSelecteds.addAll(removeItemsRepeats(personaFull));

        }
        //***************************************************************************************

        if (apoderadoSelected && madreSeleceted && padreSelected) {
            Log.d(TAG, "onMultiItemsSelectedImc: todo True");
            personaRelacionesUIListSelecteds = new ArrayList<>();
            List<PersonaRelacionesUI> personaFull = new ArrayList<>();
            personaFull.addAll(getPersonsTipoRelacion(1));
            personaFull.addAll(getPersonsTipoRelacion(181));
            personaFull.addAll(getPersonsTipoRelacion(182));

            personaRelacionesUIListSelecteds.addAll(removeItemsRepeats(personaFull));
        }
        if (!apoderadoSelected && !padreSelected && !madreSeleceted)
            personaRelacionesUIListSelecteds.addAll(getPersonsTipoRelacion(1));

        Collections.sort(personaRelacionesUIListSelecteds, new Comparator<PersonaRelacionesUI>() {
            @Override
            public int compare(PersonaRelacionesUI o1, PersonaRelacionesUI o2) {
                String idPersonaHijo1 = String.valueOf(o1.getPersonaPrincipal().getPersonaId());
                String idPersonaHijo2 = String.valueOf(o2.getPersonaPrincipal().getPersonaId());
                return idPersonaHijo1.compareTo(idPersonaHijo2);
            }
        });
        personasPadresToSendMessageSelected.clear();
        personasPadresToSendMessageSelected = personaRelacionesUIListSelecteds;
        showPersonasListToSend();
    }

    private List<PersonaRelacionesUI> removeItemsRepeats(List<PersonaRelacionesUI> asistenciaSesionAlumnos) {

        List<PersonaRelacionesUI> trueListFinal = new ArrayList<>();

        Collections.sort(asistenciaSesionAlumnos, new Comparator<PersonaRelacionesUI>() {
            @Override
            public int compare(PersonaRelacionesUI o1, PersonaRelacionesUI o2) {
                String idPersonaHijo1 = String.valueOf(o1.getPersona().getPersonaId());
                String idPersonaHijo2 = String.valueOf(o2.getPersona().getPersonaId());
                return idPersonaHijo1.compareTo(idPersonaHijo2);
            }
        });
        for (int i = 0; i < asistenciaSesionAlumnos.size(); i++) {
            if (i > 0) {
                if (asistenciaSesionAlumnos.get(i).getPersona().getPersonaId()
                        != asistenciaSesionAlumnos.get(i - 1).getPersona().getPersonaId()) {
                    trueListFinal.add(asistenciaSesionAlumnos.get(i));
                }
            } else {
                trueListFinal.add(asistenciaSesionAlumnos.get(i));

            }
        }

        return trueListFinal;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    FirebaseMessage firebaseMessage;

    @Override
    public void onBtnSendClicked(EditText txtTitulo, EditText txtContenido) {

        startSendMessage(txtTitulo, txtContenido, null);


    }

    private void startSendMessage(EditText txtTitulo, EditText txtContenido, List<PersonaRelacionesUI> personaRelaciones) {
        this.titulo = txtTitulo.getText().toString();
        this.contenido = txtContenido.getText().toString();

        if (titulo.equals("")) txtTitulo.setError("Ingresar Titulo");
        if (contenido.equals("")) txtContenido.setError("Ingresar Contenido");

        if (!txtTitulo.getText().toString().equals("") && !txtContenido.getText().toString().equals("")) {

            getOfficialMessages(personaRelaciones, new Listener<List<OfficialMessageWrapper>>() {
                @Override
                public void onSuccess(List<OfficialMessageWrapper> data) {
                    sendOfficialMessages(data);
                    Log.d(TAG, "onSuccess: ");
                }

                @Override
                public void onError(Exception ex) {
                    Log.d(TAG, "onError: " + ex.toString());
                }
            });
        }
        if (view != null) {
            view.clearImputs();
        }
    }

    protected void generateMensajesDatabase() {

        SendMessageBaseRepository repository = SendMessageBaseRepository.getInstance(
                new SendMessageBaseLocalDataSource(),
                new SendMessageBaseRemoteDataSource()
        );

        handler.execute(new CreateMessageUseCase(repository),
                new CreateMessageUseCase.RequestValues(
                        titulo,
                        contenido,
                        personasGlobalSendMessageSelected,
                        needResponseMessageSelected,
                        intencionUISelected,
                        idCargaCursoProtected
                ), new UseCase.UseCaseCallback<CreateMessageUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(CreateMessageUseCase.ResponseValue response) {
                        Log.d(TAG, "onSuccess: Mensajes Creados");
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError: Mensajes no Creados");

                    }
                });
    }

    private void sendOfficialMessages(List<OfficialMessageWrapper> data) {
        Log.d(TAG, "sendOfficialMessages: ");
        if (data.isEmpty()) return;

        if (firebaseMessage == null) {
            firebaseMessage = new FirebaseMessage();
        }
        for (OfficialMessageWrapper wrapper : data) {
            firebaseMessage.sendOfficialMessage(
                    wrapper.getPhonenumberFrom(),
                    wrapper.getPhonenumberTo(),
                    wrapper.getText(),
                    wrapper.getMessage(),
                    this
            );
        }
    }

    @Override
    public void onSuccess(ChatMessage message) {
        Log.d(TAG, "onSuccess: ");
//        messagesSendedCounter++;
//        if (messagesSendedCounter == officialMessagesToSendSize) {
//            showImportantMessage(res.getString(R.string.global_message_sucess));
//        }
    }

    @Override
    public void onError(ChatMessage message, String error) {
        Log.d(TAG, "onError: " + error);
        //showMessage(error);
    }

    @Override
    public void onAlumnoCheckedChange(boolean check) {
    }

    @Override
    public void onPadresCheckedChange(boolean check) {
        Log.d(TAG, "onPadresCheckedChange: ");
    }

    @Override
    public void onRespuestaCheckedChange(boolean check) {
        Log.d(TAG, "onRespuestaCheckedChange: " + check);
        needResponseMessageSelected = check;
        Log.d(TAG, "needResponseMessageSelected: " + needResponseMessageSelected);
    }

    private IntencionUI intencionUISelected;

    @Override
    public void onIntencionSelected(IntencionUI intencionUISelected) {
        //guardarIntencion
        Log.d(TAG, "onIntencionSelected: ");
        this.intencionUISelected = intencionUISelected;
        view.showIntencionNameSelected(intencionUISelected.getNombre());
    }

    @Override
    public void createMessage(String asunto, String contenido) {
//        createMessageCallback(asunto, contenido);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        List<Persona> personaList = (List<Persona>) extras.getSerializable(EXTRA_PERSON_LIST);
        idCargaCursoProtected = extras.getInt(EXTRA_ID_CARGA_CURSO);
        idRubroProtected = extras.getString(EXTRA_ID_RUBRO);
        Log.d(TAG, "idRubroProtected: " + idRubroProtected);
        Log.d(TAG, "idCargaCursoProtected: " + idCargaCursoProtected);


        if (!personaList.isEmpty()) {
            getPersonasRelacionadasUseCase(personaList);
        } else {
            showFinalMessage(res.getString(R.string.empty_list_destinos));
        }

    }

    private void getPersonasRelacionadasUseCase(List<Persona> personaList) {
        showProgress();

        handler.execute(getPersonasRelacionasUseCase,
                new GetPersonasRelacionasUseCase.RequestValues(personaList),
                new UseCase.UseCaseCallback<GetPersonasRelacionasUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetPersonasRelacionasUseCase.ResponseValue response) {
                        personasConRelacionesDataBase = response.getPersonaRelacionesList();
                        Log.d(TAG, "onSuccess: " + personasConRelacionesDataBase.toString());
                        personasHijosToSendMessageSelected = getHijosWithCelular();
                        personasPadresToSendMessageSelected = getPersonsTipoRelacion(1);
//                        showPersonasListToSend();
                        showPersonasListToSend();
                        removeAllHijosToSend(true);
                    }

                    @Override
                    public void onError() {
                        showFinalMessage(res.getString(R.string.empty_list_destinos));
                    }


                });

    }

    private List<PersonaRelacionesUI> getHijosWithCelular() {
        List<PersonaRelacionesUI> list = new ArrayList<>();
        if (!personasConRelacionesDataBase.isEmpty()) {
            for (PersonaRelacionesUI personaPrincipal : personasConRelacionesDataBase) {
                if (!personaPrincipal.getPersona().getCelular().isEmpty())
                    list.add(personaPrincipal);
            }
        }
        return list;
    }

    private boolean visibleAllRvHijos;
    private boolean visibleAllRvPadres;

    private void showPersonasListToSend() {
        if (view != null) {
            if (personasHijosToSendMessageSelected.isEmpty()) {
                view.enableViews(false);
                view.showRvDestinoPersona(View.GONE, RV_HIJOS);
            } else {
                view.enableViews(true);
                visibleAllRvHijos = false;
                personasGlobalSendMessageSelected.addAll(personasHijosToSendMessageSelected);
                view.showRvDestinoPersona(View.VISIBLE, RV_HIJOS);
                view.showPersonListToSendHijos(personasHijosToSendMessageSelected);
                view.showOneItemrvHijos(personasHijosToSendMessageSelected.size());
            }
            if (personasPadresToSendMessageSelected.isEmpty()) {
                view.enableViews(false);
                view.showRvDestinoPersona(View.GONE, RV_PADRES);
            } else {
                view.enableViews(true);
                visibleAllRvPadres = false;
                personasGlobalSendMessageSelected.addAll(personasPadresToSendMessageSelected);
                view.showRvDestinoPersona(View.VISIBLE, RV_PADRES);
                view.showPersonListToSendPadres(personasPadresToSendMessageSelected);
                view.showAllrvPadres(personasPadresToSendMessageSelected.size());
            }
        }
    }

    @Override
    public void onBtnSendClickedIndividual(EditText txtAsunto, EditText txtDescripcionMensaje, PersonaRelacionesUI personaRelaciones) {
        List<PersonaRelacionesUI> personaRelacionesUIInsividualList = new ArrayList<>();
        personaRelacionesUIInsividualList.add(personaRelaciones);
        startSendMessage(txtAsunto, txtDescripcionMensaje, personaRelacionesUIInsividualList);

    }

    private List<PersonaRelacionesUI> getPadres() {
        List<PersonaRelacionesUI> list = new ArrayList<>();
        if (!personasConRelacionesDataBase.isEmpty())
            for (PersonaRelacionesUI persona : personasConRelacionesDataBase) {
                List<PersonaRelacionesUI> relaciones = persona.getPersonasRelacionadas();
                if (!relaciones.isEmpty()) {
                    list.addAll(relaciones);
                }
            }
        return list;
    }

    private List<PersonaRelacionesUI> getPersonsTipoRelacion(int tipoRelacionId) {
        List<PersonaRelacionesUI> list = new ArrayList<>();

        if (!personasConRelacionesDataBase.isEmpty()) {
            for (PersonaRelacionesUI personaPrincipal : personasConRelacionesDataBase) {
                List<PersonaRelacionesUI> personasRelacionadasSecList = personaPrincipal.getPersonasRelacionadas();
                for (PersonaRelacionesUI personaRelacionadaSec : personasRelacionadasSecList) {
                    if (tipoRelacionId == 1) {
                        if (personaRelacionadaSec.isApoderado())
                            if (!personaRelacionadaSec.getPersona().getCelular().equals(""))
                                list.add(personaRelacionadaSec);
                    } else {
                        if (personaRelacionadaSec.getTipoParentezco() == tipoRelacionId)
                            if (!personaRelacionadaSec.getPersona().getCelular().equals(""))
                                list.add(personaRelacionadaSec);
                    }
                }
            }
        }
        Log.d(TAG, "personasConRelacionesDataBase: size list : " + personasConRelacionesDataBase.size());
        Log.d(TAG, "getPersonsTipoRelacion: size list : " + list.size());
        return list;
    }

    private List<PersonaRelacionesUI> getAlumnosYPadres() {
        List<PersonaRelacionesUI> list = new ArrayList<>();
        if (!personasConRelacionesDataBase.isEmpty())
            for (PersonaRelacionesUI persona :
                    personasConRelacionesDataBase) {
                list.add(persona);
                List<PersonaRelacionesUI> relaciones = persona.getPersonasRelacionadas();
                if (!relaciones.isEmpty()) {
                    list.addAll(relaciones);
                }
            }
        return list;
    }

    protected interface Listener<T> {
        void onSuccess(T data);

        void onError(Exception ex);
    }

    protected abstract void getOfficialMessages(List<PersonaRelacionesUI> personaRelaciones, Listener<List<OfficialMessageWrapper>> listener);

    @Override
    public void deletePersonaHijosDestinoOfListSelected(PersonaRelacionesUI personaRelacionesUI) {
        personasHijosToSendMessageSelected.remove(personaRelacionesUI);
        view.changeCount(RV_HIJOS, personasHijosToSendMessageSelected.size());

    }

    @Override
    public void deletePersonaPadreDestinoOfListSelected(PersonaRelacionesUI personaRelacionesUI) {
        personasPadresToSendMessageSelected.remove(personaRelacionesUI);
        view.changeCount(RV_PADRES, personasPadresToSendMessageSelected.size());
    }

    private List<PersonaRelacionesUI> personasPadresAutoCompleteTextView = new ArrayList<>();
    private List<PersonaRelacionesUI> personasHijosAutoCompleteTextView = new ArrayList<>();


    @Override
    public void addAutoCompleteListHijosDelete(PersonaRelacionesUI personaRelacionesUI) {
        Log.d(TAG, "addAutoCompleteListHijosDelete: ");
        personasHijosAutoCompleteTextView.add(personaRelacionesUI);
        reloadListPersonHijosDelete();

    }

    @Override
    public void addAutoCompleteLisPadresDelete(PersonaRelacionesUI personaRelacionesUI) {
        Log.d(TAG, "addAutoCompleteLisPadresDelete: ");
        personasPadresAutoCompleteTextView.add(personaRelacionesUI);
        reloadListPersonPadresDelete();
    }

    private void reloadListPersonHijosDelete() {
        view.setupAutoCompleteTexViewHijos(personasHijosAutoCompleteTextView);
    }

    private void reloadListPersonPadresDelete() {
        view.setupAutoCompleteTexViewPadres(personasPadresAutoCompleteTextView);
    }

    @Override
    public void updateListPersonaHijosSelecetd(PersonaRelacionesUI personaRelacionesUI) {
        personasHijosToSendMessageSelected.add(personaRelacionesUI);
        view.changeCount(RV_HIJOS, personasHijosToSendMessageSelected.size());

    }

    @Override
    public void updateListPersonaPadresSelecetd(PersonaRelacionesUI itemAtPosition) {
        personasPadresToSendMessageSelected.add(itemAtPosition);
        view.changeCount(RV_PADRES, personasPadresToSendMessageSelected.size());

    }

    @Override
    public void removeFromAutoCompleteTextViewHijos(PersonaRelacionesUI itemAtPosition) {
        personasHijosAutoCompleteTextView.remove(itemAtPosition);
        reloadListPersonHijosDelete();
    }

    @Override
    public void removeFromAutoCompleteTextViewPadres(PersonaRelacionesUI itemAtPosition) {
        personasPadresAutoCompleteTextView.remove(itemAtPosition);
        reloadListPersonPadresDelete();
    }

    @Override
    public void onCountClicked(String recyclerView, boolean showAllRv) {
        switch (recyclerView) {
            case SendMessageBasePresenterImpl.RV_HIJOS:
                if (!showAllRv) view.showAllrvHijos(personasHijosToSendMessageSelected.size());
                if (showAllRv) view.showOneItemrvHijos(personasHijosToSendMessageSelected.size());

                break;
            case SendMessageBasePresenterImpl.RV_PADRES:
                if (!showAllRv) view.showAllrvPadres(personasPadresToSendMessageSelected.size());
                if (showAllRv) view.showOneItemrvPadres(personasPadresToSendMessageSelected.size());
                break;

        }
    }

    @Override
    public void changeVisivilityLyHijos(boolean visibleLyHijos) {
        if (visibleLyHijos) {
            view.hideLyHijos();
            view.showAllrvPadres(personasPadresToSendMessageSelected.size());
            visibleLyHijos = false;
            removeAllHijosToSend(true);

        } else {
            view.showLyHijos();
            view.showAllrvHijos(personasHijosToSendMessageSelected.size());
            view.showOneItemrvPadres(personasPadresToSendMessageSelected.size());
            removeAllHijosToSend(false);
            visibleLyHijos = true;
        }
        view.updateVisivilityLy(visibleLyHijos);
    }

    @Override
    public void removeAllHijosToSend(boolean removeHijos) {
        if (removeHijos) {
            personasGlobalSendMessageSelected.clear();
            personasGlobalSendMessageSelected.addAll(personasPadresToSendMessageSelected);
        } else {
            personasGlobalSendMessageSelected.clear();
            personasGlobalSendMessageSelected.addAll(personasPadresToSendMessageSelected);
            personasGlobalSendMessageSelected.addAll(personasHijosToSendMessageSelected);
        }
    }
}
