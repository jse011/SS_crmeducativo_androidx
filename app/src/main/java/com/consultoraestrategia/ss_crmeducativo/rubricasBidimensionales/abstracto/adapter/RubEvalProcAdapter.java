package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.holder.RubEvalProcHolder;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;

import java.util.List;

/**
 * Created by CCIE on 19/03/2018.
 */

public class RubEvalProcAdapter extends RecyclerView.Adapter<RubEvalProcHolder> {
    private List<RubEvalProcUi> rubEvalProcUiList;

    public RubEvalProcAdapter(List<RubEvalProcUi> rubEvalProcUiList) {
        this.rubEvalProcUiList = rubEvalProcUiList;
    }

    @NonNull
    @Override
    public RubEvalProcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubros_rubricas, parent, false);
        return new RubEvalProcHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RubEvalProcHolder holder, int position) {
        RubEvalProcUi rubEvalProcUi = rubEvalProcUiList.get(position);
        holder.bind(rubEvalProcUi);
    }

    @Override
    public int getItemCount() {
        if (rubEvalProcUiList==null)return 0;
        return rubEvalProcUiList.size();
    }
}
