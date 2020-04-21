package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase;



import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.TecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.UploadFile;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.math.BigDecimal;

import okhttp3.internal.Util;


public class ConvertirTipoNotaTeclado extends UseCase<ConvertirTipoNotaTeclado.RequestValues, ConvertirTipoNotaTeclado.ResponseValue> {

    @Override
    protected void executeUseCase(final ConvertirTipoNotaTeclado.RequestValues requestValues) {
        try {
        String cantidadTexto = requestValues.getCantidadActual();
        if(TextUtils.isEmpty(cantidadTexto)) cantidadTexto = "";

        switch (requestValues.getTecladoNumerico()){
            case REMOVER:
                cantidadTexto = removeLastCharacter(cantidadTexto);
                break;
            default:
                cantidadTexto += requestValues.getTecladoNumerico().getValor();
                break;
        }

        double nota = 0D;
        if(!TextUtils.isEmpty(cantidadTexto)&&!cantidadTexto.equals(TecladoNumerico.PUNTO.getValor()))nota = Double.parseDouble(cantidadTexto);

            BigDecimal bigDecimal  = new BigDecimal(nota);
            long iPart = bigDecimal.longValue();
            long precision = bigDecimal.remainder(BigDecimal.ONE).longValue();
            if(precision > 1 ){
                cantidadTexto = removeLastCharacter(cantidadTexto);
            }
            if(formatearDecimales(cantidadTexto)>3)cantidadTexto= removeLastCharacter(cantidadTexto);

        getUseCaseCallback().onSuccess(new ResponseValue(nota, cantidadTexto));
        } catch (Exception e) {
            e.printStackTrace();
            getUseCaseCallback().onError();
        }

    }
    private int formatearDecimales(String texto){
        int count=0;
        boolean find=false;
        for (int x=0;x<texto.length();x++){
            if(String.valueOf(texto.charAt(x)).equals("."))find=true;
            if(find)count=count+1;
        }
        return count;
    }

    private String removeLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static class RequestValues implements  UseCase.RequestValues{
        private String cantidadActual;
        private TecladoNumerico tecladoNumerico;
        private double limiteSuperior;
        private double limiteInferior;
        private boolean incluidoLInferior;
        private boolean incluidoLSuperior;

        public RequestValues(String cantidadActual, TecladoNumerico tecladoNumerico, double limiteSuperior, double limiteInferior, boolean incluidoLInferior, boolean incluidoLSuperior) {
            this.cantidadActual = cantidadActual;
            this.tecladoNumerico = tecladoNumerico;
            this.limiteSuperior = limiteSuperior;
            this.limiteInferior = limiteInferior;
            this.incluidoLInferior = incluidoLInferior;
            this.incluidoLSuperior = incluidoLSuperior;
        }

        public String getCantidadActual() {
            return cantidadActual;
        }

        public TecladoNumerico getTecladoNumerico() {
            return tecladoNumerico;
        }

        public double getLimiteSuperior() {
            return limiteSuperior;
        }

        public double getLimiteInferior() {
            return limiteInferior;
        }

        public boolean isIncluidoLInferior() {
            return incluidoLInferior;
        }

        public boolean isIncluidoLSuperior() {
            return incluidoLSuperior;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
       private Double cantidad;
       private String cantidadTexto;

        public ResponseValue(Double cantidad, String cantidadTexto) {
            this.cantidad = cantidad;
            this.cantidadTexto = cantidadTexto;
        }

        public Double getCantidad() {
            return cantidad;
        }

        public String getCantidadTexto() {
            return cantidadTexto;
        }
    }
}
