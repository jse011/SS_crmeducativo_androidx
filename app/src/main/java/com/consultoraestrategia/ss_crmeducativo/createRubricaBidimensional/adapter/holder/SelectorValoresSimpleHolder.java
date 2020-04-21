package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class SelectorValoresSimpleHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_texto)
    TextView tipoTexto;
    @BindView(R.id.nombre)
    TextView nombre;

    public SelectorValoresSimpleHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(null);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi){
        tipoTexto.setText(valorTipoNotaUi.getTitle());
        nombre.setText(valorTipoNotaUi.getAlias());
    }

}
