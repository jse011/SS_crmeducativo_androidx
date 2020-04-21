package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class GrupoColumnViewHolder extends ColumnHeaderViewHolder<GrupoProcesoUi> {
    @BindView(R.id.imgToogle)
    ImageView imgToogle;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;
    private GrupoProcesoUi grupoProcesoUi;
    public GrupoColumnViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bind(GrupoProcesoUi columnHeader) {
        this.grupoProcesoUi = columnHeader;
        String nombre = columnHeader.getNombre();
        if(nombre != null){
            nombre = nombre.toUpperCase();
        }
        txtTitle.setText(nombre);
        if (columnHeader.isToogle()) {
            imgToogle.clearAnimation();
            imgToogle.setRotation(180);
            imgToogle.setSelected(false);
        } else {
            imgToogle.clearAnimation();
            imgToogle.setRotation(0);
            //ViewCompat.animate(imgProfile).rotation(180).start();
            imgToogle.setSelected(true);
        }
    }

    public GrupoProcesoUi getGrupoProcesoUi() {
        return grupoProcesoUi;
    }
}
