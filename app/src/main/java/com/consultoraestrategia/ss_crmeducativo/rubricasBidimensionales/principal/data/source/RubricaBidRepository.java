package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.local.RubricaBidLocal;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.remote.RubricaBidRemote;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaBidRepository implements RubricaBidDataSource {

    RubricaBidLocal rubricaBidLocal;
    RubricaBidRemote rubricaBidRemote;

    public RubricaBidRepository(RubricaBidLocal rubricaBidLocal, RubricaBidRemote rubricaBidRemote) {
        this.rubricaBidLocal = rubricaBidLocal;
        this.rubricaBidRemote = rubricaBidRemote;
    }

    @Override
    public void getPeriodoList(int periodoId, int cargaCursoid, CallBackList callBackList) {
        rubricaBidLocal.getPeriodoList(periodoId, cargaCursoid, callBackList);
        rubricaBidRemote.getPeriodoList(periodoId, cargaCursoid, callBackList);
    }

    @Override
    public void getRubricasBid(int periodoId, int cargaCursoid, int cursoId, CallBackListRub callBackListRub) {
        rubricaBidLocal.getRubricasBid(periodoId, cargaCursoid, cursoId, callBackListRub);
        rubricaBidRemote.getRubricasBid(periodoId, cargaCursoid, cursoId, callBackListRub);
    }

}
