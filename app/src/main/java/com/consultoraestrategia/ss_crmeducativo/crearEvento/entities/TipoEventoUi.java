package com.consultoraestrategia.ss_crmeducativo.crearEvento.entities;

import androidx.annotation.NonNull;

public class TipoEventoUi {
    private int Id;
    private String nombre;
    private boolean selecionado;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoEventoUi)) return false;

        TipoEventoUi that = (TipoEventoUi) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @NonNull
    @Override
    public String toString() {
        return ""+nombre;
    }
}
