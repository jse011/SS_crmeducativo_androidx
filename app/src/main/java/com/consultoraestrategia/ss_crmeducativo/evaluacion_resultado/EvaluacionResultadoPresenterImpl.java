package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado;

import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.domain.usecase.GetRubroEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;

import java.util.ArrayList;

/**
 * Created by @stevecampos on 18/10/2017.
 */

public class EvaluacionResultadoPresenterImpl extends EvaluacionAbstractPresenterImpl {

    private static final String TAG = EvaluacionResultadoPresenterImpl.class.getSimpleName();
    private GetRubroEvaluacionList getRubroEvaluacionList;

    public EvaluacionResultadoPresenterImpl(UseCaseHandler handler, GetRubroEvaluacionList getRubroEvaluacionList) {
        super(handler);
        this.getRubroEvaluacionList = getRubroEvaluacionList;
    }

    @Override
    protected void getAlumnosConNotas(final Callback<AlumnoUi> callback) {
        Log.d(TAG, "getAlumnosConNotas" + mIdRubroEvalResultado);
        //if (periodoSelected == null) return;
        handler.execute(
                getRubroEvaluacionList,
                new GetRubroEvaluacionList.RequestValues(mIdRubroEvalResultado, tipo),
                new UseCase.UseCaseCallback<GetRubroEvaluacionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroEvaluacionList.ResponseValue response) {
                        Log.d(TAG, "getAlumnosConNotas onSuccess");
                        callback.onListLoaded(response.getAlumnosList());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "getAlumnosConNotas onError");
                        callback.onListLoaded(new ArrayList<AlumnoUi>());
                    }
                }
        );
    }



}
