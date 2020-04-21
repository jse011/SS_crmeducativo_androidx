package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class SelectorIconosSimpleHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_imagen)
    ImageView tipoImagen;
    @BindView(R.id.nombre)
    TextView nombre;

    public SelectorIconosSimpleHolder(View itemView) {
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
    }

}
