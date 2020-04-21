package com.consultoraestrategia.ss_crmeducativo.personalChat.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.personalChat.view.PersonalChatView;

public interface PersonalChatPresenter extends BasePresenter<PersonalChatView> {

    void createMessage(String message);

    void onRefresh();

    void onclikBack();
}
