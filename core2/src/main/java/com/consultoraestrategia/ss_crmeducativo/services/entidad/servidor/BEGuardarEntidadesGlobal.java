package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

public class BEGuardarEntidadesGlobal extends BEDatosServidor {

    public GEDatosRubroEvaluacionProceso rubroEvaluacionProceso;
    public GEDatosTareasRecursos tareaRecursos;
    public BEDatosSesionAprendizaje sesionAprendizaje;
    public GEDatosEnvioAsistencia asistencia;
    public BEDatosEnvioGrupo grupo;
    public BEDatosEnvioMensajeria mensajeria;
    public BEDatosCasos casos;
    public BEDatosCargaAcademica cargaAcademica;
    public int usuarioId;
    public int silaboId;
    public int perdiodoId;
    public int grupoId;
    public int calendarioPeriodoId;
    public int programaEducativoId;

    public GEDatosRubroEvaluacionProceso getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public void setRubroEvaluacionProceso(GEDatosRubroEvaluacionProceso rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public int getProgramaEducativoId() {
        return programaEducativoId;
    }

    public void setProgramaEducativoId(int programaEducativoId) {
        this.programaEducativoId = programaEducativoId;
    }

    public GEDatosTareasRecursos getTareaRecursos() {
        return tareaRecursos;
    }

    public void setTareaRecursos(GEDatosTareasRecursos tareaRecursos) {
        this.tareaRecursos = tareaRecursos;
    }

    public BEDatosSesionAprendizaje getSesionAprendizaje() {
        return sesionAprendizaje;
    }

    public void setSesionAprendizaje(BEDatosSesionAprendizaje sesionAprendizaje) {
        this.sesionAprendizaje = sesionAprendizaje;
    }

    public GEDatosEnvioAsistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(GEDatosEnvioAsistencia asistencia) {
        this.asistencia = asistencia;
    }

    public BEDatosEnvioGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(BEDatosEnvioGrupo grupo) {
        this.grupo = grupo;
    }

    public BEDatosEnvioMensajeria getMensajeria() {
        return mensajeria;
    }

    public void setMensajeria(BEDatosEnvioMensajeria mensajeria) {
        this.mensajeria = mensajeria;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BEDatosCasos getCasos() {
        return casos;
    }

    public void setCasos(BEDatosCasos casos) {
        this.casos = casos;
    }

    public BEDatosCargaAcademica getCargaAcademica() {
        return cargaAcademica;
    }

    public void setCargaAcademica(BEDatosCargaAcademica cargaAcademica) {
        this.cargaAcademica = cargaAcademica;
    }

    public int getSilaboId() {
        return silaboId;
    }

    public void setSilaboId(int silaboId) {
        this.silaboId = silaboId;
    }

    public int getPerdiodoId() {
        return perdiodoId;
    }

    public void setPerdiodoId(int perdiodoId) {
        this.perdiodoId = perdiodoId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    @Override
    public String toString() {
        return "BEGuardarEntidadesGlobal{" +
                "rubroEvaluacionProceso=" + rubroEvaluacionProceso +
                ", tareaRecursos=" + tareaRecursos +
                ", sesionAprendizaje=" + sesionAprendizaje +
                ", asistencia=" + asistencia +
                ", grupo=" + grupo +
                ", mensajeria=" + mensajeria +
                ", usuarioId=" + usuarioId +
                ", fecha_servidor=" +  fecha_servidor+
                '}';
    }
}
