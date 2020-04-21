package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter;


import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder.RubroProcesoRubicaHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;

import java.util.List;


/**
 * Created by kike on 22/10/2017.
 */

public class RubroProcesoAdapter extends RecyclerView.Adapter<RubroProcesoRubicaHolder> {

    protected static String TAG = RubroProcesoAdapter.class.getSimpleName();
    protected List<IndicadorUi> indicadorUiList;

    public RubroProcesoAdapter(List<IndicadorUi> indicadorUiList) {
        this.indicadorUiList = indicadorUiList;
    }

    @Override
    public RubroProcesoRubicaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View viewNormal = layoutInflater.inflate(R.layout.layout_item_crear_rubrica_rubro_bidimencional, parent, false);
        return new RubroProcesoRubicaHolder(viewNormal);
    }

    @Override
    public void onBindViewHolder(RubroProcesoRubicaHolder holder, final int position) {
        IndicadorUi indicadorUi = indicadorUiList.get(position);
        holder.bind(indicadorUi);
    }

    @Override
    public int getItemCount() {
        if (indicadorUiList == null) return 0;
        return indicadorUiList.size();
    }

    public void setItems(List<IndicadorUi> items) {
        this.indicadorUiList.clear();
        this.indicadorUiList.addAll(items);
        notifyDataSetChanged();
    }


}
