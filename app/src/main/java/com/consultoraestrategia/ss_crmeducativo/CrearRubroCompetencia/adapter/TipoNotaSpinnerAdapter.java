package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class TipoNotaSpinnerAdapter extends BaseAdapter {
    private List<TipoNotaUi> tipoNotas;
    private String TAG = "CrearRubroPresenterImpl";

    public TipoNotaSpinnerAdapter(List<TipoNotaUi> tipoNotas) {
        this.tipoNotas = tipoNotas;
    }
    @Override
    public int getCount() {
        return tipoNotas.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoNotas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tiponota_spinner_items, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_tipo_nota);
        txtTitle.setText(tipoNotas.get(i).getNombre());
        return view;
    }

    public void setPersonas(List<TipoNotaUi> tipoNotas){
        this.tipoNotas.clear();
        this.tipoNotas.addAll(tipoNotas);
        notifyDataSetChanged();
    }


}
