package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciasContainer;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class IndicadorChooserPresenterImpl implements IndicadorChooserPresenter {


    private List<CompetenciaUi> competenciaList = new ArrayList<>();
    private IndicadorChooserView view;

    @Override
    public void attachView(IndicadorChooserView view) {
        this.view= view;
    }

    @Override
    public void onCreate() {
        for (CompetenciaUi competencia : competenciaList) {
            if(competencia.getCapacidadList()!=null){
                for (CapacidadUi capacidadUi : competencia.getCapacidadList()){
                    if(capacidadUi.getIndicadorList()!=null){
                        for (IndicadorUi indicadorUi : capacidadUi.getIndicadorList()){
                            indicadorUi.setCompetenciaOwner(competencia);
                            indicadorUi.setCapacidadOwner(capacidadUi);
                            if(indicadorUi.getCampoAccionList()!=null){
                                for (CampoAccionUi campoAccionUi: indicadorUi.getCampoAccionList()){
                                    campoAccionUi.setIndicadorUi(indicadorUi);
                                    campoAccionUi.setCompetenciaUi(competencia);
                                    campoAccionUi.setCapacidadUi(capacidadUi);
                                }
                            }

                        }
                    }


                }
            }
        }


        if (competenciaList.isEmpty()) {
            if(view!=null)view.showMensajeVacio();
        } else {
            if(view!=null)view.hideMensajeVacio();
        }
        if (competenciaList != null && !competenciaList.isEmpty()) {
            if(view!=null)view.showCompetenciaList(competenciaList);
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

    @Override
    public void setExtra(Bundle arguments) {
        competenciaList.clear();

        CompetenciasContainer container = Parcels.unwrap(arguments.getParcelable(IndicadoresChoserDialogFragment.ARG_COMPETENCIA_LIST));
        if(container!=null)this.competenciaList.addAll(container.getCompetenciaList());

    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onClickCampoAccion(CampoAccionUi campoAccionUi) {
        Log.d(getClass().getSimpleName(), "onClickCampoAccion");
        IndicadorUi indicadorUi =  campoAccionUi.getIndicadorUi();
        if(!campoAccionUi.isChecked()){
            campoAccionUi.setChecked(true);
            indicadorUi.setChecked(true);
            indicadorUi.setSelected(true);
        }else {
            campoAccionUi.setChecked(false);
            int cant_selected = 0;

            List<CampoAccionUi> campoAccionList = indicadorUi.getCampoAccionList();
            if(campoAccionList!=null){
                for (CampoAccionUi itemCampoAccionUi : campoAccionList){
                    if(itemCampoAccionUi.isChecked()){
                        cant_selected ++;
                    }
                }
            }

            indicadorUi.setChecked(cant_selected!=0);
            indicadorUi.setSelected(cant_selected!=0);
        }


        if(view!=null)view.updateCampotematico(campoAccionUi.getCompetenciaUi(), indicadorUi, campoAccionUi);
    }

    @Override
    public void onClickIndicador(IndicadorUi indicadorUi) {
        Log.d(getClass().getSimpleName(), "onClickIndicador");
        indicadorUi.setChecked(!indicadorUi.isChecked());
        indicadorUi.setSelected(!indicadorUi.isSelected());
        if(indicadorUi.getCampoAccionList()!=null)
            for (CampoAccionUi campoAccionUi: indicadorUi.getCampoAccionList()){
                switch (campoAccionUi.getTipo()){
                    case DEFAULD:
                        campoAccionUi.setChecked(indicadorUi.isChecked());
                        break;
                    case PARENT:
                        //campoAccionUi.setChecked(false);
                        break;
                    case CHILDREN:
                        campoAccionUi.setChecked(indicadorUi.isChecked());
                        break;
                }
            }

        if(view!=null)view.updateIndicador(indicadorUi.getCompetenciaOwner(), indicadorUi);
    }
}
