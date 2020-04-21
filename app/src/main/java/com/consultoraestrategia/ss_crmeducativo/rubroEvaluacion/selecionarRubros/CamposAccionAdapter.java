package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder.CamposAccionHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 08/05/2018.
 */

public class CamposAccionAdapter extends RecyclerView.Adapter<CamposAccionHolder> {


    public static final String TAG = CamposAccionAdapter.class.getSimpleName();
    public static final int CamposAccionAdapter_PADRE=0;
    public static final int CamposAccionAdapter_HIJO=1;
    public static final int CamposAccionAdapter_DEFECTO=2;
    List<CamposAccionUi> accionUiList = new ArrayList<>();

    public CamposAccionAdapter(List<CamposAccionUi> accionUiList) {
        this.accionUiList.addAll(accionUiList);
    }

    @Override
    public CamposAccionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubro_select_campos_accion, parent, false);
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

    public void setList(List<CamposAccionUi> camposAccionUiList) {
        accionUiList.clear();
        accionUiList.addAll(camposAccionUiList);
    }
}
