package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.presenter;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.TipoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.view.FilterChooserView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;

import java.util.ArrayList;
import java.util.List;

public class FilterChooserPresenterImpl implements FilterChooserPresenter {

    public String TAG = FilterChooserPresenterImpl.class.getSimpleName();

    private UseCaseHandler handler;
    private FilterChooserView view;
    private ArrayList<CompetenciaCheck> competenciaCheckedList = new ArrayList<>();

    public FilterChooserPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void filterChooserCheckItem(CompetenciaCheck tipoCompetencia) {
        if(!tipoCompetencia.getChecked())tipoCompetencia.setChecked(true);
        for (CompetenciaCheck competenciaCheck: competenciaCheckedList){
         if(!tipoCompetencia.equals(competenciaCheck))competenciaCheck.setChecked(false);
        }

        if (view != null) view.setList(competenciaCheckedList);
    }

    @Override
    public void onAceptar() {
        int contador = 0;
        for (CompetenciaCheck c : competenciaCheckedList) if (c.getChecked()) contador++;
        if (contador != 0) view.onSuccess(competenciaCheckedList);
        else {
            view.mostrarMensaje("Seleccioné un tipo de competencia");
        }
    }

    @Override
    public void onViewCreate() {
        Log.d(TAG, "ONVIEWCREATE");
    }

    @Override
    public void setExtras(ArrayList<CompetenciaCheck> competenciaCheckArrayList) {
        this.competenciaCheckedList = competenciaCheckArrayList;
    }

    @Override
    public void attachView(FilterChooserView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        if (competenciaCheckedList.size() == 0) {
            competenciaCheckedList.add(new CompetenciaCheck(TipoCompetencia.BASE, true));
            competenciaCheckedList.add(new CompetenciaCheck(TipoCompetencia.OTROS, false));
        }
        if (view != null) {
            view.setList(competenciaCheckedList);
        }
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }
}
