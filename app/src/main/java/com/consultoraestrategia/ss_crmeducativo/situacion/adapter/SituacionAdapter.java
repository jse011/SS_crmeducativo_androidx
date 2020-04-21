package com.consultoraestrategia.ss_crmeducativo.situacion.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.situacion.adapter.holder.SituacionHolder;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class SituacionAdapter extends RecyclerView.Adapter<SituacionHolder> {

    private List<SituacionUI> situacionUIList;

    public SituacionAdapter(List<SituacionUI> situacionUIList) {
        this.situacionUIList = situacionUIList;
    }

    @Override
    public SituacionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_situacion, parent, false);
        return new SituacionHolder(view);
    }

    @Override
    public void onBindViewHolder(SituacionHolder holder, int position) {
        SituacionUI situacionUIItem = situacionUIList.get(position);
        holder.bind(situacionUIItem);
    }

    @Override
    public int getItemCount() {
        return situacionUIList.size();
    }

    public void setSituacionUIList(List<SituacionUI> situacionUIList) {
        this.situacionUIList.clear();
        this.situacionUIList.addAll(situacionUIList);
        notifyDataSetChanged();
    }
}
