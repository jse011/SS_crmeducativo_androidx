package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import org.parceler.Parcel;

/**
 * Created by kike on 25/10/2017.
 */
@Parcel
public class RubrosAsociadosUi {


    public int numeroRubroAsociado;
    public String nombreRubroAsociado;

    public String circleColor;

    public String idRubroEvaluacionProcesoPrincipal;

    public String idRubroEvaluacionProcesoSecundario;

    public RubrosAsociadosUi() {
    }

    public RubrosAsociadosUi(int numeroRubroAsociado, String circleColor, String idRubroEvaluacionProcesoPrincipal, String idRubroEvaluacionProcesoSecundario) {
        this.numeroRubroAsociado = numeroRubroAsociado;
        this.circleColor = circleColor;
        this.idRubroEvaluacionProcesoPrincipal = idRubroEvaluacionProcesoPrincipal;
        this.idRubroEvaluacionProcesoSecundario = idRubroEvaluacionProcesoSecundario;
    }

    public int getNumeroRubroAsociado() {
        return numeroRubroAsociado;
    }

    public void setNumeroRubroAsociado(int numeroRubroAsociado) {
        this.numeroRubroAsociado = numeroRubroAsociado;
    }

    public String getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(String circleColor) {
        this.circleColor = circleColor;
    }

    public String getIdRubroEvaluacionProcesoPrincipal() {
        return idRubroEvaluacionProcesoPrincipal;
    }

    public void setIdRubroEvaluacionProcesoPrincipal(String idRubroEvaluacionProcesoPrincipal) {
        this.idRubroEvaluacionProcesoPrincipal = idRubroEvaluacionProcesoPrincipal;
    }

    public String getIdRubroEvaluacionProcesoSecundario() {
        return idRubroEvaluacionProcesoSecundario;
    }

    public void setIdRubroEvaluacionProcesoSecundario(String idRubroEvaluacionProcesoSecundario) {
        this.idRubroEvaluacionProcesoSecundario = idRubroEvaluacionProcesoSecundario;
    }

    public String getNombreRubroAsociado() {
        return nombreRubroAsociado;
    }

    public void setNombreRubroAsociado(String nombreRubroAsociado) {
        this.nombreRubroAsociado = nombreRubroAsociado;
    }

    @Override
    public String toString() {
        return "RubrosAsociadosUi{" +
                "numeroRubroAsociado=" + numeroRubroAsociado +
                ", nombreRubroAsociado='" + nombreRubroAsociado + '\'' +
                ", circleColor='" + circleColor + '\'' +
                ", idRubroEvaluacionProcesoPrincipal='" + idRubroEvaluacionProcesoPrincipal + '\'' +
                ", idRubroEvaluacionProcesoSecundario='" + idRubroEvaluacionProcesoSecundario + '\'' +
                '}';
    }
}
