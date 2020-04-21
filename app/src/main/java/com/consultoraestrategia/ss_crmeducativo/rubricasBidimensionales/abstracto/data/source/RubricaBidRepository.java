package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.local.RubricaBidLocal;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

/**
 * Created by CCIE on 20/03/2018.
 */

public class RubricaBidRepository implements RubricaBidDataSource {
    RubricaBidLocal rubricaBidLocal;

    public RubricaBidRepository(RubricaBidLocal rubricaBidLocal) {
        this.rubricaBidLocal = rubricaBidLocal;
    }

    @Override
    public void getRubricaBidItem(int rubroEvalBidId,int countIndicador, CallBackRubricaBid callBackRubricaBid) {
        rubricaBidLocal.getRubricaBidItem(rubroEvalBidId,countIndicador, callBackRubricaBid);
    }

    @Override
    public void deleteRubricaBidItem(RubBidUi rubBidUi, CallBackEliminarRubricaBid callBackRubricaBid) {
        rubricaBidLocal.deleteRubricaBidItem(rubBidUi,callBackRubricaBid);
    }

    @Override
    public boolean publicarEvaluacion(String rubricaEvaluacionId) {
        return rubricaBidLocal.publicarEvaluacion(rubricaEvaluacionId);
    }

    @Override
    public PeriodoUi getPeriodo(int calendarioPeriodoId, int cargaCursoId) {
        return rubricaBidLocal.getPeriodo(calendarioPeriodoId, cargaCursoId);
    }

}
