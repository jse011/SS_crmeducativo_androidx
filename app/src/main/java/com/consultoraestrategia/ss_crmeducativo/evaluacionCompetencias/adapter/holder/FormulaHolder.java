package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/04/2018.
 */

public class FormulaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_cant_rubro)
    TextView textViewCantidad;

    public FormulaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CapacidadUi capacidadUi, String parametroDisenio, Context context) {
        textViewCantidad.setText(capacidadUi.getContador()+"");
//        Drawable mDrawable = context.getResources().getDrawable(R.drawable.ic_circle_unidades);
//        mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(parametroDisenio), PorterDuff.Mode.SRC_IN));
//        textViewCantidad.setBackground(mDrawable);
    }
}
