package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source;

import com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo.CalendarioPeriodoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.modelViews.UnidadAprendizajeCargaCursoModel;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

public class UnidadesLocalDataSource implements UnidadesDataSource {


    RubroProcesoDao rubroProcesoDao;
    ParametrosDisenioDao parametrosDisenioDao;
    private CalendarioPeriodoDao calendarioPeriodoDao;

    public UnidadesLocalDataSource(RubroProcesoDao rubroProcesoDao, ParametrosDisenioDao parametrosDisenioDao, CalendarioPeriodoDao calendarioPeriodoDao) {
        this.rubroProcesoDao = rubroProcesoDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
        this.calendarioPeriodoDao = calendarioPeriodoDao;
    }

    private static String TAG = UnidadesLocalDataSource.class.getSimpleName();
    @Override
    public void getUnidadesList(int idCargaCurso, int idCalendarioPeriodo, int idAnioAcademico, CallbackUnidades callbackUnidades) {
        List<UnidadAprendizajeUi> listObject= new ArrayList<>();

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            boolean isvigente = calendarioPeriodoDao.isVigenteCalendarioCursoPeriodo(idCalendarioPeriodo, idCargaCurso, false,databaseWrapper);
            CalendarioPeriodo calendarioPeriodo = calendarioPeriodoDao.get(idCalendarioPeriodo);
            boolean isEdiatar = false;

            if(calendarioPeriodo!=null)
                switch (calendarioPeriodo.getEstadoId()){
                case CalendarioPeriodo.CALENDARIO_PERIODO_CREADO:
                    isEdiatar = true;
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_VIGENTE:
                    isEdiatar = isvigente;
                    break;
                case CalendarioPeriodo.CALENDARIO_PERIODO_CERRADO:
                    isEdiatar = isvigente;
                    break;
                }

            List<UnidadAprendizaje> vlst_unidadAprendizaje = UnidadAprendizajeCargaCursoModel.SQLView()
                    .select(Utils.f_allcolumnTable(Utils.f_allcolumnTable(UnidadAprendizaje_Table.ALL_COLUMN_PROPERTIES)))
                    //.select(UnidadAprendizaje_Table.unidadAprendizajeId.withTable(), UnidadAprendizaje_Table.titulo.withTable())
                    .getQuery(idCargaCurso, idCalendarioPeriodo, idAnioAcademico)
                    .queryList();
            //lista.add(vlst_unidadAprendizaje);

            for(UnidadAprendizaje unidadAprendizaje:vlst_unidadAprendizaje ){
                //sesionAprendizajeUiList.clear();

                UnidadAprendizajeUi unidad= new UnidadAprendizajeUi();
                unidad.setUnidadAprendizajeId(unidadAprendizaje.getUnidadAprendizajeId());
                unidad.setNroUnidad(unidadAprendizaje.getNroUnidad());
                unidad.setTitulo(unidadAprendizaje.getTitulo());
                unidad.setSituacionSignificativa(unidadAprendizaje.getSituacionSignificativa());
                unidad.setNroHoras(unidadAprendizaje.getNroHoras());
                unidad.setNroSesiones(unidadAprendizaje.getNroSesiones());
                unidad.setNroSemanas(unidadAprendizaje.getNroSemanas());
                unidad.setEstadoId(unidadAprendizaje.getEstadoId());
                unidad.setSilaboEventoId(unidadAprendizaje.getSilaboEventoId());
                unidad.setSituacionSignificativaComplementaria(unidadAprendizaje.getSituacionSignificativaComplementaria());
                unidad.setDesafio(unidadAprendizaje.getDesafio());
                unidad.setReto(unidadAprendizaje.getReto());
                unidad.setToogle(unidadAprendizaje.isToogle());
                ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(idCargaCurso);
                if(parametrosDisenio!=null)unidad.setColor(parametrosDisenio.getColor1());

                List<SesionAprendizaje> sesionAprendizajes = SQLite.select()
                        .from(SesionAprendizaje.class).where(SesionAprendizaje_Table.unidadAprendizajeId.eq(unidadAprendizaje.getUnidadAprendizajeId()))
                        .and(SesionAprendizaje_Table.rolId.eq(4))
                        .and(SesionAprendizaje_Table.estadoId.in(SesionAprendizaje.AUTORIZADO_ESTADO))//297
                        .and(SesionAprendizaje_Table.estadoEjecucionId.in(
                                SesionAprendizaje.PROGRAMADO_EJECUCION,
                                SesionAprendizaje.HECHO_EJECUCION,
                                SesionAprendizaje.PENDIENTE_EJECUCION))
                        .orderBy(SesionAprendizaje_Table.fechaEjecucion.asc())
                        .queryList(databaseWrapper);

                List<SesionAprendizajeUi>sesionAprendizajeUiList= new ArrayList<>();

                for(SesionAprendizaje sesion:sesionAprendizajes ){

                    SesionAprendizajeUi sesionAprendizajeUi = new SesionAprendizajeUi();
                    sesionAprendizajeUi.setSesionAprendizajeId(sesion.getSesionAprendizajeId());
                    sesionAprendizajeUi.setUnidadAprendizajeId(sesion.getUnidadAprendizajeId());
                    sesionAprendizajeUi.setTitulo(sesion.getTitulo());
                    sesionAprendizajeUi.setHoras(sesion.getHoras());
                    sesionAprendizajeUi.setContenido(sesion.getContenido());
                    sesionAprendizajeUi.setUsuarioCreador(sesion.getUsuarioCreador());
                    sesionAprendizajeUi.setUsuarioAccion(sesion.getUsuarioAccion());
                    sesionAprendizajeUi.setEstadoId(sesion.getEstadoId());
                    sesionAprendizajeUi.setFechaEjecucion(sesion.getFechaEjecucion());
                    sesionAprendizajeUi.setFechaReprogramacion(sesion.getFechaReprogramacion());
                    sesionAprendizajeUi.setFechaPublicacion(sesion.getFechaPublicacion());
                    sesionAprendizajeUi.setNroSesion(sesion.getNroSesion());
                    sesionAprendizajeUi.setRolId(sesion.getRolId());
                    sesionAprendizajeUi.setFechaRealizada(sesion.getFechaRealizada());
                    sesionAprendizajeUi.setEstadoEjecucionId(sesion.getEstadoEjecucionId());
                    sesionAprendizajeUi.setProposito(sesion.getProposito());
                    sesionAprendizajeUi.setVigente(isvigente);
                    sesionAprendizajeUi.setEditar(isEdiatar);
                    long recursos= SQLite.selectCountOf()
                            .from(RubroEvaluacionProcesoC.class)
                            .where(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.withTable().is(sesion.getSesionAprendizajeId()))
                            .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(280))
                            .and(RubroEvaluacionProcesoC_Table.tiporubroid.in(RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL, RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL))
                            .count(databaseWrapper);

                    sesionAprendizajeUi.setCantidad_recursos(recursos);
                    String evaluados= rubroProcesoDao.cantidadRubroEvaluadosPorSession(sesion.getSesionAprendizajeId(), databaseWrapper);
                    sesionAprendizajeUi.setCantidadEvaluadosSesion(evaluados);
                    sesionAprendizajeUiList.add(sesionAprendizajeUi);
                }

                unidad.setObjectListSesiones(sesionAprendizajeUiList);
                listObject.add(unidad);
            }
            databaseWrapper.setTransactionSuccessful();
            callbackUnidades.onListeUnidades(listObject, 1);
        } catch (Exception e){
            e.printStackTrace();
            callbackUnidades.onListeUnidades(new ArrayList<UnidadAprendizajeUi>(), 1);
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public void saveToogleUnidad(UnidadAprendizajeUi unidadAprendizajeUi,  Callback callback) {

        UnidadAprendizaje unidadAprendizaje = SQLite.select()
                .from(UnidadAprendizaje.class)
                .where(UnidadAprendizaje_Table.unidadAprendizajeId.eq(unidadAprendizajeUi.getUnidadAprendizajeId()))
                .querySingle();
        if(unidadAprendizaje!=null){
            unidadAprendizaje.setToogle(unidadAprendizajeUi.isToogle());
            unidadAprendizaje.save();
            callback.onLoad(true,1);
        }else callback.onLoad(false, 0);


    }

    @Override
    public void saveSesionAprendizaje(SesionAprendizajeUi sesionAprendizajeUi) {

        SesionAprendizaje sesionAprendizaje  = SQLite.select()
                .from(SesionAprendizaje.class)
                .where(SesionAprendizaje_Table.sesionAprendizajeId.eq(sesionAprendizajeUi.getSesionAprendizajeId()))
                .querySingle();
        if(sesionAprendizaje!=null){
            sesionAprendizaje.setEstadoEjecucionId(sesionAprendizajeUi.getEstadoEjecucionId());
            sesionAprendizaje.setSyncFlag(SesionAprendizajeUi.FLAG_UPDATED);
            sesionAprendizaje.save();
        }

    }


}
