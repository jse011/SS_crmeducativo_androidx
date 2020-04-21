package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendAdapter extends RecyclerView.Adapter<SendAdapter.ViewHolder> {

    private List<SentimientoUi> lista = new ArrayList<>();
    private Listener listener;

    public SendAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_camera_reconcimient_send_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(lista.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<SentimientoUi> lista) {
        this.lista.clear();
        this.lista.addAll(lista);
        notifyDataSetChanged();
    }

    public void addList(SentimientoUi sentimientoUi) {
        int postion = lista.indexOf(sentimientoUi);
        if(postion!=-1)lista.set(postion, sentimientoUi);
        else lista.add(sentimientoUi);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.img_preview)
        ImageView imgPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final SentimientoUi sentimientoUi, final Listener listener) {
            Glide.with(imgPreview)
                    .load(sentimientoUi.getFoto())
                    .apply(new RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(imgPreview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(sentimientoUi);
                }
            });
        }
    }

    public interface Listener {
        void onClick(SentimientoUi sentimientoUi);
    }

}
