package com.consultoraestrategia.ss_crmeducativo.tipoNota;

import android.content.res.Resources;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.domainUseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.List;


/**
 * Created by kike on 15/02/2018.
 */

public class TipoNotaPresenterImpl extends BasePresenterImpl<TipoNotaView> implements TipoNotaPresenter {

    public String TAG = TipoNotaPresenterImpl.class.getSimpleName();
    private GetTipoNotaList getTipoNotaList;

    public TipoNotaPresenterImpl(UseCaseHandler handler, Resources res, GetTipoNotaList getTipoNotaList) {
        super(handler, res);
        this.getTipoNotaList = getTipoNotaList;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        String nombreAcceso = extras.getString("nombreAcceso");
        this.idAcceso = extras.getInt("idAcceso", 0);
        this.idPersona = extras.getInt("idPersona", 0);
        this.idUsuario = extras.getInt("idUsuario", 0);
        //Log.d(TAG, "onCreate : " + nombreAcceso + " / " + idAcceso + " / " + idPersona + " / " + idUsuario);
    }

    private void getTipoNotaList(int usuarioCreadorId) {
        Log.d(TAG, "getTipoNOtaList");
        handler.execute(getTipoNotaList,
                new GetTipoNotaList.RequestValues(usuarioCreadorId),
                new UseCase.UseCaseCallback<GetTipoNotaList.ResponseValues>() {
                    @Override
                    public void onSuccess(GetTipoNotaList.ResponseValues response) {
                        Log.d(TAG, "onSuccess : " + response.getTipoNotaUiList().size());
                       if(view!=null) view.mostrarLista(response.getTipoNotaUiList());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onStart() {
        Log.d(TAG, "OnStart");
    }

    int idAcceso, idPersona, idUsuario;

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
            getTipoNotaList(idUsuario);

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressedImpl");
        if (view != null) {
            view.regresoMainActivity(idUsuario);
        }
    }


 /*   @Override
    public void onSingleItemSelected(String singleItem) {

    }*/

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void showListTipoNota(List<TipoNotaUi> tipoNotaUiList) {

    }

    @Override
    public void showActivityCrearTipoNota() {
      if(view!=null)view.extras(idUsuario);
    }
}
