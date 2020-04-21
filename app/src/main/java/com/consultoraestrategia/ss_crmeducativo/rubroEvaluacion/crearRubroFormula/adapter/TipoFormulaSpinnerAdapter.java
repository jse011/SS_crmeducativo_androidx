package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoFormulaUi;

import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class TipoFormulaSpinnerAdapter extends BaseAdapter {

    private static String TAG = TipoFormulaSpinnerAdapter.class.getSimpleName();

    List<TipoFormulaUi> formulaUiList;

    public TipoFormulaSpinnerAdapter(List<TipoFormulaUi> formulaUiList) {
        this.formulaUiList = formulaUiList;
    }

    @Override
    public int getCount() {
        return formulaUiList.size();
    }

    @Override
    public Object getItem(int i) {
        return formulaUiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ((TipoFormulaUi) getItem(i)).getIdTipo();
    }

    TextView txtTitle;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.tipo_evaluacion_spinner_items, null);
        txtTitle = (TextView) view.findViewById(R.id.txt_tipo_evaluacion);
        txtTitle.setText(formulaUiList.get(i).getNombre());
        return view;
    }

    public void setTipoFormulaList(List<TipoFormulaUi> formulaUis) {
        this.formulaUiList.clear();
        this.formulaUiList.addAll(formulaUis);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
         this.formulaUiList.remove(position-1);
         notifyDataSetChanged();
        //notifyItemRemoved(position);
    }


}
