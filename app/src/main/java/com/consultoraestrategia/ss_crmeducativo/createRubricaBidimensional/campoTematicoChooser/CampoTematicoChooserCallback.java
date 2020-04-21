package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoTematicoChooser;


import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import java.util.List;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public interface CampoTematicoChooserCallback {
    void onCampoTematicoListOk(List<IndicadorUi> indicadorList);
    void onCampoTematicoCancel();
}
