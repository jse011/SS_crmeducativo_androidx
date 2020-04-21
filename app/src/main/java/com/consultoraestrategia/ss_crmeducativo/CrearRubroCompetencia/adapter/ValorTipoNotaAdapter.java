package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.SelectorIconosHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.SelectorIconosSimpleHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.SelectorValoresHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.SelectorValoresSimpleHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class ValorTipoNotaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private final static int SELECTOR_ICONOS = 1, SELECTOR_VALORES = 2, SELECTOR_VALORES_SIMPLE = 3, SELECTOR_ICONOS_SIMPLE = 4;
    public final static int TIPO_SIMPLE = 0, TIPO_COMPLEJO = 1;
    private int tipo;

    public ValorTipoNotaAdapter(List<ValorTipoNotaUi> valorTipoNotaUiList, int tipo ) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;
        this.tipo = tipo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        switch (viewType){
            case SELECTOR_ICONOS:
                view = layoutInflater.inflate(R.layout.item_crear_rubrica__selector_iconos, viewGroup, false);
                viewHolder = new SelectorIconosHolder(view);
                break;
            case SELECTOR_VALORES:
                view = layoutInflater.inflate(R.layout.item_crear_rubrica_selector_valores, viewGroup, false);
                viewHolder = new SelectorValoresHolder(view);
                break;
            case SELECTOR_ICONOS_SIMPLE:
                view = layoutInflater.inflate(R.layout.item_crear_rubrica__selector_iconos_simple, viewGroup, false);
                viewHolder = new SelectorIconosSimpleHolder(view);
                break;
           default:
                view = layoutInflater.inflate(R.layout.item_crear_rubrica_selector_valores_simple, viewGroup, false);
                viewHolder = new SelectorValoresSimpleHolder(view);
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
                selectorIconosAdapter.bind(valorTipoNotaUi);
                break;
            case SELECTOR_VALORES:
                SelectorValoresHolder selectorValoresHolder = (SelectorValoresHolder)holder;
                selectorValoresHolder.bind(valorTipoNotaUi);
                break;
            case SELECTOR_ICONOS_SIMPLE:
                SelectorIconosSimpleHolder selectorIconosSimpleHolder = (SelectorIconosSimpleHolder)holder;
                selectorIconosSimpleHolder.bind(valorTipoNotaUi);
                break;
            case SELECTOR_VALORES_SIMPLE:
                SelectorValoresSimpleHolder selectorValoresSimpleHolder = (SelectorValoresSimpleHolder)holder;
                selectorValoresSimpleHolder.bind(valorTipoNotaUi);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        ValorTipoNotaUi valorTipoNotaUi = valorTipoNotaUiList.get(position);
        TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();

        if(tipoNotaUi!=null && tipoNotaUi.getTipoNota()== TipoNotaUi.TipoNota.IMAGEN){
            if(tipo==TIPO_COMPLEJO){
                return  SELECTOR_ICONOS;
            }else {
                return  SELECTOR_ICONOS_SIMPLE;
            }

        }else {
            if(tipo==TIPO_COMPLEJO){
                return  SELECTOR_VALORES;
            }else {
                return  SELECTOR_VALORES_SIMPLE;
            }
        }
    }

    @Override
    public int getItemCount() {
        return valorTipoNotaUiList.size();
    }

    public void setList(List<ValorTipoNotaUi> valorTipoNotaList) {
        valorTipoNotaUiList.clear();
        valorTipoNotaUiList.addAll(valorTipoNotaList);
        notifyDataSetChanged();
    }
}
