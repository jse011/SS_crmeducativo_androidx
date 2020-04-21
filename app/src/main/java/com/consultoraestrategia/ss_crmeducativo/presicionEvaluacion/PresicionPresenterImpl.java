package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase.GetValorTipoNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase.ConvertirTipoNotaTeclado;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase.ValidarTipoNotaTeclado;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.TecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.ValorTipoNotaPrecisionUi;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.PresicionDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.PresicionView;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;
import java.util.Locale;

public class PresicionPresenterImpl extends BaseFragmentPresenterImpl<PresicionView> implements PresicionPresenter{

    private GetValorTipoNotaPresicion getValorTipoNotaPresicion;
    private ConvertirTipoNotaTeclado convertirTipoNotaTeclado;
    private ValidarTipoNotaTeclado validarTipoNotaTeclado;
    private ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi;
    private String rubroEvaluacionId;
    private String valorTipoNotaId;
    private double notaAnterior;
    private String notaText;
    private double notaTeclado;

    public PresicionPresenterImpl(UseCaseHandler handler, Resources res, GetValorTipoNotaPresicion getValorTipoNotaPresicion, ConvertirTipoNotaTeclado convertirTipoNotaTeclado, ValidarTipoNotaTeclado validarTipoNotaTeclado) {
        super(handler, res);
        this.getValorTipoNotaPresicion = getValorTipoNotaPresicion;
        this.convertirTipoNotaTeclado = convertirTipoNotaTeclado;
        this.validarTipoNotaTeclado = validarTipoNotaTeclado;
    }

