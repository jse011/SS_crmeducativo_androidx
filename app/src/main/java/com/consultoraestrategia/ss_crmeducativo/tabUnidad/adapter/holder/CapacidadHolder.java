package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CapacidadUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CapacidadHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.capacidadTitulo)
    TextView capacidadTitulo;


    public CapacidadHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CapacidadUi capacidadUi, int i, int competencia){
        capacidadTitulo.setText(competencia+"."+i+". "+capacidadUi.getNombre());
    }
}
