package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.RubroProcesoWrapper;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposTematicosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.DesempenioIcdUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubrosAsociadosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.DeleteRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowCamposTematicos;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowDesempenioIcds;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstractView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.silabo.RubroResultadoSilaboPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE;
import static com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi.Tipo.UNIDIMENCIONAL;

/**
 * Created by SCIEV on 6/02/2018.
 */

public abstract class AbstractPresenterImpl implements FragmentAbstractPresenter {
    protected FragmentAbstractView view;
    protected UseCaseHandler handler;
    protected DeleteRubroProceso deleteRubroProcesoSilabo;
    protected GetRubroProceso getRubroProceso;
    protected ShowCamposTematicos showCamposTematicos;
    protected ShowDesempenioIcds showDesempenioIcds;
    protected Resources res;
    protected ChangeToogle changeToogle;
    protected PublicarTodasEvaluacion publicarTodasEvaluacion;

    public static final String TAG = AbstractPresenterImpl.class.getSimpleName();
    protected int cursoId;
    protected List<Object> rubroProcesoList = new ArrayList<>();

    public AbstractPresenterImpl(UseCaseHandler handler, Resources res, DeleteRubroProceso deleteRubroProcesoSilabo, ShowCamposTematicos showCamposTematicos, ShowDesempenioIcds showDesempenioIcds, GetRubroProceso getRubroProceso, ChangeToogle changeToogle,PublicarTodasEvaluacion publicarTodasEvaluacion) {
        this.handler = handler;
        this.deleteRubroProcesoSilabo = deleteRubroProcesoSilabo;
        this.getRubroProceso = getRubroProceso;
        this.showCamposTematicos = showCamposTematicos;
        this.showDesempenioIcds = showDesempenioIcds;
        this.res = res;
        this.changeToogle = changeToogle;
        this.publicarTodasEvaluacion = publicarTodasEvaluacion;
    }

    protected abstract int getProgramaEducativoId();


    public interface ListCallback<T> {
        void onListLoaded(List<T> items);
    }

    public interface Callback<T> {
        void onListLoaded(T item);
    }


    @Override
    public void attachView(FragmentAbstractView view) {
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
        Log.d(TAG, "onResume");
        // getPeriodoList();
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
       /* if (periodoSelected != null || cursoId > 0) {
            getPeriodoList();
        }else {

        }*/
        //getRubroProcesoAbstractList(1);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void _onClickCapacidad(CapacidadUi capacidadUi) {
        onToogleCapacidad(capacidadUi);
        if(view!=null)view.updateCompetencia(capacidadUi);
        onClickCapacidad(capacidadUi);
    }



    private void onToogleCapacidad(CapacidadUi capacidadUi) {
        if (isNullView()) return;
        Log.d(TAG, "onToogleCapacidad");
        if (capacidadUi.isToogle()) {
            capacidadUi.setToogle(false);
        } else {
            capacidadUi.setToogle(true);
        }
        ChangeToogle.Response response = changeToogle.execute(new ChangeToogle.Requests(capacidadUi.isToogle(),capacidadUi.getId()));
        if(!response.isSucces()){}
    }

    protected abstract void onClickCapacidad(CapacidadUi capacidadUi);

