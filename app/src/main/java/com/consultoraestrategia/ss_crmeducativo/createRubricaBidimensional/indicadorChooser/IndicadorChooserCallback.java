package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import java.util.List;

/**
 * Created by @stevecampos on 6/02/2018.
 */

public interface IndicadorChooserCallback {
    void onIndicadorListOk(List<CompetenciaUi> competenciaList);
    void onIndicadorListCancel();
}
