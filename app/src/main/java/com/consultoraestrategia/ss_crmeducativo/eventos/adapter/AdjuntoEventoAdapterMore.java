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
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjuntoEventoAdapterMore extends RecyclerView.Adapter<AdjuntoEventoAdapterMore.ViewHolder>{
    private List<EventoAdjuntoUi> adjuntoUiList;
    private Listener listener;
    private EventosUi eventoUi;
    public AdjuntoEventoAdapterMore(List<EventoAdjuntoUi> adjuntoUiList, EventosUi eventoUi, Listener listener) {
        this.adjuntoUiList = adjuntoUiList;
        this.eventoUi = eventoUi;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_colegio_adjunto_more_clean, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindMore(position==3&&adjuntoUiList.size()>4,adjuntoUiList.size()-4, position==3||adjuntoUiList.size()-1==position,adjuntoUiList.get(position),eventoUi, listener);
    }

    @Override
    public int getItemCount() {
        return Math.min(adjuntoUiList.size(), 4);
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


        Listener listener;
        EventoAdjuntoUi adjuntoUi;
        boolean more;
        private EventosUi eventoUi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindMore(boolean more, int countMore, boolean ultimoItem, EventoAdjuntoUi adjuntoUi, EventosUi eventoUi, Listener listener) {
            this.listener = listener;
            this.adjuntoUi = adjuntoUi;
            this.eventoUi = eventoUi;
            this.more = more;
            init();
            linea1.setVisibility(View.GONE);
            if(more){
                morePreview.setVisibility(View.VISIBLE);
                String textCount = "+"+countMore;
                morePreviewCount.setText(textCount);
            }else {
                morePreview.setVisibility(View.GONE);
            }

            linea.setVisibility(ultimoItem?View.GONE:View.VISIBLE);

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
            ViewHolder.this.listener.onClickAdjunto(eventoUi,adjuntoUi, more);
        }
    }

    public interface Listener {
        void onClickAdjunto(EventosUi eventoUi, EventoAdjuntoUi adjuntoUi, boolean more);
    }

}
