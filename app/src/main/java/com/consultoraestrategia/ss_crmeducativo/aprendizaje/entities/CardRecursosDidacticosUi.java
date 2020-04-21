package com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities;

import java.util.List;

/**
 * Created by SCIEV on 17/01/2018.
 */

public class CardRecursosDidacticosUi {

    private List<RecursosDidacticoUi> recursosDidacticoUis;

    public List<RecursosDidacticoUi> getRecursosDidacticoUis() {
        return recursosDidacticoUis;
    }

    public CardRecursosDidacticosUi(List<RecursosDidacticoUi> recursosDidacticoUis) {
        this.recursosDidacticoUis = recursosDidacticoUis;

    }
}
