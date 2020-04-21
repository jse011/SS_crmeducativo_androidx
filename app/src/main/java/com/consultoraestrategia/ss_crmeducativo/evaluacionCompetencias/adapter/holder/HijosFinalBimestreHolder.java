package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.RubroRNRFormulaAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.HijosFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.PadreFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.RubroEvalRNRFormulaUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 12/04/2018.
 */

public class HijosFinalBimestreHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
    private static String TAG = HijosFinalBimestreHolder.class.getSimpleName();

    @BindView(R.id.txtTitulo)
    TextView textViewTitulo;
    @BindView(R.id.txtFecha)
    TextView textViewFecha;
    @BindView(R.id.media)
    TextView textViewMedia;
//    @BindView(R.id.textView106)
//    TextView number;
    @BindView(R.id.reciclador_formula)
    RecyclerView recyclerView;
    @BindView(R.id.imageViewTipoFinal)
    ImageView imageViewTipoFinal;
    @BindView(R.id.card_rubro_proceso)
    CardView card_rubro_proceso;

    RubroRNRFormulaAdapter rubroRNRFormulaAdapter;
    EvaluacionCompetenciaListener listener;
    HijosFinalBimestreUi hijosFinalBimestre;
    PadreFinalBimestreUi padreFinalBimestreUi;
    public HijosFinalBimestreHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        card_rubro_proceso.setOnClickListener(this);
    }

    public void bind(HijosFinalBimestreUi hijosFinalBimestre , EvaluacionCompetenciaListener listener, final PadreFinalBimestreUi padreFinalBimestreUi, final Context context) {
        this.hijosFinalBimestre=hijosFinalBimestre;
        this.padreFinalBimestreUi = padreFinalBimestreUi;
        if(listener!=null)this.listener=listener;
        else return;
        textViewFecha.setText(hijosFinalBimestre.getFecha());
        textViewMedia.setText(hijosFinalBimestre.getMedia());
//        number.setBackgroundColor(Color.parseColor(padreFinalBimestreUi.getColor()));
//        number.setText(String.valueOf(hijosFinalBimestre.getContador()));
        //textViewTitulo.setText(hijosFinalBimestre.getTitulo());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        rubroRNRFormulaAdapter = new RubroRNRFormulaAdapter(new ArrayList<RubroEvalRNRFormulaUi>(), padreFinalBimestreUi.getColor(), context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rubroRNRFormulaAdapter);

       List<RubroEvalRNRFormulaUi> rubroEvalRNRFormulaUis= hijosFinalBimestre.getRubroEvalRNRFormulas();
       Log.d(TAG, "SIZE "+ rubroEvalRNRFormulaUis.size());
        rubroRNRFormulaAdapter.setRnformulas(rubroEvalRNRFormulaUis);
        mostrarTipoFinal(hijosFinalBimestre);
    }

    private void mostrarTipoFinal(HijosFinalBimestreUi hijosFinalBimestre) {
        switch (hijosFinalBimestre.getTipoFinalBimestre()) {
            case TIPO_FINAL_BASE:

                imageViewTipoFinal.setVisibility(View.GONE);
                //number.setVisibility(View.VISIBLE);
                textViewTitulo.setText(hijosFinalBimestre.getContador() + ": " + hijosFinalBimestre.getTitulo());
                break;
            case TIPO_FINAL_BIMESTRE:

                imageViewTipoFinal.setVisibility(View.VISIBLE);
                //number.setText("*");
//                textViewTitulo.setTypeface(Typeface.DEFAULT_BOLD);
//                textViewTitulo.setTextColor(Color.parseColor(padreFinalBimestreUi.getColor()));
                textViewTitulo.setText(hijosFinalBimestre.getTitulo());
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_rubro_proceso:
            Log.d(TAG, "onClickTipoComportamiento");
               listener.onObjectClicked(hijosFinalBimestre);
                break;
             default:
                 break;
        }
    }
}
