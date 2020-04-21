package com.consultoraestrategia.ss_crmeducativo.tabUnidad.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.TipoEnum;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.UnidadItem;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.presenter.UnidadPresenter;

import java.util.List;

public interface UnidadView extends BaseView<UnidadPresenter> {
    void setListMenu(List<UnidadItem> listMenu);
    void onShowAprendizaje(UnidadParametros unidadParametros);
    void onChangeMenuItem();

    void showTituloUnidad(String titulo);
//    void onShowAprendizaje();
    void showListChoose(TipoEnum tipoEnum);
    void onShowFragment(UnidadItem unidadItem, UnidadParametros unidadParametros);
}
