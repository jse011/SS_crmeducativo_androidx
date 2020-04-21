package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemCamtoTematicoHijoHolder extends RecyclerView.ViewHolder {



    @BindView(R.id.texto)
    TextView textTitulo;


    public ItemCamtoTematicoHijoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(CampotematicoUi campotematicoUi) {
        textTitulo.setText(campotematicoUi.getDescripcion());
    }

}