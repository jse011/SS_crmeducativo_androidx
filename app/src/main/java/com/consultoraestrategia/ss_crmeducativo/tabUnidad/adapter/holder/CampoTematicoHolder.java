package com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.CampoTematicoAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CampoTematicoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textCampoTematico)
    TextView txtCampoTematico;
    @BindView(R.id.rvCampoHijo)
    RecyclerView rvCampoHijo;
    CampoTematicoAdapter adapter;

    public CampoTematicoHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(CampoAccionUi campoAccionUi, int positionHijo, int positionPadre){
        if (campoAccionUi.getTipo()== CampoAccionUi.Tipo.PADRE){
            txtCampoTematico.setText(positionHijo+". "+campoAccionUi.getCampoAccionNombre());
            rvCampoHijo.setVisibility(View.VISIBLE);
            txtCampoTematico.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
            adapter = new CampoTematicoAdapter(campoAccionUi.getCampoAccionUis(), positionHijo);
            rvCampoHijo.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rvCampoHijo.setAdapter(adapter);
        }else {
            rvCampoHijo.setVisibility(View.GONE);
            txtCampoTematico.setText(positionPadre+". "+positionHijo+". "+campoAccionUi.getCampoAccionNombre());
            txtCampoTematico.setTextColor(itemView.getResources().getColor(R.color.md_grey_600));
        }
    }
}
