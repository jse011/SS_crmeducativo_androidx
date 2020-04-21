package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 15/08/2018.
 */
@Table(database = AppDatabase.class)
public class Dimension extends BaseModel {
    @PrimaryKey
    int dimensionId;
    @Column
    String nombre;
    @Column
    String descripcion;
    @Column
    int instrumentoEvalId;
    @Column
    String  sigla;
    @Column
    String  enfoque;
    @Column
    double confiabilidad;
    @Column
    double intervaloInicio;
    @Column
    double intervaloFin;
    @Column//Boolean
    int incluidoIInicio;
    @Column//Boolean
    int incluidoIFin;
    @Column
    String color;
    @Column
    String icono;
    @Column
    String medida;
    @Column
    int orden;

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
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
}
