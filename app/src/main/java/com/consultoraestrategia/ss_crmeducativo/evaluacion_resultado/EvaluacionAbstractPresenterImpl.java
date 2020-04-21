package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoAbstractFragment.ARG_ID_RUBRO_EVAL_RESULTADO;
import static com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoAbstractFragment.ARG_TIPO_COMPETENCIA;

/**
 * Created by @stevecampos on 5/01/2018.
 */

public abstract class EvaluacionAbstractPresenterImpl implements EvaluacionResultadoPresenter {

    protected abstract void getAlumnosConNotas(Callback<AlumnoUi> callback);
    Resources resources;

    public interface Callback<T> {
        void onListLoaded(List<T> items);

    }

    private static final String TAG = EvaluacionAbstractPresenterImpl.class.getSimpleName();

    private EvaluacionResultadoView view;
    protected UseCaseHandler handler;


    public EvaluacionAbstractPresenterImpl(UseCaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void setResources(Resources resources) {
        this.resources= resources;
    }

    @Override
    public void attachView(EvaluacionResultadoView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        getAlumnosConNotas();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
    }


    private RubroEvaluacionUi rubroEvaluacionUi;
    protected List<RubroEvaluacionUi> rubros = new ArrayList<>();
    protected List<AlumnoUi> alumnosConNotas = new ArrayList<>();
    List<List<Object>> celdaUiList =  new ArrayList<>();

    private void getAlumnosConNotas() {

        Log.d(TAG, "getAlumnosConNotas");
        getAlumnosConNotas(new Callback<AlumnoUi>() {
            @Override
            public void onListLoaded(List<AlumnoUi> alumnoList) {
                alumnosConNotas = alumnoList;
                Log.d(TAG, "TAMANIO NOTAS " + alumnosConNotas.size());
                for(AlumnoUi alumnoUi: alumnosConNotas){

                    List<Object> lista = new ArrayList<>();
                    lista.addAll(alumnoUi.getNotaUiList());
                        celdaUiList.add(lista);
                }
                onNotasLoadedSucess();
            }
        });
    }

    private void onNotasLoadedSucess() {
        Log.d(TAG, "onNotasLoadedSucess");
        if (this.alumnosConNotas.size() > 0) {
            setRubros();
            showAlumnosConNotas(this.alumnosConNotas, this.rubros, this.celdaUiList, this.rubroEvaluacionUi);
        } else {
            switch (tipo){
                case 0:
                    showEmptyView(resources.getString(R.string.empty_data_resultados_capacidad));
                    break;
                case 1:
                    showEmptyView(resources.getString(R.string.empty_data_resultados_competencia));
                    break;
                case 2:
                    showEmptyView(resources.getString(R.string.empty_data_resultados_bimestre));
                    break;
            }
            hideProgress();

        }
    }

    private void setRubros() {
        rubros.clear();

        if (!alumnosConNotas.isEmpty()) {
         AlumnoUi alumnoUi=alumnosConNotas.get(0);
         this.rubroEvaluacionUi=alumnoUi.getNotaUiList().get(0).getRubro();
              List<NotaUi> notas = alumnoUi.getNotaUiList();
              for (NotaUi nota : notas) {
                  rubros.add(nota.getRubro());
              }

        }
    }


    private void showAlumnosConNotas(List<AlumnoUi> alumnoUis, List<RubroEvaluacionUi> rubroEvaluacionUis, List<List<Object>> celdaUiList, RubroEvaluacionUi rubroEvaluacionUi) {
        Log.d(TAG, "showAlumnosConNotas ");
        if (view != null) {
            view.hideProgress();
            view.showTableEvaluacion(alumnoUis,rubroEvaluacionUis, celdaUiList, rubroEvaluacionUi);
        }
    }

    private void showEmptyView(String mensaje) {
        Log.d(TAG, "showEmptyView ");
        if (view != null) {
            view.showEmptyView(mensaje);
        }
    }

    private void hideProgress() {
        if (view != null) {
            view.hideProgress();
        }
    }



    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        view = null;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }

    protected int mIdRubroEvalResultado;
    protected int parametrodisenioid;
    protected int tipo;


    @Override
    public void setExtras(Bundle args) {
        if (args != null) {
            this.mIdRubroEvalResultado = args.getInt(ARG_ID_RUBRO_EVAL_RESULTADO);
            this.parametrodisenioid = args.getInt("parametrodisenioid");
            this.tipo= args.getInt(ARG_TIPO_COMPETENCIA);

        }

        Log.d(TAG, "mIdRubroEvalResultado: " + mIdRubroEvalResultado);
    }
    private void showDialogMensaje(AlumnoUi alumnoUi) {
        Log.d(TAG, "showDialogMensaje");
        if (view != null) {
            view.showDialogMensajes(alumnoUi);
        }
    }



    @Override
    public void showFragmentInfoAlumno(int row) {
        AlumnoUi alumnoUiSeleted=alumnosConNotas.get(row);
        Log.d(TAG, "ALUMNO"+ alumnoUiSeleted.getLastName()+ " id "+ alumnoUiSeleted.getId());
        if(view!=null)view.showFragmentInfoAlumno(alumnoUiSeleted);
    }


}
