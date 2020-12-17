package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

public class CabeceraUi {
    public static final int ALUMNO = 1, COMPETENCIA = 2, TITULO= 3,TITULO_ALUMNO = 4, ESPACIO_CALENDARIO = 5, COMPETENCIA_FINAL = 6, COMPETENCIA_FINAL_TITULO = 7;
    private int tipo;
    private int rowSpan;
    private String titulo;
    private int rubroResultadoId;
    private int competenciaId;

    public CabeceraUi() {
    }

    public CabeceraUi(int tipo, int rowSpan) {
        this.tipo = tipo;
        this.rowSpan = rowSpan;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setRubroResultadoId(int rubroResultadoId) {
        this.rubroResultadoId = rubroResultadoId;
    }

    public int getRubroResultadoId() {
        return rubroResultadoId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }
}
