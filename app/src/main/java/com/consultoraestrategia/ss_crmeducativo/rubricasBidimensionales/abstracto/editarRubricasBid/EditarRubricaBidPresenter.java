package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

/**
 * Created by kike on 08/06/2018.
 */

public interface EditarRubricaBidPresenter extends BasePresenter<EditarRubricaBidView> {

    void onTipoEvaluacionClicked();

    void onTipoFormaEvaluacionClicked();

    void onGuardarCambios(String titulo,String alias);

}
