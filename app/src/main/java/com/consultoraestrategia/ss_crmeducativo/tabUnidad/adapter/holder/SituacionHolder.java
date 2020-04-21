package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SituacionHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.txtDescripcionSFirst)
    TextView txtDescripcionSFirst;
    @BindView(R.id.txtSubTitulo)
    TextView txtSubTitulo;
    @BindView(R.id.txtDescripcionSecond)
    TextView txtDescripcionSecond;
    @BindView(R.id.imgTipoSubtitulo)
    ImageView imgTipoSubtitulo;

    public SituacionHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(SituacionUi situacionUi){
        txtTitulo.setText(situacionUi.getTitulo());
        txtDescripcionSFirst.setText(situacionUi.getDescripcionTipo());
        txtSubTitulo.setText(situacionUi.getSubtitulo());
        txtDescripcionSecond.setText(situacionUi.getDescripcionSubtitulo());

        switch (situacionUi.getTipo()) {
            case SITUACION_SIG:
                Drawable desafio = itemView.getResources().getDrawable(R.drawable.ic_situacion_desafio);
                desafio.mutate().setColorFilter(itemView.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                imgTipoSubtitulo.setImageDrawable(desafio);
                break;
            case IDEA_CLAVE:
                Drawable reto = itemView.getResources().getDrawable(R.drawable.ic_situacion_reto);
                reto.mutate().setColorFilter(itemView.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                imgTipoSubtitulo.setImageDrawable(reto);
                break;
        }

    }
}
