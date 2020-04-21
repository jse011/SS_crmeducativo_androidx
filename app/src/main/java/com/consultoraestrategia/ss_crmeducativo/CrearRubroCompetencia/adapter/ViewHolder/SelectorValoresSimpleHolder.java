package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.R;

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
        tipoTexto.setText(valorTipoNotaUi.getTitulo());
        nombre.setText(valorTipoNotaUi.getDescripcion());
    }

}
