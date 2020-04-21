package com.consultoraestrategia.ss_crmeducativo.personalChat.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.CreateGroupChat;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetChatReceiver;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetMessageBefore;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetMessageChat;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.GetPersonsOfGroups;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.SaveLastConexion;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.SaveSessionActive;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.SendMessage;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidateChatExistence;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidateChatExistenceGroup;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidatePersonExistence;
import com.consultoraestrategia.ss_crmeducativo.personalChat.useCase.ValidatePersonGroupExistence;
import com.consultoraestrategia.ss_crmeducativo.personalChat.view.PersonalChatActivity;
import com.consultoraestrategia.ss_crmeducativo.personalChat.view.PersonalChatView;
import com.consultoraestrategia.ss_crmeducativo.utils.ReferenceFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PersonalChatPreImpl extends BasePresenterImpl<PersonalChatView> implements PersonalChatPresenter {

    private String TAG= PersonalChatPreImpl.class.getSimpleName();
    private int idSender;
    private String idReceiver;
    private ValidateChatExistence validateChatExistence;
    private ValidatePersonExistence validatePersonExistence;
    private GetMessageChat getMessageChat;
    private GetChatReceiver getChatReceiver;
    private SendMessage sendMessage;
    private GetMessageBefore getMessageBefore;
    private SaveLastConexion saveLastConexion;
    private SaveSessionActive saveSessionActive;
    private SendDataChatBundle sendDataChatBundle;
    private SendDataChatBundle.TypeChat type;

    private SendDataChatBundle.TypeGroup typeGroup;
    private ValidateChatExistenceGroup validateChatExistenceGroup;
    private ValidatePersonGroupExistence validatePersonGroupExistence;
    private CreateGroupChat createGroupChat;
    private GetPersonsOfGroups getPersonsOfGroups;
    private SendDataChatBundle.TypePerson typeGroupPersons;

    private String codeChat;
    private Date lastMessage;

    public PersonalChatPreImpl(UseCaseHandler handler, Resources res,ValidateChatExistence validateChatExistence,ValidatePersonExistence validatePersonExistence,
                               GetMessageChat getMessageChat,GetChatReceiver getChatReceiver,SendMessage sendMessage, GetMessageBefore getMessageBefore,
                               SaveLastConexion saveLastConexion, SaveSessionActive saveSessionActive,ValidateChatExistenceGroup validateChatExistenceGroup,
                               ValidatePersonGroupExistence validatePersonGroupExistence,CreateGroupChat createGroupChat,GetPersonsOfGroups getPersonsOfGroups) {
        super(handler, res);
        this.validateChatExistence=validateChatExistence;
        this.validatePersonExistence=validatePersonExistence;
        this.getMessageChat=getMessageChat;
        this.getChatReceiver=getChatReceiver;
        this.sendMessage=sendMessage;
        this.getMessageBefore=getMessageBefore;
        this.saveLastConexion=saveLastConexion;
        this.saveSessionActive=saveSessionActive;
        this.validateChatExistenceGroup=validateChatExistenceGroup;
        this.validatePersonGroupExistence=validatePersonGroupExistence;
        this.createGroupChat=createGroupChat;
        this.getPersonsOfGroups=getPersonsOfGroups;

    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        Log.d(TAG, "setExtras");
        this.sendDataChatBundle=SendDataChatBundle.clone(extras);
        idSender=sendDataChatBundle.getSenderId();
//        idReceiver=sendDataChatBundle.getReceiverId();
        type=sendDataChatBundle.getTypeChat();
        if(type== SendDataChatBundle.TypeChat.GROUP){
            typeGroup=sendDataChatBundle.getTypeGroup();
            typeGroupPersons=sendDataChatBundle.getTypePerson();
            Log.d(TAG, "typeGroup "+ typeGroup+ " typeGroupPersons"+ typeGroupPersons);
        }


        Log.d(TAG, "idSender "+ idSender+ " idReceiver"+ idReceiver);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        validatePerson();

    }

    private void saveSession() {
        saveSessionActive.execute(idSender);
    }

    private void saveConexion() { saveLastConexion.execute(idSender);
    }
    private boolean getInternet(){
        if(view==null)return false;
        return view.isInternetAvailable();
    }

    private void validatePerson() {
        showProgress();
        handler.execute(validatePersonExistence, new ValidatePersonExistence.RequestValues(idSender,  getInternet()), new UseCase.UseCaseCallback<ValidatePersonExistence.ResponseValue>() {
            @Override
            public void onSuccess(ValidatePersonExistence.ResponseValue response) {
                if(response.isExistenceSender()){
                    saveSession();
                    if(type== SendDataChatBundle.TypeChat.GROUP)valideGroupExistence();
                     else validatePersonReceiver();
                }
                hideProgress();
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error validate person existence ");
            }
        });
    }



    private void validatePersonReceiver() {
        showProgress();

        handler.execute(validatePersonExistence, new ValidatePersonExistence.RequestValues(Integer.parseInt(idReceiver), getInternet()), new UseCase.UseCaseCallback<ValidatePersonExistence.ResponseValue>() {
            @Override
            public void onSuccess(ValidatePersonExistence.ResponseValue response) {
                if(response.isExistenceSender()){
                    getchatHeader();
                    valideExistenceChat();
                    if(view!=null)view.hideMessageNotInternet();
                }
                else{
                    if(view!=null)view.showNotInternet();
                }
                hideProgress();
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error validate person receiver existence ");
            }
        });
    }

    private void valideExistenceChat() {
        showProgress();
       handler.execute(validateChatExistence, new ValidateChatExistence.RequestValues(idSender, Integer.parseInt(idReceiver)), new UseCase.UseCaseCallback<ValidateChatExistence.ResponseValue>() {
           @Override
           public void onSuccess(ValidateChatExistence.ResponseValue response) {
               hideProgress();
               if(response.isExistence())
                   codeChat= response.getCodeChat();

               listConversation();


           }

           @Override
           public void onError() {
               Log.d(TAG, "onError valideExistenceChat");
           }
       });

    }



    private void listConversation() {

        if(view!=null) view.hideMessageNotInternet();;
            showProgress();
            if(type== SendDataChatBundle.TypeChat.PERSONAL){if(codeChat==null)codeChat=idSender+"_"+idReceiver;}
            handler.execute(getMessageChat, new GetMessageChat.RequestValues(codeChat, type, typeGroupPersons), new UseCase.UseCaseCallback<GetMessageChat.ResponseValue>() {
                @Override
                public void onSuccess(GetMessageChat.ResponseValue response) {
                    int size=  response.getMessageUis().size();
                    Log.d(TAG, "response "+size);
                    if(size!=0){
                        Object object=response.getMessageUis().get(1);
                        if(object instanceof MessageUi) lastMessage=((MessageUi)object).getDate();

                        Log.d(TAG, "id last message "+lastMessage);
                        if(view!=null)
                        {
                            view.setListMessage(response.getMessageUis(), idSender);

                            if(size==0&& !view.isInternetAvailable())view.showMessageNotInternet();
                        }
                    }

                    hideProgress();
                }

                @Override
                public void onError() {
                    Log.d(TAG, "onError listConversation");
                }
            });


    }

    private void getchatHeader() {

        handler.execute(getChatReceiver, new GetChatReceiver.RequestValues(Integer.parseInt(idReceiver)), new UseCase.UseCaseCallback<GetChatReceiver.ResponseValue>() {
            @Override
            public void onSuccess(GetChatReceiver.ResponseValue response) {
                if(response.getChatUi()!=null){
                    Log.d(TAG, "chat header "+response.getChatUi().getNameReceiver() );
                    if(view!=null)view.showHeader(response.getChatUi());
                }
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError getchatHeader");
            }
        });

    }

    @Override
    public void createMessage(String message) {
        MessageUi messageUiCreated = new MessageUi();
        messageUiCreated.setMessage(message);
        messageUiCreated.setIdsender(idSender);
       // messageUiCreated.setIdreceiver(idReceiver);
        Calendar calendar= Calendar.getInstance();
        messageUiCreated.setDate(calendar.getTime());


        Log.d(TAG, "codeChat "+codeChat );
        if(type== SendDataChatBundle.TypeChat.PERSONAL){if(codeChat==null)codeChat=idSender+"_"+idReceiver;}
        handler.execute(sendMessage, new SendMessage.RequestValues(codeChat, messageUiCreated, typeGroupPersons, type), new UseCase.UseCaseCallback<SendMessage.ResponseValue>() {
            @Override
            public void onSuccess(SendMessage.ResponseValue response) {
                Log.d(TAG, "message send "+ response.isCreated());
                if(response.isCreated())if(view!=null)view.updateItemCreated(response.getMessageId());

            }

            @Override
            public void onError() {
                Log.d(TAG, "onError sending message");
            }
        });

    }

    @Override
    public void onRefresh() {
        if(codeChat!=null){
            getMoreMessage();
        }

    }

    @Override
    public void onclikBack() {
        if(view!=null) {
            if (type == SendDataChatBundle.TypeChat.GROUP)view.backToMainChat(idSender);
            else view.closed();
        }
    }

    private void getMoreMessage() {
        showProgress();
        if(view!=null) view.hideMessageNotInternet();;
        if(lastMessage==null){
            hideProgress();
            return;

        }
        handler.execute(getMessageBefore, new GetMessageBefore.RequestValues(codeChat, lastMessage, type, typeGroupPersons), new UseCase.UseCaseCallback<GetMessageBefore.ResponseValue>() {
        @Override
        public void onSuccess(GetMessageBefore.ResponseValue response) {
            hideProgress();
            Log.d(TAG, "more messages  "+ response.getMessageUis().size());
            if(view!=null)
            {
                view.refreshList(response.getMessageUis());
                int size= response.getMessageUis().size();
                if(size!=0) {
                    Object object=response.getMessageUis().get(1);
                    if(object instanceof MessageUi) lastMessage=((MessageUi)object).getDate();
                }
                Log.d(TAG, "lastMessage"+ lastMessage);
                if(size==0&& !view.isInternetAvailable())view.showMessageNotInternet();

            }
        }

        @Override
        public void onError() {
                Log.d(TAG, "onError get more messages");
        }
    });

    }

    @Override
    public void onStop() {
        super.onStop();
        saveConexion();
    }

    //message group

    private void valideGroupExistence() {

        handler.execute(validatePersonGroupExistence, new ValidatePersonGroupExistence.RequestValues(idReceiver, getInternet(), typeGroup, typeGroupPersons), new UseCase.UseCaseCallback<ValidatePersonGroupExistence.ResponseValue>() {
            @Override
            public void onSuccess(ValidatePersonGroupExistence.ResponseValue response) {
                Log.d(TAG, "valideGroupExistence existence " +response.isExistenceSender());
                if(response.isExistenceSender())validateChatExistenceGroup();

            }

            @Override
            public void onError() {
                Log.d(TAG, "valideGroupExistence error ");
            }
        });
    }



    private void validateChatExistenceGroup() {
        handler.execute(validateChatExistenceGroup, new ValidateChatExistenceGroup.RequestValues(idSender, idReceiver,typeGroupPersons, typeGroup), new UseCase.UseCaseCallback<ValidateChatExistenceGroup.ResponseValue>() {
            @Override
            public void onSuccess(ValidateChatExistenceGroup.ResponseValue response) {
                if(response.isExistence()){
                        if(view!=null)view.showHeaderGroup(response.getChatUi());
                            codeChat=response.getChatUi().getCode();
                    listConversation();
                }else
                    createChatGroup();

            }

            @Override
            public void onError() {
                Log.d(TAG, "validateChatExistenceGroup error ");
            }
        });

    }
    private void getPersons(){
        handler.execute(getPersonsOfGroups, new GetPersonsOfGroups.RequestValues(idReceiver, idSender, typeGroup,typeGroupPersons), new UseCase.UseCaseCallback<GetPersonsOfGroups.ResponseValue>() {
            @Override
            public void onSuccess(GetPersonsOfGroups.ResponseValue response) {
                Log.d(TAG, "response  getPersons size "+ response.getPersonsId().size());
                //if(!response.getPersonsId().isEmpty())createChatGroup(response.getPersonsId());
            }

            @Override
            public void onError() {
                Log.d(TAG, "getPersons error ");
            }
        });
    }
    private void createChatGroup(){
        handler.execute(createGroupChat, new CreateGroupChat.RequestValues(idReceiver, idSender,typeGroupPersons,typeGroup), new UseCase.UseCaseCallback<CreateGroupChat.ResponseValue>() {
            @Override
            public void onSuccess(CreateGroupChat.ResponseValue response) {
                if(view!=null)view.showHeaderGroup(response.getChatUi());
                codeChat=response.getChatUi().getCode();
                listConversation();
                Log.d(TAG, "createChatGroup  code "+ codeChat);
            }

            @Override
            public void onError() {
                Log.d(TAG, "createChatGroup error ");
            }
        });
    }

}
