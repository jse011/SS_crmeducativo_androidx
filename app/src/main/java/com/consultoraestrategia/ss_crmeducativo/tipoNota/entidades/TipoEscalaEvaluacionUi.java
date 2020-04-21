package com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades;

/**
 * Created by kike on 16/02/2018.
 */

public class TipoEscalaEvaluacionUi {
    private int idTipo;
    private String nombre;

    public TipoEscalaEvaluacionUi() {
    }

    public TipoEscalaEvaluacionUi(int idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "" + nombre + "";
    }
}
