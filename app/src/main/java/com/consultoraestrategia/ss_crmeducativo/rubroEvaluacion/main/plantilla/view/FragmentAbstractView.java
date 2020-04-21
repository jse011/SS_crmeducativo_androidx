package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.RubroProcesoWrapper;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroEvaluacionResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.FragmentAbstractPresenter;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public interface FragmentAbstractView extends BaseView<FragmentAbstractPresenter> {
    void showRubroEvaluacionProceso(List<Object> items);
    void showProgres();
    void hideProgres();
    void updateCompetencia(Object o);
    void addRubroProceso(Object o, RubroProcesoUi rubroProcesoUi);
    void updateRubroProceso(Object o, RubroProcesoUi rubroProcesoUi, int programaEducativoId);
    void showListaIndicadoressSesionAprendizaje(int sesionAprendizajeId, int nivel, int competencia);
    void showListaIndicadoressSilavoEvento(int sesionAprendizajeId, int nivel, int competencia);
    void hideListaIndicadores();
    void setupAdapter();
    void showCrearRubro(int sesionAprendizajeId, int silavoEventoId, int calendarioPeriodoId, int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds, int programaEducativoId, int cursoId);
    void showEvaluacionUnidimencionalSesion(int rubroEvaluacionId, String titulo,int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval);
    void showEvaluacionUnidimencionalSesion(String rubroEvaluacionId, String titulo,int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval, String rubroProcesoTipoNotaId, int entidadId, int georeferenciaId, int programaEducativoId);
    void showEvaluacionUnidimencionalGrupos(int rubroEvaluacionId, String titulo,int sesionAprendizajeId, int cargaCursoId, int cursoId,  boolean disabledEval);
    void showEvaluacionUnidimencionalGrupos(String rubroEvaluacionId, String titulo,int sesionAprendizajeId, int cargaCursoId, int cursoId,int idCargaAcademica, boolean disabledEval, String rubroEvaluacionTIpoNotaId);
    void listIndicadoresFragment(int id, int nivel, CapacidadUi capacidadUi, int calendarioPeriodoId);
    void onClickAceptarDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi);
    void initDialogTypeFormula(List<RubroProcesoUi> procesoUiList, CapacidadUi capacidadUi, int programaEducativoId, int cargaCursoId);
    void onClickAceptarDialogFormula(CapacidadUi capacidadUi, RubroProcesoUi rubroEvaluacionProcesoUi);
    void onAddBasicDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi);
    void showDialogDelete(CapacidadUi capacidadUi,RubroProcesoUi procesoUi, int programaEducativoId);
    void deleteRubro(CapacidadUi capacidadUi, RubroProcesoUi rubroEvaluacionProcesoUi, int programaEducativoId);
    void showMessage(String message);
   // void initViewTipoRubros(RubroEvaluacionResultadoUi rubroEvaluacionResultadoUi, RubroEvaluacionProcesoUi rubroEvaluacionProcesoUi, int mIdCargaCurso, int mIdCurso);
   void initViewTipoRubros(RubroEvaluacionResultadoUi rubroEvaluacionResultadoUi, RubroProcesoUi rubroProcesoUi, int mIdCargaCurso, int mIdCurso);
    void refreshList(Object o);
    /*Vista para AdapterExample Simple*/
    void onShowListSingleChooser(String title, List<Object> items, int positionSelected);
    void goToCamposFragment(String key);

    /*Tipos Opciones Rubros*/
    void initOpcionesRubroFormula(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, View view);
    void initOpcionesRubroRubrica(CapacidadUi capacidadUi,RubroProcesoUi rubroProcesoUi,View view);
    void initOpcionesRubroNormal(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, View view);
    void mostrarDialogoAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi);

    /*startEditActivity*/
    void startEditActivity(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId);
    void showMensaje(String mensaje);
    void showMsgOfflineError();
    void showMsgOffline();
    void hideMsgOffline();
    void showSeleccionarRubros(CapacidadUi capacidadUi);


    void mostrarObjectLista(List<Object> objectList);

    void showDialogDesanclar(CapacidadUi capacidadUi);

    void updateAncladoCapacidad(CapacidadUi capacidadUi);

    void showTipoRubroProceso();

    void showMessageCancel(String message);

    void startEditActivityRubroNormal(RubroProcesoWrapper  rubroProcesoWrapper);

    void showDialogPublicar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId);

    void succesChangePublicar(RubroProcesoUi rubroProcesoUi, int programaEducativoId);

    void showCardProcesamiento(String color1, String color2);

    void hideCardProcesamiento();

    void initServiceUpdate(int programaEducativoId);

    void  actualizarRubroTipoAncla();

    void showDialogProgress();

    void hideDialogProgress();

    void savePositionList();

    void clearPositionList();

    void startEditFormual(String key);
}

