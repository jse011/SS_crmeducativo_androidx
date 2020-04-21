package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion;

import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetIndicadorList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubroList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.PublicarEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveListAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionEmpty;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 5/10/2017.
 */

public class RegistroPresenterImpl implements RegistroPresenter {
    private String TAG = "RegistroPresenterImpl";
    private RegistroView view;
    private List<RubroEvaluacionUi> rubroEvaluacionUis;
    private UseCaseHandler useCaseHandler;
    private GetRubroList getRubroList;
    private GetAlumnoList getAlumnoList;
    private SaveAlumnoEvaluacion saveAlumnoEvaluacion;
    private GetIndicadorList getIndicadorList;
    private SaveListAlumnoEvaluacion saveListAlumnoEvaluacion;
    private int eventoIdFloatButton;
    private float posicionElementoX;
    private float posicionElementoY;
    private RubroEvaluacionUi rubroEvaluacionUi;
    private int sesionAprendizajeId;
    private int entidadId;
    private int georeferenciaId;
    private int idCargaCurso;
    private List<IndicadorUi> indicadorUis;
    private GetRubro getRubro;
    private String rubroEvaluacionId;
    private int cursoId;
    private List<AlumnosEvaluacionUi> alumnosList;
    private boolean disabledEval;
    private FiltroTableUi filtroTableUi;
    private AlumnosEvaluacionUi alumnoSelect;
    private PublicarEvaluacion publicarEvaluacion;
    private boolean btnEye;
    private boolean btnFooter;

    public RegistroPresenterImpl(UseCaseHandler useCaseHandler, GetRubroList getRubroList, GetAlumnoList getAlumnoList, GetIndicadorList getIndicadorList, SaveAlumnoEvaluacion saveAlumnoEvaluacion, GetRubro getRubro, SaveListAlumnoEvaluacion saveListAlumnoEvaluacion, PublicarEvaluacion publicarEvaluacion) {
        this.useCaseHandler = useCaseHandler;
        this.getRubroList = getRubroList;
        this.getAlumnoList = getAlumnoList;
        this.getIndicadorList = getIndicadorList;
        this.saveAlumnoEvaluacion = saveAlumnoEvaluacion;
        this.rubroEvaluacionUi = new RubroEvaluacionUi();
        this.indicadorUis = new ArrayList<>();
        this.rubroEvaluacionUis = new ArrayList<>();
        this.getRubro = getRubro;
        this.saveListAlumnoEvaluacion = saveListAlumnoEvaluacion;
        this.publicarEvaluacion = publicarEvaluacion;
    }

