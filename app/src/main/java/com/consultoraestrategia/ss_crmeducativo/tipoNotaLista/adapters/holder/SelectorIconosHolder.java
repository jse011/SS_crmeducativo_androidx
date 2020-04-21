package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.holder;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.TipoNotaPrecisionAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class SelectorIconosHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_imagen)
    ImageView tipoImagen;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.rc_presicion)
    RecyclerView rcPrecision;
    @BindView(R.id.txt_valor_numerico)
    TextView txtValorNumerico;
    @BindView(R.id.txt_is_precision)
    TextView txtIsPrecision;

    public SelectorIconosHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(null);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi){
            Glide.with(itemView.getContext())
                .load(valorTipoNotaUi.getIcono())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                .into(tipoImagen);
        nombre.setText(valorTipoNotaUi.getAlias());
        txtValorNumerico.setText(String.valueOf(valorTipoNotaUi.getValorNumerico()));
        txtIsPrecision.setText(valorTipoNotaUi.getPrecisionList()==null||valorTipoNotaUi.getPrecisionList().isEmpty()?"No":"Sí");
        rcPrecision.setLayoutManager(new GridLayoutManager(itemView.getContext(), 5));
        rcPrecision.setAdapter(new TipoNotaPrecisionAdapter(valorTipoNotaUi.getPrecisionList()));
    }

}
