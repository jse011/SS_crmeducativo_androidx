package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import android.widget.FrameLayout;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;


/**
 * Created by SCIEV on 23/02/2018.
 */

public class BodyCellViewUi {

    private FrameLayout frameLayout;
    private AbstractViewHolder abstractViewHolder;

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public void setFrameLayout(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    public void setAbstractViewHolder(AbstractViewHolder abstractViewHolder) {
        this.abstractViewHolder = abstractViewHolder;
    }

    public AbstractViewHolder getAbstractViewHolder() {
        return abstractViewHolder;
    }
}
