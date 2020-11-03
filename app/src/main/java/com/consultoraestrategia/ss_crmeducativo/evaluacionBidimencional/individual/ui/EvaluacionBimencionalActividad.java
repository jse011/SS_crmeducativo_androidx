package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.local.EvalRubBidLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.remote.EvalRubBidRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.EvaluacionRubricaBidimencionalPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionBimencionalAbstractActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionRubricaBidimencionalPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvaluacionRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.SearchAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdateEvaluacionFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.utils.InjectorRepositoryRubroList;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class EvaluacionBimencionalActividad extends EvaluacionBimencionalAbstractActividad {

    public static final String TAG = EvaluacionBimencionalActividad.class.getSimpleName();



    public static void launchEvaluacionBimencionalActividadIndividual(Context context, Bundle args, String rubBidId, int cargaCursoId) {
        Intent intent = new Intent(context, EvaluacionBimencionalActividad.class);
        args.putString(EvaluacionBimencionalActividad.EXTRA_RUB_BID_ID, rubBidId);
        args.putInt(EvaluacionBimencionalActividad.EXTRA_CARGACURSO_ID, cargaCursoId);
        intent.putExtras(args);
        Log.d(TAG, "Bundle: " + args);
        context.startActivity(intent);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EvaluacionRubricaBidimencionalPresenter getPresenter() {
        EvalRubBidRepository repository = new EvalRubBidRepository(new EvalRubBidLocalDataSource(), new EvalRubBidRemoteDataSource());
        return new EvaluacionRubricaBidimencionalPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetRubBid(repository),
                new GetAlumnoConProc(repository),
                new EvalAlumnosProcesoBid(repository),
                new GetIndicadorRubro(repository),
                new UpdateEvaluacionFormula(repository),
                new SearchAlumnoConProc(repository),
                new EvaluacionRubroFormula(InjectorRepositoryRubroList.getEvaluacionFormulaRepository())
                , new CalcularMediaDesviacion(repository));
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }
}
