package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.changePesoIndicador;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

/**
 * Created by @stevecampos on 19/02/2018.
 */

public interface PesoIndicadorCellCallback {
    void onBtnNegativeClickedPesoIndicadorCell();
    void onBtnPostivePesoIndicadorCell(IndicadorUi indicadorUi, int x, int y);
}
