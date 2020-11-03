package com.consultoraestrategia.ss_crmeducativo.chatJse;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;

import java.util.List;

public interface ChatView extends BaseView<ChatPresenter> {
    void setConstantSalaId(String salaId);
    void setCabecera(String nombre, String descripcion, String url);
    void clearSend();
    void setListMessage(List<Object> response, int personaId, boolean notify);
    void refreshList(List<Object> response);
    void addMessage(List<Object> messageUiList);
    void scrollToPositionBotton(boolean delay);
    void scrollToPosition(int position);
    void showFloatingButton();
    void hideFloatingButton();
    void showEmoticon();
    //void changeBtnIconTeclado();
    //void changeBtnIconEmoticon();
    void showTeclado();
    void showConutMessage(int countMessage);
    void hideConutMessage();
    void setConutMessage(int countMessage);
    boolean isShowEmoji();

    void showAnclarMessage(MessageUi2 clone);

    void showInfoMessage();

    void hideInfoMessage();

    void showDeleteMessage();

    void hideDeleteMessage();

    void changeList();

    void setCountSelection(int countSeleccionado);

    void changeToolbarSelection();

    void changeToolbarNormal();

    void showAlertDelete(String mensaje);

    void hideAnclarMessage();

    void showOnPickImage(PersonaUi personaUiExterna);

    void showAnclarMessage(int emisorId, int personaId, String nombre, String imagenFcm, String mensaje);

    void onInitListener();

    void updateList(MessageUi2 messageUi2);

    void updateListPosition(int posicion, MessageUi2 messageUi1);

    void addList(Object add);
}
