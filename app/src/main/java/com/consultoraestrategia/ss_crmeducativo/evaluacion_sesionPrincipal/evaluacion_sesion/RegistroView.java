package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 5/10/2017.
 */

public interface RegistroView extends BaseView<RegistroPresenter> {

    void moverButtonfloat(float posicionX, float posicionY);

    void showEvaluacion(List<AlumnosEvaluacionUi> evaluados, List<NotaUi> notaUis, RubroEvaluacionUi rubroEvaluacionUi, String titulo, String indicador, boolean disabledEval);

    void showProgress();

    void hideProgess();

    void addRubro(RubroEvaluacionUi rubro);

    void updateRubro(RubroEvaluacionUi rubro);

    void deleteRubro(RubroEvaluacionUi rubro);

    void setRubro(List<RubroEvaluacionUi> rubros);

    void showDialogAgregarRubro(int sesionAprendizajeId, int indicadorId);

    void showDialogInidicador(List<IndicadorUi> indicadorUis);

    void hideDialogInidicador();

    void changeNotaEvaluacion();

    void showContentVacio(int message);

    void hideContentVacio();

    void showDilogEvaluacionSelectorNotas(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis);

    void showDilogEvaluacionTecladoNumerico(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosList);

    void changeTable();

    void onShowDialogInfoRubro(String rubroEvaluacionId);

    void onShowInfoUsuario(AlumnosEvaluacionUi item);

    void onShowPresionEvaluacion(double nota, String rubroEvaluacionId, String valorTipoNotaId, String color, boolean validar);

    void showListarRubros(List<NotaUi> notaUiList, RubroEvaluacionUi.TipoNota tipo);

    void showFooter();

    void hideFooter();

    void changeTableAvanzado();

    void changeTableSimple();

    void cleanAllList();

    void updateAlumnoEvaluacion(List<AlumnosEvaluacionUi> alumnosEvaluacionUis);

    void showTutorial();

    void showInfoComentario(String evaluacionId);

    void notiftyDataBaseChange();

    void showBtnFooter();

    void hideBtnFooter();

    void changeSwiteOn();

    void changeSwiteOff();

    void changeEyeSimple();

    void changerEyeFocus();

    void showBtnClean(boolean b);
}
