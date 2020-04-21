package com.consultoraestrategia.ss_crmeducativo.main.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Georeferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Georeferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio_Table;
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
import com.consultoraestrategia.ss_crmeducativo.entities.Rol;
import com.consultoraestrategia.ss_crmeducativo.entities.Rol_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TecnicaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.TecnicaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumento;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CalendarioPeriodoModel;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetAccesosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetCursosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetHijosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetUsuarioCallback;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.DiasHorario;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioRolGeoReferenciaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class LocalDataSource implements MainDataSource {

    private static final String TAG = LocalDataSource.class.getSimpleName();
    private SessionUserDao sessionUserDao;
    private PersonaDao personaDao;
    private CalendarioPeriodoDao calendarioPeriodoDao;

    public LocalDataSource(SessionUserDao sessionUserDao, PersonaDao personaDao, CalendarioPeriodoDao calendarioPeriodoDao) {
        this.sessionUserDao = sessionUserDao;
        this.personaDao = personaDao;
        this.calendarioPeriodoDao = calendarioPeriodoDao;
    }

    @Override
    public boolean succesData() {
        try {
            SessionUser sessionUser = sessionUserDao.getCurrentUser();
            sessionUser.setDataImported(true);
            return sessionUser.save();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void getAccesosUIList(int idUsuario, int idHijo, GetAccesosListCallback callback) {
//        idHijo = 1720;
        Log.d(TAG, "GetAccesosListCallback idUsuario : " + idUsuario);
        Log.d(TAG, "GetAccesosListCallback idHijo : " + idHijo);
        /*PersonaUi persona = null;
        Usuario usuario = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.usuarioId.is(idUsuario))
                .querySingle();

        List<UsuarioAccesoUI> usuarioAccesoUIs = new ArrayList<>();
        /*List<UsuarioAcceso> usuarioAccesoList = SQLite.select()
                .from(UsuarioAcceso.class)
                .queryList();

        Log.d(TAG, "usuarioAccesoList : " + usuarioAccesoList.toString());

        PersonaUi personaUsuario = SQLite.select()
                .from(PersonaUi.class)
                .where(Persona_Table.personaId.is(usuario.getPersonaId()))
                .querySingle();

        for (UsuarioAcceso usuarioAcceso : usuarioAccesoList) {
            usuarioAccesoUIs.add(
                    new UsuarioAccesoUI(
                            usuarioAcceso.getId(),
                            usuarioAcceso.getAbreviacion(),
                            usuarioAcceso.getIcono(),
                            idUsuario,
                            personaUsuario.getPersonaId(),
                            null
                    )
            );
        }*/

        callback.onAccesosListLoaded(new ArrayList<UsuarioAccesoUI>());
    }



    @Override
    public void getHijosUIList(int idUsuario, int idHijo, GetHijosListCallback callback) {

        Usuario us = SQLite.select()
                .from(Usuario.class)
                .where(Usuario_Table.usuarioId.is(idUsuario))
                .querySingle();

        List<Persona> personaList = new ArrayList<>();
        if(us!=null) personaList.addAll(personaDao.getHijos(us.getPersonaId()));

        if (personaList.size() > 0) {
            callback.onHijosListLoaded(personaList);
        } else {
            callback.onError("No Tiene Hijos");
        }
    }

    @Override
    public void getCursosUIList(int idUsuario, int idPrograma, int idAnioAcademico, GetCursosListCallback callback) {
        Log.d(TAG, "idPrograma : " + idPrograma);
        Log.d(TAG, "idUsuario : " + idUsuario);
        Log.d(TAG, "idAnioAcademico : " + idAnioAcademico);


        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<CursosUI> cursosUIList = new ArrayList<>();
            List<CargaCursos> cargaCursosListToView = new ArrayList<>();

            Empleado empleado = SQLite.select(Utils.f_allcolumnTable(Empleado_Table.ALL_COLUMN_PROPERTIES))
                    .from(Empleado.class)
                    .innerJoin(Usuario.class)
                    .on(Empleado_Table.personaId.withTable()
                            .eq(Usuario_Table.personaId.withTable()))
                    .where(Usuario_Table.usuarioId.withTable().eq(idUsuario))
                    .querySingle(databaseWrapper);

            int empleadoId = empleado!=null ? empleado.getEmpleadoId(): 0;

            List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .innerJoin(CargaCursoDocente.class)
                    .on(CargaCursoDocente_Table.cargaCursoId.withTable()
                            .eq(CargaCursos_Table.cargaCursoId.withTable()))
                    .innerJoin(CargaAcademica.class)
                    .on(CargaCursos_Table.cargaAcademicaId.withTable()
                            .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                    .where(CargaCursoDocente_Table.docenteId.withTable().eq(empleadoId))
                    .and(CargaCursos_Table.complejo.withTable().eq(1))
                    .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(idAnioAcademico))
                    .queryList(databaseWrapper);


            cargaCursosList.addAll(SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .innerJoin(CargaAcademica.class)
                    .on(CargaCursos_Table.cargaAcademicaId.withTable()
                            .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                    .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                    .and(CargaCursos_Table.complejo.withTable().eq(0))
                    .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(idAnioAcademico))
                    .queryList(databaseWrapper));


                for (int i = 0; i < cargaCursosList.size(); i++) {
                    PlanCursos planCursos = SQLite.select()
                            .from(PlanCursos.class)
                            .where(PlanCursos_Table.planCursoId.is(cargaCursosList.get(i).getPlanCursoId()))
                            .querySingle(databaseWrapper);
                    if(planCursos == null){
                        Log.d(TAG,"error el plancurso con la carga curso id: "+ cargaCursosList.get(i).getCargaCursoId());
                        continue;
                    }


                    Cursos curso = SQLite.select()
                            .from(Cursos.class)
                            .where(Cursos_Table.cursoId.is(planCursos.getCursoId()))
                            .querySingle(databaseWrapper);

                    if(curso == null){
                        Log.d(TAG,"error el curso con la carga curso id: "+ cargaCursosList.get(i).getCargaCursoId());
                        continue;
                    }
                    Log.d(TAG, "cargaCursosList : " + cargaCursosList.get(i).getCargaCursoId());

                    CursosUI cursos = getDatosCursoUI(curso, planCursos, i, cargaCursosList.get(i), idPrograma, idAnioAcademico,empleado,databaseWrapper);

                    if (cursos != null) {
                        cargaCursosListToView.add(cargaCursosList.get(i));
                        cursosUIList.add(cursos);
                    }
                }


                Collections.sort(cursosUIList, new Comparator<CursosUI>() {
                    @Override
                    public int compare(CursosUI c1, CursosUI c2) {
                        try {
                            return c1.getNombreCurso().compareTo(c2.getNombreCurso());
                        }catch (Exception e){
                            return -1;
                        }
                    }
                });

            databaseWrapper.setTransactionSuccessful();
                callback.onCursosListLoaded(cursosUIList, cargaCursosListToView);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public void getUsuarioUI(GetUsuarioCallback callback) {
        Log.d(TAG, "getUsuarioUI");
        SessionUser user = SessionUser.getCurrentUser();
        if(user!=null){
            UsuarioUi usuarioUi = new UsuarioUi();
            usuarioUi.setUsuarioid(user.getUserId());
            usuarioUi.setSuccessData(user.isDataImported());
            List<UsuarioRolGeoreferencia> rolGeoreferencia = SQLite.select()
                    .from(UsuarioRolGeoreferencia.class)
                    .innerJoin(Entidad.class)
                    .on(UsuarioRolGeoreferencia_Table.entidadId.withTable()
                            .eq(Entidad_Table.entidadId.withTable()))
                    .where(UsuarioRolGeoreferencia_Table.usuarioId
                            .is(user.getUserId()))
                    .and(Entidad_Table.estadoId.eq(Entidad.ESTADO_AUTORIZADO))
                    .queryList();

            List<UsuarioRolGeoReferenciaUi> entidadUiList = new ArrayList<>();
            for (UsuarioRolGeoreferencia usuarioRolGeoreferencia: rolGeoreferencia){
                UsuarioRolGeoReferenciaUi entidadUi = new UsuarioRolGeoReferenciaUi();

                entidadUi.setId(usuarioRolGeoreferencia.getUsuarioRolGeoreferenciaId());
                entidadUi.setGeoreferenciaId(usuarioRolGeoreferencia.getGeoReferenciaId());
                entidadUi.setEntidadId(usuarioRolGeoreferencia.getEntidadId());
                Georeferencia georeferencia = SQLite.select()
                        .from(Georeferencia.class)
                        .where(Georeferencia_Table.georeferenciaId.eq(usuarioRolGeoreferencia.getGeoReferenciaId()))
                        .querySingle();
                if(georeferencia!=null){
                    entidadUi.setNombreGeoreferencia(georeferencia.getNombre());
                }else {
                    entidadUi.setNombreGeoreferencia("georeferencia desconocida");
                }

                Entidad entidad = SQLite.select()
                        .from(Entidad.class)
                        .where(Entidad_Table.entidadId.eq(usuarioRolGeoreferencia.getEntidadId()))
                        .querySingle();

                if(entidad!=null){
                    entidadUi.setNombreEntidad(entidad.getNombre());
                }else {
                    entidadUi.setNombreEntidad("entidad desconocida");
                }

                entidadUi.setRolid(usuarioRolGeoreferencia.getRolId());
                Rol rol = SQLite.select()
                        .from(Rol.class)
                        .where(Rol_Table.rolId.is(usuarioRolGeoreferencia.getRolId()))
                        .querySingle();
                if(rol!=null){
                    entidadUi.setNombreRol(rol.getNombre());
                }else {
                    entidadUi.setNombreEntidad("rol desconocido");
                }

                entidadUiList.add(entidadUi);
            }


            Persona persona = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.is(user.getPersonaId()))
                    .querySingle();

            Empleado empleado = SQLite.select()
                    .from(Empleado.class)
                    .where(Empleado_Table.personaId.eq(
                            persona==null ? 0: persona.getPersonaId()
                    ))
                    .querySingle();

            PersonaUi personaUi = null;
            if(persona!=null&&empleado!=null){
                personaUi = new PersonaUi();
                personaUi.setPersonaId(persona.getPersonaId());
                personaUi.setApellidos(persona.getApellidos());
                personaUi.setNombres(persona.getNombres());
                personaUi.setCorreo(persona.getCorreo());
                personaUi.setCelular(persona.getCelular());
                personaUi.setNombre(persona.getFirstName());
                personaUi.setFoto(persona.getFoto());
                personaUi.setEmpleadoId(empleado.getEmpleadoId());
            }

            usuarioUi.setEntidadUiList(entidadUiList);
            usuarioUi.setPersonaUi(personaUi);
            callback.onUsuarioLoaded(usuarioUi);
        }else {
            callback.onError("No se puedo encontrar datos Usuario");
        }
    }

    @Override
    public void getPeriodoList(int anioAcademicoId, int programaEducativoId, SucessCallback<List<PeriodoUi>> sucessCallback) {
        Log.d(TAG, "getPeriodoList : ");
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select(Utils.f_allcolumnTable(CalendarioPeriodo_Table.ALL_COLUMN_PROPERTIES))
                    .from(CalendarioPeriodo.class)
                    .innerJoin(CalendarioAcademico.class)
                    .on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()
                            .eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                    .innerJoin(AnioAcademico.class)
                    .on(CalendarioAcademico_Table.idAnioAcademico.withTable()
                            .eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                    .where(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                    .queryList();

            List<PeriodoUi> list = new ArrayList<>();
            int count=0;
            for (CalendarioPeriodo periodo :
                    calendarioPeriodoList) {

                Tipos tipo = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(periodo.getTipoId()))
                        .querySingle();


                String tipoNombre = "";
                if(tipo!=null)tipoNombre = tipo.getNombre();
                PeriodoUi periodoUi = new PeriodoUi(periodo.getCalendarioPeriodoId(),tipoNombre , "", false);
                list.add(periodoUi);
                //boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(periodo.getCalendarioPeriodoId(), 0, false, databaseWrapper);

                if (periodo.getEstadoId() == CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE){
                    periodoUi.setStatus(true);
                    count++;
                }


            }

            /*if (count>1){
                for (PeriodoUi periodoUi:list){
                    periodoUi.setStatus(false);
                }
                PeriodoUi firstPositionList = list.get(0);
                firstPositionList.setStatus(true);
            }*/


            databaseWrapper.setTransactionSuccessful();
            sucessCallback.onLoad(true,list );
        } catch (Exception e){
            e.printStackTrace();
            sucessCallback.onLoad(false,new ArrayList<PeriodoUi>() );
        }finally {
            databaseWrapper.endTransaction();
        }
    }


    private CursosUI getDatosCursoUI(Cursos curso, PlanCursos planCursos, int position, CargaCursos cargaCursos, int idPrograma, int idAnioAcademico, Empleado empleado, DatabaseWrapper databaseWrapper){
        CursosUI cursosUI = new CursosUI();
        PlanEstudios planEstudios = SQLite.select()
                .from(PlanEstudios.class)
                .where(PlanEstudios_Table.planEstudiosId.is(planCursos.getPlanEstudiosId()))
                .and(PlanEstudios_Table.programaEduId.is(idPrograma))
                .querySingle(databaseWrapper);

        if (planEstudios == null) return null;

        CargaAcademica cargaAcademica = SQLite.select(Utils.f_allcolumnTable(CargaAcademica_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaAcademica.class)
                .where(CargaAcademica_Table.cargaAcademicaId.is(cargaCursos.getCargaAcademicaId()))
                .and(CargaAcademica_Table.idPlanEstudio.is(planEstudios.getPlanEstudiosId()))
                .and(CargaAcademica_Table.idAnioAcademico.is(idAnioAcademico))
                .groupBy(Utils.f_allcolumnTable(CargaAcademica_Table.ALL_COLUMN_PROPERTIES))
                .querySingle(databaseWrapper);

        Empleado docenteCurso;

        cursosUI.setComplejo(cargaCursos.getComplejo() == 0);
        if(cargaCursos.getComplejo() == 0){
            docenteCurso = SQLite.select()
                            .from(Empleado.class)
                            .where(Empleado_Table.empleadoId.is(cargaCursos.getEmpleadoId()))
                            .querySingle(databaseWrapper);
        }else {
            docenteCurso = SQLite.select()
                    .from(Empleado.class)
                    .innerJoin(CargaCursoDocente.class)
                    .on(Empleado_Table.empleadoId.withTable()
                            .eq(CargaCursoDocente_Table.docenteId.withTable()))
                    .where(CargaCursoDocente_Table.cargaCursoId.withTable().eq(cargaCursos.getCargaCursoId()))
                    .and(CargaCursoDocente_Table.docenteId.withTable().eq(empleado.getEmpleadoId()))
                    .querySingle(databaseWrapper);
        }





        String nombreDocente = "";
        if (docenteCurso != null) {
            Persona personaDocente = SQLite.select()
                    .from(Persona.class)
                    .where(Persona_Table.personaId.is(docenteCurso.getPersonaId()))
                    .querySingle(databaseWrapper);

           if(personaDocente!=null) nombreDocente = personaDocente.getApellidoPaterno() + " " + personaDocente.getApellidoMaterno() + ", " + personaDocente.getNombres();

        } else {
            nombreDocente = "Faltan Datos Docente";
        }

        String diaHora = "";
        String salon = "";
        String gradoSeccion = null;
        int grupoAcademicoId = 0;
        int periodoAcademicoId = 0;
        boolean isTutor = false;

        if (cargaAcademica != null) {

            cursosUI.setCargaAcademicaId(cargaAcademica.getCargaAcademicaId());
            Log.d(TAG, "CargaAcedmica " + cargaAcademica.getCargaAcademicaId());
            Seccion seccion = SQLite.select()
                    .from(Seccion.class)
                    .where(Seccion_Table.seccionId.is(cargaAcademica.getSeccionId()))
                    .querySingle(databaseWrapper);

            Periodo periodos = SQLite.select()
                    .from(Periodo.class)
                    .where(Periodo_Table.periodoId.is(planCursos.getPeriodoId()))
                    .querySingle(databaseWrapper);

            String aliasPeriodo = "";
            String nombreSeccion = "";
            if(periodos != null)aliasPeriodo = periodos.getAlias();
            if(periodos != null)periodoAcademicoId = periodos.getPeriodoId();
            if(seccion != null)nombreSeccion = seccion.getNombre();
            if(seccion != null)grupoAcademicoId = seccion.getSeccionId();
            gradoSeccion = aliasPeriodo + " " + nombreSeccion;

            Aula aula = SQLite.select()
                    .from(Aula.class)
                    .where(Aula_Table.aulaId.is(cargaAcademica.getAulaId()))
                    .querySingle(databaseWrapper);
            if (aula != null) {
                salon = (aula.getDescripcion() + ": " + aula.getNumero() + " ");
            }

            if(empleado!=null)isTutor = cargaAcademica.getIdEmpleadoTutor() == empleado.getEmpleadoId();

        } else {
            gradoSeccion = "Datos Faltantes";
        }


        List<CursosDetHorario> cursosDetHorarioList = SQLite.select()
                .from(CursosDetHorario.class)
                .where(CursosDetHorario_Table.idCargaCurso.is(cargaCursos.getCargaCursoId()))
                .queryList(databaseWrapper);
        List<DiasHorario> diasHorarios = new ArrayList<>();

        if (cursosDetHorarioList.size() != 0) {
            cursosUI.setIdCursDetalleHorario(cursosDetHorarioList.get(0).getIdDetHorario());
        } else {
            cursosUI.setIdCursDetalleHorario(0);
        }

        Log.d(TAG, "Curso Detalle Horario " + cursosDetHorarioList.toString());


        String horarioCurso = "";

        /*for (CursosDetHorario cursosDetHorario : cursosDetHorarioList) {

            if (cursosDetHorario != null) {
                DetalleHorario detalleHorario = SQLite.select()
                        .from(DetalleHorario.class)
                        .where(DetalleHorario_Table.idDetalleHorario.is(cursosDetHorario.getIdDetHorario()))
                        .querySingle(databaseWrapper);
                if (detalleHorario != null) {
                    HorarioDia horarioDia = SQLite.select()
                            .from(HorarioDia.class)
                            .where(HorarioDia_Table.idHorarioDia.is(detalleHorario.getIdHorarioDia()))
                            .querySingle(databaseWrapper);
                    if (horarioDia != null) {
                        Dia dia = SQLite.select()
                                .from(Dia.class)
                                .where(Dia_Table.diaId.is(horarioDia.getIdDia()))
                                .querySingle(databaseWrapper);
                        if (dia != null) {


                            String horaInicio = detalleHorario.getHoraInicio();
                            String horaFin = detalleHorario.getHoraFin();

                            diaHora = (horaInicio.substring(0, 5) + " - " + horaFin.substring(0, 5));
                            diasHorarios.add(new DiasHorario(dia.getNombre() + ": ", diaHora));

                        }
                    }
                }
            }
        }
        Collections.sort(diasHorarios, new Comparator<DiasHorario>() {
            public int compare(DiasHorario obj1, DiasHorario obj2) {
                return obj1.getNombreDia().compareTo(obj2.getNombreDia());
            }
        });

        horarioCurso = diasHorarios.toString().substring(1, diasHorarios.toString().length() - 1);*/

        cursosUI.setImgVisible(false);
        cursosUI.setNombreDocenteVisible(false);

        SilaboEvento silaboEvento = SQLite.select()
                .from(SilaboEvento.class)
                .where(SilaboEvento_Table.cargaCursoId.is(cargaCursos.getCargaCursoId()))
                .querySingle(databaseWrapper);

        cursosUI.setIdCurso(curso.getCursoId());
        cursosUI.setNombreCurso(curso.getNombre());
        cursosUI.setGradoSeccion(gradoSeccion);
        cursosUI.setDiaHora(horarioCurso);
        cursosUI.setNroSalon(salon);
        cursosUI.setCargaCurso(cargaCursos.getCargaCursoId());
        cursosUI.setNombreDocente(nombreDocente);
        cursosUI.setGrupoAcademicoId(grupoAcademicoId);
        cursosUI.setPeriodoAcademicoId(periodoAcademicoId);
        cursosUI.setPlanEstudios(planEstudios.getNombrePlan());

        NivelAcademico nivelAcademico = SQLite.select(NivelAcademico_Table.entidadId.withTable(), NivelAcademico_Table.nivelAcadId.withTable(), NivelAcademico_Table.activo.withTable(), NivelAcademico_Table.nombre.withTable())
                .from(NivelAcademico.class)
                .innerJoin(ProgramasEducativo.class)
                .on(ProgramasEducativo_Table.nivelAcadId.withTable()
                        .eq(NivelAcademico_Table.nivelAcadId.withTable()))
                .where(ProgramasEducativo_Table.programaEduId.withTable()
                        .eq(planEstudios.getProgramaEduId()))
                .querySingle(databaseWrapper);

        if(nivelAcademico!=null)cursosUI.setNivelAcademico(nivelAcademico.getNombre());

        if(silaboEvento != null)cursosUI.setSilaboId(silaboEvento.getSilaboEventoId());
        ParametrosDisenio parametrosDisenio = null;
        if(silaboEvento != null)parametrosDisenio = SQLite.select()
                .from(ParametrosDisenio.class)
                .where(ParametrosDisenio_Table.parametroDisenioId.is(silaboEvento.getParametroDisenioId()))
                .querySingle(databaseWrapper);

        if (parametrosDisenio == null) {
                parametrosDisenio = SQLite.select()
                        .from(ParametrosDisenio.class)
                        .where(ParametrosDisenio_Table.nombre.eq("default"))
                        .querySingle(databaseWrapper);
        }

        if(parametrosDisenio != null){
            cursosUI.setBackgroundSolidColor(parametrosDisenio.getColor1());
            cursosUI.setUrlBackgroundItem(parametrosDisenio.getPath());
            cursosUI.setParametroDisenioId(parametrosDisenio.getParametroDisenioId());
        }

        if(silaboEvento != null){
            switch (silaboEvento.getEstadoId()){
                case SilaboEvento.ESTADO_CREADO:
                    //curso no autorizado
                    cursosUI.setEstado(CursosUI.Estado.CREADO);
                    break;
                default:
                    cursosUI.setEstado(CursosUI.Estado.AUTORIZADO);
                    break;
            }
        }else{
            cursosUI.setEstado(CursosUI.Estado.SINSILABO);
            //NO tinee progrma anual
        }
        int count = 0;
        List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursos.getCargaCursoId(),databaseWrapper);
        for (PersonaContratoQuery personaContratoQuery : personaList){
            if(personaContratoQuery.getVigente()==1)count++;
        }
        cursosUI.setCantidadPersonas(count);
        cursosUI.setTutor(isTutor);

        return cursosUI;
    }

    @Override
    public void getGradosList(int idPrograma, int idUsuario, int idAnioAcademico, SucessCallback<List<GradoUi>> sucessCallback) {

        List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                .from(CargaCursos.class)
                .innerJoin(Empleado.class)
                .on(CargaCursos_Table.empleadoId.withTable()
                        .eq(Empleado_Table.empleadoId.withTable()))
                .innerJoin(Usuario.class)
                .on(Empleado_Table.personaId.withTable()
                        .eq(Usuario_Table.personaId.withTable()))
                .where(Usuario_Table.usuarioId.is(idUsuario))
                .and(CargaCursos_Table.complejo.eq(0))
                .groupBy(CargaCursos_Table.cargaAcademicaId).queryList();


        List<GradoUi>gradoUiList= new ArrayList<>();

        for(CargaCursos cargaCursos: cargaCursosList){
            Log.d(TAG, "cargaacademicaId "+cargaCursos.getCargaAcademicaId());
            CargaAcademica cargaAcademica= SQLite.select().from(CargaAcademica.class)
                    .innerJoin(PlanEstudios.class)
                    .on(CargaAcademica_Table.idPlanEstudio.eq(PlanCursos_Table.planEstudiosId))
                    .where(CargaCursos_Table.cargaAcademicaId.eq(cargaCursos.getCargaAcademicaId()))
                    .and(PlanEstudios_Table.programaEduId.eq(idPrograma))
                    .and(CargaAcademica_Table.idAnioAcademico.eq(idAnioAcademico)).querySingle();

            if(cargaAcademica!=null){
                Seccion seccion= SQLite.select().from(Seccion.class).where(Seccion_Table.seccionId.eq(cargaAcademica.getSeccionId())).querySingle();

                Periodo periodo= SQLite.select().from(Periodo.class).where(Periodo_Table.periodoId.eq(cargaAcademica.getPeriodoId())).querySingle();


                GradoUi gradoUi= new GradoUi();
                gradoUi.setCargaAcademicaId(cargaAcademica.getCargaAcademicaId());

                if(periodo!=null){
                    gradoUi.setPeriodoId((int) periodo.getPeriodoId());
                    gradoUi.setPeriodo(periodo.getAlias());
                }

                if(seccion!=null){
                    gradoUi.setSeccionId(seccion.getSeccionId());
                    gradoUi.setSeccion(seccion.getNombre());
                }

                gradoUi.setDocenteId(cargaCursos.getEmpleadoId());
                gradoUiList.add(gradoUi);
            }

        }
        if(!gradoUiList.isEmpty())gradoUiList.get(0).setSeleted(true);

        sucessCallback.onLoad(true, gradoUiList);
    }


    @Override
    public boolean saveAlarma(int hora, int minute) {
        boolean success=false;
        try {
            SessionUser sesion = sessionUserDao.getCurrentUser();
                sesion.setHourSync(hora);
                sesion.setMinuteSync(minute);
            success = sesion.save();
        }catch (Exception e){
            e.getStackTrace();
        }
        return success;
    }

    @Override
    public AlarmaUi getHoraAlarma() {
        SessionUser sesion = sessionUserDao.getCurrentUser();
        AlarmaUi alarmaUi= null;
        if(sesion.getHourSync()!=-1){
            alarmaUi= new AlarmaUi();
            alarmaUi.setHora(sesion.getHourSync());
            alarmaUi.setMinute(sesion.getMinuteSync());
            String amPm="";
            if(sesion.getHourSync()<12)amPm= "AM";
            else amPm="PM";
            alarmaUi.setTiempo(amPm);
        }
        return alarmaUi;
    }

    @Override
    public List<AnioAcademicoUi> getListAnioAcademico(int usuarioId) {

        List<AnioAcademicoUi> anioAcademicoUis = new ArrayList<>();



       List<UsuarioRolGeoreferencia> usuarioRolGeoreferenciaList = SQLite.select()
               .from(UsuarioRolGeoreferencia.class)
               .where(UsuarioRolGeoreferencia_Table.usuarioId.eq(usuarioId))
               .queryList();

       List<Integer> gereferenciaIdList = new ArrayList<>();
       for (UsuarioRolGeoreferencia usuarioRolGeoreferencia: usuarioRolGeoreferenciaList){
           gereferenciaIdList.add(usuarioRolGeoreferencia.getGeoReferenciaId());
       }

        List<AnioAcademico> anioAcademicoList = SQLite.select()
                .from(AnioAcademico.class)
                .where(AnioAcademico_Table.georeferenciaId.in(gereferenciaIdList))
                .queryList();

        Collections.sort(anioAcademicoList, new Comparator<AnioAcademico>() {
            public int compare(AnioAcademico o1, AnioAcademico o2) {
                return Utils.convertirfecha(o2.getFechaFin()).compareTo(Utils.convertirfecha(o1.getFechaFin()));
            }
        });


        for (AnioAcademico anioAcademico : anioAcademicoList){
            AnioAcademicoUi anioAcademicoUi = new AnioAcademicoUi();
            anioAcademicoUi.setAnioAcademicoId(anioAcademico.getIdAnioAcademico());
            anioAcademicoUi.setNombre(anioAcademico.getNombre());
            anioAcademicoUi.setGeoreferencia(anioAcademico.getGeoreferenciaId());
            if(anioAcademico.getEstadoId()==AnioAcademico.ANIO_ACADEMICO_ACTIVO){
                anioAcademicoUi.setVigente(true);
            }
            anioAcademicoUi.setToogle(anioAcademico.isToogle());
            anioAcademicoUis.add(anioAcademicoUi);
        }

        return anioAcademicoUis;
    }

    @Override
    public List<ProgramaEduactivosUI> getListProgramaEducativo(int anioCademicoId, int usuarioId) {

        Log.d(TAG, "anioCademicoId: " + anioCademicoId + " usuarioId: " +usuarioId);
        List<ProgramaEduactivosUI> programaEduactivosUIs = new ArrayList<>();


        int empleadoId = 0;

        Empleado empleado = SQLite.select(Utils.f_allcolumnTable(Empleado_Table.ALL_COLUMN_PROPERTIES))
                .from(Empleado.class)
                .innerJoin(Usuario.class)
                .on(Empleado_Table.personaId.withTable()
                        .eq(Usuario_Table.personaId.withTable()))
                .where(Usuario_Table.usuarioId.withTable().eq(usuarioId))
                .querySingle();
        Log.d(TAG, "empleado: " + empleado.getEmpleadoId() );
        if(empleado!=null)empleadoId = empleado.getEmpleadoId();

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
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioCademicoId))
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
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioCademicoId))
                .queryList();

        cargaCursosList.addAll(cargaCursosComplejoList);

        Set<Integer> planCursoIdList = new LinkedHashSet<>();
        for (CargaCursos itemCargaCursos : cargaCursosList){
            planCursoIdList.add(itemCargaCursos.getPlanCursoId());
        }
        Log.d(TAG, "planCursoIdList: " + planCursoIdList);
        List<ProgramasEducativo> programasEducativoList = SQLite.select()
                .from(ProgramasEducativo.class)
                .innerJoin(PlanEstudios.class)
                .on(ProgramasEducativo_Table.programaEduId.withTable()
                        .eq(PlanEstudios_Table.programaEduId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(PlanCursos_Table.planEstudiosId.withTable()))
                .where(PlanCursos_Table.planCursoId.withTable()
                        .in(planCursoIdList))
                .and(ProgramasEducativo_Table.estadoId.withTable().eq(37))
                .groupBy(ProgramasEducativo_Table.programaEduId.withTable())
                .queryList();

        for (ProgramasEducativo programasEducativo: programasEducativoList){
            ProgramaEduactivosUI programaEduactivosUI = new ProgramaEduactivosUI();
            programaEduactivosUI.setIdPrograma(programasEducativo.getProgramaEduId());
            programaEduactivosUI.setNombrePrograma(programasEducativo.getNombre());
            programaEduactivosUI.setSeleccionado(programasEducativo.isToogle());
            programaEduactivosUIs.add(programaEduactivosUI);
        }

        return programaEduactivosUIs;

    }

    @Override
    public RetrofitCancel getDatosInicioSesion(int usuarioId, int anioId, SucessCallback<Boolean> callback) {
        return null;
    }

    @Override
    public RetrofitCancel updateListAnioAcademico(int usuarioId, SucessCallback<Boolean> callback) {
        return null;
    }

}
