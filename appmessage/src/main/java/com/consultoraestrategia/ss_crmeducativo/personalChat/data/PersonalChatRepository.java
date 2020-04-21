package com.consultoraestrategia.ss_crmeducativo.personalChat.data;

import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.Date;
import java.util.List;

public class PersonalChatRepository  implements PersonalChatDataSource {

    PersonalChatDataRemoteSource personalChatDataRemoteSource;
    PersonalChatDataLocalSource personalChatDataLocalSource;


    public PersonalChatRepository(PersonalChatDataRemoteSource personalChatDataRemoteSource,PersonalChatDataLocalSource personalChatDataLocalSource) {
        this.personalChatDataRemoteSource = personalChatDataRemoteSource;
        this.personalChatDataLocalSource=personalChatDataLocalSource;
    }

    @Override
    public void validateChatExistence(int idsender, int idreceiver,Callback<String>callback){
         personalChatDataRemoteSource.validateChatExistence(idsender, idreceiver,callback);
    }



    @Override
    public void validatePersonExistence(int idperson, boolean internet,  SuccessCallback successCallbackPerson) {
        personalChatDataRemoteSource.validatePersonExistence(idperson,internet,successCallbackPerson);
    }

    @Override
    public void listLastMessage(String codeChat,  SendDataChatBundle.TypeChat  type, SendDataChatBundle.TypePerson typePersonsGroup,Callback<List<Object>>chatUiCallback) {
        personalChatDataRemoteSource.listLastMessage(codeChat,type,typePersonsGroup, chatUiCallback);
    }

    @Override
    public void foundChatReceiver(int idreceiver, Callback<ChatUi>chatUiCallback){
        personalChatDataRemoteSource.foundChatReceiver(idreceiver,chatUiCallback);
    }

    @Override
    public void saveLastConexion(int idsender) {
        personalChatDataRemoteSource.saveLastConexion(idsender);
    }

    @Override
    public void saveActiveSession(int idsender) {
        personalChatDataRemoteSource.saveActiveSession(idsender);
    }

    @Override
    public void sendMessage(String codeChat, MessageUi messageUi, SendDataChatBundle.TypePerson typePersonsGroup,  SendDataChatBundle.TypeChat type, Callback<String> successCallback) {
        personalChatDataRemoteSource.sendMessage(codeChat,messageUi, typePersonsGroup,type,successCallback);
    }

    @Override
    public void listBeforeMessage(String codeChat, Date lastMessage,  SendDataChatBundle.TypeChat  type,  SendDataChatBundle.TypePerson typePersonsGroup,Callback<List<Object>> chatUiCallback) {
        personalChatDataRemoteSource.listBeforeMessage(codeChat, lastMessage,type,typePersonsGroup, chatUiCallback);
    }

    @Override
    public void valideGroupExistence(String idgroup, boolean internet, SendDataChatBundle.TypeGroup type,  SendDataChatBundle.TypePerson typePerson,SuccessCallback successCallbackPerson) {
        personalChatDataRemoteSource.valideGroupExistence(idgroup, internet, type, typePerson,successCallbackPerson);
    }

    @Override
    public void validateChatExistenceGroup(int idsender, String idreceiver, SendDataChatBundle.TypePerson typePerson, SendDataChatBundle.TypeGroup typeGroup,  Callback<ChatUi> callback) {
        personalChatDataRemoteSource.validateChatExistenceGroup(idsender, idreceiver,typePerson, typeGroup,callback);
    }

    @Override
    public void getPersonsOfGroup(int senderId, String groupId, SendDataChatBundle.TypeGroup typeGroup,  SendDataChatBundle.TypePerson typePerson,Callback<List<Integer>> callback) {
        personalChatDataLocalSource.getPersonsOfGroup(senderId, groupId,typeGroup,typePerson, callback);
    }

    @Override
    public void createChatGroup(int idsender, String idreceiver,   SendDataChatBundle.TypePerson typePerson, SendDataChatBundle.TypeGroup typeGroup,Callback<ChatUi> callback) {
        personalChatDataRemoteSource.createChatGroup(idsender, idreceiver, typePerson,typeGroup,callback);
    }


}
