package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;

import java.util.List;

/**
 * Created by CCIE on 26/03/2018.
 */

public interface CrearRubroFormulaView extends BaseView<CrearRubroFormulaPresenter> {

    void showListCheckedRubros(CapacidadUi capacidadUi, List<RubroProcesoUi> listaddChecked, boolean columnPeso);

    void showTipoNotaSelected(TipoNotaUi tipoNota);

    void showTipoFormulaSelected(String name);

    void showTipoRedondeadoSelected(String name);

    void showPesoRubro(double countPeso);

    void hidePesoRubro(double countPeso);

    void showMessageLimiteWeigth(double peso);

    void showMessageWeigth(double peso);

    void showDialogEditPeso(RubroProcesoUi rubroProcesoUi);

    void showError(String error);

    void success(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi);

    void mostrarListaTipoNota(String dialogTitle, final List<Object> items, int positionSelected, int programaEducativoId);

    void removerRubro(RubroProcesoUi rubroProcesoUi);

    void changeListaRubros();

    void onSincronizate(int programaEducatId);

    void showDialogProgress();

    void hideDialogProgress();

    void showDialogInfoTipoNota(String titulo, TipoNotaUi tipoNotaSelected);

    void setNombreFormula(String titulo);

    void success();

    void disabledListFormula();

    void setTitulo(String title);
}
