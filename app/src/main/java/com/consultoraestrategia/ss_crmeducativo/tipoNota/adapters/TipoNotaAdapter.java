package com.consultoraestrategia.ss_crmeducativo.tipoNota.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.adapters.holder.TipoNotaHolder;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public class TipoNotaAdapter extends RecyclerView.Adapter<TipoNotaHolder> {
    private String TAG = TipoNotaAdapter.class.getSimpleName();
    List<TipoNotaUi> tipoNotaUiList;

    public TipoNotaAdapter(List<TipoNotaUi> tipoNotaUiList) {
        this.tipoNotaUiList = tipoNotaUiList;
    }

    @Override
    public TipoNotaHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tipo_nota_seleccion, viewGroup, false);
        return new TipoNotaHolder(view);
    }

    @Override
    public void onBindViewHolder(TipoNotaHolder tipoNotaHolder, int position) {
        TipoNotaUi tipoNotaUi = tipoNotaUiList.get(position);
        tipoNotaHolder.bind(tipoNotaUi);
    }

    @Override
    public int getItemCount() {
        if (tipoNotaUiList == null) {
            return 0;
        }
        return tipoNotaUiList.size();
    }

    public void showListTipoNota(List<TipoNotaUi> tipoNotaUiList) {
        Log.d(TAG, "tipoNotaUiList " + tipoNotaUiList.size());
        this.tipoNotaUiList.clear();
        this.tipoNotaUiList.addAll(tipoNotaUiList);
        notifyDataSetChanged();
    }


}
