package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.view.TipoNotaListActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.view.TipoNotaListActivityView;

/**
 * Created by SCIEV on 16/04/2018.
 */

public class TipoNotaListActivityPresenterImpl extends BasePresenterImpl<TipoNotaListActivityView> implements TipoNotaListActivityPresenter {


    private String titulo;
    private String tipoNotaId;

    public TipoNotaListActivityPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
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
        this.titulo = extras.getString(TipoNotaListActivity.ACTIVITYTITLE);
    }

    @Override
    public void onSelectedTipoNota(String tipoNotaId, String nombre) {
        this.tipoNotaId =tipoNotaId;
        if(view != null)view.onSucces(tipoNotaId);
    }

    @Override
    public void onBtnCreateClicked() {
        //if(view != null)view.onSucces(tipoNotaId);
    }

    @Override
    protected String getTag() {
        return TipoNotaListActivityPresenterImpl.class.getSimpleName();
    }
}
