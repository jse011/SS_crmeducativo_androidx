package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class RubroEvaluacionResultado extends BaseRelEntity {

    public static final  int ANCLADA=313, EVALUADO=314, CREADO =237, ACTUALIZADO = 239, ELIMINADO = 280;
    @Unique
    @PrimaryKey
    int rubroEvalResultadoId;
    @Column
    String titulo;
    @Column
    String subtitulo;
    @Column
    private String tipoNotaId;
    @Column
    String colorfondo;
    //    String colorFondo;
    @Column
    boolean mColorFondo;
    @Column
    String valorDefecto;
    @Column
    int competenciaId;
    @Column
    int calendarioPeriodoId;
    @Column
    String anchoColumna;
    @Column
    boolean ocultarColumna;
    @Column
    int tipoFormulaId;
    @Column
    int silaboBaseId;
    @Column
    int silaboEventoId;
    @Column
    int tipoRedondeoId;
    @Column
    int valorRedondeoId;
    @Column
    double peso;
    @Column
    int estadoId;
    @Column
    private boolean obtenerNota;
    @Column
    private int icdId;
    @Column
    private int nivel;
    @Column
    private String rubroEvalProcesoId;
    @Column
    private int evaluable;
    @Column
    private int escalaEvaluacionId;
    @Column
    public int rubroFormal;
    @Column
    public int tipoCalendarioPeriodoId;
    @Column
    public int rubroEvalResultadoBaseId;
    @Column
    public double promedio;
    @Column
    public double desviacionEstandar;

    public RubroEvaluacionResultado() {
    }



    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public boolean ismColorFondo() {
        return mColorFondo;
    }

    public void setmColorFondo(boolean mColorFondo) {
        this.mColorFondo = mColorFondo;
    }

    public int getUsuarioCreacionId() {
        return usuarioCreacionId;
    }

    public void setUsuarioCreacionId(int usuarioCreacionId) {
        this.usuarioCreacionId = usuarioCreacionId;
    }

    public int getUsuarioAccionId() {
        return usuarioAccionId;
    }

    public void setUsuarioAccionId(int usuarioAccionId) {
        this.usuarioAccionId = usuarioAccionId;
    }

    public boolean isObtenerNota() {
        return obtenerNota;
    }

    public void setObtenerNota(boolean obtenerNota) {
        this.obtenerNota = obtenerNota;
    }

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
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



    public boolean isMColorFondo() {
        return mColorFondo;
    }

    public void setMColorFondo(boolean mColorFondo) {
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

    public int getSilaboBaseId() {
        return silaboBaseId;
    }

    public void setSilaboBaseId(int silaboBaseId) {
        this.silaboBaseId = silaboBaseId;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getColorfondo() {
        return colorfondo;
    }

    public void setColorfondo(String colorfondo) {
        this.colorfondo = colorfondo;
    }

    public int getIcdId() {
        return icdId;
    }

    public void setIcdId(int icdId) {
        this.icdId = icdId;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public int getEvaluable() {
        return evaluable;
    }

    public void setEvaluable(int evaluable) {
        this.evaluable = evaluable;
    }

    public int getEscalaEvaluacionId() {
        return escalaEvaluacionId;
    }

    public void setEscalaEvaluacionId(int escalaEvaluacionId) {
        this.escalaEvaluacionId = escalaEvaluacionId;
    }

    public int getRubroFormal() {
        return rubroFormal;
    }

    public void setRubroFormal(int rubroFormal) {
        this.rubroFormal = rubroFormal;
    }

    public int getTipoCalendarioPeriodoId() {
        return tipoCalendarioPeriodoId;
    }

    public void setTipoCalendarioPeriodoId(int tipoCalendarioPeriodoId) {
        this.tipoCalendarioPeriodoId = tipoCalendarioPeriodoId;
    }

    public int getRubroEvalResultadoBaseId() {
        return rubroEvalResultadoBaseId;
    }

    public void setRubroEvalResultadoBaseId(int rubroEvalResultadoBaseId) {
        this.rubroEvalResultadoBaseId = rubroEvalResultadoBaseId;
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
}
