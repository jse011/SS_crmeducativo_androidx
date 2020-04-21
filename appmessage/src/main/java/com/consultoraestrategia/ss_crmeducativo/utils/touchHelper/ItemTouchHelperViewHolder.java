package com.consultoraestrategia.ss_crmeducativo.utils.touchHelper;

import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;

/**
 * Interface to notify an item ViewHolder of relevant callbacks from {@link
 * androidx.recyclerview.widget.ItemTouchHelper.Callback}.
 *
 * @author Paul Burke (ipaulpro)
 */
public interface ItemTouchHelperViewHolder {

    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();

    /**
     * Returns information if we can drag this view.
     * @return true if draggable, false otherwise
     */
    boolean canDrag();

    /**
     * Returns information if we can swipe this view.
     * @return true if swipeable, false otherwise
     */
    boolean canSwipe();

    View backgroundView();

    void setTranslationX(float dX, boolean isCurrentlyActive);

    void clearView();
}
