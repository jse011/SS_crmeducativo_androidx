package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.CaracteristicaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultadoDetEstiloAprendizajeHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;

    public ResultadoDetEstiloAprendizajeHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CaracteristicaUi caracteristicaUi){
        txtDescripcion.setText(caracteristicaUi.getDescripcion());
    }


}
