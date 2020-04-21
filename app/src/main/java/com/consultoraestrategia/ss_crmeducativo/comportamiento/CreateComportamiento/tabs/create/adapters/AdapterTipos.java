package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.ListenerTabCreateComport;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import java.util.List;

public class AdapterTipos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TIPO_HIJO = 1, TIPO_PADRE = 0;
    List<TipoUi> tipos;
    ListenerTabCreateComport listenerTabCreateComport;

    public AdapterTipos(List<TipoUi> tipos, ListenerTabCreateComport listenerTabCreateComport) {
        this.tipos = tipos;
        this.listenerTabCreateComport = listenerTabCreateComport;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TIPO_HIJO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_comport_tipo_item, parent, false);
                return new TiposHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipos_padres_comportamiento, parent, false);
                return new TipoPadresHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TipoUi tipoUi = (TipoUi) tipos.get(position);
        if (holder instanceof TiposHolder) {
            TiposHolder tiposHolder = (TiposHolder) holder;
            tiposHolder.bind(tipoUi, listenerTabCreateComport);
        } else {

            TipoPadresHolder tiposHolder = (TipoPadresHolder) holder;
            tiposHolder.bind(tipoUi, listenerTabCreateComport);
        }
    }

    @Override
    public int getItemCount() {
        return tipos.size();
    }

    public void setTiposList(List<TipoUi> tiposList) {
        tipos.clear();
        this.tipos.addAll(tiposList);
        notifyDataSetChanged();
        ;

    }

    @Override
    public int getItemViewType(int position) {
        TipoUi tipoUi = tipos.get(position);
        switch (tipoUi.getHerencia()) {
            case HIJO:
                return TIPO_HIJO;
            default:
                return TIPO_PADRE;
        }
    }
}
