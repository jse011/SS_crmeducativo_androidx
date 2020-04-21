package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.listener.InfoRubricaBidimensionalListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CellHolder  extends RecyclerView.ViewHolder  {

    @BindView(R.id.txt_valor)
    TextView txtValor;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.imgIcono)
    CircleImageView imgIcono;
    @BindView(R.id.txt_eval_descripcion)
    EditText txtEvalDescripcion;
    CriterioEvaluacionUi criterioEvaluacionUi;
    List<CriterioEvaluacionUi> criterioEvaluacionUis;
    InfoRubricaBidimensionalListener listener;
    List<Cell> cellList;




    public CellHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            }

    public void bind(final InfoRubricaBidimensionalListener listener, final CriterioEvaluacionUi criterioEvaluacionUi, final List<CriterioEvaluacionUi> criterioEvaluacionUis, final int position, final List<Cell> cellList) {
        this.criterioEvaluacionUis = criterioEvaluacionUis;
        this.listener = listener;
        this.cellList = cellList;
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

        txtEvalDescripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                criterioEvaluacionUis.get(position).setTitulo(txtEvalDescripcion.getText().toString());
                listener.sendList(criterioEvaluacionUis);
            }
        });


    }

    }

