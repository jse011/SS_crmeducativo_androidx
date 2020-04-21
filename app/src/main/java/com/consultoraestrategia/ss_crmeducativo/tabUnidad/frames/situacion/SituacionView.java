package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.situacion;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

import java.util.List;

public interface SituacionView extends BaseView<SituacionPresenter> {
    void showSituacion(List<SituacionUi> situacionUis);

    void showSituacionBox(SituacionUi situacionUi);

    void showProgressBar();

    void hideProgressBar();
}
