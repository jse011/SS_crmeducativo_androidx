package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CampotematicoListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderCampotematicoDetalle extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.chbx)
    CheckBox checkBox;
    @BindView(R.id.text_titulo)
    TextView textTitulo;
    CampotematicoUi campotematicoUi;
    String TAG= ViewHolderCampotematicoDetalle.class.getSimpleName();
    private CampotematicoListener listener;
    private CompetenciaUi competenciaUi;
    private IndicadorUi indicadorUi;

    public ViewHolderCampotematicoDetalle(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(final CompetenciaUi competenciaUi, final IndicadorUi indicadorUi, final CampotematicoUi campotematicoUi, final CampotematicoListener listener){
        this.campotematicoUi= campotematicoUi;
        this.listener = listener;
        this.competenciaUi = competenciaUi;
        this.indicadorUi = indicadorUi;

       setView();

    }

    private void setView() {
        boolean isChecked = campotematicoUi.isChecked();
        String title = campotematicoUi.getDescripcion();
        //in some cases, it will prevent unwanted situations
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(isChecked);
        textTitulo.setText(title);
        checkBox.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listener.onCampotematicoSelect(competenciaUi,indicadorUi,campotematicoUi);
    }

    public void notifyChanged() {
        setView();
    }
}

