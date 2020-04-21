package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui.EvaluacionGlobalFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui.EvaluacionGlobalView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 15/11/2017.
 */

public class EvaluacionGlobalPresenterImpl implements EvaluacionGlobalPresenter {

    private static final String TAG = EvaluacionGlobalPresenterImpl.class.getSimpleName();
    private EvaluacionGlobalView view;
    private int sesionAprendizajeId;
    private RubroEvaluacionUi rubroEvaluacionUi;
    private AlumnosEvaluacionUi alumnosEvaluacionUi;
    private SelectorNumericoUi selectorNumericoUi;
    private List<AlumnosEvaluacionUi> alumnosEvaluacionUiList;
    private List<String> listStringValues;
    private List<Double> listDoubleValues;
    private UseCaseHandler useCaseHandler;
    private SaveAlumnoEvaluacion saveAlumnoEvaluacion;
    private int posicionNota;

    public EvaluacionGlobalPresenterImpl(UseCaseHandler useCaseHandler, SaveAlumnoEvaluacion saveAlumnoEvaluacion) {
        this.useCaseHandler = useCaseHandler;
        this.saveAlumnoEvaluacion = saveAlumnoEvaluacion;
    }

    @Override
    public void attachView(EvaluacionGlobalView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");

        changeAlumnoEvaluacion();
    }

    private void changeAlumnoEvaluacion() {
        setCabecera();
        setNumberPicker();
        selecionarNota();
    }

    private void setCabecera() {
        if (view!=null)view.showName(alumnosEvaluacionUi.getName());
        if (view!=null)view.showImageProfile(alumnosEvaluacionUi.getFotoPerfil());
        if (view!=null)view.setHeaderTitle(rubroEvaluacionUi.getTitulo());
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {

    }

    private void selecionarNota() {

    }

    private void setNumberPicker() {

        double longitudPaso = selectorNumericoUi.getLongitudPaso();
        double valorMaximo = selectorNumericoUi.getValorMaximo();
        double valorMinimo = selectorNumericoUi.getValorMinino();

        this.listDoubleValues = new ArrayList<>();
        this.listStringValues = new ArrayList<>();
        Log.d(TAG, "valorMaximo: "+ valorMaximo + "valorMinimo: "+ valorMinimo);

        if(longitudPaso < 0){
            longitudPaso = 1.0;
        }
        //20 > 0 true
        while (valorMaximo > valorMinimo){
            listDoubleValues.add(valorMinimo);
            listStringValues.add(String.valueOf(valorMinimo));
            valorMinimo = valorMinimo + longitudPaso;
        }
        
        listDoubleValues.add(valorMaximo);
        listStringValues.add(String.valueOf(valorMaximo));

        String[] values = listStringValues.toArray(new String[listStringValues.size()]);
        if (view!=null)view.setupNumberPicker(values);
        
        
        NotaUi notaUi = alumnosEvaluacionUi.getNotaUi();
        if(notaUi == null)return;
        for (double itemValue: this.listDoubleValues) {

        }




        //this.posicionNota = poscion;
        //view.selectNumberPickert(poscion);



        Log.d(TAG, "listDoubleValues: "+ listDoubleValues.size());
        Log.d(TAG, "listStringValues: "+listStringValues.size() );
        Log.d(TAG, "values: "+ values.length);

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }

    @Override
    public void onAttach() {
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreateView() {
        Log.d(TAG, "onCreateView");
    }

    @Override
    public void onViewCreated() {
        Log.d(TAG, "onViewCreated");
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
    public void setExtras() {
        this.rubroEvaluacionUi = EvaluacionGlobalFragment._rubroEvaluacionUi;
        this.sesionAprendizajeId = EvaluacionGlobalFragment._sesionAprendizajeId;
        this.alumnosEvaluacionUi = EvaluacionGlobalFragment._alumnosEvaluacionUi;
        this.selectorNumericoUi = EvaluacionGlobalFragment._selectorNumericoUi;
        this.alumnosEvaluacionUiList = EvaluacionGlobalFragment._alumnosEvaluacionUiList;
    }

    @Override
    public void btnFinalizar() {
        saveRubroEvaluacion();
        if (view!=null)view.hideDialogo();
    }

    @Override
    public void btnRetroceder() {
        saveRubroEvaluacion();
        changePosicion(-1);
    }

    @Override
    public void btnAvanzar() {
        saveRubroEvaluacion();
        changePosicion(1);
    }

    private void changePosicion(int posicion) {

        int posicionAlumno = this.alumnosEvaluacionUiList.indexOf(alumnosEvaluacionUi);
        if(posicionAlumno == -1)return;
        AlumnosEvaluacionUi alumnosEvaluacionUi = null;
        try {
            alumnosEvaluacionUi = this.alumnosEvaluacionUiList.get(posicionAlumno + posicion);
            this.alumnosEvaluacionUi = alumnosEvaluacionUi;
            this.selectorNumericoUi = (SelectorNumericoUi) alumnosEvaluacionUi.getNotaUis().get(0);
            changeAlumnoEvaluacion();

            if(view == null)return;
            switch (posicion){
                case -1:
                    view.showBtnNextTextColor();
                    break;
                case 1:
                    view.showBtnRetroTextColor();
                    break;
            }

        }catch (Exception e){
            if(view == null)return;
            switch (posicion){
                case -1:
                    view.hideBtnRetroTextColor();
                    break;
                case 1:
                    view.hideBtnNextTextColor();
                    break;
            }
        }
    }

    @Override
    public void setOrderType(int orderType) {
    }

    @Override
    public void onValueChange(int newVal,int oldVal) {
        this.posicionNota = newVal;
    }

    private void saveRubroEvaluacion(){
        double nota = this.listDoubleValues.get(posicionNota);
        selectorNumericoUi.setNota(nota);
        selectorNumericoUi.setResaltar(true);
        alumnosEvaluacionUi.setNotaUi(selectorNumericoUi);
        saveAlumnoEvaluacion.execute(new SaveAlumnoEvaluacion.RequestValues(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi), new UseCaseSincrono.Callback<SaveAlumnoEvaluacion.ResponseValue>() {
            @Override
            public void onResponse(boolean success, SaveAlumnoEvaluacion.ResponseValue value) {
                Log.d(TAG, "onSuccess " + success);
            }
        });
    }
}
