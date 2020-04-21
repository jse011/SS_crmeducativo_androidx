package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;

import java.util.List;

/**
 * Created by SCIEV on 5/10/2017.
 */

public interface RegistroView extends BaseView<RegistroPresenter> {
    void moverButtonfloat(float posicionX, float posicionY);
    void showEvaluacion(List<Object> evaluados, List<NotaUi> notaUis, RubroEvaluacionUi rubroEvaluacionUi, String indicador, boolean disabledEval);
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
    void showTeamList(int cargaCursoId, int cursoId, int rubroEvaluacionId) ;
    void showTeamList(int cargaCursoId, int cursoId, String rubroEvaluacionId, int idcargaAcademica) ;
    void showContentVacio(int message);
    void hideContentVacio();
    void showDilogEvaluacionTecladoNumerico(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis);
    void showDilogEvaluacionTecladoNumerico(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<GrupoEvaluacionUi> gruposEvaluacion);
    void changeTable();
    void onShowInfoRubro(String rubroEvaluacionProcesoId);
    void showDilogInfoUsuario(AlumnosEvaluacionUi alumnosEvaluacionUi);
    void onShowPresionEvaluacion(double nota, String rubroEvaluacionId, String valorTipoNotaId, String color, boolean validar);
    void showListarRubros(List<NotaUi> notaUiList, RubroEvaluacionUi.TipoNota tipo);
    void showFooter();
    void hideFooter();
    void changeTableAvanzado();
    void changeTableSimple();
    void showFrameGroupsList();
    void hideFrameGroupsList();

    void clearAllList();

    void showInfoComentario(String evaluacionId);

    void notifyChangeDataBase();

    void showDilogEvaluacionSelectorNotas(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUiSelected, SelectorNumericoUi selectorNumericoUi, List<GrupoEvaluacionUi> gruposEvaluacion);

    void showDilogEvaluacionSelectorNotas(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUiSelected, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis);

    void removeRowRange(GrupoEvaluacionUi grupoEvaluacionUi, List<AlumnosEvaluacionUi> columnHeaders, List<List<NotaUi>> cellList);

    void addRowRange(GrupoEvaluacionUi grupoEvaluacionUi, List<AlumnosEvaluacionUi> columnHeaders, List<List<NotaUi>> cellList);

    void changeSwiteOn();

    void changeSwiteOff();

    void changeEyeSimple();

    void changerEyeFocus();

    void showBtnClean(boolean b);
}
