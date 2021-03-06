package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by SCIEV on 20/02/2018.
 */

public class RecyclerItemClickListener extends GestureDetector.SimpleOnGestureListener implements RecyclerView.OnItemTouchListener {

    private String id;
    public interface RecyclerViewOnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View clickedView);

        void onItemLongClick(RecyclerView.ViewHolder viewHolder, View clickedView);
    }

    private RecyclerViewOnItemClickListener listener;
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;

    public RecyclerItemClickListener(RecyclerView recyclerView, RecyclerViewOnItemClickListener listener) {
        if (recyclerView == null || listener == null) {
            throw new IllegalArgumentException("RecyclerView and Listener arguments can not be null");
        }
        this.recyclerView = recyclerView;
        this.listener = listener;
        this.gestureDetector = new GestureDetector(recyclerView.getContext(), this);
        this.id = RecyclerItemClickListener.class.getSimpleName();
        removerDupicado();
    }

    public RecyclerItemClickListener(RecyclerView recyclerView, RecyclerViewOnItemClickListener listener, String id) {
        if (recyclerView == null || listener == null) {
            throw new IllegalArgumentException("RecyclerView and Listener arguments can not be null");
        }
        this.recyclerView = recyclerView;
        this.listener = listener;
        this.gestureDetector = new GestureDetector(recyclerView.getContext(), this);
        this.id = id;
        removerDupicado();
    }

    private void removerDupicado(){
        recyclerView.removeOnItemTouchListener(this);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onShowPress(MotionEvent e) {
        View view = getChildViewUnder(e);
        if (view != null) {
            view.setPressed(true);
        }
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        View view = getChildViewUnder(e);
        if (view == null) return false;
        view.setPressed(false);
        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
        listener.onItemClick(viewHolder, view);
        return true;
    }

    public void onLongPress(MotionEvent e) {
        View view = getChildViewUnder(e);
        if (view == null) return;
        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
        listener.onItemLongClick(viewHolder, view);
        view.setPressed(false);
    }

    @Nullable
    private View getChildViewUnder(MotionEvent e) {
        return recyclerView.findChildViewUnder(e.getX(), e.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecyclerItemClickListener that = (RecyclerItemClickListener) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
