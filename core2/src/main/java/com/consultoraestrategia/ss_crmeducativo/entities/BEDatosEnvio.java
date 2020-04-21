package com.consultoraestrategia.ss_crmeducativo.entities;


import java.util.List;

/**
 * Created by irvinmarin on 13/04/2017.
 */

public class BEDatosEnvio {
    private List<AnioAcademico> anioAcademicos;//BEDatosEnvio
    private List<Contrato> contratos;//BEDatosEnvio
    private List<DetalleContratoAcad> detalleContratoAcad;//BEDatosEnvio
    private List<CargaAcademica> cargasAcademicas;//CARGA_ACADEMICA
    private List<CargaCursos> cargaCursos;//CARGA_ACADEMICA
    private List<Empleado> empleados;//CARGA_ACADEMICA
    private List<PlanCursos> planCursos;//CARGA_ACADEMICA
    private List<Cursos> cursos;//CARGA_ACADEMICA
    private List<Periodo> periodos;//CARGA_ACADEMICA
    private List<Seccion> secciones;//CARGA_ACADEMICA
    private List<Aula> aulas;//CARGA_ACADEMICA
    private List<CuentaCorriente> cuentaCorriente;//BEDatosEnvio
    private List<ProgramasEducativo> programasEducativos;//CARGA_ACADEMICA
    private List<NivelAcademico> nivelesAcademicos;//CARGA_ACADEMICA
    private List<PlanEstudios> planEstudios;//CARGA_ACADEMICA
    private List<PlanesEstudiosAlumno> planEstudiosAlumno;//CARGA_ACADEMICA
    private List<PlanCursosAlumno> planCursosAlumno;//BEDatosEnvio
    private List<Estados> estados;//BEDatosEnvio
    private List<Tipos> tipos;//BEDatosEnvio
    private List<Persona> personas;//BEDatosEnvio
    private List<Relaciones> relaciones;//BEDatosEnvio
    private List<Competencia> competencias;//SILABO_EVENTO
    private List<CursoCompetencia> cursoCompetencias;//BEDatosEnvio
    private List<CalendarioAcademico> calendarioAcademicos;//CARGA_ACADEMICA
    private List<CalendarioPeriodo> calendarioPeriodos;//BEDatosEnvio
    private List<DesarrolloCompetenciaCurso> desarrolloCompetenciaCursos;
    private List<EvaluacionCapacidad> evaluacionCapacidades;

    //--------------------------------------------------------
    private List<SilaboEvento> silaboEvento;//SILABO_EVENTO
    //-----------------indicadores---------------------------
    private List<Icds> icds;//SILABO_EVENTO
    private List<SesionEventoDesempenioIcd> rel_sesion_desempenio_icd;//NO vine nada eliminar
    private List<DesempenioIcd> rel_desempenio_icd;//SILABO_EVENTO
    private List<SesionEventoCompetenciaDesempenioIcd> rel_sesion_evento_competencia_desempenioicd;//SILABO_EVENTO
    //-------------------------------------------------------
    private List<RecursoDidacticoEventoC> recursoDidactico;//SILABO_EVENTO
    private List<TipoNotaC> tipoNota;//TIPO_NOTA
    private List<ValorTipoNotaC> valorTipoNota;//TIPO_NOTA
    private List<EscalaEvaluacion> escalaEvaluacion;//TIPO_NOTA
    private List<ColorCondicionalC> colorCondicional;//BEDatosEnvio

    private List<RubroEvaluacionResultado> rubroEvaluacionResultado;//RUBRO_EVAL_RESULTADO
    private List<RubroEvalRNRFormula> rubroEvalResultadoFormula;//RUBRO_EVAL_RESULTADO
    private List<RubroEvaluacionProcesoC> rubroEvaluacionProceso;//RUBOR_EVAL_PROCESO
    private List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula;//RUBOR_EVAL_PROCESO
    private List<EvaluacionResultadoC> evaluacionResultado;//RUBRO_EVAL_RESULTADO
    private List<EvaluacionProcesoC> evaluacionProceso;//RUBOR_EVAL_PROCESO

