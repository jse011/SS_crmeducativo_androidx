package com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.holder.DownloadHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.ArrayList;
import java.util.List;


public class DownloadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final DownloadItemListener downloadItemListener;
    private final RecyclerView recyclerView;
    private List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();

    public DownloadAdapter(DownloadItemListener downloadItemListener, RecyclerView recyclerView) {
        this.downloadItemListener = downloadItemListener;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DownloadHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_download_repositorio, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DownloadHolder) holder).bind(repositorioFileUiList.get(position), downloadItemListener);
    }

    @Override
    public int getItemCount() {
        return repositorioFileUiList.size();
    }

    public void update(RepositorioFileUi repositorioFileUi) {
        int position = repositorioFileUiList.indexOf(repositorioFileUi);
        if (position != -1) {
            repositorioFileUiList.set(position, repositorioFileUi);
            notifyItemChanged(position);
        }
    }

    public synchronized void updateProgress(RepositorioFileUi repositorioFileUi, int count) {
        DownloadHolder repositorioHolder = getRepositorioHolder(repositorioFileUi);
        if (repositorioHolder != null) repositorioHolder.count(count);
    }

    private synchronized DownloadHolder getRepositorioHolder(RepositorioFileUi repositorioFileUi) {
        DownloadHolder repositorioDownloadHolder = null;
        int posicion = this.repositorioFileUiList.indexOf(repositorioFileUi);
        Log.d(getClass().getSimpleName(), "RepositorioHolder posicion: " + posicion + "repositorioFileUi: " + repositorioFileUi.getNombreArchivo());
        if (posicion != -1) {
            repositorioDownloadHolder = (DownloadHolder) recyclerView.findViewHolderForLayoutPosition(posicion);
        }
        return repositorioDownloadHolder;
    }


    public void setList(ArrayList<RepositorioFileUi> repositorioFileUis) {
        repositorioFileUiList.clear();
        repositorioFileUiList.addAll(repositorioFileUis);
        notifyDataSetChanged();
    }
}
