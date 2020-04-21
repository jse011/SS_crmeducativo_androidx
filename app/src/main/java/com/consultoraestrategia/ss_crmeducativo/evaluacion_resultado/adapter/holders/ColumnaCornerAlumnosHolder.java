package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnaCornerAlumnosHolder  extends AbstractViewHolder {

    @BindView(R.id.cantAlumnos)
    TextView cantAlumnos;
    @BindView(R.id.fondo)
    View fondo;

    public ColumnaCornerAlumnosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int cantidadA, String color){
        String cantidadAlumnos = "("+cantidadA+")";
        cantAlumnos.setText(cantidadAlumnos);
        try {
            if(!TextUtils.isEmpty(color))fondo.setBackgroundColor(Color.parseColor(color));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