    //--------------------------------------------------------
    private List<Hora> hora;//HORARIO
    private List<HorarioPrograma> horarioPrograma;//HORARIO
    private List<HorarioHora> horarioHora;//HORARIO
    private List<DetalleHorario> detalleHorario;//HORARIO
    private List<CursosDetHorario> cursosDetHorario;//HORARIO
    private List<AsistenciaSesionAlumnoC> asistenciaAlumnos;//ASISTENCIA
    private List<Dia> dia;//HORARIO
    private List<Horario> horario;//HORARIO
    private List<HorarioDia> horarioDia;//HORARIO
    private List<ActividadAprendizaje> actividad;//SILABO_EVENTO
    private List<SesionAprendizaje> sesiones;//SILABO_EVENTO
    private List<CampoTematico> campoTematico;//SILABO_EVENTO
    private List<CompetenciaUnidad> competenciaUnidad;//SILABO_EVENTO
    private List<IndicarLogro> indicarLogro;//BEDatosEnvio
    private List<RecursoArchivo> recursoArchivo;//SILABO_EVENTO
    private List<SilaboCompetencia> silabocompetencia;//SILABO_EVENTO
    private List<UnidadTipo> unidadTipo;//SILABO_EVENTO
    private List<T_GC_REL_COMPETENCIA_SESION_EVENTO> competenciaSesion;//SILABO_EVENTO

    //-----------------------------------------------------
    private List<Intencion> intenciones;//MENSAJERIA
    private List<IntencionItem> intencionItems;//MENSAJERIA
    private List<ListaUsuario> listaUsuarios;//MENSAJERIA
    private List<ListaUsuarioDetalle> listUsuarioDetalle;//MENSAJERIA
    private List<CanalComunicacion> canalComunicacion;//MENSAJERIA
    private List<CanalDestinoEstadoC> canalDestinoEstado;//MENSAJERIA
    private List<MensajeC> mensajes;//MENSAJERIA
    private List<MensajeUsuarioC> mensajeUsuario;//MENSAJERIA
    private List<MensajeIntencionItemC> mensajeIntencionItem;//MENSAJERIA
    private List<UsuarioCanalComunicacion> usCanalComunicacion;//MENSAJERIA



    private List<UnidadAprendizaje> unidadAprendizaje;//SILABO_EVENTO
    private List<RecursoReferenciaC> recursoReferencia;//SILABO_EVENTO
    private List<Usuario> usuariosrelacionados;

    private List<UsuarioAcceso> accesos;

    private List<TareasC> tareas;//SILABO_EVENTO
    private List<TareasRecursosC> tareasRecursos;//SILABO_EVENTO
    private List<SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica> sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas;// SILABO_EVENTO con el nombre: sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas
    private List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> rel_unidad_apren_evento_tipo;//BEDatosEnvio
    private List<T_RN_MAE_TIPO_EVALUACION> rn_mae_tipo_evaluacion;//TIPO_NOTA
    private List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> rel_unidad_evento_competencia_desempenio_icd;//SILABO_EVENTO
    private List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> rel_unidad_evento_competencia_desempenio_icd_campo_tematico;//SILABO_EVENTO

    public List<SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica> getSesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas() {
        return sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas;
    }

    public void setSesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas(List<SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica> sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas) {
        this.sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas = sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas;
    }

    private List<SesionEventoDesempenioIcdCampotematico> sesion_desempenio_icd_campotematico;//SILABO_EVENTO
    private List<SilaboEventoCompentenciaDesempenioIcd> silaboeventocompetenciadesempenioicd;//SILABO_EVENTO
    private List<SilaboEventoDesempenioIcdCampotematico> silaboeventodesempenioicdcampotematico;//SILABO_EVENTO
    private List<ParametrosDisenio> obtener_parametros_disenio;//BEDatosEnvio


    public BEDatosEnvio() {
    }

