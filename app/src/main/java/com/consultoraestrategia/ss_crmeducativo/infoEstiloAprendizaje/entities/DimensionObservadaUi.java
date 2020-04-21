package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities;


/**
 * Created by SCIEV on 15/08/2018.
 */
public class DimensionObservadaUi {
    private String key;
    private int poscion;
    private DimensionUi dimensionUi;
    private InstrumentoObservadoUi instrumentoObservadoUi;
    private int valor;
    private int area;
    private int porcentaje;

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

    public int getPoscion() {
        return poscion;
    }

    public void setPoscion(int poscion) {
        this.poscion = poscion;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
