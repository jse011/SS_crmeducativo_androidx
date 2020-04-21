package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista.CamposAccionChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CampoIndicadorHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.text_indicador)
    TextView textIndicador;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;
    private CamposAccionChooserCallback listener;
    private IndicadorUi indicadorUi;
    @BindView(R.id.chbx)
    CheckBox checkBox;
    private CampoAccionUi campoAccionUi;
    private IndicadorCampoAccionUi indicadorCompetenciaUi;

    public CampoIndicadorHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(IndicadorCampoAccionUi indicadorCompetenciaUi, CamposAccionChooserCallback listener) {
        this.listener = listener;
        this.indicadorUi = indicadorCompetenciaUi.getIndicadorUi();
        this.campoAccionUi = indicadorCompetenciaUi.getCampoAccionUi();
        this.indicadorCompetenciaUi = indicadorCompetenciaUi;
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(indicadorCompetenciaUi.getChecked());
        checkBox.setOnCheckedChangeListener(this);
        try {
            Drawable drawable = null;
            imgIndicador.setVisibility(View.VISIBLE);
            textIndicador.setText(TextUtils.isEmpty(indicadorUi.getTitulo())?"":indicadorUi.getTitulo().trim());
            CompetenciaUi competenciaUi = indicadorUi.getCompetenciaOwner();
            if(competenciaUi.isBase()){
                switch (indicadorUi.getTipoIndicadorUi()) {
                    case SER:
                        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple().error( R.drawable.ic_speedometer)).into(imgIndicador);
                        break;
                    case HACER:
                        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple().error( R.drawable.ic_speedometer)).into(imgIndicador);
                        break;
                    case SABER:
                        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple().error( R.drawable.ic_speedometer)).into(imgIndicador);
                        break;
                    case DEFAULT:
                        drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
                        break;
                    default:
                        drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
                        break;
                }

            }else if(competenciaUi.isEnfoque()){
                drawable = ContextCompat.getDrawable(itemView.getContext(),  R.drawable.ic_enfoque_1);
            }else if(competenciaUi.isTrans()){
                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_transversal);
            }else {
                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
            }

            if (drawable != null)imgIndicador.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        indicadorCompetenciaUi.setChecked(checkBox.isChecked());
        listener.onCheckedIndicador(indicadorCompetenciaUi);
    }

    public void notifyChanged() {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(indicadorCompetenciaUi.getChecked());
        checkBox.setOnCheckedChangeListener(this);
    }
}
