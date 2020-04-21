package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase.GetAniosAcademicosUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase.GetCuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.ui.CuentaCorrienteActivity;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.main.ui.MainActivity;

import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class CuentaCorrientePresenterImpl implements CuentaCorrientePresenter {
    private static final String TAG = CuentaCorrientePresenterImpl.class.getSimpleName();

    private CuentaCorrienteView view;

    private UseCaseHandler useCaseHandler;
    private GetCuentaCoUI getCuentaCoUI;
    private GetAniosAcademicosUI getAniosAcademicosUI;
    private GetPersona getPersona;

    public CuentaCorrientePresenterImpl(UseCaseHandler useCaseHandler, GetPersona getPersona, GetCuentaCoUI getCuentaCoUI, GetAniosAcademicosUI getAniosAcademicosUI) {
        this.useCaseHandler = useCaseHandler;
        this.getPersona = getPersona;
        this.getCuentaCoUI = getCuentaCoUI;
        this.getAniosAcademicosUI = getAniosAcademicosUI;
    }

    @Override
    public void attachView(CuentaCorrienteView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        showProgress();
        setImgProfile();
        getPersona();

        getAniosAcademicosUI();
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
        view = null;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        if (view != null) {

        }
    }

    private void setImgProfile() {
        view.setUrlImgProfile(urlImgProfile);
    }

    private void getPersona() {
        useCaseHandler.execute(getPersona,
                new GetPersona.RequestValues(idAlumno),
                new UseCase.UseCaseCallback<GetPersona.ResponseValue>() {
                    @Override
                    public void onSuccess(GetPersona.ResponseValue response) {
                        showPersona(response.getPersona());

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void showPersona(Persona persona) {
        if (view!=null)view.setNombreAlumno(persona);
    }

    private void showProgress() {
        view.showProgress();
    }

    private void hideProgress() {
        view.hideProgress();
    }

    private void getAniosAcademicosUI() {
        useCaseHandler.execute(getAniosAcademicosUI,
                new GetAniosAcademicosUI.RequestValues(idAlumno),
                new UseCase.UseCaseCallback<GetAniosAcademicosUI.ResponseValue>() {
                    @Override
                    public void onSuccess(GetAniosAcademicosUI.ResponseValue response) {
                        setAniosAcademicos(response.getAnioAcademicoList());
                        hideProgress();
                    }

                    @Override
                    public void onError() {
                        hideProgress();
                    }
                });
    }

    private void setAniosAcademicos(List<String> aniosAcademicosList) {
        if (view!=null)view.setAniosAcademicosList(aniosAcademicosList);
    }

    private void getGetCuentaCoUI(String anioSelected) {
        useCaseHandler.execute(getCuentaCoUI,
                new GetCuentaCoUI.RequestValues(idAlumno, anioSelected),

                new UseCase.UseCaseCallback<GetCuentaCoUI.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCuentaCoUI.ResponseValue response) {
                        setRecyclerList(response.getCuentaCoUIList());
                        setRestante(response.getRestante());
                    }

                    @Override
                    public void onError() {
                    }
                });


    }

    private void setRestante(double restante) {
        if (restante > 0) {
            view.showMessageDeuda("Usted tiene una deuda de : ", restante);
        } else {
            view.hideMessageDeuda();
        }
    }

    private void setRecyclerList(List<CuentaCoUI> cuentaCoUIList) {
        if (view!=null)view.setCuentaCorrienteList(cuentaCoUIList);
    }

    private int idAlumno;
    private String urlImgProfile;

    @Override
    public void setExtras(Bundle extras) {
        idAlumno = extras.getInt(CuentaCorrienteActivity.EXTRA_ID_ALUMNO);
        urlImgProfile = extras.getString(CuentaCorrienteActivity.URL_IMG_PROFILE);
        Log.d(TAG, "extras : " + extras);
    }

    @Override
    public void findCuotas(String anioSelected) {
        getGetCuentaCoUI(anioSelected);
    }
}
