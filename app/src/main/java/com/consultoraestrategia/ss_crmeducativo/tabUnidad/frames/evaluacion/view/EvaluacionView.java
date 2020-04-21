package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.presenter.EvaluacionUnidadPresenter;

import java.util.List;

public interface EvaluacionView  extends BaseView<EvaluacionUnidadPresenter> {
    void setListIndicadores(List<IndicadorUi> indicadorUis);

    void setListRubricas(List<RubricaUi> rubricaUiList);
    void showEvaluacionGrupalRubrica(CRMBundle bundle, int cargaCursoId, String rubrica);
    void showEvaluacionIndividualRubrica(CRMBundle bundle, int cargaCursoId, String rubrica);
    void initAdapter();

    void setListCompetenciasRubros(List<Object> objects);

    void showEvaluacionRubroIndividual(String idRubrica, String titulo, int i, int cargaCursoId, int i1, boolean b, String tipoNotaId, int i2, int i3, int programaEducativoId);

    void showEvaluacionRubroGrupal(String idRubrica, String titulo, int i, int cargaCursoId, int i1, int cargaAcademicaId, boolean b, String tipoNotaId, int programaEducativoId);
    void showTxtVacioRubrica();
    void hideTxtVacioRubrica();

    void showTxtVacioRubro();

    void hideTxtVacioRubro();
}
