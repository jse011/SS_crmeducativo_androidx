package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.ui.JustificacionView;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetTipoJustificacion;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;

import java.util.ArrayList;
import java.util.List;

public class JustificacionPresenteImpl extends BaseFragmentPresenterImpl<JustificacionView> implements JustificacionPresenter{

    String TAG= JustificacionPresenteImpl.class.getSimpleName();


    public JustificacionPresenteImpl( UseCaseHandler handler, Resources res) {
        super(handler, res);
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
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
    }

    @Override
    public void onViewCreated() {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated();

    }

    @Override
    public void onCreateView() {
        Log.d(TAG, "onCreateView");
        super.onCreateView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
