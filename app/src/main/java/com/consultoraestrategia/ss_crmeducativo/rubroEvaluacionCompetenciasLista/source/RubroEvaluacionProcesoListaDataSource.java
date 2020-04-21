package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.RubroProcesoUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public interface RubroEvaluacionProcesoListaDataSource {

    interface ListCallback<T> {
        void onLoaded(List<T> list);
    }

    interface Callback<T> {
        void onLoaded(T item);
    }

    void getCompetenciaList(ArrayList<Integer> competenciaIdList, int sesionAprendizajeId, int nivel, ListCallback<CompetenciaUi> callback);
}

