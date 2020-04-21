package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;

import java.util.ArrayList;
import java.util.List;

public class ComentarioPredeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MensajeUi> mensajeUiList = new ArrayList<>();
    ArchivoComentarioListener listener;

    public ComentarioPredeAdapter(ArchivoComentarioListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coment_pred, parent, false);
        return new ComentarioPredHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MensajeUi mensajeUi = mensajeUiList.get(position);
        ((ComentarioPredHolder) holder).bind(mensajeUi, listener);
    }

    @Override
    public int getItemCount() {
        return mensajeUiList.size();
    }

    public void setMensajesList(List<MensajeUi> mensajeUis) {
        this.mensajeUiList.clear();
        this.mensajeUiList.addAll(mensajeUis);
        notifyDataSetChanged();

    }

    public interface ArchivoComentarioListener{

        void onClickComentarioNormal(MensajeUi mensajeUi);
    }
}
