package com.consultoraestrategia.ss_crmeducativo.entities;

import java.util.List;

/**
 * Created by irvinmarin on 21/12/2017.
 */

public class BERespuesta_TareaEvento {

    private int idTareaEventoAndroid;
    private int idTareaEventoServer;
    private List<ResponseRec_Did> responseRec_Did;

    public BERespuesta_TareaEvento() {
    }

    public List<ResponseRec_Did> getResponseRec_Did() {
        return responseRec_Did;
    }

    public void setResponseRec_Did(List<ResponseRec_Did> responseRec_Did) {
        this.responseRec_Did = responseRec_Did;
    }

    public int getIdTareaEventoAndroid() {
        return idTareaEventoAndroid;
    }

    public void setIdTareaEventoAndroid(int idTareaEventoAndroid) {
        this.idTareaEventoAndroid = idTareaEventoAndroid;
    }

    public int getIdTareaEventoServer() {
        return idTareaEventoServer;
    }

    public void setIdTareaEventoServer(int idTareaEventoServer) {
        this.idTareaEventoServer = idTareaEventoServer;
    }

    @Override
    public String toString() {
        return "BERespuesta_TareaEvento{" +
                "idTareaEventoAndroid=" + idTareaEventoAndroid +
                ", idTareaEventoServer=" + idTareaEventoServer +
                ", responseRec_Did=" + responseRec_Did +
                '}';
    }
}
