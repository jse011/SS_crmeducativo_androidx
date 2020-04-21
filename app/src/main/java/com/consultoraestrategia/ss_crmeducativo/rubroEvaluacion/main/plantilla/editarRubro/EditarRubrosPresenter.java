package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

public interface EditarRubrosPresenter extends BasePresenter<EditarRubrosView> {

    void onTipoEvaluacionClicked();

    void onTipoFormaEvaluacionClicked();

    void onGuardarCambios(String titulo,String alias);
}
