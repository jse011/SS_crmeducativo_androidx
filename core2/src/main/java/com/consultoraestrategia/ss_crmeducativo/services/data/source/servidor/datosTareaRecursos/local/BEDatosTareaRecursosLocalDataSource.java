package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.BEDatosTareaRecursosDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class BEDatosTareaRecursosLocalDataSource extends ServiceLocalDataSource<GEDatosTareasRecursos> implements BEDatosTareaRecursosDataSource {
    private static final String TAG = BEDatosTareaRecursosLocalDataSource.class.getSimpleName();
    private GEDatosRubroEvaluacionProcesoDataSource geDatosRubroEvaluacionProcesoLocalDataSource;

    public BEDatosTareaRecursosLocalDataSource(GEDatosRubroEvaluacionProcesoDataSource geDatosRubroEvaluacionProcesoLocalDataSource) {
        this.geDatosRubroEvaluacionProcesoLocalDataSource = geDatosRubroEvaluacionProcesoLocalDataSource;
    }

    @Override
    public void getDatosExportar(ObjectCallBack<GEDatosTareasRecursos> callBack) {
        getGEDatosTareasRecursosDatosExportar(null, callBack);
    }

    @Override
    public void changeEstado(final GEDatosTareasRecursos item, final int syncFlag, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListSyncFlagUpdate(TareasC.class, item.getTareas(), syncFlag, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(RecursoDidacticoEventoC.class, item.getRecursoDidactico(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(TareasRecursosC.class, item.getTareasRecursos(), syncFlag, databaseWrapper, false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public void saveDatos(final GEDatosTareasRecursos item, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListComprobacionSave(TareasC.class, item.getTareas(), false);
            TransaccionUtils.fastStoreListComprobacionSave(RecursoDidacticoEventoC.class, item.getRecursoDidactico(), false);
            TransaccionUtils.fastStoreListComprobacionSave(TareasRecursosC.class, item.getTareasRecursos(), false);
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
    public void getGEDatosTareasRecursosDatosExportar(List<String> rubroEvalKeyList, ObjectCallBack<GEDatosTareasRecursos> callBack) {

        final GEDatosTareasRecursos beDatosTareasRecursos = new GEDatosTareasRecursos();
        List<TareasC> tareasCList;
        if(rubroEvalKeyList==null){
            tareasCList = ConsultaUtils.getChangeItemsTable(TareasC.class);
        }else {
            List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(TareaRubroEvaluacionProceso.class,
                    TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.in(rubroEvalKeyList));

            List<String> TareaKeyList = new ArrayList<>();
            for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso: tareaRubroEvaluacionProcesoList){
                TareaKeyList.add(tareaRubroEvaluacionProceso.getTareaId());
            }

            tareasCList = ConsultaUtils.getChangeItemsTableChild(TareasC.class,
                    TareasC_Table.key.in(TareaKeyList));
        }
        beDatosTareasRecursos.setTareas(tareasCList);


        if(beDatosTareasRecursos.getTareas().isEmpty()){callBack.onResponse(false, null);return;}

        List<String> tareaKey = new ArrayList<>();
        for (TareasC tareasC: beDatosTareasRecursos.getTareas()){
            tareaKey.add(tareasC.getKey());
        }
        beDatosTareasRecursos.setTareasRecursos(ConsultaUtils.getChangeItemsTableChild(TareasRecursosC.class, TareasRecursosC_Table.tareaId.in(tareaKey)));
        List<String> recursosKey = new ArrayList<>();
        for (TareasRecursosC tareasRecursosC: beDatosTareasRecursos.getTareasRecursos()){
            recursosKey.add(tareasRecursosC.getRecursoDidacticoId());
        }
        beDatosTareasRecursos.setRecursoDidactico(ConsultaUtils.getChangeItemsTableChild(RecursoDidacticoEventoC.class, RecursoDidacticoEventoC_Table.key.in(recursosKey)));

        List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProcesoList = ConsultaUtils.getChangeItemsTableChild(TareaRubroEvaluacionProceso.class,
                TareaRubroEvaluacionProceso_Table.tareaId.in(tareaKey));

        beDatosTareasRecursos.setTareaRubroEvaluacionProceso(tareaRubroEvaluacionProcesoList);

        if(geDatosRubroEvaluacionProcesoLocalDataSource!=null && rubroEvalKeyList == null){

            List<String> rubroEvaluacionProcesoKeyList = new ArrayList<>();
            for (TareaRubroEvaluacionProceso tareaRubroEvaluacionProceso: tareaRubroEvaluacionProcesoList){
                rubroEvaluacionProcesoKeyList.add(tareaRubroEvaluacionProceso.getRubroEvalProcesoId());
            }

            /*geDatosRubroEvaluacionProcesoLocalDataSource.getGEDatosRubroEvaluacionProcesoDatosExportar(rubroEvaluacionProcesoKeyList, new ObjectCallBack<GEDatosRubroEvaluacionProceso>() {
                @Override
                public void onResponse(boolean success, GEDatosRubroEvaluacionProceso item) {
                    if(success){
                        beDatosTareasRecursos.setGeDatosRubroEvaluacionProceso(item);
                    }else {
                        beDatosTareasRecursos.setGeDatosRubroEvaluacionProceso(new GEDatosRubroEvaluacionProceso());
                    }
                }
            });*/
        }

        Log.d(TAG,"Size Tarea:" + beDatosTareasRecursos.getTareas().size() );
        Log.d(TAG,"Size TareaRubro:" + beDatosTareasRecursos.getTareaRubroEvaluacionProceso().size() );

        try {
            if (!beDatosTareasRecursos.getTareas().isEmpty()){
                callBack.onResponse(true, beDatosTareasRecursos);
            }else {
                callBack.onResponse(false, null);
            }

        }catch (Exception e){
            callBack.onResponse(false, null);
        }
    }

    @Override
    public void comprobarListaCambiosTareaRecursos(List<String> tareaRecusoIdList, DosObjectCallBack<List<Long>, List<Long>> dosObjectCallBack) {

    }
}
