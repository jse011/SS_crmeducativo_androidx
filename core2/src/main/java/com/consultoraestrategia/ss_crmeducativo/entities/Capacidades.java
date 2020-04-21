package com.consultoraestrategia.ss_crmeducativo.entities;


import java.util.List;

/**
 * Created by kelvi on 24/02/2017.
 */


public class Capacidades {


    private int idCap;
    private int idEvalCap;
    private List<EvaluacionCapacidad> evaluacionCapacidadList;
    private String trimestre;

    public Capacidades() {
    }

    public Capacidades(int idCap, int idEvalCap, List<EvaluacionCapacidad> evaluacionCapacidadList, String trimestre) {
        this.idCap = idCap;
        this.idEvalCap = idEvalCap;
        this.evaluacionCapacidadList = evaluacionCapacidadList;
        this.trimestre = trimestre;
    }

    public int getIdEvalCap() {
        return idEvalCap;
    }

    public void setIdEvalCap(int idEvalCap) {
        this.idEvalCap = idEvalCap;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public int getIdCap() {
        return idCap;
    }

    public void setIdCap(int id) {
        this.idCap = id;
    }

    public List<EvaluacionCapacidad> getEvaluacionCapacidadList() {
        return evaluacionCapacidadList;
    }

    public void setEvaluacionCapacidadList(List<EvaluacionCapacidad> evaluacionCapacidadList) {
        this.evaluacionCapacidadList = evaluacionCapacidadList;
    }
}
