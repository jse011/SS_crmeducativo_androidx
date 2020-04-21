package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui.CrearRubroCabeceraView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;

/**
 * Created by SCIEV on 28/02/2018.
 */

public class CrearRubroCabeceraPresenterImpl extends BasePresenterImpl<CrearRubroCabeceraView> {

    private CrearRubroCabeceraView view;

    public CrearRubroCabeceraPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return CrearRubroCabeceraPresenterImpl.class.getSimpleName();
    }

    @Override
    public void attachView(CrearRubroCabeceraView view) {
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
        view = null;
    }

    @Override
    public void onBackPressed() {
        if(view!=null)view.showFinalMessageAceptCancel("¿Seguro que quiere salir?", res.getString(com.consultoraestrategia.ss_crmeducativo.core2.R.string.msg_confirmacionTitlle));
    }

    @Override
    public void setExtras(Bundle extras) {

    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {
        if (view!=null)view.onAcceptButtom();
    }


}
