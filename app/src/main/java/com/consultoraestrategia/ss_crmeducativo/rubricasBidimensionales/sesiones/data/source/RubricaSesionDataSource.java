package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public interface RubricaSesionDataSource {


    interface CallBackListRub{
        void onListaRubBidList(List<RubBidUi> rubBidUis);
    }

    interface CallBack<T>{
        void onListaRubBidList(boolean success, T item);
    }


    void getRubricasSesion(int sesionAprendizajeId, int cargaCursoid, int cursoId, int calendarioPeriodoId, CallBackListRub callBackListRub);
    void getCalendarioPeriodo(int calendarioPeriodoId, CallBack<PeriodoUi> callBack);
}
