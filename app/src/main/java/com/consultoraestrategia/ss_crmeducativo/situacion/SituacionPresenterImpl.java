package com.consultoraestrategia.ss_crmeducativo.situacion;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.situacion.ui.SituacionFragment;
import com.consultoraestrategia.ss_crmeducativo.situacion.usecase.GetSituacionListUI;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public class SituacionPresenterImpl implements SituacionPresenter {
    private static final String TAG = SituacionPresenterImpl.class.getSimpleName();
    private SituacionView view;
    private UseCaseHandler handler;
    private GetSituacionListUI getSituacionListUI;
    private int entidadId;
    private int unidadAprendizajeId;
    private int cargaCursoId;
    public SituacionPresenterImpl(UseCaseHandler handler, GetSituacionListUI getSituacionListUI) {
        this.handler = handler;
        this.getSituacionListUI = getSituacionListUI;

    }



    @Override
    public void attachView(SituacionView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
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
    public void onDestroy() {

        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed   ");
    }


    public void getSituaionUIList() {
        GetSituacionListUI.ResponseValue response= getSituacionListUI.execute(new GetSituacionListUI.RequestValues(unidadAprendizajeId, cargaCursoId,entidadId));
        if(!response.getList().isEmpty()){
            Log.d(TAG, "getSituaionUIList"+ response.getList().size());
            showSituaiconList(response);

        }else {if(view!=null)view.showMessage();}

    }

    @Override
    public void onResumeFragment() {
        getSituaionUIList();
    }

    @Override
    public void setExtras(Bundle bundle) {
       entidadId= bundle.getInt(SituacionFragment.ID_ENTIDAD, 0);
       unidadAprendizajeId= bundle.getInt(SituacionFragment.ID_UNIDAD_APRENDIZAJE,0);
       cargaCursoId= bundle.getInt(SituacionFragment.ID_CARGA_CURSO,0);
    }

    @Override
    public void onViewCreated() {
        if(view!=null)view.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSituaionUIList();
                if(view!=null)view.hideProgress();
            }
        },600);
    }

    private void showSituaiconList(GetSituacionListUI.ResponseValue response) {
        if (view != null) view.showSituacionUIList(response.getList());
    }

    private void showError(String msaje) {
        if (view != null) view.showMsjLong(msaje);
    }


}
