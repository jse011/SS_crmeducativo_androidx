package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 02/08/2017.
 */

public class MensajeIntItemR {
    public int IdServer;

    public long IdAndroid;

    public MensajeIntItemR() {
    }

    public MensajeIntItemR(int idServer, long idAndroid) {
        IdServer = idServer;
        IdAndroid = idAndroid;
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
