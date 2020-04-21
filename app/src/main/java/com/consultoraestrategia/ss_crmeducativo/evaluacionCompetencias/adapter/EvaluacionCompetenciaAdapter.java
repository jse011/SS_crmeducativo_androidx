package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder.EvaluacionCompetenciasHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder.PadreFinalBimestreHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.PadreFinalBimestreUi;
import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TIPO_COMPETENCIA = 0;
    private final int NOTAFINALBIMESTRE = 1;
    private List<Object> objectList;
    private Context context;
    private EvaluacionCompetenciaListener listener;

    public EvaluacionCompetenciaAdapter(List<Object> objectList, Context context) {
        this.objectList = objectList;
        this.context = context;
    }

    public void setListener(EvaluacionCompetenciaListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TIPO_COMPETENCIA:
                View viewCompetencias = layoutInflater.inflate(R.layout.item_evaluacion_competencia, parent, false);
                viewHolder = new EvaluacionCompetenciasHolder(viewCompetencias);
                break;
            case NOTAFINALBIMESTRE:
                View viewNotaFinalBimestre = layoutInflater.inflate(R.layout.item_padre_final_bimestre, parent, false);
                viewHolder = new PadreFinalBimestreHolder(viewNotaFinalBimestre);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TIPO_COMPETENCIA:
                CompetenciaUi competenciaUi = (CompetenciaUi) objectList.get(position);
                EvaluacionCompetenciasHolder competenciasHolder = (EvaluacionCompetenciasHolder) holder;
                competenciasHolder.bind(competenciaUi, listener, objectList, context);
                break;
            case NOTAFINALBIMESTRE:
                PadreFinalBimestreUi notaFinalBimestre = (PadreFinalBimestreUi) objectList.get(position);
                PadreFinalBimestreHolder notaFinalBimestreHolder = (PadreFinalBimestreHolder) holder;
                notaFinalBimestreHolder.bind(notaFinalBimestre, listener, context);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (objectList == null) return 0;
        return objectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = objectList.get(position);
        if (object instanceof CompetenciaUi) {
            return TIPO_COMPETENCIA;
        } else if (object instanceof PadreFinalBimestreUi) {
            return NOTAFINALBIMESTRE;
        }
        return super.getItemViewType(position);
    }

    public void setObjectLista(List<Object> objectList) {
        Log.d("objectList","Size : "+ objectList.size());
        this.objectList.clear();//Limpiamos la memoria (Objeto)
        this.objectList.addAll(objectList);//Agrega toda lista
        notifyDataSetChanged(); // Pinta otra ves la lista
    }


    public void updatObject(Object o) {
        int position = this.objectList.indexOf(o);
        if (position != -1) {
            this.objectList.set(position, o);
            notifyItemChanged(position);
        }
    }
}
