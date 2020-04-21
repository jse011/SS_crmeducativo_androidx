package com.consultoraestrategia.ss_crmeducativo.main.entities;

public class GradoUi {

    private int cargaAcademicaId;
    private String seccion;
    private String periodo;
    private int seccionId;
    private int periodoId;
    private boolean seleted;
    private int docenteId;
    private int contador;

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public int getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }

    public boolean isSeleted() {
        return seleted;
    }

    public void setSeleted(boolean seleted) {
        this.seleted = seleted;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public String toString() {
        return "GradoUi{" +
                "cargaAcademicaId=" + cargaAcademicaId +
                ", seccion='" + seccion + '\'' +
                ", periodo='" + periodo + '\'' +
                ", seccionId=" + seccionId +
                ", periodoId=" + periodoId +
                ", seleted=" + seleted +
                ", docenteId=" + docenteId +
                ", contador=" + contador +
                '}';
    }
}
