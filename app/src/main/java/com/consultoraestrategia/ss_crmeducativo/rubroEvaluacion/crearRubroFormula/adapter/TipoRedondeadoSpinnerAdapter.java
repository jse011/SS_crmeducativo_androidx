package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoRedondeadoUi;

import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class TipoRedondeadoSpinnerAdapter extends BaseAdapter {
    List<TipoRedondeadoUi> tipoRedondeadoUiList;

    public TipoRedondeadoSpinnerAdapter(List<TipoRedondeadoUi> tipoRedondeadoUiList) {
        this.tipoRedondeadoUiList = tipoRedondeadoUiList;
    }

    @Override
    public int getCount() {
        return tipoRedondeadoUiList.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoRedondeadoUiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((TipoRedondeadoUi)tipoRedondeadoUiList.get(i)).getIdTipo();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tipo_evaluacion_spinner_items, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_tipo_evaluacion);
        txtTitle.setText(tipoRedondeadoUiList.get(i).getNombre());
        return view;
    }

    public void setTipoRedondeadoUiList(List<TipoRedondeadoUi>tipoRedondeadoUiList){
        this.tipoRedondeadoUiList.clear();
        this.tipoRedondeadoUiList.addAll(tipoRedondeadoUiList);
        notifyDataSetChanged();
    }
}
