package com.consultoraestrategia.ss_crmeducativo.chats.presenter;

import androidx.fragment.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.chats.contacs.ContactsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.groups.GroupsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.listchats.ChatsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.view.Chatview;

public interface ChatPresenter extends BasePresenter<Chatview> {

    void onChildsFragmentViewCreated();

    void attachView(ChatsFragment chatsFragment);

    void onChatsFragmentViewDestroyed();

    void onContactsFragmentViewDestroyed();

    void attachView(ContactsFragment f);
    void attachView(GroupsFragment groupsFragment);

    void onResumenChatsList();

    void onResumContactsList();

    void onPageChanged(Class<? extends Fragment> aClass);

    void search(String toString);

    void setListOld();

    void showListFilter(ContactUi.TypeContact teacher);

    void onGroupsFragmentViewDestroyed();

    void clickTextSearch();

    void valideFragmentSearch();

    void activityCreatedContactsFragment();

    void onClickChatContacto(ContactUi contactUi);

    void activityChatsFragment();

    void onClickChat(ChatUi chatUi);

    void activityGroupsFragment();

    void onRefreshChats();


//    void attachView(DetalleDirectivosFragment f);
//
//    void attachView(DetalleProfesoresFragment f);
}
