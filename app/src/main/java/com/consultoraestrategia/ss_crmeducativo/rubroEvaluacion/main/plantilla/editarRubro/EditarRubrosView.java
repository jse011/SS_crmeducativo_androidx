package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

public interface EditarRubrosView extends BaseView<EditarRubrosPresenter> {

    void mostrarTitulos(String tituloRubrica, String alias);

    void mostrarTipoFormaSelected(String nombre);

    void mostrarTipoEvaluacion(String nombre);

    void mostrarCajaTextoTipoEvaluacion(String nombre);

    void mostrarCajaTextoTipoNota(String nombre);

    void mostrarCajaTextoTipoFormaEvaluacion(String nombre);

    void mostrarMensaje(String mensaje);

    void onSuccessMensaje(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId);

    void showTipoNotaSelected(com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi tipoNotaUi);

    void mostrarCajaTextoTipoFormula(String nombre);

    void mostrarCajaTextoValorFormula(String nombre);

    void changeRubroFormula();

    void changeRubSimple();
}
