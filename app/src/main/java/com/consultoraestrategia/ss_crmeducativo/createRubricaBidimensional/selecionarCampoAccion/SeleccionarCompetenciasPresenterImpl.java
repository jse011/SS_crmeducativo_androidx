package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion;

import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

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

    private List<CampoAccionUi> campoAccionUiList = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        BuscarCamposAccionListWrapper wrapper = (BuscarCamposAccionListWrapper)extras.getSerializable(BuscarCamposAccionActivity.EXTRA_CAMPOSACCIONLIST);
        this.campoAccionUiList = wrapper.getItems();
        showProgress();
        showCompetenciaList(this.campoAccionUiList);
    }

    private void showCompetenciaList(List<CampoAccionUi> competenciaList) {
        Log.d(TAG, "showCompetenciaList");
        if (competenciaList == null || competenciaList.isEmpty()) {
            showImportantMessage(res.getString(R.string.createrubbid_error_competencias_empty));
            hideProgress();
            return;
        }


        Log.d(TAG, "showCompetenciaListSize: " + competenciaList.size());
        List<Object> competenciaBaseList = new ArrayList<>();
        List<Object> competenciaTransversalList = new ArrayList<>();
        List<Object> competenciaEnfoqueList = new ArrayList<>();


        for (CampoAccionUi itemCampotematicoUiPadre : competenciaList) {

            IndicadorCampoAccionUi indicadorUibase=null;
            IndicadorCampoAccionUi indicadorUienfoque=null;
            IndicadorCampoAccionUi indicadorUitransversal=null;
            if(itemCampotematicoUiPadre.getTipo()==  CampoAccionUi.Tipo.PARENT){

                for (CampoAccionUi itemCampoAccionUihijo : itemCampotematicoUiPadre.getCampoAccionUiList()) {

                    CompetenciaUi competenciaUi = itemCampoAccionUihijo.getCompetenciaUi();

                    if (competenciaUi.isBase()) {

                        int posicion = competenciaBaseList.indexOf(itemCampotematicoUiPadre);
                        if(posicion==-1)competenciaBaseList.add(itemCampotematicoUiPadre);
                        IndicadorUi indicadorUiHijo = itemCampoAccionUihijo.getIndicadorUi();

                        if(indicadorUibase==null || !indicadorUibase.getIndicadorUi().equals(indicadorUiHijo)){
                            indicadorUibase = new IndicadorCampoAccionUi();
                            indicadorUibase.setIndicadorUi(indicadorUiHijo);
                            indicadorUibase.setCampoAccionUi(itemCampotematicoUiPadre);
                            competenciaBaseList.add(indicadorUibase);
                        }
                        indicadorUiHijo.setCompetenciaOwner(competenciaUi);
                        itemCampoAccionUihijo.setIndicadorCampoAccionUi(indicadorUibase);
                        competenciaBaseList.add(itemCampoAccionUihijo);

                        if(itemCampoAccionUihijo.isChecked())indicadorUibase.setChecked(true);
                    }else if (competenciaUi.isEnfoque()) {

                        int posicion = competenciaEnfoqueList.indexOf(itemCampotematicoUiPadre);
                        if(posicion==-1)competenciaEnfoqueList.add(itemCampotematicoUiPadre);
                        IndicadorUi indicadorUiHijo = itemCampoAccionUihijo.getIndicadorUi();

                        if(indicadorUienfoque==null || !indicadorUienfoque.getIndicadorUi().equals(indicadorUiHijo)){
                            indicadorUienfoque = new IndicadorCampoAccionUi();
                            indicadorUienfoque.setIndicadorUi(indicadorUiHijo);
                            indicadorUienfoque.setCampoAccionUi(itemCampotematicoUiPadre);
                            competenciaEnfoqueList.add(indicadorUienfoque);
                        }
                        indicadorUiHijo.setCompetenciaOwner(competenciaUi);
                        itemCampoAccionUihijo.setIndicadorCampoAccionUi(indicadorUienfoque);
                        competenciaEnfoqueList.add(itemCampoAccionUihijo);

                        if(itemCampoAccionUihijo.isChecked())indicadorUienfoque.setChecked(true);
                    } else if (competenciaUi.isTrans()) {

                        int posicion = competenciaTransversalList.indexOf(itemCampotematicoUiPadre);
                        if(posicion==-1)competenciaTransversalList.add(itemCampotematicoUiPadre);
                        IndicadorUi indicadorUiHijo = itemCampoAccionUihijo.getIndicadorUi();

                        if(indicadorUitransversal==null || !indicadorUitransversal.getIndicadorUi().equals(indicadorUiHijo)){
                            indicadorUitransversal = new IndicadorCampoAccionUi();
                            indicadorUitransversal.setIndicadorUi(indicadorUiHijo);
                            indicadorUitransversal.setCampoAccionUi(itemCampotematicoUiPadre);
                            competenciaTransversalList.add(indicadorUitransversal);
                        }
                        indicadorUiHijo.setCompetenciaOwner(competenciaUi);
                        itemCampoAccionUihijo.setIndicadorCampoAccionUi(indicadorUitransversal);
                        competenciaTransversalList.add(itemCampoAccionUihijo);

                        if(itemCampoAccionUihijo.isChecked())indicadorUitransversal.setChecked(true);
                    }
                }

            }else if(itemCampotematicoUiPadre.getTipo()==  CampoAccionUi.Tipo.DEFAULD){
                CompetenciaUi competenciaUi = itemCampotematicoUiPadre.getCompetenciaUi();
                if (competenciaUi.isBase()) {
                    competenciaBaseList.add(itemCampotematicoUiPadre);
                    //competenciaBaseList.add(itemCampotematicoUiPadre.getIndicadorUi());
                    continue;
                }

                if (competenciaUi.isEnfoque()) {
                    competenciaEnfoqueList.add(itemCampotematicoUiPadre);
                    //competenciaBaseList.add(itemCampotematicoUiPadre.getIndicadorUi());
                    continue;
                }

                if (competenciaUi.isTrans()) {
                    competenciaTransversalList.add(itemCampotematicoUiPadre);
                    //competenciaBaseList.add(itemCampotematicoUiPadre.getIndicadorUi());
                }
            }
        }
        showCompetencias(competenciaBaseList, competenciaTransversalList, competenciaEnfoqueList);
        hideProgress();
    }



    private void showCompetencias(List<Object> competenciaBaseList, List<Object> competenciaTransversalList, List<Object> competenciaEnfoqueList) {
        if (view != null) view.showCompetencias(competenciaBaseList, competenciaTransversalList, competenciaEnfoqueList);
    }

    @Override
    public void onAceptarButtonClicked() {
        devolverResultado(campoAccionUiList);
    }

    @Override
    public void onCheckedCamposAccion(CampoAccionUi newCampoAccionUi) {
        int posicion = -1;
        for (CampoAccionUi itemCampoAccionUi :this.campoAccionUiList){
            posicion ++;
            if(itemCampoAccionUi.getTipo() == CampoAccionUi.Tipo.PARENT)continue;
            IndicadorUi indicadorUi = itemCampoAccionUi.getIndicadorUi();
            if(!itemCampoAccionUi.equals(newCampoAccionUi))continue;
            if(!indicadorUi.equals(newCampoAccionUi.getIndicadorUi()))continue;
            campoAccionUiList.set(posicion,itemCampoAccionUi);
        }


    }

    @Override
    public void onQueryTextChange(String newText) {
        if(view != null)view.flitroCompetencia(newText);

    }

    @Override
    public void onCheckedIndicador(IndicadorCampoAccionUi indicadorCompetenciaUi) {

        for (CampoAccionUi itemCampoAccionUi :this.campoAccionUiList){
            CampoAccionUi campoAccionUiNuevo = indicadorCompetenciaUi.getCampoAccionUi();
           if(itemCampoAccionUi.getId() == campoAccionUiNuevo.getId()&&itemCampoAccionUi.getTipo()== CampoAccionUi.Tipo.PARENT){
               for (CampoAccionUi itemcampoAccionHijoAntiguo: itemCampoAccionUi.getCampoAccionUiList()){
                   for (CampoAccionUi itemcampoAccionHijo : campoAccionUiNuevo.getCampoAccionUiList()){
                       if(itemcampoAccionHijoAntiguo.getId()==itemcampoAccionHijo.getId()){
                           itemcampoAccionHijoAntiguo.setChecked(itemcampoAccionHijo.isChecked());
                       }
                   }
               }
           }
        }
    }


    private void devolverResultado(List<CampoAccionUi> competenciaList) {
        if (view != null) view.devolverResultado(campoAccionUiList);
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
