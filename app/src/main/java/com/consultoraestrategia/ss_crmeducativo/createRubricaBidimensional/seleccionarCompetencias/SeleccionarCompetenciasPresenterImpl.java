package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.seleccionarCompetencias;

import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */

public class SeleccionarCompetenciasPresenterImpl extends BasePresenterImpl<SeleccionarCompetenciaView> implements SeleccionarCompetenciaPresenter {
    public static final String TAG = SeleccionarCompetenciasPresenterImpl.class.getSimpleName();

    public SeleccionarCompetenciasPresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    private List<CompetenciaUi> competenciaList = new ArrayList<>();


    @Override
    public void onResume() {
        super.onResume();
        showProgress();
        CompetenciaListWrapper wrapper = (CompetenciaListWrapper)extras.getSerializable(SeleccionarCompetenciasActivity.EXTRA_COMPETENCIALIST);
        showCompetenciaList(wrapper.getItems());
    }

    private void showCompetenciaList(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "showCompetenciaList");
        if (competenciaList == null || competenciaList.isEmpty()) {
            showImportantMessage(res.getString(R.string.createrubbid_error_competencias_empty));
            hideProgress();
            return;
        }
        Log.d(TAG, "showCompetenciaListSize: " + competenciaList.size());
        this.competenciaList.clear();
        this.competenciaList.addAll(competenciaList);

        List<CompetenciaUi> competenciaBaseList = new ArrayList<>();
        List<CompetenciaUi> competenciaTransversalList = new ArrayList<>();
        List<CompetenciaUi> competenciaEnfoqueList = new ArrayList<>();

        for (CompetenciaUi competencia : competenciaList) {
            if (competencia.isBase()) {
                competenciaBaseList.add(competencia);
            } else if (competencia.isEnfoque()) {
                competenciaEnfoqueList.add(competencia);
            }else if (competencia.isTrans())
                competenciaTransversalList.add(competencia);

        }

        showCompetencias(competenciaBaseList, competenciaTransversalList, competenciaEnfoqueList);
        hideProgress();
    }

    private void showCompetencias(List<CompetenciaUi> competenciaBaseList, List<CompetenciaUi> competenciaTransversalList, List<CompetenciaUi> competenciaEnfoqueList) {
        if (view != null)
            view.showCompetencias(competenciaBaseList, competenciaTransversalList, competenciaEnfoqueList);

    }

    @Override
    public void onAceptarButtonClicked() {
        devolverResultado(competenciaList);
    }


    private void devolverResultado(List<CompetenciaUi> competenciaList) {
        if (view != null) view.devolverResultado(competenciaList);
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return TAG;
    }
}
