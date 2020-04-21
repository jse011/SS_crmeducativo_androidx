package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.holders.DestinatariosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioCanalComunicacion;

import java.util.List;

/**
 * Created by kelvi on 03/03/2017.
 */

public class DestinatariosAdapter extends RecyclerView.Adapter<DestinatariosViewHolder> {
    private static final String TAG = "TalentosAdapter";


    private List<Relaciones> relacionesList;
    private OnCickListener onCickListener;

    public DestinatariosAdapter(List<Relaciones> relacionesList, OnCickListener onCickListener) {
        this.relacionesList = relacionesList;
        this.onCickListener = onCickListener;
    }

    public interface OnCickListener {
        void onItemClickListener(List<UsuarioCanalComunicacion> usuarioCanalComunicacionList, int Persona);
    }

    @Override
    public DestinatariosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destinatarios, parent, false);
        return new DestinatariosViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final DestinatariosViewHolder holder, final int position) {
        holder.bind(relacionesList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return relacionesList.size();
    }


}
