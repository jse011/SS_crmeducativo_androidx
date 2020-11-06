package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

public class CabeceraUi {
    public static final int ALUMNO = 1, COMPETENCIA = 2;
    private int tipo;
    private int rowSpan;

    public CabeceraUi(int tipo, int rowSpan) {
        this.tipo = tipo;
        this.rowSpan = rowSpan;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }
}
