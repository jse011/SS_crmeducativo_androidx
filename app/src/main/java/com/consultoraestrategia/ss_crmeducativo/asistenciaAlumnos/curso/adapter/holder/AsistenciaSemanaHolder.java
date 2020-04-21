package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaSemanaHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.txt_titulo_asistencia)
    TextView txtTitulo;


    public AsistenciaSemanaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String semana) {
        txtTitulo.setText(semana);
    }

}
