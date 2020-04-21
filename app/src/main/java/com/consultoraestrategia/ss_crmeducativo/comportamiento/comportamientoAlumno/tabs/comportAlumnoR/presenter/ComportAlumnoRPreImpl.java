package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.ui.ComportAlumnoRview;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportamientosDestinos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.UploadFile;

import java.util.List;

public class ComportAlumnoRPreImpl extends BaseFragmentPresenterImpl<ComportAlumnoRview> implements ComportAlumnoRPresenter {

    int docenteId;
    int alumnoId;
    List<ComportamientoUi>comportamientoUiList;
    GetComportamientosDestinos getComportamientosDestinos;
    String TAG= ComportAlumnoRPreImpl.class.getSimpleName();


    public ComportAlumnoRPreImpl(UseCaseHandler handler, Resources res, GetComportamientosDestinos getComportamientosDestinos) {
        super(handler, res);
        this.getComportamientosDestinos=getComportamientosDestinos;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle= new CRMBundle(extras);
        this.docenteId=crmBundle.getEmpleadoId();
        Log.d(TAG, "docenteId " +docenteId);
        this.alumnoId= extras.getInt("alumnoId");
        getComportamientos();
    }

    private void getComportamientos() {

    handler.execute(getComportamientosDestinos, new GetComportamientosDestinos.RequestValues(docenteId, alumnoId), new UseCase.UseCaseCallback<GetComportamientosDestinos.ResponseValue>() {
        @Override
        public void onSuccess(GetComportamientosDestinos.ResponseValue response) {
            Log.d(TAG, "onSuccess " +response.getComportamientoUiList().size());
            comportamientoUiList=response.getComportamientoUiList();
            showList();

        }

        @Override
        public void onError() {
            Log.d(TAG, "onError " );
        }
    });

    }

    private void showList() {
        if(view!=null)view.hideProgress();
        if(comportamientoUiList.size()>0)if(view!=null)view.showList(comportamientoUiList);
        else if(view!=null)view.showEmptyText();
    }

    @Override
    public void onResume() {
        super.onResume();
        getComportamientos();
    }
}
