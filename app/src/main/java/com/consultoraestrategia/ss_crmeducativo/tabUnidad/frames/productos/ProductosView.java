package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.productos;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;

import java.util.List;

public interface ProductosView extends BaseView<ProductosPresenter> {
    void showProductosUnidad(List<ProductoUi> productoUis);

    void showProgressBar();

    void hideProgressBar();
}
