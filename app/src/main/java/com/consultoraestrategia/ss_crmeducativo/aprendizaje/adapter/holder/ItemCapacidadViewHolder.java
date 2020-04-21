package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 13/02/2018.
 */

public class ItemCapacidadViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtCapacidad)
    TextView txtCapacidad;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    public ItemCapacidadViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

    public void bind(CompetenciaUi capacidadUi, boolean views) {

        if (views){
            txtCapacidad.setVisibility(View.VISIBLE);
            String capasidad = "<b>Capacidad:</b> " + capacidadUi.getTitulo();
            txtCapacidad.setText(Html.fromHtml(capasidad));
        }else txtCapacidad.setVisibility(View.GONE);

    }
}
