package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada.DimensionObservadaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionData2;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionData2_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.CalendarioPeriodoModel;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 20/02/2018.
 */

public class TabsCursoLocalDataSource implements TabsCursoDataSource {
    public static int REGISTRO_ERROR = 0;
    public static int REGISTRO_SUCCESS = 1;
    private static final String TAG = TabsCursoLocalDataSource.class.getSimpleName();
    private CalendarioPeriodoDao calendarioPeriodoDao;
    private DimensionObservadaDao dimensionObservadaDao;

    public TabsCursoLocalDataSource(CalendarioPeriodoDao calendarioPeriodoDao, DimensionObservadaDao dimensionObservadaDao) {
            this.calendarioPeriodoDao = calendarioPeriodoDao;
            this.dimensionObservadaDao = dimensionObservadaDao;
    }

    @Override
    public List<PeriodoUi> getPeriodoList(int cargaCursoId, int cursoId, int parametrodisenioid,int anioAcademicoId) {
        Log.d(TAG, "getPeriodoList");
        Log.d(TAG, "cargaCursoId: " + cargaCursoId);
        Log.d(TAG, "cursoId: " + cursoId);
        List<PeriodoUi> list = new ArrayList<>();
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            List<CalendarioPeriodo> calendarioPeriodoList = CalendarioPeriodoModel.SQLView()
                    .select(CalendarioPeriodo_Table.calendarioPeriodoId.withTable(),
                            CalendarioPeriodo_Table.tipoId.withTable(),
                            CalendarioPeriodo_Table.estadoId.withTable(),
                            CalendarioPeriodo_Table.fechaFin.withTable())
                    .getQuery(cargaCursoId, anioAcademicoId)
                    .queryList(databaseWrapper);


            Log.d(TAG, "SIZEPERIODO1 : " + calendarioPeriodoList.size());

            boolean seleccionado = false;

            int count=0;
            for (CalendarioPeriodo periodo :
                    calendarioPeriodoList) {
                Log.d(TAG, "COUNT : " + calendarioPeriodoList.size() + " /  periodoid : " + periodo.getCalendarioPeriodoId() + " / " + periodo.getCalendarioPeriodoId()+ " / " +periodo.getEstadoId());

                Tipos tipo = SQLite.select()
                        .from(Tipos.class)
                        .where(Tipos_Table.tipoId.eq(periodo.getTipoId()))
                        .querySingle(databaseWrapper);
                if(tipo==null)continue;
                //Log.d(TAG, "PeriodoUi : " + tipo.getTipoId() + "")

                PeriodoUi periodoUi = new PeriodoUi(periodo.getCalendarioPeriodoId(), tipo.getNombre(), "", false);
                list.add(periodoUi);
                boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(periodo.getCalendarioPeriodoId(), cargaCursoId, false,databaseWrapper);
                periodoUi.setVigente(isvigente);
                periodoUi.setFechaFin(periodo.getFechaFin());

                switch (periodo.getEstadoId()){
                    case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                        periodoUi.setEstado(PeriodoUi.Estado.Creado);
                        periodoUi.setEditar(true);
                        break;
                    case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                        periodoUi.setEstado(PeriodoUi.Estado.Vigente);
                        periodoUi.setEditar(isvigente);
                        break;
                    case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                        periodoUi.setEstado(PeriodoUi.Estado.Cerrado);
                        periodoUi.setEditar(isvigente);
                        break;
                }
            }

//            if (!seleccionado && calendarioPeriodoList.size() > 0) {
//                PeriodoUi firstPositionList = list.get(0);
//                firstPositionList.setStatus(true);
//            }
//
            /*if (count>1){
                for (PeriodoUi periodoUi:list){
                    periodoUi.setStatus(false);
                }
                PeriodoUi firstPositionList = list.get(0);
                firstPositionList.setStatus(true);
            }
*/
            databaseWrapper.setTransactionSuccessful();


        } catch (Exception e){
            e.printStackTrace();
        }finally {
            databaseWrapper.endTransaction();
        }

