package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.holder;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.Estilo;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotaTextoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_intervalo)
    TextView txtIntervalo;
    @BindView(R.id.txt_alias)
    TextView txtAlias;
    @BindView(R.id.txt_nota)
    TextView txtNota;
    @BindView(R.id.root)
    ConstraintLayout constraintLayout;
    public NotaTextoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(NotaUi notaUi){
        txtIntervalo.setText(notaUi.getIntervalos());
        txtAlias.setText(notaUi.getContenido());
        txtNota.setText(String.format("%.1f", notaUi.getNotaEvaluacion()));
        colorPaintAlumnos(notaUi.getEstilo());
    }

    private void colorPaintAlumnos(Estilo estilo) {
        switch (estilo) {
            case AZUL:
                constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_blue_300));
                txtAlias.setTextColor(Color.WHITE);
                txtNota.setTextColor(Color.WHITE);
                txtIntervalo.setTextColor(Color.WHITE);
                break;
            case ROJO:
                constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_pink_50));
                txtAlias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_300));
                txtNota.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_300));
                txtIntervalo.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_300));
                break;
            case ANARANJADO:
                constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_orange_300));
                txtAlias.setTextColor(Color.WHITE);
                txtNota.setTextColor(Color.WHITE);
                txtIntervalo.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_300));
                break;
            case VERDE:
                constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_green_300));
                txtAlias.setTextColor(Color.WHITE);
                txtNota.setTextColor(Color.WHITE);
                txtIntervalo.setTextColor(Color.WHITE);
                break;
        }
    }
}
