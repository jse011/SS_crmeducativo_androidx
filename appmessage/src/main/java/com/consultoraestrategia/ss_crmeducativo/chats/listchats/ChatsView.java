package com.consultoraestrategia.ss_crmeducativo.chats.listchats;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;

import java.util.List;

public interface ChatsView {
    void setListChats(List<ChatUi> chats);
    void  hideProgress();
    void showProgress();

    void searhChats(String toString);
}
