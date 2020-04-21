package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.AprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CarEvidenciaUi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvidenciaViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.rec_evidencia)
    RecyclerView recEvidencia;
    @BindView(R.id.txtvacio)
    TextView txtvacio;

    public EvidenciaViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


    public void bind(CarEvidenciaUi carEvidenciaUi) {
        recEvidencia.setAdapter(new AprendizajeAdapter(new ArrayList<Object>(carEvidenciaUi.getEvidenciaUiList()), null, recEvidencia));
        if(carEvidenciaUi.getEvidenciaUiList().isEmpty()){
            txtvacio.setVisibility(View.VISIBLE);
        }else {
            txtvacio.setVisibility(View.GONE);
        }
        recEvidencia.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
    }
}