    @Override
    protected String getTag() {
        return PresicionPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        notaAnterior = extras.getDouble(PresicionDialogFragment.NOTA);
        rubroEvaluacionId = extras.getString(PresicionDialogFragment.RUBROEVALUACIONID);
        valorTipoNotaId = extras.getString(PresicionDialogFragment.VALORTIPONOTAID);
        String color = extras.getString(PresicionDialogFragment.COLOREVALUACION);
        Log.d(getTag(),extras+"" );
        Log.d(getTag(),"valorTipoNotaId: " + valorTipoNotaId);
        getValorTipoNotaPresicion(rubroEvaluacionId,valorTipoNotaId, color);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClickCalAceptar() {
        validarTipoNotaTeclado(notaTeclado, valorTipoNotaPrecisionUi);
    }

    @Override
    public void onClickCalEspacio() {

    }

    @Override
    public void onClickCalNumero(TecladoNumerico tecladoNumerico) {
        convertirTipoNotaTeclado(notaText, tecladoNumerico, valorTipoNotaPrecisionUi);
    }

    @Override
    public void onClickCalBorrar() {
        convertirTipoNotaTeclado(notaText, TecladoNumerico.REMOVER, valorTipoNotaPrecisionUi);
    }

    @Override
    public void onClickCalPunto() {
        convertirTipoNotaTeclado(notaText, TecladoNumerico.PUNTO, valorTipoNotaPrecisionUi);
    }

    @Override
    public void onClickDirectoPresicionItem(NotaCircularUi notaCircularUi) {
        try {
            double notaActual = Double.parseDouble(notaCircularUi.getContenido());
            if(view!=null)view.onSuccessNota(notaAnterior,notaActual,valorTipoNotaId, rubroEvaluacionId);
        }catch (Exception e){
            showMessage("Error al transformar la nota");
            e.printStackTrace();
        }

    }

    private void getValorTipoNotaPresicion(String rubroEvaluacionId, String valorTipoNotaId, final String color){
        handler.execute(getValorTipoNotaPresicion, new GetValorTipoNotaPresicion.RequestValues(rubroEvaluacionId,valorTipoNotaId, color),
                new UseCase.UseCaseCallback<GetValorTipoNotaPresicion.ResponseValue>() {
                    @Override
                    public void onSuccess(GetValorTipoNotaPresicion.ResponseValue response) {
                        valorTipoNotaPrecisionUi = response.getValorTipoNotaPrecisionUi();
                       iniciarVista(color);
                    }

                    @Override
                    public void onError() {
                        showMessage("Error obtener valores de precisión");
                    }
                });
    }

    private void iniciarVista(String color) {
        if(valorTipoNotaPrecisionUi==null)return;
         setRubroEvaluacion(valorTipoNotaPrecisionUi);
         setValorNotaAsignado(notaAnterior, color);
         setColorFondo(color);
         switch (valorTipoNotaPrecisionUi.getTipo()){
             case SELECTOR_ICONOS:
                 setImagenNota(valorTipoNotaPrecisionUi);
                 break;
             case SELECTOR_VALORES:
                 setSelectorNota(valorTipoNotaPrecisionUi);
                 break;
         }
         setDescripcionNota(valorTipoNotaPrecisionUi);
         setRangoNota(valorTipoNotaPrecisionUi);
         //setCalValorNota(valorTipoNotaPrecisionUi);
         if(valorTipoNotaPrecisionUi.getNotaCircularUiList()!=null)setListItems(valorTipoNotaPrecisionUi.getNotaCircularUiList());
    }

    private void setColorFondo(String color) {
        if(view!=null)view.setColorFondo(color);
    }

    private void setValorNotaAsignado(Double notaActual, String color){
        if(view!=null)view.setValorNotaAsignado(String.format(Locale.US,"%.1f", notaActual),color);
    }

    private void setImagenNota(ValorTipoNotaPrecisionUi valorNotaAsignado){
        if(view!=null)view.setImagenNota(valorNotaAsignado.getIcono());
    }

    private void setSelectorNota(ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi){
        if(view!=null)view.setSelectorNota(valorTipoNotaPrecisionUi.getAlias());
    }

    private void setDescripcionNota(ValorTipoNotaPrecisionUi valorNotaAsignado){
        if(view!=null)view.setDescripcionNota(valorNotaAsignado.getDescripcion());
    }

    private void setRangoNota(ValorTipoNotaPrecisionUi valorNotaAsignado){
        StringBuilder rango =  new StringBuilder();

        if(valorNotaAsignado.isIncluidoLSuperior()){
            rango.append("[ ");
        }else {
            rango.append("< ");
        }
        rango.append(String.format(Locale.US,"%.1f", valorNotaAsignado.getLimiteSuperior()));
        rango.append(" - ");
        rango.append(String.format(Locale.US,"%.1f", valorNotaAsignado.getLimiteInferior()));
        if(valorNotaAsignado.isIncluidoLInferior()){
            rango.append(" ]");
        }else {
            rango.append(" >");
        }
        if(view!=null)view.setRangoNota(rango.toString());
    }

    private void setCalValorNota(String notaText){
        if(view!=null)view.setCalValorNota(notaText);
    }

    private void setRubroEvaluacion(ValorTipoNotaPrecisionUi valorNotaAsignado){
        if(view!=null)view.setRubroEvaluacion(valorNotaAsignado.getRubroNombre());
    }

    private void setListItems(List<NotaCircularUi> listItems){
        if(view!=null)view.setListItems(listItems);
    }

    private void convertirTipoNotaTeclado(final String cantidad, TecladoNumerico tecladoNumerico , ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi){
        handler.execute(convertirTipoNotaTeclado, new ConvertirTipoNotaTeclado.RequestValues(cantidad,
                        tecladoNumerico,
                        valorTipoNotaPrecisionUi.getLimiteSuperior(),
                        valorTipoNotaPrecisionUi.getLimiteInferior(),
                        valorTipoNotaPrecisionUi.isIncluidoLInferior(),
                        valorTipoNotaPrecisionUi.isIncluidoLSuperior()),
                new UseCase.UseCaseCallback<ConvertirTipoNotaTeclado.ResponseValue>() {
                    @Override
                    public void onSuccess(ConvertirTipoNotaTeclado.ResponseValue response) {
                        notaTeclado = response.getCantidad();
                        notaText = response.getCantidadTexto();
                        setCalValorNota(notaText);

                    }

                    @Override
                    public void onError() {
                        showMessage("Nota errónea");
                    }
                });
    }

    private void validarTipoNotaTeclado(final double cantidad, ValorTipoNotaPrecisionUi valorTipoNotaPrecisionUi){
        handler.execute(validarTipoNotaTeclado, new ValidarTipoNotaTeclado.RequestValues(cantidad,
                        valorTipoNotaPrecisionUi.getLimiteSuperior(),
                        valorTipoNotaPrecisionUi.getLimiteInferior(),
                        valorTipoNotaPrecisionUi.isIncluidoLInferior(),
                        valorTipoNotaPrecisionUi.isIncluidoLSuperior()),
                new UseCase.UseCaseCallback<ValidarTipoNotaTeclado.ResponseValue>() {
                    @Override
                    public void onSuccess(ValidarTipoNotaTeclado.ResponseValue response) {
                        if(response.isSuccess()){
                            if(view!=null)view.onSuccessNota(notaAnterior, cantidad,valorTipoNotaId, rubroEvaluacionId);
                        }else {
                            showMessage(response.getMesage());
                        }

                    }

                    @Override
                    public void onError() {
                        showMessage("Error al validar nota");
                    }
                });
    }

}
