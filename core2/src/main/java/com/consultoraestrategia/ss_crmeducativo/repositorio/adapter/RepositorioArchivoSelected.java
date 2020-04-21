package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;

import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivoSelected extends RecyclerView.Adapter<RepositorioArchivoSelected.ViewHolder> {

    protected List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();

    public RepositorioArchivoSelected() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repositorio_seleted, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(repositorioFileUiList.get(i));
    }

    @Override
    public int getItemCount() {
        return repositorioFileUiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tipoArchivo;
        TextView nombreArchivo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoArchivo = itemView.findViewById(R.id.tipoArchivo);
            nombreArchivo = itemView.findViewById(R.id.nombreArchivo);
        }

        public void bind(RepositorioFileUi repositorioFileUi) {
            nombreArchivo.setText(repositorioFileUi.getNombreArchivo());
            setupIcono(repositorioFileUi.getTipoFileU());
        }

        private void setupIcono(RepositorioTipoFileU tipoFileU) {
            switch (tipoFileU){
                case PDF:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_pdf));
                    break;
                case AUDIO:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_aud));
                    break;
                case VIDEO:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_vid));
                    break;
                case YOUTUBE:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.youtube));
                    break;
                case IMAGEN:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_img));
                    break;
                case VINCULO:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_link));
                    break;
                case DOCUMENTO:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_doc));
                    break;
                case DIAPOSITIVA:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_ppt));
                    break;
                case HOJA_CALCULO:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_xls));
                    break;
                case MATERIALES:
                    tipoArchivo.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ext_other));
                    break;
            }

        }
    }

    public void setList(List<RepositorioFileUi> repositorioFileUis){
        repositorioFileUiList.clear();
        repositorioFileUiList.addAll(repositorioFileUis);
        notifyDataSetChanged();
    }
}
