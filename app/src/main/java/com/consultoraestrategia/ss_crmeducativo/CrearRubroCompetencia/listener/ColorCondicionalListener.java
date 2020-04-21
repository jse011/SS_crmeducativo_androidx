package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;

/**
 * Created by SCIEV on 6/11/2017.
 */

public interface ColorCondicionalListener {
    void onClickRango(ColorCondicionalUi colorCondicionalUi);
    void onClickDesde(ColorCondicionalUi colorCondicionalUi);
    void onClickHascta(ColorCondicionalUi colorCondicionalUi);
    void onClickEliminar(ColorCondicionalUi colorCondicionalUi);
    void onClickColorTexto(ColorCondicionalUi colorCondicionalUi);
    void onClickColorFondo(ColorCondicionalUi colorCondicionalUi);
}
