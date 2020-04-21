package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import java.util.Objects;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class NotaUi {

    public enum TipoNota{IMAGEN,TEXTO,SELECTOR_NUMERICO,DEFECTO,TECLADO_NUMERICO};
    private String Id;
    private String Tipo;
    private String contenido;
    private Boolean resaltar;
    private double nota;
    private double notaDefault;
    private String icono;
    private Estilo estilo = Estilo.BLANCO;
    private double notaEvaluacion;
    private String intervalos;
    private double limiteInferior;
    private double limiteSuperior;
    private boolean incluidoLInferior;
    private boolean incluidoLSuperior;
    private boolean intervalo;
    private TipoNota tipoNota = TipoNota.DEFECTO;
    private GrupoEvaluacionUi grupoEvaluacion;
    private AlumnosEvaluacionUi alumnoEvaluacion;


    public void setGrupoEvaluacion(GrupoEvaluacionUi grupoEvaluacion) {
        this.grupoEvaluacion = grupoEvaluacion;
    }

    public GrupoEvaluacionUi getGrupoEvaluacion() {
        return grupoEvaluacion;
    }

    public void setAlumnoEvaluacion(AlumnosEvaluacionUi alumnoEvaluacion) {
        this.alumnoEvaluacion = alumnoEvaluacion;
    }

    public AlumnosEvaluacionUi getAlumnoEvaluacion() {
        return alumnoEvaluacion;
    }

    public NotaUi() {
        resaltar = false;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Boolean getResaltar() {
        return resaltar;
    }

    public void setResaltar(Boolean resaltar) {
        this.resaltar = resaltar;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public boolean isIntervalo() {
        return intervalo;
    }

    public void setIntervalo(boolean intervalo) {
        this.intervalo = intervalo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaUi notaUi = (NotaUi) o;
        return Objects.equals(Id, notaUi.Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id);
    }

    public void setNotaEvaluacion(double notaEvaluacion) {
        this.notaEvaluacion = notaEvaluacion;
    }

    public double getNotaEvaluacion() {
        return notaEvaluacion;
    }

    public String getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(String intervalos) {
        this.intervalos = intervalos;
    }

    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public boolean isIncluidoLInferior() {
        return incluidoLInferior;
    }

    public void setIncluidoLInferior(boolean incluidoLInferior) {
        this.incluidoLInferior = incluidoLInferior;
    }

    public boolean isIncluidoLSuperior() {
        return incluidoLSuperior;
    }

    public void setIncluidoLSuperior(boolean incluidoLSuperior) {
        this.incluidoLSuperior = incluidoLSuperior;
    }

    @Override
    public String toString() {
        return "NotaUi{" +
                "Id='" + Id + '\'' +
                ", Tipo='" + Tipo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", resaltar=" + resaltar +
                ", nota=" + nota +
                ", icono='" + icono + '\'' +
                ", estilo=" + estilo +
                ", notaEvaluacion=" + notaEvaluacion +
                ", intervalos='" + intervalos + '\'' +
                ", limiteInferior=" + limiteInferior +
                ", limiteSuperior=" + limiteSuperior +
                ", incluidoLInferior=" + incluidoLInferior +
                ", incluidoLSuperior=" + incluidoLSuperior +
                '}';
    }

    public TipoNota getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }

    public double getNotaDefault() {
        return notaDefault;
    }

    public void setNotaDefault(double notaDefault) {
        this.notaDefault = notaDefault;
    }
}
