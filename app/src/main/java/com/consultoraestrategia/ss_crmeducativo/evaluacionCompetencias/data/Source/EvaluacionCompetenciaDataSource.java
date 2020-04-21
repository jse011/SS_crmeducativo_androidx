package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public interface EvaluacionCompetenciaDataSource {

    interface CallBack {
        void onLista(List<Object> objectList);
    }
    interface CallbackSucces<T>{
        void onLoad(boolean success);
    }
    interface ObjectCallback {
        void onObject(CapacidadUi capacidadUi, boolean success);
        void onError(String mensaje);
    }
    interface Callback2 {
        void onLoad(boolean success, String mensaje);
    }

    void getCompenteciaLista(int periodoId, int cargaCursoId, int cursoId, List<FiltradoUi> filtradoUiList, CallBack callBack);

    void getEnfoqueTransVersal(int periodoId, int cargaCursoId, int cursoId, CallBack callBack);

    void onEvaluacionResultado(Object object,int periodoId,ObjectCallback objectCallback);
    boolean changeToogleCompetencia(int competenciaId, boolean toogle);

    void anclarResultados(int calendarioPeriodoId , int cargaCursoId, List<FiltradoUi> filtradoUiList, Callback2 callback);
    boolean validarAnclaCalendarioPeriodo(int idCalendarioPeriodo, int idCargaCurso, boolean isRubroResultado);

    boolean recalcularMediaDv(int idRubro);

}
