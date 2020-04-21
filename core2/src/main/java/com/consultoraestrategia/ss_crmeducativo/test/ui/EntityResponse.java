package com.consultoraestrategia.ss_crmeducativo.test.ui;

/**
 * Created by @stevecampos on 5/01/2018.
 */

public class EntityResponse {
    public int IdServer;
    public int IdAndroid;

    public EntityResponse() {
    }

    public int getIdServer() {
        return IdServer;
    }

    public void setIdServer(int idServer) {
        IdServer = idServer;
    }

    public int getIdAndroid() {
        return IdAndroid;
    }

    public void setIdAndroid(int idAndroid) {
        IdAndroid = idAndroid;
    }

    @Override
    public String toString() {
        return "EntityResponse{" +
                "IdServer=" + IdServer +
                ", IdAndroid=" + IdAndroid +
                '}';
    }
}
