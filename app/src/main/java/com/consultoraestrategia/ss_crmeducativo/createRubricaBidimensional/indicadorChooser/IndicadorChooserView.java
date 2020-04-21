package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import java.util.List;

/**
 * Created by @stevecampos on 6/02/2018.
 */

public interface IndicadorChooserView extends BaseView<IndicadorChooserPresenter> {
    void showCompetenciaList(List<CompetenciaUi> competenciaList);
    void showMensajeVacio();

    void hideMensajeVacio();

    void notyChangeList();

    void updateIndicador(CompetenciaUi competenciaUi, IndicadorUi indicadorUi);

    void updateCampotematico(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampoAccionUi campoAccionUi);
}
