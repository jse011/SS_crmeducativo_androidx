package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class CapacidadUi {
    private int competenciaId;

    public enum EstadoCapacidad{DEFECTO,ANCLADA,EVALUADA}
    private String rubroEvaluacionId;
    private int contador;
    private String titulo;
    private String alias;
    private String fecha;
    private String media;
    private int silaboEventoId;
    private CompetenciaUi competenciaUi;
    private EstadoCapacidad estadoCapacidad;
    private List<CapacidadUi> capacidadUiList;
    public enum  Tipo{
        RESULTADO_COMPETENCIA, RESULTADO_CAPACIDAD, DEFAULT
    }

    private Tipo tipo=Tipo.DEFAULT;

    public CapacidadUi() {
        this.estadoCapacidad= EstadoCapacidad.DEFECTO;
    }

    public CapacidadUi(String rubroEvaluacionId, int contador, String titulo, String alias, String fecha, String media,int silaboEventoId,int competenciaId ) {
        this.rubroEvaluacionId = rubroEvaluacionId;
        this.contador = contador;
        this.titulo = titulo;
        this.alias = alias;
        this.fecha = fecha;
        this.media = media;
        this.silaboEventoId = silaboEventoId;
        this.competenciaId = competenciaId;
    }

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
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

    public CompetenciaUi getCompetenciaUi() {
        return competenciaUi;
    }

    public void setCompetenciaUi(CompetenciaUi competenciaUi) {
        this.competenciaUi = competenciaUi;
    }

    public List<CapacidadUi> getCapacidadUiList() {
        return capacidadUiList;
    }

    public void setCapacidadUiList(List<CapacidadUi> capacidadUiList) {
        this.capacidadUiList = capacidadUiList;
    }

    public EstadoCapacidad getEstadoCapacidad() {
        return estadoCapacidad;
    }

    public void setEstadoCapacidad(EstadoCapacidad estadoCapacidad) {
        this.estadoCapacidad = estadoCapacidad;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }


}

