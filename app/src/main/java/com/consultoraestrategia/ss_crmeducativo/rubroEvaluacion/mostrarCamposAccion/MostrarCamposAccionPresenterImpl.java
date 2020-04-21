package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.MostrarCamposAccion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.view.MostrarCamposAccionFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.view.MostrarCamposAccionView;

/**
 * Created by kike on 08/05/2018.
 */

public class MostrarCamposAccionPresenterImpl implements MostrarCamposAccionPresenter {

    public static final String TAG = MostrarCamposAccionPresenterImpl.class.getSimpleName();

    private UseCaseHandler handler;
    private MostrarCamposAccionView view;
    private MostrarCamposAccion mostrarCamposAccion;


    public MostrarCamposAccionPresenterImpl(UseCaseHandler handler, MostrarCamposAccion mostrarCamposAccion) {
        this.handler = handler;
        this.mostrarCamposAccion = mostrarCamposAccion;
    }

    @Override
    public void attachView(MostrarCamposAccionView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onExtras(Bundle bundle) {
        if (bundle == null) return;
        String keyRubroProceso = bundle.getString(MostrarCamposAccionFragment.RUBROPROCESO_KEY);
        //if(keyRubroProceso!=null && Utils.Em)
        iniciarUseCase(keyRubroProceso);
    }

    private void iniciarUseCase(String keyRubroProceso) {
        handler.execute(mostrarCamposAccion,
                new MostrarCamposAccion.RequestValues(keyRubroProceso),
                new UseCase.UseCaseCallback<MostrarCamposAccion.ResponseValues>() {
                    @Override
                    public void onSuccess(MostrarCamposAccion.ResponseValues response) {
                        if(view!=null)view.actualizarLista(response.getIndicadoresCamposAccionUis());
                       // Log.d(TAG,"Completo la Lista : " +response.getCamposAccionUiList().size());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG,"onError" );
                    }
                });
    }
}
