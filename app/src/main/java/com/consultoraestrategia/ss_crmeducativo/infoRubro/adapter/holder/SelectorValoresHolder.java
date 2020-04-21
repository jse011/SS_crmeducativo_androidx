package com.consultoraestrategia.ss_crmeducativo.infoRubro.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.ValorTipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;



public class SelectorValoresHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_texto)
    TextView tipoTexto;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.detalle)
    TextView detalle;

    public SelectorValoresHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi){
        tipoTexto.setText(valorTipoNotaUi.getTitulo());
        nombre.setText(valorTipoNotaUi.getAlias());
        if (valorTipoNotaUi.getDetalle()==null || valorTipoNotaUi.getDetalle().isEmpty())
            detalle.setText("Sin Descripcion");
        else detalle.setText(valorTipoNotaUi.getDetalle());
    }

}
