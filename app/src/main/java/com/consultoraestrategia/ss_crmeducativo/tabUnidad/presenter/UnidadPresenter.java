package com.consultoraestrategia.ss_crmeducativo.tabUnidad.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.UnidadItem;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.view.UnidadView;

public interface UnidadPresenter extends BasePresenter<UnidadView> {
    void onChangeToogle(UnidadItem unidadItem);

    void onClickFilter();

    void onChangeFilter(Object o);
}
