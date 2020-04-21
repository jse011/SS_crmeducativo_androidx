package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaColumnaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaFilaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.EvaluacionFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.view.EvaluacionFormulaActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.view.EvaluacionFormulaView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCIE on 03/05/2018.
 */

public class EvaluacionFormulaPresenterImpl extends BasePresenterImpl<EvaluacionFormulaView> implements EvaluacionFormulaPresenter {
    public static final String EVALUACION_FORMULA_PRESENTER_TAG = EvaluacionFormulaPresenterImpl.class.getSimpleName();
    private RubroProcesoUi rubroProcesoUi;
    private int cargaCursoId;
    private int cursoId;
    private EvaluacionFormula evaluacionFormula;


    public EvaluacionFormulaPresenterImpl(UseCaseHandler handler, Resources res, EvaluacionFormula evaluacionFormula) {
        super(handler, res);
        this.evaluacionFormula = evaluacionFormula;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        rubroProcesoUi = Parcels.unwrap(extras.getParcelable(EvaluacionFormulaActivity.ARG_RUBRO_PROCE));//rubroEvaluacionProcesoUi
        cargaCursoId = extras.getInt(FragmentAbstract.ARG_ID_CARGA_CURSO);
        cursoId = extras.getInt(FragmentAbstract.ARG_ID_CURSO);
        Log.d(EVALUACION_FORMULA_PRESENTER_TAG, " extras : " + rubroProcesoUi.getTitulo() + " / cargaCurso : " + cargaCursoId + " / curso :" + cursoId);
    }

    List<AlumnosUi> alumnosConNotas;
    List<EvaluacionFormula_RubroEvaluacionUi> rubros = new ArrayList<>();
    List<NotaUi> notas;

    private void iniciarListas(int cargaCursoId, int cursoId, RubroProcesoUi rubroProcesoUi) {
       if(view!=null)view.showProgress();
        handler.execute(evaluacionFormula,
                new EvaluacionFormula.RequestValues(rubroProcesoUi, cargaCursoId, cursoId),
                new UseCase.UseCaseCallback<EvaluacionFormula.ResponseValue>() {
                    @Override
                    public void onSuccess(EvaluacionFormula.ResponseValue response) {

                        alumnosConNotas = response.getAlumnosUis();

                        for (AlumnosUi alumnosUi : alumnosConNotas)notas = alumnosUi.getNotaUiList();
                        setRubros();
                        mostrarListaTablas(alumnosConNotas, rubros, response.getFormulaCeldasList());
                        if(view!=null)view.hideProgress();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

   /* private List<FormulaColumnaCabecera> formulaColumnaCabeceraList;
    private List<List<FormulaCelda>> formulaCeldaList;
    private List<FormulaCelda> formulaFilaCabecerasList;*/

    private void mostrarListaTablas(List<AlumnosUi> alumnosUis, List<EvaluacionFormula_RubroEvaluacionUi> rubroEvaluacionUis, List<List<FormulaCelda>> formulaCeldas) {
        Log.d(getTag(), "rubroEvaluacionUis : " + rubroEvaluacionUis.size());
        String notaId = null;
        try {

            List<FormulaColumnaCabecera> formulaColumnaCabeceras = (List<FormulaColumnaCabecera>) (Object) rubroEvaluacionUis;
            List<FormulaFilaCabecera> formulaFilaCabeceras = (List<FormulaFilaCabecera>) (Object) alumnosUis;

            List<List<FormulaCelda>> formulaCeldas2 = new ArrayList<>();
            List<FormulaCelda> formulaCeldas1 = new ArrayList<>();
            formulaCeldas1.addAll(notas);
            formulaCeldas2.add(formulaCeldas1);
            // List<List<FormulaCelda>> formulaCeldas2 = (List<List<FormulaCelda>>) (Object) notasUis;
            /*formulaColumnaCabeceraList = new ArrayList<>();
         formulaColumnaCabeceraList.addAll(formulaColumnaCabeceras);*/
                view.mostrarListaTablas(formulaColumnaCabeceras, formulaFilaCabeceras, formulaCeldas);
        } catch (Exception ignored) {

        }
    }


    @Override
    protected String getTag() {
        return EVALUACION_FORMULA_PRESENTER_TAG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (view != null) view.mostrarTitulo(rubroProcesoUi.getTitulo());
        iniciarListas(cargaCursoId, cursoId, rubroProcesoUi);
    }

    private void setRubros() {
        rubros.clear();
        if (!alumnosConNotas.isEmpty()) {
            AlumnosUi alumnoUi = alumnosConNotas.get(0);
            this.notas = alumnoUi.getNotaUiList();
            for (NotaUi nota : notas) {
                rubros.add(nota.getRubro());
            }
        }
    }

    private void setNotas() {
        notas.clear();
        if (!notas.isEmpty()) {
            AlumnosUi alumnoUi = alumnosConNotas.get(0);
            //for (NotaUi nota : alumnoUi.getNotaUiList()) {
            notas.addAll(alumnoUi.getNotaUiList());
            // }
        }
    }

    @Override
    public void onClickRubroEvalProceso(EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi) {
        if(view!=null)view.showInfoRubroEvalProceso(rubroProcesoUi);
    }

    @Override
    public void onClickAlumno(AlumnosUi alumnosUi) {
        if(view!=null)view.showInfoUsuario(alumnosUi);
    }


}
