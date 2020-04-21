package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public interface RubricaBidDataSource {

    interface CallBackList{
        void onLista(List<PeriodoUi> lista);
    }
    interface CallBackListRub{
        void onListaRubBidList(List<RubBidUi> rubBidUis);
    }

    void getPeriodoList(int cursoId, int cargaCursoid, CallBackList periodoUiCallBackList);

    void getRubricasBid(int periodoId, int cargaCursoid,int cursoId, CallBackListRub callBackListRub);
}
