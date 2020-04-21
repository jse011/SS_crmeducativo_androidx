package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;

/**
 * Created by @stevecampos on 3/04/2018.
 */

public interface EvaluacionCompetenciaListener {
    void onObjectClicked(Object object);

    void onAceptarDialogEvaluar(Object object);
    void clickToogle(CompetenciaUi competenciaUi );
}
