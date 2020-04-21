package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase;



import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.TecladoNumerico;


public class ValidarTipoNotaTeclado extends UseCase<ValidarTipoNotaTeclado.RequestValues, ValidarTipoNotaTeclado.ResponseValue> {

    @Override
    protected void executeUseCase(final ValidarTipoNotaTeclado.RequestValues requestValues) {
        try {

        if(!validarInclucionInferior(requestValues.getNota(), requestValues.getLimiteInferior(), requestValues.isIncluidoLInferior())){
            getUseCaseCallback().onSuccess(new ResponseValue(false,"Limite inferior incorrecto" ));
        }else if(!validarInclucionSuperior(requestValues.getNota(), requestValues.getLimiteSuperior(), requestValues.isIncluidoLSuperior())){
            getUseCaseCallback().onSuccess(new ResponseValue(false,"Limite superior incorrecto" ));

        }else {
            getUseCaseCallback().onSuccess(new ResponseValue(true,"Exito" ));
        }
        } catch (Exception e) {
            e.printStackTrace();
            getUseCaseCallback().onError();
        }

    }

    private boolean validarInclucionInferior(double nota, double limite, boolean limiteIncluido){
        if(limiteIncluido){
            if(nota >= limite){
                return  true;
            }else {
                return  false;
            }
        }else {
            if(nota > limite){
                return  true;
            }else {
                return  false;
            }
        }
    }

    private boolean validarInclucionSuperior(double nota, double limite, boolean limiteIncluido){
        if(limiteIncluido){
            if(nota <= limite){
                return  true;
            }else {
                return  false;
            }
        }else {
            if(nota < limite){
                return  true;
            }else {
                return  false;
            }
        }
    }

    public static class RequestValues implements  UseCase.RequestValues{
        private double nota;
        private double limiteSuperior;
        private double limiteInferior;
        private boolean incluidoLInferior;
        private boolean incluidoLSuperior;

        public RequestValues(double nota, double limiteSuperior, double limiteInferior, boolean incluidoLInferior, boolean incluidoLSuperior) {
            this.nota = nota;
            this.limiteSuperior = limiteSuperior;
            this.limiteInferior = limiteInferior;
            this.incluidoLInferior = incluidoLInferior;
            this.incluidoLSuperior = incluidoLSuperior;
        }

        public double getNota() {
            return nota;
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
       private boolean success;
       private String mesage;

        public ResponseValue(boolean success, String mesage) {
            this.success = success;
            this.mesage = mesage;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMesage() {
            return mesage;
        }
    }
}
