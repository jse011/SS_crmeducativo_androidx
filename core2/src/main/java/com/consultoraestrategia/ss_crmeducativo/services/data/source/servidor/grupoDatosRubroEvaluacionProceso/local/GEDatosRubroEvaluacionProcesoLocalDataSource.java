package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.local;

import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.local.BEDatosEnvioGrupoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.local.BEDatosEnvioTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.local.BEDatosRubroEvaluacionProcesoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.BEDatosTareaRecursosDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class GEDatosRubroEvaluacionProcesoLocalDataSource extends ServiceLocalDataSource<GEDatosRubroEvaluacionProceso> implements GEDatosRubroEvaluacionProcesoDataSource {
    private BEDatosRubroEvaluacionProcesoLocalDataSource beDatosRubroEvaluacionProcesoLocalDataSource;
    private BEDatosEnvioTipoNotaLocalDataSource beDatosEnvioTipoNotaLocalDataSource;
    private BEDatosEnvioGrupoLocalDataSource beDatosEnvioGrupoLocalDataSource;
    private BEDatosTareaRecursosDataSource beDatosTareaRecursosDataSource;

    public GEDatosRubroEvaluacionProcesoLocalDataSource(BEDatosRubroEvaluacionProcesoLocalDataSource beDatosRubroEvaluacionProcesoLocalDataSource, BEDatosEnvioTipoNotaLocalDataSource beDatosEnvioTipoNotaLocalDataSource, BEDatosEnvioGrupoLocalDataSource beDatosEnvioGrupoLocalDataSource,BEDatosTareaRecursosDataSource beDatosTareaRecursosDataSource) {
        this.beDatosRubroEvaluacionProcesoLocalDataSource = beDatosRubroEvaluacionProcesoLocalDataSource;
        this.beDatosEnvioTipoNotaLocalDataSource = beDatosEnvioTipoNotaLocalDataSource;
        this.beDatosEnvioGrupoLocalDataSource = beDatosEnvioGrupoLocalDataSource;
        this.beDatosTareaRecursosDataSource = beDatosTareaRecursosDataSource;
    }

    @Override
    public void getDatosExportar(final ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {

        getGEDatosRubroEvaluacionProcesoDatosExportar(null, new ObjectCallBack<GEDatosRubroEvaluacionProceso>() {
            @Override
            public void onResponse(boolean success, final GEDatosRubroEvaluacionProceso item) {
                if(success){
                    final BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = item.getBeDatosRubroEvaluacionProceso();
                    List<String> rubroEvaluacionKey = new ArrayList<>();
                    for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC:
                            beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso())rubroEvaluacionKey.add(rubroEvaluacionProcesoC.getKey());

                   /*
                    beDatosTareaRecursosDataSource.getGEDatosTareasRecursosDatosExportar(rubroEvaluacionKey, new ObjectCallBack<GEDatosTareasRecursos>() {
                        @Override
                        public void onResponse(boolean success, GEDatosTareasRecursos itemTarea) {
                            if(success){
                                List<GEDatosTareasRecursos> geDatosTareasRecursosList = new ArrayList<>();
                                geDatosTareasRecursosList.add(itemTarea);
                                beDatosRubroEvaluacionProceso.setObtenerTareaRubroEvaluacionProceso(geDatosTareasRecursosList);
                            }else {
                                beDatosRubroEvaluacionProceso.setObtenerTareaRubroEvaluacionProceso(new ArrayList<GEDatosTareasRecursos>());
                            }

                            callBack.onResponse(true, item);
                        }
                    });*/
                    callBack.onResponse(true, item);
                }else {
                    callBack.onResponse(false, null);
                }
            }
        });

    }

    @Override
    public void changeEstado(final GEDatosRubroEvaluacionProceso item, final int syncFlag, final SuccessCallBack callBack) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            BEDatosEnvioGrupo beDatosEnvioGrupo = item.getBeDatosEnvioGrupo();
            TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(),syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, beDatosEnvioGrupo.getEquipo(), syncFlag, databaseWrapper, false);

            BEDatosEnvioTipoNota beDatosEnvioTipoNota = item.getBeDatosEnvioTipoNota();
            TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag,databaseWrapper, false);

            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = item.getBeDatosRubroEvaluacionProceso();
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), syncFlag, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), syncFlag,databaseWrapper, true);

            GEDatosTareasRecursos geDatosTareasRecursos = item.getBeDatosTareaRecursos();
            TransaccionUtils.fastStoreListComprobacionSave(RecursoDidacticoEventoC.class,geDatosTareasRecursos.getRecursoDidactico(),false);
            TransaccionUtils.fastStoreListComprobacionSave(TareasRecursosC.class,geDatosTareasRecursos.getTareasRecursos(),false);
            TransaccionUtils.fastStoreListComprobacionSave(TareasC.class,geDatosTareasRecursos.getTareas(),false);
            //TransaccionUtils.fastStoreListComprobacionSave(TareaRubroEvaluacionProceso.class,geDatosTareasRecursos.getTareaRubroEvaluacionProceso(),false);



            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    @Override
    public void saveDatos(final GEDatosRubroEvaluacionProceso item, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso = item.getBeDatosRubroEvaluacionProceso();
            TransaccionUtils.fastStoreListComprobacionSave(RubroEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getRubroEvaluacionProceso(), true);
            TransaccionUtils.fastStoreListComprobacionSave(RubroEvalRNPFormulaC.class, beDatosRubroEvaluacionProceso.getRubroEvalProcesoFormula(), false);
            TransaccionUtils.fastStoreListComprobacionSave(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoEquipo(), false);
            TransaccionUtils.fastStoreListComprobacionSave(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class, beDatosRubroEvaluacionProceso.getObtenerRubroEvaluacionProcesoIntegrante(), false);
            TransaccionUtils.fastStoreListComprobacionSave(EquipoEvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getObtenerEquipoEvaluacionProceso(), false);
            TransaccionUtils.fastStoreListComprobacionSave(RubroEvaluacionProcesoCampotematicoC.class, beDatosRubroEvaluacionProceso.getRubro_evaluacion_proceso_campotematico(), false);
            TransaccionUtils.fastStoreListComprobacionSave(CriterioRubroEvaluacionC.class, beDatosRubroEvaluacionProceso.getObtenerCriterioRubroEvaluacionProceso(), false);
            TransaccionUtils.fastStoreListComprobacionSave(EvaluacionProcesoC.class, beDatosRubroEvaluacionProceso.getEvaluacionProceso(), true);

            GEDatosTareasRecursos geDatosTareasRecursos = item.getBeDatosTareaRecursos();
            TransaccionUtils.fastStoreListComprobacionSave(RecursoDidacticoEventoC.class,geDatosTareasRecursos.getRecursoDidactico(),false);
            TransaccionUtils.fastStoreListComprobacionSave(TareasRecursosC.class,geDatosTareasRecursos.getTareasRecursos(),false);
            TransaccionUtils.fastStoreListComprobacionSave(TareasC.class,geDatosTareasRecursos.getTareas(),false);
            //TransaccionUtils.fastStoreListComprobacionSave(TareaRubroEvaluacionProceso.class,geDatosTareasRecursos.getTareaRubroEvaluacionProceso(),false);


            BEDatosEnvioTipoNota beDatosEnvioTipoNota = item.getBeDatosEnvioTipoNota();
            TransaccionUtils.fastStoreListComprobacionSave(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), false);
            TransaccionUtils.fastStoreListComprobacionSave(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), false);
            TransaccionUtils.fastStoreListComprobacionSave(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), false);
            TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, beDatosEnvioTipoNota.getEscalaEvaluacion(), databaseWrapper, false);
            TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvioTipoNota.getRn_mae_tipo_evaluacion(), databaseWrapper, false);

            //BEDatosEnvioGrupo
            BEDatosEnvioGrupo beDatosEnvioGrupo = item.getBeDatosEnvioGrupo();
            TransaccionUtils.fastStoreListComprobacionSave(EquipoC.class, beDatosEnvioGrupo.getEquipo(), false);
            TransaccionUtils.fastStoreListComprobacionSave(EquipoIntegranteC.class, beDatosEnvioGrupo.getEquipo_integrante(), false);
            TransaccionUtils.fastStoreListComprobacionSave(GrupoEquipoC.class, beDatosEnvioGrupo.getGrupo_equipo(), false);



            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    @Override
    public void getGEDatosRubroEvaluacionProcesoDatosExportar(List<String> rubroEvaluacionKeyList, final ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {
        try {
            final GEDatosRubroEvaluacionProceso geDatosRubroEvaluacionProceso = new GEDatosRubroEvaluacionProceso();

            ObjectCallBack<BEDatosRubroEvaluacionProceso> beDatosRubroEvaluacionProcesoObjectCallBack = new ObjectCallBack<BEDatosRubroEvaluacionProceso>() {
                @Override
                public void onResponse(boolean success, final BEDatosRubroEvaluacionProceso itemRubroEval) {
                    geDatosRubroEvaluacionProceso.setBeDatosRubroEvaluacionProceso(itemRubroEval);

                    beDatosEnvioTipoNotaLocalDataSource.getDatosExportarRelRubroEvalProceso(itemRubroEval.getRubroEvaluacionProceso(), new ObjectCallBack<BEDatosEnvioTipoNota>() {
                        @Override
                        public void onResponse(boolean success, BEDatosEnvioTipoNota item) {
                            geDatosRubroEvaluacionProceso.setBeDatosEnvioTipoNota(item);
                            beDatosEnvioGrupoLocalDataSource.getDatosExportarRelRubroEvalProceso(itemRubroEval.getObtenerRubroEvaluacionProcesoEquipo(), new ObjectCallBack<BEDatosEnvioGrupo>() {
                                @Override
                                public void onResponse(boolean success, BEDatosEnvioGrupo item) {
                                    geDatosRubroEvaluacionProceso.setBeDatosEnvioGrupo(item);
                                    if (!geDatosRubroEvaluacionProceso.getBeDatosRubroEvaluacionProceso().getRubroEvaluacionProceso().isEmpty()) {
                                        callBack.onResponse(true, geDatosRubroEvaluacionProceso);
                                    } else {
                                        callBack.onResponse(false, null);
                                    }
                                }
                            });
                        }
                    });

                }
            };

            if(rubroEvaluacionKeyList==null){
                beDatosRubroEvaluacionProcesoLocalDataSource.getDatosExportar(beDatosRubroEvaluacionProcesoObjectCallBack);
            }else {
                beDatosRubroEvaluacionProcesoLocalDataSource.getRubroEvalDatosExportar(rubroEvaluacionKeyList,beDatosRubroEvaluacionProcesoObjectCallBack);
            }

        }catch (Exception e){
            callBack.onResponse(false, null);
        }
    }

    @Override
    public void comprobarCambiosRubroEvaluacion(String rubroEvalProcesoId, DosObjectCallBack<Long, Long> callBack) {
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalProcesoId))
                .querySingle();

        if(rubroEvalProcesoId != null && rubroEvaluacionProcesoC!=null){callBack.onResponse(true,0L, rubroEvaluacionProcesoC.getFechaAccion());
        }else {
            callBack.onResponse(false,0L, 0L);
        }
    }

    @Override
    public void getDatosImportarPorCalendarioPeriodo(BEVariables beVariables, ObjectCallBack<GEDatosRubroEvaluacionProceso> objectCallBack) {

    }
}
