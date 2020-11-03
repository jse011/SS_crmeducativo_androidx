package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.local.EvalRubBidLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.remote.EvalRubBidRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.ui.EvaluacionBimencionalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionRubricaBidimencionalPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal.EvaluacionRubricaBidimencionalGrupalPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionBimencionalAbstractActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvaluacionRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetGrupoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdateEvaluacionFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.utils.InjectorRepositoryRubroList;

/**
 * Created by SCIEV on 5/04/2018.
 */

public class EvaluacionBimencionalGrupalActividad extends EvaluacionBimencionalAbstractActividad {
    public static final String TAG = EvaluacionBimencionalGrupalActividad.class.getSimpleName();



    public static void launchEvalRubBidActivity(Context context, Bundle args, String rubBidId, int cargaCursoId) {
        Intent intent = new Intent(context, EvaluacionBimencionalGrupalActividad.class);
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
        return new EvaluacionRubricaBidimencionalGrupalPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetRubBid(repository),
                new GetGrupoConProc(repository),
                new EvalAlumnosProcesoBid(repository),
                new GetIndicadorRubro(repository),
                new UpdateEvaluacionFormula(repository),
                new EvaluacionRubroFormula(InjectorRepositoryRubroList.getEvaluacionFormulaRepository())
        ,       new CalcularMediaDesviacion(repository));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem search = menu.findItem(R.id.action_search);
        search.setVisible(false);
        return true;
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
       finish();
    }


}
