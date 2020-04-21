package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.adapter.holder.FiltradoAdapterHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.listener.FiltradoCheckItemListener;

import java.util.List;


/**
 * Created by kike on 11/04/2018.
 */

public class FiltradoAdapter extends RecyclerView.Adapter<FiltradoAdapterHolder> {

    private List<FiltradoUi> filtradoUiList;
    private FiltradoCheckItemListener listener;

    public FiltradoAdapter(List<FiltradoUi> filtradoUiList, FiltradoCheckItemListener listener) {
        this.filtradoUiList = filtradoUiList;
        this.listener = listener;
    }

    @Override
    public FiltradoAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filtrado_dialog, parent, false);
        return new FiltradoAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(FiltradoAdapterHolder holder, int position) {
        FiltradoUi filtradoUi = filtradoUiList.get(position);
        holder.bind(filtradoUi,listener);
    }

    @Override
    public int getItemCount() {
        if (filtradoUiList == null) return 0;
        return filtradoUiList.size();
    }

    public void setFiltradoList(List<FiltradoUi> filtradoUiList) {
        this.filtradoUiList.clear();
        this.filtradoUiList.addAll(filtradoUiList);
        notifyDataSetChanged();
    }

}
