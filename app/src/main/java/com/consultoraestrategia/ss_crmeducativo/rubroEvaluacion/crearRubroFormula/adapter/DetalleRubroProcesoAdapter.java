package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.helper.ItemTouchHelperAdapter;
import com.consultoraestrategia.ss_crmeducativo.helper.SimpleItemTouchHelperCallback;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EditPesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.RubroProcesoAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder.PesoRubroHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder.RubroProcesoFormulaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder.RubroProcesoNormalHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder.RubroProcesoRubicaHolder;

import java.util.Collections;
import java.util.List;

public class DetalleRubroProcesoAdapter extends RubroProcesoAdapter implements ItemTouchHelperAdapter {

    public DetalleRubroProcesoAdapter(List<RubroProcesoUi> rubroProcesoUis, DetalleRubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi) {
        super(rubroProcesoUis, listener, capacidadUi);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case RUBROPROCESO_RUBRICA:
                View viewRubrica = layoutInflater.inflate(R.layout.layout_item_rubro_bidime_select_formula, parent, false);
                viewHolder = new RubroProcesoRubicaHolder(viewRubrica);
                break;
            case RUBROPROCESO_FORMULA:
                View viewFormula = layoutInflater.inflate(R.layout.layout_item_rubro_formula_select_formula, parent, false);
                viewHolder = new RubroProcesoFormulaHolder(viewFormula);
                break;
            case PESO_EDIT_RUBRO_NORMAL:
                View viewpeso = layoutInflater.inflate(R.layout.layout_item_rubro_formula_peso_formula, parent, false);
                viewHolder = new PesoRubroHolder(viewpeso);
                break;
            default:
                View viewNormal = layoutInflater.inflate(R.layout.layout_item_rubro_proceso_select_formula, parent, false);
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
                rubroProcesoRubricaHolder.bind(rubroProcesoUiFormula, ((DetalleRubroEvaluacionProcesoListener) listener), capacidadUi);
                break;
            case RUBROPROCESO_FORMULA:
                RubroProcesoFormulaHolder rubroProcesoFormulaHolder = (RubroProcesoFormulaHolder) holder;
                rubroProcesoFormulaHolder.bind(rubroProcesoUiFormula, ((DetalleRubroEvaluacionProcesoListener) listener), capacidadUi);
                break;
            case PESO_EDIT_RUBRO_NORMAL:
                PesoRubroHolder pesoRubroHolder = (PesoRubroHolder) holder;
                EditPesoUi editPesoUi = (EditPesoUi) rubroProcesoUiFormula;
                pesoRubroHolder.bind(editPesoUi, listener);
                break;
            default:
                RubroProcesoNormalHolder rubroProcesoNormalHolder = (RubroProcesoNormalHolder) holder;
                rubroProcesoNormalHolder.bind(rubroProcesoUiFormula, ((DetalleRubroEvaluacionProcesoListener) listener), capacidadUi);
                break;
        }

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(rubroProcesoUis, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(rubroProcesoUis, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        if (listener != null) {
            ((DetalleRubroEvaluacionProcesoListener) listener).onRubroEvalButtonDeleteClick(rubroProcesoUis.get(position));
        }
    }

    @Override
    public void setRecyclerView(RecyclerView recyclerView, GridLayoutManager gridLayoutManager) {
        super.setRecyclerView(recyclerView, gridLayoutManager);
        setupItemTouchHelper();
    }

    private void setupItemTouchHelper() {
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Log.d(TAG, "onViewDetachedFromWindow");
        holder.setIsRecyclable(false);
        super.onViewDetachedFromWindow(holder);
    }

}
