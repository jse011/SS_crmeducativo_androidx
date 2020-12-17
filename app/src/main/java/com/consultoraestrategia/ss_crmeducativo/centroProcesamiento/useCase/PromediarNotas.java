package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data.CentroProcesamientoRepositorio;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RespGenerarResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class PromediarNotas {
    private CentroProcesamientoRepositorio repositorio;

    public PromediarNotas(CentroProcesamientoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public RetrofitCancel execute(int silaboEventoId, int cargaCursoId, int calendarioPeriodoId, int rubroFormal, Callback callback){
        return repositorio.promediarNotaResultado(silaboEventoId, cargaCursoId, calendarioPeriodoId, rubroFormal, new CentroProcesamientoRepositorio.Callback<List<RespGenerarResultadoUi>>(){
            @Override
            public void onLoad(boolean success,  List<RespGenerarResultadoUi>  item) {
                if(success){
                    callback.onSuccess();
                }else {
                    callback.onError();
                }
            }
        } );
    }

    public interface Callback{
        void onSuccess();
        void onError();
    }
}
