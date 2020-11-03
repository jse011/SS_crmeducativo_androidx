package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui.EvalRubBidIndView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionBimencionalAbstractActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionRubricaBidimencionalPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvaluacionRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdateEvaluacionFormula;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 8/03/2018.
 */

public abstract class EvaluacionRubricaBidimencionalAbstractPresenterImpl extends BasePresenterImpl<EvaluacionRubricaBidimencionalView> implements EvaluacionRubricaBidimencionalPresenter {

    protected String rubBidId;
    protected int cargaCursoId;
    protected int entidadId;
    protected int georeferenciaId;
    protected int cursoId;
    protected int idCargaAcademica;
    protected int programaEducativoId;
    protected String titulo = getTituloTable();
    protected GetRubBid getRubBid;
    protected RubBidUi rubBidUi;
    protected GetIndicadorRubro getIndicador;
    protected EvalAlumnosProcesoBid evalAlumnosProcesoBid;
    protected UpdateEvaluacionFormula updateEvaluacionFormula;
    protected List<ColumnHeader> columnheaderList;
    protected List<List<Cell>> cellLists;
    protected List<RowHeader> rowHeaderList;
    protected List<GrupoProcesoUi> grupoProcesoUis;
    private EvaluacionRubroFormula evaluacionRubroFormula;
    private long f_CreacionServidor;
    private long f_CreacionLocal;
    protected FiltroTableUi filtroTableUi = new FiltroTableUi();
    private String TAG =EvaluacionRubricaBidimencionalAbstractPresenterImpl.class.getSimpleName();
    private CalcularMediaDesviacion calcularMediaDesviacion;
    protected boolean isCalendarioVigente;
    protected EvalRubBidIndView evalRubBidIndView;

    public EvaluacionRubricaBidimencionalAbstractPresenterImpl(UseCaseHandler handler, Resources res, GetRubBid getRubBid, EvalAlumnosProcesoBid evalAlumnosProcesoBid, GetIndicadorRubro getIndicador, UpdateEvaluacionFormula updateEvaluacionFormula,
                                                               EvaluacionRubroFormula evaluacionRubroFormula, CalcularMediaDesviacion calcularMediaDesviacion) {
        super(handler, res);
        this.getRubBid = getRubBid;
        this.evalAlumnosProcesoBid = evalAlumnosProcesoBid;
        this.getIndicador = getIndicador;
        columnheaderList = new ArrayList<>();
        this.updateEvaluacionFormula = updateEvaluacionFormula;
        this.evaluacionRubroFormula = evaluacionRubroFormula;
        this.calcularMediaDesviacion=calcularMediaDesviacion;
    }

    protected abstract String getTituloTable();

    @Override
    protected String getTag() {
        return EvaluacionRubricaBidimencionalAbstractPresenterImpl.class.getSimpleName();
    }

