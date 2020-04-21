package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.tipoNotaLista;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;

/**
 * Created by CCIE on 07/05/2018.
 */

public class TipoNotaPresenterImpl extends BasePresenterImpl<TipoNotaView> implements TipoNotaPresenter {

    private int programaEducativoId = 0;

    public TipoNotaPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    String titulo;

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.titulo = extras.getString(TipoNotaListActivity.ACTIVITYTITLE);
    }



    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSelectedTipoNota(String tipoNotaId, String nombre) {
        if (view == null) return;
        view.onSucces(tipoNotaId);
    }
}
