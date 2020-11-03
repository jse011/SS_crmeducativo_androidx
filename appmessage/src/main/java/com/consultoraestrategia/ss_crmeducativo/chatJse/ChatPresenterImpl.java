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
    private String nombreChat;
    private String fotoChat;

    ChatPresenterImpl(UseCaseHandler handler, Resources res, GetPersona getPersona, SaveMessage saveMessage, GetListMessage getListMessage, GetListLastMessage getListLastMessage,
                      GetTokenSala getTokenSala, SendNotificacion sendNotificacion, ClipboardMessage clipboardMessage, ChangeMessageEstadoEliminado changeMessageEstadoEliminado,
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
        emoticon = false;
        personaUi = getPersona.execute(personaId);
        personaUiExterna = getPersona.execute(personaExternaId);
        mensajesList.clear();
        setCabecera();
        getMessage(true);
    }


    public void _onRecivedMessage(List<MessageUi2> messageUis) {
        boolean lasPosition = false;
        if((mensajesList.size()-2)<= lastVisibleItem){
            lasPosition = true;
        }

        boolean change = false;
        Set<MessageUi2> addList = new LinkedHashSet<>();
        for (MessageUi2 messageUi1 : messageUis){

            //if(lastDateMessage!=null&&messageUi1.getFecha().compareTo(lastDateMessage)<=0)continue;

            boolean nuevoMensaje = true;
            int posicion = 0;
            for (Object o : mensajesList){
                if (o instanceof MessageUi2) {
                    MessageUi2 messageUi2 = (MessageUi2) o;
                    if(messageUi1.getId().equals(messageUi2.getId())){
                        if(!messageUi1.equals(messageUi2)){
                            mensajesList.set(posicion, messageUi1);
                            if (view != null) view.updateListPosition(posicion, messageUi1);
                            change = true;
                        }
                        nuevoMensaje = false;
                        break;
                    }
                }
                posicion++;
            }

            if(nuevoMensaje){
                addList.add(messageUi1);
            }
        }

        if(!addList.isEmpty()){
            for (MessageUi2 add : addList){

                if(add.getFecha()==null){
                    add.setFecha(new Date());
                    add.setEstado(MessageUi2.ESTADO.CREADO);
                }
                String fecha = Utils.getDateChat(add.getFecha());
                int position = mensajesList.indexOf(fecha);
                if(position==-1){
                    mensajesList.add(fecha);
                    if(view!=null)view.addList(fecha);
                }
                mensajesList.add(add);
                if(view!=null)view.addList(add);
                change = true;

                if(add.getEmisorId()!=personaId){
                    countMessage ++;
                }
            }
        }

        if(change){
            if(lasPosition){
                Log.d(getTag(), "ChatActivityTAG getMessage onRecivedMessage lasPosition");
                //if (view != null) view.setListMessage(mensajesList, personaId, false);
                if(view!=null)view.scrollToPositionBotton(true);
                countMessage=0;
                if(view!=null)view.hideConutMessage();
            }else {
                Log.d(getTag(), "ChatActivityTAG getMessage onRecivedMessage");
                if(countMessage>0)if(view!=null)view.showConutMessage(countMessage);
                if(view!=null)view.showFloatingButton();
                //if (view != null) view.setListMessage(mensajesList, personaId, true);
            }
            notificarFirebase();
        }


    }

    private void getMessage(final boolean  scrollToPositionBotton){
        showProgress();
        if(listenerFirebase!=null)listenerFirebase.onStop();
        listenerFirebase = getListMessage.execute(personaId, personaExternaId, new GetListMessage.Callback() {
            @Override
            public void onRecivedMessage(List<MessageUi2> messageUis) {
                _onRecivedMessage(messageUis);
            }

            @Override
            public void onSuccess(List<Object> response) {
                Log.d(getTag(), "ChatActivityTAG getMessage onSuccess Init");

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

                if(scrollToPositionBotton){
                    if (view != null) view.setListMessage(new ArrayList<Object>(list), personaId, false);
                    if (view != null)view.scrollToPositionBotton(false);
                }else {
                    if (view != null) view.setListMessage(new ArrayList<Object>(list), personaId, true);
                }
                hideProgress();

                if(view!=null)view.onInitListener();
            }

            @Override
            public void onError() {
                hideProgress();
                Log.d(getTag(), "onError");
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
        this.nombreChat = extras.getString("nombreChat");
        this.fotoChat = extras.getString("fotoChat");

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

        MessageUi2 messageUi = getSaveMessage(text, null, null,replick);

        replick = null;
        if(view!=null)view.hideAnclarMessage();


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
        if(view!=null)view.scrollToPositionBotton(true);
    }

    @Override
    public void onKeyboardOpens(int lastVisibleItem) {
        Log.d(getTag(), "onKeyboardOpens");
        Log.d(getTag(), "lastVisibleItem: " + lastVisibleItem);
        Log.d(getTag(), "mensajesList.size(): " + mensajesList.size());
        if((mensajesList.size()-2)<=lastVisibleItem||lastVisibleItem==-1){
            if(view!=null)view.scrollToPositionBotton(true);
        }
    }


    @Override
    public void onBtnEmoticonClicked() {
        emoticon=!emoticon;
        if(emoticon){
            if(view!=null)view.showEmoticon();
            //if(view!=null)view.changeBtnIconTeclado();
        }else {
            if(view!=null)view.showTeclado();
            //if(view!=null)view.changeBtnIconEmoticon();
        }
    }


    @Override
    public void onKeyboardClose() {

    }

    private void getMoreMessage() {
        if(lastDateMessage== null){
            hideProgress();
            return;
        }
        showProgress();
        if(listenerFirebase!=null)listenerFirebase.onStop();
        listenerFirebase = getListLastMessage.execute(personaId, personaExternaId, lastDateMessage, new GetListLastMessage.Callback() {
            @Override
            public void onSuccess(List<Object> response) {
                MessageUi2 messageUiFocus = null;
                for (Object o : response){
                    if(o instanceof MessageUi2){
                        messageUiFocus = (MessageUi2)o;
                    }
                }
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
                if(view!=null)view.setListMessage(mensajesList, personaId, false);
                int position = mensajesList.indexOf(messageUiFocus);
                if(position!=-1){
                    int positionActual=position+2;
                    if(mensajesList.size()<positionActual){
                        if(view!=null)view.scrollToPosition(positionActual);
                    }else {
                        if(view!=null)view.scrollToPosition(position);
                    }

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

            @Override
            public void onRecivedMessage(List<MessageUi2> messageUis) {
                _onRecivedMessage(messageUis);
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
            //if(view!=null)view.changeBtnIconEmoticon();
        }
    }

    @Override
    public void onSeleccionarMessage(MessageUi2 messageUi2) {
        this.replick = messageUi2;

        if(view!=null)view.showAnclarMessage(messageUi2.getEmisorId(),personaId,(personaUiExterna!=null?personaUiExterna.getNombre():""),replick.getImagenFcm(), replick.getMensaje());
    }

    @Override
    public void onLongClick(MessageUi2 messageUi2) {
        seleccionar(messageUi2);
    }

    private void seleccionar(MessageUi2 messageUi2){
        if(messageUi2.getEstado()== MessageUi2.ESTADO.ELIMINADO)return;

        messageUi2.setSelected(!messageUi2.isSelected());
        if(view!=null)view.updateList(messageUi2);
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

        //if(view!=null)view.changeList();
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
                    item.setEstado(MessageUi2.ESTADO.ELIMINADO);
                    if(view!=null)view.updateList(item);
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
        int count = 0;
        for (String image : returnValue){
            MessageUi2 messageUi2;
            if(count==0){
                messageUi2 = getSaveMessage(descripcion, image,null,null);
            }else {
                messageUi2 = getSaveMessage("", image,null,null);
            }

            messageUi2List.add(messageUi2);
            count++;
        }

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

    @Override
    public void onSelectedSticker(String image) {
        if(TextUtils.isEmpty(image)){
            if(view!=null)view.showMessage("error");
            return;
        }
        if(view!=null)view.clearSend();

        MessageUi2 messageUi = getSaveMessage(null, null, image, replick);

        replick = null;
        if(view!=null)view.hideAnclarMessage();

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


    private MessageUi2 getSaveMessage(String text, String imagen, String sticker, MessageUi2 replick) {
        MessageUi2 messageUi = new MessageUi2();
        messageUi.setId("");
        //messageUi.setFecha(new Date());
        if(!TextUtils.isEmpty(nombreChat)){
            messageUi.setNameChat(nombreChat);
        }else {
            messageUi.setNameChat(!TextUtils.isEmpty(personaUiExterna.getNombre())?personaUiExterna.getNombre():null);
        }
        if(!TextUtils.isEmpty(fotoChat)){
            messageUi.setImagenChat(fotoChat);
        }else {
            messageUi.setImagenChat(!TextUtils.isEmpty(personaUiExterna.getFoto())?personaUiExterna.getFoto():null);
        }

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
            messageUi.setPersonaIdReplick(replick.getEmisorId());
            messageUi.setImagenReplick(replick.getImagenFcm());
            messageUi.setMensajeReplickId(replick.getId());
            messageUi.setMensajeReplick(replick.getMensaje());
        }

        if(!TextUtils.isEmpty(sticker)){
            messageUi.setTipo(MessageUi2.TIPO.STICKER);
            messageUi.setImagenFcm(sticker);
            messageUi.setMensaje("\uD83D\uDC9F️ Sticker");
        }else if(TextUtils.isEmpty(imagen)){
            messageUi.setTipo(MessageUi2.TIPO.TEXTO);
        }else {
            messageUi.setTipo(MessageUi2.TIPO.IMAGEN);
        }

        messageUi.setImagen(imagen);

        return messageUi;
    }

    private void clearSelection(){
        for(Object o : mensajesList){
            if(o instanceof MessageUi2){
                MessageUi2 item = ((MessageUi2)o);
                item.setSelected(false);
                if(view!=null)view.updateList(item);
            }
        }
        countSeleccionado = 0;
        toolbarSelection = false;
        changeToolbarSelection();
        //if(view!=null)view.changeList();
    }

    private void changeToolbarSelection() {
        if(toolbarSelection){
            if(view!=null)view.changeToolbarSelection();
        }else {
            if(view!=null)view.changeToolbarNormal();
        }
    }
}