    public List<SesionEventoCompetenciaDesempenioIcd> getRel_sesion_evento_competencia_desempenioicd() {
        return rel_sesion_evento_competencia_desempenioicd;
    }

    public List<ParametrosDisenio> getObtener_parametros_disenio() {
        return obtener_parametros_disenio;
    }

    public void setObtener_parametros_disenio(List<ParametrosDisenio> obtener_parametros_disenio) {
        this.obtener_parametros_disenio = obtener_parametros_disenio;
    }

    public void setRel_sesion_evento_competencia_desempenioicd(List<SesionEventoCompetenciaDesempenioIcd> rel_sesion_evento_competencia_desempenioicd) {
        this.rel_sesion_evento_competencia_desempenioicd = rel_sesion_evento_competencia_desempenioicd;
    }

    public List<EscalaEvaluacion> getEscalaEvaluacion() {
        return escalaEvaluacion;
    }

    public void setEscalaEvaluacion(List<EscalaEvaluacion> escalaEvaluacion) {
        this.escalaEvaluacion = escalaEvaluacion;
    }

    public List<TareasRecursosC> getTareasRecursos() {
        return tareasRecursos;
    }

    public void setTareasRecursos(List<TareasRecursosC> tareasRecursos) {
        this.tareasRecursos = tareasRecursos;
    }

