package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EditPesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 6/08/2018.
 */

public class PesoRubroHolder extends RecyclerView.ViewHolder implements TextWatcher {
    @BindView(R.id.editText2)
    EditText editText2;

    private RubroProcesoUi rubroEvaluacionProcesoUi;
    private RubroEvaluacionProcesoListener listener;

    public PesoRubroHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(EditPesoUi editPesoUi, RubroEvaluacionProcesoListener listener) {
        this.rubroEvaluacionProcesoUi = editPesoUi.getRubroProcesoUi();
        this.listener = listener;


        if (rubroEvaluacionProcesoUi.isCheck()) {
            editText2.setVisibility(View.VISIBLE);
            if (rubroEvaluacionProcesoUi.getPeso() == 0.0) {
                editText2.setText(String.valueOf((int)rubroEvaluacionProcesoUi.getPeso()));
            } else {
                editText2.setText(String.valueOf((int)rubroEvaluacionProcesoUi.getPeso()));
                editText2.addTextChangedListener(this);
            }
        } else {
            Log.d(PesoRubroHolder.class.getSimpleName(), "falseee");
            editText2.setVisibility(View.GONE);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        listener.onPesoChanged(rubroEvaluacionProcesoUi,charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
