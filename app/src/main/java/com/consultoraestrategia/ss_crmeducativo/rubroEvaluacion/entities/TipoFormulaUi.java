package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import org.parceler.Parcel;

/**
 * Created by kike on 4/11/2017.
 */
@Parcel
public class TipoFormulaUi {
    public int idTipo;
    public String nombre;
    public boolean selected;

    public TipoFormulaUi() {
    }

    public TipoFormulaUi(int idTipo, String nombre, boolean selected) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.selected = selected;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
