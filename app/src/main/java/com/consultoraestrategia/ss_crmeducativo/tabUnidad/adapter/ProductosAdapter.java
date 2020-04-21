package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder.ProductoHolder;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter {

    List<ProductoUi> productoUis;

    public ProductosAdapter(List<ProductoUi> productoUis) {
        this.productoUis = productoUis;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_producto_unidad, viewGroup, false);
        return new ProductoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ProductoUi productoUi = productoUis.get(i);
        ProductoHolder holder = (ProductoHolder) viewHolder;
        holder.bind(productoUi, i+1);
    }

    @Override
    public int getItemCount() {
        return productoUis.size();
    }

    public void setProductoUis(List<ProductoUi> productoUis) {
        this.productoUis.clear();
        this.productoUis.addAll(productoUis);
        notifyDataSetChanged();
    }
}
