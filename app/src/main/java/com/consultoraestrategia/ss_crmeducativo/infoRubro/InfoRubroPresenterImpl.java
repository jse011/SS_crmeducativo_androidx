package com.consultoraestrategia.ss_crmeducativo.infoRubro;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CampoTematicoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroView;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.usecase.GetRubroDetalle;

import java.util.List;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class InfoRubroPresenterImpl extends BaseFragmentPresenterImpl<InfoRubroView> implements InfoRubroPresenter{

    private GetRubroDetalle getRubroDetalle;
    private String rubroEvalProcesoId;
    private RubroEvalProcesoUi rubroEvalProcesoUi;
    private boolean verMasDesempenio;
    private int maxLinTexDesempenio = 2;

    public InfoRubroPresenterImpl(UseCaseHandler handler, Resources res, GetRubroDetalle getRubroDetalle) {
        super(handler, res);
        this.getRubroDetalle = getRubroDetalle;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return InfoRubroPresenterImpl.class.getSimpleName();
    }

    @Override
    public void postCantidadLineasDesempenio(int lineCount) {
        if(lineCount >= (maxLinTexDesempenio + 1)){
            if(view!=null)view.enabledVerMas(maxLinTexDesempenio);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        InfoRubro();
    }

    private void InfoRubro() {
        handler.execute(getRubroDetalle,
                new GetRubroDetalle.RequestValues(rubroEvalProcesoId),
                new UseCase.UseCaseCallback<GetRubroDetalle.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroDetalle.ResponseValue response) {
                        rubroEvalProcesoUi = response.getRubroEvalProcesoUi();
                        inicializar();
                    }

                    @Override
                    public void onError() {
                        Log.d(getTag(), "Error! Info Rubro Key: "+ rubroEvalProcesoId);
                    }
                });
    }

    @Override
    public void onClickVerMasDesempenio() {
        if(verMasDesempenio){
            verMasDesempenio = false;
            if(view==null)return;
            view.formatMinimizarTextDesmepenio(maxLinTexDesempenio);
            view.changeTextoVerMasDesempenio("Ver más");
        }else {
            verMasDesempenio = true;
            if(view==null)return;
            view.formatMaximizarTextDesmepenio();
            view.changeTextoVerMasDesempenio("Ver menos");
        }
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.rubroEvalProcesoId = extras.getString(InfoRubroFragment.EXTRA_RUBROEVALPROCESOID);
    }

    private void inicializar() {
       if(view==null)return;
        CapacidadUi capacidadUi = rubroEvalProcesoUi.getCapacidadUi();
        CompetenciaUi competenciaUi = capacidadUi.getCompetenciaUi();
        DesempenioUi desempenioUi = rubroEvalProcesoUi.getDesempenioUi();
        IndicadorUi indicadorUi = desempenioUi.getIndicadorUi();
        TipoNotaUi tipoNotaUi = rubroEvalProcesoUi.getTipoNotaUi();
        List<CampoTematicoUi> campoAccionUiList = rubroEvalProcesoUi.getCampoTematicoUiList();

        view.showRubro(rubroEvalProcesoUi);
        view.showIndicador(indicadorUi, competenciaUi);
        view.showDesempenio(desempenioUi);
        view.showCompetencia(competenciaUi);
        view.showCapacidad(capacidadUi);
        view.showTipoNota(tipoNotaUi);
        view.showCampoAccionList(campoAccionUiList);
    }
}
