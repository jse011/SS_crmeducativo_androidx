package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.RecursosDidacticoUi;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public interface GetRecursoDidacticoCallback {
    void onRecursoLoad(List<RecursosDidacticoUi> recursosDidacticoUis);
    void onError(String errot);

}
