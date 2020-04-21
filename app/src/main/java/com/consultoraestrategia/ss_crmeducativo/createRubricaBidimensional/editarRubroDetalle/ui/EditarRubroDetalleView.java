package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.EditarRubroDetallePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.listener.EditarRubroDetalleCallBack;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

import java.util.List;

/**
 * Created by @stevecampos on 28/02/2018.
 */

public interface EditarRubroDetalleView extends BaseView<EditarRubroDetallePresenter>, EditarRubroDetalleCallBack {

    void showIndicador(IndicadorUi indicadorUi);

    void showCompetencia(CompetenciaUi competenciaOwner);

    void showCapacidad(CapacidadUi capacidadOwner);

    void showCampoAccionList(List<CampoAccionUi> campoAccionList);

    void formatMinimizarTextDesmepenio(int maxLinTex);

    void formatMaximizarTextDesmepenio();

    void changeTextoVerMasDesempenio(String s);

    void enabledVerMas(int maxLinTex);

    void showTipoNota(TipoNotaUi tipoNotaUi, List<Cell> cellList);

    void disabledCampoAccion();

    void enabledCampoAccion();

    void showCriterioEvaluacion(List<CriterioEvaluacionUi> criterioEvaluacionUiList);

    void hideCriterioEvaluacion();

    void setTituloRubro(String editableTitulo);

    void showDialogEditCriterio(CriterioEvaluacionUi criterioEvaluacionUi);
}

