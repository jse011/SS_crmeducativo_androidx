package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CellTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.MatrizResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.CerrarCursoResultado;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.GetMatrizResultado;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.PromediarNotas;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CentProcesoView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CerrarCursoDialogView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.GenerarNotasDialogView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.TutorialCentView;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.utils.AndroidOnline.Online;

import java.util.ArrayList;
import java.util.List;

public class CentProcesoPresenterImpl extends BasePresenterImpl<CentProcesoView> implements CentProcesoPresenter{
    private GetMatrizResultado getMatrizResultado;
    private GetCalendarioPeriodo getCalendarioPeriodo;
    private int silaboEventoId;
    private int cargaCursoId;
    private int rubroFormal;
    private RegistroCentProcesamientoView registroCentProcesamientoView;

    private TutorialCentView tutorialCentView;
    private String tituloCurso;
    private String color1;
    private String color3;
    private String color2;
    private int programaId;
    private int anioAcademicoId;
    private List<PeriodoUi> calendarioPeriodoList = new ArrayList<>();
    private PeriodoUi selectedPeriodoUi;
    private GenerarNotasDialogView generarNotasDialogView;
    private MatrizResultadoUi matrizResultadoUi;
    private PromediarNotas  promediarNotas;
    private CerrarCursoResultado cerrarCursoResultado;
    private CerrarCursoDialogView cerrarCursoDialogView;
    private RetrofitCancel retrofitCancelGetMatriz;
    private RetrofitCancel retrofitCancelPromediarNotas;
    private RetrofitCancel retrofitCancelCerrarCursoResultado;
    private Online online;
    public CentProcesoPresenterImpl(UseCaseHandler handler, Resources res, Online online, GetMatrizResultado getMatrizResultado, GetCalendarioPeriodo getCalendarioPeriodo, PromediarNotas promediarNotas, CerrarCursoResultado cerrarCursoResultado) {
        super(handler, res);
        this.getMatrizResultado = getMatrizResultado;
        this.getCalendarioPeriodo = getCalendarioPeriodo;
        this.promediarNotas = promediarNotas;
        this.cerrarCursoResultado = cerrarCursoResultado;
        this.online = online;
    }

    @Override
    protected String getTag() {
        return "CentProcesoPresenterImpl";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.calendarioPeriodoList.clear();
        this.calendarioPeriodoList.addAll(getCalendarioPeriodo.execute(programaId, anioAcademicoId, cargaCursoId));
        for (PeriodoUi periodoUi : calendarioPeriodoList){
            if(periodoUi.isStatus())selectedPeriodoUi = periodoUi;
        }


    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        this.silaboEventoId = crmBundle.getSilaboEventoId();
        this.cargaCursoId = crmBundle.getCargaCursoId();
        this.tituloCurso = crmBundle.getCursoTitulo();
        this.color1 = crmBundle.getColor1();
        this.color3 = crmBundle.getColor3();
        this.color2 = crmBundle.getColor2();
        this.programaId = crmBundle.getProgramaEducativoId();
        this.anioAcademicoId = crmBundle.getAnioAcademico();
    }

    private void getMatrizResultado() {
        /*
        * {"interface":"RestAPI","method":"flst_RegistroEvaluacion","parameters":{"vint_SilaboEventoId":6681,"vint_CargaCursoId":1888,"vint_CalendarioPeriodoId":87,"vint_RubroFormal":1}}*/
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideError();
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.showProgress();
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideCorner();
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.clearListMatrizResultado();
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
        this.matrizResultadoUi = null;
        if(selectedPeriodoUi!=null){
            online.online(new Online.Callback() {
                @Override
                public void onLoad(boolean success) {
                    if(success){
                        
                        retrofitCancelGetMatriz = getMatrizResultado.execute(silaboEventoId, cargaCursoId, selectedPeriodoUi.getIdCalendarioPeriodo(), rubroFormal, tituloCurso, new GetMatrizResultado.Callback() {
                            @Override
                            public void onSuccess(MatrizResultadoUi response) {
                                showPromediar(response);
                            }

                            @Override
                            public void onError() {
                                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.clearListMatrizResultado();
                                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideProgress();
                                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideCorner();
                                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
                                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.showErrorInterno();
                            }

                        });
                    }else {
                        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.clearListMatrizResultado();
                        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideProgress();
                        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideCorner();
                        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
                        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.showErrorSinInternet();
                    }
                }
            });


        }else {
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.clearListMatrizResultado();
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideProgress();
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideCorner();
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.showSelecionCalendarioPerido();
        }

    }

