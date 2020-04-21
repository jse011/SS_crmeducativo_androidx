package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.situacion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetSituacion;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

public class SituacionPresenterImpl extends BaseFragmentPresenterImpl<SituacionView> implements SituacionPresenter {

    UnidadParametros unidadParametros;
    int entidadId;
    int unidadAprendizajeId;
    GetSituacion getSituacion;

    public SituacionPresenterImpl(UseCaseHandler handler, Resources res, GetSituacion getSituacion) {
        super(handler, res);
        this.getSituacion = getSituacion;
    }

    @Override
    protected String getTag() {
        return SituacionPresenterImpl.class.getSimpleName();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        unidadParametros = UnidadParametros.clone(extras);
        if (unidadParametros!=null){
            unidadAprendizajeId = unidadParametros.getUnidadAprendizajeId();
            entidadId = unidadParametros.getEntidadId();
        }
    }

    public void setGetSituacion(){
        if (view!=null)view.showProgressBar();
        handler.execute(getSituacion, new GetSituacion.RequestValues(unidadAprendizajeId, entidadId), new UseCase.UseCaseCallback<GetSituacion.ResponseValues>() {
            @Override
            public void onSuccess(GetSituacion.ResponseValues response) {
                if (view!=null)view.hideProgressBar();
                if (view!=null)view.showSituacion(response.getSituacionUis());
                if (view!=null)view.showSituacionBox(response.getSituacionUis().get(0));
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setGetSituacion();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
