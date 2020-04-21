package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;

import java.util.List;

/**
 * Created by CCIE on 20/03/2018.
 */

public interface RubricaBidDataSource {


    interface CallBackRubricaBid{
        void onActualizacionRubricaBid(RubBidUi rubBidUi);
    }
    interface CallBackEliminarRubricaBid{
        void onEliminarRubricaBid(RubBidUi rubBidUi, int estado, List<RubEvalProcUi> rubEvalProcUis);
    }

    void getRubricaBidItem(int rubroEvalBidId,int countIndicador, CallBackRubricaBid callBackRubricaBid);

    void deleteRubricaBidItem(RubBidUi rubBidUi,CallBackEliminarRubricaBid callBackRubricaBid);

    boolean publicarEvaluacion(String rubricaEvaluacionId);

    PeriodoUi getPeriodo(int calendarioPeriodoId, int cargaCursoId);

}
