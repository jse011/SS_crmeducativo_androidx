package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TecladoNumericoCellViewHolder extends AbstractViewHolder {
    @BindView(R.id.txtResultado)
    public TextView txtResultado;
    private NotaUi notaUi;

    public TecladoNumericoCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindBody(NotaUi notaUi) {
        this.notaUi = notaUi;
        txtResultado.setText(String.valueOf(Utils.formatearDecimales(notaUi.getNota(), 2)));
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }
}
