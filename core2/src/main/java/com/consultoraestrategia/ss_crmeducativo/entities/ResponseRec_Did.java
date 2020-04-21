package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 21/12/2017.
 */

public class ResponseRec_Did {
    private int idRecursoDidacticoAndroid;
    private int idRecursoDidacticoServer;

    public ResponseRec_Did() {
    }

    public ResponseRec_Did(int idRecursoDidacticoAndroid, int idRecursoDidacticoServer) {
        this.idRecursoDidacticoAndroid = idRecursoDidacticoAndroid;
        this.idRecursoDidacticoServer = idRecursoDidacticoServer;
    }

    public int getIdRecursoDidacticoAndroid() {
        return idRecursoDidacticoAndroid;
    }

    public void setIdRecursoDidacticoAndroid(int idRecursoDidacticoAndroid) {
        this.idRecursoDidacticoAndroid = idRecursoDidacticoAndroid;
    }

    public int getIdRecursoDidacticoServer() {
        return idRecursoDidacticoServer;
    }

    public void setIdRecursoDidacticoServer(int idRecursoDidacticoServer) {
        this.idRecursoDidacticoServer = idRecursoDidacticoServer;
    }

    @Override
    public String toString() {
        return "ResponseRec_Did{" +
                "idRecursoDidacticoAndroid=" + idRecursoDidacticoAndroid +
                ", idRecursoDidacticoServer=" + idRecursoDidacticoServer +
                '}';
    }
}
