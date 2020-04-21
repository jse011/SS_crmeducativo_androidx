package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoEvaluacionUi;

import java.util.List;

/**
 * Created by kike on 07/12/2017.
 */

public class TipoEvaluacionSpinnerAdapter extends BaseAdapter {
    List<TipoEvaluacionUi> tipoEvaluacionUiList;

    public TipoEvaluacionSpinnerAdapter(List<TipoEvaluacionUi> tipoEvaluacionUiList) {
        this.tipoEvaluacionUiList = tipoEvaluacionUiList;
    }

    @Override
    public int getCount() {
        return tipoEvaluacionUiList.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoEvaluacionUiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((TipoEvaluacionUi) getItem(i)).getIdTipo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tipo_evaluacion_spinner_items, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_tipo_evaluacion);
        txtTitle.setText(tipoEvaluacionUiList.get(i).getNombre());
        return view;
    }

    public void setTipoFormulaList(List<TipoEvaluacionUi> formulaUis) {
        this.tipoEvaluacionUiList.clear();
        this.tipoEvaluacionUiList.addAll(formulaUis);
        notifyDataSetChanged();
    }




}
