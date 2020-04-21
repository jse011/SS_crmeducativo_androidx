package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.productos;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetProductosUnidad;

public class ProductosPresenterImpl extends BaseFragmentPresenterImpl<ProductosView> implements ProductosPresenter {

    UnidadParametros unidadParametros;
    GetProductosUnidad getProductosUnidad;
    int unidadApredizajeId;
    int calendarioPeriodoId;

    public ProductosPresenterImpl(UseCaseHandler handler, Resources res, GetProductosUnidad getProductosUnidad) {
        super(handler, res);
        this.getProductosUnidad = getProductosUnidad;
    }

    @Override
    protected String getTag() {
        return ProductosPresenterImpl.class.getSimpleName();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        unidadParametros = UnidadParametros.clone(extras);
        if (unidadParametros!=null){
            unidadApredizajeId = unidadParametros.getUnidadAprendizajeId();
            calendarioPeriodoId = unidadParametros.getCalendarioPeriodoId();
        }
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onStart() {
        super.onStart();
        setGetProductos();
    }

    private void setGetProductos() {
        if (view!=null)view.showProgressBar();
        handler.execute(getProductosUnidad, new GetProductosUnidad.RequestValues(unidadApredizajeId, calendarioPeriodoId), new UseCase.UseCaseCallback<GetProductosUnidad.ResponseValues>() {
            @Override
            public void onSuccess(GetProductosUnidad.ResponseValues response) {
                if (view!=null)view.hideProgressBar();
                if (view!=null)view.showProductosUnidad(response.getProductoUis());
            }

            @Override
            public void onError() {

            }
        });
    }
}
