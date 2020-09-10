package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class RubroEvaluacionProcesoC extends BaseEntity {
    public static final int TIPORUBRO_UNIDIMENCIONAL =  470;  // normal
    public static final int TIPORUBRO_BIMENSIONAL = 471; //rubrica
    public static final int TIPORUBRO_BIDIMENCIONAL_DETALLE = 473;//detalle
    public static final int FORMAEVAL_INDIVIDUAL = 477;
    public static final int FORMAEVAL_GRUPAL = 478;
    public static final int ESTADO_ANCLADA=313, ESTADO_EVALUADO=314, ESTADO_CREADO =237, ESTADO_ACTUALIZADO = 239, ESTADO_ELIMINADO = 280;
    @Column
    String rubroEvalProcesoId;
    @Column
    String titulo;
    @Column
    String subtitulo;
    @Column
    String colorFondo;
    @Column
    boolean mColorFondo;
    @Column
    String valorDefecto;
    @Column
    int competenciaId;
    @Column
    int calendarioPeriodoId;
    @Column
    private String anchoColumna;
    @Column
    boolean ocultarColumna;
    @Column
    int tipoFormulaId;
    @Column
    int silaboEventoId;
    @Column
    int tipoRedondeoId;
    @Column
    int valorRedondeoId;
    @Column
    int rubroEvalResultadoId;//campo para eliminar
    @Column
    String tipoNotaId;
    @Column
    int sesionAprendizajeId;
    @Column
    int desempenioIcdId;
    @Column
    int campoTematicoId;
    @Column
    int tipoEvaluacionId;
    @Column
    int estadoId;
    @Column
    int tipoEscalaEvaluacionId;
    /*Opcional*/
    @Column
    int tipoColorRubroProceso;
    @Column
    int tiporubroid;
    @Column
    int formaEvaluacionId;
    @Column
    int countIndicador;
    @Column
    int rubroFormal;
    @Column
    int msje;
    @Column
    double promedio;
    @Column
    double desviacionEstandar;
    @Column
    int unidadAprendizajeId;

    @Column
    int estrategiaEvaluacionId;
    @Column
    private String tareaId;

    public RubroEvaluacionProcesoC() {
        super();
    }

    public RubroEvaluacionProcesoC(int androidId) {
        super(androidId);
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
    }

    public boolean ismColorFondo() {
        return mColorFondo;
    }

    public void setmColorFondo(boolean mColorFondo) {
        this.mColorFondo = mColorFondo;
    }

    public String getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(String valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public String getAnchoColumna() {
        return anchoColumna;
    }

    public void setAnchoColumna(String anchoColumna) {
        this.anchoColumna = anchoColumna;
    }

    public boolean isOcultarColumna() {
        return ocultarColumna;
    }

    public void setOcultarColumna(boolean ocultarColumna) {
        this.ocultarColumna = ocultarColumna;
    }

    public int getTipoFormulaId() {
        return tipoFormulaId;
    }

    public void setTipoFormulaId(int tipoFormulaId) {
        this.tipoFormulaId = tipoFormulaId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getTipoRedondeoId() {
        return tipoRedondeoId;
    }

    public void setTipoRedondeoId(int tipoRedondeoId) {
        this.tipoRedondeoId = tipoRedondeoId;
    }

    public int getValorRedondeoId() {
        return valorRedondeoId;
    }

    public void setValorRedondeoId(int valorRedondeoId) {
        this.valorRedondeoId = valorRedondeoId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getTipoEvaluacionId() {
        return tipoEvaluacionId;
    }

    public void setTipoEvaluacionId(int tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public int getDesempenioIcdId() {
        return desempenioIcdId;
    }

    public void setDesempenioIcdId(int desempenioIcdId) {
        this.desempenioIcdId = desempenioIcdId;
    }

    public int getCampoTematicoId() {
        return campoTematicoId;
    }

    public void setCampoTematicoId(int campoTematicoId) {
        this.campoTematicoId = campoTematicoId;
    }

    public int getTipoEscalaEvaluacionId() {
        return tipoEscalaEvaluacionId;
    }

    public void setTipoEscalaEvaluacionId(int tipoEscalaEvaluacionId) {
        this.tipoEscalaEvaluacionId = tipoEscalaEvaluacionId;
    }

    public int getTipoColorRubroProceso() {
        return tipoColorRubroProceso;
    }

    public void setTipoColorRubroProceso(int tipoColorRubroProceso) {
        this.tipoColorRubroProceso = tipoColorRubroProceso;
    }

    public int getTiporubroid() {
        return tiporubroid;
    }

    public void setTiporubroid(int tiporubroid) {
        this.tiporubroid = tiporubroid;
    }

    public int getFormaEvaluacionId() {
        return formaEvaluacionId;
    }

    public void setFormaEvaluacionId(int formaEvaluacionId) {
        this.formaEvaluacionId = formaEvaluacionId;
    }

    public int getCountIndicador() {
        return countIndicador;
    }

    public void setCountIndicador(int countIndicador) {
        this.countIndicador = countIndicador;
    }

    /*@Override
    public long getId() {
        return getRubroEvalProcesoId();
    }*/

    public int getRubroFormal() {
        return rubroFormal;
    }

    public void setRubroFormal(int rubroFormal) {
        this.rubroFormal = rubroFormal;
    }

    public int getMsje() {
        return msje;
    }

    public void setMsje(int msje) {
        this.msje = msje;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }

    public static RubroEvaluacionProcesoC getById(String idRubroProtected) {
        return  SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(idRubroProtected))
                .querySingle();
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    public int getEstrategiaEvaluacionId() {
        return estrategiaEvaluacionId;
    }

    public void setEstrategiaEvaluacionId(int estrategiaEvaluacionId) {
        this.estrategiaEvaluacionId = estrategiaEvaluacionId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    @Override
    public String toString() {
        return "RubroEvaluacionProcesoC{" +
                "rubroEvalProcesoId='" + rubroEvalProcesoId + '\'' +
                ", titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", tipoFormulaId=" + tipoFormulaId +
                ", silaboEventoId=" + silaboEventoId +
                ", tipoRedondeoId=" + tipoRedondeoId +
                ", valorRedondeoId=" + valorRedondeoId +
                ", rubroEvalResultadoId=" + rubroEvalResultadoId +
                ", tipoNotaId='" + tipoNotaId + '\'' +
                ", tipoEvaluacionId=" + tipoEvaluacionId +
                ", tiporubroid=" + tiporubroid +
                ", rubroFormal=" + rubroFormal +
                ", key='" + key + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubroEvaluacionProcesoC)) return false;

        RubroEvaluacionProcesoC that = (RubroEvaluacionProcesoC) o;

        return getKey() != null ? getKey().equals(that.getKey()) : that.getKey() == null;
    }

    @Override
    public int hashCode() {
        return getKey() != null ? getKey().hashCode() : 0;
    }
}
