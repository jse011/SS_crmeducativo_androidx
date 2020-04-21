package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.holders.BandejaRecibidosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;

import java.util.List;

/**
 * Created by kelvi on 03/03/2017.
 */

public class BandejaRecibidoAdapter extends RecyclerView.Adapter<BandejaRecibidosViewHolder> {


    private List<MensajeUsuarioC> mensajeUsuarioList;


    public BandejaRecibidoAdapter(List<MensajeUsuarioC> mensajeList) {
        this.mensajeUsuarioList = mensajeList;
    }

    @Override
    public BandejaRecibidosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_mensaje, parent, false);
        return new BandejaRecibidosViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final BandejaRecibidosViewHolder holder, int position) {
        holder.bind(mensajeUsuarioList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mensajeUsuarioList.size();
    }


}
