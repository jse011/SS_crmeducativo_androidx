package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 08/05/2018.
 */

public class CamposAccionHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_binieta)
    TextView txtBinieta;
    @BindView(R.id.txt_nombre)
    TextView txtNombre;


    public CamposAccionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CamposAccionUi camposAccionUi) {
        txtBinieta.setText("- ");
        txtNombre.setText(camposAccionUi.getNombre());
    }
}
