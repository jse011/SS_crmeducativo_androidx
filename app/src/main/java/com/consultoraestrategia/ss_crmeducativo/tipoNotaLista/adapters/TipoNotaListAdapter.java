package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.holder.TipoNotaHolder;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class TipoNotaListAdapter extends RecyclerView.Adapter<TipoNotaHolder> {

    private List<TipoNotaUi> tipoNotaUiList;
    private TipoNotaListener listener;

    public TipoNotaListAdapter(List<TipoNotaUi> tipoNotaUiList, TipoNotaListener listener) {
        this.tipoNotaUiList = tipoNotaUiList;
        this.listener = listener;
    }

    @Override
    public TipoNotaHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.layout_item_tipo_nota_list, viewGroup, false);
        return new TipoNotaHolder(view);
    }

    @Override
    public void onBindViewHolder(TipoNotaHolder holder, int position) {
        TipoNotaUi tipoNotaUi = tipoNotaUiList.get(position);
        holder.bind(tipoNotaUi, listener);
    }

    @Override
    public int getItemCount() {
        return tipoNotaUiList.size();
    }

    public void setTipoNotaUiList(List<TipoNotaUi> tipoNotaUiList) {
        this.tipoNotaUiList = tipoNotaUiList;
        notifyDataSetChanged();
    }

    public void updateTipoNotaUi(TipoNotaUi tipoNotaUi) {
        int position = tipoNotaUiList.indexOf(tipoNotaUi);
        if (position != -1){
            this.tipoNotaUiList.set(position, tipoNotaUi);
            notifyItemChanged(position);
        }
    }

    public void selectTipoNotaUi(String tipoNotaId){
        TipoNotaUi tipoNotaUi = new TipoNotaUi();
        tipoNotaUi.setId(tipoNotaId);
        int position = tipoNotaUiList.indexOf(tipoNotaUi);
        if (position != -1){
           tipoNotaUi = tipoNotaUiList.get(position);
            tipoNotaUi.setChecket(true);
            this.tipoNotaUiList.set(position, tipoNotaUi);
            notifyItemChanged(position);
        }

    }

    public void tipoNotaSelected(TipoNotaUi tipoNotaUi) {
        List<TipoNotaUi> tipoNotaUis = new ArrayList<>();
        tipoNotaUis.add(tipoNotaUi);
        setTipoNotaUiList(tipoNotaUis);
    }
}
