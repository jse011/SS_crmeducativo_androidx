package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal;

import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.EvaluacionRubricaBidimencionalAbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvaluacionRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetGrupoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdateEvaluacionFormula;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionRubroEvaluacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class EvaluacionRubricaBidimencionalGrupalPresenterImpl extends EvaluacionRubricaBidimencionalAbstractPresenterImpl {
    private GetGrupoConProc getAlumnConProc;
    private EvalProcUi evalProcUiSelected;
    private String TAG= EvaluacionRubricaBidimencionalGrupalPresenterImpl.class.getSimpleName();

    public EvaluacionRubricaBidimencionalGrupalPresenterImpl(UseCaseHandler handler, Resources res, GetRubBid getRubBid, GetGrupoConProc getAlumnConProc, EvalAlumnosProcesoBid evalAlumnosProcesoBid, GetIndicadorRubro getIndicador, GetFechaCreacionRubroEvaluacion getFechaCreacionRubroEvaluacion, UpdateEvaluacionFormula updateEvaluacionFormula,
                                                             EvaluacionRubroFormula evaluacionRubroFormula, CalcularMediaDesviacion calcularMediaDesviacion) {
        super(handler, res, getRubBid, evalAlumnosProcesoBid,getIndicador, getFechaCreacionRubroEvaluacion,updateEvaluacionFormula,evaluacionRubroFormula, calcularMediaDesviacion);
        this.getAlumnConProc = getAlumnConProc;
    }

    @Override
    protected String getTituloTable() {
        return "Grupos";
    }

    @Override
    protected void getAlumnConProc() {

        handler.execute(
                getAlumnConProc,
                new GetGrupoConProc.RequestValues(rubBidId, cursoId, cargaCursoId, entidadId, georeferenciaId),
                new UseCase.UseCaseCallback<GetGrupoConProc.ResponseValue>() {
                    @Override
                    public void onSuccess(GetGrupoConProc.ResponseValue response) {
                        rubBidUi = response.getRubBidUi();
                        columnheaderList = response.getAlumnoProcesoUis();
                        cellLists = response.getCellList();
                        grupoProcesoUis = response.getGrupoProcesoUis();
                        //showAlumnos(response.getAlumnoProcesoUis());


                        if (columnheaderList.size() == 0) {
                            if (view!=null)view.showFrameLayoutGrupos();
                            showTemList();
                        }
                        Log.d(getTag(), "size"+columnheaderList.size()+" " + cellLists.size() + " " + grupoProcesoUis.size());
                        showAlumnosConRubricaTable();
                    }

                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                    }
                }
        );
    }


    @Override
    public void onClickAlumno(AlumnoProcesoUi alumnoProcesoUi) {
        if(view!=null){
            if(alumnoProcesoUi.isVigente())
            view.showinfoUsuario(alumnoProcesoUi, rubBidId);
        }
    }


    @Override
    public void onClikCornerTableView() {

    }

    @Override
    public void onClickSelector(EvalProcUi evalProcUi) {
        Log.d(TAG, "onClickSelector");
        if(view==null)return;
        this.evalProcUiSelected = evalProcUi;
        switch (evalProcUiSelected.getFormaEvaluar()){
            case GRUPAL:
                view.startEvalBidInd();
                break;
            case INDIVIDUAL:
                if (evalProcUiSelected.isAlumnoActivo())view.startEvalBidInd();
        }

    }

    private void onClickSelectorGrupal(String equipoId) {
        Log.d(TAG, "onClickSelectorGrupal");
        try {
            GrupoProcesoUi grupoProcesoUi = new GrupoProcesoUi();
            grupoProcesoUi.setId(equipoId);
            grupoProcesoUi = grupoProcesoUis.get(grupoProcesoUis.indexOf(grupoProcesoUi));
            if (evalRubBidIndView!=null)evalRubBidIndView.setData(grupoProcesoUi, rubBidUi, new ArrayList<ColumnHeader>(grupoProcesoUis));
            super.ocultarMsgActualizacion();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void onClickSelectorIndividual(int alumnoId, String equipoId) {
        try {
            Log.d(TAG, "onClickSelectorIndividual");
            GrupoProcesoUi grupoProcesoUi = new GrupoProcesoUi();
            grupoProcesoUi.setId(equipoId);
            grupoProcesoUi = grupoProcesoUis.get(grupoProcesoUis.indexOf(grupoProcesoUi));
            List<AlumnoProcesoUi> alumnoProcesoUiList = grupoProcesoUi.getAlumnoProcesoUis();
            AlumnoProcesoUi alumnoProcesoUi = new AlumnoProcesoUi();
            alumnoProcesoUi.setId(alumnoId);
            alumnoProcesoUi = alumnoProcesoUiList.get(alumnoProcesoUiList.indexOf(alumnoProcesoUi));
            if (evalRubBidIndView!=null)evalRubBidIndView.setData(alumnoProcesoUi, rubBidUi, new ArrayList<ColumnHeader>(alumnoProcesoUiList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreateDialogEvalRubrica() {
        Log.d(TAG, "onCreateDialogEvalRubrica");
        if(evalProcUiSelected==null)return;

        if(evalProcUiSelected.getFormaEvaluar() == EvalProcUi.FormaEvaluar.GRUPAL){
            onClickSelectorGrupal(evalProcUiSelected.getEquipoId());
        }else{
            onClickSelectorIndividual(evalProcUiSelected.getAlumnoId(),evalProcUiSelected.getEquipoId());
        }
    }
}
