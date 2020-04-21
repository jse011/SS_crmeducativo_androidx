package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionView;

public interface EvaluacionUnidadPresenter  extends BaseFragmentPresenter<EvaluacionView> {
    void onClickRubrica(RubricaUi rubricaUi);
}
