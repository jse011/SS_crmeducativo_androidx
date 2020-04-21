package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BodyCellViewGroupComentario extends BodyCellViewGroup{


    public BodyCellViewGroupComentario(Context context) {
        super(context);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluacion_sesion_item_option_comentario, this, true);
        unbinder = ButterKnife.bind(this, view);
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
    public void bindBody(AlumnosEvaluacionUi item, int row, int column) {

    }


}
