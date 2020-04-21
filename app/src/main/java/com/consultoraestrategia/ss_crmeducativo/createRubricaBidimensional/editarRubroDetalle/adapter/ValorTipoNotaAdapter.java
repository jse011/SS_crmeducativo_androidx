package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.holder.SelectorIconosHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.holder.SelectorValoresHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import java.util.List;


public class ValorTipoNotaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private List<Cell> cellList;
    private final static int SELECTOR_ICONOS = 1, SELECTOR_VALORES = 2;

    public ValorTipoNotaAdapter(List<ValorTipoNotaUi> valorTipoNotaUiList, List<Cell> cellList) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
        this.cellList = cellList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        switch (viewType){
            case SELECTOR_ICONOS:
                view = layoutInflater.inflate(R.layout.item_tipo_nota_selector_iconos, viewGroup, false);
                viewHolder = new SelectorIconosHolder(view);
                break;
            default:
                view = layoutInflater.inflate(R.layout.item_tipo_nota_selector_valores, viewGroup, false);
                viewHolder = new SelectorValoresHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUiList.get(position);
        switch (holder.getItemViewType()){
            case SELECTOR_ICONOS:
                SelectorIconosHolder selectorIconosAdapter = (SelectorIconosHolder)holder;
                selectorIconosAdapter.bind(valorTipoNotaUi, cellList, position+1);
                break;
            case SELECTOR_VALORES:
                SelectorValoresHolder selectorValoresHolder = (SelectorValoresHolder)holder;
                selectorValoresHolder.bind(valorTipoNotaUi, cellList, position+1);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUiList.get(position);
        TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();

        if(tipoNotaUi!=null && tipoNotaUi.getTipo()== TipoNotaUi.Tipo.SELECTOR_ICONOS){
            return SELECTOR_ICONOS;
        }else {
            return SELECTOR_VALORES;
        }
    }

    @Override
    public int getItemCount() {
        return valorTipoNotaUiList.size();
    }

}
