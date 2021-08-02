package com.consultoraestrategia.ss_crmeducativo.eventos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjuntoEventoEncuesta extends RecyclerView.Adapter<AdjuntoEventoEncuesta.ViewHolder> {

    List<EventoAdjuntoUi> eventoAdjuntoUiList;
    Listener listener;

    public AdjuntoEventoEncuesta(List<EventoAdjuntoUi> eventoAdjuntoUiList, Listener listener) {
        this.eventoAdjuntoUiList = eventoAdjuntoUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento_encuesta_link, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(eventoAdjuntoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return eventoAdjuntoUiList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_link)
        TextView txtLink;
        private Listener listener;
        private EventoAdjuntoUi adjuntoUi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(EventoAdjuntoUi adjuntoUi, Listener listener) {
            this.adjuntoUi = adjuntoUi;
            this.listener = listener;
            txtLink.setText(adjuntoUi.getTitulo());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLinkEncuesta(adjuntoUi);
                }
            });
        }
    }

    public interface Listener{
        void onLinkEncuesta(EventoAdjuntoUi adjuntoUi);
    }
}
