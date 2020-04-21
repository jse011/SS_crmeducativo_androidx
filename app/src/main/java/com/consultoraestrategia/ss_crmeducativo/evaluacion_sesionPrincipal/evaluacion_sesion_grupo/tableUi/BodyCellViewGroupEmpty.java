package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;

import butterknife.ButterKnife;

public class BodyCellViewGroupEmpty extends BodyCellViewGroup {

    public BodyCellViewGroupEmpty(Context context) {
        super(context);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluacion_sesion_item_option_empty, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {

    }

    @Override
    public void clickView(Object row, Object colum) {

    }

    @Override
    public NotaUi getNotaUi() {
        return null;
    }

    @Override
    public void bindBody(Object item, int row, int column) {

    }

}
