package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;

import java.util.List;

public class TipoUi  {
    public enum Herencia{PADRE, HIJO}
    private int id;
    private String nombre;
    private String objeto;
    private String  concepto;
    private Herencia herencia;
    private int cantidad;
    private int porcentaje;
    private boolean selected;
    private List<TipoComportamientoUi> tipoUiListHijos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<TipoComportamientoUi> getTipoUiListHijos() {
        return tipoUiListHijos;
    }

    public void setTipoUiListHijos(List<TipoComportamientoUi> tipoUiListHijos) {
        this.tipoUiListHijos = tipoUiListHijos;
    }

    public Herencia getHerencia() {
        return herencia;
    }

    public void setHerencia(Herencia herencia) {
        this.herencia = herencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoUi)) return false;

        TipoUi tipoUi = (TipoUi) o;

        return getId() == tipoUi.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
