package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import java.util.List;
import java.util.Objects;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class RubroEvaluacionUi {

    private boolean calendarioVigente;

    public void setCalendarioVigente(boolean calendarioVigente) {
        this.calendarioVigente = calendarioVigente;
    }

    public boolean isCalendarioVigente() {
        return calendarioVigente;
    }

    public enum TipoNota{IMAGEN,TEXTO,SELECTOR_NUMERICO,DEFECTO,TECLADO_NUMERICO};
    public enum Tipo{NORMAL,RUBRICA_DETALLE}
    private String Id;
    private String Titulo;
    private List<AlumnosEvaluacionUi> alumnos;
    private List<NotaUi> evaluacionProcesoUis;
    private int cargaCursosId;
    private boolean status;
    private String tipoNotaId;
    private TipoNota tipoNota;
    private IndicadorUi indicadorUi;
    private Tipo tipo =Tipo.NORMAL;
    public RubroEvaluacionUi() {
        indicadorUi = new IndicadorUi();
    }
    public int IdServer;
    private int SesionAprendizajeId;
    public TipoCompetencia tipoCompetencia;
    private int calendarioPeriodoId;



    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public List<AlumnosEvaluacionUi> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<AlumnosEvaluacionUi> alumnos) {
        this.alumnos = alumnos;
    }

    public List<NotaUi> getEvaluacionProcesoUis() {
        return evaluacionProcesoUis;
    }

    public void setEvaluacionProcesoUis(List<NotaUi> evaluacionProcesoUis) {
        this.evaluacionProcesoUis = evaluacionProcesoUis;
    }

    public int getCargaCursosId() {
        return cargaCursosId;
    }

    public void setCargaCursosId(int cargaCursosId) {
        this.cargaCursosId = cargaCursosId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public TipoNota getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }

    public int getIdServer() {
        return IdServer;
    }

    public void setIdServer(int idServer) {
        IdServer = idServer;
    }

    public int getSesionAprendizajeId() {
        return SesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        SesionAprendizajeId = sesionAprendizajeId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public TipoCompetencia getTipoCompetencia() {
        return tipoCompetencia;
    }

    public void setTipoCompetencia(TipoCompetencia tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RubroEvaluacionUi that = (RubroEvaluacionUi) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id);
    }
}
