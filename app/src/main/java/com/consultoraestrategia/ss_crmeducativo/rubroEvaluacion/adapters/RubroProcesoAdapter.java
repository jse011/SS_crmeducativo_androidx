package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters;


import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder.RubroProcesoFormulaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder.RubroProcesoNormalHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder.RubroProcesoRubicaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;

import java.util.List;


/**
 * Created by kike on 22/10/2017.
 */

public class RubroProcesoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int RUBROPROCESO_RUBRICA = 473;
    public static final int RUBROPROCESO_FORMULA = 1;
    public static final int RUBROPROCESO_NORMAL = 470;
    public static final int RUBRO_SILABO = 1, RUBRO_SESIONES = 2;
    

    private static String TAG = RubroProcesoAdapter.class.getSimpleName();
    private List<RubroProcesoUi> rubroProcesoUis;
    private RubroEvaluacionProcesoListener listener;
    private RecyclerView recyclerView;
    private CapacidadUi capacidadUi;
    private int tipoList;


    public RubroProcesoAdapter(List<RubroProcesoUi> rubroProcesoUis, RubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi, int tipoList) {
        this.rubroProcesoUis = rubroProcesoUis;
        this.listener = listener;
        this.capacidadUi = capacidadUi;
        this.tipoList = tipoList;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutRubrica;
        int layouRubros;
        int layoutFormula;
        switch (tipoList){
            case RUBRO_SILABO:
                layoutRubrica = R.layout.layout_item_rubro_proceso_rubrica_bidimencional;
                layoutFormula = R.layout.layout_item_rubro_proceso_formula;
                layouRubros = R.layout.layout_item_rubro_proceso;
                break;
            default:
                layoutRubrica = R.layout.layout_item_rubro_proceso_rubrica_bidimencional_sesion;
                layoutFormula = R.layout.layout_item_rubro_proceso_formula_sesion;
                layouRubros = R.layout.layout_item_rubro_proceso_sesion;
                break;
        }
        switch (viewType) {
            case RUBROPROCESO_RUBRICA:
                View viewRubricaS = layoutInflater.inflate(layoutRubrica, parent, false);
                viewHolder = new RubroProcesoRubicaHolder(viewRubricaS);
                break;
            case RUBROPROCESO_FORMULA:
                View viewFormula = layoutInflater.inflate(layoutFormula, parent, false);
                viewHolder = new RubroProcesoFormulaHolder(viewFormula);
                break;
            default:
                View viewNormal = layoutInflater.inflate(layouRubros, parent, false);
                viewHolder = new RubroProcesoNormalHolder(viewNormal);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        RubroProcesoUi rubroProcesoUiFormula = rubroProcesoUis.get(position);
        int total = rubroProcesoUis.size();
        switch (holder.getItemViewType()) {
            case RUBROPROCESO_RUBRICA:
                RubroProcesoRubicaHolder rubroProcesoRubricaHolder = (RubroProcesoRubicaHolder) holder;
                rubroProcesoRubricaHolder.bind(rubroProcesoUiFormula, listener, capacidadUi, tipoList, total-position);
                break;
            case RUBROPROCESO_FORMULA:
                RubroProcesoFormulaHolder rubroProcesoFormulaHolder = (RubroProcesoFormulaHolder) holder;
                rubroProcesoFormulaHolder.bind(rubroProcesoUiFormula, listener, capacidadUi, tipoList, total-position);
                break;
            default:
                RubroProcesoNormalHolder rubroProcesoNormalHolder = (RubroProcesoNormalHolder) holder;
                rubroProcesoNormalHolder.bind(rubroProcesoUiFormula, listener, capacidadUi, tipoList, total -position);
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (rubroProcesoUis == null) return 0;
        return rubroProcesoUis.size();
    }


    @Override
    public int getItemViewType(int position) {
        RubroProcesoUi rubroProcesoUi = rubroProcesoUis.get(position);
        if (rubroProcesoUi.getTipoFormulaId() > 0) return RUBROPROCESO_FORMULA;
        switch (rubroProcesoUi.getTipo()) {
            case BIDIMENCIONAL_DETALLE:
                return RUBROPROCESO_RUBRICA;
            default:
                return RUBROPROCESO_NORMAL;

        }

    }


    public void addItem(RubroProcesoUi rubroProcesoUi) {
        rubroProcesoUi.setPosicion(getItemCount() + 1);
        int position = 0;
        rubroProcesoUis.add(position, rubroProcesoUi);
        notifyDataSetChanged();
    }

    public void updateItem(RubroProcesoUi rubroProcesoUi) {
        int position = this.rubroProcesoUis.indexOf(rubroProcesoUi);
        if (position != -1) {
            this.rubroProcesoUis.set(position, rubroProcesoUi);
            notifyItemChanged(position);
            actualizarPorItem(rubroProcesoUis.size());
        }
    }

    public void deleteItem(RubroProcesoUi rubroProcesoUi) {
        int position = this.rubroProcesoUis.indexOf(rubroProcesoUi);
        if (position != -1) {
            this.rubroProcesoUis.remove(rubroProcesoUi);
            notifyItemRemoved(position);
            actualizarPorItem(rubroProcesoUis.size());
        }
    }

    public void actualizarPorItem(int tamanio){
        for (int i = 0; i < tamanio; i++) {
            notifyItemChanged(i);
        }
    }

    public void setItems(List<RubroProcesoUi> items) {
        this.rubroProcesoUis.clear();
        this.rubroProcesoUis.addAll(items);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void validarListCheck() {
        boolean validarCheck = true;
        for (RubroProcesoUi rubroProcesoUi1 : rubroProcesoUis) {
            if (rubroProcesoUi1.isCheck()) {
                validarCheck = true;
            }
            rubroProcesoUi1.setCheck(validarCheck);
        }
    }
}
