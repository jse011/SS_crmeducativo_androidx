package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter.holder.CellHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.listener.InfoRubricaBidimensionalListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;

import java.util.List;


public class CellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private static String TAG = CellAdapter.class.getSimpleName();
    private List<CriterioEvaluacionUi> criterioEvaluacionUis;
    private RecyclerView recyclerView;
    private InfoRubricaBidimensionalListener listener;
    private CriterioEvaluacionUi criterioEvaluacionUi;
    private List<Cell>cellList;

    public CellAdapter(List<CriterioEvaluacionUi> criterioEvaluacionUis, RecyclerView recyclerView, InfoRubricaBidimensionalListener listener, List<Cell> cellLista) {
        this.criterioEvaluacionUis=criterioEvaluacionUis;
        this.recyclerView = recyclerView;
        this.listener = listener;
        this.cellList = cellLista;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell_rubrica_info, parent, false);
        return new CellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CriterioEvaluacionUi criterioEvaluacionUi = criterioEvaluacionUis.get(position);
        CellHolder cellHolder = (CellHolder) holder;
        cellHolder.bind(listener,criterioEvaluacionUi, criterioEvaluacionUis,position, cellList);

//        for (Cell cell: criterioEvaluacionUi.getCellList())
//            if (cell instanceof CriterioEvaluacionUi){
//                criterioEvaluacionUi =criterioEvaluacionUis.get(position);
//                CellHolder cellHolder = (CellHolder) holder;
//                cellHolder.bind(listener,criterioEvaluacionUi, criterioEvaluacionUis,position);
//            }

    }

    @Override
    public int getItemCount() {
        return criterioEvaluacionUis.size();
    }

    public void setCellList(List<CriterioEvaluacionUi> criterioEvaluacionUis) {
        this.criterioEvaluacionUis.clear();
        this.criterioEvaluacionUis.addAll(criterioEvaluacionUis);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public List<CriterioEvaluacionUi> getValorTipoNotaUis() {
        return criterioEvaluacionUis;
    }

    public InfoRubricaBidimensionalListener getListener() {
        return listener;
    }


    public void disabledClickListenerCampoAccion() {

    }

    public void enabledClickListenerCampoAccion() {

    }
}

