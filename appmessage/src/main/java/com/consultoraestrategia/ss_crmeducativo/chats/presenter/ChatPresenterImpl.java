package com.consultoraestrategia.ss_crmeducativo.chats.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.chats.contacs.ContacsView;
import com.consultoraestrategia.ss_crmeducativo.chats.contacs.ContactsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.chats.groups.GroupsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.groups.GroupsView;
import com.consultoraestrategia.ss_crmeducativo.chats.listchats.ChatsFragment;
import com.consultoraestrategia.ss_crmeducativo.chats.listchats.ChatsView;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetAllChats;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetChats;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetChatsGroups;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetContacts;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetDatosChatAndUpdatePersonaGrupo;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetGroups;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetListFilterGroups;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetSenderInformation;
import com.consultoraestrategia.ss_crmeducativo.chats.useCase.GetUsuario;
import com.consultoraestrategia.ss_crmeducativo.chats.view.Chatview;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChatPresenterImpl extends BasePresenterImpl<Chatview> implements ChatPresenter  {

    private String TAG=ChatPresenterImpl.class.getSimpleName();
    private GetSenderInformation getSenderInformation;
    private GetDatosChatAndUpdatePersonaGrupo getDatosChatAndUpdatePersonaGrupo;
    private GetChats getChats;
    private List<ChatUi> chatUiListAll = new ArrayList<>();
    private int TYPE_SENDER = 0,TYPE_RECEIVER=1;
    private ChatsView chatsView;
    private ContacsView contacsView;
    private GroupsView groupsView;
    private GetContacts getContacts;
    private List<Object>objectListContacts;
    private Class<? extends Fragment> fragmentClassVisible;
    private GetGroups getGroups;
    private List<Object>objectListGroups = new ArrayList<>();
    private GetListFilterGroups getListFilterGroups;
    private GetChatsGroups getChatsGroups;
    private GetUsuario getUsuario;
    private GetAllChats getAllChats;

    private int personaId;
    private RetrofitCancel retrofitCancel;
    private int empleadoId;
    private List<ListenerFirebase> listenerFirebaseList = new ArrayList<>();
    private boolean emoticon;

    public ChatPresenterImpl(UseCaseHandler handler, Resources res,GetSenderInformation getSenderInformation,GetChats getChats,GetContacts getContacts,GetGroups getGroups,
                             GetListFilterGroups getListFilterGroups, GetChatsGroups getChatsGroups, GetUsuario getUsuario,
                             GetAllChats getAllChats, GetDatosChatAndUpdatePersonaGrupo getDatosChatAndUpdatePersonaGrupo) {
        super(handler, res);
        this.getSenderInformation=getSenderInformation;
        this.getChats=getChats;
        this.getContacts=getContacts;
        this.getGroups=getGroups;
        this.getListFilterGroups=getListFilterGroups;
        this.getChatsGroups=getChatsGroups;
        this.getUsuario = getUsuario;
        this.getAllChats = getAllChats;
        this.getDatosChatAndUpdatePersonaGrupo = getDatosChatAndUpdatePersonaGrupo;
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
    public void onCreate() {
        super.onCreate();
        fragmentClassVisible= ChatsFragment.class;
        UsuarioUi usuarioUi = getUsuario.execute();
        personaId = usuarioUi.getPersonaId();
        empleadoId = usuarioUi.getDocenteId();
        objectListGroups.clear();
        objectListGroups.addAll(getGroups.execute(personaId));
        if(view!=null)view.subscribeToTopic(usuarioUi.getPersonaId());
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);

    }

    private void init() {
        getHeader();
    }

    @Override
    public void onChildsFragmentViewCreated() {
        Log.d(TAG, "onChildsFragmentViewCreated");
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResumen lol");
        retrofitCancel = getDatosChatAndUpdatePersonaGrupo.execute(personaId, chatUiListAll, new GetDatosChatAndUpdatePersonaGrupo.Callback() {
            @Override
            public void onSuccess(List<ChatUi> item) {
                if (chatsView != null) chatsView.setListChats(item);
            }

            @Override
            public void onError() {
                Log.d(getTag(), "getListAllChats error");
            }
        });
    }


            @Override
    public void attachView(ChatsFragment chatsFragment) {
        Log.d(TAG, "attachView fragment");
        this.chatsView=chatsFragment;

    }
    @Override
    public void attachView(ContactsFragment contacsView) {
        this.contacsView=contacsView;
    }

    @Override
    public void attachView(GroupsFragment groupsFragment) {
        this.groupsView=groupsFragment;
    }

    private void getHeader() {
        handler.execute(getSenderInformation, new GetSenderInformation.RequestValues(personaId), new UseCase.UseCaseCallback<GetSenderInformation.ResponseValue>() {
            @Override
            public void onSuccess(GetSenderInformation.ResponseValue response) {
                if(view!=null){
                    Log.d(TAG, "getHeader "+ response.getUserUi().getPhoto());
                    view.showHeader(response.getUserUi().getPhoto());
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getListContacts() {
        if(contacsView!=null)contacsView.showProgress();
        handler.execute(getContacts, new GetContacts.RequestValues(personaId), new UseCase.UseCaseCallback<GetContacts.ResponseValue>() {
            @Override
            public void onSuccess(GetContacts.ResponseValue response) {
                if(contacsView!=null){
                    Log.d(TAG, "getListContacts size "+ response.getObjects().size());
                    contacsView.hideProgress();
                    contacsView.setListContacts(response.getObjects());
                    objectListContacts=response.getObjects();
                }
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error list contacts");
            }
        });
    }



    @Override
    public void onResumenChatsList() {

    }

    @Override
    public void onResumContactsList() {
        Log.d(TAG, "onResumContactsList ");
        getListContacts();
    }

    @Override
    public void onPageChanged(Class<? extends Fragment> aClass) {
        if (aClass == null) return;
        Log.d(TAG, "fragmentClassVisible "+fragmentClassVisible);
        fragmentClassVisible = aClass;
        if(view==null)return;
        if(fragmentClassVisible == ChatsFragment.class){
            // view.hideButtonContact();
        }
        else if(fragmentClassVisible== GroupsFragment.class){
           // view.hideButtonContact();
            view.hideSearch();
        }
        //else
          //  view.showButtonContact();
    }

    @Override
    public void search(String toString) {
        Log.d(TAG, "fragmentClassVisible "+fragmentClassVisible);
        if(fragmentClassVisible==ChatsFragment.class){
            if(chatsView!=null)chatsView.searhChats(toString);
        }
        else if(fragmentClassVisible== ContactsFragment.class){
            if(contacsView!=null)contacsView.searhContacts(toString);
        }


    }

    @Override
    public void setListOld() {
        if(fragmentClassVisible==ChatsFragment.class){
            if(chatsView!=null)chatsView.setListChats(chatUiListAll);
        }
        else if(fragmentClassVisible== ContactsFragment.class){
            if(contacsView!=null)contacsView.setOldListContacts();
        }
    }

    @Override
    public void showListFilter(ContactUi.TypeContact type) {
        List<Object>objects= new ArrayList<>();
        for(Object object: objectListContacts){
            if(object instanceof ContactUi){
                ContactUi contactUi=(ContactUi)object;
                if(contactUi.getTypeContact()==type)objects.add(object);
            }

        }
        int typeInt;
        Log.d(TAG, "showListTeacher type "+ type);
        if(type== ContactUi.TypeContact.STUDENT)typeInt=1;
        else if(type== ContactUi.TypeContact.EXECUTIVE)typeInt=2;
        else typeInt=3;

       if(view!=null)view.sendListContacts(objects, typeInt);
    }

    @Override
    public void onGroupsFragmentViewDestroyed() {
        this.groupsView=null;
    }


    @Override
    public void clickTextSearch() {
        if(fragmentClassVisible==GroupsFragment.class){
            Log.d(TAG, "clickTextSearch  ");
        }

    }

    @Override
    public void valideFragmentSearch() {
        if(fragmentClassVisible==GroupsFragment.class){
           updateListGroups();
        }else {
            if(view!=null)view.showSearchDefault();
        }
    }

    @Override
    public void activityCreatedContactsFragment() {
        getListContacts();
    }

    @Override
    public void onClickChatContacto(ContactUi contactUi) {
        int personaExternaId = contactUi.getIdPerson();
        if(view!=null)view.showChatPersonal(personaId, personaExternaId);
    }

    @Override
    public void activityChatsFragment() {
        //getListChats(TYPE_SENDER);
        getListAllChats();
    }

    @Override
    public void onClickChat(ChatUi chatUi) {
        
        switch (chatUi.getTypeChat()){
            case GROUP:
                if(view!=null)view.showChatGrupal(personaId,chatUi);
                break;
            case PERSONAL:
                int personaExternaId = 0;
                personaExternaId = chatUi.getIdReceiver();
                if(personaExternaId==personaId){
                    personaExternaId = chatUi.getIdSender();
                }

                Log.d(TAG, "personaExternaId: " + personaExternaId);
                Log.d(TAG, "personaId: " + personaId);
                if(view!=null)view.showChatPersonal(personaId, personaExternaId);
                break;
        }
        
    }

    @Override
    public void activityGroupsFragment() {
        if(groupsView!=null)groupsView.setList(objectListGroups);
    }

    @Override
    public void onRefreshChats() {
        chatUiListAll.clear();
        getListAllChats();
    }

    private void getListAllChats() {
        Log.d(TAG, "getListChats ");
        if(chatsView!=null)chatsView.showProgress();
        if(retrofitCancel!=null)retrofitCancel.cancel();
        for(ListenerFirebase listenerFirebase : listenerFirebaseList)listenerFirebase.onStop();
        listenerFirebaseList.clear();
        listenerFirebaseList.addAll(getAllChats.execute(personaId, empleadoId, new GetAllChats.Callback() {
            @Override
            public void onSuccess(List<ChatUi> chatUiList) {
                Iterator<ChatUi> iteratorBand = chatUiListAll.iterator();
                while (iteratorBand.hasNext()) {
                    ChatUi chatUi1 = iteratorBand.next();
                    for (ChatUi chatUi2 : chatUiList) {
                        if (chatUi1.getId().equals(chatUi2.getId())) {
                            iteratorBand.remove();
                            Log.d(getTag(), "Elminar");
                        }
                    }
                }

                chatUiListAll.addAll(chatUiList);

                Collections.sort(chatUiListAll, new Comparator<ChatUi>() {
                    public int compare(ChatUi obj1, ChatUi obj2) {
                        return obj2.getLastDate().compareTo(obj1.getLastDate());
                    }

                });

                Log.d(getTag(), "lista3: " + chatUiListAll.size());
                retrofitCancel = getDatosChatAndUpdatePersonaGrupo.execute(personaId, chatUiListAll, new GetDatosChatAndUpdatePersonaGrupo.Callback() {
                    @Override
                    public void onSuccess(List<ChatUi> item) {
                        if (chatsView != null) chatsView.hideProgress();
                        Log.d(getTag(), "lista4: " + item.size());
                        if (chatsView != null) chatsView.setListChats(item);
                    }

                    @Override
                    public void onError() {
                        Log.d(getTag(), "getListAllChats error");
                    }
                });



            }

            @Override
            public void onError() {
                if (chatsView != null) chatsView.hideProgress();
                Log.d(TAG, "Error list chats");
            }
        }));

    }

    private void updateListGroups() {

    handler.execute(getListFilterGroups, new GetListFilterGroups.RequestValues(personaId), new UseCase.UseCaseCallback<GetListFilterGroups.ResponseValue>() {
        @Override
        public void onSuccess(GetListFilterGroups.ResponseValue response) {
            Log.d(TAG, "response filter size  "+ response.getObjects().size());
            if(view!=null)view.setListFilterGroups(response.getObjects(), objectListGroups);
        }

        @Override
        public void onError() {
            Log.d(TAG, "updateListGroups onError ");
        }
    });

    }

    @Override
    public void onChatsFragmentViewDestroyed() {
        this.chatsView=null;
    }

    @Override
    public void onContactsFragmentViewDestroyed() {
        this.contacsView=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(retrofitCancel!=null)retrofitCancel.cancel();
        for(ListenerFirebase listenerFirebase : listenerFirebaseList)listenerFirebase.onStop();
        listenerFirebaseList.clear();
    }
}
