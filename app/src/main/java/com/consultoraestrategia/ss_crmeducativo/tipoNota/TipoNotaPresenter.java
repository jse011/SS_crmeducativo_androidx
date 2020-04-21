package com.consultoraestrategia.ss_crmeducativo.tipoNota;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public interface TipoNotaPresenter  extends BasePresenter<TipoNotaView> {

    void showListTipoNota (List<TipoNotaUi> tipoNotaUiList);

    void showActivityCrearTipoNota();
}
