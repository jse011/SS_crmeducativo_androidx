package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class TipoEvaluacionSpinnerAdapter extends BaseAdapter {
    private List<TipoEvaluacionUi> tipoEvaluacionUis;
    private String TAG = "CrearRubroPresenterImpl";

    public TipoEvaluacionSpinnerAdapter(List<TipoEvaluacionUi> tipoNotas) {
        this.tipoEvaluacionUis = tipoNotas;
    }
    @Override
    public int getCount() {
        return tipoEvaluacionUis.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoEvaluacionUis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((TipoEvaluacionUi) getItem(i)).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tipo_evaluacion_spinner_items, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_tipo_evaluacion);
        txtTitle.setText(tipoEvaluacionUis.get(i).getNombre());
        return view;
    }

    public void setTipoNotas(List<TipoEvaluacionUi> tipoNotas){
        this.tipoEvaluacionUis.clear();
        this.tipoEvaluacionUis.addAll(tipoNotas);
        notifyDataSetChanged();
    }



}
