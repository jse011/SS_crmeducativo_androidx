package com.consultoraestrategia.ss_crmeducativo.SendMessage_base;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface SendMessageBaseView extends BaseView<SendMessageBasePresenter> {
    void showTypeIntencionList(List<IntencionUI> intencionUIList);

    void enableViews(boolean isEnable);

    void clearImputs();

    void showIntencionNameSelected(String nombre);

    void showPersonListToSendHijos(List<PersonaRelacionesUI> personaMessageList);

    void setupAutoCompleteTexViewHijos(List<PersonaRelacionesUI> personaRelacionesDeleted);

    void showPersonListToSendPadres(List<PersonaRelacionesUI> personaMessageList);

    void setupAutoCompleteTexViewPadres(List<PersonaRelacionesUI> personaRelacionesDeleted);

    void showRvDestinoPersona(int typeVisibility, String wichRv);

    void showAllrvHijos(int sizeList);

    void showAllrvPadres(int sizeList);

    void showOneItemrvHijos(int sizeList);

    void showOneItemrvPadres(int sizeList);

    void hideLyHijos();

    void showLyHijos();

    void updateVisivilityLy(boolean visibleLyHijosChanged);

    void changeCount(String wichRv, int sizeList);

    void closeActivity();

    void showMensajePredSelecetd(int idIntencionSelected);

    void showSuccessSendMessageIndividual(String msj);
}
