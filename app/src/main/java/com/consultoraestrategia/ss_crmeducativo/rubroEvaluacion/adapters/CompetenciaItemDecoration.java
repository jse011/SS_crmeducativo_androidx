package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class CompetenciaItemDecoration extends RecyclerView.ItemDecoration {

    private int lastpositionPadding;
    private int firstpositionPadding;

    public CompetenciaItemDecoration(int firstpositionPadding, int lastpositionPadding) {
        this.lastpositionPadding = lastpositionPadding;
        this.firstpositionPadding = firstpositionPadding;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        final int itemCount = state.getItemCount();

        /** first position */
        if (itemPosition == 0) {
            outRect.set(0, firstpositionPadding, 0, 0);
        }
        /** last position */
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(0, 0, 0, lastpositionPadding);
        }
        /** positions between first and last */
        else {

        }
    }
}
