package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.local;


import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TipoEvaluacionDao;
import com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao.TipoNotaDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao.TiposDao;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoNotaUi;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosDataSource;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

public class EditarRubrosLocalData implements EditarRubrosDataSource {
    public static final String TAG = EditarRubrosLocalData.class.getSimpleName();

    private RubroProcesoDao rubroEvaluacionDao;
    private TiposDao tiposDao;
    private TipoEvaluacionDao tipoEvaluacionDao;
    private TipoNotaDao tipoNotaDao;

    public EditarRubrosLocalData(RubroProcesoDao rubroEvaluacionDao, TiposDao tiposDao, TipoEvaluacionDao tipoEvaluacionDao, TipoNotaDao tipoNotaDao) {
        this.rubroEvaluacionDao = rubroEvaluacionDao;
        this.tiposDao = tiposDao;
        this.tipoEvaluacionDao = tipoEvaluacionDao;
        this.tipoNotaDao = tipoNotaDao;
    }

    /*Por ahora no se podra editar el tipoNota*/
    @Override
    public void obtenerTipoNotaLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoNotaUi>> listCallback) {

    }

    @Override
    public void obtenerTipoFormaLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoFormaUi>> listCallback) {
        List<Tipos> tiposList = tiposDao.getFormaEvaluacionList();
        if (tiposList == null) return;
        List<TipoFormaUi> tipoFormaUiList = TipoFormaUi.transformFormaEvaluacion(tiposList);
        listCallback.onSuccess(tipoFormaUiList);
    }

    @Override
    public void obtenerTipoEvaluacionLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoEvaluacionUi>> listCallback) {
        List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacionList = tipoEvaluacionDao.getAll();
        if (tipoEvaluacionList == null) return;
        List<TipoEvaluacionUi> tipoUiList = TipoEvaluacionUi.transformTipoEvaluacion(tipoEvaluacionList);
        listCallback.onSuccess(tipoUiList);
    }

    @Override
    public void obtenerRegistroTipoNota(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubroProcesoUi.getKey());
        TipoNotaC tipoNotaC = tipoNotaDao.getMyTipoNota(procesoC.getTipoNotaId());
        if (tipoNotaC == null) return;
        callBackRegistro.onSuccess(tipoNotaC.getKey(), tipoNotaC.getNombre());
    }

    @Override
    public void obtenerRegistroTipoForma(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubroProcesoUi.getKey());
        Tipos tipos = tiposDao.get(procesoC.getFormaEvaluacionId());
        if (tipos == null) return;
        callBackRegistro.onSuccess(String.valueOf(tipos.getTipoId()), tipos.getNombre());
    }

    @Override
    public void obtenerRegistroTipoEvaluacion(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        RubroEvaluacionProcesoC procesoC = obtenerRegistroRubro(rubroProcesoUi.getKey());
        T_RN_MAE_TIPO_EVALUACION rn_mae_tipo_evaluacion = tipoEvaluacionDao.obtenerRegistroTipoEvaluacion(procesoC.getTipoEvaluacionId());
        if (rn_mae_tipo_evaluacion == null) return;
        callBackRegistro.onSuccess(String.valueOf(rn_mae_tipo_evaluacion.getTipoEvaluacionId()), rn_mae_tipo_evaluacion.getNombre());

    }

    @Override
    public void guardarCambios(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, final String titulo, final String alias, final String keyTipoEvaluacion, final String keyTipoFormulaEvaluacion, final CallBackRegistro<CapacidadUi, RubroProcesoUi> callback) {
        Log.d(TAG,"rubroProcesoUi : "+rubroProcesoUi.getTipoEvaluacionId()+"/ "+rubroProcesoUi.getTipoFormulaId());

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                RubroEvaluacionProcesoC rubroEvaluacionProcesoC = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.eq(rubroProcesoUi.getKey()))
                        .querySingle();
                if(rubroEvaluacionProcesoC!=null){
                    rubroEvaluacionProcesoC.setTitulo(titulo);
                    rubroEvaluacionProcesoC.setSubtitulo(alias);
                    rubroEvaluacionProcesoC.setTipoEvaluacionId(Integer.valueOf(keyTipoEvaluacion));
                    rubroEvaluacionProcesoC.setFormaEvaluacionId(Integer.valueOf(keyTipoFormulaEvaluacion));
                    rubroEvaluacionProcesoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
                    rubroEvaluacionProcesoC.save();

                    int tipoEvaluacionId = Integer.parseInt(keyTipoEvaluacion);
                    int tipoFormulaEvaluacionId = Integer.parseInt(keyTipoFormulaEvaluacion);
                    rubroProcesoUi.setTitulo(titulo);
                    rubroProcesoUi.setSubTitulo(alias);
                    T_RN_MAE_TIPO_EVALUACION tipo = SQLite.select()
                            .from(T_RN_MAE_TIPO_EVALUACION.class)
                            .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.eq(tipoEvaluacionId))
                            .querySingle();
                    if(tipo!=null) rubroProcesoUi.setTipoEvaluacion(tipo.getNombre());

                    if(tipoFormulaEvaluacionId==477)
                        rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.INDIVIDUAL);
                    else if(tipoEvaluacionId == 478)
                        rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.GRUPAL);
                    else
                        rubroProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);

                    callback.onSuccess(capacidadUi, rubroProcesoUi);
                }

            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {

            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {

            }
        }).build();
        transaction.execute();


    }

    @Override
    public void obtenerTipoFormula(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        try {
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = rubroEvaluacionDao.get(rubroProcesoUi.getKey());
            Tipos tipos = tiposDao.get(rubroEvaluacionProcesoC.getTipoFormulaId());
            callBackRegistro.onSuccess(String.valueOf(tipos.getTipoId()), tipos.getNombre());
        }catch (Exception e){
            e.printStackTrace();
            callBackRegistro.onSuccess(null,null);
        }

    }

    @Override
    public void obtenerValorRedondeo(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        try {
            RubroEvaluacionProcesoC rubroEvaluacionProcesoC = rubroEvaluacionDao.get(rubroProcesoUi.getKey());
            Tipos tipos = tiposDao.get(rubroEvaluacionProcesoC.getValorRedondeoId());
            callBackRegistro.onSuccess(String.valueOf(tipos.getTipoId()), tipos.getNombre());
        }catch (Exception e){
            e.printStackTrace();
            callBackRegistro.onSuccess(null,null);
        }
    }

    private RubroEvaluacionProcesoC obtenerRegistroRubro(String keyRubro) {
        return rubroEvaluacionDao.getMyRegistro(keyRubro);
    }





}
