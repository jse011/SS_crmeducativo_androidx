package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder.ColorCondicionalViewHolder;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.ColorCondicionalListener;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

/**
 * Created by SCIEV on 18/10/2017.
 */

public class ColorCondicionalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG = "ColorCondicionalAdapter";
    private List<ColorCondicionalUi> colorCondicionalUis;
    private RecyclerView recyclerView;
    private ColorCondicionalListener listener;

    public ColorCondicionalAdapter(List<ColorCondicionalUi> colorCondicionalUis, RecyclerView recyclerView, ColorCondicionalListener listener) {
        this.colorCondicionalUis = colorCondicionalUis;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_color_condiconal, viewGroup, false);
        return new ColorCondicionalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewGroup, int position) {
        ColorCondicionalUi colorCondicionalUi = colorCondicionalUis.get(position);
        ColorCondicionalViewHolder colorCondicionalViewHolder = (ColorCondicionalViewHolder) viewGroup;
        colorCondicionalViewHolder.bind(colorCondicionalUi, listener);
    }

    @Override
    public int getItemCount() {
        return colorCondicionalUis.size();
    }

    public void addColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        this.colorCondicionalUis.add(colorCondicionalUi);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public int updateColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        int position = this.colorCondicionalUis.indexOf(colorCondicionalUi);
        Log.d(TAG, "updateColorCondicional: " + position);
        if (position != -1) {
            this.colorCondicionalUis.set(position, colorCondicionalUi);
            notifyItemChanged(position);
        }
        return position;
    }

    public void deleteColorCondiconal(ColorCondicionalUi colorCondicionalUi) {
        int position = this.colorCondicionalUis.indexOf(colorCondicionalUi);
        if (position != -1) {
            this.colorCondicionalUis.remove(colorCondicionalUi);
            notifyItemRemoved(position);
        }

    }

    public void setColorCondicionalUis(List<ColorCondicionalUi> colorCondicionalUis) {
        this.colorCondicionalUis.clear();
        this.colorCondicionalUis.addAll(colorCondicionalUis);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public List<ColorCondicionalUi> getColorCondicionalUis() {
        return colorCondicionalUis;
    }

}
