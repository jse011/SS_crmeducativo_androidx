package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Hora;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioHora;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;

import java.util.List;

public class BEDatosAnioAcademico {

    private List<Aula> aulas;
    private List<CargaAcademica> cargasAcademicas;
    private List<CargaCursoDocente> cargaCursoDocente;
    private List<CargaCursoDocenteDet> cargaCursoDocenteDet;
    private List<CargaCursos> cargaCursos;
    private List<Cursos> cursos;
    private List<NivelAcademico> nivelesAcademicos;
    private List<ParametrosDisenio> parametrosDisenio;
    private List<Periodo> periodos;
    private List<PlanCursos> planCursos;
    private List<PlanEstudios> planEstudios;
    private List<ProgramasEducativo> programasEducativos;
    private List<Seccion> secciones;
    private List<SilaboEvento> silaboEvento;
    private List<CalendarioPeriodo> calendarioPeriodos;
    private List<CargaCursoCalendarioPeriodo> cargaCursoCalendarioPeriodo;
    private List<CalendarioPeriodoDetalle> calendarioPeriodoDetalles;
    private long fecha_servidor;
    private List<Tipos> tipos;
    private List<CalendarioAcademico> calendarioAcademico;
    private List<Rutas> rutas;

    private List<Horario> horario;
    private List<Hora> hora;
    private List<HorarioPrograma> horarioPrograma;
    private List<HorarioHora> horarioHora;
    private List<DetalleHorario> detalleHorario;
    private List<Dia> dia;
    private List<HorarioDia> horarioDia;
    private List<CursosDetHorario> cursosDetHorario;

    private List<WebConfig> bEWebConfigs;


    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<CargaAcademica> getCargasAcademicas() {
        return cargasAcademicas;
    }

    public void setCargasAcademicas(List<CargaAcademica> cargasAcademicas) {
        this.cargasAcademicas = cargasAcademicas;
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

    public List<CargaCursos> getCargaCursos() {
        return cargaCursos;
    }

    public void setCargaCursos(List<CargaCursos> cargaCursos) {
        this.cargaCursos = cargaCursos;
    }

    public List<Cursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
    }

    public List<NivelAcademico> getNivelesAcademicos() {
        return nivelesAcademicos;
    }

    public void setNivelesAcademicos(List<NivelAcademico> nivelesAcademicos) {
        this.nivelesAcademicos = nivelesAcademicos;
    }

    public List<ParametrosDisenio> getParametrosDisenio() {
        return parametrosDisenio;
    }

    public void setParametrosDisenio(List<ParametrosDisenio> parametrosDisenio) {
        this.parametrosDisenio = parametrosDisenio;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    public List<PlanCursos> getPlanCursos() {
        return planCursos;
    }

    public void setPlanCursos(List<PlanCursos> planCursos) {
        this.planCursos = planCursos;
    }

    public List<PlanEstudios> getPlanEstudios() {
        return planEstudios;
    }

    public void setPlanEstudios(List<PlanEstudios> planEstudios) {
        this.planEstudios = planEstudios;
    }

    public List<ProgramasEducativo> getProgramasEducativos() {
        return programasEducativos;
    }

    public void setProgramasEducativos(List<ProgramasEducativo> programasEducativos) {
        this.programasEducativos = programasEducativos;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public List<SilaboEvento> getSilaboEvento() {
        return silaboEvento;
    }

    public void setSilaboEvento(List<SilaboEvento> silaboEvento) {
        this.silaboEvento = silaboEvento;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public List<CalendarioPeriodo> getCalendarioPeriodos() {
        return calendarioPeriodos;
    }

    public void setCalendarioPeriodos(List<CalendarioPeriodo> calendarioPeriodos) {
        this.calendarioPeriodos = calendarioPeriodos;
    }

    public List<CargaCursoCalendarioPeriodo> getCargaCursoCalendarioPeriodo() {
        return cargaCursoCalendarioPeriodo;
    }

    public void setCargaCursoCalendarioPeriodo(List<CargaCursoCalendarioPeriodo> cargaCursoCalendarioPeriodo) {
        this.cargaCursoCalendarioPeriodo = cargaCursoCalendarioPeriodo;
    }

    public List<CalendarioPeriodoDetalle> getCalendarioPeriodoDetalles() {
        return calendarioPeriodoDetalles;
    }

    public void setCalendarioPeriodoDetalles(List<CalendarioPeriodoDetalle> calendarioPeriodoDetalles) {
        this.calendarioPeriodoDetalles = calendarioPeriodoDetalles;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<CalendarioAcademico> getCalendarioAcademico() {
        return calendarioAcademico;
    }

    public void setCalendarioAcademico(List<CalendarioAcademico> calendarioAcademico) {
        this.calendarioAcademico = calendarioAcademico;
    }

    public List<Rutas> getRutas() {
        return rutas;
    }

    public void setRutas(List<Rutas> rutas) {
        this.rutas = rutas;
    }

    public List<Hora> getHora() {
        return hora;
    }

    public void setHora(List<Hora> hora) {
        this.hora = hora;
    }

    public List<HorarioPrograma> getHorarioPrograma() {
        return horarioPrograma;
    }

    public void setHorarioPrograma(List<HorarioPrograma> horarioPrograma) {
        this.horarioPrograma = horarioPrograma;
    }

    public List<HorarioHora> getHorarioHora() {
        return horarioHora;
    }

    public void setHorarioHora(List<HorarioHora> horarioHora) {
        this.horarioHora = horarioHora;
    }

    public List<DetalleHorario> getDetalleHorario() {
        return detalleHorario;
    }

    public void setDetalleHorario(List<DetalleHorario> detalleHorario) {
        this.detalleHorario = detalleHorario;
    }

    public List<Dia> getDia() {
        return dia;
    }

    public void setDia(List<Dia> dia) {
        this.dia = dia;
    }

    public List<HorarioDia> getHorarioDia() {
        return horarioDia;
    }

    public void setHorarioDia(List<HorarioDia> horarioDia) {
        this.horarioDia = horarioDia;
    }

    public List<CursosDetHorario> getCursosDetHorario() {
        return cursosDetHorario;
    }

    public void setCursosDetHorario(List<CursosDetHorario> cursosDetHorario) {
        this.cursosDetHorario = cursosDetHorario;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public List<WebConfig> getWebConfigs() {
        return bEWebConfigs;
    }

    public void setWebConfigs(List<WebConfig> webConfigs) {
        this.bEWebConfigs = webConfigs;
    }
}
