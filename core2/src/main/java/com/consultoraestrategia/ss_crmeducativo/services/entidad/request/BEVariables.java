package com.consultoraestrategia.ss_crmeducativo.services.entidad.request;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by SCIEV on 12/06/2018.
 */
public class BEVariables implements Serializable {

    public final static String USUARIOID = "BEVariables.usuarioId";
    public final static String CARGACURSOID= "BEVariables.cargaCursoId";
    public final static String CALENDARIOPERIODOID = "BEVariables.calendarioPeriodoId";
    public final static String DOCENTEID = "BEVariables.docenteId";
    public final static String SILABOEVENTOID = "BEVariables.silavoEventoId";
    public final static String RUBROEVALUACIONID = "BEVariables.rubroEvaluacionId";
    public final static String SESIONAPRENDIZAJE = "BEVariables.sesionAprendizajeId";
    public final static String UNIDADAPRENDIZAJE = "BEVariables.unidadAprendizajeId";
    public final static String GRUPOEQUIPO = "BEVariables.grupoEquipoId";
    public final static String PROGRAMAEDUCATIVOID = "BEVariables.programaEducativoId";
    public final static String ANIOACADEMICOID = "BEVariables.anioAcademicoId";
    public final static String DATATIME = "BEVariables.dataTime";
    public final static String PAQUETE = "BEVariables.paquete";


    public int usuarioId;
    public int cargaCursoId;
    public int calendarioPeriodoId;
    public int docenteId;
    public int silavoEventoId;
    public String rubroEvaluacionId;
    private int sesionEventoId;
    private String grupoEquipoId;
    private int unidadAprendizajeId;
    private int programaEducativoId;
    private int anioAcademicoId;
    private long dataTime;
    private String paquete;

    public BEVariables() {

    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getSilavoEventoId() {
        return silavoEventoId;
    }

    public void setSilavoEventoId(int silavoEventoId) {
        this.silavoEventoId = silavoEventoId;
    }

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    public int getSesionEventoId() {
        return sesionEventoId;
    }

    public void setSesionEventoId(int sesionEventoId) {
        this.sesionEventoId = sesionEventoId;
    }

    public String getGrupoEquipoId() {
        return grupoEquipoId;
    }

    public void setGrupoEquipoId(String grupoEquipoId) {
        this.grupoEquipoId = grupoEquipoId;
    }

    public int getUnidadAprendizajeId() {
        return unidadAprendizajeId;
    }

    public void setUnidadAprendizajeId(int unidadAprendizajeId) {
        this.unidadAprendizajeId = unidadAprendizajeId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getAnioAcademicoId() {
        return anioAcademicoId;
    }

    public void setAnioAcademicoId(int anioAcademicoId) {
        this.anioAcademicoId = anioAcademicoId;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }

    @Override
    public String toString() {
        return "BEVariables{" +
                "usuarioId=" + usuarioId +
                ", cargaCursoId=" + cargaCursoId +
                ", calendarioPeriodoId=" + calendarioPeriodoId +
                ", docenteId=" + docenteId +
                ", silavoEventoId=" + silavoEventoId +
                ", rubroEvaluacionId='" + rubroEvaluacionId + '\'' +
                ", sesionEventoId=" + sesionEventoId +
                ", grupoEquipoId='" + grupoEquipoId + '\'' +
                ", unidadAprendizajeId=" + unidadAprendizajeId +
                ", programaEducativoId=" + programaEducativoId +
                ", anioAcademicoId=" + anioAcademicoId +
                ", dataTime=" + dataTime +
                ", paquete='" + paquete + '\'' +
                '}';
    }

    public void convertBundle(Bundle bundle){
        bundle.putInt(USUARIOID,usuarioId);
        bundle.putInt(CARGACURSOID,cargaCursoId);
        bundle.putInt(CALENDARIOPERIODOID,calendarioPeriodoId);
        bundle.putInt(DOCENTEID,docenteId);
        bundle.putInt(SILABOEVENTOID,silavoEventoId);
        bundle.putString(RUBROEVALUACIONID,rubroEvaluacionId);
        bundle.putInt(SESIONAPRENDIZAJE,sesionEventoId);
        bundle.putInt(UNIDADAPRENDIZAJE,unidadAprendizajeId);
        bundle.putString(GRUPOEQUIPO,grupoEquipoId);
        bundle.putInt(PROGRAMAEDUCATIVOID,programaEducativoId);
        bundle.putInt(ANIOACADEMICOID,anioAcademicoId);
        bundle.putLong(DATATIME,dataTime);
        bundle.putString(PAQUETE,paquete);
    }

    public static BEVariables getBundle(Bundle bundle){
        BEVariables beVariables = new BEVariables();
        beVariables.setUsuarioId(bundle.getInt(USUARIOID));
        beVariables.setCargaCursoId(bundle.getInt(CARGACURSOID));
        beVariables.setCalendarioPeriodoId(bundle.getInt(CALENDARIOPERIODOID));
        beVariables.setDocenteId(bundle.getInt(DOCENTEID));
        beVariables.setSilavoEventoId(bundle.getInt(SILABOEVENTOID));
        beVariables.setRubroEvaluacionId(bundle.getString(RUBROEVALUACIONID,""));
        beVariables.setSesionEventoId(bundle.getInt(SESIONAPRENDIZAJE));
        beVariables.setGrupoEquipoId(bundle.getString(GRUPOEQUIPO));
        beVariables.setUnidadAprendizajeId(bundle.getInt(UNIDADAPRENDIZAJE));
        beVariables.setProgramaEducativoId(bundle.getInt(PROGRAMAEDUCATIVOID));
        beVariables.setAnioAcademicoId(bundle.getInt(ANIOACADEMICOID));
        beVariables.setDataTime(bundle.getLong(DATATIME));
        beVariables.setPaquete(bundle.getString(PAQUETE));
        return beVariables;
    }
}
