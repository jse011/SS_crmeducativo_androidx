package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoNotaUi;

import java.util.List;

public interface EditarRubrosDataSource {

    interface Callback<T> {
        void onSuccess(T objeto);
    }
    interface CallBackRegistro<T,P>{
        void onSuccess(T objetoT, P objetoP);
    }

    void obtenerTipoNotaLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoNotaUi>> listCallback);

    void obtenerTipoFormaLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoFormaUi>> listCallback);

    void obtenerTipoEvaluacionLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoEvaluacionUi>> listCallback);

    void obtenerRegistroTipoNota(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro);

    void obtenerRegistroTipoForma(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro);

    void obtenerRegistroTipoEvaluacion(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro);

    void guardarCambios(CapacidadUi capacidadUi,RubroProcesoUi rubroProcesoUi, String titulo, String alias, String keyTipoEvaluacion, String keyTipoFormulaEvaluacion, CallBackRegistro<CapacidadUi,RubroProcesoUi> callback);

    void obtenerTipoFormula(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro);

    void obtenerValorRedondeo(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro);
}
