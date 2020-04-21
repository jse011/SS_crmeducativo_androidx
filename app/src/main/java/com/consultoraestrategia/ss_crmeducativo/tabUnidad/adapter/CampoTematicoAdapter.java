package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder.CampoTematicoHolder;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;

import java.util.List;

public class CampoTematicoAdapter extends RecyclerView.Adapter {

    private List<CampoAccionUi> campoAccionUis;
    private int positionPadre;

    public CampoTematicoAdapter(List<CampoAccionUi> campoAccionUis, int positionPadre) {
        this.campoAccionUis = campoAccionUis;
        this.positionPadre = positionPadre;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_campos_accion_unidad, viewGroup, false);
        return new CampoTematicoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CampoAccionUi campoAccionUi = campoAccionUis.get(i);
        CampoTematicoHolder holder = (CampoTematicoHolder) viewHolder;
        holder.bind(campoAccionUi, i+1, positionPadre);
    }

    @Override
    public int getItemCount() {
        return campoAccionUis.size();
    }

    public void setCampoAccionUis(List<CampoAccionUi> campoAccionUis) {
        this.campoAccionUis.clear();
        this.campoAccionUis.addAll(campoAccionUis);
        notifyDataSetChanged();
    }
}
