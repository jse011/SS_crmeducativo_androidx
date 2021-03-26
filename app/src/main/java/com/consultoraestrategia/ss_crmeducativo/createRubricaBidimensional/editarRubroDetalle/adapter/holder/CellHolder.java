package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.listener.InfoRubricaBidimensionalListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CellHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txt_valor)
    TextView txtValor;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.imgIcono)
    CircleImageView imgIcono;
    @BindView(R.id.txt_eval_descripcion)
    TextInputEditText txtEvalDescripcion;
    CriterioEvaluacionUi criterioEvaluacionUi;
    private InfoRubricaBidimensionalListener listener;




    public CellHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        txtEvalDescripcion.setOnClickListener(this);
            }

    public void bind(final InfoRubricaBidimensionalListener listener, final CriterioEvaluacionUi criterioEvaluacionUi) {
        this.criterioEvaluacionUi = criterioEvaluacionUi;
        this.listener = listener;
        ValorTipoNotaUi valorTipoNotaUi = criterioEvaluacionUi.getValorTipoNotaUi();
        switch (valorTipoNotaUi.getTipoNotaUi().getTipo()){
            default:
                imgIcono.setVisibility(View.GONE);
                break;
            case SELECTOR_ICONOS:
                txtValor.setVisibility(View.GONE);
                imgIcono.setVisibility(View.VISIBLE);
                txtDescripcion.setText(valorTipoNotaUi.getAlias());
                Glide.with(itemView.getContext())
                        .load(valorTipoNotaUi.getIcono())
                        .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle))
                        .into(imgIcono);
                imgIcono.setVisibility(View.VISIBLE);
                break;
            case SELECTOR_VALORES:
                imgIcono.setVisibility(View.GONE);
                txtValor.setVisibility(View.VISIBLE);
                txtValor.setText(valorTipoNotaUi.getTitle());
                txtDescripcion.setText(valorTipoNotaUi.getAlias());
                break;
        }

        if (!TextUtils.isEmpty(txtEvalDescripcion.getText()) || criterioEvaluacionUi.getTitulo()!=null) {
            txtEvalDescripcion.setText(criterioEvaluacionUi.getTitulo());
        } else {
            txtEvalDescripcion.setText("");
        }




    }


    @Override
    public void onClick(View v) {
        listener.onClickCriterioEvaluacion(criterioEvaluacionUi);
    }
}

