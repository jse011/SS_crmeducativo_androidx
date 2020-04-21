package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.EvaluacionFormulaPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaColumnaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaFilaCabecera;

import java.util.List;

/**
 * Created by CCIE on 03/05/2018.
 */

public interface EvaluacionFormulaView extends BaseView<EvaluacionFormulaPresenter> {

    void mostrarTitulo(String titulo);
    //void mostrarListaTablas(List<FormulaColumnaCabecera> columnaCabeceraList, List<FormulaFilaCabecera> filaCabeceraList, List<List<FormulaCelda>> bodyList);
    void mostrarListaTablas(List<FormulaColumnaCabecera> columnaCabeceraList, List<FormulaFilaCabecera> filaCabeceraList, List<List<FormulaCelda>> bodyList);
    void showInfoRubroEvalProceso(EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi);
    void showInfoUsuario(AlumnosUi alumnosUi);
}
