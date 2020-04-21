package com.consultoraestrategia.ss_crmeducativo.comportamiento.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportamientoList;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.ui.ComportamientoView;

import org.xml.sax.Parser;

import java.util.List;

public class ComportamientoPresenterImpl extends BaseFragmentPresenterImpl<ComportamientoView> implements ComportamientoPresenter {

    String TAG = ComportamientoPresenterImpl.class.getSimpleName();
    GetComportamientoList getComportamientoList;
    int programaEducativoId;
    int cargaAcademicaId;
    int cargaCursoId;
    int docenteId;
    int cursoId;
    int calendarioPeriodoId;
    int georeferenciaId;
    int entidadId;
    private AlumnoUi alumnoUiseleted;
    private List<AlumnoUi> alumnoUiList;
    private boolean initFragment = true;

    CRMBundle crmBundle;

    public ComportamientoPresenterImpl(UseCaseHandler handler, Resources res, GetComportamientoList getComportamientoList) {
        super(handler, res);
        this.getComportamientoList = getComportamientoList;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void getListC() {

        handler.execute(getComportamientoList, new GetComportamientoList.RequestValues(programaEducativoId, cargaAcademicaId, cargaCursoId, docenteId, calendarioPeriodoId, georeferenciaId, entidadId), new UseCase.UseCaseCallback<GetComportamientoList.ResponseValue>() {
            @Override
            public void onSuccess(GetComportamientoList.ResponseValue response) {
                Log.d(TAG, "onSuccess " + response.getAlumnoUiList().size());
                alumnoUiList = response.getAlumnoUiList();
                showList();
            }

            @Override
            public void onError() {

            }
        });

    }

    private void showList() {
        Log.d(TAG, " showList ");
        hideProgress();
        if (alumnoUiList.size() > 0) {
            if (view != null) view.hideEmptyText();
        } else {
            if (view != null) view.showEmptyText();
        }
        if (view != null) view.setComportamientos(alumnoUiList);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        crmBundle = new CRMBundle(extras);
        this.programaEducativoId = crmBundle.getProgramaEducativoId();
        this.cargaAcademicaId = crmBundle.getCargaAcademicaId();
        this.cargaCursoId = crmBundle.getCargaCursoId();
        this.docenteId = crmBundle.getEmpleadoId();
        this.calendarioPeriodoId = crmBundle.getCalendarioPeriodoId();
        this.georeferenciaId = crmBundle.getGeoreferenciaId();
        this.entidadId = crmBundle.getEntidadId();

        Log.d(TAG, " setExtras ");
        if(view!=null)view.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view!=null)view.hideProgress();
                getListC();
            }
        }, 1500);

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
    public void onResumeFragment(String calendarioPeriodoId) {
        Log.d(TAG, "onResumeFragment");
        this.calendarioPeriodoId = Integer.parseInt(calendarioPeriodoId);
        crmBundle.setCalendarioPeriodoId(Integer.parseInt(calendarioPeriodoId));
        getListC();

    }

    @Override
    public void setselectedAlumno(AlumnoUi alumnoUiselected) {
        this.alumnoUiseleted = alumnoUiselected;
        if (view != null) view.lauchActivity(crmBundle, alumnoUiseleted.getId());
    }

    @Override
    public void onFabClicked() {
        if (view != null) view.lauchDialogCreate(crmBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!initFragment){
            getListC();
        }
        initFragment = false;
    }


}
