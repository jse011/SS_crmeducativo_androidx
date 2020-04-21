package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.UnidadItem;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.listener.UnidadListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnidadItemAdapter extends RecyclerView.Adapter {

    private List<UnidadItem> unidadItems;
    private UnidadListener unidadListener;

    public UnidadItemAdapter(List<UnidadItem> unidadItems, UnidadListener unidadListener) {
        this.unidadItems = unidadItems;
        this.unidadListener = unidadListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_unidad_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UnidadItem unidadItem = unidadItems.get(i);
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.bind(unidadItem, unidadListener);
    }

    @Override
    public int getItemCount() {
        return unidadItems.size();
    }

    public void setList(List<UnidadItem> listMenu) {
        unidadItems.clear();
        unidadItems.addAll(listMenu);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener    {

        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.constrate)
        ConstraintLayout constrate;

        UnidadListener unidadListener;
        UnidadItem unidadItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            constrate.setOnClickListener(this);
        }

        public void bind(UnidadItem unidadRubros, UnidadListener unidadListener){
            this.unidadItem = unidadRubros;
            this.unidadListener = unidadListener;
            textTitle.setText(unidadRubros.getTipo().getNombre());
            constrate.setBackground(changeToogle(unidadRubros.isSelect()));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.constrate:
                    unidadListener.changeToogle(unidadItem);
                    break;
            }
        }

        public LayerDrawable changeToogle(boolean estado) {
            String colorFondo;
            String colorBorder;
            if (estado) {
                colorFondo = "#ffffffff";
                colorBorder = "#0088cc";
            } else {
                colorFondo = "#0088cc";
                colorBorder = "#ffffffff";
            }
            try{
                textTitle.setTextColor(Color.parseColor(colorBorder));
                LayerDrawable shape = (LayerDrawable) ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_radius_left);
                final GradientDrawable gradientDrawable = (GradientDrawable) shape.findDrawableByLayerId(R.id.shape);
                gradientDrawable.setStroke(2, Color.parseColor(colorBorder));
                gradientDrawable.setColor(Color.parseColor(colorFondo));
                return shape;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

    }



}
