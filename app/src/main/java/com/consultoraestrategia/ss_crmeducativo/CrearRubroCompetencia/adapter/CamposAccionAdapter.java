package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.CamposAccionHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

/**
 * Created by kike on 08/05/2018.
 */

public class CamposAccionAdapter extends RecyclerView.Adapter<CamposAccionHolder> {


    public static final String TAG = CamposAccionAdapter.class.getSimpleName();
    public static final int CamposAccionAdapter_PADRE=0;
    public static final int CamposAccionAdapter_HIJO=1;
    public static final int CamposAccionAdapter_DEFECTO=2;
    List<CamposAccionUi> accionUiList;

    public CamposAccionAdapter(List<CamposAccionUi> accionUiList) {
        this.accionUiList = accionUiList;
    }

    @Override
    public CamposAccionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crear_rubro_campos_accion, parent, false);
        return new CamposAccionHolder(view);
    }

    @Override
    public void onBindViewHolder(CamposAccionHolder holder, int position) {
        CamposAccionUi camposAccionUi = accionUiList.get(position);
        holder.bind(camposAccionUi);
    }

    @Override
    public int getItemCount() {
        if (accionUiList == null) return 0;
        return accionUiList.size();
    }



    @Override
    public int getItemViewType(int position) {
        CamposAccionUi campotematicoUi = accionUiList.get(position);
        String CAMPOTEMATICODETALLE = campotematicoUi.getTipo().name();
        Log.d(TAG,"CAMPOTEMATICODETALLE" +CAMPOTEMATICODETALLE);
        if(CAMPOTEMATICODETALLE.equals("PARENT")){
            return CamposAccionAdapter_PADRE;
        }else if (CAMPOTEMATICODETALLE.equals("CHILDREN")){
            return CamposAccionAdapter_HIJO;
        }else if(CAMPOTEMATICODETALLE.equals("DEFECTO")){
            return CamposAccionAdapter_DEFECTO;
        }else{
            return 5;
        }

    }


    public void setCamposAccionList(List<CamposAccionUi> camposAccionList){
        this.accionUiList.clear();
        this.accionUiList.addAll(camposAccionList);
        notifyDataSetChanged();
    }

}