    public List<TareasC> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareasC> tareas) {
        this.tareas = tareas;
    }

    public List<CanalDestinoEstadoC> getCanalDestinoEstado() {
        return canalDestinoEstado;
    }

    public void setCanalDestinoEstado(List<CanalDestinoEstadoC> canalDestinoEstado) {
        this.canalDestinoEstado = canalDestinoEstado;
    }

    public List<MensajeC> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<MensajeC> mensajes) {
        this.mensajes = mensajes;
    }

    public List<MensajeUsuarioC> getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(List<MensajeUsuarioC> mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public List<MensajeIntencionItemC> getMensajeIntencionItem() {
        return mensajeIntencionItem;
    }

    public void setMensajeIntencionItem(List<MensajeIntencionItemC> mensajeIntencionItem) {
        this.mensajeIntencionItem = mensajeIntencionItem;
    }

    public List<UsuarioAcceso> getAccesos() {
        return accesos;
    }

    public void setAccesos(List<UsuarioAcceso> accesos) {
        this.accesos = accesos;
    }

    public List<AnioAcademico> getAnioAcademicos() {
        return anioAcademicos;
    }

    public void setAnioAcademicos(List<AnioAcademico> anioAcademicos) {
        this.anioAcademicos = anioAcademicos;
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

    public List<CargaAcademica> getCargasAcademicas() {
        return cargasAcademicas;
    }

    public void setCargasAcademicas(List<CargaAcademica> cargasAcademicas) {
        this.cargasAcademicas = cargasAcademicas;
    }

    public List<CargaCursos> getCargaCursos() {
        return cargaCursos;
    }

    public void setCargaCursos(List<CargaCursos> cargaCursos) {
        this.cargaCursos = cargaCursos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<PlanCursos> getPlanCursos() {
        return planCursos;
    }

    public void setPlanCursos(List<PlanCursos> planCursos) {
        this.planCursos = planCursos;
    }

    public List<Cursos> getCursos() {
        return cursos;
    }

    public void setCursos(List<Cursos> cursos) {
        this.cursos = cursos;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<CuentaCorriente> getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(List<CuentaCorriente> cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public List<ProgramasEducativo> getProgramasEducativos() {
        return programasEducativos;
    }

    public void setProgramasEducativos(List<ProgramasEducativo> programasEducativos) {
        this.programasEducativos = programasEducativos;
    }

    public List<NivelAcademico> getNivelesAcademicos() {
        return nivelesAcademicos;
    }

    public void setNivelesAcademicos(List<NivelAcademico> nivelesAcademicos) {
        this.nivelesAcademicos = nivelesAcademicos;
    }

    public List<PlanEstudios> getPlanEstudios() {
        return planEstudios;
    }

    public void setPlanEstudios(List<PlanEstudios> planEstudios) {
        this.planEstudios = planEstudios;
    }

    public List<PlanesEstudiosAlumno> getPlanEstudiosAlumno() {
        return planEstudiosAlumno;
    }

    public void setPlanEstudiosAlumno(List<PlanesEstudiosAlumno> planEstudiosAlumno) {
        this.planEstudiosAlumno = planEstudiosAlumno;
    }

    public List<PlanCursosAlumno> getPlanCursosAlumno() {
        return planCursosAlumno;
    }

    public void setPlanCursosAlumno(List<PlanCursosAlumno> planCursosAlumno) {
        this.planCursosAlumno = planCursosAlumno;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<Relaciones> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<Relaciones> relaciones) {
        this.relaciones = relaciones;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public List<CursoCompetencia> getCursoCompetencias() {
        return cursoCompetencias;
    }

    public void setCursoCompetencias(List<CursoCompetencia> cursoCompetencias) {
        this.cursoCompetencias = cursoCompetencias;
    }

    public List<CalendarioAcademico> getCalendarioAcademicos() {
        return calendarioAcademicos;
    }

    public void setCalendarioAcademicos(List<CalendarioAcademico> calendarioAcademicos) {
        this.calendarioAcademicos = calendarioAcademicos;
    }

    public List<CalendarioPeriodo> getCalendarioPeriodos() {
        return calendarioPeriodos;
    }

    public void setCalendarioPeriodos(List<CalendarioPeriodo> calendarioPeriodos) {
        this.calendarioPeriodos = calendarioPeriodos;
    }

    public List<DesarrolloCompetenciaCurso> getDesarrolloCompetenciaCursos() {
        return desarrolloCompetenciaCursos;
    }

    public void setDesarrolloCompetenciaCursos(List<DesarrolloCompetenciaCurso> desarrolloCompetenciaCursos) {
        this.desarrolloCompetenciaCursos = desarrolloCompetenciaCursos;
    }

    public List<EvaluacionCapacidad> getEvaluacionCapacidades() {
        return evaluacionCapacidades;
    }

    public void setEvaluacionCapacidades(List<EvaluacionCapacidad> evaluacionCapacidades) {
        this.evaluacionCapacidades = evaluacionCapacidades;
    }

    public List<SilaboEvento> getSilaboEvento() {
        return silaboEvento;
    }

    public void setSilaboEvento(List<SilaboEvento> silaboEvento) {
        this.silaboEvento = silaboEvento;
    }

    public List<RecursoDidacticoEventoC> getRecursoDidactico() {
        return recursoDidactico;
    }

    public void setRecursoDidactico(List<RecursoDidacticoEventoC> recursoDidactico) {
        this.recursoDidactico = recursoDidactico;
    }

    public void setTipoNota(List<TipoNotaC> tipoNota) {
        this.tipoNota = tipoNota;
    }

    public void setValorTipoNota(List<ValorTipoNotaC> valorTipoNota) {
        this.valorTipoNota = valorTipoNota;
    }

    public List<TipoNotaC> getTipoNota() {
        return tipoNota;
    }

    public List<ValorTipoNotaC> getValorTipoNota() {
        return valorTipoNota;
    }

    public List<ColorCondicionalC> getColorCondicional() {
        return colorCondicional;
    }

    public void setColorCondicional(List<ColorCondicionalC> colorCondicional) {
        this.colorCondicional = colorCondicional;
    }

    public List<RubroEvaluacionResultado> getRubroEvaluacionResultado() {
        return rubroEvaluacionResultado;
    }

    public void setRubroEvaluacionResultado(List<RubroEvaluacionResultado> rubroEvaluacionResultado) {
        this.rubroEvaluacionResultado = rubroEvaluacionResultado;
    }

    public List<RubroEvalRNRFormula> getRubroEvalResultadoFormula() {
        return rubroEvalResultadoFormula;
    }

    public void setRubroEvalResultadoFormula(List<RubroEvalRNRFormula> rubroEvalResultadoFormula) {
        this.rubroEvalResultadoFormula = rubroEvalResultadoFormula;
    }

    public List<RubroEvaluacionProcesoC> getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public void setRubroEvaluacionProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public List<RubroEvalRNPFormulaC> getRubroEvalProcesoFormula() {
        return rubroEvalProcesoFormula;
    }

    public void setRubroEvalProcesoFormula(List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula) {
        this.rubroEvalProcesoFormula = rubroEvalProcesoFormula;
    }

    public List<EvaluacionResultadoC> getEvaluacionResultado() {
        return evaluacionResultado;
    }

    public void setEvaluacionResultado(List<EvaluacionResultadoC> evaluacionResultado) {
        this.evaluacionResultado = evaluacionResultado;
    }

    public List<EvaluacionProcesoC> getEvaluacionProceso() {
        return evaluacionProceso;
    }

    public void setEvaluacionProceso(List<EvaluacionProcesoC> evaluacionProceso) {
        this.evaluacionProceso = evaluacionProceso;
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

    public List<CursosDetHorario> getCursosDetHorario() {
        return cursosDetHorario;
    }

    public void setCursosDetHorario(List<CursosDetHorario> cursosDetHorario) {
        this.cursosDetHorario = cursosDetHorario;
    }

    public List<AsistenciaSesionAlumnoC> getAsistenciaAlumnos() {
        return asistenciaAlumnos;
    }

    public void setAsistenciaAlumnos(List<AsistenciaSesionAlumnoC> asistenciaAlumnos) {
        this.asistenciaAlumnos = asistenciaAlumnos;
    }

    public List<Dia> getDia() {
        return dia;
    }

    public void setDia(List<Dia> dia) {
        this.dia = dia;
    }

    public List<Horario> getHorario() {
        return horario;
    }

    public void setHorario(List<Horario> horario) {
        this.horario = horario;
    }

    public List<HorarioDia> getHorarioDia() {
        return horarioDia;
    }

    public void setHorarioDia(List<HorarioDia> horarioDia) {
        this.horarioDia = horarioDia;
    }

    public List<ActividadAprendizaje> getActividad() {
        return actividad;
    }

    public void setActividad(List<ActividadAprendizaje> actividad) {
        this.actividad = actividad;
    }

    public List<SesionAprendizaje> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionAprendizaje> sesiones) {
        this.sesiones = sesiones;
    }

    public List<CampoTematico> getCampoTematico() {
        return campoTematico;
    }

    public void setCampoTematico(List<CampoTematico> campoTematico) {
        this.campoTematico = campoTematico;
    }

    public List<CompetenciaUnidad> getCompetenciaUnidad() {
        return competenciaUnidad;
    }

    public void setCompetenciaUnidad(List<CompetenciaUnidad> competenciaUnidad) {
        this.competenciaUnidad = competenciaUnidad;
    }

    public List<IndicarLogro> getIndicarLogro() {
        return indicarLogro;
    }

    public void setIndicarLogro(List<IndicarLogro> indicarLogro) {
        this.indicarLogro = indicarLogro;
    }

    public List<RecursoArchivo> getRecursoArchivo() {
        return recursoArchivo;
    }

    public void setRecursoArchivo(List<RecursoArchivo> recursoArchivo) {
        this.recursoArchivo = recursoArchivo;
    }

    public List<SilaboCompetencia> getSilabocompetencia() {
        return silabocompetencia;
    }

    public void setSilabocompetencia(List<SilaboCompetencia> silabocompetencia) {
        this.silabocompetencia = silabocompetencia;
    }

    public List<UnidadTipo> getUnidadTipo() {
        return unidadTipo;
    }

    public void setUnidadTipo(List<UnidadTipo> unidadTipo) {
        this.unidadTipo = unidadTipo;
    }

    public List<T_GC_REL_COMPETENCIA_SESION_EVENTO> getCompetenciaSesion() {
        return competenciaSesion;
    }

    public void setCompetenciaSesion(List<T_GC_REL_COMPETENCIA_SESION_EVENTO> competenciaSesion) {
        this.competenciaSesion = competenciaSesion;
    }

    public List<Intencion> getIntenciones() {
        return intenciones;
    }

    public void setIntenciones(List<Intencion> intenciones) {
        this.intenciones = intenciones;
    }

    public List<IntencionItem> getIntencionItems() {
        return intencionItems;
    }

    public void setIntencionItems(List<IntencionItem> intencionItems) {
        this.intencionItems = intencionItems;
    }

    public List<ListaUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<ListaUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<ListaUsuarioDetalle> getListUsuarioDetalle() {
        return listUsuarioDetalle;
    }

    public void setListUsuarioDetalle(List<ListaUsuarioDetalle> listUsuarioDetalle) {
        this.listUsuarioDetalle = listUsuarioDetalle;
    }

    public List<CanalComunicacion> getCanalComunicacion() {
        return canalComunicacion;
    }

    public void setCanalComunicacion(List<CanalComunicacion> canalComunicacion) {
        this.canalComunicacion = canalComunicacion;
    }

    public List<UsuarioCanalComunicacion> getUsCanalComunicacion() {
        return usCanalComunicacion;
    }

    public void setUsCanalComunicacion(List<UsuarioCanalComunicacion> usCanalComunicacion) {
        this.usCanalComunicacion = usCanalComunicacion;
    }

    public List<UnidadAprendizaje> getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(List<UnidadAprendizaje> unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public List<RecursoReferenciaC> getRecursoReferencia() {
        return recursoReferencia;
    }

    public void setRecursoReferencia(List<RecursoReferenciaC> recursoReferencia) {
        this.recursoReferencia = recursoReferencia;
    }

    public List<Usuario> getUsuariosrelacionados() {
        return usuariosrelacionados;
    }

    public void setUsuariosrelacionados(List<Usuario> usuariosrelacionados) {
        this.usuariosrelacionados = usuariosrelacionados;
    }

    public List<Icds> getIcds() {
        return icds;
    }

    public void setIcds(List<Icds> icds) {
        this.icds = icds;
    }

    public List<SesionEventoDesempenioIcdCampotematico> getSesion_desempenio_icd_campotematico() {
        return sesion_desempenio_icd_campotematico;
    }

    public void setSesion_desempenio_icd_campotematico(List<SesionEventoDesempenioIcdCampotematico> sesion_desempenio_icd_campotematico) {
        this.sesion_desempenio_icd_campotematico = sesion_desempenio_icd_campotematico;
    }

    public List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> getRel_unidad_apren_evento_tipo() {
        return rel_unidad_apren_evento_tipo;
    }

    public List<T_RN_MAE_TIPO_EVALUACION> getRn_mae_tipo_evaluacion() {
        return rn_mae_tipo_evaluacion;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> getRel_unidad_evento_competencia_desempenio_icd() {
        return rel_unidad_evento_competencia_desempenio_icd;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> getRel_unidad_evento_competencia_desempenio_icd_campo_tematico() {
        return rel_unidad_evento_competencia_desempenio_icd_campo_tematico;
    }

    @Override
    public String toString() {
        return "BEDatosEnvio{" +
                "anioAcademicos=" + anioAcademicos +
                ", contratos=" + contratos +
                ", detalleContratoAcad=" + detalleContratoAcad +
                ", cargasAcademicas=" + cargasAcademicas +
                ", cargaCursos=" + cargaCursos +
                ", empleados=" + empleados +
                ", planCursos=" + planCursos +
                ", cursos=" + cursos +
                ", periodos=" + periodos +
                ", secciones=" + secciones +
                ", aulas=" + aulas +
                ", cuentaCorriente=" + cuentaCorriente +
                ", programasEducativos=" + programasEducativos +
                ", nivelesAcademicos=" + nivelesAcademicos +
                ", planEstudios=" + planEstudios +
                ", planEstudiosAlumno=" + planEstudiosAlumno +
                ", planCursosAlumno=" + planCursosAlumno +
                ", estados=" + estados +
                ", tipos=" + tipos +
                ", personas=" + personas +
                ", relaciones=" + relaciones +
                ", competencias=" + competencias +
                ", cursoCompetencias=" + cursoCompetencias +
                ", calendarioAcademicos=" + calendarioAcademicos +
                ", calendarioPeriodos=" + calendarioPeriodos +
                ", desarrolloCompetenciaCursos=" + desarrolloCompetenciaCursos +
                ", evaluacionCapacidades=" + evaluacionCapacidades +
                ", silaboEvento=" + silaboEvento +
                ", recursoDidactico=" + recursoDidactico +
                ", tipoNota=" + tipoNota +
                ", valorTipoNota=" + valorTipoNota +
                ", colorCondicional=" + colorCondicional +
                ", rubroEvaluacionResultado=" + rubroEvaluacionResultado +
                ", rubroEvalResultadoFormula=" + rubroEvalResultadoFormula +
                ", rubroEvaluacionProceso=" + rubroEvaluacionProceso +
                ", rubroEvalProcesoFormula=" + rubroEvalProcesoFormula +
                ", evaluacionResultado=" + evaluacionResultado +
                ", evaluacionProceso=" + evaluacionProceso +
                ", icds=" + icds +
                ", hora=" + hora +
                ", horarioPrograma=" + horarioPrograma +
                ", horarioHora=" + horarioHora +
                ", detalleHorario=" + detalleHorario +
                ", cursosDetHorario=" + cursosDetHorario +
                ", asistenciaAlumnos=" + asistenciaAlumnos +
                ", dia=" + dia +
                ", horario=" + horario +
                ", horarioDia=" + horarioDia +
                ", actividad=" + actividad +
                ", sesiones=" + sesiones +
                ", campoTematico=" + campoTematico +
                ", competenciaUnidad=" + competenciaUnidad +
                ", indicarLogro=" + indicarLogro +
                ", recursoArchivo=" + recursoArchivo +
                ", silabocompetencia=" + silabocompetencia +
                ", unidadTipo=" + unidadTipo +
                ", competenciaSesion=" + competenciaSesion +
                ", intenciones=" + intenciones +
                ", intencionItems=" + intencionItems +
                ", listaUsuarios=" + listaUsuarios +
                ", listUsuarioDetalle=" + listUsuarioDetalle +
                ", canalComunicacion=" + canalComunicacion +
                ", canalDestinoEstado=" + canalDestinoEstado +
                ", mensajes=" + mensajes +
                ", mensajeUsuario=" + mensajeUsuario +
                ", mensajeIntencionItem=" + mensajeIntencionItem +
                ", usCanalComunicacion=" + usCanalComunicacion +
                ", unidadAprendizaje=" + unidadAprendizaje +
                ", recursoReferencia=" + recursoReferencia +
                ", usuariosrelacionados=" + usuariosrelacionados +
                ", accesos=" + accesos +
                ", Rel_Unidad_Evento_Competencia_Desempenio_ICD=" + rel_unidad_evento_competencia_desempenio_icd +
                ", Rel_Unidad_Evento_Competencia_Desempenio_ICD_Campo_Tematico=" + rel_unidad_evento_competencia_desempenio_icd_campo_tematico +
                '}';
    }

    public List<SesionEventoDesempenioIcd> getRel_sesion_desempenio_icd() {
        return rel_sesion_desempenio_icd;
    }

    public List<DesempenioIcd> getRel_desempenio_icd() {
        return rel_desempenio_icd;
    }

    public List<SilaboEventoCompentenciaDesempenioIcd> getSilaboeventocompetenciadesempenioicd() {
        return silaboeventocompetenciadesempenioicd;
    }

    public List<SilaboEventoDesempenioIcdCampotematico> getSilaboeventodesempenioicdcampotematico() {
        return silaboeventodesempenioicdcampotematico;
    }
}
