package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CampotematicoListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderCampotematico extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.chbx)
    CheckBox checkBox;
    @BindView(R.id.text_titulo)
    TextView textTitulo;
    private CampotematicoUi campotematicoUi;
    private CampotematicoListener listener;
    private IndicadorUi indicadorUi;
    private CompetenciaUi competenciaUi;
    private String TAG= ViewHolderCampotematico.class.getSimpleName();
    public ViewHolderCampotematico(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(CompetenciaUi competenciaUi, final IndicadorUi indicadorUi, final CampotematicoUi campotematicoUi, final CampotematicoListener listener){

        this.indicadorUi = indicadorUi;
        this.campotematicoUi = campotematicoUi;
        this.competenciaUi = competenciaUi;
        this.listener = listener;
        Log.d(TAG, " bind " + campotematicoUi.isChecked() + " / " + campotematicoUi.getDescripcion()+ "indicadores " +  indicadorUi.getCapacidadUi().getId() + " nombw " + indicadorUi.getTitulo());

       setView();

    }

    private void setView() {
        boolean isChecked = campotematicoUi.isChecked();
        String title = campotematicoUi.getDescripcion();
        //in some cases, it will prevent unwanted situations
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(isChecked);
        textTitulo.setText(Utils.capitalize(title));
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

