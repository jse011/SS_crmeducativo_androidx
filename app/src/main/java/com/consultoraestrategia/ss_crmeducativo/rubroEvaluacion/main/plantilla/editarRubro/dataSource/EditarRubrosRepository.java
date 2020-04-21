package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import java.util.List;

public class EditarRubrosRepository implements EditarRubrosDataSource {

    private EditarRubrosDataSource localData;

    public EditarRubrosRepository(EditarRubrosDataSource localData) {
        this.localData = localData;
    }

    @Override
    public void obtenerTipoNotaLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoNotaUi>> listCallback) {
        localData.obtenerTipoNotaLista(rubroProcesoUi, listCallback);
    }

    @Override
    public void obtenerTipoFormaLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoFormaUi>> listCallback) {
        localData.obtenerTipoFormaLista(rubroProcesoUi, listCallback);
    }

    @Override
    public void obtenerTipoEvaluacionLista(RubroProcesoUi rubroProcesoUi, Callback<List<TipoEvaluacionUi>> listCallback) {
        localData.obtenerTipoEvaluacionLista(rubroProcesoUi, listCallback);
    }

    @Override
    public void obtenerRegistroTipoNota(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerRegistroTipoNota(rubroProcesoUi, callBackRegistro);
    }

    @Override
    public void obtenerRegistroTipoForma(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerRegistroTipoForma(rubroProcesoUi, callBackRegistro);
    }

    @Override
    public void obtenerRegistroTipoEvaluacion(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerRegistroTipoEvaluacion(rubroProcesoUi, callBackRegistro);
    }

    @Override
    public void guardarCambios(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, String titulo, String alias, String keyTipoEvaluacion, String keyTipoFormulaEvaluacion, CallBackRegistro<CapacidadUi, RubroProcesoUi> callback) {
        localData.guardarCambios(capacidadUi,rubroProcesoUi,titulo, alias, keyTipoEvaluacion, keyTipoFormulaEvaluacion, callback);
    }

    @Override
    public void obtenerTipoFormula(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerTipoFormula(rubroProcesoUi,callBackRegistro);
    }

    @Override
    public void obtenerValorRedondeo(RubroProcesoUi rubroProcesoUi, CallBackRegistro<String, String> callBackRegistro) {
        localData.obtenerTipoFormula(rubroProcesoUi,callBackRegistro);
    }

}
