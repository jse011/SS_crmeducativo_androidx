package com.consultoraestrategia.ss_crmeducativo.situacion.source;

import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.situacion.usecase.GetSituacionListUI;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public interface SituacionDataSource {

    List<SituacionUI> getSituacionUIList(GetSituacionListUI.RequestValues requestValues);

}
