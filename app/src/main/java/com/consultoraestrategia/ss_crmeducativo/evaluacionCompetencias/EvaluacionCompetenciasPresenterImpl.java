package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.AnclarResultados;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.GetCompetenciasLista;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.GetEnfoqueTransVersalLista;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.UseEvaluado;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.ValidarAnclaCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtroDialog.FiltroView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.view.EvaluacionCompetenciasView;
import com.raizlabs.android.dbflow.sql.language.SQLite;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciasPresenterImpl extends BaseFragmentPresenterImpl<EvaluacionCompetenciasView> implements EvaluacionCompetenciasPresenter {
    private static String EVALUACION_COMPETENCIA_TAG = EvaluacionCompetenciasPresenterImpl.class.getSimpleName();
    private GetCompetenciasLista getCompetenciasLista;
    private GetEnfoqueTransVersalLista getEnfoqueTransVersalLista;
    private UseEvaluado useEvaluado;
    private int calendarioPeriodoId;
    private int cargaCursoId;
    private int cursoId;
    private int programaEducativoId;
    private ChangeToogle changeToogle;
    private AnclarResultados anclarResultados;
    private String color;
    private boolean calendarioActivo;
    private ValidarAnclaCalendarioPeriodo validarAnclaCalendarioPeriodo;
    private List<Object>objectList;
    private boolean initFragment = true;
    private FiltroView filtroView;
    private ArrayList<FiltradoUi> filtradoUiList = new ArrayList<>();
    private boolean vigente;


    public EvaluacionCompetenciasPresenterImpl(UseCaseHandler handler, Resources res, GetCompetenciasLista getCompetenciasLista, GetEnfoqueTransVersalLista getEnfoqueTransVersalLista, UseEvaluado useEvaluado, ChangeToogle changeToogle, AnclarResultados anclarResultados, ValidarAnclaCalendarioPeriodo validarAnclaCalendarioPeriodo) {
        super(handler, res);
        this.getCompetenciasLista = getCompetenciasLista;
        this.getEnfoqueTransVersalLista = getEnfoqueTransVersalLista;
        this.useEvaluado = useEvaluado;
        this.changeToogle = changeToogle;
        this.anclarResultados=anclarResultados;
        this.validarAnclaCalendarioPeriodo=validarAnclaCalendarioPeriodo;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.cargaCursoId = extras.getInt("idCargaCurso", 0);
        this.calendarioPeriodoId = extras.getInt("idCalendarioPeriodo", 0);
        this.cursoId = extras.getInt("cursoId", 0);
        CRMBundle crmBundle = new CRMBundle(extras);
        this.calendarioActivo=crmBundle.isCalendarioActivo();
        this.programaEducativoId = crmBundle.getProgramaEducativoId();
      //  Log.d(EVALUACION_COMPETENCIA_TAG, "isvigente "+ calendarioActivo);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        iniFiltro();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        validarAnclaPeriodoVigente(calendarioPeriodoId);
    }

    private void iniFiltro() {
        filtradoUiList.clear();
        filtradoUiList.add(new FiltradoUi(FiltradoUi.Tipo.COMPETENCIA_BASE, true));
        filtradoUiList.add(new FiltradoUi(FiltradoUi.Tipo.COMPETENCIA_TRANSVERSAL_ENFOQUE, false));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return EVALUACION_COMPETENCIA_TAG;

    }

    @Override
    public void onResume() {
        if(initFragment){
            if(view!=null)view.showProgress();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(view!=null)view.hideProgress();
                    getCompetenciasLista();
                }
            }, 500);
        }else {
            getCompetenciasLista();
        }
        initFragment = false;
        super.onResume();
    }

    private void getCompetenciasLista() {
        if (calendarioPeriodoId == 0){
            if(view!=null)view.mostrarMensaje(res.getString(R.string.empty_calendario));
            return;
        }
        if(view!=null)view.showProgress();
        validarAnclaPeriodoVigente(calendarioPeriodoId);
        handler.execute(
                getCompetenciasLista, new GetCompetenciasLista.ResquestValues(calendarioPeriodoId, cargaCursoId, cursoId, filtradoUiList),
                new UseCase.UseCaseCallback<GetCompetenciasLista.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCompetenciasLista.ResponseValue response) {
                        if (response.getObjectList() == null || response.getObjectList().isEmpty()) {
                            msjNohayregistros();
                            if(view!=null)view.hideProgress();
                        } else {
                            objectList= response.getObjectList();
                            onEvaluacionCompetencia(response.getObjectList());
                            if (view != null) {
                                for(Object object:response.getObjectList() )
                                    if(object instanceof CompetenciaUi)color=((CompetenciaUi) object).getParametroDisenioUi().getColor1();
                                //view.mostrarListaCompetencia(response.getObjectList().size());
                                view.ocultarMensaje();
                                view.hideProgress();
                                view.setParametroDisenio(color);

                                if(!vigente){
                                    if(objectList!=null&&objectList.size()>0)
                                        for(Object o: objectList){
                                            if(o instanceof CompetenciaUi)
                                                for(CapacidadUi capacidadUi: ((CompetenciaUi)o).getCapacidadUiList()){
                                                    capacidadUi.setEstadoCapacidad(CapacidadUi.EstadoCapacidad.DEFECTO);
                                                }
                                        }
                                    if(view!=null)view.hideCerrarCurso();
                                    if(view!=null)view.hideAncla();
                                    if(view!=null)view.showDialogPeriodoDesactivado(res.getString(R.string.resultados_desabilitados));
                                }else {
                                    if(view!=null)view.hideDialogPeriodoDesactivado();
                                    if(view!=null)view.showAncla();
                                    if(view!=null)view.showCerrarCurso();
                                }

                                if(objectList!=null)mostrarObjectLista(objectList);
                            }
                        }
                    }

                    @Override
                    public void onError() {
                        Log.d(EVALUACION_COMPETENCIA_TAG, "getRubBidList::onError");
                        if (view != null) view.hideProgress();
                    }
                }
        );
    }

    private void msjNohayregistros() {
        if (view != null) {
            view.mostrarMensaje("No hay Registros");
            view.hideProgress();
        }
    }

    List<Object> objectListCompetenciabase;

    private void onEvaluacionCompetencia(List<Object> objectList) {
        if (objectList != null) {
            this.objectListCompetenciabase = objectList;
            mostrarObjectLista(objectList);
            hideProgress();
        }
    }


    private void mostrarObjectLista(List<Object> list) {
        Log.d(EVALUACION_COMPETENCIA_TAG, "mostrarObjectLista");
        if (view != null) {
            view.mostrarObjectLista(list);
        }
    }

    @Override
    public int getCalendarioPeriodoId() {
        int periodoId = 0;
        if (calendarioPeriodoId != 0) {
            periodoId = calendarioPeriodoId;
        }
        return periodoId;
    }

    @Override
    public void onResumeFragment(Bundle bundle) {
        getCompetenciasLista();
    }

    @Override
    public void onAceptarDialogEvaluado(Object object) {
        iniciarUseCaseEvaluacion(object);
    }

    private void iniciarUseCaseEvaluacion(Object object) {
        Log.d(getClass().getSimpleName(), "iniciarUseCaseEvaluacion ");
        // CompetenciaUi capacidadUiCompetenciaUi = capacidadUi.getCompetenciaUi();
        int periodoId = calendarioPeriodoId;
        useEvaluado.execute(new UseEvaluado.RequestValues(object, periodoId), new UseCaseSincrono.Callback<UseEvaluado.ResponseValue>() {
            @Override
            public void onResponse(boolean success, UseEvaluado.ResponseValue response) {
                Log.d(EVALUACION_COMPETENCIA_TAG, "onSuccess " + response.getCapacidadUi());
                if (response.isSuccess()) {
                    if(view!=null)view.simpleSyncIntenService(programaEducativoId);
                    if (view != null) view.changeFragmentRubros(calendarioPeriodoId, calendarioActivo);
                    getCompetenciasLista();
                } else{
                    if(TextUtils.isEmpty(response.getMensaje())){
                        if (view != null)view.showMessage(res.getString(R.string.msg_validar_ancla_evaluaciones));
                    } else {
                        if(view!=null)view.showMessage(response.getMensaje());
                    }
                }

            }
        });
    }

    @Override
    public void onRefrescarItems() {
        getCompetenciasLista();
    }

    @Override
    public void onResumeFragment() {
        getCompetenciasLista();
    }

    @Override
    public void actualizarResultadosPeriodo(String idCalendarioPeriodo, boolean status) {
        this.calendarioPeriodoId = Integer.parseInt(idCalendarioPeriodo);
        this.calendarioActivo= status;
        getCompetenciasLista();

    }

    private void validarAnclaPeriodoVigente(int calendarioPeriodoId) {
        ValidarAnclaCalendarioPeriodo.Response validarResponse = validarAnclaCalendarioPeriodo.execute(new ValidarAnclaCalendarioPeriodo.Requests(calendarioPeriodoId, cargaCursoId, true));
        vigente = validarResponse.isSucces();
        Log.d(EVALUACION_COMPETENCIA_TAG, "Iisvigente "+ vigente);
        if(!vigente){
            if(view!=null)view.hideCerrarCurso();
            if(view!=null)view.hideAncla();
        }

        if(!vigente && calendarioPeriodoId!=0){
            if(view!=null)view.showDialogPeriodoDesactivado(res.getString(R.string.resultados_desabilitados));
        }else {
            if(view!=null)view.hideDialogPeriodoDesactivado();
        }
    }

    @Override
    public void clickCompetencia(CompetenciaUi competenciaUi) {
        if (isNullView()) return;
        if (competenciaUi.isStado()) {
            competenciaUi.setStado(false);
        } else {
            competenciaUi.setStado(true);
        }
        ChangeToogle.Response response = changeToogle.execute(new ChangeToogle.Requests(competenciaUi.isStado(), competenciaUi.getCompetenciaId()));


    }

    @Override
    public void executeAnclado(final AlertDialog alertDialog) {
        if(view!=null)view.showDialogWaitAncla(alertDialog);
        handler.execute(anclarResultados, new AnclarResultados.Request(calendarioPeriodoId, cargaCursoId,filtradoUiList), new UseCase.UseCaseCallback<AnclarResultados.Response>() {
            @Override
            public void onSuccess(AnclarResultados.Response response) {
                if(response.isSucces()){
                    if(view!=null)view.hideAlerDialogwaitAncla(alertDialog);
                    if(view!=null)view.setAnchorCheck();
                    if(view!=null)view.simpleSyncIntenService(programaEducativoId);
                    getCompetenciasLista();
                    if (view != null) view.changeFragmentRubros(calendarioPeriodoId, calendarioActivo);

                }else {
                    if(!TextUtils.isEmpty(response.getMensaje()))if(view!=null)view.showMessage(response.getMensaje());
                }
            }
            @Override
            public void onError() {
            }
        });
    }

    @Override
    public void onClickedBtnCerrar() {
        try {
            List<CargaCursoCalendarioPeriodo> cargaCursoCalendarioPeriodoList = SQLite.select()
                    .from(CargaCursoCalendarioPeriodo.class)
                    .where(CargaCursoCalendarioPeriodo_Table.cargaCursoId.withTable()
                            .eq(cargaCursoId))
                    .and(CargaCursoCalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                    .queryList();
            for (CargaCursoCalendarioPeriodo cargaCursoCalendarioPeriodo: cargaCursoCalendarioPeriodoList){
                cargaCursoCalendarioPeriodo.setEstadoId(CargaCursoCalendarioPeriodo.CERRADO);
                cargaCursoCalendarioPeriodo.setSyncFlag(BaseRelEntity.FLAG_UPDATED);
                cargaCursoCalendarioPeriodo.save();
            }
            //if(view!=null)view.simpleSyncIntenService(programaEducativoId); no es nesario la actividad lo ejecuta
            if(view!=null)view.reloadActivity(programaEducativoId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onbtnAceptarFiltroDialog() {

        int count = 0;
        for (FiltradoUi itemFiltradoUi: filtradoUiList){
            if(itemFiltradoUi.isaBooleanTemporal())count++;
        }
        if(count==0){
            if(view!=null)view.showMessage("Seleccioné un tipo de competencia");
            return;
        }

        for (FiltradoUi filtradoUi : filtradoUiList){
            filtradoUi.setaBoolean(filtradoUi.isaBooleanTemporal());
        }
        if(filtroView!=null)filtroView.hideDialog();
        getCompetenciasLista();
    }

    @Override
    public void filtradoCheckItemFiltroDialog(FiltradoUi filtradoUi) {
        if(!filtradoUi.isaBooleanTemporal())filtradoUi.setaBooleanTemporal(true);
        for (FiltradoUi itemfiltradoUi: filtradoUiList){
         if(!itemfiltradoUi.equals(filtradoUi))itemfiltradoUi.setaBooleanTemporal(false);
        }
        if(filtroView!=null)filtroView.setLisFiltroDialog(filtradoUiList);
    }

    @Override
    public void attachView(FiltroView filtroView) {
        this.filtroView = filtroView;
    }

    @Override
    public void onDestroyDialogFiltro() {
        filtroView = null;
    }

    @Override
    public void onResumeDialogFiltro() {
        if(filtroView!=null)filtroView.setLisFiltroDialog(filtradoUiList);
    }

    @Override
    public void onClickFiltro() {
        for (FiltradoUi filtradoUi : filtradoUiList){
            filtradoUi.setaBooleanTemporal(filtradoUi.isaBoolean());
        }
       if(view!=null)view.showFiltroDialog();
    }


    protected boolean isNullView() {
        if (view == null) {
            return true;
        }
        return false;
    }




}
