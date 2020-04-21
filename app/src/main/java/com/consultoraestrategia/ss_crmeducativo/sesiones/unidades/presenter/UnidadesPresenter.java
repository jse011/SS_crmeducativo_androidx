package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.presenter;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.FragmentUnidadesView;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;

public interface UnidadesPresenter extends BasePresenter<FragmentUnidadesView> {
    void setExtras(Bundle bundle);
    void onResumeFragment(String idCalendarioPeriodo);
    void saveSesionAprendizaje(SesionAprendizajeUi sesionAprendizajeUi);

    void onClickSesion(SesionAprendizajeUi sesionAprendizaje);

    void onViewCreated();

    void startUnidadDetalle(UnidadAprendizajeUi unidadId);

    void onClickVerMas(UnidadAprendizajeUi unidadAprendizaje);

    void onConfigurationChanged();
}
