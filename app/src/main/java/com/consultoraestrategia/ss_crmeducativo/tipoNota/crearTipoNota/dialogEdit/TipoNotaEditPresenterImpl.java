package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

/**
 * Created by kike on 04/04/2018.
 */

public class TipoNotaEditPresenterImpl implements TipoNotaEditPresenter {

    private static String TIPO_NOTA_EDIT_TAG = TipoNotaEditPresenterImpl.class.getSimpleName();

    private UseCaseHandler handler;
    private TipoNotaEditView view;

    public TipoNotaEditPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void attachView(BaseView view) {
        this.view = (TipoNotaEditView) view;
    }

    @Override
    public void onCreate() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onDestroy");
        view = null;
    }

    @Override
    public void onBackPressed() {
        Log.d(TIPO_NOTA_EDIT_TAG, "onBackPressed");
    }

    @Override
    public void onCheckBox(boolean status) {
        if (view != null) {
            if (status) {
                view.mostrarCajatexto();
                Log.d(TIPO_NOTA_EDIT_TAG, "onCheckBox  : " + status);
            } else {
                view.ocultarCajaTexto();
                Log.d(TIPO_NOTA_EDIT_TAG, "onCheckBox  : " + status);
            }
        }
    }

    @Override
    public void onbtnAceptar(ValoresUi valoresUi) {
        if (view != null) {
            if (valoresUi.getTitulo().equals("") && valoresUi.getDescripcion().equals("")) {
                view.mostrarMensaje("Llenes los campos");
            } else {
                view.onAceptarSuccessDialog(valoresUi);
            }
        }
    }

}
