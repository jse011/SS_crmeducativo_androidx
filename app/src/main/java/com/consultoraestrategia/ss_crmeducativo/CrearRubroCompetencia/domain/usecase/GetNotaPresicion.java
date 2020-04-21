package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.PrecisionUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetNotaPresicion {


    public GetNotaPresicion() {

    }


    public List<PrecisionUi> execute(GetNotaPresicion.RequestValues requestValues) {


        int limiteSuperior = requestValues.getLimiteSuperior();
        int limiteInferior = requestValues.getLimiteInferior();
        Log.d(GetNotaPresicion.class.getSimpleName(), "limiteSuperior: " +limiteSuperior+ " ,limiteInferior: " + limiteInferior);
        int cantidad = limiteSuperior - limiteInferior + 1;
        List<PrecisionUi> notaCircularUis  = new ArrayList<>();
        for (int i = 0; i<cantidad; i++){
            PrecisionUi notaCircularUi = new PrecisionUi();
            notaCircularUi.setContenido(String.valueOf(limiteInferior));
            notaCircularUi.setContornoColor(requestValues.getColor());
            notaCircularUis.add(notaCircularUi);
            limiteInferior ++;
        }
        //Log.d(GetValorTipoNotaPresicion.class.getSimpleName(), "isIncluidoLInferior: " +valorTipoNotaPrecisionUi.isIncluidoLInferior()+ " ,isIncluidoLSuperior: " + valorTipoNotaPrecisionUi.isIncluidoLSuperior());

        PrecisionUi removeNotaInferior = null;
        PrecisionUi removeNotaSuperior = null;

        if(!requestValues.isIncluidoLInferior()){
            removeNotaInferior = notaCircularUis.get(0);
        }

        if(!requestValues.isIncluidoLSuperior()){
            removeNotaSuperior = notaCircularUis.get(cantidad-1);
        }
        if(removeNotaInferior!=null)notaCircularUis.remove(removeNotaInferior);
        if(removeNotaSuperior!=null)notaCircularUis.remove(removeNotaSuperior);

        Collections.reverse(notaCircularUis);
        return notaCircularUis;
    }


    public static class RequestValues {
        private String color;
        private int limiteSuperior;
        private int limiteInferior;
        private boolean incluidoLSuperior;
        private boolean incluidoLInferior;


        public RequestValues(String color, int limiteSuperior, int limiteInferior, boolean incluidoLSuperior, boolean incluidoLInferior) {
            this.color = color;
            this.limiteSuperior = limiteSuperior;
            this.limiteInferior = limiteInferior;
            this.incluidoLSuperior = incluidoLSuperior;
            this.incluidoLInferior = incluidoLInferior;
        }

        public int getLimiteSuperior() {
            return limiteSuperior;
        }


        public int getLimiteInferior() {
            return limiteInferior;
        }


        public String getColor() {
            return color;
        }



        public boolean isIncluidoLInferior() {
            return incluidoLInferior;
        }



        public boolean isIncluidoLSuperior() {
            return incluidoLSuperior;
        }

    }

}
