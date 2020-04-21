package com.consultoraestrategia.ss_crmeducativo.entities;

import java.util.List;

/**
 * Created by irvinmarin on 02/08/2017.
 */

public class BERespuestaMensaje {
    public int IdServer;

    public long IdAndroid;

    List<MensajeUsuarioR> MensajeUsuarioR;

    List<MensajeIntItemR> MensajeIntItem;


    public BERespuestaMensaje() {
    }

    public BERespuestaMensaje(int idServer, long idAndroid) {
        IdServer = idServer;
        IdAndroid = idAndroid;
    }

    public List<com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioR> getMensajeUsuarioR() {
        return MensajeUsuarioR;
    }

    public void setMensajeUsuarioR(List<com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioR> mensajeUsuarioR) {
        MensajeUsuarioR = mensajeUsuarioR;
    }

    public List<MensajeIntItemR> getMensajeIntItem() {
        return MensajeIntItem;
    }

    public void setMensajeIntItem(List<MensajeIntItemR> mensajeIntItem) {
        MensajeIntItem = mensajeIntItem;
    }

    public int getIdServer() {
        return IdServer;
    }

    public void setIdServer(int idServer) {
        IdServer = idServer;
    }

    public long getIdAndroid() {
        return IdAndroid;
    }

    public void setIdAndroid(long idAndroid) {
        IdAndroid = idAndroid;
    }
}
