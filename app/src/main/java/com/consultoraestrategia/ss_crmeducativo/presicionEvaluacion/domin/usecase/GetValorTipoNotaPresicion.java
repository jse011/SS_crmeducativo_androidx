package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.PresicionDataSource;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.PresicionRepository;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.ValorTipoNotaPrecisionUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetValorTipoNotaPresicion extends UseCase<GetValorTipoNotaPresicion.RequestValues, GetValorTipoNotaPresicion.ResponseValue> {
    PresicionRepository repository;

    public GetValorTipoNotaPresicion(PresicionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(final GetValorTipoNotaPresicion.RequestValues requestValues) {
        repository.getTipoNota(requestValues.getRubroEvaluacionId(), requestValues.getValorTipoNotaId(), new PresicionDataSource.Callback<ValorTipoNotaPrecisionUi>() {
            @Override
            public void onLoad(boolean success, ValorTipoNotaPrecisionUi item) {
                if(success){
                    item.setNotaCircularUiList(calcularNotaCiruclar(item, requestValues.getColorEvaluacion()));
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    private List<NotaCircularUi> calcularNotaCiruclar(ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi, String color){

        int limiteSuperior = (int)valorTipoNotaPrecisionUi.getLimiteSuperior();
        int limiteInferior = (int)valorTipoNotaPrecisionUi.getLimiteInferior();
        Log.d(GetValorTipoNotaPresicion.class.getSimpleName(), "limiteSuperior: " +limiteSuperior+ " ,limiteInferior: " + limiteInferior);
        int cantidad = limiteSuperior - limiteInferior + 1;
        List<NotaCircularUi> notaCircularUis  = new ArrayList<>();
        for (int i = 0; i<cantidad; i++){
            NotaCircularUi notaCircularUi = new NotaCircularUi();
            notaCircularUi.setContenido(String.valueOf(limiteInferior));
            notaCircularUi.setContornoColor(color);
            notaCircularUis.add(notaCircularUi);
            limiteInferior ++;
        }
        Log.d(GetValorTipoNotaPresicion.class.getSimpleName(), "isIncluidoLInferior: " +valorTipoNotaPrecisionUi.isIncluidoLInferior()+ " ,isIncluidoLSuperior: " + valorTipoNotaPrecisionUi.isIncluidoLSuperior());

        NotaCircularUi removeNotaInferior = null;
        NotaCircularUi removeNotaSuperior = null;

        if(!valorTipoNotaPrecisionUi.isIncluidoLInferior()){
            removeNotaInferior = notaCircularUis.get(0);
        }

        if(!valorTipoNotaPrecisionUi.isIncluidoLSuperior()){
            removeNotaSuperior = notaCircularUis.get(cantidad-1);
        }
        if(removeNotaInferior!=null)notaCircularUis.remove(removeNotaInferior);
        if(removeNotaSuperior!=null)notaCircularUis.remove(removeNotaSuperior);

        Collections.reverse(notaCircularUis);
        return notaCircularUis;
    }

    public static class RequestValues implements  UseCase.RequestValues{
        private String rubroEvaluacionId;
        private String valorTipoNotaId;
        private String colorEvaluacion;

        public RequestValues(String rubroEvaluacionId, String valorTipoNotaId, String colorEvaluacion) {
            this.valorTipoNotaId = valorTipoNotaId;
            this.colorEvaluacion = colorEvaluacion;
            this.rubroEvaluacionId = rubroEvaluacionId;
        }

        public String getRubroEvaluacionId() {
            return rubroEvaluacionId;
        }

        public String getValorTipoNotaId() {
            return valorTipoNotaId;
        }

        public String getColorEvaluacion() {
            return colorEvaluacion;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi;

        public ResponseValue(ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi) {
            this.valorTipoNotaPrecisionUi = valorTipoNotaPrecisionUi;
        }

        public ValorTipoNotaPrecisionUi getValorTipoNotaPrecisionUi() {
            return valorTipoNotaPrecisionUi;
        }
    }
}
