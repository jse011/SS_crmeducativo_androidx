package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.helper.ItemTouchHelperViewHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;

/**
 * Created by SCIEV on 10/08/2018.
 */

public abstract class CrearRubroProcesoHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener,RecyclerItemClickListener.RecyclerViewOnItemClickListener , TextWatcher, ItemTouchHelperViewHolder {

    private final static String TAG = CrearRubroProcesoHolderBase.class.getSimpleName();

    public CrearRubroProcesoHolderBase(View itemView) {
        super(itemView);
    }

    protected abstract FrameLayout getFrameLayout();

    protected abstract View getLayout();

    public void setGroupTranslation(float dX, float dY, boolean isCurrentlyActive) {
        float widthDeleteContainer = getFrameLayout().getWidth();
        Log.d(TAG, "dX: " + dX + ",dY: " + dY + ", isCurrentlyActive: " + isCurrentlyActive + ", widthDeleteContainer: " + widthDeleteContainer);
        getLayout().setTranslationX(dX);
    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }

    @Override
    public void onItemSwiped() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View clickedView) {

    }

    @Override
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View clickedView) {

    }
}
