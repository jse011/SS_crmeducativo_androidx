package com.consultoraestrategia.ss_crmeducativo.chats.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.presenter.ChatPresenter;

import java.util.List;

public interface Chatview extends BaseView<ChatPresenter> {


    void showHeader(String url_image);

    void hideButtonContact();

    void showButtonContact();

    void sendListContacts(List<Object> objects, int typeInt);

    void showSearchDefault();

    void setListFilterGroups(List<Object> filters,List<Object> objectListGroups);

    void hideSearch();

    void showChatPersonal(int personaId, int personaExternaId);

    void showChatGrupal(int personaId, ChatUi chatUi);

    void subscribeToTopic(int personaId);
}
