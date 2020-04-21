package com.consultoraestrategia.ss_crmeducativo.bundle;

import android.os.Bundle;


public class CRMBundle {

    private static final String PROGRAMA_EDUCATIVOID = CRMBundle.class.getSimpleName() + ".programaEducativoId";
    private static final String CARGA_CURSOID = CRMBundle.class.getSimpleName() + ".cargaCursoId";
    private static final String EMPLEADOID = CRMBundle.class.getSimpleName() + ".empleadoId";
    private static final String SILAVOEVENTOID = CRMBundle.class.getSimpleName() + ".silaboEventoId";
    private static final String CALENDARIOPERIODOID = CRMBundle.class.getSimpleName() + ".calendarioPeriodoId";
    private static final String RUBROEVALUACIONRESULTADOID = CRMBundle.class.getSimpleName() + ".rubroEvalResultadoId";
    private static final String SESIONAPRENDIZAJEID = CRMBundle.class.getSimpleName() + ".sesionAprendizajeId";
    private static final String PERSONAID = CRMBundle.class.getSimpleName() + ".personaId";
    private static final String CURSOID = CRMBundle.class.getSimpleName() + ".cursoId";
    private static final String TAREAID = CRMBundle.class.getSimpleName() + ".tareaId";
    private static final String CARGA_ACADEMICAID = CRMBundle.class.getSimpleName()+ ".cargaAcademicaId";
    private static final String GEOREFERENCIAID = CRMBundle.class.getSimpleName()+ ".georeferenciaqId";
    private static final String PARAMETRODISENIOID = CRMBundle.class.getSimpleName()+ ".parametroDisenioId";
    private static final String ENTIDAD_ID= CRMBundle.class.getSimpleName()+ ".entidadId";
    private static final String CALENDARIO_ACTIVO= CRMBundle.class.getSimpleName()+ ".calendarioActivo";
    private static final String GRUPO_ACADEMICO_ID= CRMBundle.class.getSimpleName()+ ".grupoAcademicoId";
    private static final String PERIODO_ACADEMICO_ID= CRMBundle.class.getSimpleName()+ ".periodoAcademicoId";
    private static final String ANIO_ACADEMICO_ID= CRMBundle.class.getSimpleName()+ ".anioAcademicoId";
    private static final String CALENDARIO_EDITAR = CRMBundle.class.getSimpleName()+ ".calendarioEditar";
    private static final String USUARIOID = CRMBundle.class.getSimpleName()+ ".usuarioId";
    private static final String CURSO_COMPLEJO = CRMBundle.class.getSimpleName()+ ".complejo";;

    private int programaEducativoId;
    private int cargaCursoId;
    private int empleadoId;
    private int silaboEventoId;
    private int calendarioPeriodoId;
    private int rubroEvalResultadoId;
    private int sesionAprendizajeId;
    private int personaId;
    private int cursoId;
    private String tareaId;
    private int cargaAcademicaId;
    private int georeferenciaId;
    private int parametroDisenioId;
    private int entidadId;
    private boolean calendarioActivo;//Por elminar no siempre van a venir usar el id del calendarioId
    private boolean calendarioEditar;//Por elminar no siempre van a venir usar el id del calendarioId
    private int grupoAcademicoId;
    private int periodAcademicoId;
    private int anioAcademico;
    private int usuarioId;
    private boolean complejo;

    public int getParametroDisenioId() {
        return parametroDisenioId;
    }

    public void setParametroDisenioId(int parametroDisenioId) {
        this.parametroDisenioId = parametroDisenioId;
    }

    public int getCargaAcademicaId() {
        return cargaAcademicaId;
    }

