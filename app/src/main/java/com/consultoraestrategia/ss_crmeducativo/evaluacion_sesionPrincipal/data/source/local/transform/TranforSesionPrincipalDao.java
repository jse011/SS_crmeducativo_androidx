package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.transform;

import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

/**
 * Created by SCIEV on 24/04/2018.
 */

public class TranforSesionPrincipalDao {

    public static RubroEvaluacionUi getTansforRubroEvaluacionUi(RubroEvaluacionProcesoC input) {
        RubroEvaluacionUi rubroEvaluacionUi = new RubroEvaluacionUi();
        rubroEvaluacionUi.setId(input.getKey());
        rubroEvaluacionUi.setTitulo(input.getTitulo());
        rubroEvaluacionUi.setSesionAprendizajeId(input.getSesionAprendizajeId());
        rubroEvaluacionUi.setCalendarioPeriodoId(input.getCalendarioPeriodoId());
        switch (input.getTiporubroid()){
            case RubroEvaluacionProcesoC.TIPORUBRO_BIDIMENCIONAL_DETALLE:
                rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.RUBRICA_DETALLE);
                break;
            case RubroEvaluacionProcesoC.TIPORUBRO_UNIDIMENCIONAL:
                rubroEvaluacionUi.setTipo(RubroEvaluacionUi.Tipo.NORMAL);
                break;
        }
        return rubroEvaluacionUi;
    }

    public static IndicadorUi getTansforIndicadorUi(Icds icds){
        IndicadorUi indicadorUi = new IndicadorUi();
        indicadorUi.setId(icds.getIcdId());
        indicadorUi.setTitle(icds.getTitulo());
        indicadorUi.setAlias(icds.getAlias());
        return indicadorUi;
    }

}
