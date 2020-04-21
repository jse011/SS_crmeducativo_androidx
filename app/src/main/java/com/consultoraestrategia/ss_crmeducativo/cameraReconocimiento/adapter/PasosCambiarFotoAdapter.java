package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasosCambiarFotoAdapter extends RecyclerView.Adapter<PasosCambiarFotoAdapter.ViewHolder> {

    private List<SentimientoUi> lista = new ArrayList<>();

    public PasosCambiarFotoAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_camera_reconcimiento_paso, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(lista.get(i));
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_paso)
        ImageView imgPreview;
        @BindView(R.id.paso)
        TextView paso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(SentimientoUi sentimientoUi) {
            paso.setText(String.valueOf(sentimientoUi.getPosition()));
            Glide.with(imgPreview)
                    .load(sentimientoUi.getFoto())
                    .apply(new RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imgPreview);

        }
    }


}