    protected boolean isNullView() {
        if (view == null) {
            return true;
        }
        return false;
    }


    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        Log.d(TAG, "onPeriodoCargaCursoSelected");
    }


    @Override
    public void onAddBasicDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi) {
        Log.d(TAG, "onAddBasicDialog");
    }

    @Override
    public void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi) {
        Log.d(TAG, "onClickRubrosAsociados");

    }

    @Override
    public void onAgregarRubroFormula(CapacidadUi capacidadUi, RubroProcesoUi crearRubroEvalUi) {
        Log.d(TAG, "onAgregarRubroFormula : "+ crearRubroEvalUi.getRubrosAsociadosUiList().size());
    }

    @Override
    public void onSelectOptions(final CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, final View vistaPosicionItem) {
        Log.d(TAG, "onSelectOptions :" + rubroProcesoUi.getTipo());
        if (rubroProcesoUi.getTipoFormulaId() > 0) {
            if (view != null)
                view.initOpcionesRubroFormula(capacidadUi, rubroProcesoUi, vistaPosicionItem);
        } else if (rubroProcesoUi.getTipo() == BIDIMENCIONAL_DETALLE) {
            if (view != null)
                view.initOpcionesRubroRubrica(capacidadUi, rubroProcesoUi, vistaPosicionItem);
        } else if (rubroProcesoUi.getTipo() == UNIDIMENCIONAL) {
            if (view != null)
                view.initOpcionesRubroNormal(capacidadUi, rubroProcesoUi, vistaPosicionItem);
        }

    }


    @Override
    public void onSelectOpcionesRubroFormula(CapacidadUi capacidadUi, int posicionMenu, RubroProcesoUi rubroProcesoUi) {
        switch (posicionMenu) {
            case 0:
                if (view != null)view.startEditFormual(rubroProcesoUi.getKey());
                break;
            case 2:
                //boolean diableAncla = capacidadUi.isTieneResultado();
                //if(diableAncla){
                    if (view != null) view.mostrarDialogoAnclar(capacidadUi, rubroProcesoUi);
                //}else {
                  //  if (view != null) view.showMessage("El rubro resultado a anclar no existe.");
                //}
                break;
            case 1:
                if (view != null) view.showDialogDelete(capacidadUi, rubroProcesoUi, getProgramaEducativoId());
                break;
        }
    }

    @Override
    public void onSelectOpcionesRubroRubrica(CapacidadUi capacidadUi, int posicionMenu, RubroProcesoUi rubroProcesoUi) {
        switch (posicionMenu) {
            case 0:
                if (view != null) view.showDialogDelete(capacidadUi, rubroProcesoUi, getProgramaEducativoId());
                break;
        }
    }

    @Override
    public void onSelectOpcionesRubroNormal(CapacidadUi capacidadUi, int posicionMenu, RubroProcesoUi rubroProcesoUi) {
        switch (posicionMenu) {
            case 0:
               setRubroWrapperBundle(rubroProcesoUi, capacidadUi);
                break;
            case 1:
                if (view != null) view.showDialogDelete(capacidadUi, rubroProcesoUi, getProgramaEducativoId());
                break;
            case 2:
                if (view != null) view.showDialogPublicar(capacidadUi, rubroProcesoUi, getProgramaEducativoId());
                break;
        }
    }
    public void setRubroWrapperBundle( RubroProcesoUi rubroProcesoUi,CapacidadUi capacidadUi ){
        ArrayList<Integer>indicadoresId= new ArrayList<>();
        for(CamposAccionUi camposAccionUi: rubroProcesoUi.getIndicadoresCamposAccionUi().getCampoAccionList())indicadoresId.add(Integer.parseInt(camposAccionUi.getKey()));
        RubroProcesoWrapper rubroProcesoWrapper= new RubroProcesoWrapper();
        rubroProcesoWrapper.setRubroId(rubroProcesoUi.getKey());
        rubroProcesoWrapper.setTitulo(rubroProcesoUi.getTitulo());
        rubroProcesoWrapper.setIndicadorId(rubroProcesoUi.getDesempenioIcdId());
        rubroProcesoWrapper.setFormaEvaluacionId(rubroProcesoUi.getFormaEvaluacionId());
        rubroProcesoWrapper.setTipoEvaluacionId(rubroProcesoUi.getTipoEvaluacionId());
        rubroProcesoWrapper.setCampotematicoIds(indicadoresId);
        rubroProcesoWrapper.setSesionAprendizajeId(rubroProcesoUi.getSesionAprendizajeId());
        rubroProcesoWrapper.setSilaboEventoId(rubroProcesoUi.getSilaboEventoId());
        rubroProcesoWrapper.setCalendarioId(rubroProcesoUi.getCalendarioPeriodId());
        rubroProcesoWrapper.setCompetenciaId(capacidadUi.getId());
        rubroProcesoWrapper.setProgramaEducativoId(getProgramaEducativoId());
        rubroProcesoWrapper.setTipoNotaId(rubroProcesoUi.getTipoNotaId());
        rubroProcesoWrapper.setEstrategiaEvalId(rubroProcesoUi.getEstrategiaId());
        Log.d(TAG,  " / cursoId " + cursoId);
        rubroProcesoWrapper.setCursoId(cursoId);
        rubroProcesoWrapper.setSubtitulo(rubroProcesoUi.getSubTitulo());
        if (view != null)view.startEditActivityRubroNormal(rubroProcesoWrapper);

    }

    @Override
    public void onAceptarDialogAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "onAceptarDialogAnclar");
       /* rubroProcesoUi.setTipoAncla(true);
        if (view != null) view.updateRubroProceso(capacidadUi, rubroProcesoUi);*/

    }

    @Override
    public void onSelectDelete(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, final int programaEducativoId) {
        Log.d(TAG, "Elminando View, CaseUse RubroProceso");
        deleteRubroProcesoSilabo.execute(new DeleteRubroProceso.RequestValues(rubroProcesoUi),
                new UseCaseSincrono.Callback<DeleteRubroProceso.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, DeleteRubroProceso.ResponseValue response) {
                        Log.d(TAG, "getRubroEvaluacionResultadoList onSuccess");
                        if (view != null) {
                            // Log.d(TAG,"ResponseRubroAsociadosUi : "+response.getRubrosAsociadosUi().getNumeroRubroAsociado());
                            Log.d(TAG, "validate : " + response.getValidateSuccess());
                            if (response.getValidateSuccess() == RubroResultadoSilaboPresenterImpl.REGISTRO_ERROR) {
                                view.showMessage("Error al eliminar rubro");
                            } else if (response.getValidateSuccess() == RubroResultadoSilaboPresenterImpl.REGISTRO_MESSAGE) {
                                if( rubroProcesoUi.getTipoFormula() == RubroProcesoUi.TipoFormula.ANCLADA || rubroProcesoUi.getTipoFormula() == RubroProcesoUi.TipoFormula.EVALUADA){
                                    view.showMessage("Acción cancelada, el rubro fórmula a eliminar esta anclada.");
                                }else {
                                    boolean showMensaje = false;

                                    if(capacidadUi !=null){
                                        int posicion = capacidadUi.getRubroProcesoUis().size();
                                        for(RubroProcesoUi itemRubroProcesoUi : capacidadUi.getRubroProcesoUis()){
                                            if (itemRubroProcesoUi.getTipoFormulaId() > 0){
                                                for (RubrosAsociadosUi rubrosAsociadosUi:itemRubroProcesoUi.getRubrosAsociadosUiList()){
                                                    //Log.d(TAG, "rubrosAsociadosUi " + rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());
                                                    String rubroEvalId = rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario()==null?"":rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario();
                                                    if(rubroEvalId.equals(rubroProcesoUi.getKey())){
                                                        view.showMessageCancel("Acción cancelada, eliminé primero el rubro fórmula " + posicion+": "+itemRubroProcesoUi.getTitulo());
                                                        showMensaje= true;
                                                    }
                                                }
                                            }
                                            posicion--;
                                        }
                                    }


                                    if(!showMensaje){
                                        view.showMessageCancel("Acción cancelada, eliminé primero rubro fórmula");
                                    }

                                }
                            } else if (response.getValidateSuccess() == RubroResultadoSilaboPresenterImpl.REGISTRO_SUCCESS) {
                                // Log.d(TAG , "else : "+ capacidadUi.getTitle()+" / rubro : "+ rubroProcesoUi.getTitulo());
                                //int position = objectList.indexOf
                                //Log.d(TAG, "validate : " + response.getValidateSuccess());
                                List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                                int posicion = rubroProcesoUiList.indexOf(rubroProcesoUi);
                                if(posicion!=-1)rubroProcesoUiList.remove(posicion);
                                capacidadUi.setCantidad(rubroProcesoUiList.size());
                                view.deleteRubro(capacidadUi, rubroProcesoUi, programaEducativoId);
                            } else {
                                Log.d(TAG, "NIGGA");
                            }
                        }
                    }
                });

    }

    @Override
    public void onUpdateRubroEvaluacion(String rubroEvaluacionId, int programaEducativoId) {
        Log.d(TAG, "onUpdateRubroEvaluacion");
    }

    @Override
    public void getRubroProceso(String rubroEvaluacionProcesoId, final Callback<RubroProcesoUi> callback) {
        handler.execute(getRubroProceso,
                new GetRubroProceso.RequestValues(rubroEvaluacionProcesoId),
                new UseCase.UseCaseCallback<GetRubroProceso.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroProceso.ResponseValue response) {
                        callback.onListLoaded(response.getRubroProcesoUi());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    /*No borrar- NIggaaa!!*/
    @Override
    public void onActivityResult(Bundle bundle) {
    }


    public void onSingleItemSelected(String tituloDialog, List<Object> singleItem, int position) {
        if (singleItem instanceof DesempenioIcdUi) {
            validationView(tituloDialog, singleItem, position);
        } else if (singleItem instanceof CamposTematicosUi) {
            validationView(tituloDialog, singleItem, position);
        }
    }

    @Override
    public void onBtnCamposTematicos(RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "onBtnCamposTematicos : " + rubroProcesoUi.getTipo());
        switch (rubroProcesoUi.getTipo()) {
            case UNIDIMENCIONAL:
                initUnidimencional(rubroProcesoUi);
                break;
            case BIDIMENCIONAL_DETALLE:
                initBimencional(rubroProcesoUi);
                break;
            default:
                break;
        }

    }

    private void initUnidimencional(RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "initUnidimencional : " + rubroProcesoUi.getTipo());
        if (view != null) {
            view.goToCamposFragment(rubroProcesoUi.getKey());
        }
       /* handler.execute(showCamposTematicos,
                new ShowCamposTematicos.RequestValues(rubroProcesoUi),
                new UseCase.UseCaseCallback<ShowCamposTematicos.ResponseValues>() {
                    @Override
                    public void onSuccess(ShowCamposTematicos.ResponseValues response) {
                        validationView("Campos Tematicos ", response.getCamposTematicosUis(), -1);
                        Log.d(TAG, "initUnidimencionalLista : " + response.getCamposTematicosUis().size());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "No hay Items");
                    }
                });*/

    }

    private void initBimencional(RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "initBimencional : " + rubroProcesoUi.getTipo());
        handler.execute(showDesempenioIcds,
                new ShowDesempenioIcds.RequestValues(rubroProcesoUi),
                new UseCase.UseCaseCallback<ShowDesempenioIcds.ResponseValues>() {
                    @Override
                    public void onSuccess(ShowDesempenioIcds.ResponseValues response) {
                        validationView("Indicadores ", response.getDesempenioList(), -1);
                        //  onSingleItemSelected("Campos Tematicos", response.getDesempenioList(), -1);
                        Log.d(TAG, "initBimencionalLista : " + response.getDesempenioList().size());
                        /*if (response.getDesempenioList().size() > 0) {

                        } else {
                            Log.d(TAG, "No hay Items");
                        }*/
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    private void validationView(String tituloDialog, List<Object> objectList, int position) {
        if (view != null) {
            view.onShowListSingleChooser(tituloDialog, objectList, -1);
        }
    }

    @Override
    public void actualizarListaRubricas() {

    }

    @Override
    public void onClickSelectRubros(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickAncla(CapacidadUi capacidadUi) {

    }

    @Override
    public void onAceptarDialogDesanAnclar(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickBtnAceptarPublicarTodoEvaluaciones(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        boolean success = publicarTodasEvaluacion.execute(rubroProcesoUi.getKey());
        if(success){
            rubroProcesoUi.setPublicarEval(RubroProcesoUi.PublicarEval.TODOS);
            if(view!=null)view.updateRubroProceso(capacidadUi, rubroProcesoUi, programaEducativoId);
            if(view!=null)view.succesChangePublicar(rubroProcesoUi, programaEducativoId);
        }
    }

    @Override
    public void onClickAceptarCalcular() {

    }
}
