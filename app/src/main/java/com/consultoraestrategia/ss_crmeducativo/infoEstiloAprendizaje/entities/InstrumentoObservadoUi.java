package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 15/08/2018.
 */
public class InstrumentoObservadoUi {
    private String key;
    private int personaId;
    private String fechaEvaluacion;
    private String nombreEvaluacion;
    private String icono;
    private List<DimensionObservadaUi> dimensionObservadaUiList;
    private List<DimensionObservadaUi> dimensionObservadaUiListOrdenada = new ArrayList<>();
    DimensionObservadaUi dimensionObservadaUiPrincipalOne;
    DimensionObservadaUi dimensionObservadaUiPrincipalTwo;

    public InstrumentoObservadoUi() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public void setNombreEvaluacion(String nombreEvaluacion) {
        this.nombreEvaluacion = nombreEvaluacion;
    }

    public String getNombreEvaluacion() {
        return nombreEvaluacion;
    }

    public List<DimensionObservadaUi> getDimensionObservadaUiList() {
        return dimensionObservadaUiList;
    }

    public void setDimensionObservadaUiList(List<DimensionObservadaUi> dimensionObservadaUiList) {
        this.dimensionObservadaUiList = dimensionObservadaUiList;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public DimensionObservadaUi getDimensionObservadaUiPrincipalOne() {
        return dimensionObservadaUiPrincipalOne;
    }

    public void setDimensionObservadaUiPrincipalOne(DimensionObservadaUi dimensionObservadaUiPrincipalOne) {
        this.dimensionObservadaUiPrincipalOne = dimensionObservadaUiPrincipalOne;
    }

    public DimensionObservadaUi getDimensionObservadaUiPrincipalTwo() {
        return dimensionObservadaUiPrincipalTwo;
    }

    public void setDimensionObservadaUiPrincipalTwo(DimensionObservadaUi dimensionObservadaUiPrincipalTwo) {
        this.dimensionObservadaUiPrincipalTwo = dimensionObservadaUiPrincipalTwo;
    }

    public List<DimensionObservadaUi> getDimensionObservadaUiListOrdenada() {
        return dimensionObservadaUiListOrdenada;
    }

    public void setDimensionObservadaUiListOrdenada(List<DimensionObservadaUi> dimensionObservadaUiListOrdenada) {
        this.dimensionObservadaUiListOrdenada = dimensionObservadaUiListOrdenada;
    }
}
