package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.RubroProcesoWrapper;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetValorTipoNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.PrecisionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view.TipoNotaListaFragment;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view.TipoNotaListaView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class TipoNotaListaPresenterImpl implements TipoNotaListaPresenter {
    private static final String TAG = TipoNotaListaPresenterImpl.class.getSimpleName();
    private UseCaseHandler handler;
    private GetTipoNotaList getTipoNotaList;
    private TipoNotaListaView view;
    private TipoNotaUi tipoNotaUiSeleccionado;
    private TipoUi.Tipo[] tipoTipoNotaUiList;
    private int programaEducativoId;
    private RubroProcesoWrapper rubroProcesoUi;
    private boolean complejo;
    private GetValorTipoNotaPresicion getValorTipoNotaPresicion;

    public TipoNotaListaPresenterImpl(UseCaseHandler handler, GetTipoNotaList getTipoNotaList, GetValorTipoNotaPresicion getValorTipoNotaPresicion) {
        this.handler = handler;
        this.getTipoNotaList = getTipoNotaList;
        this.getValorTipoNotaPresicion = getValorTipoNotaPresicion;
    }

    @Override
    public void attachView(TipoNotaListaView view) {
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
        if(view!=null)view.showProgress();
        getTipoNotaList();
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
    public void setExtras(Bundle extras) {
        try {
            this.tipoTipoNotaUiList = (TipoUi.Tipo[]) extras.get(TipoNotaListaFragment.ENUM_TIPO_TIPONOTAS);
        }catch (Exception e){
            this.tipoTipoNotaUiList = new TipoUi.Tipo[]{};
        }

        this.rubroProcesoUi = RubroProcesoWrapper.getBundle(extras);
        this.programaEducativoId = extras.getInt(TipoNotaListaFragment.ENUM_PROGRAMA_EDUCATIVO_ID,0);
        this.complejo = extras.getBoolean(TipoNotaListaFragment.ENUM_COMPLEJO);

    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

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
    public void onClickTipoNota(TipoNotaUi tipoNotaUi) {
        if(tipoNotaUi.isChecket()){
            tipoNotaUi.setChecket(false);
            if(view!=null)view.succesTipoNota(-1,"");
        }else {
            tipoNotaUi.setChecket(true);
            if(view!=null) view.succesTipoNota(tipoNotaUi.getId(), tipoNotaUi.getNombre());
        }


        if(tipoNotaUiSeleccionado != null && !tipoNotaUiSeleccionado.equals(tipoNotaUi)){
            tipoNotaUiSeleccionado.setChecket(false);
            if(view!=null)view.updateTipoNota(tipoNotaUiSeleccionado);
        }
        this.tipoNotaUiSeleccionado = tipoNotaUi;
    }

    @Override
    public void onCancelar() {
        //(presenter
    }

    @Override
    public void onAceptar() {

    }

    @Override
    public void onClickRefresh() {
        getTipoNotaList();
    }

    @Override
    public void onClickImportar() {
        BEVariables beVariables =  new BEVariables();
        beVariables.setProgramaEducativoId(programaEducativoId);
        if(view!=null)view.showActivityImportarTipoNota(beVariables);
    }

    private void getTipoNotaList() {

        handler.execute(getTipoNotaList,
                new GetTipoNotaList.RequestValues(programaEducativoId, tipoTipoNotaUiList),
                new UseCase.UseCaseCallback<GetTipoNotaList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoNotaList.ResponseValue response) {
                        List<TipoNotaUi> tipoNotaUis = new ArrayList<>();

                        if(complejo){
                            for(TipoNotaUi tipoNotaUi:response.getTipoNotaUiList() ){
                                if(tipoNotaUi.getTipoUi().getTipo() == TipoUi.Tipo.SELECTOR_VALORES ||
                                        tipoNotaUi.getTipoUi().getTipo() == TipoUi.Tipo.SELECTOR_ICONOS ){
                                    if(tipoNotaUi.getValorTipoNotaUiList()!=null&&
                                            tipoNotaUi.getValorTipoNotaUiList().size()>2){
                                        tipoNotaUis.add(tipoNotaUi);
                                    }

                                }else {
                                    tipoNotaUis.add(tipoNotaUi);
                                }
                            }
                        }else {
                            tipoNotaUis.addAll(response.getTipoNotaUiList());
                        }


                        if(rubroProcesoUi.getRubroId()!=null)for(TipoNotaUi tipoNotaUi:tipoNotaUis ){
                            if(tipoNotaUi.getId().equals(rubroProcesoUi.getTipoNotaId())){
                                tipoNotaUiSeleccionado= tipoNotaUi;
                                tipoNotaUi.setChecket(true);
                                if(view!=null) view.succesTipoNota(tipoNotaUi.getId(), tipoNotaUi.getNombre());
                            }
                            tipoNotaUi.setEnabled(false);
                        }


                        getPrescion(tipoNotaUis);

                        if(view!=null) view.onShowList(tipoNotaUis);
                        if(view!=null) view.hideProgress();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void getPrescion(List<TipoNotaUi> tipoNotaUis) {
        for (TipoNotaUi tipoNotaUi: tipoNotaUis){
            if(tipoNotaUi.getValorTipoNotaUiList()!=null)
            for (ValorTipoNotaUi valorTipoNotaUi : tipoNotaUi.getValorTipoNotaUiList()){

                List<PrecisionUi> precisionUis = getValorTipoNotaPresicion.execute(new GetValorTipoNotaPresicion.RequestValues(
                        valorTipoNotaUi.getColor(),
                        (int)valorTipoNotaUi.getLimiteSuperior(),
                        (int)valorTipoNotaUi.getLimiteInferior(),
                        valorTipoNotaUi.isIncluidoLSuperior(),
                        valorTipoNotaUi.isIncluidoLInferior()));

                valorTipoNotaUi.setPrecisionList(precisionUis);
            }
        }
    }


}
