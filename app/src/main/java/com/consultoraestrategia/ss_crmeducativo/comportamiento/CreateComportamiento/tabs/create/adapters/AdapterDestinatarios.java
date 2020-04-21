package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.ListenerDestinosComport;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;

import java.util.List;

public class AdapterDestinatarios extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UsuarioUi> usuarioUiList;
    ListenerDestinosComport listener;

    public AdapterDestinatarios(List<UsuarioUi> usuarioUiList, ListenerDestinosComport listener) {
        this.usuarioUiList = usuarioUiList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destinos_comportamiento, parent, false);
        return new DestinosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UsuarioUi usuarioUi = usuarioUiList.get(position);
        DestinosHolder destinosHolder = (DestinosHolder) holder;
        destinosHolder.bind(usuarioUi, listener);
    }

    @Override
    public int getItemCount() {
        return usuarioUiList.size();
    }

    public void setUsuarioUiList(List<UsuarioUi> usuarioUis) {
        usuarioUiList.clear();
        usuarioUiList.addAll(usuarioUis);
        notifyDataSetChanged();
    }

    public void update(UsuarioUi usuarioUi){
        int position = this.usuarioUiList.indexOf(usuarioUi);
        if (position != -1) {
            this.usuarioUiList.set(position, usuarioUi);
            notifyItemChanged(position);
        }
    }
}
