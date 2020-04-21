package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.holder.SelectorIconosHolder;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.holder.SelectorValoresHolder;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.ValorTipoNotaUi;

import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class ValorTipoNotaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private TipoUi.Tipo tipo;

    public ValorTipoNotaAdapter(List<ValorTipoNotaUi> valorTipoNotaUiList, TipoUi.Tipo tipo) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
        this.tipo = tipo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        switch (tipo){
            case SELECTOR_ICONOS:
                view = layoutInflater.inflate(R.layout.item_tipo_nota_selector_iconos_2, viewGroup, false);
                viewHolder = new SelectorIconosHolder(view);
                break;
            default:
                view = layoutInflater.inflate(R.layout.item_tipo_nota_selector_valores_2, viewGroup, false);
                viewHolder = new SelectorValoresHolder(view);
                break;
        }
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUiList.get(position);
        switch (tipo){
            case SELECTOR_ICONOS:
                SelectorIconosHolder selectorIconosAdapter = (SelectorIconosHolder)holder;
                selectorIconosAdapter.bind(valorTipoNotaUi);
                break;
            case SELECTOR_VALORES:
                SelectorValoresHolder selectorValoresHolder = (SelectorValoresHolder)holder;
                selectorValoresHolder.bind(valorTipoNotaUi);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return valorTipoNotaUiList.size();
    }

}
