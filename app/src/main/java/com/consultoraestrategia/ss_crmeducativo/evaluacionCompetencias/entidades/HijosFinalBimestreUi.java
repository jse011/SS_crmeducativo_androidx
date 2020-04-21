package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades;

import java.util.List;

/**
 * Created by kike on 12/04/2018.
 */

public class HijosFinalBimestreUi {
    public enum TipoFinalBimestre{TIPO_FINAL_BASE,TIPO_FINAL_BIMESTRE}
    private int contador;
    private int id;
    private String titulo;
    private String alias;
    private String fecha;
    private String media;
    private String color;
    private List<RubroEvalRNRFormulaUi> rubroEvalRNRFormulas;
    private PadreFinalBimestreUi padreFinalBimestre;
    private TipoFinalBimestre tipoFinalBimestre;
    private int rubroEvalId;

    public HijosFinalBimestreUi() {

    }

    public HijosFinalBimestreUi(int contador, int id, String titulo, String alias, String fecha, String media, List<RubroEvalRNRFormulaUi> rubroEvalRNRFormulas, PadreFinalBimestreUi padreFinalBimestre, TipoFinalBimestre tipoFinalBimestre, int rubroId, String color) {
        this.contador = contador;
        this.id = id;
        this.titulo = titulo;
        this.alias = alias;
        this.fecha = fecha;
        this.media = media;
        this.rubroEvalRNRFormulas = rubroEvalRNRFormulas;
        this.padreFinalBimestre = padreFinalBimestre;
        this.tipoFinalBimestre = tipoFinalBimestre;
        this.rubroEvalId= rubroId;
        this.color = color;
    }

    /* public HijosFinalBimestreUi(int contador, int id, String titulo, String alias, String fecha, String media, List<RubroEvalRNRFormulaUi> rubroEvalRNRFormulas, PadreFinalBimestreUi padreFinalBimestre) {
        this.contador = contador;
        this.id = id;
        this.titulo = titulo;
        this.alias = alias;
        this.fecha = fecha;
        this.media = media;
        this.rubroEvalRNRFormulas = rubroEvalRNRFormulas;
        this.padreFinalBimestre = padreFinalBimestre;
    }*/

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public List<RubroEvalRNRFormulaUi> getRubroEvalRNRFormulas() {
        return rubroEvalRNRFormulas;
    }

    public void setRubroEvalRNRFormulas(List<RubroEvalRNRFormulaUi> rubroEvalRNRFormulas) {
        this.rubroEvalRNRFormulas = rubroEvalRNRFormulas;
    }

    public PadreFinalBimestreUi getPadreFinalBimestre() {
        return padreFinalBimestre;
    }

    public void setPadreFinalBimestre(PadreFinalBimestreUi padreFinalBimestre) {
        this.padreFinalBimestre = padreFinalBimestre;
    }

    public TipoFinalBimestre getTipoFinalBimestre() {
        return tipoFinalBimestre;
    }

    public void setTipoFinalBimestre(TipoFinalBimestre tipoFinalBimestre) {
        this.tipoFinalBimestre = tipoFinalBimestre;
    }

    public int getRubroEvalId() {
        return rubroEvalId;
    }

    public void setRubroEvalId(int rubroEvalId) {
        this.rubroEvalId = rubroEvalId;
    }
}
