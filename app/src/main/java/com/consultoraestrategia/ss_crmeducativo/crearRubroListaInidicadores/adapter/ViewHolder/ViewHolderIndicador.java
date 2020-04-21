package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.ViewHolder;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.CampotematicoAdapter;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CampotematicoListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import org.zakariya.stickyheaders.SectioningAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 20/08/2017.
 */

public class ViewHolderIndicador extends SectioningAdapter.ItemViewHolder implements CompoundButton.OnCheckedChangeListener {
 String TAG = ViewHolderIndicador.class.getSimpleName();
    @BindView(R.id.rec_campotematico)
    RecyclerView recCampotematico;
    @BindView(R.id.txt_inidicador)
    TextView txtInidicador;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;
    @BindView(R.id.chbx)
    CheckBox checkBox;
    CampotematicoAdapter adapter;
    CampotematicoListener listener;
    private CompetenciaUi competencia;
    private IndicadorUi indicadorUi;

    public ViewHolderIndicador(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public CampotematicoAdapter getAdapter() {
        return adapter;
    }

    public void bind(CompetenciaUi competencia, IndicadorUi indicadorUi, CampotematicoListener campotematicoListener) {
        //txtCount.setText(indicadorUi.getSelector());
        this.listener = campotematicoListener;
        //in some cases, it will prevent unwanted situations
        this.indicadorUi = indicadorUi;
        this.competencia = competencia;
        setView();
        adapter = new CampotematicoAdapter(competencia,indicadorUi, indicadorUi.getCampotematicoUis(),listener);
        recCampotematico.setAdapter(adapter);
        recCampotematico.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        recCampotematico.setHasFixedSize(true);
        //recCampotematico.setLayoutAnimation(null);
    }

    public void notifyChanged() {
        setView();
        adapter.notifyAllChildChanged(recCampotematico);
    }

    private void setView() {
        checkBox.setOnCheckedChangeListener(null);
        txtInidicador.setText(indicadorUi.getTitulo());
        checkBox.setChecked(indicadorUi.isSeleccionado());
        checkBox.setOnCheckedChangeListener(this);

        Drawable drawable = null;
        if(competencia.getTipo()== CompetenciaUi.Tipo.COMPETENCIA_BASE){
            switch (indicadorUi.getTipoIndicadorUi()){
                case SER:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                    break;
                case HACER:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                    break;
                case SABER:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                    break;
                case DEFAULT:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                    break;
            }

        }else if(competencia.getTipo()== CompetenciaUi.Tipo.COMPETENCIA_ENFQ){
            drawable = ContextCompat.getDrawable(itemView.getContext(),  R.drawable.ic_enfoque_1);
        }else if(competencia.getTipo()== CompetenciaUi.Tipo.COMPETENCIA_TRANS){
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_transversal);
        }else {
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
        }

        if (drawable != null)imgIndicador.setImageDrawable(drawable);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listener.onClickIndicador(competencia,indicadorUi);
    }
}
