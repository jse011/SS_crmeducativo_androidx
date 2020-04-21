package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class IndicadorCampoAccionUi implements Serializable {
    public IndicadorUi indicadorUi;
    public CampoAccionUi campoAccionUi;
    public boolean checked;

    public IndicadorCampoAccionUi() {
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }

    public CampoAccionUi getCampoAccionUi() {
        return campoAccionUi;
    }

    public void setCampoAccionUi(CampoAccionUi campoAccionUi) {
        this.campoAccionUi = campoAccionUi;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return checked;
    }
}
