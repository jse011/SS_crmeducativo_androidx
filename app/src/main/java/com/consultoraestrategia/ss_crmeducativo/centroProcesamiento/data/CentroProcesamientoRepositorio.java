package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.MatrizResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RespGenerarResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public interface CentroProcesamientoRepositorio {
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    interface SuccessCallback{
        void onLoad(boolean success);
    }
    RetrofitCancel getMatrizResultado(int silaboEveId, int cargaCursoId, int calendarioPerId, int rubroformal, Callback<MatrizResultadoUi> callback);
    List<PeriodoUi> getCalendarioPeriodo(int programaId, int anioAcademicoId, int cargaCursoId);
    RetrofitCancel promediarNotaResultado(int silaboEventoId, int cargaCursoId, int calendarioPeriodoId, int rubroFormal, Callback< List<RespGenerarResultadoUi> > stringCallback);
    RetrofitCancel updateCargaCursoCalendarioPeriodo(int cargaCursoId, int calendarioPeriodoId, SuccessCallback callback);
}
