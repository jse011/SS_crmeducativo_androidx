package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 02/08/2017.
 */

public class BERespuestaEvaluacionResultado {
    public int IdServer;
    public int IdAndroid;

    public BERespuestaEvaluacionResultado() {
    }

    public BERespuestaEvaluacionResultado(int idServer, int idAndroid) {
        IdServer = idServer;
        IdAndroid = idAndroid;
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
        return "BERespuestaEvaluacionResultado{" +
                "IdServer=" + IdServer +
                ", IdAndroid=" + IdAndroid +
                '}';
    }
}
