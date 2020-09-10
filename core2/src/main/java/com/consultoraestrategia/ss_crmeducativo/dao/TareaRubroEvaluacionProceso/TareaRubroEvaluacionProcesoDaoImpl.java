package com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class TareaRubroEvaluacionProcesoDaoImpl implements TareaRubroEvaluacionProcesoDao{


    private static TareaRubroEvaluacionProcesoDao mInstance;

    public TareaRubroEvaluacionProcesoDaoImpl() {
    }

    public static TareaRubroEvaluacionProcesoDao getInstance() {
        if (mInstance == null) {
            mInstance = new TareaRubroEvaluacionProcesoDaoImpl();
        }
        return mInstance;
    }

    @Override
    public boolean elimarTareaRubroEvaluacionProceso(String rubroEvalProcesoId) {
        boolean result;

        try {
            DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
            DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
            SQLite.update(RubroEvaluacionProcesoC.class)
                    .set(RubroEvaluacionProcesoC_Table.tareaId.eq(""))
                    .where(RubroEvaluacionProcesoC_Table.key.eq(rubroEvalProcesoId))
                    .execute();
            databaseWrapper.setTransactionSuccessful();
            databaseWrapper.beginTransaction();
            result = true;
        } catch (Exception error){
            error.printStackTrace();
            result = false;
        }
        return result;
    }
    
}
