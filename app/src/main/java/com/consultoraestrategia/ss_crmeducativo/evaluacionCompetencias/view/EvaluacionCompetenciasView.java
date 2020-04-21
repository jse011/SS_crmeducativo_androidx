package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.view;


import android.app.AlertDialog;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.EvaluacionCompetenciasPresenter;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public interface EvaluacionCompetenciasView extends BaseView<EvaluacionCompetenciasPresenter> {

    void mostrarObjectLista(List<Object> objectList);

    void mostrarListaCompetencia(int size);

    void ocultaListaCompetencia();

    void mostrarMensaje(String mensaje);

    void ocultarMensaje();

    void actualizarCapacidad(Object o);

    //Modificar Object por el tipo de objeto CompetenciaUi(NO ESTOY SEGURO)
    void startEvalResultActivity(Object object);

    void changeFragmentRubros(int calendarioPeriodoId, boolean calendarioActivo);

    void showDialogWaitAncla(AlertDialog alertDialog);

    void hideAlerDialogwaitAncla(AlertDialog alertDialog);
    void setParametroDisenio(String color);

    void setAnchorCheck();

    void hideContent();

    void showContent();

    void simpleSyncIntenService(int programaEducativoId);
    void hideAncla();
    void showAncla();

    void showDialogPeriodoDesactivado(String string);
    void hideDialogPeriodoDesactivado();

    void hideCerrarCurso();
    void showCerrarCurso();

    void showFiltroDialog();

    void reloadActivity();
}
