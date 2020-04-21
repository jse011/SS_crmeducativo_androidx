package com.consultoraestrategia.ss_crmeducativo.chatGrupal;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.SalaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.ChangeMessageEstadoEliminado;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.ClipboardMessage;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.GetListLastMessage;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.GetListMessage;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.GetSala;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.GetTokensSala;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.SaveImagenMessage;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.SaveMessage;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.SendNotificacion;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase.UpdateTokenSala;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ChatPresenterImpl extends BasePresenterImpl<ChatView> implements ChatPresenter {
    private final GetSala getSala;
    private final SendNotificacion sendNotificacion;
    private PersonaUi personaUi;
    private GetPersona getPersona;
    private SaveMessage saveMessage;
    private GetListMessage getListMessage;
    private GetListLastMessage getListLastMessage;
    private List<Object> mensajesList = new ArrayList<>();
    private Date lastDateMessage;
    private ListenerFirebase listenerFirebase;
    private int lastVisibleItem;
    private boolean emoticon;
    private boolean startActivity;
    private int countMessage = 0;
    private TipoSalaEnum tipo;
    private SalaUi salaUi;
    private int cargaCursoId;
    private int cargaAcademicaId;
    private String grupoEquipoId;
    private int personaId;
    private List<Long> docenteId;
    private String salaId = "";
    private GetTokensSala getTokensSala;
    private List<String> tokens = new ArrayList<>();
    private UpdateTokenSala updateTokenSala;
    private MessageUi2 replick;
    private SaveImagenMessage saveImagenMessage;
    private boolean toolbarSelection;
    private int countSeleccionado;
    private MessageUi2 selectedLongMessageUi;
    private ChangeMessageEstadoEliminado changeMessageEstadoEliminado;
    private ClipboardMessage clipboardMessage;
    private boolean diferenteMessageSelected;

    ChatPresenterImpl(UseCaseHandler handler, Resources res, GetPersona getPersona, SaveMessage saveMessage, GetListMessage getListMessage, GetListLastMessage getListLastMessage,
                      GetSala getSala, SendNotificacion sendNotificacion, GetTokensSala getTokensSala, UpdateTokenSala updateTokenSala, SaveImagenMessage saveImagenMessage,
                      ChangeMessageEstadoEliminado changeMessageEstadoEliminado, ClipboardMessage clipboardMessage) {
        super(handler, res);
        this.getPersona = getPersona;
        this.saveMessage = saveMessage;
        this.getListMessage = getListMessage;
        this.getListLastMessage = getListLastMessage;
        this.getSala = getSala;
        this.sendNotificacion = sendNotificacion;
        this.getTokensSala = getTokensSala;
        this.updateTokenSala = updateTokenSala;
        this.saveImagenMessage = saveImagenMessage;
        this.changeMessageEstadoEliminado = changeMessageEstadoEliminado;
        this.clipboardMessage = clipboardMessage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(tipo==null)if(view!=null)view.finishActivty();
        personaUi = getPersona.execute(personaId);
        salaUi = getSala.execute(cargaAcademicaId, cargaCursoId, grupoEquipoId, tipo);
        mensajesList.clear();
        setCabecera();
        getMessage(true);
        getTokensSala();
        updateTokenSala.execute(salaId, String.valueOf(personaId));
    }

    private void getTokensSala() {
        getTokensSala.execute(salaId, new GetTokensSala.Callback() {
            @Override
            public void onSucces(List<String> tokens) {
                ChatPresenterImpl.this.tokens.clear();
                ChatPresenterImpl.this.tokens.addAll(tokens);
            }
            @Override
            public void onError() {

            }
        });
    }

    private void getMessage(final boolean  scrollToPositionBotton){
        showProgress();
        if(listenerFirebase!=null)listenerFirebase.onStop();
        listenerFirebase = getListMessage.execute(salaId, personaId, new GetListMessage.Callback() {
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
                            if(messageUi1.getId().equals(messageUi2.getId())||(messageUi1.getEmisorId()== personaId && messageUi2.getEmisorId()== personaId
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
                        if(add.getEmisorId()!= personaId){
                            countMessage ++;
                        }
                    }
                    mensajesList = new ArrayList<Object>(list);
                }

                if(change){
                    if(lasPosition){
                        if (view != null) view.setListMessage(mensajesList, personaId);
                        if(view!=null)view.scrollToPositionBotton();
                        countMessage=0;
                        if(view!=null)view.hideConutMessage();
                    }else {
                        if(countMessage>0)if(view!=null)view.showConutMessage(countMessage);
                        if (view != null) view.setListMessage(mensajesList, personaId);
                    }
                    notificarFirebase();
                }

            }

            @Override
            public void onSuccess(List<Object> response) {
                List<MessageUi2> addList = new ArrayList<>();
                for (Object o1: response){
                    if(o1 instanceof MessageUi2){
                        MessageUi2 messageUi1 = (MessageUi2)o1;
                        if(lastDateMessage!=null&&messageUi1.getFecha().compareTo(lastDateMessage)<=0)continue;

                        int posicion = 0;
                        for (Object o : mensajesList){
                            if (o instanceof MessageUi2) {
                                MessageUi2 messageUi2 = (MessageUi2) o;
                                if(messageUi1.getId().equals(messageUi2.getId())||(messageUi1.getEmisorId()== personaId && messageUi2.getEmisorId()== personaId
                                        &&messageUi1.getMensaje().equals(messageUi2.getMensaje())
                                        &&messageUi1.getFecha().equals(messageUi2.getFecha()))){
                                    mensajesList.set(posicion, messageUi1);
                                    break;
                                }
                            }
                            posicion++;
                        }

                        if(posicion==mensajesList.size()){
                            addList.add(messageUi1);
                        }
                    }
                }

                if(!addList.isEmpty()){
                    Set<Object> list = new LinkedHashSet<>(mensajesList);
                    for (MessageUi2 add : addList){
                        list.add(Utils.getDateChat(add.getFecha()));
                        list.add(add);
                        if(add.getEmisorId()!= personaId){
                            countMessage ++;
                        }
                    }
                    mensajesList = new ArrayList<Object>(list);
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
                if (view != null) view.setListMessage(mensajesList, personaId);
            }
        });
    }

    private void notificarFirebase(){
       List<MessageUi2> messageUi2List = new ArrayList<>();
        for (Object o: mensajesList){
            if(o instanceof MessageUi2){
                MessageUi2 messageUi2 = (MessageUi2)o;
                if(messageUi2.getEnviarNotificacion()){
                    messageUi2.setEnviarNotificacion(false);
                    messageUi2List.add(messageUi2);
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
            notification.setNombreGrupo(salaUi.getNombre());
            notification.setAliasGrupo(salaUi.getAlias());
            notification.setColorGrupo(salaUi.getColor());
            notification.setDataTime(notification.getFecha().getTime());
            sendNotificacion.execute(notification,tokens,new SendNotificacion.CallBack() {
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

    private void setCabecera() {
        String nombre = salaUi!=null?salaUi.getNombre():"desconocido";
        String descripcion = salaUi!=null?salaUi.getDescripcion():"";
        String alias = salaUi!=null?salaUi.getAlias():"";
        String color = salaUi!=null?salaUi.getColor():"";
        if(view!=null)view.setCabecera(nombre, descripcion, alias, color, tipo);
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

        cargaCursoId = extras.getInt("cargaCursoId");
        cargaAcademicaId = extras.getInt("cargaAcademicaId");
        grupoEquipoId = extras.getString("grupoEquipoId");
        docenteId = (ArrayList<Long>)extras.get("docenteId");
        personaId = extras.getInt("personaId");

        int tipo_sala = extras.getInt("tipo_sala");
        int nivel_sala = extras.getInt("nivel_sala");

        Log.d(getTag(), "cargaCursoId: " + cargaCursoId);
        Log.d(getTag(), "cargaAcademicaId: " + cargaAcademicaId);
        Log.d(getTag(), "grupoEquipoId: " + grupoEquipoId);
        Log.d(getTag(), "personaId: " + personaId);
        Log.d(getTag(), "tipo_sala: " + tipo_sala);
        Log.d(getTag(), "nivel_sala: " + nivel_sala);

        if(tipo_sala== ChatGrupalActivity.TIPO_CLASSROON && nivel_sala== ChatGrupalActivity.NIVEL_GENERAL){
            tipo = TipoSalaEnum.SALON_GENERAL;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_CLASSROON && nivel_sala== ChatGrupalActivity.NIVEL_PADRES){
            tipo = TipoSalaEnum.SALON_PADRES;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_CLASSROON && nivel_sala== ChatGrupalActivity.NIVEL_ALUMNO){
            tipo = TipoSalaEnum.SALON_ALUMNOS;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_COURSE && nivel_sala== ChatGrupalActivity.NIVEL_GENERAL){
            tipo = TipoSalaEnum.CURSO_GENERAL;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_COURSE && nivel_sala== ChatGrupalActivity.NIVEL_PADRES){
            tipo = TipoSalaEnum.CURSO_PADRES;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_COURSE && nivel_sala== ChatGrupalActivity.NIVEL_ALUMNO){
            tipo = TipoSalaEnum.CURSO_ALUMNOS;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_TEAM && nivel_sala== ChatGrupalActivity.NIVEL_GENERAL){
            tipo = TipoSalaEnum.LISTA_GENERAL;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_TEAM && nivel_sala== ChatGrupalActivity.NIVEL_PADRES){
            tipo = TipoSalaEnum.LISTA_PADRES;
        }else if(tipo_sala== ChatGrupalActivity.TIPO_TEAM && nivel_sala== ChatGrupalActivity.NIVEL_ALUMNO){
            tipo = TipoSalaEnum.LISTA_ALUMNOS;
        }

        switch (tipo){
            case SALON_GENERAL:
            case SALON_PADRES:
            case SALON_ALUMNOS:
                salaId = tipo.getNombre()+ "_" +cargaAcademicaId;
                break;
            case CURSO_GENERAL:
            case CURSO_PADRES:
            case CURSO_ALUMNOS:
                salaId = tipo.getNombre()+ "_" +cargaCursoId;
                break;
            case LISTA_GENERAL:
            case LISTA_PADRES:
            case LISTA_ALUMNOS:
                salaId = tipo.getNombre()+ "_" +grupoEquipoId;
                break;
        }
        Log.d(getTag(), "tipo: " + tipo.getNombre());
        Log.d(getTag(), "salaId: " + salaId);
        if(view!=null)view.setConstantSalaId(salaId);
    }

    @Override
    public void onClickSend(String text) {

        if(TextUtils.isEmpty(text)){
            if(view!=null)view.showMessage("Escribe un mensaje");
            return;
        }
        if(view!=null)view.clearSend();

        MessageUi2 messageUi2 = getSaveMessage(text, null,replick);
        replick = null;
        if(view!=null)view.hideAnclarMessage();
        Set<Object> list = new LinkedHashSet<>(mensajesList);
        list.add(Utils.getDateChat(messageUi2.getFecha()));
        list.add(messageUi2);
        mensajesList = new ArrayList<Object>(list);
        if(view!=null)view.addMessage(mensajesList);

        saveMessage.execute(messageUi2, new SaveMessage.Callback(){
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
        messageUi.setNombreEmisor(personaUi!=null?personaUi.getNombre():"desconocido");
        messageUi.setReceptorId(0);
        messageUi.setSalaId(salaId);
        messageUi.setSalaTipo(tipo.getNombre());
        messageUi.setCargaAcademicaId(cargaAcademicaId);
        messageUi.setCargaCursoId(cargaCursoId);
        messageUi.setGrupoEquipoId(grupoEquipoId);
        messageUi.setEstado(MessageUi2.ESTADO.CREADO);
        messageUi.setDocenteId(new ArrayList<Long>(new LinkedHashSet<Long>(docenteId)));
        messageUi.setMensaje(text);
        messageUi.setTipo(TextUtils.isEmpty(imagen)? MessageUi2.TIPO.TEXTO: MessageUi2.TIPO.IMAGEN);
        messageUi.setImagen(imagen);
        Set<String> reference = new LinkedHashSet<>();
        for (Long integer : docenteId){
            reference.add("docente_"+ integer);
        }
        reference.add("carga_"+ cargaAcademicaId);
        reference.add(salaId);
        messageUi.setReferencia(new ArrayList<String>(reference));

        if(replick!=null){

            PersonaUi personaUi = replick.getPersonaUi();
            if(personaUi!=null){
                messageUi.setPersonaReplick(personaUi.getNombre());
            }else {
                messageUi.setPersonaReplick(replick.getNombreEmisor());
            }

            messageUi.setImagenReplick(replick.getImagenFcm());
            messageUi.setMensajeReplickId(replick.getId());
            messageUi.setMensajeReplick(replick.getMensaje());
        }

        return messageUi;
    }

    @Override
    public void onResume() {
        super.onResume();
        emoticon = false;
        setEmoticon(emoticon);
        if(view!=null)view.setConstantSalaId(salaId);
        /*if(startActivity) getMessage(false);
        startActivity= true;*/
    }

    private void setEmoticon(boolean emoticon){
        if(emoticon){
            if(view!=null)view.changeBtnIconTeclado();
        }else {
            if (view != null) view.changeBtnIconEmoticon();
        }
    }

    @Override
    public void onRefresh() {
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

    private void getMoreMessage() {
        if(lastDateMessage== null){
            hideProgress();
            return;
        }
        showProgress();
        getListLastMessage.execute(salaId, personaId, lastDateMessage, new GetListLastMessage.Callback() {
            @Override
            public void onSuccess(List<Object> response) {
                List<MessageUi2> addList = new ArrayList<>();
                for (Object o1: response){
                    if(o1 instanceof MessageUi2){
                        MessageUi2 messageUi1 = (MessageUi2)o1;
                        if(lastDateMessage!=null&&messageUi1.getFecha().compareTo(lastDateMessage)<=0)continue;

                        int posicion = 0;
                        for (Object o : mensajesList){
                            if (o instanceof MessageUi2) {
                                MessageUi2 messageUi2 = (MessageUi2) o;
                                if(messageUi1.getId().equals(messageUi2.getId())||(messageUi1.getEmisorId()== personaId && messageUi2.getEmisorId()== personaId
                                        &&messageUi1.getMensaje().equals(messageUi2.getMensaje())
                                        &&messageUi1.getFecha().equals(messageUi2.getFecha()))){
                                    mensajesList.set(posicion, messageUi1);
                                    break;
                                }
                            }
                            posicion++;
                        }

                        if(posicion==mensajesList.size()){
                            addList.add(messageUi1);
                        }
                    }
                }

                if(!addList.isEmpty()){
                    Set<Object> list = new LinkedHashSet<>(mensajesList);
                    for (MessageUi2 add : addList){
                        list.add(Utils.getDateChat(add.getFecha()));
                        list.add(add);
                        if(add.getEmisorId()!= personaId){
                            countMessage ++;
                        }
                    }
                    mensajesList = new ArrayList<Object>(list);
                }

                int size= response.size();
                if(size!=0) {
                    Object object=response.get(1);
                    if(object instanceof MessageUi2) lastDateMessage = ((MessageUi2)object).getFecha();
                }
                Log.d(getTag(), "lastDateMessage"+ lastDateMessage);

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
    public void onStop() {
        super.onStop();
        if(view!=null)view.setConstantSalaId(null);
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
    public void onKeyboardClose() {
        emoticon = false;
        setEmoticon(emoticon);
    }

    @Override
    public void onSeleccionarMessage(MessageUi2 messageUi2) {
        this.replick = messageUi2;

        MessageUi2 clone = new MessageUi2();


        if(messageUi2.getEmisorId()==personaId){
            clone.setPersonaReplick("Tú");
        }else {
            PersonaUi personaUi = messageUi2.getPersonaUi();
            if(personaUi!=null){
                clone.setPersonaReplick(personaUi.getNombre());
            }else {
                clone.setPersonaReplick(messageUi2.getNombreEmisor());
            }
        }

        clone.setImagenReplick(replick.getImagenFcm());
        clone.setMensajeReplickId(replick.getId());
        clone.setMensajeReplick(replick.getMensaje());

        if(view!=null)view.showAnclarMessage(clone);

    }

    @Override
    public void onClickedBtnCloseAnclar() {
        if(view!=null)view.hideAnclarMessage();
        replick = null;
    }

    @Override
    public void onClickedBtnImagen() {
        if(view!=null)view.showOnPickImage();
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

    @Override
    public void onClickPickImage() {
        if(view!=null)view.showCameraPreview(salaUi);
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

    @Override
    public void onClickDelete() {
        String mensaje = "";
        if(selectedLongMessageUi!=null){
            if(selectedLongMessageUi.getEmisorId()==personaId){
                mensaje = "¿Eliminar este mensaje?";
            }else {
                PersonaUi personaUi = selectedLongMessageUi.getPersonaUi();
                if(personaUi!=null){
                    mensaje = "¿Eliminar el mensaje de "+personaUi.getNombre()+" ?";
                }else {
                    mensaje = "¿Eliminar este mensaje?";
                }
            }
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

    private void changeToolbarSelection() {
        if(toolbarSelection){
            if(view!=null)view.changeToolbarSelection();
        }else {
            if(view!=null)view.changeToolbarNormal();
        }
    }
}
