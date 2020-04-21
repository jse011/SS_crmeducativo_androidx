package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.PrecisionAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.ValorTipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class SelectorValoresHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_texto)
    TextView tipoTexto;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.txt_valor_numerico)
    TextView txtValorNumerico;
    @BindView(R.id.txt_is_precision)
    TextView txtIsPrecision;

    @BindView(R.id.rc_presicion)
    RecyclerView rcPrecision;

    public SelectorValoresHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(null);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi){
        tipoTexto.setText(valorTipoNotaUi.getTitle());
        nombre.setText(valorTipoNotaUi.getAlias());
        txtValorNumerico.setText(String.valueOf(valorTipoNotaUi.getValorDefecto()));
        txtIsPrecision.setText(valorTipoNotaUi.getPrecisionList()==null||valorTipoNotaUi.getPrecisionList().isEmpty()?"No":"Sí");
        rcPrecision.setLayoutManager(new GridLayoutManager(itemView.getContext(),6));
        rcPrecision.setAdapter(new PrecisionAdapter(valorTipoNotaUi.getPrecisionList()));
        rcPrecision.setNestedScrollingEnabled(false);
        rcPrecision.setHasFixedSize(false);

    }

}
