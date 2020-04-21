package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.local.RubricaSesionLocal;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.remote.RubricaSesionRemote;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaSesionRepository implements RubricaSesionDataSource {

    private RubricaSesionLocal rubricaSesionLocal;
    private RubricaSesionRemote rubricaSesionRemote;

    public RubricaSesionRepository(RubricaSesionLocal rubricaSesionLocal, RubricaSesionRemote rubricaSesionRemote) {
        this.rubricaSesionLocal = rubricaSesionLocal;
        this.rubricaSesionRemote = rubricaSesionRemote;
    }

    @Override
    public void getRubricasSesion(int sesionAprendizajeId, int cargaCursoid, int cursoId, int calendarioPeriodoId, CallBackListRub callBackListRub) {
        rubricaSesionLocal.getRubricasSesion(sesionAprendizajeId, cargaCursoid, cursoId, calendarioPeriodoId, callBackListRub);
        rubricaSesionRemote.getRubricasSesion(sesionAprendizajeId, cargaCursoid, cursoId, calendarioPeriodoId, callBackListRub);
    }

    @Override
    public void getCalendarioPeriodo(int calendarioPeriodoId, CallBack<PeriodoUi> callBack) {
        rubricaSesionLocal.getCalendarioPeriodo(calendarioPeriodoId, callBack);
        rubricaSesionRemote.getCalendarioPeriodo(calendarioPeriodoId, callBack);
    }
}
