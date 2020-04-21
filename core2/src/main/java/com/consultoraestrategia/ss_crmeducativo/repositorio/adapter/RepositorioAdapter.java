package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder.RepositorioBtnAddHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder.RepositorioDownloadHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder.RepositorioUpdateHolder;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;

import java.util.ArrayList;
import java.util.List;


public class RepositorioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected int UPDATE = 0, DOWNLOAD = 1,BTN_ADD = 2;
    private boolean version_two = false;
    protected List<Object> repositorioFileUiList = new ArrayList<>();
    protected RepositorioItemListener repositorioItemListener;
    protected RepositorioItemUpdateListener repositorioItemUpdateListener;
    protected RecyclerView recyclerView;

    public RepositorioAdapter(RepositorioItemListener repositorioItemListener, RepositorioItemUpdateListener repositorioItemUpdateListener, RecyclerView recyclerView,boolean version_two) {
        this.repositorioItemListener = repositorioItemListener;
        this.repositorioItemUpdateListener = repositorioItemUpdateListener;
        this.recyclerView = recyclerView;
        this.version_two = version_two;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == UPDATE) {
            return new RepositorioUpdateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repositorio_update, parent, false));
        } else if(viewType == DOWNLOAD) {
            int layout = R.layout.item_repositorio;
            if(version_two){
                layout = R.layout.item_repositorio_version_two;
            }
            return new RepositorioDownloadHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        }else {
            return new RepositorioBtnAddHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coment_btn_archivo, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == UPDATE) {
            ((RepositorioUpdateHolder) holder).bind((UpdateRepositorioFileUi) repositorioFileUiList.get(position), repositorioItemUpdateListener);
        } else if(holder.getItemViewType() == DOWNLOAD) {
            ((RepositorioDownloadHolder) holder).bind((RepositorioFileUi) repositorioFileUiList.get(position), repositorioItemListener);
        }else {
            ((RepositorioBtnAddHolder) holder).bind(repositorioItemListener);
        }

    }

    @Override
    public int getItemCount() {
        return repositorioFileUiList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = repositorioFileUiList.get(position);
        if( o instanceof RepositorioFileUi){
            RepositorioFileUi repositorioFileUi = (RepositorioFileUi)o;
            if (repositorioFileUi instanceof UpdateRepositorioFileUi) {
                return UPDATE;
            } else {
                return DOWNLOAD;
            }
        }else {
            return BTN_ADD;
        }


    }

    public void setList(List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(getClass().getSimpleName(),"setList :" +repositorioFileUiList.size());
        this.repositorioFileUiList.clear();
        this.repositorioFileUiList.addAll(repositorioFileUiList);
        notifyDataSetChanged();
    }

    public void update(RepositorioFileUi repositorioFileUi) {
        int position = repositorioFileUiList.indexOf(repositorioFileUi);
        if (position != -1) {
            repositorioFileUiList.set(position, repositorioFileUi);
            notifyItemChanged(position);
        }
    }

    public synchronized void updateProgress(RepositorioFileUi repositorioFileUi, int count) {
        if(repositorioFileUi instanceof UpdateRepositorioFileUi){
            RepositorioUpdateHolder repositorioHolder = getRepositorioHolder((UpdateRepositorioFileUi)repositorioFileUi);
            if (repositorioHolder != null) repositorioHolder.count(count);
        }else {
            RepositorioDownloadHolder repositorioHolder = getRepositorioHolder(repositorioFileUi);
            if (repositorioHolder != null) repositorioHolder.count(count);
        }

    }

    private synchronized RepositorioDownloadHolder getRepositorioHolder(RepositorioFileUi repositorioFileUi) {
        RepositorioDownloadHolder repositorioDownloadHolder = null;
        int posicion = this.repositorioFileUiList.indexOf(repositorioFileUi);
        Log.d(getClass().getSimpleName(), "RepositorioHolder posicion: " + posicion + "repositorioFileUi: " + repositorioFileUi.getNombreArchivo());
        if (posicion != -1) {
            repositorioDownloadHolder = (RepositorioDownloadHolder) recyclerView.findViewHolderForLayoutPosition(posicion);
        }
        return repositorioDownloadHolder;
    }
    private synchronized RepositorioUpdateHolder getRepositorioHolder(UpdateRepositorioFileUi repositorioFileUi) {
        RepositorioUpdateHolder repositorioUpdateHolder = null;
        int posicion = this.repositorioFileUiList.indexOf(repositorioFileUi);
        Log.d(getClass().getSimpleName(), "RepositorioHolder posicion: " + posicion + "repositorioFileUi: " + repositorioFileUi.getNombreArchivo());
        if (posicion != -1) {
            repositorioUpdateHolder = (RepositorioUpdateHolder) recyclerView.findViewHolderForLayoutPosition(posicion);
        }
        return repositorioUpdateHolder;
    }

    public void addPostionInitList(List<UpdateRepositorioFileUi> updateRepositorioFileUiList) {
        this.repositorioFileUiList.addAll(0, updateRepositorioFileUiList);
        notifyDataSetChanged();
    }

    public void removeListSinNotificationChange(List<UpdateRepositorioFileUi> updateRepositorioFileUis) {
        this.repositorioFileUiList.removeAll(updateRepositorioFileUis);
    }

    public void setVersion_two(boolean version_two) {
        this.version_two = version_two;
        notifyDataSetChanged();
    }
}
