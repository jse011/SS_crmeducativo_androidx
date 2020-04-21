package com.consultoraestrategia.ss_crmeducativo.tipoNota.adapters.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 15/02/2018.
 */

public class TipoNotaHolder extends RecyclerView.ViewHolder {
    private String TAG = TipoNotaHolder.class.getSimpleName();
    @BindView(R.id.textnombre)
    TextView textNombre;
    @BindView(R.id.textDescripcion)
    TextView textDescripcion;
    @BindView(R.id.imageView)
    ImageView imageViewTipoNota;
    @BindView(R.id.coonstrainLayout)
    ConstraintLayout constraintLayoutColor;


    public TipoNotaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TipoNotaUi tipoNotaUi) {
        // imageViewTipoNota.setImageDrawable(itemView.getResources().getDrawable(getTipoValor(tipoNotaUi.getTipoNotaId())));
        imageViewTipoNota.setBackground(itemView.getResources().getDrawable(getTipoValor(tipoNotaUi.getTipoSelectorId())));
        Log.d(TAG, "bind : " + tipoNotaUi.getNombre());
        textNombre.setText(tipoNotaUi.getNombre() + "");
        textDescripcion.setText("Selector Valores");
    }

    private int SELECTOR_ICONO = 24;
    private int SELECTOR_VALORES = 25;

    private int getTipoValor(int idTipoNota) {
        if (SELECTOR_ICONO == idTipoNota) {
            constraintLayoutColor.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.color_yellow));
            return R.drawable.doodles;
        } else {
            constraintLayoutColor.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_orange_600));
            return R.drawable.back_ground_activity;
        }
    }
}
