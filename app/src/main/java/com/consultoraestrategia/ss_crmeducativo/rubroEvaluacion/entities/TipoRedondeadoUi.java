package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import org.parceler.Parcel;

/**
 * Created by kike on 4/11/2017.
 */
@Parcel
public class TipoRedondeadoUi {
    public int idTipo;
    public String nombre;

    public TipoRedondeadoUi() {
    }

    public TipoRedondeadoUi(int idTipo, String nombre) {
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
