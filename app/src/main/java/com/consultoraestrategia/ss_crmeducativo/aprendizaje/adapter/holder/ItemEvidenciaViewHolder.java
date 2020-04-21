package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemEvidenciaViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_contenido)
    TextView txtContenido;
    @BindView(R.id.txt_numeracion)
    TextView txtNumeracion;

    public ItemEvidenciaViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(EvidenciaUi evidenciaUi) {
        txtNumeracion.setText(String.valueOf(evidenciaUi.getNumeracion() + "."));
        txtContenido.setText(evidenciaUi.getTitulo());
    }
}
