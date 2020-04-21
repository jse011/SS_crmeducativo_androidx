package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

import java.util.List;

public class ComportDestinosAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ComportamientoUi> comportamientoUiList;



    public ComportDestinosAdpter(List<ComportamientoUi> comportamientoUiList) {
        this.comportamientoUiList = comportamientoUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comport_destinos, parent, false);
        return new ComportamientoDestinoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ComportamientoUi comportamientoUi = comportamientoUiList.get(position);
        ComportamientoDestinoHolder comportamientoDestinoHolder = (ComportamientoDestinoHolder) holder;
        comportamientoDestinoHolder.bind(comportamientoUi);
    }

    @Override
    public int getItemCount() {
        return comportamientoUiList.size();
    }

    public void setComportamientoUiList(List<ComportamientoUi> comportamientoUis) {
        comportamientoUiList.clear();
        comportamientoUiList.addAll(comportamientoUis);
        notifyDataSetChanged();
    }
}
