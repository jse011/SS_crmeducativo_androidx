package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;

import java.util.List;

public class BEDimensionDesarrollo {
    private List<DimensionDesarrollo> dimensionDesarrollo;
    private List<DimensionDesarrolloDetalle> dimensionDesarrolloDetalle;
    private List<EstrategiaEvaluacion> estrategiaEvaluacion;
    private long fecha_servidor;
    private List<DimensionDesarrolloEstrategiaEvaluacionC> dimensionDesarrolloEstrategiaEval;
    private List<Dimension> dimension;
    private List<DimensionObservada> dimensionObservada;
    private List<InstrumentoEvaluacion> instrumentoEvaluacion;
    private List<InstrumentoObservado> instrumentoObservado;

    public List<DimensionDesarrollo> getDimensionDesarrollo() {
        return dimensionDesarrollo;
    }

    public void setDimensionDesarrollo(List<DimensionDesarrollo> dimensionDesarrollo) {
        this.dimensionDesarrollo = dimensionDesarrollo;
    }

    public List<DimensionDesarrolloDetalle> getDimensionDesarrolloDetalle() {
        return dimensionDesarrolloDetalle;
    }

    public void setDimensionDesarrolloDetalle(List<DimensionDesarrolloDetalle> dimensionDesarrolloDetalle) {
        this.dimensionDesarrolloDetalle = dimensionDesarrolloDetalle;
    }

    public List<EstrategiaEvaluacion> getEstrategiaEvaluacion() {
        return estrategiaEvaluacion;
    }

    public void setEstrategiaEvaluacion(List<EstrategiaEvaluacion> estrategiaEvaluacion) {
        this.estrategiaEvaluacion = estrategiaEvaluacion;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    public List<DimensionDesarrolloEstrategiaEvaluacionC> getDimensionDesarrolloEstrategiaEval() {
        return dimensionDesarrolloEstrategiaEval;
    }

    public void setDimensionDesarrolloEstrategiaEval(List<DimensionDesarrolloEstrategiaEvaluacionC> dimensionDesarrolloEstrategiaEval) {
        this.dimensionDesarrolloEstrategiaEval = dimensionDesarrolloEstrategiaEval;
    }

    public List<Dimension> getDimension() {
        return dimension;
    }

    public void setDimension(List<Dimension> dimension) {
        this.dimension = dimension;
    }

    public List<DimensionObservada> getDimensionObservada() {
        return dimensionObservada;
    }

    public void setDimensionObservada(List<DimensionObservada> dimensionObservada) {
        this.dimensionObservada = dimensionObservada;
    }

    public List<InstrumentoEvaluacion> getInstrumentoEvaluacion() {
        return instrumentoEvaluacion;
    }

    public void setInstrumentoEvaluacion(List<InstrumentoEvaluacion> instrumentoEvaluacion) {
        this.instrumentoEvaluacion = instrumentoEvaluacion;
    }

    public List<InstrumentoObservado> getInstrumentoObservado() {
        return instrumentoObservado;
    }

    public void setInstrumentoObservado(List<InstrumentoObservado> instrumentoObservado) {
        this.instrumentoObservado = instrumentoObservado;
    }
}
