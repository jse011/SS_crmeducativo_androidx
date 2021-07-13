package com.consultoraestrategia.ss_crmeducativo.eventos.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjuntoEventoAdapter extends RecyclerView.Adapter<AdjuntoEventoAdapter.ViewHolder> {

    private List<EventoAdjuntoUi> adjuntoUiList;
    private Listener listener;

    public AdjuntoEventoAdapter(List<EventoAdjuntoUi> adjuntoUiList, Listener listener) {
        this.adjuntoUiList = adjuntoUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_colegio_adjunto_clean, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(adjuntoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return adjuntoUiList.size();
    }

    public void setList(List<EventoAdjuntoUi> adjuntoUiList) {
        if(adjuntoUiList==null)adjuntoUiList=new ArrayList<>();
        this.adjuntoUiList.clear();
        this.adjuntoUiList.addAll(adjuntoUiList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.ic_icono)
        ImageView icIcono;
        @BindView(R.id.txt_documento)
        TextView txtDocumento;
        @BindView(R.id.btn_download)
        View btnDownload;
        @BindView(R.id.more_preview)
        View morePreview;
        @BindView(R.id.more_preview_count)
        TextView morePreviewCount;
        @BindView(R.id.linea)
        View linea;
        @BindView(R.id.linea1)
        View linea1;

        EventoAdjuntoUi adjuntoUi;
        private Listener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void init(){
            Drawable drawable;
            switch (adjuntoUi.getTipoArchivo()){
                case PDF:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_pdf_ico);
                    break;
                case PRESENTACION:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_ppt_ico);
                    break;
                case HOJACALCULO:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_xls_ico);
                    break;
                case DOCUMENTO:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_doc_ico);
                    break;
                case AUDIO:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_aud_ico);
                    break;
                case OTROS:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_not_av_ico);
                    break;
                default:
                    drawable = ContextCompat.getDrawable(icIcono.getContext(), R.drawable.ext_not_av_ico);
                    break;
            }

            icIcono.setImageDrawable(drawable);
            txtDocumento.setText(adjuntoUi.getTitulo());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClickAdjunto(adjuntoUi);
        }

        public void bind(EventoAdjuntoUi adjuntoUi, Listener listener) {
            this.listener = listener;
            this.adjuntoUi = adjuntoUi;
            init();
            linea1.setVisibility(View.VISIBLE);
            linea.setVisibility(View.GONE);
        }
    }

    public interface Listener {
        void onClickAdjunto(EventoAdjuntoUi adjuntoUi);
    }
}