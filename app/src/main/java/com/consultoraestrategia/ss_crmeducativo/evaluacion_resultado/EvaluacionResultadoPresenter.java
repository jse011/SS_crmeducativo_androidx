package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoView;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public interface EvaluacionResultadoPresenter extends BasePresenter<EvaluacionResultadoView> {
    //void setExtras(int mIdCargaCurso, int mIdCurso);
    void setExtras(Bundle args);


    void showFragmentInfoAlumno(int row);

    void setResources(Resources resources);
}
