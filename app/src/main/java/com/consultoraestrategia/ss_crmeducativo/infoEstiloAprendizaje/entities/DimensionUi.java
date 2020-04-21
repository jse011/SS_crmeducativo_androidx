package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities;

import java.util.List;

/**
 * Created by SCIEV on 15/08/2018.
 */
public class DimensionUi {
    public enum EDimension{EC,OR,CA,EA,DEFAULT}
    private EDimension eDimension = EDimension.DEFAULT;
    private int id;
    private String nombre;
    private String descripcion;
    private int instrumentoEvalId;
    private String  sigla;
    private String  enfoque;
    private double confiabilidad;
    private double intervaloInicio;
    private double intervaloFin;
    private int incluidoIInicio;
    private int incluidoIFin;
    private String color;
    private String icono;
    private String medida;
    private int orden;
    private List<CaracteristicaUi> caracteristicaUiList;

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

    public EDimension geteDimension() {
        return eDimension;
    }

    public void seteDimension(EDimension eDimension) {
        this.eDimension = eDimension;
    }

    public List<CaracteristicaUi> getCaracteristicaUiList() {
        return caracteristicaUiList;
    }

    public void setCaracteristicaUiList(List<CaracteristicaUi> caracteristicaUiList) {
        this.caracteristicaUiList = caracteristicaUiList;
    }
}
