package com.consultoraestrategia.ss_crmeducativo.personalChat.data;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.Date;
import java.util.List;

public interface PersonalChatDataSource {


    interface Callback<T> {
        void onLoad(boolean success, T item);
    }
    interface SuccessCallback{
        void onLoad(boolean success);
    }




    void validateChatExistence(int idsender, int idreceiver, Callback<String>callback);
    void validatePersonExistence(int idperson, boolean internet,SuccessCallback successCallbackPerson);
    void listLastMessage(String codeChat, SendDataChatBundle.TypeChat type, SendDataChatBundle.TypePerson  typePersonsGroup,Callback<List<Object>>chatUiCallback);
    void foundChatReceiver(int idreceiver,Callback<ChatUi>chatUiCallback);
    void saveLastConexion(int idsender );
    void saveActiveSession(int idsender);
    void sendMessage(String codeChat, MessageUi messageUi, SendDataChatBundle.TypePerson typePerson,SendDataChatBundle.TypeChat  type,Callback<String> successCallback);
    void listBeforeMessage(String codeChat, Date lastMessage,  SendDataChatBundle.TypeChat  type, SendDataChatBundle.TypePerson typePerson,Callback<List<Object>>chatUiCallback);

    //methods for groups
    void valideGroupExistence (String idgroup, boolean internet, SendDataChatBundle.TypeGroup typeGroup,  SendDataChatBundle.TypePerson typePerson, SuccessCallback successCallbackPerson);
    void validateChatExistenceGroup(int idsender, String idreceiver, SendDataChatBundle.TypePerson  typePerson,  SendDataChatBundle.TypeGroup typeGroup, Callback<ChatUi>callback);
    void getPersonsOfGroup(int senderId, String groupId,    SendDataChatBundle.TypeGroup typeGroup, SendDataChatBundle.TypePerson  typePerson,Callback<List<Integer>>callback);
    void createChatGroup(int idsender,  String idreceiver, SendDataChatBundle.TypePerson  typePerson,   SendDataChatBundle.TypeGroup typeGroup,  Callback<ChatUi> callback );
}
