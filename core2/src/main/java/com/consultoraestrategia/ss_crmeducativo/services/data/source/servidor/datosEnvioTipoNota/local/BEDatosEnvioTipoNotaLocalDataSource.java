package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.local;

import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioTipoNotaLocalDataSource extends ServiceLocalDataSource<BEDatosEnvioTipoNota> implements BEDatosEnvioTipoNotaDataSource {
    @Override
    public void getDatosExportar(ObjectCallBack<BEDatosEnvioTipoNota> callBack) {
        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTable(TipoNotaC.class));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTable(ValorTipoNotaC.class));
        beDatosEnvioTipoNota.setRel_Programa_Educativo_TipoNota(ConsultaUtils.getChangeItemsTable(RelProgramaEducativoTipoNota.class));
        callBack.onResponse(true,beDatosEnvioTipoNota);
    }

    @Override
    public void getDatosExportarRelRubroEvalProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList, ObjectCallBack<BEDatosEnvioTipoNota> callBack) {

        List<String> tipoNotakeys = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC: rubroEvaluacionProcesoList) {
            tipoNotakeys.add(rubroEvaluacionProcesoC.getTipoNotaId());
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTableChild(TipoNotaC.class, TipoNotaC_Table.key.in(tipoNotakeys)));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTableChild(ValorTipoNotaC.class, ValorTipoNotaC_Table.tipoNotaId.in(tipoNotakeys)));
        callBack.onResponse(true,beDatosEnvioTipoNota);
    }

    @Override
    public void getDatosExportarRelAsistenciaAlumnos(List<AsistenciaSesionAlumnoC> asistenciaAlumnos, ObjectCallBack<BEDatosEnvioTipoNota> objectCallBack) {
        List<String> tipoNotakeys = new ArrayList<>();
        for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: asistenciaAlumnos) {
            TipoNotaC tipoNotaC = SQLite.select()
                    .from(TipoNotaC.class)
                    .innerJoin(ValorTipoNotaC.class)
                    .on(TipoNotaC_Table.key.withTable()
                            .eq(ValorTipoNotaC_Table.tipoNotaId.withTable()))
                    .where(ValorTipoNotaC_Table.key.withTable().is(asistenciaSesionAlumnoC.getValorTipoNotaId()))
                    .querySingle();
            if(tipoNotaC == null)continue;

            tipoNotakeys.add(tipoNotaC.getKey());
        }

        BEDatosEnvioTipoNota beDatosEnvioTipoNota = new BEDatosEnvioTipoNota();
        beDatosEnvioTipoNota.setTipoNota(ConsultaUtils.getChangeItemsTableChild(TipoNotaC.class, TipoNotaC_Table.key.in(tipoNotakeys)));
        beDatosEnvioTipoNota.setValorTipoNota(ConsultaUtils.getChangeItemsTableChild(ValorTipoNotaC.class, ValorTipoNotaC_Table.tipoNotaId.in(tipoNotakeys)));
        objectCallBack.onResponse(true,beDatosEnvioTipoNota);
    }

    @Override
    public void changeEstado(BEDatosEnvioTipoNota item, int syncFlag, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, item.getTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, item.getValorTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, item.getRel_Programa_Educativo_TipoNota(), syncFlag,databaseWrapper, false);

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
    public void saveDatos(BEDatosEnvioTipoNota item, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            TransaccionUtils.fastStoreListComprobacionSave(TipoNotaC.class, item.getTipoNota(), false);
            TransaccionUtils.fastStoreListComprobacionSave(ValorTipoNotaC.class, item.getValorTipoNota(), false);
            TransaccionUtils.fastStoreListComprobacionSave(RelProgramaEducativoTipoNota.class, item.getRel_Programa_Educativo_TipoNota(), false);
            TransaccionUtils.fastStoreListSetData(EscalaEvaluacion.class, item.getEscalaEvaluacion(), databaseWrapper, false);
            TransaccionUtils.fastStoreListSetData(T_RN_MAE_TIPO_EVALUACION.class, item.getRn_mae_tipo_evaluacion(), databaseWrapper, false);

            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }
}
