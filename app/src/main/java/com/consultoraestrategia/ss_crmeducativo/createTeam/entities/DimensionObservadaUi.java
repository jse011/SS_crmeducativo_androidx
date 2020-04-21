package com.consultoraestrategia.ss_crmeducativo.createTeam.entities;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 15/08/2018.
 */
@Parcel
public class DimensionObservadaUi {
    public String key;
    public DimensionUi dimensionUi;
    public InstrumentoObservadoUi instrumentoObservadoUi;
    public int valor;
    public int area;
    public int porcentaje;
    public int poscion;

    public DimensionObservadaUi() {
    }

    public DimensionUi getDimensionUi() {
        return dimensionUi;
    }

    public void setDimensionUi(DimensionUi dimensionUi) {
        this.dimensionUi = dimensionUi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public InstrumentoObservadoUi getInstrumentoObservadoUi() {
        return instrumentoObservadoUi;
    }

    public void setInstrumentoObservadoUi(InstrumentoObservadoUi instrumentoObservadoUi) {
        this.instrumentoObservadoUi = instrumentoObservadoUi;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getArea() {
        return area;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPoscion(int poscion) {
        this.poscion = poscion;
    }

    public int getPoscion() {
        return poscion;
    }

}
