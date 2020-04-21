package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.useCase.GetCompetenciaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.view.RubroEvaluacionCompetenciaView;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 9/02/2018.
 */

public class RubroEvaluacionCompetenciaPresenterImpl implements RubroEvaluacionCompetenciaPresenter {
    private int personaId;
    private int sesionAprendizajeId;
    private int backgraound;
    private int idCargaCurso;
    private int cursoId;
    private int nivel;
    private RubroEvaluacionCompetenciaView view;
    private UseCaseHandler handler;
    private GetCompetenciaList getCompetenciaList;
    private List<CompetenciaUi> competenciaUiList;
    private ArrayList<Integer> competenciaIdList;
    private List<CompetenciaUi> competenciaUiCheckList;
    String TAG= RubroEvaluacionCompetenciaPresenterImpl.class.getSimpleName();

    public RubroEvaluacionCompetenciaPresenterImpl(UseCaseHandler handler, GetCompetenciaList getCompetenciaList) {
        this.handler = handler;
        this.getCompetenciaList = getCompetenciaList;
        this.competenciaUiCheckList = new ArrayList<>();
    }

    @Override
    public void attachView(RubroEvaluacionCompetenciaView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
       // getRubroProceso();
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
        view = null;
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void setExtras(Bundle arguments) {
        personaId = arguments.getInt(TabsSesionesActivityV2.INT_PERSONAID);
        sesionAprendizajeId = arguments.getInt(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJEID);
        backgraound = arguments.getInt(TabsSesionesActivityV2.INT_BACKGROUND);
        idCargaCurso = arguments.getInt(TabsSesionesActivityV2.INT_CARGACURSO_ID);
        cursoId = arguments.getInt(TabsSesionesActivityV2.INT_CURSO_ID);
        nivel = arguments.getInt(TabsSesionesActivityV2.INT_NIVEL);
        competenciaIdList = arguments.getIntegerArrayList(FragmentRubroEvalProcesoLista.LIST_COMPETENCIAS);
    }

    @Override
    public void onClickCompetencia(CompetenciaUi competenciaUi) {
        if(competenciaUi.isChecked()){
            competenciaUi.setChecked(false);
            competenciaUiCheckList.remove(competenciaUi);
        }else {
            competenciaUi.setChecked(true);
            competenciaUiCheckList.add(competenciaUi);
        }
        if(view == null)return;
        if(competenciaUiCheckList.size() == 0){
            view.onCheckTotal(true);
        }else {
            view.onCheckTotal(false);
        }
    }

    @Override
    public void onClickAceptar() {
        if(view == null)return;
        if(competenciaUiList == null)return;
        ArrayList<Integer> competenciaIds = new ArrayList<>();
        for (CompetenciaUi competenciaUi: competenciaUiList){
            if(!competenciaUi.isChecked())continue;
            Log.d(TAG, "titulo"+ competenciaUi.getTitulo()+ "id"+ competenciaUi.getId());
            competenciaIds.add(competenciaUi.getId());
        }
        view.sussesCompetencias(competenciaIds);
        view.hideDialog();
    }

    @Override
    public void onClickCancelar() {
        if(view == null)return;
        view.hideDialog();
    }

    @Override
    public void onClickTodos() {
      // getRubroProceso();

    }


    private void getRubroProceso() {
        handler.execute(getCompetenciaList,
                new GetCompetenciaList.RequestValues(competenciaIdList,sesionAprendizajeId, nivel),
                new UseCase.UseCaseCallback<GetCompetenciaList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCompetenciaList.ResponseValue response) {
                        competenciaUiList = response.getCompetenciaUiList();
                        competenciaUiCheckList.clear();
                        if(view == null)return;
                        view.hideProgress();

                        view.showCompetencias(response.getCompetenciaUiList());
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }
}
