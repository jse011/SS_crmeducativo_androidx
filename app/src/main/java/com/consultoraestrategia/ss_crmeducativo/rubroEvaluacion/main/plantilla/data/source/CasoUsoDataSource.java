package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.entidades.IndicadoresUi;

import java.util.List;

/**
 * Created by kike on 19/02/2018.
 */

public interface CasoUsoDataSource {
    interface ObjectCallback {
        void onDelete(RubroProcesoUi rubroProcesoUi, int validateSuccess);
    }
    interface ObjectCallbackList <T>{
        void onLoadList(T list);
    }
    interface ListCamposTematicosCallBack{
        void onListCamposTematicos(List<Object> camposTematicosUiList);
    }
    interface ListDesempenioIcdsCallBack{
        void onListDesempenioIcds(List<Object> desempenioIcdUiList);
    }
    void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, ObjectCallback objectCallback);
    void showListCamposTematicos(String rubroProcesoKey,ObjectCallbackList<List<IndicadoresCamposAccionUi>> listCamposTematicosCallBack);
    void showListIndicadores(String rubroProcesoKey,ObjectCallbackList<List<IndicadoresUi>> listObjectCallbackList);
    boolean changeToogleCapacidad(int capacidadId, boolean toogle);
}
