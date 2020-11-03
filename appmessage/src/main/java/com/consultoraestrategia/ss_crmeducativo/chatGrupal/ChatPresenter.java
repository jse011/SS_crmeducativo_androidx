package com.consultoraestrategia.ss_crmeducativo.chatGrupal;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;

import java.util.ArrayList;

public interface ChatPresenter extends BasePresenter<ChatView> {
    void onClickSend(String text);
    void onRefresh();
    void changeFirsthPostion(int firstVisibleItem);
    void onBtnBajarClicked();
    void onKeyboardOpens(int lastVisibleItem);
    void onBtnEmoticonClicked();
    void onClickMsgListener();

    void onKeyboardClose();

    void onSeleccionarMessage(MessageUi2 messageUi2);

    void onClickedBtnCloseAnclar();

    void onClickedBtnImagen();

    void onSeleccionList(ArrayList<String> returnValue, String descripcion);

    void onClickPickImage();

    void onLongClick(MessageUi2 messageUi2);

    void onClickMessage(MessageUi2 messageUi2);

    void onClickNavigationHome();

    void onClickDelete();

    void onClickAcceptDelete();

    void onClickInfo();

    void onClickCopy();

    void onSelectedSticker(String data);
}
