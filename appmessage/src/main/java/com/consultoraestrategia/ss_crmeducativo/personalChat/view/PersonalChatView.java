package com.consultoraestrategia.ss_crmeducativo.personalChat.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.presenter.PersonalChatPresenter;

import java.util.List;

public interface PersonalChatView extends BaseView<PersonalChatPresenter> {

    void setListMessage(List<Object> messageUiList, int idSender);

    void showHeader(ChatUi chatUi);

    void refreshList(List<Object> messageUis);

    boolean isInternetAvailable();

    void showMessageNotInternet();
    void hideMessageNotInternet();

    void showNotInternet();

    void updateItemCreated(String messageId);

    void showHeaderGroup(ChatUi chatUi);


    void backToMainChat(int idSender);

    void closed();
}
