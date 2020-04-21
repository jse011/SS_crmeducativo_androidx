package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source;


import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;

public interface InfoCriterioEvalDataSource {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    void getInformacionIndicador(int indicadorId, Callback<IndicadorUi> callback);
}
