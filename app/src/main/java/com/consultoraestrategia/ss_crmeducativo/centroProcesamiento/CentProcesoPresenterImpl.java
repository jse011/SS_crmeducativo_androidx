package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento;

import android.content.res.Resources;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;

public class CentProcesoPresenterImpl extends BasePresenterImpl<CentProcesoView>  implements CentProcesoPresenter{
    public CentProcesoPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    protected String getTag() {
        return "CentProcesoPresenterImpl";
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
