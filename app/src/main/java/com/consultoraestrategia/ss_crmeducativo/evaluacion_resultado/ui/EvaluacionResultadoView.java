package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.EvaluacionResultadoPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.TipoNotaUi;

import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public interface EvaluacionResultadoView extends BaseView<EvaluacionResultadoPresenter> {
    void showProgress();

    void hideProgress();

    void showTableEvaluacion(List<AlumnoUi>alumnoUis, List<RubroEvaluacionUi> rubroEvaluacionUis,List<List<Object>> celdaUiList, RubroEvaluacionUi rubroEvaluacionUi);

    void showDialogMensajes(AlumnoUi alumnoUi);

    void showEmptyView(String mensaje);

    void showError(String error);

    void showFragmentInfoAlumno(AlumnoUi alumnoUiSeleted);
}
