package com.consultoraestrategia.ss_crmeducativo.infoRubro.ui;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CampoTematicoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoNotaUi;

import java.util.List;

/**
 * Created by SCIEV on 28/08/2018.
 */

public interface InfoRubroView extends BaseView<InfoRubroPresenter> {

    void showRubro(RubroEvalProcesoUi rubroEvalProcesoUi);

    void showIndicador(IndicadorUi indicadorUi, CompetenciaUi competenciaUi);

    void showDesempenio(DesempenioUi desempenioUi);

    void showCompetencia(CompetenciaUi competenciaOwner);

    void showCapacidad(CapacidadUi capacidadOwner);

    void showCampoAccionList(List<CampoTematicoUi> campoAccionList);

    void showTipoNota(TipoNotaUi tipoNotaUi);

    void formatMinimizarTextDesmepenio(int maxLinTex);

    void formatMaximizarTextDesmepenio();

    void changeTextoVerMasDesempenio(String s);

    void enabledVerMas(int maxLinTex);
}
