package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.local;

import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TipoEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

public class EditarRubricaLocalData implements EditarRubricaDataSource {

    private RubroProcesoDao rubroEvaluacionDao;
    private TiposDao tiposDao;
    private TipoEvaluacionDao tipoEvaluacionDao;
    private TipoNotaDao tipoNotaDao;



    public EditarRubricaLocalData(RubroProcesoDao rubroEvaluacionDao, TiposDao tiposDao, TipoEvaluacionDao tipoEvaluacionDao, TipoNotaDao tipoNotaDao) {
        this.rubroEvaluacionDao = rubroEvaluacionDao;
        this.tiposDao = tiposDao;
        this.tipoEvaluacionDao = tipoEvaluacionDao;
        this.tipoNotaDao = tipoNotaDao;
    }

    /*Por ahora no se podra editar el tipoNota*/
    @Override
    public void obtenerTipoNotaLista(RubBidUi rubBidUi, Callback<List<TipoNotaUi>> listCallback) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubBidUi.getKey());
        if (procesoC == null) {
            listCallback.onSuccess(null);
        } else {

        }
    }

    @Override
    public void obtenerTipoFormaLista(RubBidUi rubBidUi, Callback<List<TipoFormaUi>> listCallback) {
        List<Tipos> tiposList = tiposDao.getFormaEvaluacionList();
        if (tiposList == null) return;
        List<TipoFormaUi> tipoFormaUiList = TipoFormaUi.transformFormaEvaluacion(tiposList);
        listCallback.onSuccess(tipoFormaUiList);
    }

    @Override
    public void obtenerTipoEvaluacionLista(RubBidUi rubBidUi, Callback<List<TipoEvaluacionUi>> listCallback) {
        List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacionList = tipoEvaluacionDao.getAll();
        if (tipoEvaluacionList == null) return;
        List<TipoEvaluacionUi> tipoUiList = TipoEvaluacionUi.transformTipoEvaluacion(tipoEvaluacionList);
        listCallback.onSuccess(tipoUiList);
    }

    @Override
    public void obtenerRegistroTipoNota(RubBidUi rubBidUi, CallBackRegistro<String, String> callBackRegistro) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubBidUi.getKey());
        TipoNotaC tipoNotaC = tipoNotaDao.getMyTipoNota(procesoC.getTipoNotaId());
        if (tipoNotaC == null) return;
        callBackRegistro.onSuccess(tipoNotaC.getKey(),tipoNotaC.getNombre());
    }

    @Override
    public void obtenerRegistroTipoForma(RubBidUi rubBidUi, CallBackRegistro<String, String> callBackRegistro) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubBidUi.getKey());
        Tipos tipos = tiposDao.get(procesoC.getFormaEvaluacionId());
        if (tipos == null) return;
        callBackRegistro.onSuccess(String.valueOf(tipos.getTipoId()),tipos.getNombre());
    }

    @Override
    public void obtenerRegistroTipoEvaluacion(RubBidUi rubBidUi, CallBackRegistro<String, String> callBackRegistro) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubBidUi.getKey());
        T_RN_MAE_TIPO_EVALUACION rn_mae_tipo_evaluacion = tipoEvaluacionDao.obtenerRegistroTipoEvaluacion(procesoC.getTipoEvaluacionId());
        if (rn_mae_tipo_evaluacion == null) return;
        callBackRegistro.onSuccess(String.valueOf(rn_mae_tipo_evaluacion.getTipoEvaluacionId()),rn_mae_tipo_evaluacion.getNombre());
    }

    @Override
    public void guardarCambios(final RubBidUi rubBidUi, final String titulo, final String alias, String keyTipoEvaluacion, String keyTipoFormulaEvaluacion, final Callback<RubBidUi> callback) {
       actualizarRubricarBid(rubBidUi, titulo, alias, keyTipoEvaluacion, keyTipoFormulaEvaluacion, new BaseDaoImpl.Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                if(result){
                    rubBidUi.setTitle(titulo);
                    rubBidUi.setAlias(alias);
                    callback.onSuccess(rubBidUi);
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });
    }

    private RubroEvaluacionProcesoC obtenerRegistroRubro(String keyRubro) {
        return rubroEvaluacionDao.getMyRegistro(keyRubro);
    }

    public void actualizarRubricarBid(final RubBidUi rubBidUi, final String titulo, final String alias, final String keyTipoEvaluacion, final String keyTipoFormaEvaluacion, final BaseDaoImpl.Callback<Boolean> callback) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(rubBidUi.getKey()))
                        .querySingle();
                if(rubroEvaluacionProcesoC!=null){
                    rubroEvaluacionProcesoC.setTitulo(titulo);
                    rubroEvaluacionProcesoC.setTipoEvaluacionId(Integer.valueOf(keyTipoEvaluacion));
                    rubroEvaluacionProcesoC.setFormaEvaluacionId(Integer.valueOf(keyTipoFormaEvaluacion));
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
}
