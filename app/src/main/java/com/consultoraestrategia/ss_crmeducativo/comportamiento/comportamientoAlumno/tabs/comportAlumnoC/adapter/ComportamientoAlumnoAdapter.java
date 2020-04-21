package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui.ListenerComportAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.ArrayList;
import java.util.List;

public class ComportamientoAlumnoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private String TAG = ComportamientoAlumnoAdapter.class.getSimpleName();
    private List<ComportamientoUi> comportamientoUiList;
    private ListenerComportAlumnoC listenerComportAlumnoC;
    private DownloadItemListener downloadItemListener;
    private RecyclerView recyclerView;

    public ComportamientoAlumnoAdapter(List<ComportamientoUi> comportamientoUiList, ListenerComportAlumnoC listenerComportAlumnoC, DownloadItemListener downloadItemListener, RecyclerView recyclerView) {
        this.comportamientoUiList = comportamientoUiList;
        this.listenerComportAlumnoC = listenerComportAlumnoC;
        this.downloadItemListener = downloadItemListener;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comport_alumno, parent, false);
        return new ComportamientoAlumnoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ComportamientoUi comportamientoUi = comportamientoUiList.get(position);
        ComportamientoAlumnoHolder comportamientoAlumnoHolder = (ComportamientoAlumnoHolder) holder;
        comportamientoAlumnoHolder.bind(comportamientoUi, listenerComportAlumnoC,downloadItemListener);

    }

    @Override
    public int getItemCount() {
        return comportamientoUiList.size();
    }

    public void setComportamientoUiList(List<ComportamientoUi> lista) {
        comportamientoUiList.clear();
        this.comportamientoUiList.addAll(lista);
        notifyDataSetChanged();
    }

    public void delete(ComportamientoUi comportamientoUi) {
        Log.d(TAG, "elimimna");
        int position = comportamientoUiList.indexOf(comportamientoUi);
        if (position != -1) {
            comportamientoUiList.remove(comportamientoUi);
            notifyItemRemoved(position);
        }
    }

    public void add(ComportamientoUi comportamientoUi) {
        Log.d(TAG, "agregar");
        if (!this.comportamientoUiList.contains(comportamientoUi)) {
            this.comportamientoUiList.add(comportamientoUi);
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public int update(ComportamientoUi comportamientoUi) {
        int position = this.comportamientoUiList.indexOf(comportamientoUi);
        if (position != -1) {
            this.comportamientoUiList.set(position, comportamientoUi);
            notifyItemChanged(position);
        }
        return position;

    }

    public List<DownloadAdapter> getListDownloadAdapter(RepositorioFileUi repositorioFileUi) {
        List<DownloadAdapter> downloadAdapters = new ArrayList<>();
        try {
            ArchivoUi archivoUi = (ArchivoUi)repositorioFileUi;
            for (int childCount = recyclerView.getChildCount(), i = 0; i < childCount; ++i) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                if(holder instanceof  ComportamientoAlumnoHolder){
                    ComportamientoAlumnoHolder comportamientoAlumnoHolder = (ComportamientoAlumnoHolder) holder;
                    List<ArchivoUi> repositorioFileUiList = comportamientoAlumnoHolder.getRecursosUIList();
                    int posicion = repositorioFileUiList.indexOf(archivoUi);
                    if(posicion!=-1){
                        DownloadAdapter downloadAdapter = comportamientoAlumnoHolder.getRecursosCasosAdapter();
                        downloadAdapters.add(downloadAdapter);
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return downloadAdapters;
    }

    public void update(RepositorioFileUi repositorioFileUi){
        for (DownloadAdapter downloadAdapter : getListDownloadAdapter(repositorioFileUi)){
            downloadAdapter.update(repositorioFileUi);
        }
    }

    public void updateProgress(RepositorioFileUi repositorioFileUi, int count) {
        for (DownloadAdapter downloadAdapter : getListDownloadAdapter(repositorioFileUi)){
            downloadAdapter.updateProgress(repositorioFileUi, count);
        }
    }
}
