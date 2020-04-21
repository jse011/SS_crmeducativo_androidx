package com.consultoraestrategia.ss_crmeducativo.tipoNota;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public interface TipoNotaView extends BaseView<TipoNotaPresenter> {

    void mostrarLista(List<TipoNotaUi> tipoNotaUiList);
    void regresoMainActivity(int idUsuario);
    void extras (int idUsuario);
}
