package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
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
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuscarCampoHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.chbx)
    CheckBox checkBox;
    @BindView(R.id.text_titulo)
    TextView textTitulo;
    @BindView(R.id.text_indicador)
    TextView textIndicador;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;

    private CampoAccionUi campoAccionUi;
    private IndicadorUi indicadorUi;

    public BuscarCampoHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    private CamposAccionChooserCallback listener;

    public void bind(CampoAccionUi campoAccionUi, CamposAccionChooserCallback listener) {
        this.campoAccionUi = campoAccionUi;
        this.indicadorUi = this.campoAccionUi.getIndicadorUi();
        this.listener = listener;
        boolean isChecked = campoAccionUi.isChecked();
        String title = campoAccionUi.getTitle();
        textTitulo.setText(title);
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(isChecked);
        checkBox.setOnCheckedChangeListener(this);
        binIndicador();
    }

    private void binIndicador(){
        try {
            Drawable drawable = null;
            imgIndicador.setVisibility(View.VISIBLE);
            textIndicador.setText(indicadorUi.getTitulo());
            CompetenciaUi competenciaUi = indicadorUi.getCompetenciaOwner();
            if(competenciaUi.isBase()){
                switch (indicadorUi.getTipoIndicadorUi()) {
                    case SER:
                        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
                        break;
                    case HACER:
                        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
                        break;
                    case SABER:
                        Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
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
        campoAccionUi.setChecked(checkBox.isChecked());
        listener.onCheckedCamposAccion(campoAccionUi);
    }


    public void notifyChanged() {
        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(campoAccionUi.isChecked());
        checkBox.setOnCheckedChangeListener(this);
    }
}
