package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;

import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class TipoNotaSpinnerAdapter extends BaseAdapter {
    private List<TipoNotaUi> tipoNotaUiList;

    public TipoNotaSpinnerAdapter(List<TipoNotaUi> tipoNotaUiList) {
        this.tipoNotaUiList = tipoNotaUiList;
    }

    @Override
    public int getCount() {
        return tipoNotaUiList.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoNotaUiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((TipoNotaUi) getItem(i)).getIdTipo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tipo_evaluacion_spinner_items, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_tipo_evaluacion);
        txtTitle.setText(tipoNotaUiList.get(i).getNombre());
        return view;
    }
    public void setTipoNotaUiList(List<TipoNotaUi> tipoNotaUiList){
        this.tipoNotaUiList.clear();
        this.tipoNotaUiList.addAll(tipoNotaUiList);
        notifyDataSetChanged();
    }
}
