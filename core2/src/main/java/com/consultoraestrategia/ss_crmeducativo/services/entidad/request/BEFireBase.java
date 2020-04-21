package com.consultoraestrategia.ss_crmeducativo.services.entidad.request;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by SCIEV on 12/06/2018.
 */
public class BEFireBase  {
    private long timestamp;
    private int calendarioPeriodoId;
    private int cargaCursoId;
    private int docenteId;
    private String rubroEvalProcesoId;
    private int usuarioId;
    private int silaboEventoId;
    private int sesionAprendizajeId;
    private int unidadAprendizajeId;
    private int programaEducativoId;
    private int anioAcademicoId;



    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    @Override
    public String toString() {
        return "BEFireBase{" +
                "timestamp=" + timestamp +
                ", calendarioPeriodoId=" + calendarioPeriodoId +
                ", cargaCursoId=" + cargaCursoId +
                ", docenteId=" + docenteId +
                ", rubroEvalProcesoId='" + rubroEvalProcesoId + '\'' +
                ", usuarioId=" + usuarioId +
                ", silaboEventoId=" + silaboEventoId +
                ", sesionAprendizajeId=" + sesionAprendizajeId +
                ", unidadAprendizajeId=" + unidadAprendizajeId +
                ", programaEducativoId=" + programaEducativoId +
                '}';
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
}
