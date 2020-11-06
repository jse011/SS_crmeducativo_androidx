package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

public class ColumnTableRegEvalUi {
    public static final int ALUMNO = 1, FINAL = 2, NOTA = 3;

    public ColumnTableRegEvalUi(int tipo) {
        this.tipo = tipo;
    }

    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
