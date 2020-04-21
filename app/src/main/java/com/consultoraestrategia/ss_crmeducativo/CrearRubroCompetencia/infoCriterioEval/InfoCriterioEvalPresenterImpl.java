package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.domain.usecase.GetIndicador;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.ui.InfoCriterioEvalFragment;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.ui.InfoCriterioEvalView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;

import java.util.List;

public class InfoCriterioEvalPresenterImpl extends BaseFragmentPresenterImpl<InfoCriterioEvalView> implements InfoCriterioEvalPresenter {

    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private GetIndicador getIndicador;
    private int indicadorId;
    private IndicadorUi indicadorUi;

    public InfoCriterioEvalPresenterImpl(UseCaseHandler handler, Resources res, GetIndicador getIndicador) {
        super(handler, res);
        this.getIndicador = getIndicador;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.valorTipoNotaUiList = (List<ValorTipoNotaUi>) extras.getSerializable(InfoCriterioEvalFragment.ARRAYLIST_VALORTIPONOTA);
        this.indicadorId = extras.getInt(InfoCriterioEvalFragment.INDICADOR_ID);

    }

    @Override
    public void onResume() {
        super.onResume();
        InfoIndicador();
        showValores();
    }


    private void InfoIndicador() {
        handler.execute(getIndicador,
                new GetIndicador.RequestValues(indicadorId),
                new UseCase.UseCaseCallback<GetIndicador.ResponseValues>() {
                    @Override
                    public void onSuccess(GetIndicador.ResponseValues response) {
                        indicadorUi = response.getIndicadorUi();
                        showIndicador();
                    }

                    @Override
                    public void onError() {
                        Log.d(getTag(), "Error! Info Rubro Key: ");
                    }
                });
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onClickAceptar() {

    }

    @Override
    public void onCLickCancelar() {

    }

    private void showValores() {

        if (view == null) return;
        view.showValorTipoNota(valorTipoNotaUiList);
    }

    private void showIndicador() {
        if (view == null) return;
        view.showIndicador(indicadorUi);
    }
}
