package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.adapters.holders.MensajePredeterminadoViewHolder;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.List;

/**
 * Created by irvinmarin on 19/12/2017.
 */

public class MensajePredeterminadoAdapter extends RecyclerView.Adapter<MensajePredeterminadoViewHolder> {


    private List<MensajePredeterminadoUI> mensajePredeterminadoUIList;
    private OnMensajePredeterminadoClickedListener clickedListener;
    private RecyclerView recyclerView;

    public MensajePredeterminadoAdapter(RecyclerView recyclerView, List<MensajePredeterminadoUI> mensajePredeterminadoUIList, OnMensajePredeterminadoClickedListener clickedListener) {
        this.mensajePredeterminadoUIList = mensajePredeterminadoUIList;
        this.clickedListener = clickedListener;
        this.recyclerView = recyclerView;
    }

    public interface OnMensajePredeterminadoClickedListener {
        void onItemClickListener(MensajePredeterminadoUI mensajePredeterminadoUI);

        void onClickEditarListener(MensajePredeterminadoUI mensajePredeterminadoUI);

        void onClickDeleteListener(MensajePredeterminadoUI mensajePredeterminadoUI);

        void onItemAlreadyDeleted();

        void onRestoreItem(MensajePredeterminadoUI mensajePredeterminadoUI);
    }

    @NonNull
    @Override
    public MensajePredeterminadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje_predeterminado, parent, false);
        return new MensajePredeterminadoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final MensajePredeterminadoViewHolder vh, final int position) {
        vh.bind(mensajePredeterminadoUIList.get(position), clickedListener);
    }

    @Override
    public int getItemCount() {
//        return 5;
        return mensajePredeterminadoUIList.size();
    }


    public void setMensajePredeterminadoUIList(List<MensajePredeterminadoUI> mensajePredeterminadoUIList) {
        this.mensajePredeterminadoUIList.clear();
        this.mensajePredeterminadoUIList.addAll(mensajePredeterminadoUIList);
        notifyDataSetChanged();
    }

    public void delete(MensajePredeterminadoUI mensajePredeterminadoUI) {
        int position = mensajePredeterminadoUIList.indexOf(mensajePredeterminadoUI);
        if (position != -1) {
            mensajePredeterminadoUIList.remove(mensajePredeterminadoUI);
            notifyItemRemoved(position);
        }
    }

    public void add(MensajePredeterminadoUI mensajePredeterminadoUI) {
        if (!this.mensajePredeterminadoUIList.contains(mensajePredeterminadoUI)) {
            this.mensajePredeterminadoUIList.add(mensajePredeterminadoUI);
            notifyItemInserted(getItemCount() - 1);
            scrollToLastItem();
        }
    }

    private void scrollToLastItem() {
        recyclerView.scrollToPosition(getItemCount() - 1);
    }
}
