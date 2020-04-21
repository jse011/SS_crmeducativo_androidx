package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class DesempenioUi {
    private int id;
    private String descripcion;
    private IndicadorUi indicadorUi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }
}
