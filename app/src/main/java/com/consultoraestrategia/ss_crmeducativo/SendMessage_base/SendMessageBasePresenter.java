package com.consultoraestrategia.ss_crmeducativo.SendMessage_base;

import android.os.Parcelable;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface SendMessageBasePresenter extends BasePresenter<SendMessageBaseView> {

    void onBtnSendClicked(EditText titulo, EditText contenido);

    void onAlumnoCheckedChange(boolean check);

    void onPadresCheckedChange(boolean check);

    void onRespuestaCheckedChange(boolean check);

    void onIntencionSelected(IntencionUI intencionUI);

    void createMessage(String asunto, String contenido);

    void deletePersonaHijosDestinoOfListSelected(PersonaRelacionesUI personaRelacionesUI);

    void addAutoCompleteListHijosDelete(PersonaRelacionesUI personaRelacionesUI);

    void updateListPersonaHijosSelecetd(PersonaRelacionesUI personaRelacionesUI);

    void removeFromAutoCompleteTextViewHijos(PersonaRelacionesUI itemAtPosition);

    void updateListPersonaPadresSelecetd(PersonaRelacionesUI itemAtPosition);

    void removeFromAutoCompleteTextViewPadres(PersonaRelacionesUI itemAtPosition);

    void deletePersonaPadreDestinoOfListSelected(PersonaRelacionesUI personaRelacionesUI);

    void addAutoCompleteLisPadresDelete(PersonaRelacionesUI personaRelacionesUI);

    void onCountClicked(String recyclerView, boolean showAllRvHijos);

    void changeVisivilityLyHijos(boolean visibleLyHijos);

    void removeAllHijosToSend(boolean removeHijos);

    void updateNeedResponse(boolean checked);

    void onMultiItemsSelectedImc(List<CharSequence> charSequenceList, boolean isInit);

    void setTareUIActual(Parcelable parcelableExtra);

    void showMensajePredSelected();

    void onBtnSendClickedIndividual(EditText txtAsunto, EditText txtDescripcionMensaje, PersonaRelacionesUI personaRelaciones);
}
