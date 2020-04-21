package com.consultoraestrategia.ss_crmeducativo.programahorario;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetCursoCompletos;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetProgramaHorarioCompleto;
import com.consultoraestrategia.ss_crmeducativo.programahorario.domain.usecase.GetReporteHorario;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.BanerUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoHorarioDiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ui.ProgramaHorarioView;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public abstract class ProgramaHorarioPresenterImpl extends BasePresenterImpl<ProgramaHorarioView> implements ProgramaHorarioPresenter {
    private GetProgramaHorarioCompleto getProgramaHorarioCompleto;
    private GetReporteHorario getReporteHorario;
    protected int empleadoId;
    protected int programaEducativoId;
    protected int anioAcademicoId;
    protected List<ProgramaHorarioUi> programaHorarioUiList = new ArrayList<>();
    protected List<CursoUi> cursoUiList = new ArrayList<>();
    protected ProgramaHorarioUi programaHorarioUi;

    public interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    public ProgramaHorarioPresenterImpl(UseCaseHandler handler, Resources res, GetProgramaHorarioCompleto getProgramaHorarioCompleto, GetReporteHorario getReporteHorario) {
        super(handler, res);
        this.getProgramaHorarioCompleto = getProgramaHorarioCompleto;
        this.getReporteHorario = getReporteHorario;
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        programaEducativoId = crmBundle.getProgramaEducativoId();
        empleadoId = crmBundle.getEmpleadoId();
        anioAcademicoId = crmBundle.getAnioAcademico();
    }

    @Override
    protected String getTag() {
        return ProgramaHorarioPresenterImpl.class.getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }


    @Override
    public void onResume() {
        super.onResume();
        listarCurso(new Callback<List<CursoUi>>() {
            @Override
            public void onLoad(boolean success, List<CursoUi> item) {
                if(success){
                    cursoUiList.clear();
                    cursoUiList.addAll(item);
                    if(view!=null)view.showCurso(cursoUiList);
                    listarProgramaHorario();
                }else {
                    showMessage("Error en los cursos");
                }

            }
        });
        showProgress();
    }

    protected abstract void listarCurso(Callback<List<CursoUi>> callback);


    private void listarProgramaHorario() {
        handler.execute(getProgramaHorarioCompleto, new GetProgramaHorarioCompleto.RequestValue(programaEducativoId, anioAcademicoId),
                new UseCase.UseCaseCallback<GetProgramaHorarioCompleto.ResponseValue>() {
                    @Override
                    public void onSuccess(GetProgramaHorarioCompleto.ResponseValue response) {
                        programaHorarioUiList.clear();
                        programaHorarioUiList.addAll(response.getProgramaHorarioUiList());
                        if(!programaHorarioUiList.isEmpty()){
                            programaHorarioUi = programaHorarioUiList.get(0);
                            programaHorarioUi.setSelect(true);
                        }

                        if(programaHorarioUiList.size()>1)if(view!=null)view.showListaProgramaEducativo(programaHorarioUiList);

                        if(programaHorarioUiList.size()>1){
                            if(view!=null)view.showTabPrograma();
                        }else {
                            if(view!=null)view.hideTabPrograma();
                        }

                        listarHoraio();
                    }

                    @Override
                    public void onError() {
                        showMessage("Error obtener horario programa");
                    }
                });


    }

    private void listarHoraio() {
        handler.execute(getReporteHorario,
                new GetReporteHorario.RequestValues(programaHorarioUiList, programaHorarioUi, cursoUiList),
                new UseCase.UseCaseCallback<GetReporteHorario.ResponseValue>() {
                    @Override
                    public void onSuccess(GetReporteHorario.ResponseValue response) {
                        List<HoraUi> fila = new ArrayList<>(response.getHoraUiList());
                        List<DiaUi> diaUiList = new ArrayList<>(response.getDiaUiList());
                        List<List<Object>> lists = new ArrayList<>(response.getLists());
                        if(view!=null){
                            if(fila.size()>0) view.showHorario(diaUiList, fila, lists);
                            else {
                                view.showTextEmpty();
                                hideProgress();
                            }
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void ProgramaHorarioUi(ProgramaHorarioUi programaHorarioUi) {
        showProgress();
        if(this.programaHorarioUi!=null)this.programaHorarioUi.setSelect(false);
        programaHorarioUi.setSelect(true);
        this.programaHorarioUi = programaHorarioUi;
        if(view!=null)view.showListaProgramaEducativo(programaHorarioUiList);
        listarHoraio();
    }

    @Override
    public void postShowHorario() {
        hideProgress();
    }
}
