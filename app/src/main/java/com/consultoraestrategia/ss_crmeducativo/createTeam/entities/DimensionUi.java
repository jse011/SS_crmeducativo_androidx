package com.consultoraestrategia.ss_crmeducativo.createTeam.entities;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 15/08/2018.
 */
@Parcel
public class DimensionUi {
    public int id;
    public String nombre;
    public String descripcion;
    public int instrumentoEvalId;
    public String  sigla;
    public String  enfoque;
    public double confiabilidad;
    public double intervaloInicio;
    public double intervaloFin;
    public int incluidoIInicio;
    public int incluidoIFin;
    public String color;
    public String icono;
    public String medida;
    public int orden;

    public DimensionUi() {
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getInstrumentoEvalId() {
        return instrumentoEvalId;
    }

    public void setInstrumentoEvalId(int instrumentoEvalId) {
        this.instrumentoEvalId = instrumentoEvalId;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEnfoque() {
        return enfoque;
    }

    public void setEnfoque(String enfoque) {
        this.enfoque = enfoque;
    }

    public double getConfiabilidad() {
        return confiabilidad;
    }

    public void setConfiabilidad(double confiabilidad) {
        this.confiabilidad = confiabilidad;
    }

    public double getIntervaloInicio() {
        return intervaloInicio;
    }

    public void setIntervaloInicio(double intervaloInicio) {
        this.intervaloInicio = intervaloInicio;
    }

    public double getIntervaloFin() {
        return intervaloFin;
    }

    public void setIntervaloFin(double intervaloFin) {
        this.intervaloFin = intervaloFin;
    }

    public int getIncluidoIInicio() {
        return incluidoIInicio;
    }

    public void setIncluidoIInicio(int incluidoIInicio) {
        this.incluidoIInicio = incluidoIInicio;
    }

    public int getIncluidoIFin() {
        return incluidoIFin;
    }

    public void setIncluidoIFin(int incluidoIFin) {
        this.incluidoIFin = incluidoIFin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "" + nombre + "";
    }
}
