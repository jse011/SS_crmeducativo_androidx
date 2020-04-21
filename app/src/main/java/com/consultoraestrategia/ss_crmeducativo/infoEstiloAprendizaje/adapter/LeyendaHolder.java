package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter;

import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.LeyendaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeyendaHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.colorEstilo)
    ImageView colorImagen;
    @BindView(R.id.estiloAprendizaje)
    TextView txtEstiloAprendizaje;


    public LeyendaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(LeyendaUi leyendaUi) {
        txtEstiloAprendizaje.setText(leyendaUi.getNombre());
        colorImagen.setBackgroundColor(Color.parseColor(leyendaUi.getColor()));
    }
}
