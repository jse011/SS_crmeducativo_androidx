package com.consultoraestrategia.ss_crmeducativo.programahorario.simple;

import android.content.res.Resources;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ProgramaHorarioPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetCurso;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetProgramaHorarioCompleto;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetReporteHorario;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;

import java.util.List;

public class ProgramaHorarioSimplePresenterImpl extends ProgramaHorarioPresenterImpl {
    private GetCurso getCurso;
    private int cargaCursoId;

    public ProgramaHorarioSimplePresenterImpl(UseCaseHandler handler, Resources res, GetProgramaHorarioCompleto getProgramaHorarioCompleto, GetReporteHorario getReporteHorario, GetCurso getCurso) {
        super(handler, res, getProgramaHorarioCompleto, getReporteHorario);
        this.getCurso = getCurso;
    }

    @Override
    protected void listarCurso(final Callback<List<CursoUi>> callback) {
        handler.execute(getCurso, new GetCurso.RequestValues(cargaCursoId, programaEducativoId, anioAcademicoId),
                new UseCase.UseCaseCallback<GetCurso.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCurso.ResponseValue response) {
                        callback.onLoad(true,response.getCursoUiList());
                    }

                    @Override
                    public void onError() {
                        callback.onLoad(false,null);
                    }
                });
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        this.cargaCursoId = crmBundle.getCargaCursoId();
    }

    @Override
    public void onCLickAcceptButtom() {

    }
}
