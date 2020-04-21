package com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class TareaRubroEvaluacionProcesoDaoImpl extends BaseDaoImpl<TareaRubroEvaluacionProceso, TareaRubroEvaluacionProceso_Table> implements TareaRubroEvaluacionProcesoDao{


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
    protected Class<TareaRubroEvaluacionProceso> getEntityClass() {
        return TareaRubroEvaluacionProceso.class;
    }

    @Override
    protected Class<TareaRubroEvaluacionProceso_Table> getTableclass() {
        return TareaRubroEvaluacionProceso_Table.class;
    }

    @Override
    public boolean elimarTareaRubroEvaluacionProceso(String rubroEvalProcesoId) {
        boolean result;

        try {
            DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
            DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
            SQLite.delete().from(TareaRubroEvaluacionProceso.class)
                    .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.eq(rubroEvalProcesoId))
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

    @Override
    public TareaRubroEvaluacionProceso getTareaRubroPorRubroId(String rubroEvaluacionProcesoId) {
        return SQLite.select()
                .from(TareaRubroEvaluacionProceso.class)
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoId))
                .querySingle();
    }

    @Override
    public TareaRubroEvaluacionProceso getTareaRubroPorRubroId(String rubroEvaluacionProcesoId, DatabaseWrapper databaseWrapper) {
        return  SQLite.select()
                .from(TareaRubroEvaluacionProceso.class)
                .where(TareaRubroEvaluacionProceso_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoId))
                .querySingle(databaseWrapper);
    }
}
