package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.CompetenciaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.HijosFinalBimestreAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.PadreFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 12/04/2018.
 */

public class PadreFinalBimestreHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_cant_rubro)
    TextView textViewContador;
    @BindView(R.id.txtTitulo)
    TextView textViewTitulo;
    @BindView(R.id.recycler_two)
    RecyclerView reciclador;
    HijosFinalBimestreAdapter adapter;

    public PadreFinalBimestreHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(PadreFinalBimestreUi padreFinalBimestre , final EvaluacionCompetenciaListener listener, final Context context) {
        if(listener==null)return;
        textViewContador.setText(padreFinalBimestre.getContador());
        Drawable mDrawable = context.getResources().getDrawable(R.drawable.ic_circle_unidades);
        try {
            mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(padreFinalBimestre.getColor()), PorterDuff.Mode.SRC_IN));
        }catch (Exception e){
            e.printStackTrace();
        }
        textViewContador.setBackground(mDrawable);
        textViewTitulo.setText(padreFinalBimestre.getNombre());
        Log.d(getClass().getSimpleName(), "Color; "+ padreFinalBimestre.getColor());
        try {
            textViewTitulo.setTextColor(Color.parseColor(padreFinalBimestre.getColor()));
        }catch (Exception e){
            e.printStackTrace();
        }
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(itemView.getContext(), OrientationHelper.VERTICAL, false);
        CompetenciaColumnCountProvider columnCountProvider = new CompetenciaColumnCountProvider(itemView.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        reciclador.setLayoutManager(autoColumnGridLayoutManager);
        reciclador.setHasFixedSize(true);
        Log.d(PadreFinalBimestreHolder.class.getSimpleName(), "  SIZE "+ padreFinalBimestre.getHijosBimestreList().size());
        adapter =new HijosFinalBimestreAdapter(padreFinalBimestre.getHijosBimestreList(),padreFinalBimestre, listener, context);
        reciclador.setAdapter(adapter);

    }
}
