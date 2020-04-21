package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

import java.util.List;

public class RubricaUi {

    public enum Origen{AREA, TAREA, SESION}
    public enum Tipo{RUBRICA, RUBRO}
    public enum Publicado{TODOS, PARCIAL, NINGUNO}
    public enum Forma{INDIVIDUAL , GRUPAL}
    private int contador;
    private String idRubrica;
    private String titulo;
    private String alias;
    private String fecha;
    private String tipoEvaluacion;
    private String formaEvaluacion;
    private double media;
    private double desviacionE;
    private boolean habilitado;
    private Origen origen;
    private int estadoMensaje;
    private Publicado publicado;
    private Forma forma;
    private List<Integer> cantEvalRubros;
    private Tipo tipo;
    private IndicadorUi indicadorUi;
    private String tipoNotaId;
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getIdRubrica() {
        return idRubrica;
    }

    public void setIdRubrica(String idRubrica) {
        this.idRubrica = idRubrica;
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

    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getFormaEvaluacion() {
        return formaEvaluacion;
    }

    public void setFormaEvaluacion(String formaEvaluacion) {
        this.formaEvaluacion = formaEvaluacion;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getDesviacionE() {
        return desviacionE;
    }

    public void setDesviacionE(double desviacionE) {
        this.desviacionE = desviacionE;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public int getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(int estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    public Publicado getPublicado() {
        return publicado;
    }

    public void setPublicado(Publicado publicado) {
        this.publicado = publicado;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public List<Integer> getCantEvalRubros() {
        return cantEvalRubros;
    }

    public void setCantEvalRubros(List<Integer> cantEvalRubros) {
        this.cantEvalRubros = cantEvalRubros;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }
}
