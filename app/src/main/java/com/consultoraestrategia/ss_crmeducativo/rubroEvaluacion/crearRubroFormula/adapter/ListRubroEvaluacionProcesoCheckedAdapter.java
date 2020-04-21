package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 6/11/2017.
 */

public class ListRubroEvaluacionProcesoCheckedAdapter extends RecyclerView.Adapter<ListRubroEvaluacionProcesoCheckedAdapter.ViewHolder> {

    private static String TAG = ListRubroEvaluacionProcesoCheckedAdapter.class.getSimpleName();
    private List<RubroProcesoUi> rubroEvaluacionProcesoListChecked;
    private ListRubroEvaluacionProcesoCheckedListener listener;


    public interface ListRubroEvaluacionProcesoCheckedListener {
        void onClickRubroAsociados(RubroProcesoUi rubroEvaluacionProcesoUi);
        void onTextChanged(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence charSequence);
    }


    public ListRubroEvaluacionProcesoCheckedAdapter(List<RubroProcesoUi> rubroEvaluacionProcesoListChecked, ListRubroEvaluacionProcesoCheckedListener listener) {
        //  Log.d(TAG, "QUE PASO");
        this.rubroEvaluacionProcesoListChecked = rubroEvaluacionProcesoListChecked;
        this.listener = listener;

    }

    public void showListRubroAsociadosList(List<RubroProcesoUi> rubroEvaluacionProcesoUiList) {
        this.rubroEvaluacionProcesoListChecked.clear();
        this.rubroEvaluacionProcesoListChecked.addAll(rubroEvaluacionProcesoUiList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_checked_proceso, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RubroProcesoUi rubroEvaluacionProcesoUi = rubroEvaluacionProcesoListChecked.get(position);
        holder.bind(rubroEvaluacionProcesoUi, listener);
}

    @Override
    public int getItemCount() {
        if (rubroEvaluacionProcesoListChecked == null) return 0;
        return rubroEvaluacionProcesoListChecked.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher {
        @BindView(R.id.textName)
        TextView textViewNameRubro;//editText2
        @BindView(R.id.editText2)
        EditText textViewPeso;
        @BindView(R.id.idConStraintLayout)
        ConstraintLayout constraintLayout;
        private RubroProcesoUi rubroEvaluacionProcesoUi;
        private ListRubroEvaluacionProcesoCheckedListener listener;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final RubroProcesoUi rubroEvaluacionProcesoUi, final ListRubroEvaluacionProcesoCheckedListener listener) {
            this.rubroEvaluacionProcesoUi =rubroEvaluacionProcesoUi;
            this.listener = listener;
            if (rubroEvaluacionProcesoUi.isCheck()) {
                constraintLayout.setVisibility(View.VISIBLE);
                if (rubroEvaluacionProcesoUi.getPeso() == 0.0) {
                    //Log.d(TAG, "rubroEvaluacionProcesoUi.getIdRubroEvalProceso()" + rubroEvaluacionProcesoUi.getIdRubroEvalProceso());
                    textViewNameRubro.setText(rubroEvaluacionProcesoUi.getTitulo());
                    textViewPeso.setText(String.valueOf((int)rubroEvaluacionProcesoUi.getPeso()));
                    textViewPeso.setVisibility(View.INVISIBLE);
                } else {
                    textViewNameRubro.setText(rubroEvaluacionProcesoUi.getTitulo());
                    textViewPeso.setVisibility(View.VISIBLE);
                    textViewPeso.setText(String.valueOf((int)rubroEvaluacionProcesoUi.getPeso()));
                    textViewPeso.addTextChangedListener(this);
                    /*itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClickTipoComportamiento(View view) {
                            Log.d(TAG, "onClickTipoComportamiento");
                            listener.onClickRubroAsociados(rubroEvaluacionProcesoUi);
                        }
                    });*/
                }
            } else {
                Log.d(TAG, "falseee");

                constraintLayout.setVisibility(View.GONE);

            }

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            listener.onTextChanged(rubroEvaluacionProcesoUi,charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