    @Override
    public void attachView(RegistroView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        filtroTableUi = new FiltroTableUi();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        if(view!=null)view.showTutorial();
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onBackPressed() {

    }

    //region Float Button
    @Override
    public void actionDowButtonFloat(int eventoIdFloatButton, float posicionEventoX, float posicionEventoY, float posicionElementoX, float posicionElementoY) {
        this.posicionElementoX = posicionElementoX - posicionEventoX;
        this.posicionElementoY = posicionElementoY - posicionEventoY;
        this.eventoIdFloatButton = eventoIdFloatButton;
    }

    @Override
    public void actionMoveButtonFloat(int eventoIdFloatButton, float posicionEventoX, float posicionEventoY) {
        if (view != null) {
            view.moverButtonfloat(posicionEventoX + posicionElementoX, posicionEventoY + posicionElementoY);
            this.eventoIdFloatButton = eventoIdFloatButton;
        }
    }

    @Override
    public void actionUpButtonFloat() {
        if (view != null) {
            // evento MotionEvent.ACTION_DOWN = 0
            if (eventoIdFloatButton == 0) {
                view.showDialogInidicador(indicadorUis);
            }
        }
    }
    //endregion Float Button

    //region Evaluacion
    @Override
    public void onClickEvaluacion(NotaUi evaluacion) {

    }

    @Override
    public void onClickLongEvaluacion(NotaUi evaluacion) {

    }
    //endregion Evaluacion

    @Override
    public void onItemSelectRubroEval(RubroEvaluacionUi rubroEvaluacionUi) {
        if (view != null) {
            view.showProgress();
            toogleRubroEvaluacionProceso(rubroEvaluacionUi);
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            getAlumnoList();
        }

    }

    @Override
    public void onClickItemIndicador(IndicadorUi indicadorUi) {
        if (view != null) {
            view.showDialogAgregarRubro(sesionAprendizajeId, indicadorUi.getId());
            view.hideDialogInidicador();
        }
    }

    @Override
    public void setExtras(Bundle extras) {
        sesionAprendizajeId = extras.getInt(FragmentRubroEvalProcesoLista.TAG_SESIONAPRENDIZAJEID);
        idCargaCurso = extras.getInt(FragmentRubroEvalProcesoLista.TAG_CARGACURSO_ID);
        cursoId = extras.getInt(FragmentRubroEvalProcesoLista.TAG_CARGACURSO_ID);
        rubroEvaluacionId = extras.getString(EvaluacionContainerActivity.RUBROEVALUACIONID);
        disabledEval = extras.getBoolean(EvaluacionContainerActivity.DESAVILITAR_EVALUACION, false);
        entidadId = extras.getInt(FragmentRubroEvalProcesoLista.TAG_ENTIDAD_ID);
        georeferenciaId = extras.getInt(FragmentRubroEvalProcesoLista.TAG_GEOREFERENCIA_ID, 0);
        Log.d(TAG, "disabledEval : " + disabledEval);
    }

    @Override
    public void onChangeCrearRubro() {
        getRubroList();
    }

    @Override
    public void onChangeRubro() {
        getRubro();
    }

    @Override
    public void onSelectAlumnoEvaluacion(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi) {
        notaUi.setNota(notaUi.getNotaEvaluacion());
        if (view != null) {
            if (notaUi.getResaltar()) {
                notaUi.setResaltar(false);
            } else {
                notaUi.setResaltar(true);
            }

            NotaUi notaUiAnterior = alumnosEvaluacionUi.getNotaUi();
            if (notaUiAnterior != null) {
                notaUiAnterior.setNota(notaUiAnterior.getNotaEvaluacion());
                if (notaUiAnterior != notaUi) {
                    notaUiAnterior.setResaltar(false);
                }
            }

            alumnosEvaluacionUi.setNotaUi(notaUi);
            saveRegistroEvaluacion(alumnosEvaluacionUi);
            //view.changeNotaEvaluacion();
        }
    }

    @Override
    public void onSelectAlumnoEvaluacionSelectorNota(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi) {
        if (view == null) return;
        SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) notaUi;
        if (selectorNumericoUi == null) return;
        view.showDilogEvaluacionSelectorNotas(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi, selectorNumericoUi, alumnosList);
    }

    @Override
    public void onSelectAlumnoEvaluacionTecladoNumerico(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi) {
        if (view == null) return;
        SelectorNumericoUi selectorNumericoUi = (SelectorNumericoUi) notaUi;
        if (selectorNumericoUi == null) return;
        view.showDilogEvaluacionTecladoNumerico(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi, selectorNumericoUi, alumnosList);
    }

    @Override
    public void changeEvaluacion() {
        if(view!=null)view.changeTable();
    }

    @Override
    public void onClickIndicador() {
        if(view!=null)view.onShowDialogInfoRubro(rubroEvaluacionId);
    }

    @Override
    public void onClickAlumno(AlumnosEvaluacionUi item) {
        if(view!=null)view.onShowInfoUsuario(item);
    }

    @Override
    public void updateTableView(FiltroTableUi filtroTableUi) {
        this.filtroTableUi = filtroTableUi;
    }

    @Override
    public void onLongClickNota(AlumnosEvaluacionUi item, NotaUi nota) {
        if(!nota.getResaltar())return;
        this.alumnoSelect = item;
        if(view!=null)view.onShowPresionEvaluacion(nota.getNotaEvaluacion(), rubroEvaluacionId, nota.getId(), nota.getEstilo().getColor(), nota.isIntervalo());
    }

