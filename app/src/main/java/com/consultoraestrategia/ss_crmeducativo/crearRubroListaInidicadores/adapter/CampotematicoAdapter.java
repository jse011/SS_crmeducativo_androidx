package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder.ViewHolderCampotematico;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder.ViewHolderCampotematicoDetalle;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder.ViewHolderCampotematicoPadre;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CampotematicoListener;

import java.util.List;


public class CampotematicoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG  = CampotematicoAdapter.class.getSimpleName();

    private final int CAMPOTEMATICOPADRE = 1, CAMPOTEMATICODETALLE = 2, CAMPOTEMATICOSIMPLE = 3;
    private IndicadorUi indicadorUi;
    private List<CampotematicoUi> campotematicoUis;
    private RecyclerView recyclerView;
    private CampotematicoListener campotematicoListener;
    private CompetenciaUi competenciaUi;

    public CampotematicoAdapter(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, List<CampotematicoUi> campotematicoUis, CampotematicoListener campotematicoListener) {
        this.indicadorUi = indicadorUi;
        this.campotematicoUis = campotematicoUis;
        this.campotematicoListener = campotematicoListener;
        this.competenciaUi =  competenciaUi;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case CAMPOTEMATICOPADRE:
                View view = layoutInflater.inflate(R.layout.layout_item_competencias_padre, viewGroup, false);
                viewHolder = new ViewHolderCampotematicoPadre(view);
                break;
            case CAMPOTEMATICODETALLE:
                View view2 = layoutInflater.inflate(R.layout.layout_item_competencias_detalle, viewGroup, false);
                viewHolder = new ViewHolderCampotematicoDetalle(view2);
                break;
            default:
                View view3 = layoutInflater.inflate(R.layout.layout_item_competencias, viewGroup, false);
                viewHolder = new ViewHolderCampotematico(view3);
                break;
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CampotematicoUi campotematicoUi = campotematicoUis.get(position);
        switch (viewHolder.getItemViewType()) {
            case CAMPOTEMATICOPADRE:
                Log.d(TAG,"CAMPOTEMATICOPADRE");
                ViewHolderCampotematicoPadre viewHolderCampotematicoPadre = (ViewHolderCampotematicoPadre) viewHolder;
                viewHolderCampotematicoPadre.bind(campotematicoUi);
                break;
            case CAMPOTEMATICODETALLE:
                Log.d(TAG,"CAMPOTEMATICODETALLE");
                ViewHolderCampotematicoDetalle viewHolderCampotematicoDetalle  = (ViewHolderCampotematicoDetalle) viewHolder;
                viewHolderCampotematicoDetalle.bind(competenciaUi,indicadorUi,campotematicoUi, campotematicoListener);
                break;
            case CAMPOTEMATICOSIMPLE:
                Log.d(TAG,"CAMPOTEMATICODETALLE");
                ViewHolderCampotematico viewHolderCampotematico  = (ViewHolderCampotematico) viewHolder;
                viewHolderCampotematico.bind(competenciaUi,indicadorUi,campotematicoUi, campotematicoListener);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return campotematicoUis.size();
    }

    @Override
    public int getItemViewType(int position) {
        CampotematicoUi campotematicoUi = campotematicoUis.get(position);
        if(campotematicoUi.isHijo()){
            return CAMPOTEMATICODETALLE;
        }else{
            if(campotematicoUi.isDisable()){
                return CAMPOTEMATICOPADRE;
            }else {
                return CAMPOTEMATICOSIMPLE;
            }
        }
    }

    public void addCampotematico(CampotematicoUi campotematicoUi) {
        this.campotematicoUis.add(campotematicoUi);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public void updateCampotematico(CampotematicoUi campotematicoUi) {
        int posicion = this.campotematicoUis.indexOf(campotematicoUi);
        if (posicion != -1) {
            this.campotematicoUis.set(posicion, campotematicoUi);
            notifyItemChanged(posicion);
        }
    }

    public void deleteCampotematico(CampotematicoUi campotematicoUi) {
        int posicion = this.campotematicoUis.indexOf(campotematicoUi);
        if (posicion != -1) {
            this.campotematicoUis.remove(campotematicoUi);
            notifyItemRemoved(posicion);
        }
    }

    public void setCampotematico(List<CampotematicoUi> campotematicoUis) {
        this.campotematicoUis.clear();
        this.campotematicoUis.addAll(campotematicoUis);
        notifyDataSetChanged();
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
                    if(holder instanceof ViewHolderCampotematico){
                        ViewHolderCampotematico indicadorViewHolder =  (ViewHolderCampotematico)holder;
                        indicadorViewHolder.notifyChanged();
                    }else if( holder instanceof ViewHolderCampotematicoDetalle){
                        ViewHolderCampotematicoDetalle viewHolderCampotematicoDetalle =  (ViewHolderCampotematicoDetalle)holder;
                        viewHolderCampotematicoDetalle.notifyChanged();
                    }
                }
            }
        });
    }

}