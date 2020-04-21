package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;


import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;

public class RubroProcesoWrapper implements Serializable {


    public final static String  TITULO = "RubroProcesoWrapper.titulo";
    public final static String  TIPO_EVALUACION_ID = "RubroProcesoWrapper.tipoEvaluacionId";
    public final static String  FORMA_EVALUACION_ID = "RubroProcesoWrapper.formaEvaluacionId";
    public final static String  RUBRO_ID = "RubroProcesoWrapper.rubroProcesoId";
    public final static String  INDICADOR_ID = "RubroProcesoWrapper.indicadorId";
    public final static String  CAMPOS_TEMATICOS_IDS = "RubroProcesoWrapper.camposTematicosIds";
    public final static String  PROGRAMA_EDUCATIVO_ID = "RubroProcesoWrapper.programaEducativoId";
    public final static String  SESION_APRENDIZAJE_ID= "RubroProcesoWrapper.sesionAprendizajeId";
    public final static String  SILABO_EVENTO_ID= "RubroProcesoWrapper.silaboEventoId";
    public final static String  CALENDARIO_ID= "RubroProcesoWrapper.calendarioId";
    public final static String  COMPETENCIA_ID= "RubroProcesoWrapper.competenciaId";
    public final static String  TIPO_NOTA_ID= "RubroProcesoWrapper.tipoNotaId";
    public final static String  ESTRATEGIA_ID= "RubroProcesoWrapper.estrategiaId";
    public final static String  CURSO_ID= "RubroProcesoWrapper.cursoId";
    public final static String  SUB_TITULO = "RubroProcesoWrapper.subtitulo";


    private String titulo;
    private int tipoEvaluacionId;
    private int formaEvaluacionId;
    private ArrayList<Integer> campotematicoIds;
    private int indicadorId;
    private String rubroId;
    private int programaEducativoId;
    private int sesionAprendizajeId;
    private int silaboEventoId;
    private int calendarioId;
    private int competenciaId;
    private String tipoNotaId;
    private int estrategiaEvalId;
    private int cursoId;
    private String subtitulo;
    public String getTipoNotaId() {
        return tipoNotaId;
    }

    public void setTipoNotaId(String tipoNotaId) {
        this.tipoNotaId = tipoNotaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTipoEvaluacionId() {
        return tipoEvaluacionId;
    }

    public void setTipoEvaluacionId(int tipoEvaluacionId) {
        this.tipoEvaluacionId = tipoEvaluacionId;
    }

    public int getFormaEvaluacionId() {
        return formaEvaluacionId;
    }

    public void setFormaEvaluacionId(int formaEvaluacionId) {
        this.formaEvaluacionId = formaEvaluacionId;
    }

    public ArrayList<Integer> getCampotematicoIds() {
        return campotematicoIds;
    }

    public void setCampotematicoIds(ArrayList<Integer> campotematicoIds) {
        this.campotematicoIds = campotematicoIds;
    }

    public int getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(int indicadorId) {
        this.indicadorId = indicadorId;
    }

    public String getRubroId() {
        return rubroId;
    }

    public void setRubroId(String rubroId) {
        this.rubroId = rubroId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getCalendarioId() {
        return calendarioId;
    }

    public void setCalendarioId(int calendarioId) {
        this.calendarioId = calendarioId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getEstrategiaEvalId() {
        return estrategiaEvalId;
    }

    public void setEstrategiaEvalId(int estrategiaEvalId) {
        this.estrategiaEvalId = estrategiaEvalId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public Bundle convertBundle(Bundle bundle){
        bundle.putString(TITULO, titulo);
        bundle.putInt(FORMA_EVALUACION_ID, formaEvaluacionId);
        bundle.putInt(TIPO_EVALUACION_ID, tipoEvaluacionId);
        bundle.putString(RUBRO_ID, rubroId);
        bundle.putInt(INDICADOR_ID, indicadorId);
        bundle.putSerializable(CAMPOS_TEMATICOS_IDS, campotematicoIds);
        bundle.putInt(PROGRAMA_EDUCATIVO_ID, programaEducativoId);
        bundle.putInt(SESION_APRENDIZAJE_ID, sesionAprendizajeId);
        bundle.putInt(SILABO_EVENTO_ID, silaboEventoId);
        bundle.putInt(CALENDARIO_ID, calendarioId);
        bundle.putInt(COMPETENCIA_ID, competenciaId);
        bundle.putString(TIPO_NOTA_ID, tipoNotaId);
        bundle.putInt(ESTRATEGIA_ID, estrategiaEvalId);
        bundle.putInt(CURSO_ID, cursoId);
        bundle.putString(SUB_TITULO, subtitulo);
        return bundle;
    }
    public static RubroProcesoWrapper getBundle(Bundle bundle){
        RubroProcesoWrapper rubro= new RubroProcesoWrapper();
        rubro.setTitulo(bundle.getString(TITULO));
        rubro.setTipoEvaluacionId(bundle.getInt(TIPO_EVALUACION_ID));
        rubro.setFormaEvaluacionId(bundle.getInt(FORMA_EVALUACION_ID));
        rubro.setIndicadorId(bundle.getInt(INDICADOR_ID));
        rubro.setCampotematicoIds( (ArrayList<Integer>) bundle.getSerializable(CAMPOS_TEMATICOS_IDS));
        rubro.setRubroId(bundle.getString(RUBRO_ID));
        rubro.setProgramaEducativoId(bundle.getInt(PROGRAMA_EDUCATIVO_ID));
        rubro.setSesionAprendizajeId(bundle.getInt(SESION_APRENDIZAJE_ID));
        rubro.setSilaboEventoId(bundle.getInt(SILABO_EVENTO_ID));
        rubro.setCalendarioId(bundle.getInt(CALENDARIO_ID));
        rubro.setCompetenciaId(bundle.getInt(COMPETENCIA_ID));
        rubro.setTipoNotaId(bundle.getString(TIPO_NOTA_ID));
        rubro.setEstrategiaEvalId(bundle.getInt(ESTRATEGIA_ID));
        rubro.setCursoId(bundle.getInt(CURSO_ID));
        rubro.setSubtitulo(bundle.getString(SUB_TITULO));
        return rubro;

    }
}
