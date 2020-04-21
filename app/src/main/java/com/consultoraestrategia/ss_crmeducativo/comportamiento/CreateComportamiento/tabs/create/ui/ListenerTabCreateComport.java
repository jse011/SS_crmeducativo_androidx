package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

public interface ListenerTabCreateComport {
    void onClickTipoMerito(TipoUi tipoUi);
    void seletedTipo(TipoUi tipoUi, RecyclerView.ViewHolder viewHolder);
    void setViewHolder(RecyclerView.ViewHolder viewHolder);
}
