package com.consultoraestrategia.ss_crmeducativo.previewCamera.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.ViewHolder> {

    private List<String> lista = new ArrayList<>();
    private Listener listener;

    public PreviewAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_preview_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(lista.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setLista(List<String> lista) {
        this.lista.clear();
        this.lista.addAll(lista);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.img_preview)
        ImageView imgPreview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final String url, final Listener listener) {
            Glide.with(imgPreview)
                    .load(new File(url))
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgPreview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(url);
                }
            });
        }
    }

    public interface Listener {
        void onClick(String url);
    }

    public List<String> getLista() {
        return lista;
    }
}
