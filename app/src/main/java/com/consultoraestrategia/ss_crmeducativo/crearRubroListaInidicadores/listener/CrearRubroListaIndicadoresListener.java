package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener;

import java.util.ArrayList;

/**
 * Created by SCIEV on 23/01/2018.
 */

public interface CrearRubroListaIndicadoresListener {
    void onSelectIndicadorCampotematicovoid(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds);
    void onSalirListaIndicadores();
}
