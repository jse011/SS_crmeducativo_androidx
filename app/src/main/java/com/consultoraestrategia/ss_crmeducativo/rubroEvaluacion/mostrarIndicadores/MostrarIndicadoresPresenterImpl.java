package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.useCase.MostrarIndicadores;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.view.MostrarIndicadoresView;

/**
 * Created by kike on 10/05/2018.
 */

public class MostrarIndicadoresPresenterImpl implements MostrarIndicadoresPresenter {

    MostrarIndicadoresView view;
    UseCaseHandler handler;
    MostrarIndicadores mostrarIndicadores;

    public MostrarIndicadoresPresenterImpl(UseCaseHandler handler,MostrarIndicadores mostrarIndicadores) {
        this.handler = handler;
        this.mostrarIndicadores = mostrarIndicadores;
    }

    @Override
    public void attachView(MostrarIndicadoresView view) {
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

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onExtras(Bundle bundle) {

    }
}
