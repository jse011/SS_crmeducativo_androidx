package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/05/2018.
 */

public class RubroProcesoRubicaHolder extends ViewHolder  {

    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.txtFecha)
    TextView txtFecha;
    @BindView(R.id.media)
    TextView txtMedia;
    @BindView(R.id.tipo)
    TextView textViewTipoEvaluacion;
    @BindView(R.id.forma)
    TextView textViewTipoFormaEvaluacion;
    @BindView(R.id.origen)
    TextView textViewTipoCursoSilabo;
    @BindView(R.id.contItemView)
    ConstraintLayout constraintLayoutRubros;
    @BindView(R.id.idheadConstraint)
    ConstraintLayout constraintLayoutCabecera;
    @BindView(R.id.ImageCheck)
    ImageView imageCheked;
    @BindView(R.id.imageRubrica)
    ImageView imageRubrica;
    @BindView(R.id.card_rubro_proceso)
    CardView cardRubroProceso;
    @BindView(R.id.txt_indicador)
    TextView txtIndicador;
    @BindView(R.id.vacio)
    TextView txtVacio;
    @BindView(R.id.rc_campotematico)
    RecyclerView rcCampotematico;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    private Animation slideDown;
    private Animation slideUp;

    public RubroProcesoRubicaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(IndicadorUi indicadorUi) {

        String titulo = "<b>" + indicadorUi.getPosicion() + ":</b> " + indicadorUi.getTituloRubro();
        txtTitulo.setText(Html.fromHtml(titulo));
        txtFecha.setText(indicadorUi.getFecha());
        txtMedia.setText(indicadorUi.getMedia() + "(" + indicadorUi.getDesviacionE() + ")");
        String tituloRubrica = "<b>Rúbrica:</b> "+indicadorUi.getTituloRubrica();
        textViewTipoCursoSilabo.setText(indicadorUi.getOrigen());
        textViewTipoFormaEvaluacion.setText(indicadorUi.getFormEvaluacion());
        textViewTipoEvaluacion.setText(indicadorUi.getTipoEvaluacion());

        List<CampoAccionUi> camposAccionUiList = indicadorUi.getCampoAccionList();

        imageView7.setVisibility(View.VISIBLE);
        imageView8.setVisibility(View.VISIBLE);
        txtIndicador.setText(indicadorUi.getTitulo());
        CompetenciaUi competenciaUi = indicadorUi.getCompetenciaOwner();
        if(competenciaUi.isBase()){
            switch (indicadorUi.getTipoIndicadorUi()){
                case SER:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple()).into(imageView7);
                    break;
                case HACER:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple()).into(imageView7);
                    break;
                case SABER:
                    Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                            .apply(Utils.getGlideRequestOptionsSimple()).into(imageView7);
                    break;
                case DEFAULT:
                    imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ic_speedometer));
                    break;
            }
        }else if(competenciaUi.isEnfoque()){
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ic_enfoque_1));
        }else if(competenciaUi.isTrans()){
            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(), R.drawable.ic_transversal));
        }


        if(camposAccionUiList==null || camposAccionUiList.size()>0){
            txtVacio.setVisibility(View.GONE);
            CamposAccionAdapter camposAccionAdapter = new CamposAccionAdapter(camposAccionUiList);
            rcCampotematico.setLayoutManager(new LinearLayoutManager(rcCampotematico.getContext()));
            rcCampotematico.setAdapter(camposAccionAdapter);
        } else{
            txtVacio.setVisibility(View.VISIBLE);
        }


    }
}
