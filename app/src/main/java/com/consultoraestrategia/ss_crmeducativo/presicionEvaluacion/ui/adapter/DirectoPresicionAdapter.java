package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.adapter.holder.DirectoPresicionHolder;

import java.util.List;

public class DirectoPresicionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NotaCircularUi> stringList;

    public DirectoPresicionAdapter(List<NotaCircularUi> stringList) {
        this.stringList = stringList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_directo_presicion, parent, false);
        return new DirectoPresicionHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotaCircularUi notaCircularUi = stringList.get(position);
        DirectoPresicionHolder directoPresicionHolder = (DirectoPresicionHolder)holder;
        directoPresicionHolder.bind(notaCircularUi);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public void setList(List<NotaCircularUi> notaCircularUiList){
        this.stringList.clear();
        this.stringList.addAll(notaCircularUiList);
        notifyDataSetChanged();
    }
}
