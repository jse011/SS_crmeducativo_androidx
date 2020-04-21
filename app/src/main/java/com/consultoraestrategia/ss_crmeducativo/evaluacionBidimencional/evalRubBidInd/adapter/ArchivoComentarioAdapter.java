package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchivoComentarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BTN = 1, DONWLOADER = 2, UPDATE = 3;
    private List<Object> archivoComentarioUiList = new ArrayList<>();
    private ArchivoComentarioListener listener;

    public ArchivoComentarioAdapter(ArchivoComentarioListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case DONWLOADER:
                return new HolderButton(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coment_download_repositorio, parent, false));
            default:
                return new HolderButton(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coment_btn_archivo, parent, false));
        }

    }

    @Override
    public int getItemCount() {
        return archivoComentarioUiList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case DONWLOADER:
                //((RepositorioDownloadHolder) holder).bind();
                break;
            default:
                ((HolderButton) holder).bind(listener);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object o = archivoComentarioUiList.get(0);
        if (o instanceof RepositorioFileUi) {
            return DONWLOADER;
        } else {
            return BTN;
        }
    }

    public void setArchivoComentarioUiList(List<Object> archivoComentarioUiList) {
        this.archivoComentarioUiList.clear();
        this.archivoComentarioUiList.add(0);
        this.archivoComentarioUiList.addAll(archivoComentarioUiList);
        notifyDataSetChanged();
    }

    public static class HolderButton extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btn_add_archivo)
        ConstraintLayout btnAddArchivo;
        private ArchivoComentarioListener listener;

        public HolderButton(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnAddArchivo.setOnClickListener(this);
        }

        public void bind(ArchivoComentarioListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            //listener.onClickAddArchivoComentario();
        }
    }

    public interface ArchivoComentarioListener {


    }

}
