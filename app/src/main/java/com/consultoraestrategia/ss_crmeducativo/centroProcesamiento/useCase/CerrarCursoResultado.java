package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data.CentroProcesamientoRepositorio;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class CerrarCursoResultado {
    private CentroProcesamientoRepositorio repositorio;

    public CerrarCursoResultado(CentroProcesamientoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public RetrofitCancel execute(int cargaCursoId, int calendarioPeriodoId, Callback callback){
        return repositorio.updateCargaCursoCalendarioPeriodo(cargaCursoId, calendarioPeriodoId, new CentroProcesamientoRepositorio.SuccessCallback() {
            @Override
            public void onLoad(boolean success) {
                if(success){
                    callback.onSuccess();
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSuccess();
        void onError();
    }
}
