package com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.holder.Tipoholder;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import java.util.List;

public class TipoAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    List<TipoUi> tipoUiList;

    public TipoAdapter(List<TipoUi> tipoUiList) {
        this.tipoUiList = tipoUiList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comportamiento_tipo, parent, false);
        return new Tipoholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TipoUi tipoUi= (TipoUi)tipoUiList.get(position);
        Tipoholder tipoholder=(Tipoholder)holder;
        tipoholder.bind(tipoUi);
    }

    @Override
    public int getItemCount() {
        return tipoUiList.size();
    }

    public void setTipoUiList(List<TipoUi> tipoUiLista){
        tipoUiList.clear();
        this.tipoUiList.addAll(tipoUiLista);
        notifyDataSetChanged();
    }
}