    private void showPromediar(MatrizResultadoUi matrizResultadoUi) {
        this.matrizResultadoUi = matrizResultadoUi;
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.setListMatrizResultado(matrizResultadoUi);
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideProgress();
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.showCorner();
        if(matrizResultadoUi.getEvaluacionList()==null||matrizResultadoUi.getEvaluacionList().isEmpty()){
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
        }else {
            if(matrizResultadoUi.getHabilitado()==1){
                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.desbloqueoBotones();
            }else {
                if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
            }

        }
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void attachView(RegistroCentProcesamientoView view) {
        this.registroCentProcesamientoView = view;
        if(rubroFormal==1){
            registroCentProcesamientoView.setTituloCompetencia("Compet. Base");
        }else {
            registroCentProcesamientoView.setTituloCompetencia("Transversales");
        }
        this.registroCentProcesamientoView.setupTable(color1, color2, color3);
        this.registroCentProcesamientoView.setupList(calendarioPeriodoList, color2);
        if(matrizResultadoUi==null){
            getMatrizResultado();
        }else {
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.showProgress();
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.hideCorner();
            if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showPromediar(matrizResultadoUi);
                }
            }, 500);
        }
    }

    @Override
    public void onDestroyRegistroCentProcesamientoView() {
        registroCentProcesamientoView = null;
        if(retrofitCancelGetMatriz!=null)retrofitCancelGetMatriz.cancel();
    }

    @Override
    public void onDestroyTutorialCentView() {
        tutorialCentView = null;
    }

    @Override
    public void attachView(TutorialCentView view) {
        this.tutorialCentView = view;
        this.tutorialCentView.setTitulo(tituloCurso);
        this.tutorialCentView.setTema(color1, color2, color3);
        matrizResultadoUi = null;
    }

    @Override
    public void onClickCalendario(PeriodoUi periodoUi) {
        if(registroCentProcesamientoView!=null)registroCentProcesamientoView.onToogle(selectedPeriodoUi, periodoUi);
        this.selectedPeriodoUi = periodoUi;
        getMatrizResultado();
    }

    @Override
    public void onClickTransversal() {
        this.rubroFormal = 0;
    }

    @Override
    public void onClickBase() {
        this.rubroFormal = 1;
    }

    @Override
    public void onClickGenerador() {
        if(matrizResultadoUi!=null)
        if(view!=null)view.showDialogGenerarNotas();
    }

    @Override
    public void onClickBloqueo() {
        if(matrizResultadoUi!=null)
        if(view!=null)view.showDialogCerrarEvaluacion();
    }

    @Override
    public void attachView(GenerarNotasDialogView view) {
        this.generarNotasDialogView = view;

        if(selectedPeriodoUi!=null&&matrizResultadoUi!=null){
                if(matrizResultadoUi.getEvaluacionList().isEmpty()){
                    generarNotasDialogView.showAlertaAsignarNotas(selectedPeriodoUi.getTipoName(), true, false, false);
                }else {
                    if(matrizResultadoUi.getHabilitado()==0)     {
                        generarNotasDialogView.showAlertaAsignarNotas(selectedPeriodoUi.getTipoName(), false, true, matrizResultadoUi.getEstadoCargaCurCalPerId()==305);
                    } else {
                        generarNotasDialogView.hideAlertaAsignarNotas();
                    }
                }
        }

    }

    @Override
    public void onDestroyGenerarNotasDialogView() {
        generarNotasDialogView = null;
        if(retrofitCancelPromediarNotas!=null)retrofitCancelPromediarNotas.cancel();
        retrofitCancelPromediarNotas=null;
    }

    @Override
    public void onClickGenerarNotas() {
        if(selectedPeriodoUi!=null){
            if(generarNotasDialogView!=null)generarNotasDialogView.showProgress();
            if(generarNotasDialogView!=null)generarNotasDialogView.hideButtonAction();
            if(retrofitCancelPromediarNotas!=null)retrofitCancelPromediarNotas.cancel();
             retrofitCancelPromediarNotas = promediarNotas.execute(silaboEventoId, cargaCursoId, selectedPeriodoUi.getIdCalendarioPeriodo(), rubroFormal, new PromediarNotas.Callback() {
                @Override
                public void onSuccess() {
                        if(generarNotasDialogView!=null)generarNotasDialogView.hideProgress();
                        if(generarNotasDialogView!=null)generarNotasDialogView.closeDialog();
                        getMatrizResultado();
                    retrofitCancelPromediarNotas=null;
                }

                @Override
                public void onError() {
                    if(generarNotasDialogView!=null)generarNotasDialogView.hideProgress();
                    if(generarNotasDialogView!=null)generarNotasDialogView.showButtonAction();
                    retrofitCancelPromediarNotas=null;
                    if(view!=null)view.showMessage("Tiempo de conexión agotado");
                }
            });
        }

    }

    @Override
    public void onDestroyCerrarCursoDialogView() {
        cerrarCursoDialogView = null;
        if(retrofitCancelCerrarCursoResultado!=null)retrofitCancelCerrarCursoResultado.cancel();
        retrofitCancelCerrarCursoResultado = null;
    }

    @Override
    public void attachView(CerrarCursoDialogView view) {
        this.cerrarCursoDialogView = view;
        if(selectedPeriodoUi!=null&&matrizResultadoUi!=null){
            cerrarCursoDialogView.setTitulo(selectedPeriodoUi.getTipoName());
            if(matrizResultadoUi.getEvaluacionList().isEmpty()){
                cerrarCursoDialogView.showAlertaAsignarNotas(selectedPeriodoUi.getTipoName(), true, false, false);
            }else {
                if(matrizResultadoUi.getHabilitado()==0)     {
                    cerrarCursoDialogView.showAlertaAsignarNotas(selectedPeriodoUi.getTipoName(), false, true, matrizResultadoUi.getEstadoCargaCurCalPerId()==305);
                } else {
                    cerrarCursoDialogView.hideAlertaAsignarNotas();
                }
            }
        }
    }

    @Override
    public void onClickCerrarEvaluacion() {
        if(selectedPeriodoUi!=null){
            if(cerrarCursoDialogView!=null)cerrarCursoDialogView.showProgress();
            if(cerrarCursoDialogView!=null)cerrarCursoDialogView.hideButtonAction();
            if(retrofitCancelCerrarCursoResultado!=null)retrofitCancelCerrarCursoResultado.cancel();
            retrofitCancelCerrarCursoResultado = cerrarCursoResultado.execute(cargaCursoId, selectedPeriodoUi.getIdCalendarioPeriodo(), new CerrarCursoResultado.Callback() {
                @Override
                public void onSuccess() {
                    if(cerrarCursoDialogView!=null)cerrarCursoDialogView.hideProgress();
                    if(cerrarCursoDialogView!=null)cerrarCursoDialogView.closeDialog();
                    for (CellTableRegEvalUi cellTableRegEvalUi : matrizResultadoUi.getEvaluacionList()){
                        cellTableRegEvalUi.setBimestrCerrado(true);
                        cellTableRegEvalUi.setBimestrNoVigente(true);
                    }
                    matrizResultadoUi.setRangoFecha(0);
                    matrizResultadoUi.setHabilitado(0);
                    matrizResultadoUi.setEstadoCargaCurCalPerId(305);
                    if(registroCentProcesamientoView!=null)registroCentProcesamientoView.bloqueoBotones();
                    if(registroCentProcesamientoView!=null)registroCentProcesamientoView.notifyChange();
                    retrofitCancelCerrarCursoResultado = null;
                }

                @Override
                public void onError() {
                    if(cerrarCursoDialogView!=null)cerrarCursoDialogView.hideProgress();
                    if(cerrarCursoDialogView!=null)cerrarCursoDialogView.showButtonAction();
                    retrofitCancelCerrarCursoResultado = null;
                    if(view!=null)view.showMessage("Tiempo de conexión agotado");
                }
            });
        }
    }

    @Override
    public void onClickRefresh() {
        getMatrizResultado();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(retrofitCancelGetMatriz!=null)retrofitCancelGetMatriz.cancel();
    }
}
