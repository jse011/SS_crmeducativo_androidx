package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.listener.InfoCriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CriterioEvaluacionViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_valor)
    TextView txtValor;
    @BindView(R.id.txt_eval_descripcion)
    EditText txtEvalDescripcion;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.imgIcono)
    CircleImageView imgIcono;

    private List<ValorTipoNotaUi> valoTipoNotaUis;

    public CriterioEvaluacionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final InfoCriterioEvalListener listener, final ValorTipoNotaUi valorTipoNotaUi, final List<ValorTipoNotaUi> valorTipoNotaUis, final int position) {
        this.valoTipoNotaUis = valorTipoNotaUis;
        CriterioEvalUi criterioEvalUi = valorTipoNotaUi.getCriterioEvalUi();
        if (!TextUtils.isEmpty(criterioEvalUi.getDescripcion())) txtEvalDescripcion.setText(criterioEvalUi.getDescripcion());
        else txtEvalDescripcion.setText("");


        txtEvalDescripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                valorTipoNotaUis.get(position).getCriterioEvalUi().setDescripcion(txtEvalDescripcion.getText().toString());
                listener.saveListCriteriosEval(valorTipoNotaUis);
            }
        });

        switch (valorTipoNotaUi.getTipoNota()) {
            default:
                imgIcono.setVisibility(View.GONE);
                break;
            case IMAGEN:
                txtValor.setVisibility(View.GONE);
                imgIcono.setVisibility(View.VISIBLE);
                txtDescripcion.setText(valorTipoNotaUi.getDescripcion());
                Glide.with(itemView.getContext())
                        .load(valorTipoNotaUi.getIcono())
                        .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                        .into(imgIcono);
                break;
            case TEXTO:
                imgIcono.setVisibility(View.GONE);
                txtValor.setVisibility(View.VISIBLE);
                txtValor.setText(valorTipoNotaUi.getTitulo());
                txtDescripcion.setText(valorTipoNotaUi.getDescripcion());
                break;
        }

    }


}