    public void setCargaAcademicaId(int cargaAcademicaId) {
        this.cargaAcademicaId = cargaAcademicaId;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
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

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public int getGeoreferenciaId() {
        return georeferenciaId;
    }

    public void setGeoreferenciaId(int georeferenciaId) {
        this.georeferenciaId = georeferenciaId;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public boolean isCalendarioActivo() {
        return calendarioActivo;
    }

    public void setCalendarioActivo(boolean calendarioActivo) {
        this.calendarioActivo = calendarioActivo;
    }

    public int getGrupoAcademicoId() {
        return grupoAcademicoId;
    }

    public void setGrupoAcademicoId(int grupoAcademicoId) {
        this.grupoAcademicoId = grupoAcademicoId;
    }

    public int getPeriodAcademicoId() {
        return periodAcademicoId;
    }

    public void setPeriodAcademicoId(int periodAcademicoId) {
        this.periodAcademicoId = periodAcademicoId;
    }

    public int getAnioAcademico() {
        return anioAcademico;
    }

    public void setAnioAcademico(int anioAcademico) {
        this.anioAcademico = anioAcademico;
    }

    public boolean isCalendarioEditar() {
        return calendarioEditar;
    }

    public void setCalendarioEditar(boolean calendarioEditar) {
        this.calendarioEditar = calendarioEditar;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public boolean isComplejo() {
        return complejo;
    }

    public void setComplejo(boolean complejo) {
        this.complejo = complejo;
    }

    public Bundle instanceBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt(PROGRAMA_EDUCATIVOID, programaEducativoId);
        bundle.putInt(CARGA_CURSOID, cargaCursoId);
        bundle.putInt(EMPLEADOID, empleadoId);
        bundle.putInt(SILAVOEVENTOID, silaboEventoId);
        bundle.putInt(CALENDARIOPERIODOID, calendarioPeriodoId);
        bundle.putInt(RUBROEVALUACIONRESULTADOID, rubroEvalResultadoId);
        bundle.putInt(SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(PERSONAID, personaId);
        bundle.putInt(CURSOID, cursoId);
        bundle.putString(TAREAID, tareaId);
        bundle.putInt(CARGA_ACADEMICAID, cargaAcademicaId);
        bundle.putInt(GEOREFERENCIAID, georeferenciaId);
        bundle.putInt(PARAMETRODISENIOID, parametroDisenioId);
        bundle.putInt(ENTIDAD_ID, entidadId);
        bundle.putBoolean(CALENDARIO_ACTIVO, calendarioActivo);
        bundle.putBoolean(CALENDARIO_EDITAR, calendarioEditar);
        bundle.putInt(GRUPO_ACADEMICO_ID, grupoAcademicoId);
        bundle.putInt(PERIODO_ACADEMICO_ID, periodAcademicoId);
        bundle.putInt(ANIO_ACADEMICO_ID, anioAcademico);
        bundle.putInt(USUARIOID, usuarioId);
        bundle.putBoolean(CURSO_COMPLEJO, complejo);
        return bundle;
    }

    public CRMBundle() {
    }

    public CRMBundle(Bundle bundle) {
        if(bundle==null)return;
        this.programaEducativoId = bundle.getInt(PROGRAMA_EDUCATIVOID,0);
        this.cargaCursoId = bundle.getInt(CARGA_CURSOID,0);
        this.empleadoId = bundle.getInt(EMPLEADOID, 0);
        this.silaboEventoId = bundle.getInt(SILAVOEVENTOID,0);
        this.calendarioPeriodoId = bundle.getInt(CALENDARIOPERIODOID, 0);
        this.rubroEvalResultadoId = bundle.getInt(RUBROEVALUACIONRESULTADOID, 0);
        this.sesionAprendizajeId = bundle.getInt(SESIONAPRENDIZAJEID, 0);
        this.personaId = bundle.getInt(PERSONAID, 0);
        this.cursoId = bundle.getInt(CURSOID, 0);
        this.tareaId = bundle.getString(TAREAID, null);
        this.cargaAcademicaId= bundle.getInt(CARGA_ACADEMICAID, 0);
        this.georeferenciaId = bundle.getInt(GEOREFERENCIAID,0);
        this.parametroDisenioId = bundle.getInt(PARAMETRODISENIOID, 0);
        this.entidadId=bundle.getInt(ENTIDAD_ID, 0);
        this.calendarioActivo =  bundle.getBoolean(CALENDARIO_ACTIVO);
        this.calendarioEditar =  bundle.getBoolean(CALENDARIO_EDITAR);
        this.grupoAcademicoId = bundle.getInt(GRUPO_ACADEMICO_ID,0);
        this.periodAcademicoId = bundle.getInt(PERIODO_ACADEMICO_ID, 0);
        this.anioAcademico = bundle.getInt(ANIO_ACADEMICO_ID, 0);
        this.usuarioId = bundle.getInt(USUARIOID, 0);
        this.complejo = bundle.getBoolean(CURSO_COMPLEJO, false);
    }

    @Override
    public String toString() {
        return "CRMBundle{" +
                "programaEducativoId=" + programaEducativoId +
                ", cargaCursoId=" + cargaCursoId +
                ", empleadoId=" + empleadoId +
                ", silaboEventoId=" + silaboEventoId +
                ", calendarioPeriodoId=" + calendarioPeriodoId +
                ", rubroEvalResultadoId=" + rubroEvalResultadoId +
                ", sesionAprendizajeId=" + sesionAprendizajeId +
                ", personaId=" + personaId +
                ", cursoId=" + cursoId +
                ", tareaId='" + tareaId + '\'' +
                ", cargaAcademicaId=" + cargaAcademicaId +
                ", georeferenciaId=" + georeferenciaId +
                ", parametroDisenioId=" + parametroDisenioId +
                ", entidadId=" + entidadId +
                ", calendarioActivo=" + calendarioActivo +
                ", calendarioEditar=" + calendarioEditar +
                ", grupoAcademicoId=" + grupoAcademicoId +
                ", periodAcademicoId=" + periodAcademicoId +
                ", anioAcademico=" + anioAcademico +
                ", usuarioId=" + usuarioId +
                ", complejo=" + complejo +
                '}';
    }
}
