package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto;

import android.view.View;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by kike on 11/05/2018.
 */

public abstract class CeldasViewHolder <T extends FormulaCelda> extends AbstractViewHolder {

    public CeldasViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(T cell);
}
