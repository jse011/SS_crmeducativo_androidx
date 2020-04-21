package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.dialogAsistencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaBundle;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GenerarAsistencia;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;

public class AsistenciaDialogPresenterImpl extends BaseFragmentPresenterImpl<AsistenciaDialogView> implements AsistenciaDialogPresenter {


    private GenerarAsistencia generarAsistencia;
    private AsistenciaUi asistenciaUi;

    public AsistenciaDialogPresenterImpl(UseCaseHandler handler, Resources res, GenerarAsistencia generarAsistencia) {
        super(handler, res);
        this.generarAsistencia = generarAsistencia;
    }

    @Override
    protected String getTag() {
        return AsistenciaDialogPresenterImpl.class.getSimpleName();
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
        AsistenciaBundle asistenciaBundle = AsistenciaBundle.clone(extras);
        asistenciaUi = new AsistenciaUi();
        if (asistenciaBundle != null) {
            asistenciaUi = asistenciaBundle.getAsistenciaUi();
        } else {
            showMessage("Error en la asistencia");
            if (view != null) view.close();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCLickGenerarAsistencia() {
        handler.execute(generarAsistencia,
                new GenerarAsistencia.RequestValues(asistenciaUi),
                new UseCase.UseCaseCallback<GenerarAsistencia.ResponseValue>() {
                    @Override
                    public void onSuccess(GenerarAsistencia.ResponseValue response) {
                        if (view != null) view.onClickAsistencia(response.getAsistenciaUi());
                        if (view != null) view.close();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}
