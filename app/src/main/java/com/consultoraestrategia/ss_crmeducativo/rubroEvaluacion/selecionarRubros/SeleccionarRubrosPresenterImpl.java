package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetCamposAccionRubro;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.ValidarRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jse on 01/08/2018.
 */

public class SeleccionarRubrosPresenterImpl extends BasePresenterImpl<SeleccionarRubrosView> implements SeleccionarRubrosPresenter {
    private CapacidadUi capacidadUi;
    private RubroProcesoUi rubroProcesoUi;
    private List<RubroProcesoUi> rubroProcesoUiList;
    private GetCamposAccionRubro getCamposAccionRubro;
    private ValidarRubroProceso validarRubroProceso;
    private List<IndicadoresCamposAccionUi> indicadoresCamposAccionUisList;
    private boolean sesion;
    String TAG= SeleccionarRubrosPresenterImpl.class.getSimpleName();

    public SeleccionarRubrosPresenterImpl(UseCaseHandler handler, Resources res,GetCamposAccionRubro getCamposAccionRubro, ValidarRubroProceso validarRubroProceso) {
        super(handler, res);
        this.getCamposAccionRubro = getCamposAccionRubro;
        this.validarRubroProceso=validarRubroProceso;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return SeleccionarRubrosPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onQueryTextChange(String newText) {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(TextUtils.isEmpty(SeleccionarRubrosActivity.SESION)){
            if(view!=null)view.closeActivity();
        }else {
            if(view!=null)view.removerSesion();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(capacidadUi == null){
            SeleccionarRubrosListWrapper wrapper = Parcels.unwrap(extras.getParcelable(SeleccionarRubrosActivity.EXTRA_CAPACIDAD));
            this.capacidadUi = wrapper.getCapacidadUi();
        }
        if(rubroProcesoUiList==null)showRubros();
    }

    private void showRubros() {
        if(view!=null)view.setTituloCapacidad(capacidadUi.getTitle());
        if(capacidadUi.getCompetenciaUi()!=null)if(view!=null)view.setTituloCompetencia(capacidadUi.getCompetenciaUi().getTitulo());
        try {
            List<RubroProcesoUi> rubroProcesoUiList = new ArrayList<>();

            for (RubroProcesoUi rubroProcesoUi : capacidadUi.getRubroProcesoUis()){
                if(rubroProcesoUi.getTipoFormulaId()==0){
                    rubroProcesoUiList.add(rubroProcesoUi);
                }
            }
            this.rubroProcesoUiList = rubroProcesoUiList;

            getCamposAccionRubro();
        }catch (Exception e){
            e.printStackTrace();
            Log.d(getTag(),"Error al Listar");
        }

    }

    private void getCamposAccionRubro() {
        handler.execute(getCamposAccionRubro, new GetCamposAccionRubro.RequestValues(rubroProcesoUiList),
                new UseCase.UseCaseCallback<GetCamposAccionRubro.ResponseValues>() {
                    @Override
                    public void onSuccess(GetCamposAccionRubro.ResponseValues response) {
                        indicadoresCamposAccionUisList = response.getIndicadoresCamposAccionUis();
                        if(view!=null)view.showListaRubros(capacidadUi,rubroProcesoUiList,0);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi) {
        Log.d(getTag(), "onClickRubrosAsociados : " + rubroProcesoUi.isCheck());
        boolean validar=true;
        if(rubroProcesoUi.getFormEvaluacion().equals(RubroProcesoUi.FormEvaluacion.GRUPAL)){
            validar= validarRubroSelected(rubroProcesoUi, capacidadUi);
        }
        if(validar){
            this.rubroProcesoUi = rubroProcesoUi;
            if (rubroProcesoUi.isCheck()) {
                rubroProcesoUi.setCheck(false);
            } else {
                rubroProcesoUi.setCheck(true);
            }
            changeNombreToolbar(contarRubros());

            if(contarRubros()==rubroProcesoUiList.size()){
                if(view!=null)view.setItemtoolbarCheck(false);
            }else {
                if(view!=null)view.setItemtoolbarCheck(true);
            }
        }else {
            Log.d(TAG, "no tiene evaluaciones");
            rubroProcesoUi.setCheck(false);
            if(view!=null){
                view.updateRubroValidado(rubroProcesoUi);
                view.showMessage(res.getString(R.string.msg_validar_rubro));}
        }

    }

    private boolean validarRubroSelected(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi) {
        ValidarRubroProceso.Response response= validarRubroProceso.execute(new ValidarRubroProceso.Request(rubroProcesoUi.getKey()));
        Log.d(TAG, "response " + response.isSuccess());
        return response.isSuccess();
    }


    private int contarRubros(){
        int cantidad = 0;
        for (RubroProcesoUi itemrubroProcesoUi: rubroProcesoUiList){
            if(itemrubroProcesoUi.isCheck())cantidad++;
        }
        return cantidad;
    }

    private void changeNombreToolbar(int cantidad){
        String nombre = cantidad + " rubros seleccionados";
        if(view!=null)view.changeNombreToolbar(nombre);
    }

    @Override
    public void onClickAceptarSelecion() {
        if(contarRubros()< 1){
            if(view!=null)view.showMessage(res.getString(R.string.msg_seleccion_rubro_vacio));
            return;
        }
        if(view!=null)view.succesSeleccion(capacidadUi);
    }

    @Override
    public void onClickSelecionAll() {
        for (RubroProcesoUi itemrubroProcesoUi: rubroProcesoUiList)itemrubroProcesoUi.setCheck(true);
        Log.d(getTag(), "onClickAceptarSelecion" );
        if(view!=null)view.changeListRubros();
        int cantidad  = rubroProcesoUiList.size();
        changeNombreToolbar(cantidad);
    }

    @Override
    public void onClickSelecionOff() {
        Log.d(getTag(), "onClickSelecionOff" );
        for (RubroProcesoUi itemrubroProcesoUi: rubroProcesoUiList)itemrubroProcesoUi.setCheck(false);
        if(view!=null)view.changeListRubros();
        changeNombreToolbar(0);
    }

}