    @Override
    public void attachView(EvaluacionRubricaBidimencionalView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        ///GetFechaCreacionRubroEvaluacion();
        showProgress();
        //getRubBid();
        getAlumnConProc();
        //GetFechaCreacionRubroEvaluacion();
        filtroTableUi.setPersona("");
        filtroTableUi.setOrderByASC(FiltroTableUi.OrderByASC.APELLIDO);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy ");
        //if(view!=null)view.hideCloseDialogProgress();
        if(view!=null)view.hideDialogProgress();
        view =null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void setExtras(Bundle extras) {
        rubBidId = extras.getString(EvaluacionBimencionalAbstractActividad.EXTRA_RUB_BID_ID);
        cargaCursoId = extras.getInt(EvaluacionBimencionalAbstractActividad.EXTRA_CARGACURSO_ID);
        CRMBundle c= new CRMBundle(extras);
        idCargaAcademica=  c.getCargaAcademicaId();
        isCalendarioVigente = c.isCalendarioActivo();
        entidadId = c.getEntidadId();
        georeferenciaId = c.getGeoreferenciaId();
        programaEducativoId = c.getProgramaEducativoId();
        Log.d(TAG, "idCargaAcademica "+ idCargaAcademica+ " cargaCursoId " + cargaCursoId + "rubBidId" + rubBidId);

    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }


    protected void showAlumnosConRubricaTable() {
        Log.d(getTag(), "showAlumnosConRubricaTable");
        try {
            rowHeaderList = new ArrayList<>();
            List<RubEvalProcUi> rubroEvalProcesoList = rubBidUi.getRubroEvalProcesoList();
            NotaUi notaUi = new NotaUi();
            notaUi.setId(null);
            notaUi.setNombre("NF");
            notaUi.setPorcentaje(100);
            rowHeaderList.add(notaUi);
            rowHeaderList.addAll(rubroEvalProcesoList);
            if (view!=null)view.showTitle(rubBidUi.getTitle());
            hideProgress();
            String titulo = this.titulo + " ("+columnheaderList.size()+")";
            if (view!=null)view.showTableview(titulo,rowHeaderList, columnheaderList, cellLists);


        } catch (Exception e) {
            e.printStackTrace();
            String titulo = this.titulo + " (0)";
            if(view!=null)view.showTableview(titulo,new ArrayList<RowHeader>(), new ArrayList<ColumnHeader>(), new ArrayList<List<Cell>>());
        }
    }

    protected abstract void getAlumnConProc();

    protected void showTemList() {
        if (view == null) return;
        view.showTeamList(cargaCursoId, cursoId, idCargaAcademica, rubBidId);
    }

    @Override
    public void onChangeRubricaBidimencional() {
        getAlumnConProc();
    }


    @Override
    public void onSelectGrupo(GrupoProcesoUi grupoProcesoUi) {
        Log.d(getTag(), "Grupo is togle " + grupoProcesoUi.isToogle());
        if (grupoProcesoUi.isToogle()) {
            grupoProcesoUi.setToogle(false);
        } else {
            grupoProcesoUi.setToogle(true);
        }

        List<AlumnoProcesoUi> alumnoProcesoUis = grupoProcesoUi.getAlumnoProcesoUis();
        if (alumnoProcesoUis == null) return;
        int posicion = columnheaderList.indexOf(grupoProcesoUi);
        Log.d(getTag(), "psocion: " + posicion);
        if (posicion == -1) return;
        try {
            List<ColumnHeader> columnHeaders = new ArrayList<>();
            List<List<Cell>> cellList = new ArrayList<>();
            columnHeaders.addAll(alumnoProcesoUis);
            cellList.addAll(grupoProcesoUi.getCellList());

            if (grupoProcesoUi.isToogle()) {
                if (view == null) return;
                view.addRowRange(grupoProcesoUi, columnHeaders, cellList);
            } else {
                if (view == null) return;
                view.removeRowRange(grupoProcesoUi,columnHeaders, cellList);
            }

        } catch (Exception ignored) {
        }

        ocultarMsgActualizacion();

    }

    @Override
    public void onSaveEvaluacion(final EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList) {
        evalAlumnosProcesoBid.execute(new EvalAlumnosProcesoBid.RequestValues(evalProcUi, evalProcUiList), new UseCaseSincrono.Callback<EvalAlumnosProcesoBid.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, EvalAlumnosProcesoBid.ResponseValue value) {

                    }
                }
        );
    }

    @Override
    public void onClickRubroEvaluacion(RubEvalProcUi rubEvalProcUi) {
        //getGetIndicador(rubEvalProcUi.getIcdsId(),rubEvalProcUi.getId());
        if(view!=null)view.showInfoRubro(rubEvalProcUi);

    }


    private void getGetIndicador(int indicadorId, String rubroEvaluacProcesoId){
        handler.execute(getIndicador,
                new GetIndicadorRubro.RequestValues(indicadorId, rubroEvaluacProcesoId),
                new UseCase.UseCaseCallback<GetIndicadorRubro.ResponseValue>() {
                    @Override
                    public void onSuccess(GetIndicadorRubro.ResponseValue response) {
                        if(view == null)return;
                        IndicadorUi indicadorUi = response.getIndicadorUi();
                        if(indicadorUi == null)return;
                        view.showAlertDialogIndicador(indicadorUi);
                    }
                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onBtnActualizarRubro() {
        BEVariables beVariables = new BEVariables();
        beVariables.setRubroEvaluacionId(rubBidId);
        beVariables.setCargaCursoId(cargaCursoId);
        beVariables.setCalendarioPeriodoId(rubBidUi.getCalendarioPeriodoId());
        if(view!=null)view.showActividadImportacion(beVariables);
    }

    @Override
    public void onQueryTextSubmit(String query) {
        filtroTableUi.setPersona(query);
        Log.d(getTag(),filtroTableUi.toString());
        getAlumnConProc();
    }

    @Override
    public void onQueryTextChange(String newText) {
        if(newText.trim().isEmpty()){
            filtroTableUi.setPersona(newText);
            Log.d(getTag(),filtroTableUi.toString());
            getAlumnConProc();
        }

    }

    @Override
    public void onClikCornerTableView() {
        switch (filtroTableUi.getOrderByASC()){
            case APELLIDO:
                titulo = FiltroTableUi.OrderByASC.NOMBRE.getNombre();
                filtroTableUi.setOrderByASC(FiltroTableUi.OrderByASC.NOMBRE);
                break;
            case NOMBRE:
                titulo = FiltroTableUi.OrderByASC.APELLIDO.getNombre();
                filtroTableUi.setOrderByASC(FiltroTableUi.OrderByASC.APELLIDO);
                break;
        }
        Log.d(getTag(),filtroTableUi.toString());
        getAlumnConProc();
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

    protected void ocultarMsgActualizacion(){
        if(view!=null)view.hideMsgActualizacion();
    }

    @Override
    public void recalcularMediaDesviacion(){
        calcularMediaDesviacion.execute(new CalcularMediaDesviacion.Requests(rubBidUi));
        Log.d(getTag(),"recalcularMediaDesviacion");
        //if(view!=null)view.showCloseDialogProgress();
        updateEvaluacionFormula.execute(new UpdateEvaluacionFormula.RequestValues(),
                new UseCaseSincrono.Callback<UpdateEvaluacionFormula.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, UpdateEvaluacionFormula.ResponseValue value) {
                        if(value.isPreload()){
                            //if(view!=null)view.hideCloseDialogProgress();
                            if(view!=null)view.showDialogProgress();
                        }else {
                            if(view!=null)view.onSincronization(programaEducativoId, rubBidId);
                            if(view!=null)view.cerrar();
                        }
                    }
                });


    }

    @Override
    public void onAttachView(EvalRubBidIndView evalRubBidIndView) {
        this.evalRubBidIndView = evalRubBidIndView;
    }

    @Override
    public void onCreateDialogEvalRubrica() {

    }

    @Override
    public void onDestroyDialogEvalRubrica() {
        evalRubBidIndView = null;
    }
}
