package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 11/04/2018.
 */

public class FiltradoPresenterImpl implements FiltradoPresenter {

    private static String FILTRADO_TAG = FiltradoPresenterImpl.class.getSimpleName();

    UseCaseHandler handler;
    FiltradoView view;

    public FiltradoPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void attachView(FiltradoView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(FILTRADO_TAG, "onCreate");
    }

    List<FiltradoUi> filtradoUiList;



    private void mostrarListaFiltrado(List<FiltradoUi> filtradoUiList) {
        Log.d(FILTRADO_TAG, "mostrarListaFiltrado : " + filtradoUiList.size());
        if (view != null) {
            view.mostrarFiltro(filtradoUiList);
        }
    }

    @Override
    public void onStart() {
        Log.d(FILTRADO_TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(FILTRADO_TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(FILTRADO_TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(FILTRADO_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(FILTRADO_TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(FILTRADO_TAG, "onBackPressed");
    }

    FiltradoUi filtradoUi;

    @Override
    public void filtradoCheckItemListener(FiltradoUi filtradoUi) {
        this.filtradoUi = filtradoUi;
    }

    @Override
    public void onbtnAceptar() {
        if (filtradoUi != null) {
            validarCheckItems(filtradoUi, filtradoUiList);
        }
    }

    private void validarCheckItems(FiltradoUi filtradoUi, List<FiltradoUi> filtradoUiList) {
        int contarCheckTrue = 0;
        for (int i = 0; i < filtradoUiList.size(); i++) {
            FiltradoUi filtradoUi1 = filtradoUiList.get(i);
            if (filtradoUi1.isaBoolean()) {
                contarCheckTrue++;
                Log.d(FILTRADO_TAG, "contarCheckTrue : " + contarCheckTrue);
            }
        }
        if (contarCheckTrue == 1) {
            Log.d(FILTRADO_TAG, "validarCheckItems : " + contarCheckTrue);
            if (view != null) {
                view.onSuccess(filtradoUi);
            }
        } else {
            if (view != null) {
                view.mostrarMensaje("Seleccione 1 item");
            }
            Log.d(FILTRADO_TAG, "SELECCCIONAR1ITEM : " + contarCheckTrue);
        }

    }
}
