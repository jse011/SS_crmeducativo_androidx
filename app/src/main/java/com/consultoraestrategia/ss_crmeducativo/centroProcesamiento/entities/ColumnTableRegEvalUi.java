package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

public class ColumnTableRegEvalUi {
    public static final int ALUMNO = 1, FINAL = 2, NOTA = 3, ESPACIO_CALENDARIO = 4;;
    private String titulo;
    private int rubroResultadoId;
    private int rubroPrincipalId;
    private int orden;
    private int orden2;
    private int competenciaId;
    private int parendId;

    public ColumnTableRegEvalUi() {
    }

    public ColumnTableRegEvalUi(int tipo) {
        this.tipo = tipo;
    }

    private int tipo;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public void setRubroPrincipalId(int rubroPrincipalId) {
        this.rubroPrincipalId = rubroPrincipalId;
    }

    public int getRubroPrincipalId() {
        return rubroPrincipalId;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden2(int orden2) {
        this.orden2 = orden2;
    }

    public int getOrden2() {
        return orden2;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setParendId(int parendId) {
        this.parendId = parendId;
    }

    public int getParendId() {
        return parendId;
    }
}
