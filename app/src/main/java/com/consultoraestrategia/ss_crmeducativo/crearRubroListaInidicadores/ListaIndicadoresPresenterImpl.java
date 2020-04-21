package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase.GetCompetencia;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase.GetIndicadorSesionList;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase.GetIndicadorSilavoList;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ui.ListaIndicadoresFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class ListaIndicadoresPresenterImpl implements ListaIndicadoresPresenter {
    private String TAG = ListaIndicadoresPresenterImpl.class.getSimpleName();
    private ListaIndicadoresView view;
    private UseCaseHandler handler;
    private int sesionAprendizajeId;
    private int silavoEventoId;
    private int nivel;
    private int competenciaId;
    private int indicadorId = 0;
    private ArrayList<Integer> camposIds = new ArrayList<>();
    private GetIndicadorSesionList getIndicadorSesionList;
    private GetIndicadorSilavoList getIndicadorSilavoList;
    private GetCompetencia getCompetencia;
    private List<IndicadorUi> indicadorUiList = new ArrayList<>();
    private int calendarioPeridoId;
    private List<CompetenciaUi> competenciaUiList;

    public ListaIndicadoresPresenterImpl(UseCaseHandler handler, GetIndicadorSesionList getIndicadorSesionList, GetIndicadorSilavoList getIndicadorSilavoList, GetCompetencia getCompetencia) {
        this.handler = handler;
        this.getIndicadorSesionList = getIndicadorSesionList;
        this.getIndicadorSilavoList = getIndicadorSilavoList;
        this.getCompetencia = getCompetencia;
    }


    @Override
    public void attachView(ListaIndicadoresView view) {
        this.view = view;
    }

    //region ciclo de vida
    @Override
    public void onCreate() {

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
        this.view = null;
    }

    @Override
    public void onDestroy() {
        this.view = null;
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
        getIndicadorSesionList();
        getCompetenciaUi(nivel, competenciaId);
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    //endregion ciclo de vida

    @Override
    public void onBackPressed() {
        cerraDialog();
    }


    @Override
    public void setExtras(Bundle extras) {
        sesionAprendizajeId = extras.getInt(ListaIndicadoresFragment.INT_SESION_APRENDIZAJE_ID);
        silavoEventoId = extras.getInt(ListaIndicadoresFragment.INT_SILAVO_EVENTO_ID);
        calendarioPeridoId = extras.getInt(ListaIndicadoresFragment.INT_CALENDARIO_PERIDOD_ID);
        nivel = extras.getInt(ListaIndicadoresFragment.INT_NIVEL);
        competenciaId = extras.getInt(ListaIndicadoresFragment.INT_COMPETNCIA_ID);
        Log.d(TAG, "onSelectCampotematico  " + competenciaId);
        indicadorId = extras.getInt(ListaIndicadoresFragment.INT_INDICADOR_ID);
        camposIds = extras.getIntegerArrayList(ListaIndicadoresFragment.CAMPOS_TEMATICOS_IDS);
        Log.d(TAG, "setExtras : " + sesionAprendizajeId + "/ " + silavoEventoId + " / " + nivel + "/" + indicadorId);
    }

    @Override
    public void onSelectCampotematico(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampotematicoUi campoAccionUi) {
        Log.d(TAG, "onSelectCampotematico 1 " + competenciaId);
        this.competenciaId = Integer.valueOf(indicadorUi.getCapacidadUi().getId());
        if(!campoAccionUi.isChecked()){
            campoAccionUi.setChecked(true);
            indicadorUi.setSeleccionado(true);
        }else {
            campoAccionUi.setChecked(false);
            int cant_selected = 0;

            List<CampotematicoUi> campoAccionList = indicadorUi.getCampotematicoUis();
            if(campoAccionList!=null){
                for (CampotematicoUi itemCampoAccionUi : campoAccionList){
                    if(itemCampoAccionUi.isChecked()){
                        cant_selected ++;
                    }
                }
            }

            indicadorUi.setSeleccionado(cant_selected!=0);

        }

        for (IndicadorUi itemIndicadorUi : indicadorUiList) {
            if (!itemIndicadorUi.equals(indicadorUi)) {
                itemIndicadorUi.setSeleccionado(false);
                for (CampotematicoUi itemCampotematicoUi : itemIndicadorUi.getCampotematicoUis()) {
                    itemCampotematicoUi.setChecked(false);
                }
            }
        }


        if(view!=null)view.actualizarComptenciaLista();

    }

    @Override
    public void onSeleccionarIndicador() {
        Log.d(TAG, "onSeleccionarIndicador " + indicadorUiList.size());
        ArrayList<Integer> campotematicoIds = new ArrayList<>();
        for (IndicadorUi itemIndicadorUi : indicadorUiList) {
            if (!itemIndicadorUi.isSeleccionado()) continue;
            for (CampotematicoUi itemCampotematicoUi : itemIndicadorUi.getCampotematicoUis()) {
                if (itemCampotematicoUi.isChecked()) {
                    campotematicoIds.add(itemCampotematicoUi.getId());

                }
            }
            if (campotematicoIds.size() == 0) {
                if (view != null)
                    view.showMensaje(R.string.msg_crearRubroEvaluacion_selecionar_campotematico);
            } else {
                Log.d(TAG, "onSeleccionarIndicador " + competenciaId);
                Log.d(TAG, " campotematicoIds " + campotematicoIds.size());
                if (view != null)
                    view.retornarIndicadorCampotematico(competenciaId, itemIndicadorUi.getId(), campotematicoIds);
            }
            break;
        }


    }

    @Override
    public void onClickIndicador(CompetenciaUi competencia, IndicadorUi indicadorUi) {
        indicadorUi.setSeleccionado(!indicadorUi.isSeleccionado());
        if(indicadorUi.getCampotematicoUis()!=null)
            for (CampotematicoUi campoAccionUi: indicadorUi.getCampotematicoUis()){
                if(campoAccionUi.isHijo()){
                    campoAccionUi.setChecked(indicadorUi.isSeleccionado());
                }else {
                    if(campoAccionUi.isDisable()){
                        //campoAccionUi.setChecked(false);
                    }else {
                        campoAccionUi.setChecked(indicadorUi.isSeleccionado());
                    }
                }

            }

        for (IndicadorUi itemIndicadorUi : indicadorUiList) {
            if (!itemIndicadorUi.equals(indicadorUi)) {
                itemIndicadorUi.setSeleccionado(false);
                for (CampotematicoUi itemCampotematicoUi : itemIndicadorUi.getCampotematicoUis()) {
                    itemCampotematicoUi.setChecked(false);
                }
            }
        }

        if(view!=null)view.actualizarComptenciaLista();

        this.competenciaId = Integer.valueOf(indicadorUi.getCapacidadUi().getId());
    }


    private List<IndicadorUi> onSeletedCamposTematicos(int indicadorId, ArrayList<Integer> camposIds, List<IndicadorUi> indicadorList) {

        for (IndicadorUi itemIndicadorUi : indicadorList) {
            if (itemIndicadorUi.getId() == indicadorId) {
                itemIndicadorUi.setSeleccionado(true);
                for (CampotematicoUi itemCampotematicoUi : itemIndicadorUi.getCampotematicoUis()) {
                    for (int i = 0; i < camposIds.size(); i++)
                        if (itemCampotematicoUi.getId() == camposIds.get(i)) {
                            itemCampotematicoUi.setChecked(true);
                        }
                }
            }
        }
        return indicadorList;
    }


    private void cerraDialog() {
        if (view != null) {
            view.hideDialog();
        }
    }

    private void showListIndicadores(List<CompetenciaUi> competenciaUiList) {

        if (view != null) {
            view.showListInidicadores(competenciaUiList);
        }
    }

    private void hideListIndicadores() {
        if (view != null) {
            view.hideListInidicadores();
        }
    }

    private void showProgress() {
        if (view != null) {
            view.showProgress();
        }
    }

    private void hideProgress() {
        if (view != null) {
            view.hideProgress();
        }
    }

    //region Abstraccion de Datos

    private void getIndicadorSesionList() {

        showProgress();
        if (sesionAprendizajeId > 0) {
            Log.d(TAG, "sesionAprendizajeId");
            handler.execute(
                    getIndicadorSesionList,
                    new GetIndicadorSesionList.RequestValues(sesionAprendizajeId, nivel, competenciaId),
                    new UseCase.UseCaseCallback<GetIndicadorSesionList.ResponseValue>() {
                        @Override
                        public void onSuccess(GetIndicadorSesionList.ResponseValue response) {
                            competenciaUiList = response.getCompetenciaUiList();
                            for (CompetenciaUi competenciaUi : competenciaUiList) {
                                for (Object o : competenciaUi.getItems())
                                    if (o instanceof IndicadorUi)
                                        indicadorUiList.add(((IndicadorUi) o));
                            }
                            onSeletedCamposTematicos(indicadorId, camposIds, indicadorUiList);
                            showListIndicadores(competenciaUiList);
                            hideProgress();
                        }

                        @Override
                        public void onError() {
                            Log.d(TAG, "onError getIndicadorSesionList");
                        }
                    });
        } else if (silavoEventoId > 0) {
            Log.d(TAG, "competenciaId" + competenciaId);
            final List<CompetenciaUi> lista = new ArrayList<>();
            handler.execute(
                    getIndicadorSilavoList,
                    new GetIndicadorSilavoList.RequestValues(competenciaId, silavoEventoId, nivel, calendarioPeridoId),
                    new UseCase.UseCaseCallback<GetIndicadorSilavoList.ResponseValue>() {
                        @Override
                        public void onSuccess(GetIndicadorSilavoList.ResponseValue response) {
                            competenciaUiList = response.getAlumnosEvaluacionUis();
                            for (CompetenciaUi competenciaUi : competenciaUiList) {
                                for (Object o : competenciaUi.getItems())
                                    if (o instanceof IndicadorUi)
                                        indicadorUiList.add(((IndicadorUi) o));
                            }
                            onSeletedCamposTematicos(indicadorId, camposIds, indicadorUiList);
                            showListIndicadores(competenciaUiList);
                            hideProgress();

                        }

                        @Override
                        public void onError() {
                            Log.d(TAG, "onError getIndicadorSesionList");
                        }
                    });
        } else {
            if (view != null) {
                view.hideDialog();
            }
        }
    }

    private void getCompetenciaUi(int nivel, int competenciaId) {
        handler.execute(getCompetencia,
                new GetCompetencia.RequestValues(nivel, competenciaId),
                new UseCase.UseCaseCallback<GetCompetencia.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCompetencia.ResponseValue response) {
                        if (view != null) showCompetencia(response.getCompetenciaUi());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void showCompetencia(CompetenciaUi competenciaUi) {
        if (competenciaUi == null) return;
        CapacidadUi capacidadUi = competenciaUi.getCapacidadUi();
        if (capacidadUi == null) return;
        if (view != null) view.showCompetencia(competenciaUi, capacidadUi);

    }
}
