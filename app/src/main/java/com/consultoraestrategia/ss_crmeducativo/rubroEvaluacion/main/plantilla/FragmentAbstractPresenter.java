package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla;

import android.os.Bundle;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstractView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by SCIEV on 6/02/2018.
 */

public interface FragmentAbstractPresenter extends BaseFragmentPresenter<FragmentAbstractView> {
    void setExtras(Bundle arguments);

    void onClickAddRubroEvaluacionCapacidad(CapacidadUi capacidadUi);

    void _onClickCapacidad(CapacidadUi capacidadUi);

    void onClickRubroEvaluacion(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi);

    void onClickLongClickRubroEvaluacion(RubroProcesoUi rubroProcesoUi);

    void onSelectDialogListIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds);

    void onSalirDialogListIndicadorCampotematico();

    //void onSucessDialogCrearRubroEvaluacionProceso(int rubroevaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi);

    void onSucessDialogCrearRubroEvaluacionProceso(String rubroevaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi);

    //void onSucessDialogCrearRubroEvaluacionProceso(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi);
    void onSalirDialogCrearRubroEvaluacionProceso();

    void onSussesFiltroCompetencias(ArrayList<Integer> competenciaIdList);

    /*presenter SilaboEvento*/
    void onPeriodoSelected(PeriodoUi periodoUi);

    void onAddBasicDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi);

    void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi);

    void onAgregarRubroFormula(CapacidadUi capacidadUi, RubroProcesoUi crearRubroEvalUi);

    /*Opciones*/
    void onSelectOptions(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, View vistaPosicionItem);

    void onSelectDelete(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId);

    void onUpdateRubroEvaluacion(String rubroEvaluacionId, int programaEducativoId);

    void getRubroProceso(String rubroEvaluacionProcesoId, final AbstractPresenterImpl.Callback<RubroProcesoUi> callback);

    void onAddRubroEvaluacion(String addRubroEvaluacionId);

    void onBtnCamposTematicos(RubroProcesoUi rubroProcesoUi);

    /*onActivityResult*/

    void onActivityResult(Bundle bundle);

    /*Tipos de Opciones*/

    void onSelectOpcionesRubroFormula(CapacidadUi capacidadUi, int posicionMenu, RubroProcesoUi rubroProcesoUi);

    void onSelectOpcionesRubroRubrica(CapacidadUi capacidadUi, int posicionMenu, RubroProcesoUi rubroProcesoUi);

    void onSelectOpcionesRubroNormal(CapacidadUi capacidadUi, int posicionMenu, RubroProcesoUi rubroProcesoUi);

    /*Aceptando Anclado*/
    void onAceptarDialogAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi);

    /*Actualizando Lista, Desde Rubricas- Area o Silabo */
    void actualizarListaRubricas();

    /*Actualizando Lista, Desde Rubricas- Session */
    void onActualizarRegitroSesion();


    void onResumeFragment();
    void onselectedItem(ArrayList<CompetenciaCheck> tipoCompetencia);

    void onClickSelectRubros(CapacidadUi capacidadUi);

    void onRefrescarFragment(String idCalendarioPeriodo, boolean status);

    void onClickAncla(CapacidadUi capacidadUi);

    void onAceptarDialogDesanAnclar(CapacidadUi capacidadUi);

    void onClickBtnAceptarPublicarTodoEvaluaciones(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId);

    void onClickAceptarCalcular();

    void comprobarActualizacionRubros(Map<RubroProcesoUi, CapacidadUi> rubroProcesoUiList);
}
