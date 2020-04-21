package com.consultoraestrategia.ss_crmeducativo.base.activity;

import android.content.res.Resources;
import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.core2.R;

/**
 * Created by @stevecampos on 15/01/2018.
 */

public class Presenter extends BasePresenterImpl<View> {

    public Presenter(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    public void attachView(View view) {
        super.attachView(view);
    }

    @Override
    protected String getTag() {
        return Presenter.class.getSimpleName();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (view != null) {
            view.changeBackground(R.color.md_yellow_700);
            view.showImportantMessage("Some important message!");
        }

    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        Log.d(getTag(), "onSingleItemSelected");
    }

    @Override
    public void onCLickAcceptButtom() {

    }


}
