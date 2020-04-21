package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters;

import android.graphics.drawable.Drawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.ListenerTabCreateComport;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TiposHolder  extends RecyclerView.ViewHolder {

    @BindView(R.id.txttipo)
    TextView txttipo;
    TipoUi tipoUi;
    @BindView(R.id.view)
    ConstraintLayout view;
    @BindView(R.id.imgCheck)
    ImageView imgCheck;
    final RecyclerView.ViewHolder viewHolder=this;
    ListenerTabCreateComport listener;
    public TiposHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
    public void bind(final TipoUi tipoUi, final ListenerTabCreateComport listener){
        this.tipoUi=tipoUi;
        this.listener=listener;
        txttipo.setText(tipoUi.getNombre());
        imgCheck.setVisibility(View.GONE);
        if(tipoUi.isSelected()) {
            seleccionar();
        }
        else deseleccionar();;

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.seletedTipo(tipoUi, viewHolder);
            }
        });

    }
    public TipoUi getTipoUi(){
        return tipoUi;
    }

    public void seleccionar(){
        imgCheck.setVisibility(View.VISIBLE);
        listener.setViewHolder(viewHolder);
        tipoUi.setSelected(true);
        Drawable drawable = ContextCompat.getDrawable(itemView.getContext(),R.drawable.ic_success);
        imgCheck.setImageDrawable(drawable);
    }
    public void deseleccionar(){
        imgCheck.setVisibility(View.GONE);
        tipoUi.setSelected(false);
    }


}
