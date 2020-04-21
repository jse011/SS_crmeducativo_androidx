package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class EvalProcUi extends Cell {
    public enum Tipo{SELECTOR_ICONOS, SELECTOR_VALORES}
    public enum FormaEvaluar{INDIVIDUAL, GRUPAL}
    public boolean estado;
    private String rubEvalProcId;
    private double nota;
    private String escala;
    private int alumnoId;
    private String grupoId;
    private String valorTipoNotaId;
    private String equipoId;
    private Tipo tipo = Tipo.SELECTOR_VALORES;
    private FormaEvaluar formaEvaluar = FormaEvaluar.INDIVIDUAL;
    private boolean alumnoActivo;

    public String getRubEvalProcId() {
        return rubEvalProcId;
    }

    public void setRubEvalProcId(String rubEvalProcId) {
        this.rubEvalProcId = rubEvalProcId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(String equipoId) {
        this.equipoId = equipoId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId = grupoId;
    }

    public FormaEvaluar getFormaEvaluar() {
        return formaEvaluar;
    }

    public void setFormaEvaluar(FormaEvaluar formaEvaluar) {
        this.formaEvaluar = formaEvaluar;
    }

    public boolean isAlumnoActivo() {
        return alumnoActivo;
    }

    public void setAlumnoActivo(boolean alumnoActivo) {
        this.alumnoActivo = alumnoActivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvalProcUi that = (EvalProcUi) o;

        return rubEvalProcId != null ? rubEvalProcId.equals(that.rubEvalProcId) : that.rubEvalProcId == null;
    }

    @Override
    public int hashCode() {
        return rubEvalProcId != null ? rubEvalProcId.hashCode() : 0;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EvalProcUi{" +
                "estado=" + estado +
                ", rubEvalProcId='" + rubEvalProcId + '\'' +
                ", nota=" + nota +
                ", escala='" + escala + '\'' +
                ", alumnoId=" + alumnoId +
                ", grupoId='" + grupoId + '\'' +
                ", valorTipoNotaId='" + valorTipoNotaId + '\'' +
                ", equipoId='" + equipoId + '\'' +
                ", tipo=" + tipo +
                ", formaEvaluar=" + formaEvaluar +
                '}';
    }

    public EvalProcUi copy() {
        EvalProcUi evalProcUi = new EvalProcUi();
        evalProcUi.setRubEvalProcId(getRubEvalProcId());
        evalProcUi.setNota(getNota());
        evalProcUi.setEscala(getEscala());
        evalProcUi.setAlumnoId(getAlumnoId());
        evalProcUi.setValorTipoNotaId(getValorTipoNotaId());
        evalProcUi.setEquipoId(getEquipoId());
        evalProcUi.setTipo(getTipo());
        evalProcUi.setGrupoId(getGrupoId());
        evalProcUi.setFormaEvaluar(getFormaEvaluar());
        evalProcUi.setEstado(isEstado());
       return  evalProcUi;
    }
}
