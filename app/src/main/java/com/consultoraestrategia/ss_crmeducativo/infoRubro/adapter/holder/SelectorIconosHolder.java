package com.consultoraestrategia.ss_crmeducativo.infoRubro.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;



public class SelectorIconosHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tipo_imagen)
    ImageView tipoImagen;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.detalle)
    TextView detalle;

    public SelectorIconosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ValorTipoNotaUi valorTipoNotaUi){
        Glide.with(itemView.getContext())
                .load(valorTipoNotaUi.getIcono())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                .into(tipoImagen);

        nombre.setText(valorTipoNotaUi.getAlias());

        if (valorTipoNotaUi.getDetalle()==null || valorTipoNotaUi.getDetalle().isEmpty())
            detalle.setText("Sin Descripcion");
        else detalle.setText(valorTipoNotaUi.getDetalle());
    }

}
