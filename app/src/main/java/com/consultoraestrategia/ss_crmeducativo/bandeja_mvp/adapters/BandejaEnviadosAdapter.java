package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.holders.BandejaEnviadosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;

import java.util.List;

/**
 * Created by kelvi on 03/03/2017.
 */

public class BandejaEnviadosAdapter extends RecyclerView.Adapter<BandejaEnviadosViewHolder> {


    private final String TAG = this.getClass().getSimpleName();
    private List<MensajeC> mensajeList;

    public BandejaEnviadosAdapter(List<MensajeC> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @Override
    public BandejaEnviadosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_mensaje, parent, false);
        return new BandejaEnviadosViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final BandejaEnviadosViewHolder holder, final int position) {
        holder.bind(mensajeList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mensajeList.size();
    }

}
