package com.consultoraestrategia.ss_crmeducativo.grouplist.filterChooser;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.TipoGrupoUi;

import butterknife.ButterKnife;

public class FilterChoserAdapter extends RecyclerView.Adapter<FilterChoserAdapter.FilterChoserHolder> {

    private TipoGrupoUi[] tipoGrupoUis;
    private OnItemClickListener listener;

    public FilterChoserAdapter(TipoGrupoUi[] tipoGrupoUis, OnItemClickListener listener) {
        this.tipoGrupoUis = tipoGrupoUis;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilterChoserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_seleccionar_grupo_item, parent, false);
        return new FilterChoserHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FilterChoserHolder holder, int position) {
        TipoGrupoUi tipoGrupoUi = tipoGrupoUis[position];
        holder.bind(tipoGrupoUi);
    }

    @Override
    public int getItemCount() {
        return tipoGrupoUis!=null?tipoGrupoUis.length:0;
    }

    public class FilterChoserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgTipo;
        private TextView txtNombre;
        private TipoGrupoUi tipoGrupoUi;

        public FilterChoserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre);
            imgTipo = (ImageView) itemView.findViewById(R.id.img_tipo);
            itemView.setOnClickListener(this);

        }

        public void bind(TipoGrupoUi tipoGrupoUi) {
            this.tipoGrupoUi = tipoGrupoUi;
            txtNombre.setText(tipoGrupoUi.getNombre());

            switch (tipoGrupoUi){
                case DINAMICO:
                    Glide.with(imgTipo)
                            .load(R.drawable.ic_datos_grupo)
                            .into(imgTipo);
                    break;
                case ESTATICO:
                    Glide.with(imgTipo)
                            .load(R.drawable.ic_datos_grupo)
                            .into(imgTipo);
                    break;
                case APRENDIZAJE_UNICO:
                    Glide.with(imgTipo)
                            .load(R.drawable.ic_datos_grupo)
                            .into(imgTipo);
                    break;
                case APRENDIZAJE_DIFERENTE:
                    Glide.with(imgTipo)
                            .load(R.drawable.ic_datos_grupo)
                            .into(imgTipo);
                    break;
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                default:
                    listener.onItemClick(tipoGrupoUi);
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TipoGrupoUi tipoGrupoUi);
    }


}
