package com.consultoraestrategia.ss_crmeducativo.chatJse;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.ChangeMessageEstadoEliminado;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.ClipboardMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetListLastMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetListMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.GetTokenSala;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.SaveImagenMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.SaveMessage;
import com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase.SendNotificacion;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ChatPresenterImpl extends BasePresenterImpl<ChatView> implements ChatPresenter {
    private PersonaUi personaUi;
    private PersonaUi personaUiExterna;
    private GetPersona getPersona;
    private SaveMessage saveMessage;
    private GetListMessage getListMessage;
    private GetListLastMessage getListLastMessage;
    private List<Object> mensajesList = new ArrayList<>();
    private int personaId;
    private Date lastDateMessage;
    private int personaExternaId;
    private ListenerFirebase listenerFirebase;
    private int lastVisibleItem;
    private boolean emoticon;
    private boolean startActivity;
    private int countMessage = 0;
    private GetTokenSala getTokenSala;
    private String tokenFcm = null;
    private SendNotificacion sendNotificacion;
    private MessageUi2 replick;
    private boolean toolbarSelection;
    private boolean diferenteMessageSelected;
    private int countSeleccionado;
    private MessageUi2 selectedLongMessageUi;
    private ClipboardMessage clipboardMessage;
    private ChangeMessageEstadoEliminado changeMessageEstadoEliminado;
    private SaveImagenMessage saveImagenMessage;

    ChatPresenterImpl(UseCaseHandler handler, Resources res, GetPersona getPersona,SaveMessage saveMessage,GetListMessage getListMessage,GetListLastMessage getListLastMessage,
                      GetTokenSala getTokenSala, SendNotificacion sendNotificacion, ClipboardMessage clipboardMessage,ChangeMessageEstadoEliminado changeMessageEstadoEliminado,
                      SaveImagenMessage saveImagenMessage) {
        super(handler, res);
        this.getPersona = getPersona;
        this.saveMessage = saveMessage;
        this.getListMessage = getListMessage;
        this.getListLastMessage = getListLastMessage;
        this.getTokenSala = getTokenSala;
        this.sendNotificacion = sendNotificacion;
        this.clipboardMessage = clipboardMessage;
        this.changeMessageEstadoEliminado = changeMessageEstadoEliminado;
        this.saveImagenMessage = saveImagenMessage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        personaUi = getPersona.execute(personaId);
        personaUiExterna = getPersona.execute(personaExternaId);
        mensajesList.clear();
        setCabecera();
        getMessage(true);
    }

    private void getMessage(final boolean  scrollToPositionBotton){
        showProgress();
        if(listenerFirebase!=null)listenerFirebase.onStop();
        listenerFirebase = getListMessage.execute(mensajesList, personaId, personaExternaId, new GetListMessage.Callback() {
            @Override
            public void onRecivedMessage(List<MessageUi2> messageUis) {
                boolean lasPosition = false;
                if((mensajesList.size()-2)<= lastVisibleItem){
                    lasPosition = true;
                }

                boolean change = false;
                List<MessageUi2> addList = new ArrayList<>();
                for (MessageUi2 messageUi1 : messageUis){

                    if(lastDateMessage!=null&&messageUi1.getFecha().compareTo(lastDateMessage)<=0)continue;

                    int posicion = 0;
                    for (Object o : mensajesList){
                        if (o instanceof MessageUi2) {
                            MessageUi2 messageUi2 = (MessageUi2) o;
                            if(messageUi1.getId().equals(messageUi2.getId())||(messageUi1.getEmisorId()==personaId && messageUi2.getEmisorId()==personaId
                                    &&messageUi1.getMensaje().equals(messageUi2.getMensaje())
                                    &&messageUi1.getFecha().equals(messageUi2.getFecha()))){
                                mensajesList.set(posicion, messageUi1);
                                change = true;
                                break;
                            }
                        }
                        posicion++;
                    }

                    if(posicion==mensajesList.size()){
                       addList.add(messageUi1);
                        change = true;
                    }

                }

                if(!addList.isEmpty()){
                    Set<Object> list = new LinkedHashSet<>(mensajesList);
                    for (MessageUi2 add : addList){
                        list.add(Utils.getDateChat(add.getFecha()));
                        list.add(add);
                        if(add.getEmisorId()!=personaId){
                            countMessage ++;
                        }
                    }
                    mensajesList = new ArrayList<Object>(list);
                }

                if(change){
                    if(lasPosition){
                        Log.d(getTag(), "ChatActivityTAG getMessage onRecivedMessage lasPosition");
                        if (view != null) view.setListMessage(mensajesList, personaId);
                        if(view!=null)view.scrollToPositionBotton();
                        countMessage=0;
                        if(view!=null)view.hideConutMessage();
                    }else {
                        Log.d(getTag(), "ChatActivityTAG getMessage onRecivedMessage");
                        if(countMessage>0)if(view!=null)view.showConutMessage(countMessage);
                        if (view != null) view.setListMessage(mensajesList, personaId);
                    }
                    notificarFirebase();
                }

            }

            @Override
            public void onSuccess(List<Object> response) {
                Log.d(getTag(), "ChatActivityTAG getMessage onSuccess Init");

                Iterator<Object> iteratorBand = mensajesList.iterator();
                while(iteratorBand.hasNext()){
                    Object o1 = iteratorBand.next();
                    if (o1 instanceof MessageUi2) {
                        MessageUi2 messageUi1 = (MessageUi2) o1;
                        for (Object o2 : response) {
                            if (o2 instanceof MessageUi2) {
                                MessageUi2 messageUi2 = (MessageUi2) o2;
                                if (messageUi1.getId().equals(messageUi2.getId())||
                                        (messageUi1.getEmisorId()==messageUi2.getEmisorId()&&
                                         messageUi1.getFecha().equals(messageUi2.getFecha()) &&
                                        messageUi1.getMensaje().equals(messageUi2.getMensaje()))) {
                                    iteratorBand.remove();
                                    Log.d(getTag(), "Elminar");
                                }
                            }
                        }
                    }
                }

                int size = response.size();
                Log.d(getTag(), "response " + size);
                if (size != 0) {
                    Object object = response.get(1);
                    if (object instanceof MessageUi2){
                        lastDateMessage = ((MessageUi2) object).getFecha();
                    }
                    Log.d(getTag(), "id last message " + lastDateMessage);
                    //if(size==0&& !view.isInternetAvailable())view.showMessageNotInternet();
                }

                Set<Object> list = new LinkedHashSet<>(mensajesList);
                list.addAll(response);
                mensajesList = new ArrayList<Object>(list);
                notificarFirebase();
                Log.d(getTag(), "ChatActivityTAG getMessage onSuccess");
                if (view != null) view.setListMessage(new ArrayList<Object>(list), personaId);
                if(scrollToPositionBotton)if (view != null)view.scrollToPositionBotton();
                hideProgress();
            }

            @Override
            public void onError() {
                hideProgress();
                Log.d(getTag(), "onError");
            }

            @Override
            public void onChangeEstado() {
                Log.d(getTag(), "ChatActivityTAG getMessage onChangeEstado");
                if (view != null) view.setListMessage(mensajesList, personaId);
            }
        });
    }

    private void setCabecera() {
        String nombre = personaUiExterna!=null?personaUiExterna.getNombre():"desconocido";
        String descripcion = personaUiExterna!=null?personaUiExterna.getDescripcion():"";
        String url = personaUiExterna!=null?personaUiExterna.getFoto():"";
        if(view!=null)view.setCabecera(nombre, descripcion, url);
    }

    @Override
    protected String getTag() {
        return "ChatPresenterImpl";
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
        personaId=extras.getInt("personaId");
        personaExternaId=extras.getInt("personaExternaId");
        Log.d(getTag(), "personaExternaId: " + personaExternaId);
        Log.d(getTag(), "personaId: " + personaId);
        String salaId = "";
        if(personaId>personaExternaId){
            salaId = personaId+"_"+personaExternaId;
        }else {
            salaId = personaExternaId+"_"+personaId;
        }
        if(view!=null)view.setConstantSalaId(salaId);
        getTokenSala.execute(salaId, new GetTokenSala.Callback() {
            @Override
            public void onSucces(String tokens) {
                tokenFcm = tokens;
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onClickSend(String text) {

        if(TextUtils.isEmpty(text)){
            if(view!=null)view.showMessage("Escribe un mensaje");
            return;
        }
        if(view!=null)view.clearSend();

        MessageUi2 messageUi = getSaveMessage(text, null, replick);

        replick = null;
        if(view!=null)view.hideAnclarMessage();

        Set<Object> list = new LinkedHashSet<>(mensajesList);
        list.add(Utils.getDateChat(messageUi.getFecha()));
        list.add(messageUi);
        mensajesList = new ArrayList<Object>(list);
        if(view!=null)view.addMessage(mensajesList);



        saveMessage.execute(messageUi,new SaveMessage.Callback(){
            @Override
            public void onSuccess(MessageUi2 messageUi) {
                Log.d(getTag(), "Save message success");
            }

            @Override
            public void onError() {
                Log.d(getTag(), "");
                Log.d(getTag(), "Save message error");
            }
        });
    }

    private void notificarFirebase(){
        List<MessageUi2> messageUi2List = new ArrayList<>();
        MessageUi2 ultimo = null;
        for (Object o: mensajesList){
            if(o instanceof MessageUi2){
                MessageUi2 messageUi2 = (MessageUi2)o;
                if(messageUi2.getEnviarNotificacion()){
                    messageUi2.setEnviarNotificacion(false);
                    messageUi2List.add(messageUi2);
                }
                if(messageUi2.getReceptorId()==personaExternaId){
                    ultimo = messageUi2;
                }
            }
        }

        if(!messageUi2List.isEmpty()){
            Collections.reverse(messageUi2List);
            MessageUi2 notification = messageUi2List.get(0);

            List<String> mensajes = new ArrayList<>();
            if(messageUi2List.size()>1){
                int count = 0;
                for (MessageUi2 messageUi2 : messageUi2List){
                    if(count==2){
                        mensajes.add(messageUi2.getMensaje());
                    }
                    count++;
                }
            }else {
                mensajes.add(notification.getMensaje());
            }

            notification.setMensajes(mensajes);
            notification.setDataTime(notification.getFecha().getTime());
            sendNotificacion.execute(notification,ultimo!=null?ultimo.getTokenFcm():"",new SendNotificacion.CallBack() {
                @Override
                public void onSucces(MessageUi2 messageUi2) {
                    Log.d(getTag(), "onSucces");
                }

                @Override
                public void onError(MessageUi2 messageUi2) {
                    Log.d(getTag(), "onError");
                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        emoticon = false;
        setEmoticon(emoticon);
        String salaId = "";
        if(personaId>personaExternaId){
            salaId = personaId+"_"+personaExternaId;
        }else {
            salaId = personaExternaId+"_"+personaId;
        }
        if(view!=null)view.setConstantSalaId(salaId);
        /*if(startActivity) getMessage(false);
        startActivity= true;*/
    }

    @Override
    public void onStop() {
        super.onStop();

        if(view!=null)view.setConstantSalaId(null);
    }

    @Override
    public void onRefresh() {
        Log.d(getTag(), "onRefresh");
        getMoreMessage();
    }

    @Override
    public void changeFirsthPostion(int firstVisibleItem) {
        this.lastVisibleItem = firstVisibleItem;
        Log.d(getTag(), "lastVisibleItem: " + firstVisibleItem);
        Log.d(getTag(), "mensajesList.size(): " + mensajesList.size());

        if((mensajesList.size()-2)>firstVisibleItem){
            if(view!=null)view.showFloatingButton();
            if(countMessage>0)if(view!=null)view.showConutMessage(countMessage);
        }else {
            if(view!=null)view.hideFloatingButton();
            countMessage = 0;
            if(view!=null)view.hideConutMessage();
        }
    }

    @Override
    public void onBtnBajarClicked() {
        if(view!=null)view.scrollToPositionBotton();
    }

    @Override
    public void onKeyboardOpens(int lastVisibleItem) {
        Log.d(getTag(), "onKeyboardOpens");
        Log.d(getTag(), "lastVisibleItem: " + lastVisibleItem);
        Log.d(getTag(), "mensajesList.size(): " + mensajesList.size());
        if((mensajesList.size()-2)<=lastVisibleItem){
            if(view!=null)view.scrollToPositionBotton();
        }
    }


    @Override
    public void onBtnEmoticonClicked() {
        emoticon=!emoticon;
        if(emoticon){
            if(view!=null)view.showEmoticon();
            if(view!=null)view.changeBtnIconTeclado();
        }else {
            if(view!=null)view.showTeclado();
            if(view!=null)view.changeBtnIconEmoticon();
        }
    }

    private void setEmoticon(boolean emoticon){
        if(emoticon){
            if(view!=null)view.changeBtnIconTeclado();
        }else {
            if(view!=null)view.changeBtnIconEmoticon();
        }
    }
    @Override
    public void onKeyboardClose() {
        emoticon = false;
        setEmoticon(emoticon);
    }

    private void getMoreMessage() {
        if(lastDateMessage== null){
            hideProgress();
            return;
        }
        showProgress();
        getListLastMessage.execute(personaId, personaExternaId, lastDateMessage, new GetListLastMessage.Callback() {
            @Override
            public void onSuccess(List<Object> response) {
                Iterator<Object> iteratorBand = mensajesList.iterator();
                while(iteratorBand.hasNext()){
                    Object o1 = iteratorBand.next();
                    if (o1 instanceof MessageUi2) {
                        MessageUi2 messageUi1 = (MessageUi2) o1;
                        for (Object o2 : response) {
                            if (o2 instanceof MessageUi2) {
                                MessageUi2 messageUi2 = (MessageUi2) o2;
                                if (messageUi1.getId().equals(messageUi2.getId())||(messageUi1.getEmisorId()==messageUi2.getEmisorId()
                                        && messageUi1.getReceptorId()==messageUi2.getReceptorId() &&
                                        messageUi1.getFecha().equals(messageUi2.getFecha()) &&
                                        messageUi1.getMensaje().equals(messageUi2.getMensaje()))) {
                                    iteratorBand.remove();
                                    Log.d(getTag(), "Elminar");
                                }
                            }
                        }
                    }
                }
                int size= response.size();
                if(size!=0) {
                    Object object=response.get(1);
                    if(object instanceof MessageUi2) lastDateMessage = ((MessageUi2)object).getFecha();
                }
                Log.d(getTag(), "lastDateMessage"+ lastDateMessage);
                Log.d(getTag(), "ChatActivityTAG getMessage onRecivedMessage");
                Object objet = mensajesList.get(0);
                mensajesList.addAll(0, response);
                Set<Object> list = new LinkedHashSet<>(mensajesList);
                mensajesList= new ArrayList<>(list);
                if(view!=null)view.setListMessage(mensajesList, personaId);
                int position = mensajesList.indexOf(objet);
                if(position!=-1){
                    if(view!=null)view.scrollToPosition(position);
                }else {
                    if(view!=null)view.scrollToPosition(response.size());
                }
                notificarFirebase();
                hideProgress();
                Log.d(getTag(), "more messages  "+ response.size());

                //if(size==0&& !view.isInternetAvailable())view.showMessageNotInternet();
            }

            @Override
            public void onError() {
                Log.d(getTag(), "onError get more messages");
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(listenerFirebase!=null)listenerFirebase.onStop();
    }

    @Override
    public void onClickMsgListener() {
        if(emoticon){
            emoticon=false;
            if(view!=null)view.showTeclado();
            if(view!=null)view.changeBtnIconEmoticon();
        }
    }

    @Override
    public void onSeleccionarMessage(MessageUi2 messageUi2) {
        this.replick = messageUi2;

        MessageUi2 clone = new MessageUi2();
        if(messageUi2.getEmisorId()==personaId){
            clone.setPersonaReplick("Tú");
        }else {
            clone.setPersonaReplick(personaUiExterna!=null?personaUiExterna.getNombre():"");
        }

        clone.setImagenReplick(replick.getImagenFcm());
        clone.setMensajeReplickId(replick.getId());
        clone.setMensajeReplick(replick.getMensaje());

        if(view!=null)view.showAnclarMessage(clone);
    }

    @Override
    public void onLongClick(MessageUi2 messageUi2) {
        seleccionar(messageUi2);
    }

    private void seleccionar(MessageUi2 messageUi2){
        if(messageUi2.getEstado()== MessageUi2.ESTADO.ELIMINADO)return;

        messageUi2.setSelected(!messageUi2.isSelected());
        if(messageUi2.isSelected()){
            toolbarSelection = true;
            changeToolbarSelection();
            if(!diferenteMessageSelected) diferenteMessageSelected = personaId!=messageUi2.getEmisorId();
            countSeleccionado++;
        }else {
            int count = 0;
            diferenteMessageSelected = false;
            for(Object o : mensajesList){
                if(o instanceof MessageUi2){
                    MessageUi2 item = ((MessageUi2)o);
                    if(item.isSelected()){
                        if(!diferenteMessageSelected){
                            diferenteMessageSelected = personaId!=item.getEmisorId();
                        }
                        count++;
                    }
                }


            }
            countSeleccionado = count;
            if(count>0){
                toolbarSelection = true;
            }else {
                toolbarSelection = false;
            }

            changeToolbarSelection();
        }

        if(countSeleccionado==1&&!diferenteMessageSelected){
            this.selectedLongMessageUi = messageUi2;
            if(view!=null)view.showInfoMessage();
        }else {
            this.selectedLongMessageUi = null;
            if(view!=null)view.hideInfoMessage();
        }

        if(!diferenteMessageSelected){
            if(view!=null)view.showDeleteMessage();
        }else {
            if(view!=null)view.hideDeleteMessage();
        }

        if(view!=null)view.changeList();
        if(view!=null)view.setCountSelection(countSeleccionado);
        Log.d(getTag(), "Cou7nt "+ countSeleccionado);

    }

    @Override
    public void onClickMessage(MessageUi2 messageUi2) {
        if(toolbarSelection){
            seleccionar(messageUi2);
        }

    }

    @Override
    public void onClickNavigationHome() {
        clearSelection();
    }

    @Override
    public void onClickInfo() {

    }

    @Override
    public void onClickCopy() {
        List<MessageUi2> messageUiList = new ArrayList<>();
        for(Object o : mensajesList){
            if(o instanceof MessageUi2){
                MessageUi2 item = ((MessageUi2)o);
                if(item.isSelected()){
                    messageUiList.add(item);
                }
            }
        }
        if(!messageUiList.isEmpty())clipboardMessage.execute(messageUiList);
        clearSelection();
    }

    @Override
    public void onClickDelete() {
        String mensaje = "";
        if(selectedLongMessageUi!=null){
            mensaje = "¿Eliminar este mensaje?";
        }else {
            mensaje = "¿Eliminar "+countSeleccionado+" mensajes?";
        }

        if(view!=null)view.showAlertDelete(mensaje);
    }

    @Override
    public void onClickAcceptDelete() {
        List<MessageUi2> messageUiList = new ArrayList<>();
        for(Object o : mensajesList){
            if(o instanceof MessageUi2){
                MessageUi2 item = ((MessageUi2)o);
                if(item.isSelected()&&item.getEmisorId()==personaId){
                    messageUiList.add(item);
                }
            }
        }
        if(!messageUiList.isEmpty())changeMessageEstadoEliminado.excute(messageUiList);
        clearSelection();
    }

    @Override
    public void onClickedBtnCloseAnclar() {
        if(view!=null)view.hideAnclarMessage();
        replick = null;
    }

    @Override
    public void onClickedBtnImagen() {
        if(view!=null)view.showOnPickImage(personaUiExterna);
    }

    @Override
    public void onSeleccionList(ArrayList<String> returnValue, String descripcion) {
        List<MessageUi2> messageUi2List = new ArrayList<>();
        Set<Object> list = new LinkedHashSet<>(mensajesList);
        int count = 0;
        for (String image : returnValue){
            MessageUi2 messageUi2;
            if(count==0){
                messageUi2 = getSaveMessage(descripcion, image,null);
            }else {
                messageUi2 = getSaveMessage("", image,null);
            }

            list.add(Utils.getDateChat(messageUi2.getFecha()));
            list.add(messageUi2);
            messageUi2List.add(messageUi2);
            count++;
        }

        mensajesList = new ArrayList<Object>(list);
        if(view!=null)view.addMessage(mensajesList);

        saveImagenMessage.execute(messageUi2List, new SaveMessage.Callback(){
            @Override
            public void onSuccess(MessageUi2 messageUi) {
                Log.d(getTag(), "Save message success");
            }

            @Override
            public void onError() {
                Log.d(getTag(), "");
                Log.d(getTag(), "Save message error");
            }
        });
    }


    private MessageUi2 getSaveMessage(String text, String imagen ,  MessageUi2 replick) {
        MessageUi2 messageUi = new MessageUi2();
        messageUi.setId("");
        messageUi.setFecha(new Date());
        messageUi.setEmisorId(personaId);
        messageUi.setReceptorId(personaExternaId);
        messageUi.setEstado(MessageUi2.ESTADO.CREADO);
        messageUi.setMensaje(text);
        messageUi.setTokenFcm(tokenFcm);

        List<String> reference = new ArrayList<>();
        reference.add(String.valueOf(messageUi.getEmisorId()));
        reference.add(String.valueOf(messageUi.getReceptorId()));
        reference.add(messageUi.getReceptorId()+"_"+messageUi.getEmisorId());
        reference.add(messageUi.getEmisorId()+"_"+messageUi.getReceptorId());
        messageUi.setReferencia(reference);

        if(replick!=null){

            if(replick.getEmisorId()==personaId){
                messageUi.setPersonaReplick(personaUi!=null?personaUi.getNombre():"");
            }else {
                messageUi.setPersonaReplick(personaUiExterna!=null?personaUiExterna.getNombre():"");
            }

            messageUi.setImagenReplick(replick.getImagenFcm());
            messageUi.setMensajeReplickId(replick.getId());
            messageUi.setMensajeReplick(replick.getMensaje());
        }

        messageUi.setTipo(TextUtils.isEmpty(imagen)? MessageUi2.TIPO.TEXTO: MessageUi2.TIPO.IMAGEN);
        messageUi.setImagen(imagen);


        return messageUi;
    }

    private void clearSelection(){
        for(Object o : mensajesList){
            if(o instanceof MessageUi2){
                MessageUi2 item = ((MessageUi2)o);
                item.setSelected(false);
            }
        }
        countSeleccionado = 0;
        toolbarSelection = false;
        changeToolbarSelection();
        if(view!=null)view.changeList();
    }

    private void changeToolbarSelection() {
        if(toolbarSelection){
            if(view!=null)view.changeToolbarSelection();
        }else {
            if(view!=null)view.changeToolbarNormal();
        }
    }
}
