package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.EditarFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetCamposAccionRubro;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetFormulaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoNotaRubrica;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PrecisionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoFormulaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoRedondeadoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoFormulaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoRedondeoList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.SavedRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CCIE on 26/03/2018.
 */

public class CrearRubroFormulaPresenterImpl extends BasePresenterImpl<CrearRubroFormulaView> implements CrearRubroFormulaPresenter {

    private static String CREAR_RUBRO_FORMULA_TAG = CrearRubroFormulaPresenterImpl.class.getSimpleName();

    private CapacidadUi capacidadUi;
    private List<RubroProcesoUi> evaluacionProcesoUiList;
    private GetTipoFormulaList getTipoFormulaList;
    private GetTipoNotaList getTipoNotaList;
    private GetTipoRedondeoList getTipoRedondeoList;
    private SavedRubroFormula savedRubroEvaluacionProceso;
    private GetCamposAccionRubro getCamposAccionRubro;
    private int cargaCursoId;
    private GetTipoNotaDefault getTipoNotaDefault;
    private GetNotaPresicion getNotaPresicion;
    private GetTipoNotaRubrica getTipoNotaRubrica;
    private String rubroFormulaId;
    private GetRubroProceso getRubroProceso;
    private GetFormulaList getFormulaList;
    private EditarFormula editarFormula;

    public CrearRubroFormulaPresenterImpl(UseCaseHandler handler, Resources res, GetTipoFormulaList getTipoFormulaList, GetTipoNotaList getTipoNotaList, GetTipoRedondeoList getTipoRedondeoList, SavedRubroFormula savedRubroEvaluacionProceso, GetCamposAccionRubro getCamposAccionRubro,GetTipoNotaDefault getTipoNotaDefault, GetNotaPresicion getNotaPresicion, GetTipoNotaRubrica getTipoNotaRubrica, GetRubroProceso getRubroProceso, GetFormulaList getFormulaList, EditarFormula editarFormula) {
        super(handler, res);
        this.getTipoFormulaList = getTipoFormulaList;
        this.getTipoNotaList = getTipoNotaList;
        this.getTipoRedondeoList = getTipoRedondeoList;
        this.savedRubroEvaluacionProceso = savedRubroEvaluacionProceso;
        this.getCamposAccionRubro = getCamposAccionRubro;
        this.getTipoNotaDefault = getTipoNotaDefault;
        this.getNotaPresicion = getNotaPresicion;
        this.getTipoNotaRubrica = getTipoNotaRubrica;
        this.getRubroProceso = getRubroProceso;
        this.getFormulaList = getFormulaList;
        this.editarFormula = editarFormula;
    }

    int programaEducatId;

    @Override
    public void setExtras(Bundle extras) {

        if (extras != null) {
            List<RubroProcesoUi> listaddChecked = Parcels.unwrap(extras.getParcelable("listaRubros"));
            CapacidadUi rubroEvaluacionResultadoUi = Parcels.unwrap(extras.getParcelable("rubroEvaluacionResul"));
            int programaEducativoId = extras.getInt("programaEduId");
            this.capacidadUi = rubroEvaluacionResultadoUi;
            this.evaluacionProcesoUiList = listaddChecked;
            this.programaEducatId = programaEducativoId;
            this.cargaCursoId = extras.getInt("cargaCursoId");
            this.rubroFormulaId = extras.getString("rubroFormulaId");


            Log.d(CREAR_RUBRO_FORMULA_TAG, "cargaCursoId : " + cargaCursoId);
            Log.d(CREAR_RUBRO_FORMULA_TAG, "programaEducativoId : " + programaEducativoId);
            //Log.d(CREAR_RUBRO_FORMULA_TAG, "countListChecked : " + evaluacionProcesoUiList.size() + "");
        }
    }

    private List<RubroProcesoUi> getFormulaList(String rubroFormulaId) {
        return getFormulaList.execute(rubroFormulaId);
    }

