package com.consultoraestrategia.ss_crmeducativo.util.parallaxrecyclerview;

import androidx.recyclerview.widget.RecyclerView;

public class ParallaxScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
            if (viewHolder instanceof ParallaxViewHolder) {
                ((ParallaxViewHolder) viewHolder).animateImage();
            }
        }

    }
}