    @Override
    public void onSelectPrecicionEvaluacion(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        if(alumnoSelect!=null){
            NotaUi notaUi = alumnoSelect.getNotaUi();
            if(notaUi==null)return;
            notaUi.setNota(notaActual);
            if(view!=null)view.changeTable();
            saveRegistroEvaluacion(alumnoSelect);
        }
    }

    @Override
    public void setAlumnosList() {
        ArrayList<AlumnosEvaluacionUi> alumnosEvaluacionUis = new ArrayList<>();
        for (AlumnosEvaluacionUi alumnosEvaluacionUi: alumnosList){
            if(!alumnosEvaluacionUi.isVigente())continue;
            NotaUi notaUi = alumnosEvaluacionUi.getNotaUi();
            if (notaUi!=null){
                notaUi.setResaltar(false);
                notaUi.setNota(notaUi.getNotaDefault());
                alumnosEvaluacionUis.add(alumnosEvaluacionUi);
            }
        }
        if (view != null) view.changeTable();
        saveRegistroEvaluacionList(alumnosEvaluacionUis);
        //onChangeRubro();
        //if (view!=null)view.updateAlumnoEvaluacion(alumnosEvaluacionUis);
    }

    @Override
    public void onClickBtnPublicar(AlumnosEvaluacionUi item, OptionPublicar optionPublicar) {
        if(!optionPublicar.isSelected()){
            optionPublicar.setSelected(true);
        }else {
            optionPublicar.setSelected(false);
        }

        publicarEvaluacion.execute(optionPublicar);
        if(view!=null)view.notiftyDataBaseChange();
    }

    @Override
    public void onClickBtnComentario(AlumnosEvaluacionUi item, OptionComentario comentario) {
        if(view!=null)view.showInfoComentario(comentario.getEvaluacionId());
    }

    @Override
    public void onActivityCreated() {
        getRubro();
    }

    @Override
    public void onClikCornerTableView() {
        getRubro();
    }

    @Override
    public void onClickEye() {
        if(btnEye){
            changeEyeSimple();
            changeTableSimple();
            btnEye = false;
        }else {
            changerEyeFocus();
            changeTableAvanzado();
            btnEye = true;
        }
    }

    @Override
    public void onClickFooter() {
        if(btnFooter){
            btnFooter = false;
            changeSwiteOff();
            hideFooter();
        }else {
            btnFooter = true;
            changeSwiteOn();
            showFooter();
        }
    }

    @Override
    public void onClickClear() {
        setAlumnosList();
    }

    private void changeSwiteOn(){
        if(view!=null)view.changeSwiteOn();
    }

    private void changeSwiteOff(){
        if(view!=null)view.changeSwiteOff();
    }

    private void changeEyeSimple(){
        if(view!=null)view.changeEyeSimple();
    }

    private void changerEyeFocus(){
        if(view!=null)view.changerEyeFocus();
    }

    private void showFooter(){
        if(view!=null)view.showFooter();
    }

    private void hideFooter(){
        if(view!=null)view.hideFooter();
    }

    private void changeTableAvanzado(){
        if(view!=null)view.changeTableAvanzado();
    }

    private void changeTableSimple(){
        if(view!=null)view.changeTableSimple();
    }

    private void showRubroList(List<RubroEvaluacionUi> rubros) {

        if (view != null) {
            int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
            if (posicion == -1) {
                posicion = 0;
            }

            if (rubros.size() > 0) {
                view.hideContentVacio();
                rubroEvaluacionUi = rubros.get(posicion);
                rubroEvaluacionUi.setStatus(true);
            }

            setRubroEvaluacionProceso(rubros);
            view.setRubro(rubros);
        }
    }

