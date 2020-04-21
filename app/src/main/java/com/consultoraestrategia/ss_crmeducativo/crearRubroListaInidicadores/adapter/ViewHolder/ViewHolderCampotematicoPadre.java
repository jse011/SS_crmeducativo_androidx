package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderCampotematicoPadre extends RecyclerView.ViewHolder {
    @BindView(R.id.text_titulo)
    TextView textTitulo;
    private String TAG= ViewHolderCampotematicoPadre.class.getSimpleName();
    public ViewHolderCampotematicoPadre(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(CampotematicoUi campotematicoUi){
        String title = campotematicoUi.getDescripcion();
        textTitulo.setText(Utils.capitalize(title));
    }

}
