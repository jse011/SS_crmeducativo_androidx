package com.consultoraestrategia.ss_crmeducativo.situacion.adapter.holder;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 21/02/2018.
 */

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


    public SituacionHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(final SituacionUI situacionUI) {
        txtTitulo.setText(situacionUI.getTipo());
        txtDescripcionSFirst.setText(situacionUI.getDescripcionTipo());
        txtSubTitulo.setText(situacionUI.getSubtitulo());
        txtDescripcionSecond.setText(situacionUI.getDescripcionSubtitulo());

        switch (situacionUI.getTipoSubTitulo()) {
            case SituacionUI.TIPO_DESAFIO:
                imgTipoSubtitulo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_situacion_desafio));
                break;
            case SituacionUI.TIPO_RETO:
                imgTipoSubtitulo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_situacion_reto));
                break;
        }

    }
}
