package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.local.EditarRubricaLocalData;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

public class EditarRubricaRepository implements EditarRubricaDataSource {

    private EditarRubricaLocalData localData;

    public EditarRubricaRepository(EditarRubricaLocalData localData) {
        this.localData = localData;
    }

    @Override
    public void obtenerTipoNotaLista(RubBidUi rubBidUi, Callback<List<TipoNotaUi>> listCallback) {
        localData.obtenerTipoNotaLista(rubBidUi, listCallback);
    }

    @Override
    public void obtenerTipoFormaLista(RubBidUi rubBidUi, Callback<List<TipoFormaUi>> listCallback) {
        localData.obtenerTipoFormaLista(rubBidUi, listCallback);
    }

    @Override
    public void obtenerTipoEvaluacionLista(RubBidUi rubBidUi, Callback<List<TipoEvaluacionUi>> listCallback) {
        localData.obtenerTipoEvaluacionLista(rubBidUi, listCallback);
    }

    @Override
    public void obtenerRegistroTipoNota(RubBidUi rubBidUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerRegistroTipoNota(rubBidUi, callBackRegistro);
    }

    @Override
    public void obtenerRegistroTipoForma(RubBidUi rubBidUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerRegistroTipoForma(rubBidUi, callBackRegistro);
    }

    @Override
    public void obtenerRegistroTipoEvaluacion(RubBidUi rubBidUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerRegistroTipoEvaluacion(rubBidUi, callBackRegistro);
    }

    @Override
    public void guardarCambios(RubBidUi rubBidUi,String titulo, String alias, String keyTipoEvaluacion, String keyTipoFormulaEvaluacion, Callback<RubBidUi> callback) {
        localData.guardarCambios(rubBidUi,titulo, alias, keyTipoEvaluacion, keyTipoFormulaEvaluacion, callback);
    }
}
