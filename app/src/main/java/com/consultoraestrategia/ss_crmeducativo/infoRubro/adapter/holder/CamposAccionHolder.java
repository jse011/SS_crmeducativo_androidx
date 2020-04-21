package com.consultoraestrategia.ss_crmeducativo.infoRubro.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CampoTematicoUi;

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

    public void bind(CampoTematicoUi campoTematicoUi) {
        txtBinieta.setText(campoTematicoUi.getBinieta());
        txtNombre.setText(campoTematicoUi.getTitulo());
        switch (campoTematicoUi.getTipoCampoTematicoE()){
            case DEFAULT:
                txtEspacio.setVisibility(View.GONE);
                break;
            case CHILDREN:
                txtEspacio.setVisibility(View.VISIBLE);
                break;
            case PARENT:
                txtEspacio.setVisibility(View.GONE);
                break;
        }

    }
}
