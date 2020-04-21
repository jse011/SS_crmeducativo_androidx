package com.consultoraestrategia.ss_crmeducativo.chatGrupal;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;

import java.util.List;

public interface ChatView extends BaseView<ChatPresenter> {
    void clearSend();
    void setListMessage(List<Object> response, int personaId);
    void refreshList(List<Object> response);
    void addMessage(List<Object> messageUiList);
    void scrollToPositionBotton();
    void scrollToPosition(int position);
    void showFloatingButton();
    void hideFloatingButton();
    void showEmoticon();
    void changeBtnIconTeclado();
    void changeBtnIconEmoticon();
    void showTeclado();
    void showConutMessage(int countMessage);
    void hideConutMessage();
    void setConutMessage(int countMessage);
    void finishActivty();
    void setCabecera(String nombre, String descripcion, String alias, String color, TipoSalaEnum tipo);
    void setConstantSalaId(String salaId);
    void showAnclarMessage(MessageUi2 messageUi2);
    void hideAnclarMessage();
    void showOnPickImage();

    void showCameraPreview(SalaUi salaUi);

    void changeToolbarSelection();

    void changeToolbarNormal();

    void changeList();

    void showInfoMessage();

    void hideInfoMessage();

    void showAlertDelete(String mensaje);

    void showDeleteMessage();

    void hideDeleteMessage();

    void setCountSelection(int countMessage);
}
