package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import org.parceler.Parcel;

/**
 * Created by kike on 07/12/2017.
 */
@Parcel
public class TipoEvaluacionUi {
    public int idTipo;
    public String nombre;

    public TipoEvaluacionUi() {
    }

    public TipoEvaluacionUi(int idTipo, String nombre) {
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
}
