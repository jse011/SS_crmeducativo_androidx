package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.UpdateEvaluacionFormulas;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionRubroEvaluacion;

/**
 * Created by SCIEV on 30/07/2018.
 */

public class EvaluacionContainerPresenterImpl extends BasePresenterImpl<EvaluacionContainerView> implements EvaluacionContainerPresenter {
    GetFechaCreacionRubroEvaluacion getFechaCreacionRubroEvaluacion;
    CalcularMediaDesviacion calcularMediaDesviacion;
    GetRubro getRubro;
    private String rubroEvaluacionId;
    private long f_CreacionServidor;
    private long f_CreacionLocal;
    private RubroEvaluacionUi rubroEvaluacionUi;
    private int idCargaCurso;
    private FiltroTableUi filtroTableUi;
    private boolean btnEye;
    private boolean btnFooter;
    //private boolean isCalendarioVigente;
    private int programaEducativoId;
    private boolean desavilitarEval;
    private UpdateEvaluacionFormulas updateEvaluacionFormulas;

    public EvaluacionContainerPresenterImpl(UseCaseHandler handler, Resources res, GetFechaCreacionRubroEvaluacion getFechaCreacionRubroEvaluacion, GetRubro getRubro, CalcularMediaDesviacion calcularMediaDesviacion,UpdateEvaluacionFormulas updateEvaluacionFormulas) {
        super(handler, res);
        this.getFechaCreacionRubroEvaluacion = getFechaCreacionRubroEvaluacion;
        this.getRubro = getRubro;
        this.calcularMediaDesviacion=calcularMediaDesviacion;
        this.updateEvaluacionFormulas = updateEvaluacionFormulas;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void onCreate() {
        super.onCreate();
        filtroTableUi = new FiltroTableUi();
        filtroTableUi.setPersona("");
        filtroTableUi.setOrderByASC(FiltroTableUi.OrderByASC.APELLIDO);
        updateAlumnConProc(filtroTableUi);
    }

    @Override
    protected String getTag() {
        return EvaluacionContainerPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onStart() {
        super.onStart();
        getRubro();
    }

    private void showbtnClear(){
       if (view!=null)view.showBtnClean(!desavilitarEval);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        rubroEvaluacionId = extras.getString(EvaluacionContainerActivity.RUBROEVALUACIONID);
        idCargaCurso = extras.getInt(FragmentRubroEvalProcesoLista.TAG_CARGACURSO_ID);
        CRMBundle crmBundle = new CRMBundle(extras);
        desavilitarEval = extras.getBoolean(EvaluacionContainerActivity.DESAVILITAR_EVALUACION);;
        programaEducativoId = crmBundle.getProgramaEducativoId();
        Log.d(getTag(), "desavilitarEval: " + desavilitarEval);
    }

    @Override
    public void onBtnActualizarRubro() {
        if(rubroEvaluacionUi==null)return;
        BEVariables beVariables = new BEVariables();
        beVariables.setRubroEvaluacionId(rubroEvaluacionId);
        beVariables.setCargaCursoId(idCargaCurso);
        beVariables.setCalendarioPeriodoId(rubroEvaluacionUi.getCalendarioPeriodoId());
        if (view != null) view.showActividadImportacion(beVariables);
        /*if(isCalendarioVigente){
            if(view!=null)view.hideMsgCalendarioPeriodo();
        }else {
            if(view!=null)view.showMsgCalendarioPeriodo();
        }*/
    }

    @Override
    public void onUpdateRubro() {
        calcularMediaDesviacion.execute(new CalcularMediaDesviacion.Requests(rubroEvaluacionId));

        updateEvaluacionFormulas.execute(new UpdateEvaluacionFormulas.Request(),
                new UseCaseSincrono.Callback<UpdateEvaluacionFormulas.Response>() {
                    @Override
                    public void onResponse(boolean success, UpdateEvaluacionFormulas.Response value) {
                        if(value.isPreload()){
                            if(view!=null)view.showDialogProgress();
                        }else {
                            if(view!=null)view.serviceUpdateRubro(rubroEvaluacionId);
                            if (view!=null)view.onSincronizate(programaEducativoId,rubroEvaluacionId);
                        }

                    }
                });

    }

    @Override
    public void onDestroy() {
        if(view!=null)view.hideDialogProgress();
        super.onDestroy();
    }

    @Override
    public void onQueryTextSubmit(String query) {
        filtroTableUi.setPersona(query);
        Log.d(getTag(),filtroTableUi.toString());
        updateAlumnConProc(filtroTableUi);
    }

    private void updateAlumnConProc(FiltroTableUi filtroTableUi) {
        if (view!=null)view.updateTableView(filtroTableUi);
    }

    @Override
    public void onQueryTextChange(String newText) {
        if(newText.trim().isEmpty()){
            filtroTableUi.setPersona(newText);
            Log.d(getTag(),filtroTableUi.toString());
            updateAlumnConProc(filtroTableUi);
        }

    }

    @Override
    public void onClikCornerTableView() {
        switch (filtroTableUi.getOrderByASC()){
            case APELLIDO:
                filtroTableUi.setOrderByASC(FiltroTableUi.OrderByASC.NOMBRE);
                break;
            case NOMBRE:
                filtroTableUi.setOrderByASC(FiltroTableUi.OrderByASC.APELLIDO);
                break;
        }
        Log.d(getTag(),filtroTableUi.toString());
        updateAlumnConProc(filtroTableUi);
    }

    @Override
    public void onClickEye() {

    }

    @Override
    public void onClickFooter() {

    }

    @Override
    public void onClickClear() {

    }

    @Override
    public void initTutorial() {
        if(rubroEvaluacionUi!=null){
            switch (rubroEvaluacionUi.getTipo()){
                case NORMAL:
                    if(view!=null)view.showTutorialRubroNormal();
                    break;
                case RUBRICA_DETALLE:
                    if(view!=null)view.showTutorialRubricaDetalle();
                    break;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu() {
        if(rubroEvaluacionUi!=null){
            switch (rubroEvaluacionUi.getTipo()){
                case NORMAL:
                    if(view!=null)view.showTutorialRubroNormal();
                    if(view!=null)view.showBtnHelp();
                    break;
                case RUBRICA_DETALLE:
                    if(view!=null)view.showTutorialRubricaDetalle();
                    if(view!=null)view.hideBtnHelp();
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //getFechaCreacionRubroEvaluacion();
    }

    private void getFechaCreacionRubroEvaluacion(){
        handler.execute(getFechaCreacionRubroEvaluacion,
                new GetFechaCreacionRubroEvaluacion.RequestValues(rubroEvaluacionId),
                new UseCase.UseCaseCallback<GetFechaCreacionRubroEvaluacion.ResponseValue>() {
                    @Override
                    public void onSuccess(GetFechaCreacionRubroEvaluacion.ResponseValue response) {
                        f_CreacionServidor = response.getF_Servidor();
                        f_CreacionLocal = response.getF_Local();
                        comprobarFechaRubroEvaluacion();
                    }

                    @Override
                    public void onError() {
                        ocultarMsgActualizacion();
                    }
                });
    }

    private void comprobarFechaRubroEvaluacion() {
        Log.d(getTag(),"comprobarFechaRubroEvaluacion: "+ f_CreacionServidor +" > " + f_CreacionLocal);
        if(f_CreacionServidor > f_CreacionLocal){
            visibleMsgActualizacion();
        }else {
            ocultarMsgActualizacion();
        }
    }

    private void visibleMsgActualizacion(){
        if(view!=null)view.showMsgActualizacion();
    }

    private void ocultarMsgActualizacion(){
        if(view!=null)view.hideMsgActualizacion();
    }

    private void getRubro(){
        handler.execute(getRubro, new GetRubro.RequestValues(rubroEvaluacionId),
                new UseCase.UseCaseCallback<GetRubro.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubro.ResponseValue response) {
                        rubroEvaluacionUi = response.getRubroEvaluacionUi();
                        if(rubroEvaluacionUi!=null){
                            switch (rubroEvaluacionUi.getTipo()){
                                case NORMAL:
                                    //if(view!=null)view.showBtnClean(true);
                                    showbtnClear();
                                    break;
                                case RUBRICA_DETALLE:
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
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


}
