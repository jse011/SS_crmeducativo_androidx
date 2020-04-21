package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 02/08/2017.
 */

public class CanalDestEstadoR {
    public int IdServer;

    public long IdAndroid;

    public CanalDestEstadoR() {
    }

    public CanalDestEstadoR(int idServer, long idAndroid) {
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
