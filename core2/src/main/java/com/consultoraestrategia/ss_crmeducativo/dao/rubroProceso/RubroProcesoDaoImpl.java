package com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCIE on 30/04/2018.
 */

public class RubroProcesoDaoImpl extends BaseDaoImpl<RubroEvaluacionProcesoC, RubroEvaluacionProcesoC_Table> implements RubroProcesoDao {

    public static final String PROCESO_DAO_TAG = RubroProcesoDaoImpl.class.getSimpleName();
    private static RubroProcesoDao mInstance;

    public RubroEvalRNPFormulaDao rnpFormulaDao;

    public RubroProcesoDaoImpl(RubroEvalRNPFormulaDao rnpFormulaDao) {
        this.rnpFormulaDao = rnpFormulaDao;
    }

    public static RubroProcesoDao getInstance(RubroEvalRNPFormulaDao rnpFormulaDao) {
        if (mInstance == null) {
            mInstance = new RubroProcesoDaoImpl(rnpFormulaDao);
        }
        return mInstance;
    }

    @Override
    protected Class<RubroEvaluacionProcesoC> getEntityClass() {
        return RubroEvaluacionProcesoC.class;
    }

    @Override
    protected Class<RubroEvaluacionProcesoC_Table> getTableclass() {
        return RubroEvaluacionProcesoC_Table.class;
    }


    @Override
    public String obtenerUltimoRegistroKey() {
        RubroEvaluacionProcesoC rubroEvaluacionProceso12 = SQLite.select(
                Method.max(RubroEvaluacionProcesoC_Table.fechaAccion.withTable()), Property.ALL_PROPERTY).from(RubroEvaluacionProcesoC.class)
                .querySingle();
        if (rubroEvaluacionProceso12 == null) return "";
        return rubroEvaluacionProceso12.getKey();
    }

    @Override
    public RubroEvaluacionProcesoC getMyRegistro(String rubroProcesoKey) {
        RubroEvaluacionProcesoC rubroEvaluacionProceso = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.is(rubroProcesoKey))
              //  .and(RubroEvaluacionProcesoC_Table.estadoId.notIn(280))
                .querySingle();
        return rubroEvaluacionProceso;
    }

    @Override
    public void actualizarRubroEstado(String key) {
        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.eq(key))
                .querySingle();
        if(rubroEvaluacionProcesoC!=null){
            rubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionProcesoC.ESTADO_ANCLADA);
            rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            rubroEvaluacionProcesoC.save();
        }
    }

    @Override
    public void actualizarRubroEliminado(final String key, final Callback<Boolean> callback) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(key))
                        .querySingle();
                if(rubroEvaluacionProcesoC!=null){
                    rubroEvaluacionProcesoC.setTareaId("");
                    rubroEvaluacionProcesoC.setEstadoId(RubroEvaluacionProcesoC.ESTADO_ELIMINADO);
                    rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    callback.onSuccess(rubroEvaluacionProcesoC.save());
                }else {
                    callback.onSuccess(true);
                }

            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callback.onSuccess(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callback.onError(error);
            }
        }).build();
        transaction.execute();

    }


    @Override
    public List<RubroEvaluacionProcesoC> get(List<String> rubroEvaluacionKey) {
        return SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.key.in(rubroEvaluacionKey))
                .queryList();
    }

    @Override
    public int cantidadIndicadoresFormula(String keyFormula) {
        List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoCList = rnpFormulaDao.getListaRubroFormula(keyFormula);
        Log.d(PROCESO_DAO_TAG, "rubroEvaluacionProcesoCList : " + rubroEvaluacionProcesoCList.size());
        List<Integer> integerList = new ArrayList<>();
        for (RubroEvaluacionProcesoC rubroEvaluacionProcesoC : rubroEvaluacionProcesoCList){
            int posicion = integerList.indexOf(rubroEvaluacionProcesoC.getDesempenioIcdId());
            if(posicion==-1)integerList.add(rubroEvaluacionProcesoC.getDesempenioIcdId());
        }

        return integerList.size();
    }

    @Override
    public String cantidadRubroEvaluadosPorSession(int sesionAprendizaje, DatabaseWrapper databaseWrapper) {

        RubroEvaluacionProcesoC rubroEvaluacionProcesoC = null;

        rubroEvaluacionProcesoC = SQLite.select()
                .from(RubroEvaluacionProcesoC.class)
                .where(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.eq(sesionAprendizaje))
                .and(RubroEvaluacionProcesoC_Table.tiporubroid.eq(RubroEvaluacionProcesoC.TIPORUBRO_BIMENSIONAL))
                .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                .orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.desc())
                .querySingle(databaseWrapper);

        if(rubroEvaluacionProcesoC == null){
            rubroEvaluacionProcesoC = SQLite.select()
                    .from(RubroEvaluacionProcesoC.class)
                    .where(RubroEvaluacionProcesoC_Table.sesionAprendizajeId.eq(sesionAprendizaje))
                    .and(RubroEvaluacionProcesoC_Table.tiporubroid.eq(RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL))
                    .and(RubroEvaluacionProcesoC_Table.estadoId.notEq(RubroEvaluacionProcesoC.ESTADO_ELIMINADO))
                    //.orderBy(RubroEvaluacionProcesoC_Table.fechaCreacion.desc())
                    .querySingle(databaseWrapper);
        }

        if(rubroEvaluacionProcesoC == null) return "";

        List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvaluacionProcesoC.getKey()))
                .queryList(databaseWrapper);

        int evaluados = 0;
        for (EvaluacionProcesoC evaluacionProcesoC :evaluacionProcesoCList){
            if(evaluacionProcesoC.getNota() > 0){
                evaluados++;
            }
        }
        return evaluados+" / "+evaluacionProcesoCList.size();
    }





}
