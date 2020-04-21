package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities;

import java.util.List;

public class TipoNotaUi {
    String id;
    String nombre;
    String valorDefecto;
    int valorMinimo;
    int valorMaximo;
    double longitudPaso;
    int tipoId;
    List<ValorTipoNotaUi> valorTipoNotaList;


    public TipoNotaUi() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public int getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(int valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public int getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(int valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public double getLongitudPaso() {
        return longitudPaso;
    }

    public void setLongitudPaso(double longitudPaso) {
        this.longitudPaso = longitudPaso;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaList() {
        return valorTipoNotaList;
    }

    public void setValorTipoNotaList(List<ValorTipoNotaUi> valorTipoNotaList) {
        this.valorTipoNotaList = valorTipoNotaList;
    }

    @Override
    public String toString() {
        return "TipoNotaUi{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", valorDefecto='" + valorDefecto + '\'' +
                ", valorMinimo=" + valorMinimo +
                ", valorMaximo=" + valorMaximo +
                ", longitudPaso=" + longitudPaso +
                ", tipoId=" + tipoId +
                ", valorTipoNotaList=" + valorTipoNotaList +
                '}';
    }
}
