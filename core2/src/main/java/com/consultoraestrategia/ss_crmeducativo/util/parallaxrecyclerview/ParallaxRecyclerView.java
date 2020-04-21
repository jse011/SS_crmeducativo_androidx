package com.consultoraestrategia.ss_crmeducativo.util.parallaxrecyclerview;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yahyabayramoglu on 26/04/15.
 */
public class ParallaxRecyclerView extends RecyclerView {

    private List<OnScrollListener> onScrollListenerList = new ArrayList<>();

    public ParallaxRecyclerView(Context context) {
        super(context);
        init();
    }

    public ParallaxRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParallaxRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setParallaxScrollListener(new ParallaxScrollListener());
    }

    @Override
    public void addOnScrollListener(OnScrollListener listener) {
        for (OnScrollListener onScrollListener: onScrollListenerList)removeOnScrollListener(onScrollListener);
        onScrollListenerList.clear();
        onScrollListenerList.add(listener);
        super.addOnScrollListener(listener);
    }

    public void setParallaxScrollListener(ParallaxScrollListener listener) {
        addOnScrollListener(listener);
    }


}