    private void showAlumnosList(List<AlumnosEvaluacionUi> evaluados) {
        if (view != null) {
            view.hideProgess();
            Log.d(TAG, "showAlumnosList");
            Log.d(TAG, "size : " + evaluados.size());
            this.alumnosList = evaluados;
            if (rubroEvaluacionUi.isStatus()) {
                IndicadorUi indicadorUi = rubroEvaluacionUi.getIndicadorUi();

                List<NotaUi> notaUis = new ArrayList<>();
                if (evaluados.size() > 0) {
                    notaUis.addAll(evaluados.get(0).getNotaUis());
                    String indicador = indicadorUi.getAlias();
                    if(indicador==null||indicador.trim().isEmpty()){
                        indicador = indicadorUi.getTitle();
                    }
                    String titulo = filtroTableUi.getOrderByASC().getNombre() + "("+ evaluados.size() + ")";

                    view.showEvaluacion(evaluados, notaUis, rubroEvaluacionUi, indicador, titulo , disabledEval);
                    for (NotaUi notaUi: notaUis){
                        notaUi.setIntervalos(setRangoNota(notaUi));
                    }

                    List<NotaUi> notaUiList = new ArrayList<>();
                    for (NotaUi notaUi: notaUis){
                        if(!(notaUi instanceof OptionPublicar)&&
                                !(notaUi instanceof OptionEmpty)&&
                                !(notaUi instanceof OptionComentario)){
                            notaUiList.add(notaUi);
                        }
                    }
                    view.showListarRubros(notaUiList,rubroEvaluacionUi.getTipoNota());
                    view.hideContentVacio();
                } else {
                    view.showContentVacio(R.string.sesion_evaluacion_grupo_tabla_sin_alumnos);
                }

            } else {
                view.showContentVacio(R.string.sesion_evaluacion_grupo_tabla_sin_rubro);
            }
        }


    }

