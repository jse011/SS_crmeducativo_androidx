package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemCamtoTematicoHijoHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;

import java.util.List;

public class CampoTematicoAdapter extends RecyclerView.Adapter {

    private List<CampotematicoUi> campotematicoUiListHijo;

    public CampoTematicoAdapter(List<CampotematicoUi> campotematico) {
        this.campotematicoUiListHijo = campotematico;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        view = layoutInflater.inflate(R.layout.item_campotematico_hijo, parent, false);
        viewHolder = new ItemCamtoTematicoHijoHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CampotematicoUi campotematicoUi = campotematicoUiListHijo.get(position);
        ItemCamtoTematicoHijoHolder itemCamtoTematicoHijoHolder = (ItemCamtoTematicoHijoHolder) holder;
        itemCamtoTematicoHijoHolder.bind(campotematicoUi);
    }

    @Override
    public int getItemCount() {
        return campotematicoUiListHijo.size();
    }
}
