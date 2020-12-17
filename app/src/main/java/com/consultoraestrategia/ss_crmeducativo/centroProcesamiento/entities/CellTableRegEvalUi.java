package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

public class CellTableRegEvalUi {
    public static final int ALUMNO = 1, NOTA = 2, ESPACIO_CALENDARIO = 3;
    private int tipo;
    private int evaluacionResultadoId;
    private int alumnoId;
    private int rubroEvalResultadoId;
    private String color;
    private double nota;
    private String valorTipoNotaId;
    private String tituloNota;
    private int orden;
    private int orden2;
    private boolean evaluado;
    private boolean RFEditable;
    private boolean notaDup;
    private String conclusionDescriptiva;
    private RowTableRegEvalUi alumnoUi;
    private boolean promedio;
    private int parentId;
    private int competenciaId;
    private int tipoId;
    private boolean bimestrNoVigente;
    private boolean bimestrCerrado;
    private boolean notaNoGenerada;
    private boolean alumnoVigencia;
    public CellTableRegEvalUi() {
    }

    public CellTableRegEvalUi(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setEvaluacionResultadoId(int evaluacionResultadoId) {
        this.evaluacionResultadoId = evaluacionResultadoId;
    }

    public int getEvaluacionResultadoId() {
        return evaluacionResultadoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
    }

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setTituloNota(String tituloNota) {
        this.tituloNota = tituloNota;
    }

    public String getTituloNota() {
        return tituloNota;
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

    public void setEvaluado(boolean evaluado) {
        this.evaluado = evaluado;
    }

    public boolean getEvaluado() {
        return evaluado;
    }

    public void setRFEditable(boolean rfEditable) {
        this.RFEditable = rfEditable;
    }

    public boolean getRFEditable() {
        return RFEditable;
    }

    public void setNotaDup(boolean notaDup) {
        this.notaDup = notaDup;
    }

    public boolean getNotaDup() {
        return notaDup;
    }

    public void setConclusionDescriptiva(String conclusionDescriptiva) {
        this.conclusionDescriptiva = conclusionDescriptiva;
    }

    public String getConclusionDescriptiva() {
        return conclusionDescriptiva;
    }

    public RowTableRegEvalUi getAlumnoUi() {
        return alumnoUi;
    }

    public void setAlumnoUi(RowTableRegEvalUi alumnoUi) {
        this.alumnoUi = alumnoUi;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public boolean isBimestrNoVigente() {
        return bimestrNoVigente;
    }

    public void setBimestrNoVigente(boolean bimestrNoVigente) {
        this.bimestrNoVigente = bimestrNoVigente;
    }

    public boolean isNotaNoGenerada() {
        return notaNoGenerada;
    }

    public void setNotaNoGenerada(boolean notaNoGenerada) {
        this.notaNoGenerada = notaNoGenerada;
    }

    public boolean isAlumnoVigencia() {
        return alumnoVigencia;
    }

    public void setAlumnoVigencia(boolean alumnoVigencia) {
        this.alumnoVigencia = alumnoVigencia;
    }

    public boolean isBimestrCerrado() {
        return bimestrCerrado;
    }

    public void setBimestrCerrado(boolean bimestrCerrado) {
        this.bimestrCerrado = bimestrCerrado;
    }
}
