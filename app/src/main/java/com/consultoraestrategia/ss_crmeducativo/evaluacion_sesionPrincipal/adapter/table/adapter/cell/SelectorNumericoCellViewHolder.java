package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectorNumericoCellViewHolder extends AbstractViewHolder {
    private static final String TAG = SelectorNumericoCellViewHolder.class.getSimpleName();
    @BindView(R.id.txtResultado)
    public TextView txtResultado;
    private NotaUi notaUi;

    public SelectorNumericoCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindBody(NotaUi notaUi) {
        this.notaUi = notaUi;
        txtResultado.setText(String.valueOf(notaUi.getNota()));
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }
}
