package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.ComportamientoAlumnoActivity;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.ComportamientoAlumnoview;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetCurso;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

import java.util.List;

public class ComportamientoAlumnoPresenterImpl extends BasePresenterImpl<ComportamientoAlumnoview> implements ComportamientoAlumnoPresenter {

    private String TAG= ComportamientoAlumnoPresenterImpl.class.getSimpleName();
    GetCurso getCurso;
    int cargaCursoId;
    int alumnoId;

    public ComportamientoAlumnoPresenterImpl(UseCaseHandler handler, Resources res, GetCurso getCurso) {
        super(handler, res);
        this.getCurso=getCurso;
    }

    @Override
    protected String getTag() {
        return null;
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
        CRMBundle crmBundle= new CRMBundle(extras);
        this.cargaCursoId= crmBundle.getCargaCursoId();
        this.alumnoId= extras.getInt("alumnoId");

    }
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate " );
        super.onCreate();
        init();

    }

    private void init() {
        handler.execute(getCurso, new GetCurso.RequestValues(cargaCursoId, alumnoId), new UseCase.UseCaseCallback<GetCurso.ResponseValue>() {
            @Override
            public void onSuccess(GetCurso.ResponseValue response) {
                Log.d(TAG, "onSuccess " +response.getCursoUi().getNombre());
                if(view!=null)view.setDatos(response.getCursoUi());
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