    private void getValorRedondeo(final int tipoValorRedondeoId) {
        handler.execute(getTipoRedondeoList,
                new GetTipoRedondeoList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoRedondeoList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoRedondeoList.ResponseValue response) {

                        for (TipoRedondeadoUi tipoUi : response.getTipoRedondeadoUiList()){
                            if(tipoUi.getIdTipo() == tipoValorRedondeoId){
                                if(view!=null)view.showTipoRedondeadoSelected(tipoUi.getNombre());
                            }
                        }

                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void getTipoFormulaList(final int tipoFormulaId) {
        handler.execute(getTipoFormulaList,
                new GetTipoFormulaList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoFormulaList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoFormulaList.ResponseValue response) {
                        try {
                            for (TipoFormulaUi tipoFormulaUi: response.getFormulaUiList()){
                                if(tipoFormulaId == tipoFormulaUi.getIdTipo()){

                                    if (view!=null)view.showTipoFormulaSelected(tipoFormulaUi.getNombre());

                                    showTipoFormulaSelected(tipoFormulaUi);

                                    if(view!=null)view.disabledListFormula();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private TipoNotaUi tipoNotaSelected;
    private TipoFormulaUi tipoFormulaSelected;
    private TipoRedondeadoUi tipoRedondeadoSelected;

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        Log.d(CREAR_RUBRO_FORMULA_TAG, "onSingleItemSelected: " + singleItem);
        if (singleItem instanceof TipoFormulaUi) {
            tipoFormulaSelected = (TipoFormulaUi) singleItem;
            for (TipoFormulaUi tipoFormulaUi : tipoFormulaUiList) {
                if (tipoFormulaUi.getNombre().equals(tipoFormulaSelected.getNombre())) {
                    tipoFormulaSelected = tipoFormulaUi;
                    break;
                }
            }

            if (tipoFormulaSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            showTipoFormulaSelected(tipoFormulaSelected);
        } else if (singleItem instanceof TipoRedondeadoUi) {
            tipoRedondeadoSelected = (TipoRedondeadoUi) singleItem;
            for (TipoRedondeadoUi tipoRedondeadoUi : tipoRedondeadoUiList) {
                if (tipoRedondeadoUi.getNombre().equals(tipoRedondeadoSelected.getNombre())) {
                    tipoRedondeadoSelected = tipoRedondeadoUi;
                    break;
                }
            }

            if (tipoRedondeadoSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            showTipoRedondeadoSelected(tipoRedondeadoSelected);
        }
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    private void showTipoRedondeadoSelected(TipoRedondeadoUi tipoRedondeadoSelected) {
        if (view != null && tipoRedondeadoSelected != null) {
            capacidadUi.getTipoRedondeadoUi().setIdTipo(tipoRedondeadoSelected.getIdTipo());
            capacidadUi.getTipoRedondeadoUi().setNombre(tipoRedondeadoSelected.getNombre());
            view.showTipoRedondeadoSelected(tipoRedondeadoSelected.getNombre());
        }
    }

    private double countPeso = 0.0;

    private void showTipoFormulaSelected(TipoFormulaUi tipoFormulaSelected) {
        if (tipoFormulaSelected != null) {
            capacidadUi.getTipoFormulaUi().setIdTipo(tipoFormulaSelected.getIdTipo());
            capacidadUi.getTipoFormulaUi().setNombre(tipoFormulaSelected.getNombre());
            if (view!=null)view.showTipoFormulaSelected(tipoFormulaSelected.getNombre());

            Log.d(getTag(), "tipoFormulaUi.getIdTipo() = " + tipoFormulaSelected.getIdTipo());

            if (tipoFormulaSelected.getIdTipo() == 413) {

                int totalPeso = 100;

                int cantidad = evaluacionProcesoUiList.size();
                int subtotalPeso =  (int) totalPeso/cantidad;
                int diferencia = totalPeso - (subtotalPeso * cantidad);


                for (RubroProcesoUi rubroEvaluacionProcesoUi : evaluacionProcesoUiList) {
                    // Log.d(TAG, "evaluacionProcesoUiList :" + evaluacionProcesoUiList.size());
                    rubroEvaluacionProcesoUi.setPeso(subtotalPeso);
                }

                for (int i = 0; i < diferencia; i++) {
                    RubroProcesoUi rubroEvaluacionProcesoUi = evaluacionProcesoUiList.get(i);
                    double peso = rubroEvaluacionProcesoUi.getPeso()+1;
                    rubroEvaluacionProcesoUi.setPeso(peso);
                }


                if(view!=null)view.showPesoRubro(totalPeso);
                showListCheckedRubros(capacidadUi, evaluacionProcesoUiList);

            } else {
                for (RubroProcesoUi rubroEvaluacionProcesoUi : evaluacionProcesoUiList) {
                    rubroEvaluacionProcesoUi.setPeso(0.0);
                    countPeso += rubroEvaluacionProcesoUi.getPeso();
                }

                if(view!=null)view.hidePesoRubro(countPeso);
                if(view!=null)view.showListCheckedRubros(capacidadUi, evaluacionProcesoUiList,false);
                if(view!=null)view.showMessageWeigth(countPeso);


                //  textViewHidePeso.setVisibility(View.GONE);
                // Log.d(TAG, "Other :");

            }
        }

    }

    private void showListCheckedRubros(CapacidadUi capacidadUi, List<RubroProcesoUi> evaluacionProcesoUiList) {
        /*List<RubroProcesoUi> rubroProcesoUiList =  new ArrayList<>();
        for (RubroProcesoUi rubroProcesoUi: evaluacionProcesoUiList){
            EditPesoUi editPesoUi = new EditPesoUi(rubroProcesoUi);
            rubroProcesoUiList.add(rubroProcesoUi);
            rubroProcesoUiList.add(editPesoUi);
        }*/
        if (view != null)view.showListCheckedRubros(capacidadUi, evaluacionProcesoUiList,true);

    }

    private void showTipoNotaSelected(TipoNotaUi tipoNota) {
        if (view != null) view.showTipoNotaSelected(tipoNota);
    }



    @Override
    protected String getTag() {
        return CREAR_RUBRO_FORMULA_TAG;
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        view=null;
        super.onDestroy();
    }

    private List<TipoRedondeadoUi> tipoRedondeadoUiList;
    private List<TipoNotaUi> tipoNotaUiList;
    private List<TipoFormulaUi> tipoFormulaUiList;

    private void getTipoRedondeoSpinner() {
        handler.execute(getTipoRedondeoList,
                new GetTipoRedondeoList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoRedondeoList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoRedondeoList.ResponseValue response) {
                        if (!response.getTipoRedondeadoUiList().isEmpty()) {
                            tipoRedondeadoUiList = response.getTipoRedondeadoUiList();
                            try {
                                for (TipoRedondeadoUi tipoUi : tipoRedondeadoUiList){
                                    if(tipoUi.getIdTipo() == 425){
                                        tipoRedondeadoSelected = tipoUi;
                                        tipoRedondeadoSelected.setNombre("Sin redondeo");
                                        showTipoRedondeadoSelected(tipoRedondeadoSelected);
                                        break;
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }


    private void getTipoNotaListSpinner() {
       /* handler.execute(getTipoNotaList,
                new GetTipoNotaList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoNotaList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoNotaList.ResponseValue response) {

                        //  rubroProcesoUi.getRubrosAsociadosUiList() == null || rubroProcesoUi.getRubrosAsociadosUiList().isEmpty()
                        if (!response.getTipoNotaUiList().isEmpty()) {
                            tipoNotaUiList = response.getTipoNotaUiList();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );*/
    }

    private void getTipoFormulaListSpinner() {
        handler.execute(getTipoFormulaList,
                new GetTipoFormulaList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoFormulaList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoFormulaList.ResponseValue response) {
                        try {
                            tipoFormulaUiList = response.getFormulaUiList();

                            for (TipoFormulaUi tipoFormulaUi: tipoFormulaUiList){
                                if(tipoFormulaUi.isSelected()){
                                    tipoFormulaSelected= tipoFormulaUi;
                                    break;
                                }
                            }

                            if(tipoFormulaSelected==null)tipoFormulaSelected = tipoFormulaUiList.get(0);

                            showTipoFormulaSelected(tipoFormulaSelected);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }


    @Override
    public void onBtnTipoFormulaClicked() {

        if(!TextUtils.isEmpty(rubroFormulaId))return;

        int posicion = -1;
        try {
            posicion = tipoFormulaUiList.indexOf(tipoFormulaSelected);

        }catch (Exception e){
            e.printStackTrace();
        }
        if(view !=null)view.showListSingleChooser("Tipo fórmula ", new ArrayList<Object>(tipoFormulaUiList), posicion);
    }

    @Override
    public void onBtnTipoNotaClicked() {

        if(!TextUtils.isEmpty(rubroFormulaId))return;

        showTipoNotaSingleChooser();
      /*  if (tipoNotaUiList == null || tipoNotaUiList.isEmpty()) {
            Log.d(CREAR_RUBRO_FORMULA_TAG, "No Hay Items : ");
        } else {
            List<Object> items = new ArrayList<>();
            items.addAll(tipoNotaUiList);
            //  if (view != null) view.showListSingleChooser("Tipo Nota", items, -1);
            showTipoNotaSingleChooser();
        }*/
        /*List<Object> items = new ArrayList<>();
        items.addAll(tipoNotaUiList);
        if (items == null || items.isEmpty()) {

        } else if (items.size() > 0 ) {
            if (view != null) view.showListSingleChooser("Tipo Nota", items, -1);
        }*/
    }

    private void showTipoNotaSingleChooser() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
//            items.addAll(tipoNotaUiList);
            view.hideProgress();//res.getString(R.string.createrubbid_tiponota_dialog_title)
            view.mostrarListaTipoNota("Nivel de logro", items, -1, programaEducatId);
        }
    }

    @Override
    public void onBtnTipoValorRedondeoClicked() {
        if(!TextUtils.isEmpty(rubroFormulaId))return;
        int posicion = -1;
        try {
            posicion = tipoRedondeadoUiList.indexOf(tipoRedondeadoSelected);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(view !=null)view.showListSingleChooser("Valor redondeado", new ArrayList<Object>(tipoRedondeadoUiList), posicion);
    }

    @Override
    public void onSelectClickRubroAsociados(RubroProcesoUi rubroEvaluacionProcesoUi) {
        initShowDialogPeso(rubroEvaluacionProcesoUi);
    }

    double pesoGlobal = 100;

    @Override
    public void onRubroEvaluacionProcesoSaved(RubroProcesoUi rubroEvaluacionProcesoUiPeso) {
        double countPeso = 0.0;
        // if (view != null) {

        if (evaluacionProcesoUiList != null) {
            for (int i = 0; i < evaluacionProcesoUiList.size(); i++) {
                if (evaluacionProcesoUiList.get(i).isCheck()) {
                    countPeso += evaluacionProcesoUiList.get(i).getPeso();
                    pesoGlobal = countPeso;
                }
            }
        }
        if (countPeso > 100) {
            //Aqui Cuando el limite excede
            if (view != null) view.showMessageLimiteWeigth(countPeso);
        } else {
            if (view != null) {
                view.showMessageWeigth(countPeso);
                view.showPesoRubro(countPeso);
            }
        }
        //if (view != null) view.showListCheckedRubros(evaluacionProcesoUiList);
        // }
    }

    @Override
    public void onSelectButtonAceptar(RubroProcesoUi rubroEvaluacionProcesoUi) {

        if(TextUtils.isEmpty(rubroFormulaId)){
            if (rubroEvaluacionProcesoUi.getTitulo().equals("")) {
                if(view!=null)view.showError("Ingresé el titulo");
            } else if(tipoNotaSelected==null){
                if(view!=null)view.showError("Seleccione el nivel de logro");
            }else {
                initSavedItem(rubroEvaluacionProcesoUi);
            }
        }else {
            if (rubroEvaluacionProcesoUi.getTitulo().equals("")) {
                if(view!=null)view.showError("Ingresé el titulo");
            }else {
                initEditFormula(rubroFormulaId, rubroEvaluacionProcesoUi);
            }
        }

    }

    private void initEditFormula(String rubroFormulaId, RubroProcesoUi rubroEvaluacionProcesoUi) {
        rubroEvaluacionProcesoUi.setKey(rubroFormulaId);
        boolean success = editarFormula.execute(rubroEvaluacionProcesoUi);
        if(success){
            if(view!=null)view.onSincronizate(programaEducatId);
            if(view!=null)view.success();
        }else {
            if(view!=null)view.showMessage("Error");
        }

    }

    @Override
    public void onActualizarItemTipoNota(String tipoNotaId) {
        if (!TextUtils.isEmpty(tipoNotaId)) {
            Log.d(getTag(), "tipoNotaId: " + tipoNotaId);
            getTipoNotaGlobal(tipoNotaId);
        } else {
            tipoNotaSelected = null;
            showImportantMessage(res.getString(R.string.unknown_error));
        }
    }

    @Override
    public void onTextChangedRubroAsociados(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence peso) {
        try {
            if(evaluacionProcesoUiList.size() >= 1){
                rubroEvaluacionProcesoUi.setPeso(Double.parseDouble(peso.toString()));
            }else {
                rubroEvaluacionProcesoUi.setPeso(100D);
            }
        }catch (Exception e){
            rubroEvaluacionProcesoUi.setPeso(0D);
        }
        onRubroEvaluacionProcesoSaved(rubroEvaluacionProcesoUi);
    }

    @Override
    public void onDeleteRubroEval(RubroProcesoUi rubroProcesoUi) {
        if(evaluacionProcesoUiList == null || evaluacionProcesoUiList.isEmpty())return;
        if(evaluacionProcesoUiList.size() > 1){
            evaluacionProcesoUiList.remove(rubroProcesoUi);

            if(tipoFormulaSelected != null){
                if (tipoFormulaSelected.getIdTipo() == 413) {
                    onRubroEvaluacionProcesoSaved(rubroProcesoUi);
                }
            }

            removerRubro(rubroProcesoUi);
            //showMessageRemoved();

        }else {
            changeListaRubros();
            showMessage(res.getString(R.string.crear_rubro_formula_limite));
        }

    }

    @Override
    public void onClikInfoTipoNota() {
        if(tipoNotaSelected!=null)if(view!=null)view.showDialogInfoTipoNota("Promedio de logro", tipoNotaSelected);
    }

    private void changeListaRubros() {
        if(view!=null)view.changeListaRubros();
    }

    private void removerRubro(RubroProcesoUi rubroProcesoUi) {
        if(view!=null)view.removerRubro(rubroProcesoUi);
    }

    private void showMessageRemoved() {
        if (view != null) {
            view.showMessage(res.getString(R.string.crear_rubro_formula_removed));
        }
    }

    private void showMessage(String mensaje) {
        if (view != null) {
            view.showMessage(mensaje);
        }
    }

    private void getTipoNotaGlobal(String tipoNotaId){
        tipoNotaSelected = getTipoNotaRubrica.execute(tipoNotaId);
        if(tipoNotaSelected!=null){
            showTipoNotaSelected(tipoNotaSelected);
            getPrescion(tipoNotaSelected);
        }else {
            showImportantMessage(res.getString(R.string.unknown_error));
        }
    }

    /*AQUIII GUARDA!!!*/
    private void initSavedItem(RubroProcesoUi rubroEvaluacionProcesoUi) {
        if (rubroEvaluacionProcesoUi != null) {
            int currentColorCircle = -9820580; //Color TipoFormula-
            //  int idResultado= Integer.parseInt(rubroEvaluacionResultadoUi.getIdRubroEvaluacionResultado());
            rubroEvaluacionProcesoUi.setTipoFormulaId(capacidadUi.getTipoFormulaUi().getIdTipo());
            rubroEvaluacionProcesoUi.setTipoValorRedondeoId(capacidadUi.getTipoRedondeadoUi().getIdTipo());
            rubroEvaluacionProcesoUi.setTipoEscalaEvaluacionId(capacidadUi.getTipoEscalaEvaluacionUi().getIdTipo());
            rubroEvaluacionProcesoUi.setColorRubro(currentColorCircle);
            rubroEvaluacionProcesoUi.setTipoEvaluacionId(capacidadUi.getTipoEvaluacionUi().getIdTipo());
            rubroEvaluacionProcesoUi.setCapacidadId(capacidadUi.getId());
            rubroEvaluacionProcesoUi.setCalendarioPeriodId(capacidadUi.getCalendarioId());
            rubroEvaluacionProcesoUi.setSilaboEventoId(capacidadUi.getSilaboEventoId());
            if(tipoNotaSelected!=null)rubroEvaluacionProcesoUi.setTipoNotaId(tipoNotaSelected.getIdTipoNota());
            //rubroEvaluacionProcesoUi.setTipoId(capacidadUi.getTipoNotaUi().getIdTipo());
            rubroEvaluacionProcesoUi.setFormEvaluacion(RubroProcesoUi.FormEvaluacion.FORMULA);
            //rubroEvaluacionProcesoUi.setIdRubroEvalResultado(idResultado);
            /*FALTAA*///rubroEvaluacionProcesoUi.setColorCondicionalUis(view.abstraerColoreCondicionales());
            rubroEvaluacionProcesoUi.setFecha(Utils.f_fecha_letras(new Date().getTime()));
//            rubroEvaluacionProcesoUi.setItemCircleUis();

            //rubroEvaluacionProcesoUi.setItemCircleUis(view.getListRubrosAsociados(););
            if (rubroEvaluacionProcesoUi.getTipoFormulaId() == 0 && rubroEvaluacionProcesoUi.getTipoValorRedondeoId() == 0 && rubroEvaluacionProcesoUi.getTipoEscalaEvaluacionId() == 0) {
                if (view != null) {
                    view.showError("Eliga los tipos");
                }
            } else {
                if (rubroEvaluacionProcesoUi.getTipoFormulaId() == 413) {
                    // saveRubroEvalRnpFormula(rubroEvaluacionProcesoUi);
                    if (pesoGlobal > 100) {
                        view.showError("Revise el peso total");
                    } else if (pesoGlobal < 100) {
                        view.showError("Revise el peso total");
                    } else if (pesoGlobal == 100) {
                        saveRubroEvalProceso(rubroEvaluacionProcesoUi);
                    }
                } else {
                    saveRubroEvalProceso(rubroEvaluacionProcesoUi);
                }
            }

        }
    }

    private void saveRubroEvalProceso(final RubroProcesoUi rubroEvaluacionProcesoUi) {
        // Log.d(TAG, "saveRubroEvalProceso : " + rubroEvaluacionProcesoUi.getTitulo());
        if(view!=null)view.showDialogProgress();
        savedRubroEvaluacionProceso.execute(new SavedRubroFormula.RequestValues(rubroEvaluacionProcesoUi, evaluacionProcesoUiList, cargaCursoId), new UseCaseSincrono.Callback<SavedRubroFormula.ResponseValue>() {
            @Override
            public void onResponse(boolean success, SavedRubroFormula.ResponseValue response) {
                if(success){
                    //  Log.d(CREAR_RUBRO_FORMULA_TAG,"LISTARUBROSASOCIADOS :"+response.getRubroEvaluacionProcesoUi().getRubrosAsociadosUiList().size()+"");
                    //  listener.onClickAceptarDialogFormula(capacidadUi, response.getRubroEvaluacionProcesoUi());
                    if (response != null){
                        if(view!=null)view.onSincronizate(programaEducatId);
                        if(view!=null)view.success(capacidadUi, response.getRubroEvaluacionProcesoUi());
                    }
                }else {
                    if (view != null)
                        view.showError("Error al registrar los datos correctamente");
                }

                if(view!=null)view.hideDialogProgress();
            }
        });
    }

    private void initShowDialogPeso(RubroProcesoUi rubroEvaluacionResultadoUi) {
        if (view != null) {
            view.showDialogEditPeso(rubroEvaluacionResultadoUi);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();

        if(!TextUtils.isEmpty(rubroFormulaId)){
            handler.execute(getRubroProceso, new GetRubroProceso.RequestValues(rubroFormulaId), new UseCase.UseCaseCallback<GetRubroProceso.ResponseValue>() {
                @Override
                public void onSuccess(GetRubroProceso.ResponseValue response) {
                    Log.d(getTag(), "onSuccess" );
                    RubroProcesoUi rubroProcesoUi = response.getRubroProcesoUi();
                    if(view!=null)view.setNombreFormula(rubroProcesoUi.getTitulo());

                    evaluacionProcesoUiList =  getFormulaList(rubroFormulaId);
                    capacidadUi = new CapacidadUi();
                    capacidadUi.setTipoFormulaUi(new TipoFormulaUi());
                    getCamposAccionRubro();
                    getTipoNotaGlobal(rubroProcesoUi.getTipoNotaId());
                    getTipoFormulaList(rubroProcesoUi.getTipoFormulaId());
                    getValorRedondeo(rubroProcesoUi.getTipoValorRedondeoId());

                    //

                }

                @Override
                public void onError() {

                }
            });

        }else {
            getCamposAccionRubro();
            getTipoNotaGlobalDefault();
            getTipoFormulaListSpinner();
            getTipoRedondeoSpinner();
        }

    }

    private void getCamposAccionRubro(){
        handler.execute(getCamposAccionRubro, new GetCamposAccionRubro.RequestValues(evaluacionProcesoUiList),
                new UseCase.UseCaseCallback<GetCamposAccionRubro.ResponseValues>() {
                    @Override
                    public void onSuccess(GetCamposAccionRubro.ResponseValues response) {
                        view.showListCheckedRubros(capacidadUi, evaluacionProcesoUiList,false);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void getTipoNotaGlobalDefault(){
        List<TipoNotaUi.Tipo> tipoList = new ArrayList<>();
        tipoList.add(TipoNotaUi.Tipo.SELECTOR_ICONOS);
        tipoList.add(TipoNotaUi.Tipo.SELECTOR_VALORES);
        tipoList.add(TipoNotaUi.Tipo.SELECTOR_NUMERICO);
        TipoNotaUi tipoNotaUi = getTipoNotaDefault.executeUseCase(new GetTipoNotaDefault.RequestValues(programaEducatId,tipoList));

        if(tipoNotaUi!=null){
            getPrescion(tipoNotaUi);
            showTipoNotaSelected(tipoNotaUi);
            Log.e(getTag(), "getTipoNota onSuccess");
            tipoNotaSelected = tipoNotaUi;
            //getTipoNota(tipoNotaUi.getId(),inicio);
        }else {
            showImportantMessage(res.getString(R.string.createrubbid_error_getting_tiponota));
        }

    }

    private void getPrescion(TipoNotaUi tipoNotaUi) {
        if(tipoNotaUi.getValorTipoNotaList()!=null)
            for (ValorTipoNotaUi valorTipoNotaUi : tipoNotaUi.getValorTipoNotaList()){

                List<PrecisionUi> precisionUis = getNotaPresicion.execute(new GetNotaPresicion.RequestValues(
                        valorTipoNotaUi.getColor(),
                        (int)valorTipoNotaUi.isLimiteSuperior(),
                        (int)valorTipoNotaUi.isLimiteInferior(),
                        valorTipoNotaUi.isIncluidoLSuperior(),
                        valorTipoNotaUi.isIncluidoLInferior()));

                valorTipoNotaUi.setPrecisionList(precisionUis);
            }
    }
}
