package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 08/05/2018.
 */

public class CamposAccionHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.chbx)
    CheckBox checkBox;
    @BindView(R.id.text_titulo)
    TextView textViewNombre;
    @BindView(R.id.imageView)
    ImageView imageView;


    public CamposAccionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CamposAccionUi camposAccionUi) {
        textViewNombre.setText(camposAccionUi.getNombre());
        String CAMPOTEMATICODETALLE = camposAccionUi.getTipo().name();
        if (CAMPOTEMATICODETALLE.equals("PARENT")) {
            imageView.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(true);
        } else if (CAMPOTEMATICODETALLE.equals("CHILDREN")) {
            imageView.setVisibility(View.VISIBLE);
            checkBox.setChecked(true);
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setChecked(true);
        } else if (CAMPOTEMATICODETALLE.equals("DEFECTO")) {
            imageView.setVisibility(View.VISIBLE);
            checkBox.setChecked(true);
            checkBox.setVisibility(View.VISIBLE);
        }
    }
}
