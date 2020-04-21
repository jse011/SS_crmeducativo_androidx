package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder.FormulaHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;

import java.util.List;

/**
 * Created by kike on 17/04/2018.
 */
public class FormulaAdapter extends RecyclerView.Adapter<FormulaHolder> {


    private List<CapacidadUi> capacidadUiList;
    private String parametroDisenio;
    private Context context;


    public FormulaAdapter(List<CapacidadUi> capacidadUiList, String parametroDisenio, Context context) {
        this.capacidadUiList = capacidadUiList;
        this.parametroDisenio = parametroDisenio;
        this.context = context;

    }


    @Override
    public FormulaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubros_rubricas, parent, false);
        return new FormulaHolder(view);
    }

    @Override
    public void onBindViewHolder(FormulaHolder holder, int position) {
        CapacidadUi formulaUi = capacidadUiList.get(position);
        holder.bind(formulaUi, parametroDisenio, context);
    }

    @Override
    public int getItemCount() {
        if (capacidadUiList == null) return 0;
        return capacidadUiList.size();
    }

}
