package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Caso;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoReporte;
import com.consultoraestrategia.ss_crmeducativo.entities.Comportamiento;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.GeoRefOrganigrama;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.Organigrama;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.PersonaGeoOrg;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanesEstudiosAlumno;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoEntidadGeo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosCargaAcademica extends BEDatosServidor {
    private List<CargaCursos> cargaCursos;
    private List<Contrato> contratos;
    private List<DetalleContratoAcad> detalleContratoAcad;
    public List<CargaCursoDocente> cargaCursoDocente;
    public List<CargaCursoDocenteDet> cargaCursoDocenteDet;
    private List<CargaAcademica> cargasAcademicas;
    private List<CalendarioPeriodo> calendarioPeriodos;
    private List<Empleado> empleados;
    private List<PlanCursos> planCursos;
    private List<Cursos> cursos;
    private List<Periodo> periodos;
    private List<Seccion> secciones;
    private List<CalendarioAcademico> calendarioAcademicos;
    private List<ProgramasEducativo> programasEducativos;
    private List<NivelAcademico> nivelesAcademicos;
    private List<PlanEstudios> planEstudios;//No es una tabla
    private List<PlanesEstudiosAlumno> planEstudiosAlumno;
    private List<Aula> aulas;
    private List<Archivo> archivo;
    private List<Caso> caso;
    private List<CasoArchivo> casoArchivo;
    private List<CasoReporte> casoReporte;
    private List<PersonaGeoOrg> personaGeoOrg;
    private List<Organigrama> organigrama;
    private List<GeoRefOrganigrama> geoRefOrganigrama;
    private List<TipoEntidadGeo> tipoEntidadGeo;
    private List<Comportamiento> comportamiento;
    private List<CargaCursoCalendarioPeriodo> obtenerCargaCursosCalendarioPeriodo;
    private List<CalendarioPeriodoDetalle> obtenerCalendarioPeriodoDetalle;
    private List<DimensionDesarrollo>dimensionDesarrollo;
    private List<DimensionDesarrolloDetalle>dimensionDesarrolloDetalle;
    private List<DimensionDesarrolloEstrategiaEvaluacionC>dimensionDesarrolloEstrategiaEval;
    private List<EstrategiaEvaluacion>estrategiaEvaluacion;

    public BEDatosCargaAcademica() {
    }


    public List<CargaCursos> getCargaCursos() {
        return cargaCursos;
    }

    public List<CargaAcademica> getCargasAcademicas() {
        return cargasAcademicas;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<PlanCursos> getPlanCursos() {
        return planCursos;
    }

    public List<Cursos> getCursos() {
        return cursos;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public List<CalendarioAcademico> getCalendarioAcademicos() {
        return calendarioAcademicos;
    }

    public List<ProgramasEducativo> getProgramasEducativos() {
        return programasEducativos;
    }

    public List<NivelAcademico> getNivelesAcademicos() {
        return nivelesAcademicos;
    }

    public List<PlanEstudios> getPlanEstudios() {
        return planEstudios;
    }

    public List<PlanesEstudiosAlumno> getPlanEstudiosAlumno() {
        return planEstudiosAlumno;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setCargaCursos(List<CargaCursos> cargaCursos) {
        this.cargaCursos = cargaCursos;
    }

    public void setCargasAcademicas(List<CargaAcademica> cargasAcademicas) {
        this.cargasAcademicas = cargasAcademicas;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setPlanCursos(List<PlanCursos> planCursos) {
        this.planCursos = planCursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public void setCalendarioAcademicos(List<CalendarioAcademico> calendarioAcademicos) {
        this.calendarioAcademicos = calendarioAcademicos;
    }

    public void setProgramasEducativos(List<ProgramasEducativo> programasEducativos) {
        this.programasEducativos = programasEducativos;
    }

    public void setNivelesAcademicos(List<NivelAcademico> nivelesAcademicos) {
        this.nivelesAcademicos = nivelesAcademicos;
    }

    public void setPlanEstudios(List<PlanEstudios> planEstudios) {
        this.planEstudios = planEstudios;
    }

    public void setPlanEstudiosAlumno(List<PlanesEstudiosAlumno> planEstudiosAlumno) {
        this.planEstudiosAlumno = planEstudiosAlumno;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public List<DetalleContratoAcad> getDetalleContratoAcad() {
        return detalleContratoAcad;
    }

    public void setDetalleContratoAcad(List<DetalleContratoAcad> detalleContratoAcad) {
        this.detalleContratoAcad = detalleContratoAcad;
    }

    public List<CalendarioPeriodo> getCalendarioPeriodos() {
        return calendarioPeriodos;
    }

    public void setCalendarioPeriodos(List<CalendarioPeriodo> calendarioPeriodos) {
        this.calendarioPeriodos = calendarioPeriodos;
    }

    public List<CargaCursoDocente> getCargaCursoDocente() {
        return cargaCursoDocente;
    }

    public void setCargaCursoDocente(List<CargaCursoDocente> cargaCursoDocente) {
        this.cargaCursoDocente = cargaCursoDocente;
    }

    public List<CargaCursoDocenteDet> getCargaCursoDocenteDet() {
        return cargaCursoDocenteDet;
    }

    public void setCargaCursoDocenteDet(List<CargaCursoDocenteDet> cargaCursoDocenteDet) {
        this.cargaCursoDocenteDet = cargaCursoDocenteDet;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

    public List<Caso> getCaso() {
        return caso;
    }

    public void setCaso(List<Caso> casos) {
        this.caso = casos;
    }

    public List<CasoArchivo> getCasoArchivo() {
        return casoArchivo;
    }

    public void setCasoArchivo(List<CasoArchivo> casoArchivo) {
        this.casoArchivo = casoArchivo;
    }

    public List<CasoReporte> getCasoReporte() {
        return casoReporte;
    }

    public void setCasoReporte(List<CasoReporte> casoReporte) {
        this.casoReporte = casoReporte;
    }

    public List<PersonaGeoOrg> getPersonaGeoOrg() {
        return personaGeoOrg;
    }

    public void setPersonaGeoOrg(List<PersonaGeoOrg> personaGeoOrg) {
        this.personaGeoOrg = personaGeoOrg;
    }

    public List<Organigrama> getOrganigrama() {
        return organigrama;
    }

    public void setOrganigrama(List<Organigrama> organigrama) {
        this.organigrama = organigrama;
    }

    public List<GeoRefOrganigrama> getGeoRefOrganigrama() {
        return geoRefOrganigrama;
    }

    public void setGeoRefOrganigrama(List<GeoRefOrganigrama> geoRefOrganigrama) {
        this.geoRefOrganigrama = geoRefOrganigrama;
    }

    public List<TipoEntidadGeo> getTipoEntidadGeo() {
        return tipoEntidadGeo;
    }

    public void setTipoEntidadGeo(List<TipoEntidadGeo> tipoEntidadGeo) {
        this.tipoEntidadGeo = tipoEntidadGeo;
    }

    public List<Comportamiento> getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(List<Comportamiento> comportamiento) {
        this.comportamiento = comportamiento;
    }

    public List<CargaCursoCalendarioPeriodo> getObtenerCargaCursosCalendarioPeriodo() {
        return obtenerCargaCursosCalendarioPeriodo;
    }

    public void setObtenerCargaCursosCalendarioPeriodo(List<CargaCursoCalendarioPeriodo> obtenerCargaCursosCalendarioPeriodo) {
        this.obtenerCargaCursosCalendarioPeriodo = obtenerCargaCursosCalendarioPeriodo;
    }

    public List<CalendarioPeriodoDetalle> getObtenerCalendarioPeriodoDetalle() {
        return obtenerCalendarioPeriodoDetalle;
    }

    public void setObtenerCalendarioPeriodoDetalle(List<CalendarioPeriodoDetalle> obtenerCalendarioPeriodoDetalle) {
        this.obtenerCalendarioPeriodoDetalle = obtenerCalendarioPeriodoDetalle;
    }

    public List<DimensionDesarrollo> getDimensionDesarrollo() {
        return dimensionDesarrollo;
    }

    public void setDimensionDesarrollo(List<DimensionDesarrollo> dimensionDesarrollo) {
        this.dimensionDesarrollo = dimensionDesarrollo;
    }

    public List<DimensionDesarrolloDetalle> getDimensionDesarrolloDetalle() {
        return dimensionDesarrolloDetalle;
    }

    public void setDimensionDesarrolloDetalle(List<DimensionDesarrolloDetalle> dimensionDesarrolloDetalle) {
        this.dimensionDesarrolloDetalle = dimensionDesarrolloDetalle;
    }

    public List<DimensionDesarrolloEstrategiaEvaluacionC> getDimensionDesarrolloEstrategiaEval() {
        return dimensionDesarrolloEstrategiaEval;
    }

    public void setDimensionDesarrolloEstrategiaEval(List<DimensionDesarrolloEstrategiaEvaluacionC> dimensionDesarrolloEstrategiaEval) {
        this.dimensionDesarrolloEstrategiaEval = dimensionDesarrolloEstrategiaEval;
    }

    public List<EstrategiaEvaluacion> getEstrategiaEvaluacion() {
        return estrategiaEvaluacion;
    }

    public void setEstrategiaEvaluacion(List<EstrategiaEvaluacion> estrategiaEvaluacion) {
        this.estrategiaEvaluacion = estrategiaEvaluacion;
    }

    @Override
    public String toString() {
        return "BEDatosCargaAcademica{" +
                "cargaCursos=" + cargaCursos +
                ", cargasAcademicas=" + cargasAcademicas +
                ", empleados=" + empleados +
                ", planCursos=" + planCursos +
                ", cursos=" + cursos +
                ", periodos=" + periodos +
                ", secciones=" + secciones +
                ", calendarioAcademicos=" + calendarioAcademicos +
                ", programasEducativos=" + programasEducativos +
                ", nivelesAcademicos=" + nivelesAcademicos +
                ", planEstudios=" + planEstudios +
                ", planEstudiosAlumno=" + planEstudiosAlumno +
                ", aulas=" + aulas +
                '}';
    }
}
