package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;


import java.util.List;

public class DestinoUi {

    int georeferenciaId;
    private boolean tutor;
    private boolean padre;
    private boolean apoderado;

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public void setTutor(boolean tutor) {
        this.tutor = tutor;
    }

    public boolean getTutor() {
        return tutor;
    }

    public void setPadre(boolean padre) {
        this.padre = padre;
    }

    public boolean getPadre() {
        return padre;
    }

    public void setApoderado(boolean apoderado) {
        this.apoderado = apoderado;
    }

    public boolean getApoderado() {
        return apoderado;
    }
}
