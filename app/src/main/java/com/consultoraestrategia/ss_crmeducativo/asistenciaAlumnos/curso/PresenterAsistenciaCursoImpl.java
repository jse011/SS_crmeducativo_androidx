package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetFechaAsistenciaBimentreAgrupadas;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaBannerUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui.ViewAsistenciaCurso;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;

import java.util.ArrayList;
import java.util.List;

public class PresenterAsistenciaCursoImpl extends BaseFragmentPresenterImpl<ViewAsistenciaCurso> implements PresenterAsistenciaCurso {
    List<Object> asistenciaUiList = new ArrayList<>();
    private GetFechaAsistenciaBimentreAgrupadas getFechaAsistenciaBimentreAgrupadas;
    private int cargaCursoId;
    private int calendarioPeridoId;
    private int programaEducativoId;
    private int docenteId;
    private int geografiaId;
    private int cargaAcademicaId;
    private int parametroDisenioId;
    private final static String TAG = PresenterAsistenciaCursoImpl.class.getSimpleName();
    private CRMBundle crmBundle;
    private int anioAcademicoId;

    public PresenterAsistenciaCursoImpl(UseCaseHandler handler, Resources res, GetFechaAsistenciaBimentreAgrupadas getFechaAsistenciaBimentreAgrupadas) {
        super(handler, res);
        this.getFechaAsistenciaBimentreAgrupadas = getFechaAsistenciaBimentreAgrupadas;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onResumenFragment(String idCalendarioPeriodo) {
        this.calendarioPeridoId = Integer.parseInt(idCalendarioPeriodo);
        setUpList();
    }

    @Override
    public void onClickAsistencia(FechaAsistenciaUi fechaAsistenciaUi) {
        AsistenciaUi asistenciaUi = new AsistenciaUi();
        asistenciaUi.setIdCargaCurso(cargaCursoId);
        asistenciaUi.setIdCargaAcademica(cargaAcademicaId);
        asistenciaUi.setIdProgramaEducativo(programaEducativoId);
        asistenciaUi.setIdGeoreferencia(geografiaId);
        asistenciaUi.setIdCalendarioPeriodo(calendarioPeridoId);
        asistenciaUi.setIdDocente(docenteId);
        asistenciaUi.setFecha(fechaAsistenciaUi.getFechaAsistencia());
        asistenciaUi.setColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1());
        if (view != null) view.showAsistenciaActivity(asistenciaUi);
    }

    @Override
    public CRMBundle getCrmBundle() {
        return crmBundle;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        parametroDisenioId = crmBundle.getParametroDisenioId();
        this.crmBundle = new CRMBundle(extras);
        this.cargaCursoId = crmBundle.getCargaCursoId();
        this.calendarioPeridoId = crmBundle.getCalendarioPeriodoId();
        this.programaEducativoId = crmBundle.getProgramaEducativoId();
        this.docenteId = crmBundle.getEmpleadoId();
        this.geografiaId = crmBundle.getGeoreferenciaId();
        this.cargaAcademicaId = crmBundle.getCargaAcademicaId();
        this.parametroDisenioId = crmBundle.getParametroDisenioId();
        this.anioAcademicoId = crmBundle.getAnioAcademico();
        if(view!=null)view.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view!=null)view.hideProgress();
                setUpList();
            }
        }, 2000);

    }

    @Override
    public void onResume() {
        //  super.onResume();
    }

    public void setUpList() {
         boolean state = false;
        asistenciaUiList.clear();
        if (view != null) view.showProgress();
        Log.d(TAG, "onResume");
        if (calendarioPeridoId == crmBundle.getCalendarioPeriodoId())
            state = true;
        handler.execute(getFechaAsistenciaBimentreAgrupadas, new GetFechaAsistenciaBimentreAgrupadas.RequestValues(anioAcademicoId, cargaCursoId, calendarioPeridoId, programaEducativoId, docenteId, geografiaId, parametroDisenioId, state, cargaAcademicaId),
                new UseCase.UseCaseCallback<GetFechaAsistenciaBimentreAgrupadas.ResponseValue>() {
                    @Override
                    public void onSuccess(GetFechaAsistenciaBimentreAgrupadas.ResponseValue response) {
                        if (view != null) view.hideProgress();
                        asistenciaUiList=response.getObjectList();
                        if (view != null)

                            view.showListAsistenciaCursos(asistenciaUiList, calendarioPeridoId);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
