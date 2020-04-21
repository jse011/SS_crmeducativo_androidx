package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.local;

import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNRFormula;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEvaluacionResultadoLocalDataSource extends ServiceLocalDataSource<BEDatosEvaluacionResultado> {
    @Override
    public void saveDatos(final BEDatosEvaluacionResultado item, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListComprobacionSave(RubroEvaluacionResultado.class, item.getRubroEvaluacionResultado(), true);
            TransaccionUtils.fastStoreListComprobacionSave(RubroEvalRNRFormula.class, item.getRubroEvalResultadoFormula(), false);
            TransaccionUtils.fastStoreListComprobacionSave(EvaluacionResultadoC.class, item.getEvaluacionResultado(), false);
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
    public void changeEstado(final BEDatosEvaluacionResultado item, final int syncFlag, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvaluacionResultado.class, item.getRubroEvaluacionResultado(), syncFlag, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RubroEvalRNRFormula.class, item.getRubroEvalResultadoFormula(), syncFlag, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(EvaluacionResultadoC.class, item.getEvaluacionResultado(), syncFlag,databaseWrapper, true);

            SessionUser user = SessionUser.getCurrentUser();
            if (user != null) {
                user.setDataImported(true);
                user.save();
            }else {
                throw new Error("NO se puede encontrar la SessionUser");
            }
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }


}