    private void toogleRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi) {
        if (rubroEvaluacionUi != this.rubroEvaluacionUi) {
            if (this.rubroEvaluacionUi != null) {
                this.rubroEvaluacionUi.setStatus(false);
                updateRubroEvaluacionProceso(this.rubroEvaluacionUi);
            }
            if (rubroEvaluacionUi != null) {
                rubroEvaluacionUi.setStatus(true);
                updateRubroEvaluacionProceso(rubroEvaluacionUi);
            }
            view.setRubro(rubroEvaluacionUis);
        }
    }

    //region matenimiento de Lista Rubro Evaluacion
    private void setRubroEvaluacionProceso(List<RubroEvaluacionUi> rubroEvaluacionUis) {
        this.rubroEvaluacionUis.clear();
        this.rubroEvaluacionUis.addAll(rubroEvaluacionUis);
    }

    private void addRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi) {
        rubroEvaluacionUis.add(rubroEvaluacionUi);
    }

    private int updateRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi) {
        int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
        if (posicion != -1) {
            rubroEvaluacionUis.set(posicion, rubroEvaluacionUi);
        }
        return posicion;
    }

    private void deleteRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi) {
        int posicion = rubroEvaluacionUis.indexOf(rubroEvaluacionUi);
        if (posicion != -1) {
            rubroEvaluacionUis.remove(posicion);
        }
    }
    //endregion manteniento de Lista Evalacion

    //region mantenimiento de Indicadores
    private void setIndicador(List<IndicadorUi> indicadorUis) {
        if (indicadorUis != null) {
            this.indicadorUis.clear();
            this.indicadorUis.addAll(indicadorUis);
        }
    }
    //endregion mantenimientos de Indicadores

    //region abstracion de datos
    private void getRubroList() {
        useCaseHandler.execute(
                getRubroList,
                new GetRubroList.RequestValues(sesionAprendizajeId),
                new UseCase.UseCaseCallback<GetRubroList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroList.ResponseValue response) {
                        showRubroList(response.getRubroEvaluacionProcesos());
                        getAlumnoList();
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getGetRubroList");
                    }
                }
        );

    }

    private void getSingleRubroList() {
        useCaseHandler.execute(
                getRubroList,
                new GetRubroList.RequestValues(sesionAprendizajeId),
                new UseCase.UseCaseCallback<GetRubroList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroList.ResponseValue response) {
                        showRubroList(response.getRubroEvaluacionProcesos());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getGetRubroList");
                    }
                }
        );

    }

    private void getAlumnoList() {
        Log.d(TAG, "getAlumnoList");
        view.showProgress();
        if (rubroEvaluacionUi == null) return;
        rubroEvaluacionUi.setCargaCursosId(idCargaCurso);
        useCaseHandler.execute(
                getAlumnoList,
                new GetAlumnoList.RequestValues(rubroEvaluacionUi, filtroTableUi, idCargaCurso, entidadId, georeferenciaId),
                new UseCase.UseCaseCallback<GetAlumnoList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetAlumnoList.ResponseValue response) {
                        showAlumnosList(response.getAlumnosEvaluacionUis());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getAlumnoList");
                    }
                }
        );
    }

    private void getIndicadorList() {
        useCaseHandler.execute(
                getIndicadorList,
                new GetIndicadorList.RequestValues(sesionAprendizajeId, rubroEvaluacionUi),
                new UseCase.UseCaseCallback<GetIndicadorList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetIndicadorList.ResponseValue response) {
                        setIndicador(response.getAlumnosEvaluacionUis());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError getIndicadorList");
                    }
                });
    }

    private void saveRegistroEvaluacion(AlumnosEvaluacionUi alumnosEvaluacionUi) {
        saveAlumnoEvaluacion.execute(new SaveAlumnoEvaluacion.RequestValues(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi), new UseCaseSincrono.Callback<SaveAlumnoEvaluacion.ResponseValue>() {
            @Override
            public void onResponse(boolean success, SaveAlumnoEvaluacion.ResponseValue value) {
                if(view!=null)view.notiftyDataBaseChange();
                Log.d(TAG, "onSuccess " + success);
            }
        });
    }

    private void saveRegistroEvaluacionList(List<AlumnosEvaluacionUi> alumnosEvaluacionUiList) {
        saveListAlumnoEvaluacion.execute(new SaveListAlumnoEvaluacion.RequestValues(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUiList), new UseCaseSincrono.Callback<SaveListAlumnoEvaluacion.ResponseValue>() {
            @Override
            public void onResponse(boolean success, SaveListAlumnoEvaluacion.ResponseValue value) {
                Log.d(TAG, "onSuccess " + value);
                if(view!=null)view.notiftyDataBaseChange();
            }
        });

    }

    private void getRubro() {
        Log.d(TAG, "getRubro");
        useCaseHandler.execute(getRubro,
                new GetRubro.RequestValues(rubroEvaluacionId),
                new UseCase.UseCaseCallback<GetRubro.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubro.ResponseValue response) {

                        rubroEvaluacionUi = response.getRubroEvaluacionUi();
                        if(rubroEvaluacionUi!=null){
                            switch (rubroEvaluacionUi.getTipo()){
                                case NORMAL:
                                   // disabledEval = false;
                                    break;
                                case RUBRICA_DETALLE:
                                    disabledEval = true;
                                    if(view!=null)view.showBtnClean(false);
                                    break;
                            }

                            if(rubroEvaluacionUi.getTipoNota()== RubroEvaluacionUi.TipoNota.TEXTO ||
                                    rubroEvaluacionUi.getTipoNota()== RubroEvaluacionUi.TipoNota.IMAGEN){
                                if(view!=null)view.showBtnFooter();
                            }else {
                                if(view!=null)view.hideBtnFooter();
                            }

                        }


                        getAlumnoList();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    //endregion abstracion de datos

    private String setRangoNota(NotaUi valorNotaAsignado){
        StringBuilder rango =  new StringBuilder();

        if(valorNotaAsignado.isIncluidoLSuperior()){
            rango.append("[ ");
        }else {
            rango.append("< ");
        }
        rango.append(String.format("%.1f", valorNotaAsignado.getLimiteSuperior()));
        rango.append(" - ");
        rango.append(String.format("%.1f", valorNotaAsignado.getLimiteInferior()));
        if(valorNotaAsignado.isIncluidoLInferior()){
            rango.append(" ]");
        }else {
            rango.append(" >");
        }

        return  rango.toString();
    }


}
