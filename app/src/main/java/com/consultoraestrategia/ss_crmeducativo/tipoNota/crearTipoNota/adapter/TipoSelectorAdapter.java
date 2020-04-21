package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.adapter.holder.TipoSelectorHolder;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.listener.CheckIntervaloListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.listener.ValoresListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public class TipoSelectorAdapter extends RecyclerView.Adapter<TipoSelectorHolder> {
    private static String SELECTOR_ADAPTER_TAG = TipoSelectorAdapter.class.getSimpleName();

    private final int TIPO_NOTA_SELECTOR_VALORES = 0, TIPO_NOTA_SELECTOR_ICONOS = 1;
    //   List<Object> objectList;

    private List<ValoresUi> valoresUiList;
    private ValoresListener listener;
    // private CheckIntervaloListener checkIntervaloListener;

    public TipoSelectorAdapter(List<ValoresUi> valoresUiList, ValoresListener listener) {
        this.valoresUiList = valoresUiList;
        this.listener = listener;

    }

    public void setCheckBox(CheckIntervaloListener checkIntervaloListener) {
        //    this.checkIntervaloListener = checkIntervaloListener;
    }

    @Override
    public TipoSelectorHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
       /* RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case TIPO_NOTA_SELECTOR_VALORES:
                View view1 = layoutInflater.inflate(R.layout.item_add_tipo_valores, viewGroup, false);
                viewHolder = new TipoSelectorHolder(view1);
                break;
            case TIPO_NOTA_SELECTOR_ICONOS:
                View view2 = layoutInflater.inflate(R.layout.layout_item_sub_recursos, viewGroup, false);
                viewHolder = new TipoSelectorIconoHolder(view2);
                break;
        }*/

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_tipo_valores, viewGroup, false);

        return new TipoSelectorHolder(view);
    }

    @Override
    public void onBindViewHolder(TipoSelectorHolder holder, int position) {
       /* switch (holder.getItemViewType()) {
            case TIPO_NOTA_SELECTOR_VALORES:
                ValoresUi valoresUi = (ValoresUi) objectList.get(position);
                TipoSelectorHolder vh1 = (TipoSelectorHolder) holder;
                vh1.bind(valoresUi);
                break;
            case TIPO_NOTA_SELECTOR_ICONOS:
                IconosUi iconosUi = (IconosUi) objectList.get(position);
                TipoSelectorIconoHolder vh2 = (TipoSelectorIconoHolder) holder;
                vh2.bind(iconosUi);
                break;
            default:
                break;
        }*/

        ValoresUi valoresUi = valoresUiList.get(position);
        Log.d(SELECTOR_ADAPTER_TAG, "onBindViewHolder" + listener.getClass().toString());
        holder.bind(valoresUi, listener);
    }


    @Override
    public int getItemCount() {
      /*  if (objectList == null) return 0;
        return objectList.size();*/
        if (valoresUiList == null) return 0;
        return valoresUiList.size();
    }

    public void agregarItem(ValoresUi valoresUi) {
        this.valoresUiList.add(valoresUi);
        notifyItemInserted(getItemCount() - 1);
    }

    public List<ValoresUi> getValoresUiList() {
        return valoresUiList;
    }



 /*   @Override
    public int getItemViewType(int position) {
        Object items = objectList.get(position);
        if (items instanceof ValoresUi) {
            return TIPO_NOTA_SELECTOR_VALORES;
        } else if (items instanceof IconosUi) {
            return TIPO_NOTA_SELECTOR_ICONOS;
        }
        return -1;
    }*/
}
