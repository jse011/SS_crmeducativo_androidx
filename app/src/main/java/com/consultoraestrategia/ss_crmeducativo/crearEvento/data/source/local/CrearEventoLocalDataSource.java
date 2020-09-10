package com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.local;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AgendaUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CrearEventoLocalDataSource implements CrearEventoDataSource {

    private ParametrosDisenioDao parametrosDisenioDao;

    public CrearEventoLocalDataSource(ParametrosDisenioDao parametrosDisenioDao) {
        this.parametrosDisenioDao = parametrosDisenioDao;
    }

    @Override
    public List<AgendaUi> getListAgenda(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {
        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                .and(CargaCursos_Table.complejo.withTable().eq(0))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        List<CargaCursos> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);


        List<Integer> cargaCursoIdList = new ArrayList<>();
        for (CargaCursos cargaCursos : cargaCursosList){
            cargaCursoIdList.add(cargaCursos.getCargaCursoId());
        }

        List<CursoCustom> cursoCustomList = obtenerPorCargaCursos(cargaCursoIdList);
        Set<AgendaUi> agendaCargaCursoUis = new LinkedHashSet<>();
        Set<AgendaUi> agendaCargaAcademicaUis = new LinkedHashSet<>();
        for (CursoCustom cursoCustom : cursoCustomList){
            String nombreCurso = cursoCustom.getNombre();
            String seccionPerido = cursoCustom.getPeriodo() + " " + cursoCustom.getSeccion() + " - " + cursoCustom.getNivelAcademico();
            AgendaUi agendaCargaCursoUi = new AgendaUi();
            agendaCargaCursoUi.setId("CargaCurso_"+cursoCustom.getCargaCursoId());
            agendaCargaCursoUi.setNombre(nombreCurso);
            agendaCargaCursoUi.setDescripcion(seccionPerido);
            agendaCargaCursoUi.setCargaCursoId(cursoCustom.getCargaCursoId());

            AgendaUi agendaCargaAcademicoUi = new AgendaUi();
            agendaCargaAcademicoUi.setId("CargaAdemica_"+cursoCustom.getCargaAcademicaId());
            agendaCargaAcademicoUi.setNombre(seccionPerido);
            agendaCargaAcademicoUi.setCargaAcademicaId(cursoCustom.getCargaAcademicaId());


            ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(cursoCustom.getCargaCursoId());
            if(parametrosDisenio!=null){
                agendaCargaCursoUi.setColor1(parametrosDisenio.getColor1());
                agendaCargaCursoUi.setColor2(parametrosDisenio.getColor2());
                agendaCargaCursoUi.setColor3(parametrosDisenio.getColor3());

                agendaCargaAcademicoUi.setColor1(parametrosDisenio.getColor1());
                agendaCargaAcademicoUi.setColor2(parametrosDisenio.getColor2());
                agendaCargaAcademicoUi.setColor3(parametrosDisenio.getColor3());
            }

            agendaCargaCursoUis.add(agendaCargaCursoUi);
            agendaCargaAcademicaUis.add(agendaCargaAcademicoUi);
        }

        List<AgendaUi> agendaUiList =  new ArrayList<>();
        agendaUiList.addAll(agendaCargaAcademicaUis);
        agendaUiList.addAll(agendaCargaCursoUis);
        return agendaUiList;
    }

    @Override
    public List<AlumnoUi> getAlumnosCargaAcademica(int cargaAcademicaId, int empleadoId, String enventoId) {

        List<Contrato> contratoList = SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                .from(Contrato.class)
                .innerJoin(DetalleContratoAcad.class)
                .on(Contrato_Table.idContrato.withTable()
                        .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                .where(DetalleContratoAcad_Table.cargaAcademicaId.eq(cargaAcademicaId))
                .groupBy(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        List<AlumnoUi> alumnoUiList = new ArrayList<>();
        for (Contrato contrato : contratoList){
            Persona persona = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.eq(contrato.getPersonaId()))
                    .querySingle();
            if(persona!=null){
                AlumnoUi alumnoUi = new AlumnoUi();
                alumnoUi.setId(persona.getPersonaId());
                alumnoUi.setNombres(Utils.capitalize(persona.getFirstName()));
                alumnoUi.setApellidos(persona.getApellidos());
                alumnoUi.setFoto(persona.getFoto());
                alumnoUi.setApoderadoId(contrato.getApoderadoId());
                alumnoUiList.add(alumnoUi);
            }

        }

        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(enventoId))
                .querySingle();

        if(evento!=null){
            if(evento.isEnvioPersonalizado()){
                List<EventoPersona> eventoPersonaList = SQLite.select()
                        .from(EventoPersona.class)
                        .where(EventoPersona_Table.eventoId.eq(enventoId))
                        .and(EventoPersona_Table.estado.eq(true))
                        .queryList();

                for (EventoPersona eventoPersona: eventoPersonaList){
                    for (AlumnoUi alumnoUi : alumnoUiList){
                        if(alumnoUi.getId()==eventoPersona.getPersonaId()){
                                alumnoUi.setEnviarAlumno(true);
                        }

                        if(alumnoUi.getApoderadoId() == eventoPersona.getPersonaId()){
                            alumnoUi.setEnviarPadre(true);
                        }
                    }
                }

                List<Relaciones> relacionesList = SQLite.select()
                        .from(Relaciones.class)
                        .innerJoin(EventoPersona.class)
                        .on(Relaciones_Table.personaVinculadaId.withTable()
                                .eq(EventoPersona_Table.personaId.withTable()))
                        .where(EventoPersona_Table.eventoId.withTable().eq(enventoId))
                        .and(Relaciones_Table.activo.eq(true))
                        .and(EventoPersona_Table.estado.eq(true))
                        .queryList();

                for (Relaciones relacione: relacionesList){
                    for (AlumnoUi alumnoUi : alumnoUiList){
                        if(alumnoUi.getId()==relacione.getPersonaPrincipalId()){
                            alumnoUi.setEnviarPadre(true);
                        }
                    }
                }

            }else {
                Calendario calendario = SQLite.select()
                        .from(Calendario.class)
                        .where(Calendario_Table.calendarioId.eq(evento.getCalendarioId()))
                        .querySingle();
                boolean seleccionarAllAlumno = calendario != null && calendario.getRolId() == 6;
                boolean seleccionarAllDocentes = calendario != null && calendario.getRolId() == 5;

                for (AlumnoUi alumnoUi : alumnoUiList){
                    if(seleccionarAllAlumno){
                        alumnoUi.setEnviarAlumno(true);
                    }
                    if(seleccionarAllDocentes){
                        alumnoUi.setEnviarPadre(true);
                    }

                }

            }

        }

        return alumnoUiList;
    }

    @Override
    public String getNombreCargaAcademica(int cargaAcademicaId) {
        CursoCustom cursoCustom = SQLite.select(
                CargaAcademica_Table.cargaAcademicaId.withTable(),
                CargaAcademica_Table.seccionId.withTable(),
                CargaAcademica_Table.periodoId.withTable(),
                CargaAcademica_Table.aulaId.withTable(),
                CargaAcademica_Table.idPlanEstudio.withTable(),
                Seccion_Table.nombre.withTable().as("seccion"),
                Periodo_Table.alias.withTable().as("periodo"))
                .from(CargaAcademica.class)
                .innerJoin(Seccion.class)
                .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                .innerJoin(Periodo.class)
                .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(cargaAcademicaId))
                .queryCustomSingle(CursoCustom.class);

        String nombre = "";
        if(cursoCustom!=null){
            nombre = cursoCustom.getPeriodo() + " " + cursoCustom.getSeccion();
        }
        return nombre;
    }

    @Override
    public String getNombreCargaCurso(int cargaCursoId) {
        CursoCustom cursoCustom =  SQLite.select(
                Cursos_Table.cursoId.withTable(),
                Cursos_Table.alias.withTable(),
                Cursos_Table.entidadId.withTable(),
                Cursos_Table.estadoId.withTable(),
                Cursos_Table.nivelAcadId.withTable(),
                Cursos_Table.nombre.withTable(),
                Cursos_Table.tipoCursoId.withTable(),
                CargaCursos_Table.cargaCursoId.withTable(),
                CargaAcademica_Table.cargaAcademicaId.withTable(),
                CargaAcademica_Table.seccionId.withTable(),
                CargaAcademica_Table.periodoId.withTable(),
                CargaAcademica_Table.aulaId.withTable(),
                CargaAcademica_Table.idPlanEstudio.withTable(),
                Seccion_Table.nombre.withTable().as("seccion"),
                Periodo_Table.alias.withTable().as("periodo"),
                NivelAcademico_Table.nombre.withTable().as("nivelAcademico"))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaCursos_Table.cargaAcademicaId.withTable().eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                .innerJoin(Seccion.class)
                .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                .innerJoin(Periodo.class)
                .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(CargaAcademica_Table.idPlanEstudio.withTable().eq(PlanEstudios_Table.planEstudiosId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(PlanEstudios_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(NivelAcademico.class)
                .on(ProgramasEducativo_Table.nivelAcadId.withTable().eq(NivelAcademico_Table.nivelAcadId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(CargaCursos_Table.planCursoId.withTable().eq(PlanCursos_Table.planCursoId.withTable()))
                .innerJoin(Cursos.class)
                .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                .where(CargaCursos_Table.cargaCursoId.withTable().eq(cargaCursoId))
                .queryCustomSingle(CursoCustom.class);

        String nombre = "";
        if(cursoCustom!=null){
            nombre = cursoCustom.getNombre() + " " +cursoCustom.getPeriodo() + " " + cursoCustom.getSeccion();
        }
        return nombre;
    }

    @Override
    public List<AlumnoUi> getAlumnosCargaCurso(int cargaCursoId, int empleadoId, String enventoId) {
        List<Contrato> contratoList = SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                .from(Contrato.class)
                .innerJoin(DetalleContratoAcad.class)
                .on(Contrato_Table.idContrato.withTable()
                        .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                .where(DetalleContratoAcad_Table.cargaCursoId.eq(cargaCursoId))
                .groupBy(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                .queryList();

        List<AlumnoUi> alumnoUiList = new ArrayList<>();
        for (Contrato contrato : contratoList){
            Persona persona = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.eq(contrato.getPersonaId()))
                    .querySingle();
            if(persona!=null){
                AlumnoUi alumnoUi = new AlumnoUi();
                alumnoUi.setId(persona.getPersonaId());
                alumnoUi.setNombres(Utils.capitalize(persona.getFirstName()));
                alumnoUi.setApellidos(persona.getApellidos());
                alumnoUi.setFoto(persona.getFoto());
                alumnoUi.setApoderadoId(contrato.getApoderadoId());
                alumnoUiList.add(alumnoUi);
            }

        }

        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(enventoId))
                .querySingle();

        if(evento!=null){
            if(evento.isEnvioPersonalizado()){
                List<EventoPersona> eventoPersonaList = SQLite.select()
                        .from(EventoPersona.class)
                        .where(EventoPersona_Table.eventoId.eq(enventoId))
                        .and(EventoPersona_Table.estado.eq(true))
                        .queryList();

                for (EventoPersona eventoPersona: eventoPersonaList){
                    for (AlumnoUi alumnoUi : alumnoUiList){
                        if(alumnoUi.getId()==eventoPersona.getPersonaId()){
                            alumnoUi.setEnviarAlumno(true);
                        }

                        if(alumnoUi.getApoderadoId() == eventoPersona.getPersonaId()){
                            alumnoUi.setEnviarPadre(true);
                        }
                    }
                }

                List<Relaciones> relacionesList = SQLite.select()
                        .from(Relaciones.class)
                        .innerJoin(EventoPersona.class)
                        .on(Relaciones_Table.personaVinculadaId.withTable()
                                .eq(EventoPersona_Table.personaId.withTable()))
                        .where(EventoPersona_Table.eventoId.withTable().eq(enventoId))
                        .and(Relaciones_Table.activo.eq(true))
                        .and(EventoPersona_Table.estado.eq(true))
                        .queryList();

                for (Relaciones relacione: relacionesList){
                    for (AlumnoUi alumnoUi : alumnoUiList){
                        if(alumnoUi.getId()==relacione.getPersonaPrincipalId()){
                            alumnoUi.setEnviarPadre(true);
                        }
                    }
                }

            }else {
                Calendario calendario = SQLite.select()
                        .from(Calendario.class)
                        .where(Calendario_Table.calendarioId.eq(evento.getCalendarioId()))
                        .querySingle();
                boolean seleccionarAllAlumno = calendario != null && calendario.getRolId() == 6;
                boolean seleccionarAllDocentes = calendario != null && calendario.getRolId() == 5;

                for (AlumnoUi alumnoUi : alumnoUiList){
                    if(seleccionarAllAlumno){
                        alumnoUi.setEnviarAlumno(true);
                    }
                    if(seleccionarAllDocentes){
                        alumnoUi.setEnviarPadre(true);
                    }

                }

            }

        }

        return alumnoUiList;
    }

    private List<CursoCustom> obtenerPorCargaCursos(List<Integer> cargaCursoIdList) {
        return SQLite.select(
                Cursos_Table.cursoId.withTable(),
                Cursos_Table.alias.withTable(),
                Cursos_Table.entidadId.withTable(),
                Cursos_Table.estadoId.withTable(),
                Cursos_Table.nivelAcadId.withTable(),
                Cursos_Table.nombre.withTable(),
                Cursos_Table.tipoCursoId.withTable(),
                CargaCursos_Table.cargaCursoId.withTable(),
                CargaAcademica_Table.cargaAcademicaId.withTable(),
                CargaAcademica_Table.seccionId.withTable(),
                CargaAcademica_Table.periodoId.withTable(),
                CargaAcademica_Table.aulaId.withTable(),
                CargaAcademica_Table.idPlanEstudio.withTable(),
                Seccion_Table.nombre.withTable().as("seccion"),
                Periodo_Table.alias.withTable().as("periodo"),
                NivelAcademico_Table.nombre.withTable().as("nivelAcademico"))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaCursos_Table.cargaAcademicaId.withTable().eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                .innerJoin(Seccion.class)
                .on(CargaAcademica_Table.seccionId.withTable().eq(Seccion_Table.seccionId.withTable()))
                .innerJoin(Periodo.class)
                .on(CargaAcademica_Table.periodoId.withTable().eq(Periodo_Table.periodoId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(CargaAcademica_Table.idPlanEstudio.withTable().eq(PlanEstudios_Table.planEstudiosId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(PlanEstudios_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(NivelAcademico.class)
                .on(ProgramasEducativo_Table.nivelAcadId.withTable().eq(NivelAcademico_Table.nivelAcadId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(CargaCursos_Table.planCursoId.withTable().eq(PlanCursos_Table.planCursoId.withTable()))
                .innerJoin(Cursos.class)
                .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                .where(CargaCursos_Table.cargaCursoId.withTable().in(cargaCursoIdList))
                .queryCustomList(CursoCustom.class);
    }

    @Override
    public EventoUi getEventoDocente(String eventoId) {

        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(eventoId))
                .querySingle();
        EventoUi eventoUi =  new EventoUi();
        if(evento!=null){
            eventoUi.setId(evento.getEventoId());
            eventoUi.setCalendarioId(evento.getCalendarioId());
            eventoUi.setTipoEventoId(evento.getTipoEventoId());
            eventoUi.setNombre(evento.getTitulo());
            eventoUi.setDescripcion(evento.getDescripcion());
            eventoUi.setFecha(evento.getFechaEvento());
            eventoUi.setHora(evento.getHoraEvento());
            eventoUi.setPath(evento.getPathImagen());
            Calendario calendario = SQLite.select()
                    .from(Calendario.class)
                    .where(Calendario_Table.calendarioId.eq(evento.getCalendarioId()))
                    .querySingle();

            if(calendario!=null){
                eventoUi.setCargaAcademicaId(calendario.getCargaAcademicaId());
                eventoUi.setCargaCursoId(calendario.getCargaCursoId());
            }
        }
        return eventoUi;
    }

    @Override
    public List<TipoCalendarioUi> getListTipoCalendario(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {



        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                .and(CargaCursos_Table.complejo.withTable().eq(0))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        List<CargaCursos> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(CargaAcademica_Table.idAnioAcademico.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);

        List<TipoCalendarioUi> tipoCalendarioUiList = new ArrayList<>();

        List<Calendario> calendarioList = SQLite.select()
                .from(Calendario.class)
                .where(Calendario_Table.georeferenciaId.eq(georeferenciaId))
                .and(Calendario_Table.usuarioId.eq(usuarioId))
                .queryList();

        for (Calendario calendario: calendarioList){
            TipoCalendarioUi tipoCalendarioUi = new TipoCalendarioUi();
            tipoCalendarioUi.setId(calendario.getCalendarioId());
            tipoCalendarioUi.setNombre(calendario.getNombre());
            tipoCalendarioUi.setSelecionado(false);
            tipoCalendarioUiList.add(tipoCalendarioUi);
        }

        return tipoCalendarioUiList;
    }

    @Override
    public List<TipoEventoUi> getTipoEventos() {
        List<TipoEventoUi> tiposEventosUiList = new ArrayList<>();
        List<Tipos> tiposList = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.objeto.eq("T_CE_MOV_EVENTOS"))
                .and(Tipos_Table.concepto.eq("TipoEvento"))
                .queryList();

        for (Tipos tipos: tiposList) {
            TipoEventoUi tiposEventosUi = new TipoEventoUi();
            tiposEventosUi.setId(tipos.getTipoId());
            tiposEventosUi.setNombre(tipos.getNombre());
            tiposEventosUi.setSelecionado(false);
            tiposEventosUiList.add(tiposEventosUi);
        }

        return tiposEventosUiList;
    }

    @Override
    public RetrofitCancel saveEvento(EventoUi eventoUi, boolean publicar, Callback callback) {

        return null;
    }
}