        return list;
}

    @Override
    public boolean isTutor(int cargaAcademicaId) {
        boolean success = false;
        CargaAcademica cargaAcademica = SQLite.select()
                .from(CargaAcademica.class)
                .where(CargaAcademica_Table.cargaAcademicaId.eq(cargaAcademicaId))
                .querySingle();

        Empleado empleado = null;
        SessionUser sessionUser =SessionUser.getCurrentUser();
        if(sessionUser!=null)empleado = SQLite.select()
                .from(Empleado.class)
                .where(Empleado_Table.personaId.eq(sessionUser.getPersonaId()))
                .querySingle();
        if(cargaAcademica!=null&&empleado!=null&&empleado.getEmpleadoId()==cargaAcademica.getIdEmpleadoTutor()){
            success = true;
        }

        return success;

    }

    @Override
    public boolean isAprendizajeColegio(int entidadId, int georeferenciaId) {
        InstrumentoEvaluacion instrumentoEvaluacion = null;
        instrumentoEvaluacion=dimensionObservadaDao.getDimensionColegio(entidadId, georeferenciaId);
        if (instrumentoEvaluacion!=null)return true;
        else return false;
    }

    @Override
    public ParametroDisenioUi getParametroDisenio(int parametroDisenioId) {
        ParametroDisenioUi parametroDisenioUi = new ParametroDisenioUi();
        ParametrosDisenio parametrosDisenio = SQLite.select()
                .from(ParametrosDisenio.class)
                .where(ParametrosDisenio_Table.parametroDisenioId.eq(parametroDisenioId))
                .querySingle();
        if(parametrosDisenio!=null){
            parametroDisenioUi.setParametroDisenioId(parametrosDisenio.getParametroDisenioId());
            parametroDisenioUi.setNombre(parametrosDisenio.getNombre());
            parametroDisenioUi.setConcepto(parametrosDisenio.getConcepto());
            parametroDisenioUi.setPath(parametrosDisenio.getPath());
            parametroDisenioUi.setColor1(parametrosDisenio.getColor1());
            parametroDisenioUi.setColor2(parametrosDisenio.getColor2());
            parametroDisenioUi.setColor3(parametrosDisenio.getColor3());
        }
        return parametroDisenioUi;
    }

    @Override
    public boolean isfirstTimeHere(int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaEduId, int usuarioId, int empleadoId, int cargaAcademicaId, int georeferenciaId, int entidadId, int cursoId) {


        long countSessionData = 0;


        ActualizarUi unidades = new ActualizarUi();
        unidades.setNombre("Unidadades");
        unidades.setTipo(ActualizarTipoUi.Unidades);
        unidades.setCargacursoId(cargaCursoId);
        unidades.setCalendarioPeriodoId(calendarioPeriodoId);
        unidades.setSilaboEventoId(silaboEventoId);
        unidades.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(unidades.getId()))
                .count();

        Log.d(TAG, "unidades: " + countSessionData);

        ActualizarUi tipoNota = new ActualizarUi();
        tipoNota.setNombre("Nivel de logro");
        tipoNota.setTipo(ActualizarTipoUi.TipoNota);
        tipoNota.setProgramaEducativoId(programaEduId);
        tipoNota.setUsuarioId(usuarioId);
        tipoNota.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(tipoNota.getId()))
                .count();

        Log.d(TAG, "tipoNota: " + countSessionData);

        ActualizarUi estudiantes = new ActualizarUi();
        estudiantes.setNombre("Estudiantes y su familia");
        estudiantes.setTipo(ActualizarTipoUi.Estudiantes);
        estudiantes.setCargacursoId(cargaCursoId);
        estudiantes.setDocenteId(empleadoId);
        estudiantes.setCargaAcademicaId(cargaAcademicaId);
        estudiantes.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(estudiantes.getId()))
                .count();

        Log.d(TAG, "estudiantes: " + countSessionData);

        ActualizarUi rubros = new ActualizarUi();
        rubros.setNombre("Rubro evaluación");
        rubros.setTipo(ActualizarTipoUi.Rubros);
        rubros.setCargacursoId(cargaCursoId);
        rubros.setCalendarioPeriodoId(calendarioPeriodoId);
        rubros.setSilaboEventoId(silaboEventoId);
        rubros.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(rubros.getId()))
                .count();

        Log.d(TAG, "rubros: " + countSessionData);

        /*ActualizarUi resultado = new ActualizarUi();
        resultado.setNombre("Resultado evaluación");
        resultado.setTipo(ActualizarTipoUi.Resultado);
        resultado.setCargacursoId(cargaCursoId);
        resultado.setCalendarioPeriodoId(calendarioPeriodoId);
        resultado.setSilaboEventoId(silaboEventoId);
        resultado.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(resultado.getId()))
                .count();

        Log.d(TAG, "resultado: " + countSessionData);*/

        ActualizarUi grupos = new ActualizarUi();
        grupos.setNombre("Grupos");
        grupos.setTipo(ActualizarTipoUi.Grupos);
        grupos.setCargacursoId(cargaCursoId);
        grupos.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(grupos.getId()))
                .count();

        Log.d(TAG, "grupos: " + countSessionData);

        ActualizarUi tareas = new ActualizarUi();
        tareas.setNombre("Tarea");
        tareas.setTipo(ActualizarTipoUi.Tareas);
        tareas.setCargacursoId(cargaCursoId);
        tareas.setSilaboEventoId(silaboEventoId);
        tareas.setCalendarioPeriodoId(calendarioPeriodoId);
        tareas.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(tareas.getId()))
                .count();

        Log.d(TAG, "tareas: " + countSessionData);

        ActualizarUi casos = new ActualizarUi();
        casos.setNombre("Casos");
        casos.setTipo(ActualizarTipoUi.Casos);
        casos.setCargacursoId(cargaCursoId);
        casos.setCalendarioPeriodoId(calendarioPeriodoId);
        casos.setGeoreferenciaId(georeferenciaId);
        casos.setEntidadId(entidadId);
        casos.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(casos.getId()))
                .count();

        Log.d(TAG, "casos: " + countSessionData);

