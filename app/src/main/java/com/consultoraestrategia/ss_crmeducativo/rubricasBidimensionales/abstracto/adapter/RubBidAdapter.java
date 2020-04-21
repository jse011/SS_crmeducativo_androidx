package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;


import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.holder.RubBidHolder;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RubricaListener;


import java.util.Collections;
import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class RubBidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = RubBidAdapter.class.getSimpleName();
    private List<RubBidUi> items;
    private RubricaListener listener;

    public RubBidAdapter(List<RubBidUi> items, RubricaListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubbid, parent, false);
        return new RubBidHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RubBidUi item = items.get(position);
        int total = items.size();
        ((RubBidHolder) holder).bind(item, listener, total-position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<RubBidUi> items) {
        resverList(items);
        this.items = items;
        notifyDataSetChanged();
    }

    private void resverList(List<RubBidUi> items) {
        Collections.reverse(items);
    }

    public void agregarItem(RubBidUi rubBidUi) {
        this.items.add(0,rubBidUi);
        notifyItemInserted(getItemCount() - 1);
    }

    public void editarItem(RubBidUi rubBidUi) {
        int position = this.items.indexOf(rubBidUi);
        if (position != -1) {
            this.items.set(position, rubBidUi);
            notifyItemChanged(position);
           // notifyDataSetChanged();
        }
    }

    public void eliminarItem(RubBidUi rubBidUi) {
        Log.d(TAG, "elimandoRubro");
        int position = this.items.indexOf(rubBidUi);
        if (position != -1) {
            this.items.remove(rubBidUi);
            notifyItemRemoved(position);
            actualizarPorItem(items.size());
        }
    }

    public void actualizarPorItem(int tamanio){
        for (int i = 0; i < tamanio; i++) {
            notifyItemChanged(i);
        }
    }

    public void actualizarItem(RubBidUi rubBidUi) {
        int position = this.items.indexOf(rubBidUi);
        if (position != -1) {
            this.items.set(position, rubBidUi);
            notifyItemChanged(position);
            notifyDataSetChanged();
        }
    }

}
