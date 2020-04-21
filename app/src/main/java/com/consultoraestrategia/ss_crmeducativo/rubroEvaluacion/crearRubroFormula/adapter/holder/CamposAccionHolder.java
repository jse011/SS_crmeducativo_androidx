package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 08/05/2018.
 */

public class CamposAccionHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_espacio)
    TextView txtEspacio;
    @BindView(R.id.txt_binieta)
    TextView txtBinieta;
    @BindView(R.id.txt_nombre)
    TextView txtNombre;


    public CamposAccionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CamposAccionUi camposAccionUi) {
        txtBinieta.setText(camposAccionUi.getBinieta());
        txtNombre.setText(camposAccionUi.getNombre());
        String CAMPOTEMATICODETALLE = camposAccionUi.getTipo().name();
        if (CAMPOTEMATICODETALLE.equals("PARENT")) {
            txtEspacio.setVisibility(View.GONE);
        } else if (CAMPOTEMATICODETALLE.equals("CHILDREN")) {
            txtEspacio.setVisibility(View.VISIBLE);
        } else if (CAMPOTEMATICODETALLE.equals("DEFECTO")) {
            txtEspacio.setVisibility(View.GONE);
        }
    }
}
