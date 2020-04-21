package com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaDiaHolder extends AbstractViewHolder {
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txt_etiqueta)
    TextView txtEtiqueta;
    public ColumnaDiaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(DiaUi p_jValue) {

        LayerDrawable shape = (LayerDrawable) ContextCompat.getDrawable(itemView.getContext(),R.drawable.border_radius_top);
        GradientDrawable gradientDrawable = null;
        if(shape!=null)gradientDrawable = (GradientDrawable) shape.findDrawableByLayerId(R.id.shape);
        try {
            int colorFondo = Color.parseColor(p_jValue.getColor().getNombre());
            if(gradientDrawable!=null){
                gradientDrawable.setColor(colorFondo);
                gradientDrawable.setStroke(2,colorFondo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        fondo.setBackground(shape );
        txtEtiqueta.setText(p_jValue.getAlias());

    }
}
