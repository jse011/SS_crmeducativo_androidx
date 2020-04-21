package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

public interface EditarRubricaDataSource {

    interface Callback<T> {
        void onSuccess(T objeto);
    }
    interface CallBackRegistro<T,P>{
        void onSuccess (T objetoT ,P objetoP);
    }

    void obtenerTipoNotaLista(RubBidUi rubBidUi, Callback<List<TipoNotaUi>> listCallback);

    void obtenerTipoFormaLista(RubBidUi rubBidUi, Callback<List<TipoFormaUi>> listCallback);

    void obtenerTipoEvaluacionLista(RubBidUi rubBidUi, Callback<List<TipoEvaluacionUi>> listCallback);

    void obtenerRegistroTipoNota(RubBidUi rubBidUi,CallBackRegistro<String,String> callBackRegistro);

    void obtenerRegistroTipoForma(RubBidUi rubBidUi,CallBackRegistro<String,String> callBackRegistro);

    void obtenerRegistroTipoEvaluacion(RubBidUi rubBidUi,CallBackRegistro<String,String> callBackRegistro);

    void guardarCambios(RubBidUi rubBidUi,String titulo,String alias,String keyTipoEvaluacion,String keyTipoFormulaEvaluacion,Callback<RubBidUi> callback);
}
