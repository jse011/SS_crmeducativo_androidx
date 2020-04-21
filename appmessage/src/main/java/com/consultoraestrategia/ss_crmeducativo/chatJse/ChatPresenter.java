package com.consultoraestrategia.ss_crmeducativo.chatJse;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;

import java.util.ArrayList;

public interface ChatPresenter extends BasePresenter<ChatView> {
    void onClickSend(String text);
    void onRefresh();
    void changeFirsthPostion(int firstVisibleItem);
    void onBtnBajarClicked();
    void onKeyboardOpens(int lastVisibleItem);
    void onBtnEmoticonClicked();
    void onKeyboardClose();
    void onClickMsgListener();

    void onSeleccionarMessage(MessageUi2 messageUi2);

    void onLongClick(MessageUi2 messageUi2);

    void onClickMessage(MessageUi2 messageUi2);

    void onClickNavigationHome();

    void onClickInfo();

    void onClickCopy();

    void onClickDelete();

    void onClickAcceptDelete();

    void onClickedBtnCloseAnclar();

    void onClickedBtnImagen();

    void onSeleccionList(ArrayList<String> returnValue, String descripcion);
}
