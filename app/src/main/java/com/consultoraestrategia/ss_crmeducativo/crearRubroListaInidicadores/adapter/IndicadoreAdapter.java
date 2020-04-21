package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder.ViewHolderCapacidad;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder.ViewHolderCompetencia;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder.ViewHolderIndicador;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CampotematicoListener;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.List;


public class IndicadoreAdapter extends SectioningAdapter {
    private final static int COMPETENCIA = 1, CAPACIDAD = 2, INDICADOR = 0;
    private List<CompetenciaUi> competenciaUiList;
    private RecyclerView recyclerView;
    private CampotematicoListener campotematicoListener;
    String TAG= IndicadoreAdapter.class.getSimpleName();

    public IndicadoreAdapter(List<CompetenciaUi> competenciaUiList, CampotematicoListener campotematicoListener) {
        this.competenciaUiList = competenciaUiList;
        this.campotematicoListener = campotematicoListener;
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_header_competencia, parent, false);
        return new ViewHolderCompetencia(v);
    }

    @Override
    public GhostHeaderViewHolder onCreateGhostHeaderViewHolder(ViewGroup parent) {
        final View ghostView = new View(parent.getContext());
        ghostView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new GhostHeaderViewHolder(ghostView);
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        CompetenciaUi competencia = competenciaUiList.get(sectionIndex);
        switch (itemType) {
            case CAPACIDAD:
                CapacidadUi capacidadUi = (CapacidadUi) competencia.getItems().get(itemIndex);
                ((ViewHolderCapacidad) viewHolder).bind(capacidadUi);
                break;
            case INDICADOR:
                IndicadorUi indicadorUi = (IndicadorUi) competencia.getItems().get(itemIndex);
                ((ViewHolderIndicador) viewHolder).bind(competencia,indicadorUi,campotematicoListener);
                break;
        }
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        ItemViewHolder holder;
        switch (itemUserType) {
            case CAPACIDAD:
                View viewCapacidad = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_capacidad, parent, false);
                holder = new ViewHolderCapacidad(viewCapacidad);
                break;
            default:
                View viewIndicador = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_indicador, parent, false);
                holder = new ViewHolderIndicador(viewIndicador);
                break;
        }
        return holder;
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        CompetenciaUi competencia = competenciaUiList.get(sectionIndex);
        ViewHolderCompetencia hvh = (ViewHolderCompetencia) viewHolder;
        hvh.bind(competencia);
    }

    @Override
    public int getNumberOfSections() {
        return competenciaUiList.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        Log.d(TAG, "getNumberOfItemsInSection");
        int numberOfItemsInSection = competenciaUiList.get(sectionIndex).getItems().size();
        Log.d(TAG, "numberOfItemsInSection: " + numberOfItemsInSection);
        return numberOfItemsInSection;
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }


    @Override
    public int getSectionItemUserType(int sectionIndex, int itemIndex) {
        CompetenciaUi competencia = competenciaUiList.get(sectionIndex);
        Log.d(getClass().getSimpleName(), "items: "+ competencia.getItems().size());
        Object object = competencia.getItems().get(itemIndex);
        if (object instanceof CapacidadUi) {
            return CAPACIDAD;
        }
        return INDICADOR;
    }


    public void setIndicador(List<CompetenciaUi> competenciaUiList) {
        this.competenciaUiList.clear();
        this.competenciaUiList.addAll(competenciaUiList);
        notifyAllSectionsDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void notifyAllChildChanged(final RecyclerView recyclerView) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<recyclerView.getChildCount(); i++){
                    RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                    if(holder instanceof ViewHolderIndicador){
                        ViewHolderIndicador indicadorViewHolder =  (ViewHolderIndicador)holder;
                        indicadorViewHolder.notifyChanged();
                    }
                }
            }
        });
    }
}