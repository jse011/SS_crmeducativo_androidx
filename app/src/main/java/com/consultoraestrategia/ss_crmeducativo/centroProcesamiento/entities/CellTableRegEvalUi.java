package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

public class CellTableRegEvalUi {
    public static final int ALUMNO = 1, NOTA = 2;
    private int tipo;

    public CellTableRegEvalUi(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
