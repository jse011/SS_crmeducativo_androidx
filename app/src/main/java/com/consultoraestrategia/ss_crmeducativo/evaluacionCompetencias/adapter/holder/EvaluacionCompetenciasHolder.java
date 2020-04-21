package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.CompetenciaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.EvaluacionCapacidadAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciasHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = EvaluacionCompetenciasHolder.class.getSimpleName();
    @BindView(R.id.txt_cant_rubro)
    TextView textViewContador;
    @BindView(R.id.txtTitulo)
    TextView textViewTitulo;
    @BindView(R.id.recycler_two)
    RecyclerView recicladorCapacidades;
    @BindView(R.id.card_view)
    CardView cardViewItem;
    @BindView(R.id.view3)
    View viewItem;
    EvaluacionCapacidadAdapter evaluacionCapacidadAdapter;
    CompetenciaUi competenciaUi;
    EvaluacionCompetenciaListener listener;
    List<Object> objectList;



    public EvaluacionCompetenciasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewItem.setOnClickListener(this);
    }

    public void bind(final CompetenciaUi competenciaUi, final EvaluacionCompetenciaListener listener, final List<Object> objects, final Context context) {
        this.competenciaUi = competenciaUi;
        this.objectList = objects;
        if (listener == null) return;
        this.listener=listener;
        if (competenciaUi.isStado()) {
            recicladorCapacidades.setVisibility(View.VISIBLE);
        } else {
            recicladorCapacidades.setVisibility(View.GONE);
        }

        Drawable mDrawable = context.getResources().getDrawable(R.drawable.ic_circle_unidades);
        mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(competenciaUi.getParametroDisenioUi().getColor1()), PorterDuff.Mode.SRC_IN));
        viewItem.setBackgroundColor(Color.parseColor(competenciaUi.getParametroDisenioUi().getColor1()));
        textViewTitulo.setTextColor(Color.parseColor(competenciaUi.getParametroDisenioUi().getColor1()));
        textViewContador.setText(competenciaUi.getContador());
        textViewContador.setBackground(mDrawable);
        textViewTitulo.setText(competenciaUi.getNombre().toUpperCase());
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(recicladorCapacidades.getContext(), OrientationHelper.VERTICAL, false);
        CompetenciaColumnCountProvider columnCountProvider = new CompetenciaColumnCountProvider(recicladorCapacidades.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recicladorCapacidades.setLayoutManager(autoColumnGridLayoutManager);
        recicladorCapacidades.setHasFixedSize(false);
        recicladorCapacidades.setNestedScrollingEnabled(false);
        evaluacionCapacidadAdapter = new EvaluacionCapacidadAdapter(new ArrayList<CapacidadUi>(), listener, competenciaUi, context);
        recicladorCapacidades.setAdapter(evaluacionCapacidadAdapter);
        Log.d(TAG, "size "+  competenciaUi.getCapacidadUiList().size());
        evaluacionCapacidadAdapter.setListCapacidades(competenciaUi.getCapacidadUiList());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view:
                onBtnClickCompetencia(competenciaUi);
                break;
            default:
                break;
        }
    }

    private void onBtnClickCompetencia(CompetenciaUi competenciaUi) {
        if (!competenciaUi.isStado()) {
            recicladorCapacidades.setVisibility(View.VISIBLE);

        } else {
            recicladorCapacidades.setVisibility(View.GONE);
        }
      listener.clickToogle(competenciaUi);
    }
}
