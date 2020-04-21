package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.RubroEvalRNRFormulaUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 19/04/2018.
 */

public class RubroRNRFormulaAdapter extends RecyclerView.Adapter<RubroRNRFormulaAdapter.ViewHolder> {
    private static String TAG = RubroRNRFormulaAdapter.class.getSimpleName();
    private List<RubroEvalRNRFormulaUi> rnrFormulaUiList;
    private String color;
    private Context context;

    public RubroRNRFormulaAdapter(List<RubroEvalRNRFormulaUi> rnrFormulaUiList, String color, Context context) {
        this.rnrFormulaUiList = rnrFormulaUiList;
        this.color = color;
        this.context = context;
    }

    @Override
    public RubroRNRFormulaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rubros_rubricas, parent, false);
        return new RubroRNRFormulaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RubroRNRFormulaAdapter.ViewHolder holder, int position) {
        RubroEvalRNRFormulaUi rubroEvalRNRFormulaUi = rnrFormulaUiList.get(position);
        Log.d(TAG, "onBindViewHolder : " + rubroEvalRNRFormulaUi.getContador() + "");
        holder.bind(rubroEvalRNRFormulaUi, color, context);
    }

    @Override
    public int getItemCount() {
        if (rnrFormulaUiList == null) return 0;
        return rnrFormulaUiList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public @BindView(R.id.txt_cant_rubro)
        TextView textViewCantidad;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(RubroEvalRNRFormulaUi rnrFormulaUi, String color, Context context) {
            textViewCantidad.setText(rnrFormulaUi.getContador() + "");
//            Drawable mDrawable = context.getResources().getDrawable(R.drawable.ic_circle_unidades);
//            mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN));
//            textViewCantidad.setBackground(mDrawable);
        }
    }
    public void setRnformulas(List<RubroEvalRNRFormulaUi> rnformulas){
        this.rnrFormulaUiList.clear();
        this.rnrFormulaUiList.addAll(rnformulas);
        notifyDataSetChanged();
    }
}
