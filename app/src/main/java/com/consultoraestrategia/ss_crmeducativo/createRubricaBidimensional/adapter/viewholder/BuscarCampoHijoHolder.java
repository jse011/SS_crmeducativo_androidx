package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista.CamposAccionChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuscarCampoHijoHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.chbx)
    CheckBox checkBox;
    @BindView(R.id.text_titulo)
    TextView textTitulo;
    private CampoAccionUi campoAccionUi;

    public BuscarCampoHijoHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    private CamposAccionChooserCallback listener;

    public void bind(CampoAccionUi campoAccionUi, CamposAccionChooserCallback listener) {
        this.campoAccionUi = campoAccionUi;
        this.listener = listener;
        boolean isChecked = campoAccionUi.isChecked();
        String title = campoAccionUi.getTitle();
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(isChecked);
        checkBox.setOnCheckedChangeListener(this);
        textTitulo.setText(title);

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        campoAccionUi.setChecked(checkBox.isChecked());
        listener.onCheckedCamposAccion(campoAccionUi);
    }


    public void notifyChanged() {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(campoAccionUi.isChecked());
        checkBox.setOnCheckedChangeListener(this);
    }
}
