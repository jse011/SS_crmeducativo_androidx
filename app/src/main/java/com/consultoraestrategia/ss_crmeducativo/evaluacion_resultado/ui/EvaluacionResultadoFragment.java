package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.EvaluacionResultadoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.EvaluacionResultadoRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.local.EvaluacionResultadoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.remote.EvaluacionResultadoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.domain.usecase.GetRubroEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;

/**
 * Created by @stevecampos on 8/01/2018.
 */

public class EvaluacionResultadoFragment extends EvaluacionResultadoAbstractFragment<EvaluacionResultadoFragment, EvaluacionResultadoPresenterImpl> {

    public EvaluacionResultadoFragment() {
    }

    @Override
    protected EvaluacionResultadoPresenterImpl getPresenter() {
        EvaluacionResultadoRepository repository = new EvaluacionResultadoRepository(new EvaluacionResultadoLocalDataSource(), new EvaluacionResultadoRemoteDataSource());
        return new EvaluacionResultadoPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),new GetRubroEvaluacionList(repository)
        );
    }

}
