package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.RubricaSesionDataSource;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaSesionRemote implements RubricaSesionDataSource {

    private static String RUBRICA_BID_LOCAL_TAG = RubricaSesionRemote.class.getSimpleName();


    @Override
    public void getRubricasSesion(int sesionAprendizajeId, int cargaCursoid, int cursoId, int calendarioPeriodoId, CallBackListRub callBackListRub) {

    }

    @Override
    public void getCalendarioPeriodo(int calendarioPeriodoId, CallBack<PeriodoUi> callBack) {

    }
}
