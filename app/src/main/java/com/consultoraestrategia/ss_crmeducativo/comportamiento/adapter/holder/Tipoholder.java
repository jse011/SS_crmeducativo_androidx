package com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tipoholder extends RecyclerView.ViewHolder {
    @BindView(R.id.url_tipo)
    ImageView url_tipo;
    @BindView( R.id.cantidadComportamiento)
    TextView cantidadComportamiento;
    @BindView( R.id.tipoComportamiento)
    TextView tipoComportamiento;

    public Tipoholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TipoUi tipo){

        int ruta=0;
        switch (tipo.getId()){
            case 541:
                ruta=R.drawable.medal;
                break;
            case 542:
                ruta= R.drawable.policeman;
                break;
            default:
                ruta= R.drawable.ic_graduacion;
                break;
        }

        tipoComportamiento.setText(tipo.getNombre());
        url_tipo.setBackgroundResource(ruta);

        cantidadComportamiento.setText(String.valueOf(tipo.getCantidad()));
    }
}
