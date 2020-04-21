package com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request.GenerarAsistenciaPorCargaAcademicaRequest;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request.GenerarAsistenciaPorCursoRequest;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dia.DiaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao.ValorTipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.PersonaContratoQuery;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class AsistenciaSesionAlumnoDaoImpl extends BaseDaoImpl<AsistenciaSesionAlumnoC, AsistenciaSesionAlumnoC_Table> implements AsistenciaSesionAlumnoDao {


    private PersonaDao personaDao;
    private DiaDao diaDao;
    private CalendarioPeriodoDao calendarioPeriodoDao;
    private static AsistenciaSesionAlumnoDao mInstance;
    private ValorTipoNotaDao valorTipoNotaDao;
    private TipoNotaDao tipoNotaDao;

    public AsistenciaSesionAlumnoDaoImpl(PersonaDao personaDao, DiaDao diaDao, CalendarioPeriodoDao calendarioPeriodoDao, ValorTipoNotaDao valorTipoNotaDao, TipoNotaDao tipoNotaDao) {
        this.personaDao = personaDao;
        this.diaDao = diaDao;
        this.calendarioPeriodoDao = calendarioPeriodoDao;
        this.valorTipoNotaDao = valorTipoNotaDao;
        this.tipoNotaDao = tipoNotaDao;
    }

    public static AsistenciaSesionAlumnoDao getInstance(PersonaDao personaDao, DiaDao diaDao, CalendarioPeriodoDao calendarioPeriodoDao, ValorTipoNotaDao valorTipoNotaDao, TipoNotaDao tipoNotaDao) {
        if (mInstance == null) {
            mInstance = new AsistenciaSesionAlumnoDaoImpl(personaDao, diaDao, calendarioPeriodoDao, valorTipoNotaDao, tipoNotaDao);
        }
        return mInstance;
    }

    @Override
    protected Class<AsistenciaSesionAlumnoC> getEntityClass() {
        return AsistenciaSesionAlumnoC.class;
    }

    @Override
    protected Class<AsistenciaSesionAlumnoC_Table> getTableclass() {
        return AsistenciaSesionAlumnoC_Table.class;
    }

    @Override
    public boolean generarAsistenciaPorCurso(GenerarAsistenciaPorCursoRequest request) {
        boolean success = true;
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeCargaCurso(request.getCargaCursoId());
            List<AsistenciaSesionAlumnoC> asistenciaSesionAlumnoCS =  new ArrayList<>();
            CargaAcademica cargaAcademica = SQLite.select(Utils.f_allcolumnTable(CargaAcademica_Table.ALL_COLUMN_PROPERTIES))
                            .from(CargaAcademica.class)
                            .innerJoin(CargaCursos.class)
                            .on(CargaAcademica_Table.cargaAcademicaId.withTable()
                                    .eq(CargaCursos_Table.cargaAcademicaId.withTable()))
                            .where(CargaCursos_Table.cargaCursoId.withTable().eq(request.getCargaCursoId()))
                            .querySingle();

            if(cargaAcademica==null) return false;

            TipoNotaC tipoNotaC = tipoNotaDao.getValorAsistencia(request.getProgramaEducativoId());


            if (tipoNotaC==null) return false;

            ValorTipoNotaC valorTipoNotaC = valorTipoNotaDao.getValorTipoNotaPorTipoNota(tipoNotaC.getTipoNotaId(), request.getEstadoAsistenciaValorTipoNota());

            if (valorTipoNotaC==null) return false;
            for(PersonaContratoQuery persona: personaList){
                AsistenciaSesionAlumnoC asistenciaSesionAlumnoC = new AsistenciaSesionAlumnoC();
                asistenciaSesionAlumnoC.setKey(IdGenerator.generateId());
                asistenciaSesionAlumnoC.setAlumnoId(persona.getPersonaId());
                asistenciaSesionAlumnoC.setCalendarioPeriodoId(request.getCalendarioPeridoId());
                asistenciaSesionAlumnoC.setCargaCursoId(request.getCargaCursoId());
                asistenciaSesionAlumnoC.setDocenteId(request.getEmpleadoId());
                asistenciaSesionAlumnoC.setGeoreferenciaId(request.getGeorefenciaId());
                asistenciaSesionAlumnoC.setPeriodoId(cargaAcademica.getPeriodoId());
                asistenciaSesionAlumnoC.setGrupoId(cargaAcademica.getSeccionId());
                asistenciaSesionAlumnoC.setValorTipoNotaId(valorTipoNotaC.getValorTipoNotaId());
                asistenciaSesionAlumnoC.setFechaAsistencia(cargaAcademica.getPeriodoId());
                asistenciaSesionAlumnoC.setFechaAsistencia(request.getFechaAsistencia());
                asistenciaSesionAlumnoC.setHora(request.getHora());
                asistenciaSesionAlumnoC.setSyncFlag(BaseEntity.FLAG_ADDED);
                asistenciaSesionAlumnoCS.add(asistenciaSesionAlumnoC);
            }

            FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(getEntityClass()))
                    .addAll(asistenciaSesionAlumnoCS)
                    .build()
                    .execute(databaseWrapper);

            databaseWrapper.setTransactionSuccessful();
        } catch (Exception error){
            error.printStackTrace();
            success = false;
        }finally {
            databaseWrapper.endTransaction();
        }
        return success;
    }

    @Override
    public boolean generarAsistenciaPorCargaAcademica(GenerarAsistenciaPorCargaAcademicaRequest request) {
        boolean success = true;
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            List<CargaCursos> cargaCursos= SQLite.select().from(CargaCursos.class).
                    where(CargaCursos_Table.cargaAcademicaId.withTable().eq(request.getCargaAcademicaId())).queryList();
            List<PersonaContratoQuery> personaList = new ArrayList<>();
            for(CargaCursos cargaCursos1:cargaCursos ){
                Log.d(getClass().getSimpleName(), " cargaCurso "+ cargaCursos1.getCargaCursoId());
                List<PersonaContratoQuery> personas= personaDao.getAlumnosDeCargaCurso(cargaCursos1.getCargaCursoId());
                for(PersonaContratoQuery persona: personas){
                   int position= personaList.indexOf(persona);
                   if(position==-1)personaList.add(persona);
                }
            }
            CargaAcademica cargaAcademica= SQLite.select().from(CargaAcademica.class)
                    .where(CargaAcademica_Table.cargaAcademicaId.withTable().eq(request.getCargaAcademicaId())).querySingle();
            Log.d(getClass().getSimpleName(), "cargaAacademica " +cargaAcademica.getCargaAcademicaId());
            if(cargaAcademica==null) return false;

            TipoNotaC tipoNotaC = tipoNotaDao.getValorAsistencia(request.getProgramaEducativoId());

            ValorTipoNotaC valorTipoNotaC = valorTipoNotaDao.getValorTipoNotaPorTipoNota(tipoNotaC.getTipoNotaId(), request.getEstadoAsistenciaValorTipoNota());

            if (valorTipoNotaC==null) return false;
            Log.d(getClass().getSimpleName(), "personas" + personaList.size());
            List<AsistenciaSesionAlumnoC> asistenciaSesionAlumnoCS =  new ArrayList<>();
            for(PersonaContratoQuery persona: personaList){
                AsistenciaSesionAlumnoC asistenciaSesionAlumnoC = new AsistenciaSesionAlumnoC();
                asistenciaSesionAlumnoC.setKey(IdGenerator.generateId());
                asistenciaSesionAlumnoC.setAlumnoId(persona.getPersonaId());
                asistenciaSesionAlumnoC.setCalendarioPeriodoId(request.getCalendarioPeridoId());
                asistenciaSesionAlumnoC.setDocenteId(request.getEmpleadoId());
                asistenciaSesionAlumnoC.setGeoreferenciaId(request.getGeorefenciaId());
                asistenciaSesionAlumnoC.setPeriodoId(cargaAcademica.getPeriodoId());
                asistenciaSesionAlumnoC.setGrupoId(cargaAcademica.getSeccionId());
                asistenciaSesionAlumnoC.setValorTipoNotaId(valorTipoNotaC.getValorTipoNotaId());
                asistenciaSesionAlumnoC.setFechaAsistencia(cargaAcademica.getPeriodoId());
                asistenciaSesionAlumnoC.setFechaAsistencia(request.getFechaAsistencia());
                asistenciaSesionAlumnoC.setCargaCursoId(0);
                asistenciaSesionAlumnoC.setHora(request.getHora());
                asistenciaSesionAlumnoC.setSyncFlag(BaseEntity.FLAG_ADDED);
                asistenciaSesionAlumnoCS.add(asistenciaSesionAlumnoC);
            }

            FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(getEntityClass()))
                    .addAll(asistenciaSesionAlumnoCS)
                    .build()
                    .execute(databaseWrapper);

            databaseWrapper.setTransactionSuccessful();
        } catch (Exception error){
            error.printStackTrace();
            success = false;
        }finally {
            databaseWrapper.endTransaction();
        }
        return success;
    }

    @Override
    public List<AsistenciaSesionAlumnoC> getAsistenciaPorCalendarioPeriodo(int cargaCursoId, int calendarioPeriodoId, int cargaAcademicaId) {
        /*cargaCursoId 1328
        calendarioPeriodoId 4
        cargaAcademicaId 102

        cargaCursoId 0
        calendarioPeriodoId 4
        cargaAcademicaId 102*/


        List<AsistenciaSesionAlumnoC> lista = new ArrayList<>();
        CargaAcademica cargaAcademica= SQLite.select()
                .from(CargaAcademica.class)
                .where(CargaAcademica_Table.cargaAcademicaId.withTable()
                        .eq(cargaAcademicaId))
                .querySingle();

        if(cargaAcademica!=null){
            Where<AsistenciaSesionAlumnoC> where = SQLite.select()
                    .from(AsistenciaSesionAlumnoC.class)
                    .where(AsistenciaSesionAlumnoC_Table.calendarioPeriodoId.eq(calendarioPeriodoId));

            if(cargaCursoId!=0){
                where.and(AsistenciaSesionAlumnoC_Table.cargaCursoId.eq(cargaCursoId));
            }else {
                where.and(AsistenciaSesionAlumnoC_Table.cargaCursoId.eq(0));
                where.or(AsistenciaSesionAlumnoC_Table.cargaCursoId.isNull());
            }


            where.and(AsistenciaSesionAlumnoC_Table.periodoId.withTable().eq(cargaAcademica.getPeriodoId()))
                    .and(AsistenciaSesionAlumnoC_Table.grupoId.withTable().eq(cargaAcademica.getSeccionId()));
            lista.addAll(where.queryList());
        }

        return lista ;
    }

    @Override
    public List<Long> getFechaAsistenciaPorCalendarioPeriodo(List<Integer> cargaCursoId, int calendarioPeriodoId, int anioAcademicoId) {
        CalendarioPeriodo calendarioPeriodo = calendarioPeriodoDao.get(calendarioPeriodoId);
        if(calendarioPeriodo==null)return new ArrayList<>();
        return getDiasCargaCurso(new Date(calendarioPeriodo.getFechaInicio()), new Date(calendarioPeriodo.getFechaFin()), cargaCursoId, anioAcademicoId);
    }

    private List<Long> getDiasCargaCurso(Date fechaActual, Date fechaFin, List<Integer> cargaCursoId, int anioAcademicoId){
        List<Long> longList = new ArrayList<>();
        List<Date> dateList = Utils.getDaysBetweenDates(fechaActual,fechaFin);
        List<Dia> diaList = diaDao.obtenerPorCargaCursoId(cargaCursoId, anioAcademicoId);
        Log.d(getClass().getSimpleName(), "dialist "+diaList.size() );
        Log.d(getClass().getSimpleName(), "longList "+longList.size() );
        for (Date date: dateList){
            for (Dia dia: diaList){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                if(calendar.get(Calendar.DAY_OF_WEEK) == dia.getDiaId()){
                    longList.add(calendar.getTimeInMillis());
                }
            }
        }

        return longList;
    }

    @Override
    public List<Long> getFechaAsistenciaPorCalendarioPeriodoPorFechaActual(List<Integer> cargaCursoId, int calendarioPeriodoId, Date fechaActual,  int anioAcademicoId) {
        CalendarioPeriodo calendarioPeriodo = calendarioPeriodoDao.get(calendarioPeriodoId);
        if(calendarioPeriodo==null)return new ArrayList<>();
        Log.d(getClass().getSimpleName(),calendarioPeriodo.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        Calendar calendarPeriodo = Calendar.getInstance();
        calendarPeriodo.setTimeInMillis(calendarioPeriodo.getFechaFin());
        if (calendarPeriodo.get(Calendar.YEAR) >= calendar.get(Calendar.YEAR)&&
                calendarPeriodo.get(Calendar.MONTH) >= calendar.get(Calendar.MONTH)&&
                calendarPeriodo.get(Calendar.DAY_OF_MONTH) >= calendar.get(Calendar.DAY_OF_MONTH))
            return getDiasCargaCurso(new Date(calendarioPeriodo.getFechaInicio()), fechaActual, cargaCursoId, anioAcademicoId);
        else return getDiasCargaCurso(new Date(calendarioPeriodo.getFechaInicio()), calendarPeriodo.getTime(), cargaCursoId, anioAcademicoId);
    }



}
