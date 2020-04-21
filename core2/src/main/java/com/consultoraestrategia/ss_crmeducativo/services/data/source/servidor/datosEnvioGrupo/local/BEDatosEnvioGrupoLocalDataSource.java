package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.local;

import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 18/05/2018.
 */

public class BEDatosEnvioGrupoLocalDataSource extends ServiceLocalDataSource<BEDatosEnvioGrupo> implements BEDatosEnvioGrupoDataSource {
    @Override
    public void getDatosExportar(ObjectCallBack<BEDatosEnvioGrupo> callBack) {
        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
       beDatosEnvioGrupo.setEquipo(ConsultaUtils.getChangeItemsTable(EquipoC.class));
       beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTable(EquipoIntegranteC.class));
       beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTable(EquipoIntegranteC.class));
       beDatosEnvioGrupo.setGrupo_equipo(ConsultaUtils.getChangeItemsTable(GrupoEquipoC.class));
       callBack.onResponse(true, beDatosEnvioGrupo);
    }

    @Override
    public void getDatosExportarRelRubroEvalProceso(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> evaluacion_proceso_equipocList, ObjectCallBack<BEDatosEnvioGrupo> callBack) {

        List<String> equipokeys = new ArrayList<>();
        for (T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC maeRubroEvaluacionProcesoEquipoc: evaluacion_proceso_equipocList) {
            equipokeys.add(maeRubroEvaluacionProcesoEquipoc.getEquipoId());
        }

        BEDatosEnvioGrupo beDatosEnvioGrupo = new BEDatosEnvioGrupo();
        beDatosEnvioGrupo.setEquipo(ConsultaUtils.getChangeItemsTableChild(EquipoC.class, EquipoC_Table.key.in(equipokeys)));
        beDatosEnvioGrupo.setEquipo_integrante(ConsultaUtils.getChangeItemsTableChild(EquipoIntegranteC.class, EquipoIntegranteC_Table.equipoId.in(equipokeys)));


        List<String> grupokeys = new ArrayList<>();
        for (EquipoC equipo: beDatosEnvioGrupo.getEquipo()) {
            grupokeys.add(equipo.getGrupoEquipoId());
        }

        beDatosEnvioGrupo.setGrupo_equipo(ConsultaUtils.getChangeItemsTableChild(GrupoEquipoC.class, GrupoEquipoC_Table.key.in(grupokeys)));
        callBack.onResponse(true, beDatosEnvioGrupo);
    }

    @Override
    public void comprobarCambiosGrupos(String grupoEquipoId, DosObjectCallBack<Long, Long> callBack) {
        GrupoEquipoC grupoEquipoC = SQLite.select()
                .from(GrupoEquipoC.class)
                .where(GrupoEquipoC_Table.key.eq(grupoEquipoId))
                .querySingle();

        if(grupoEquipoC != null){
            callBack.onResponse(true,0L, grupoEquipoC.getFechaAccion());
        }else {
            callBack.onResponse(false,0L, 0L);
        }
    }

    @Override
    public void getDatosImportarPorUsuario(BEVariables beVariables, ObjectCallBack<BEDatosEnvioGrupo> objectCallBack) {

    }

    @Override
    public void changeEstado(BEDatosEnvioGrupo item, int syncFlag, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListSyncFlagUpdate(GrupoEquipoC.class, item.getGrupo_equipo(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoIntegranteC.class, item.getEquipo_integrante(),syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(EquipoC.class, item.getEquipo(), syncFlag, databaseWrapper, false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    @Override
    public void saveDatos(BEDatosEnvioGrupo item, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListComprobacionSave(EquipoC.class, item.getEquipo(), false);
            TransaccionUtils.fastStoreListComprobacionSave(EquipoIntegranteC.class, item.getEquipo_integrante(), false);
            TransaccionUtils.fastStoreListComprobacionSave(GrupoEquipoC.class, item.getGrupo_equipo(), false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }


}
