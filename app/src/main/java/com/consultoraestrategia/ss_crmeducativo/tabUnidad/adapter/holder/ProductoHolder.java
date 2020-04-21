package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txtProducto)
    TextView txtProducto;
    @BindView(R.id.view)
    ImageView imgBottom;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.txtDescripcion)
    TextView txtDescripcion;
    @BindView(R.id.constraintLayoutDescripcion)
    ConstraintLayout constraintLayoutDescripcion;
    boolean state=true;

    public ProductoHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardView.setOnClickListener(this);
    }

    public void bind(ProductoUi productoUi, int position){
        txtProducto.setText(position+". "+productoUi.getProductoNombre()+"  "+productoUi.getAvance());
        txtDescripcion.setText(productoUi.getDescripcion());
        changeTogle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardView:
                changeTogle();
                break;
        }
    }

    private void changeTogle() {
        if (state){
            txtDescripcion.setVisibility(View.GONE);
            imgBottom.setRotation(0);
            state=false;
        }else {
            txtDescripcion.setVisibility(View.VISIBLE);
            imgBottom.setRotation(180);
            state=true;
        }
    }
}
