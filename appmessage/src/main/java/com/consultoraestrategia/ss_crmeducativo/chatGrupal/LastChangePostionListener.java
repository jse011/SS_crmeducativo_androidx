package com.consultoraestrategia.ss_crmeducativo.chatGrupal;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class LastChangePostionListener extends  RecyclerView.OnScrollListener implements View.OnLayoutChangeListener {

    private int lastVisibleItem = -1;
    private int lastItem = -1;
    private CallBack callBack;
    private LinearLayoutManager linearLayoutManager;
    public LastChangePostionListener(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // Find the adapter position of the first fully visible item
        // May return RecyclerView.NO_POSITION that is not handled here.
        if(linearLayoutManager==null){
            linearLayoutManager= ((LinearLayoutManager) recyclerView.getLayoutManager());
        }
        int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        if(lastVisibleItem==-1||lastVisibleItem != this.lastVisibleItem){
            callBack.changeFirsthPostion(lastVisibleItem);
        }
        this.lastVisibleItem = lastVisibleItem;

        final int item = lastVisibleItem;
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                lastItem = item;
            }
        }, 500);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if ( bottom < oldBottom) {
            callBack.onKeyboardOpens(lastItem);
        }else {
            callBack.onKeyboardClose();
        }
    }

    public interface CallBack{
        void changeFirsthPostion(int lastVisibleItem);
        void onKeyboardOpens(int lastVisibleItem);
        void onKeyboardClose();
    }
}
