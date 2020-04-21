package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EditPesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder.PesoRubroHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder.RubroProcesoFormulaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder.RubroProcesoNormalHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder.RubroProcesoRubicaHolder;

import java.util.List;


/**
 * Created by kike on 22/10/2017.
 */

public class RubroProcesoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected static final int RUBROPROCESO_RUBRICA = 473;
    protected static final int RUBROPROCESO_FORMULA = 1;
    protected static final int RUBROPROCESO_NORMAL = 470;
    protected static final int PESO_EDIT_RUBRO_NORMAL = 2;

    protected static String TAG = RubroProcesoAdapter.class.getSimpleName();
    protected List<RubroProcesoUi> rubroProcesoUis;
    protected RubroEvaluacionProcesoListener listener;
    protected RecyclerView recyclerView;
    protected GridLayoutManager gridLayoutManager;
    protected CapacidadUi capacidadUi;


    public RubroProcesoAdapter(List<RubroProcesoUi> rubroProcesoUis, RubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi) {
        this.rubroProcesoUis = rubroProcesoUis;
        this.listener = listener;
        this.capacidadUi = capacidadUi;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case RUBROPROCESO_RUBRICA:
                View viewRubrica = layoutInflater.inflate(R.layout.layout_item_rubro_bidime_select, parent, false);
                viewHolder = new RubroProcesoRubicaHolder(viewRubrica);
                break;
            case RUBROPROCESO_FORMULA:
                View viewFormula = layoutInflater.inflate(R.layout.layout_item_rubro_formula_select, parent, false);
                viewHolder = new RubroProcesoFormulaHolder(viewFormula);
                break;
            case PESO_EDIT_RUBRO_NORMAL:
                View viewpeso = layoutInflater.inflate(R.layout.layout_item_rubro_formula_peso, parent, false);
                viewHolder = new PesoRubroHolder(viewpeso);
                break;
            default:
                View viewNormal = layoutInflater.inflate(R.layout.layout_item_rubro_proceso_select, parent, false);
                viewHolder = new RubroProcesoNormalHolder(viewNormal);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        RubroProcesoUi rubroProcesoUiFormula = rubroProcesoUis.get(position);
        switch (holder.getItemViewType()) {
            case RUBROPROCESO_RUBRICA:
                RubroProcesoRubicaHolder rubroProcesoRubricaHolder = (RubroProcesoRubicaHolder) holder;
                rubroProcesoRubricaHolder.bind(rubroProcesoUiFormula, listener, capacidadUi);
                break;
            case RUBROPROCESO_FORMULA:
                RubroProcesoFormulaHolder rubroProcesoFormulaHolder = (RubroProcesoFormulaHolder) holder;
                rubroProcesoFormulaHolder.bind(rubroProcesoUiFormula, listener, capacidadUi);
                break;
            case PESO_EDIT_RUBRO_NORMAL:
                PesoRubroHolder pesoRubroHolder = (PesoRubroHolder) holder;
                EditPesoUi editPesoUi = (EditPesoUi)rubroProcesoUiFormula;
                pesoRubroHolder.bind(editPesoUi,listener);
                break;
            default:
                RubroProcesoNormalHolder rubroProcesoNormalHolder = (RubroProcesoNormalHolder) holder;
                rubroProcesoNormalHolder.bind(rubroProcesoUiFormula, listener, capacidadUi);
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
        if(rubroProcesoUi instanceof EditPesoUi)return PESO_EDIT_RUBRO_NORMAL;
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
        notifyItemInserted(position);
    }

    public void updateItem(RubroProcesoUi rubroProcesoUi) {
        int position = this.rubroProcesoUis.indexOf(rubroProcesoUi);
        if (position != -1) {
            this.rubroProcesoUis.set(position, rubroProcesoUi);
            notifyItemChanged(position);
        }
    }

    public void deleteItem(RubroProcesoUi rubroProcesoUi) {
        int position = this.rubroProcesoUis.indexOf(rubroProcesoUi);
        if (position != -1) {
            this.rubroProcesoUis.remove(rubroProcesoUi);
            notifyItemRemoved(position);
        }

    }

    public void setItems(List<RubroProcesoUi> items) {
        this.rubroProcesoUis.clear();
        this.rubroProcesoUis.addAll(items);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView, GridLayoutManager gridLayoutManager) {
        this.recyclerView = recyclerView;
        this.gridLayoutManager = gridLayoutManager;
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
