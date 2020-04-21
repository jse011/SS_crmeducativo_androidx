package com.consultoraestrategia.ss_crmeducativo.main.adapters.decoration;

/**
 * Created by SCIEV on 12/07/2018.
 */

import android.content.res.Resources;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by anthonykiniyalocts on 12/8/16.
 *
 * Quick way to add padding to first and last item in recyclerview via decorators
 */

public class EdgeDecorator extends RecyclerView.ItemDecoration {

    private static final float DP = Resources.getSystem().getDisplayMetrics().density;

    private final int edgePadding;

    /**
     * EdgeDecorator
     * @param edgePadding padding set on the left side of the first item and the right side of the last item
     */
    public EdgeDecorator(int edgePadding) {
        this.edgePadding =  (int) (DP * edgePadding);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int itemCount = state.getItemCount();

        final int itemPosition = parent.getChildAdapterPosition(view);

        // no position, leave it alone
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        // first item
        if (itemPosition == 0) {
            outRect.set(view.getPaddingLeft(), edgePadding, view.getPaddingRight(), view.getPaddingBottom());
        }
        // last item
        /*else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(view.getPaddingLeft(), view.getPaddingTop(), edgePadding, view.getPaddingBottom());
        }*/
        // every other item
        else {
            outRect.set(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}
