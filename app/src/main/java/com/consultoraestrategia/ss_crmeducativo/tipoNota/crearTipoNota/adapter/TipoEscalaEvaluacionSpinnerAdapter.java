package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;


import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class TipoEscalaEvaluacionSpinnerAdapter extends BaseAdapter {
    List<TipoEscalaEvaluacionUi> tipoEscalaEvaluacionUis;

    public TipoEscalaEvaluacionSpinnerAdapter(List<TipoEscalaEvaluacionUi> tipoEscalaEvaluacionUis) {
        this.tipoEscalaEvaluacionUis = tipoEscalaEvaluacionUis;
    }

    @Override
    public int getCount() {
        return tipoEscalaEvaluacionUis.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoEscalaEvaluacionUis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((TipoEscalaEvaluacionUi) getItem(i)).getIdTipo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tipo_evaluacion_spinner_items, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_tipo_evaluacion);
        txtTitle.setText(tipoEscalaEvaluacionUis.get(i).getNombre());
        return view;
    }

    public void seTipoEscalaEvaluacionList(List<TipoEscalaEvaluacionUi> formulaUis){
        this.tipoEscalaEvaluacionUis.clear();
        this.tipoEscalaEvaluacionUis.addAll(formulaUis);
        notifyDataSetChanged();
    }

}
