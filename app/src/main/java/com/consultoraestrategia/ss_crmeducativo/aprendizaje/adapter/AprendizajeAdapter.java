package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.CampoTematicoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.CompetenciasViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.DescripcionViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.EvaluacionViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.EvidenciaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemCampotematicoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemCapacidadViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemCompetenciaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemDesempenioViewHolderV2;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemEvidenciaViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemIcdsViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.ItemLineViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder.RecursoDidacticoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CarCampoAccion;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CarEvidenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardCompeteciasUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardRecursosDidacticosUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardSesionUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.IcdsUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class AprendizajeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int COMPETENCIA = 1, DESCRIPCION = 2, EVALUACION = 3, RECURSOS = 4, ITEMCOMPETENCIA = 6, ITEMCAPASIDAD = 7, ITEMDESEMPENIO = 8, ITEMLINE = 9, CAMPOACCION = 10, ITEMCAPOACCION = 11, ITEMICDS = 12, EVIDENCIAS = 13, ITEMEVIDENCIA = 14;
    private List<Object> items;
    private DownloadItemListener downloadListener;
    private RecyclerView recyclerView;
    private boolean view =true;

    public AprendizajeAdapter(List<Object> items, DownloadItemListener downloadListener, RecyclerView recyclerView) {
        this.items = items;
        this.downloadListener = downloadListener;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case COMPETENCIA:
                view = layoutInflater.inflate(R.layout.item_aprendizaje_competencia, parent, false);
                viewHolder = new CompetenciasViewHolder(view);
                break;
            case DESCRIPCION:
                view = layoutInflater.inflate(R.layout.item_aprendizaje_descripcion, parent, false);
                viewHolder = new DescripcionViewHolder(view);
                break;
            case EVALUACION:
                view = layoutInflater.inflate(R.layout.item_aprendizaje_evaluacion, parent, false);
                viewHolder = new EvaluacionViewHolder(view);
                break;
            case RECURSOS:
                view = layoutInflater.inflate(R.layout.item_aprendizaje_recursos_didactico, parent, false);
                viewHolder = new RecursoDidacticoViewHolder(view);
                break;
            case ITEMCOMPETENCIA:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_competencia, parent, false);
                viewHolder = new ItemCompetenciaViewHolder(view);
                break;
            case ITEMCAPASIDAD:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_capacidad, parent, false);
                viewHolder = new ItemCapacidadViewHolder(view);
                break;
            case ITEMDESEMPENIO:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_desempenio_v2, parent, false);
                viewHolder = new ItemDesempenioViewHolderV2(view);
                break;
            case CAMPOACCION:
                view = layoutInflater.inflate(R.layout.item_aprendizaje_campo_accion, parent, false);
                viewHolder = new CampoTematicoViewHolder(view);
                break;
            case ITEMCAPOACCION:
                view = layoutInflater.inflate(R.layout.item_detalle_campotematico, parent, false);
                viewHolder = new ItemCampotematicoViewHolder(view);
                break;
            case ITEMICDS:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_icds, parent, false);
                viewHolder = new ItemIcdsViewHolder(view);
                break;
            case EVIDENCIAS:
                view = layoutInflater.inflate(R.layout.item_aprendizaje_evidencia, parent, false);
                viewHolder = new EvidenciaViewHolder(view);
                break;
            case ITEMEVIDENCIA:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_evidencia, parent, false);
                viewHolder = new ItemEvidenciaViewHolder(view);
                break;
            default:
                view = layoutInflater.inflate(R.layout.item_detalle_aprendizaje_linea, parent, false);
                viewHolder = new ItemLineViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case COMPETENCIA:
                CompetenciasViewHolder competenciasViewHolder = (CompetenciasViewHolder) holder;
                CardCompeteciasUi cardCompeteciasUi = (CardCompeteciasUi) items.get(position);
                competenciasViewHolder.bind(cardCompeteciasUi);
                break;
            case DESCRIPCION:
                DescripcionViewHolder descripcionViewHolder = (DescripcionViewHolder) holder;
                CardSesionUi sesionUi = (CardSesionUi) items.get(position);
                descripcionViewHolder.bind(sesionUi);
                break;
            case EVALUACION:
                break;
            case RECURSOS:
                RecursoDidacticoViewHolder recursoDidacticoViewHolder = (RecursoDidacticoViewHolder) holder;
                CardRecursosDidacticosUi cardRecursosDidacticosUi = (CardRecursosDidacticosUi) items.get(position);
                recursoDidacticoViewHolder.bind(cardRecursosDidacticosUi, downloadListener);
                break;
            case ITEMCOMPETENCIA:
                ItemCompetenciaViewHolder itemCompetenciaViewHolder = (ItemCompetenciaViewHolder) holder;
                CompetenciaUi competenciaUi = (CompetenciaUi) items.get(position);
                itemCompetenciaViewHolder.bind(competenciaUi, this, view);
                break;
            case ITEMCAPASIDAD:
                ItemCapacidadViewHolder itemCapacidadViewHolder = (ItemCapacidadViewHolder) holder;
                CompetenciaUi capacidadUi = (CompetenciaUi) items.get(position);
                itemCapacidadViewHolder.bind(capacidadUi, view);
                break;
            case ITEMDESEMPENIO:
                ItemDesempenioViewHolderV2 itemDesempenioViewHolder = (ItemDesempenioViewHolderV2) holder;
                DesempenioUi desempenioUi = (DesempenioUi) items.get(position);
                itemDesempenioViewHolder.bind(desempenioUi, view);
                break;
            case CAMPOACCION:
                CampoTematicoViewHolder campoTematicoViewHolder = (CampoTematicoViewHolder) holder;
                CarCampoAccion carCampoAccion = (CarCampoAccion) items.get(position);
                campoTematicoViewHolder.bind(carCampoAccion);
                break;
            case ITEMCAPOACCION:
                ItemCampotematicoViewHolder itemCampotematicoViewHolder = (ItemCampotematicoViewHolder) holder;
                CampotematicoUi campotematicoUi = (CampotematicoUi) items.get(position);
                itemCampotematicoViewHolder.bind(campotematicoUi);
                break;
            case ITEMICDS:
                ItemIcdsViewHolder itemIcdsViewHolder = (ItemIcdsViewHolder) holder;
                IcdsUi icdsUi = (IcdsUi) items.get(position);
                itemIcdsViewHolder.bind(icdsUi);
                break;
            case EVIDENCIAS:
                EvidenciaViewHolder evidenciaViewHolder = (EvidenciaViewHolder) holder;
                CarEvidenciaUi carEvidenciaUi = (CarEvidenciaUi) items.get(position);
                evidenciaViewHolder.bind(carEvidenciaUi);
                break;
            case ITEMEVIDENCIA:
                ItemEvidenciaViewHolder itemEvidenciaViewHolder = (ItemEvidenciaViewHolder) holder;
                EvidenciaUi evidenciaUi = (EvidenciaUi) items.get(position);
                itemEvidenciaViewHolder.bind(evidenciaUi);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof CardCompeteciasUi) {
            return COMPETENCIA;
        } else if (item instanceof CardSesionUi) {
            return DESCRIPCION;
        } else if (item instanceof EvaluacionUi) {
            return EVALUACION;
        } else if (item instanceof CardRecursosDidacticosUi) {
            return RECURSOS;
        } else if (item instanceof CompetenciaUi) {
            CompetenciaUi competenciaUi = (CompetenciaUi) item;
            switch (competenciaUi.getTabla()) {
                case COMPETENCIA:
                    return ITEMCOMPETENCIA;
                case CAPASIDAD:
                    return ITEMCAPASIDAD;
                default:
                    return -1;
            }
        } else if (item instanceof DesempenioUi) {
            return ITEMDESEMPENIO;
        } else if (item instanceof Integer) {
            return ITEMLINE;
        } else if (item instanceof CarCampoAccion) {
            return CAMPOACCION;
        } else if (item instanceof CampotematicoUi) {
            return ITEMCAPOACCION;
        } else if (item instanceof IcdsUi) {
            return ITEMICDS;
        } else if (item instanceof CarEvidenciaUi) {
            return EVIDENCIAS;
        } else if (item instanceof EvidenciaUi) {
            return ITEMEVIDENCIA;
        } else {
            return -1;
        }
    }


    public void changeStateView(){
        if (view){
            this.view=false;
            //notifyDataSetChanged();
        }else {
            this.view=true;
            //notifyDataSetChanged();
        }
        notifyDataSetChanged();
    }

    public void addAprendizaje(Object item) {
        this.items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void setAprendizaje(List<Object> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void clearAprendizaje() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void update(RepositorioFileUi repositorioFileUi) {
        for (DownloadAdapter downloadAdapter : getListDownloadAdapter()) {
            downloadAdapter.update(repositorioFileUi);
        }
    }

    public void updateProgress(RepositorioFileUi repositorioFileUi, int count) {
        for (DownloadAdapter downloadAdapter : getListDownloadAdapter()) {
            downloadAdapter.updateProgress(repositorioFileUi, count);
        }
    }

    public List<DownloadAdapter> getListDownloadAdapter() {
        List<DownloadAdapter> downloadAdapters = new ArrayList<>();
        try {

            for (int childCount = recyclerView.getChildCount(), i = 0; i < childCount; ++i) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                if (holder instanceof RecursoDidacticoViewHolder) {
                    RecursoDidacticoViewHolder recursoDidacticoViewHolder = (RecursoDidacticoViewHolder) holder;
                    DownloadAdapter downloadAdapter = recursoDidacticoViewHolder.getAdapter();
                    downloadAdapters.add(downloadAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadAdapters;
    }
}
