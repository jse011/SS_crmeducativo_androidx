package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.ui.InfoRubricaBidimensionalFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.ui.InfoRubricaBidimensionalView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;


public class InfoRubricaBidimensionalPresentImpl extends BaseFragmentPresenterImpl<InfoRubricaBidimensionalView> implements InfoRubricaBidimensionalPresent {


    private IndicadorUi indicadorUi;
    private TipoNotaUi tipoNotaUi;
    private boolean notEditCampoAccion;


    public InfoRubricaBidimensionalPresentImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.tipoNotaUi = (TipoNotaUi) extras.getSerializable(InfoRubricaBidimensionalFragment.TIPO_NOTA_UI);
        this.indicadorUi = (IndicadorUi) extras.getSerializable(InfoRubricaBidimensionalFragment.INDICADOR_UI);
    }

    @Override
    public void onResume() {
        super.onResume();
        inicializar();
    }

    private void inicializar() {
        if (view==null)return;
        view.showIndicador(indicadorUi);
        view.showValorTipoNota(tipoNotaUi.getValorTipoNotaList());
        if(indicadorUi.getCriterioEvaluacionUiList()!=null)view.showCel(indicadorUi.getCriterioEvaluacionUiList());

    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onClickSave() {
        if(view!=null)view.saveSuccess(indicadorUi);
    }
}
