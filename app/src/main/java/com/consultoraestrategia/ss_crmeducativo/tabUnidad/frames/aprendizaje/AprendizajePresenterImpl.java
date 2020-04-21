package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.aprendizaje;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.UploadFile;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetCamposAccion;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetCompetenciaCapacidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.TipoEnum;

import java.util.ArrayList;

public class AprendizajePresenterImpl extends BaseFragmentPresenterImpl<AprendizajeView> implements AprendizajePresenter {

    private GetCompetenciaCapacidad getCompetenciaCapacidad;
    private GetCamposAccion getCamposAccion;
    private int unidadId;
    private int tipoId;
    private int calendarioPeriodoId;


    public AprendizajePresenterImpl(UseCaseHandler handler, Resources res, GetCompetenciaCapacidad getCompetenciaCapacidad, GetCamposAccion getCamposAccion) {
        super(handler, res);
        this.getCompetenciaCapacidad = getCompetenciaCapacidad;
        this.getCamposAccion = getCamposAccion;
    }

    @Override
    protected String getTag() {
        return AprendizajePresenterImpl.class.getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        UnidadParametros unidadParametros = UnidadParametros.clone(extras);
        if (unidadParametros!=null){
            unidadId = unidadParametros.getUnidadAprendizajeId();
            tipoId = TipoEnum.COMPETENCIA_BASE.getId();
            calendarioPeriodoId = unidadParametros.getCalendarioPeriodoId();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setGetCompetenciaCapacidad();
        setGetCamposTematico();

    }

    private void setGetCamposTematico() {
        if (view!=null)view.showProgressCamposTematico();
        handler.execute(getCamposAccion, new GetCamposAccion.RequestValues(unidadId, tipoId, calendarioPeriodoId), new UseCase.UseCaseCallback<GetCamposAccion.ResponseValues>() {
            @Override
            public void onSuccess(GetCamposAccion.ResponseValues response) {
                if (response.getCampoAccionUis().size()>0){
                    if (view!=null)view.showVacioCampos();
                    if (view!=null)view.showCampoAccion(response.getCampoAccionUis());
                }else  {
                    if (view!=null)view.hideVacioCampos();
                    if (view!=null)view.showCampoAccion(new ArrayList<CampoAccionUi>());
                }
                setGetCompetenciaCapacidad();
            }

            @Override
            public void onError() {
                if (view!=null)view.hideProgressCamposTematicos();
            }
        });
    }

    public void setGetCompetenciaCapacidad(){
        handler.execute(getCompetenciaCapacidad, new GetCompetenciaCapacidad.RequestValues(unidadId, tipoId, calendarioPeriodoId), new UseCase.UseCaseCallback<GetCompetenciaCapacidad.ResponseValues>() {
            @Override
            public void onSuccess(GetCompetenciaCapacidad.ResponseValues response) {
                if (response.getCompetenciaUis().size()>0){
                    if (view!=null)view.showVacioCompetencias();
                    if (view!=null)view.showCompetencias(response.getCompetenciaUis());
                }else {
                    if (view!=null)view.hideVacioCompetencias();
                    if (view!=null)view.showCompetencias(new ArrayList<CompetenciaUi>());
                }
                if (view!=null)view.hideProgressCamposTematicos();
            }

            @Override
            public void onError() {
                if (view!=null)view.hideProgressCamposTematicos();
            }
        });
    }

    @Override
    public void filterCompetencia(Object o) {
        if (o instanceof TipoEnum){
            TipoEnum tipoEnum = (TipoEnum) o;
            switch (tipoEnum){
                case COMPETENCIA_BASE:
                    tipoId = tipoEnum.getId();
                    break;
                case COMPETENCIA_TRASVERSAL:
                    tipoId = tipoEnum.getId();
                    break;
            }
            setGetCompetenciaCapacidad();
            setGetCamposTematico();
        }
    }
}
