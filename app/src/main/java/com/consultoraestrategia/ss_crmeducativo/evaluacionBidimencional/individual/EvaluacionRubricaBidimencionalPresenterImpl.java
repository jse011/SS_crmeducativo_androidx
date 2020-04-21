package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual;

import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.EvaluacionRubricaBidimencionalAbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvaluacionRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.SearchAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdateEvaluacionFormula;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionRubroEvaluacion;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class EvaluacionRubricaBidimencionalPresenterImpl extends EvaluacionRubricaBidimencionalAbstractPresenterImpl {
    private GetAlumnoConProc getAlumnConProc;
    private SearchAlumnoConProc searchAlumnoConProc;
    private EvalProcUi evalProcUiSelected;

    public EvaluacionRubricaBidimencionalPresenterImpl(UseCaseHandler handler, Resources res, GetRubBid getRubBid, GetAlumnoConProc getAlumnConProc, EvalAlumnosProcesoBid evalAlumnosProcesoBid, GetIndicadorRubro getIndicador, GetFechaCreacionRubroEvaluacion getFechaCreacionRubroEvaluacion, UpdateEvaluacionFormula updateEvaluacionFormula, SearchAlumnoConProc searchAlumnoConProc,
                                                       EvaluacionRubroFormula evaluacionRubroFormula, CalcularMediaDesviacion calcularMediaDesviacion) {
        super(handler, res, getRubBid, evalAlumnosProcesoBid,getIndicador, getFechaCreacionRubroEvaluacion, updateEvaluacionFormula, evaluacionRubroFormula, calcularMediaDesviacion);
        this.getAlumnConProc = getAlumnConProc;
        this.searchAlumnoConProc = searchAlumnoConProc;
    }

    @Override
    protected String getTituloTable() {
        return "Alumnos";
    }

    @Override
    protected void getAlumnConProc() {
        searchAlumnConProc();
        /*handler.execute(
                getAlumnConProc,
                new GetAlumnoConProc.RequestValues(rubBidId, cursoId, cargaCursoId),
                new UseCase.UseCaseCallback<GetAlumnoConProc.ResponseValue>() {
                    @Override
                    public void onSuccess(GetAlumnoConProc.ResponseValue response) {
                        rubBidUi = response.getRubBidUi();
                        columnheaderList = response.getAlumnoProcesoUis();
                        cellLists = response.getCellList();
                        //showAlumnos(response.getAlumnoProcesoUis());
                        showAlumnosConRubricaTable();
                    }
                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                    }
                }
        );*/
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClickAlumno(AlumnoProcesoUi alumnoProcesoUi) {
        if(view!=null){
            if(alumnoProcesoUi.isVigente())
                view.showinfoUsuario(alumnoProcesoUi, rubBidId);
        }
    }


    @Override
    public void onClickSelector(EvalProcUi evalProcUi) {
        if(view==null)return;
        this.evalProcUiSelected = evalProcUi;
        if(evalProcUiSelected.isAlumnoActivo())
        view.startEvalBidInd();

    }

    private void searchAlumnConProc() {
        handler.execute(
                searchAlumnoConProc,
                new SearchAlumnoConProc.RequestValues(rubBidId,cargaCursoId, filtroTableUi, entidadId, georeferenciaId),
                new UseCase.UseCaseCallback<SearchAlumnoConProc.ResponseValue>() {
                    @Override
                    public void onSuccess(SearchAlumnoConProc.ResponseValue response) {
                        Log.d(getTag(), "size :"+columnheaderList.size());
                        rubBidUi = response.getRubBidUi();
                        columnheaderList = response.getAlumnoProcesoUis();

                        cellLists = response.getCellList();
                        //showAlumnos(response.getAlumnoProcesoUis());
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
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreateDialogEvalRubrica() {
        if(evalProcUiSelected==null)return;

        AlumnoProcesoUi alumnoProcesoUi = new AlumnoProcesoUi();
        alumnoProcesoUi.setId(evalProcUiSelected.getAlumnoId());
        alumnoProcesoUi = (AlumnoProcesoUi) columnheaderList.get(columnheaderList.indexOf(alumnoProcesoUi));
        if(evalRubBidIndView!=null)evalRubBidIndView.setData(alumnoProcesoUi, rubBidUi, columnheaderList);
    }
}