/*        ActualizarUi asistencia = new ActualizarUi();
        asistencia.setNombre("Asistencia");
        asistencia.setTipo(ActualizarTipoUi.Asistencias);
        asistencia.setCargacursoId(cargaCursoId);
        asistencia.setCalendarioPeriodoId(calendarioPeriodoId);
        asistencia.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(asistencia.getId()))
                .count();

        Log.d(TAG, "asistencia: " + countSessionData);

        ActualizarUi docente = new ActualizarUi();
        docente.setNombre("Docente");
        docente.setTipo(ActualizarTipoUi.Docente);
        docente.setGeoreferenciaId(georeferenciaId);
        docente.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(docente.getId()))
                .count();

        Log.d(TAG, "docente: " + countSessionData);*/

        ActualizarUi dimencionDesarrollo = new ActualizarUi();
        dimencionDesarrollo.setNombre("Dimensión desarrollo");
        dimencionDesarrollo.setTipo(ActualizarTipoUi.Dimencion_Desarrollo);
        dimencionDesarrollo.setCursoId(cursoId);
        dimencionDesarrollo.setDocenteId(empleadoId);
        dimencionDesarrollo.setGeoreferenciaId(georeferenciaId);
        dimencionDesarrollo.setEntidadId(entidadId);
        dimencionDesarrollo.setCargacursoId(cargaCursoId);
        dimencionDesarrollo.generarId();

        countSessionData +=  SQLite.selectCountOf()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(dimencionDesarrollo.getId()))
                .count();

        Log.d(TAG, "dimencionDesarrollo: " + countSessionData);


        //return countSessionData < 9; por que ya no se trae los resultados
        return countSessionData < 8;
    }

    @Override
    public boolean getExistChangeCentroProcesamiento(int cargaCursoId, int calendarioPeriodoId) {

        return !SQLite.select()
                .from(SessionData2.class)
                .where(SessionData2_Table.id.eq(SessionData2.Key_CentroProcesamiento))
                .and(SessionData2_Table.calendarioPeriodoId.eq(calendarioPeriodoId))
                .and(SessionData2_Table.cargaCursoId.eq(cargaCursoId))
                .queryList().isEmpty();
    }

}
