package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.entities.AlumnosEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveGrupoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui.EvaluacionGlobalFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.ui.EvaluacionGlobalView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.ui.EvaluacionTecladoNumericoFragment;

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
    private Object alumnosEvaluacionUi;
    private SelectorNumericoUi selectorNumericoUi;
    private List<Object> alumnosEvaluacionUiList;
    private List<String> listStringValues;
    private List<Double> listDoubleValues;
    private UseCaseHandler useCaseHandler;
    private SaveAlumnoEvaluacion saveAlumnoEvaluacion;
    private SaveGrupoEvaluacion saveGrupoEvaluacion;
    private String notaText = "";
    private double valorMaximo;
    private double valorMinimo;

    public EvaluacionGlobalPresenterImpl(UseCaseHandler useCaseHandler, SaveAlumnoEvaluacion saveAlumnoEvaluacion, SaveGrupoEvaluacion saveGrupoEvaluacion) {
        this.useCaseHandler = useCaseHandler;
        this.saveAlumnoEvaluacion = saveAlumnoEvaluacion;
        this.saveGrupoEvaluacion = saveGrupoEvaluacion;
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
        setNota();
    }

    private void setNota() {
        if(view == null) return;
        view.showNotaError("");
        notaText = "";
        if(alumnosEvaluacionUi instanceof AlumnosEvaluacionUi){
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
            selectorNumericoUi= (SelectorNumericoUi) alumnosEvaluacionUi.getNotaUi();
            if(selectorNumericoUi != null){
                view.showNota(String.valueOf(selectorNumericoUi.getNota()));
            }else{
                view.showNota("0");
            }

        }else {



            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) this.alumnosEvaluacionUi;
            selectorNumericoUi = (SelectorNumericoUi) grupoEvaluacionUi.getNotaUi();
            if(selectorNumericoUi == null)return;
            view.showNota(String.valueOf(selectorNumericoUi.getNota()));
        }

    }

    private void setCabecera() {
        if(view == null)return;
        if(alumnosEvaluacionUi instanceof AlumnosEvaluacionUi){
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
            view.showName(alumnosEvaluacionUi.getName(), alumnosEvaluacionUi.getLastName());
            view.showImageProfile(alumnosEvaluacionUi.getFotoPerfil());
            view.setHeaderTitle(rubroEvaluacionUi.getTitulo());
        }else {
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) this.alumnosEvaluacionUi;
            view.showName(grupoEvaluacionUi.getName(), "");
            //view.showImageProfile(grupoEvaluacionUi.getFotoPerfil());
            view.setHeaderTitle(grupoEvaluacionUi.getName());
        }

        if(selectorNumericoUi == null)return;
        valorMaximo = selectorNumericoUi.getValorMaximo();
        valorMinimo = selectorNumericoUi.getValorMinino();
        view.showRangoEvaluacion(valorMinimo,valorMaximo);

    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {

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
        this.rubroEvaluacionUi = EvaluacionTecladoNumericoFragment._rubroEvaluacionUi;
        this.sesionAprendizajeId = EvaluacionTecladoNumericoFragment._sesionAprendizajeId;
        this.alumnosEvaluacionUi = EvaluacionTecladoNumericoFragment._alumnosEvaluacionUi;
        this.selectorNumericoUi = EvaluacionTecladoNumericoFragment._selectorNumericoUi;
        this.alumnosEvaluacionUiList = EvaluacionTecladoNumericoFragment._alumnosEvaluacionUiList;
    }

    @Override
    public void btnFinalizar() {
        castSaveRubroEvaluacion();
        if(view == null) return;
        view.hideDialogo();
    }

    private void castSaveRubroEvaluacion() {
        if(alumnosEvaluacionUi instanceof AlumnosEvaluacion){
            AlumnosEvaluacionUi alumnosEvaluacion = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
            if(alumnosEvaluacion.getGrupoId().isEmpty()){
                saveRubroEvaluacion();
            }else {
                saveRubroEvaluacionGrupo();
            }
        }else {
            saveRubroEvaluacionGrupo();
        }
    }

    @Override
    public void btnRetroceder() {
        castSaveRubroEvaluacion();
        changePosicion(-1, alumnosEvaluacionUi);
    }

    @Override
    public void btnAvanzar() {
        castSaveRubroEvaluacion();
        changePosicion(1, alumnosEvaluacionUi);
    }

    private void changePosicion(int posicion, Object item) {
        int posicionAlumno = this.alumnosEvaluacionUiList.indexOf(item);
        if(posicionAlumno == -1)return;
        try {
            item = this.alumnosEvaluacionUiList.get(posicionAlumno + posicion);
            this.alumnosEvaluacionUi = item;
            if(alumnosEvaluacionUi instanceof AlumnosEvaluacionUi){
                AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
                this.selectorNumericoUi = (SelectorNumericoUi) alumnosEvaluacionUi.getNotaUis().get(0);
            }else {
                GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) this.alumnosEvaluacionUi;
                this.selectorNumericoUi = (SelectorNumericoUi) grupoEvaluacionUi.getNotaUis().get(0);
            }

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
            view.hideDialogo();
            /*switch (posicion){
                case -1:
                    view.hideBtnRetroTextColor();
                    break;
                case 1:
                    view.hideBtnNextTextColor();
                    break;
            }*/
        }

        changeAlumnoEvaluacion();
    }

    @Override
    public void setOrderType(int orderType) {

    }

    @Override
    public void onBtnClicked(String number) {
        switch (number){
            case EvaluacionTecladoNumericoFragment.BTN_0:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_1:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_2:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_3:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_4:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_5:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_6:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_7:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_8:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_9:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_DOT:
                appendNota(number);
                break;
            case EvaluacionTecladoNumericoFragment.BTN_DELETE:
                appendNota(number);
                break;
        }
    }

    private void saveRubroEvaluacion(){

        AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
        alumnosEvaluacionUi.setNotaUi(selectorNumericoUi);
        saveAlumnoEvaluacion.execute(new SaveAlumnoEvaluacion.RequestValues(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi), new UseCaseSincrono.Callback<SaveAlumnoEvaluacion.ResponseValue>() {
            @Override
            public void onResponse(boolean success, SaveAlumnoEvaluacion.ResponseValue value) {
                Log.d(TAG, "onSuccess " + success);
            }
        });

    }

    private void saveRubroEvaluacionGrupo(){
        GrupoEvaluacionUi grupoEvaluacionUi = new GrupoEvaluacionUi();
        AlumnosEvaluacionUi alumnosEvaluacionUi = new AlumnosEvaluacionUi();
        if(this.alumnosEvaluacionUi instanceof AlumnosEvaluacionUi){
            alumnosEvaluacionUi = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
            alumnosEvaluacionUi.setNotaUi(selectorNumericoUi);
        }else{
            grupoEvaluacionUi = (GrupoEvaluacionUi) this.alumnosEvaluacionUi;
            grupoEvaluacionUi.setNotaUi(selectorNumericoUi);
        }

        useCaseHandler.execute(saveGrupoEvaluacion,
                new SaveGrupoEvaluacion.RequestValues(sesionAprendizajeId, rubroEvaluacionUi, grupoEvaluacionUi, alumnosEvaluacionUi),
                new UseCase.UseCaseCallback<SaveGrupoEvaluacion.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveGrupoEvaluacion.ResponseValue response) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void appendNota(String n) {
        if (n.equals("x")) {//Eliminar
            notaText = removeLastCharacter(notaText);
        } else {
            notaText += n;
        }

        if (!validateValorNumerico(notaText)) {
            selectorNumericoUi = (SelectorNumericoUi) getNotaUi();
            if(selectorNumericoUi == null) return;
            Double nota = Double.parseDouble(notaText);
            selectorNumericoUi.setNota(nota);
            selectorNumericoUi.setResaltar(true);
            showNota(notaText);
            if(view != null) view.showNotaError("");
        } else {
            showNota("");
            notaText = "";
            showErrorInvalidNota();
        }

        try {
            if(alumnosEvaluacionUi instanceof GrupoEvaluacionUi){
                GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi)alumnosEvaluacionUi;
                for (AlumnosEvaluacionUi alumnosEvaluacionUi: grupoEvaluacionUi.getAlumnosEvaluacionUis()){
                    SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) alumnosEvaluacionUi.getNotaUis().get(0);
                    selectorNumericoUi.setNota(this.selectorNumericoUi.getNota());
                    Log.d(TAG,"Nota: "+ alumnosEvaluacionUi.getName() +" = " + selectorNumericoUi.getNota());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public NotaUi getNotaUi(){
        NotaUi notaUi;
        try {
            if(this.alumnosEvaluacionUi instanceof AlumnosEvaluacionUi){
                AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) this.alumnosEvaluacionUi;
                notaUi = alumnosEvaluacionUi.getNotaUis().get(0);
            }else {
                GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) this.alumnosEvaluacionUi;
                notaUi = grupoEvaluacionUi.getNotaUis().get(0);
            }
        }
        catch (Exception e){
            notaUi= null;
        }
        return notaUi;
    }

    private String removeLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private void showNota(String nota) {
        if (view != null) {
            view.showNota(nota);
        }
    }

    private boolean validateValorNumerico(String notaUnparsed) {
        if (TextUtils.isEmpty(notaUnparsed)) return true;
        try {
            Double nota = Double.parseDouble(notaUnparsed);
            boolean valid = nota >= valorMinimo && nota <= valorMaximo;
            if (valid){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return true;
        }
    }

    private void showErrorInvalidNota() {
        if (view != null) {
            view.showErrorInvalidNota();
        }
    }
}
