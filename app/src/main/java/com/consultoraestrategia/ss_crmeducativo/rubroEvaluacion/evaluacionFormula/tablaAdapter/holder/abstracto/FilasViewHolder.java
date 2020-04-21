package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaColumnaCabecera;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;

/**
 * Created by kike on 11/05/2018.
 */

public abstract class FilasViewHolder<T extends FormulaColumnaCabecera> extends AbstractSorterViewHolder {

    public FilasViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T columnHeader);

}
