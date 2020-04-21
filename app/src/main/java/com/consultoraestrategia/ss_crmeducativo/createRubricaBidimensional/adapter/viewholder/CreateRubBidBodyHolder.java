package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.NivelPorRubro;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.RubroEvaluacionUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class CreateRubBidBodyHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txtContent)
    TextView txtContent;

    public CreateRubBidBodyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(NivelPorRubro nivelPorRubro) {
        txtContent.setText(nivelPorRubro.getTitulo());
    }

    public void bind(RubroEvaluacionUi rubroEvaluacion) {
        txtContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
        txtContent.setText(Html.fromHtml(rubroEvaluacion.getTitle()));
    }
}
