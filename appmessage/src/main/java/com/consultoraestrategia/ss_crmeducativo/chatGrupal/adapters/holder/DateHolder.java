package com.consultoraestrategia.ss_crmeducativo.chatGrupal.adapters.holder;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.ItemTouchHelperViewHolder;

public class DateHolder  extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private TextView date;
    public DateHolder(@NonNull View itemView) {
        super(itemView);
        date=(TextView) itemView.findViewById(R.id.date);
    }
    public void bind(String dateConvert) {
        date.setText(dateConvert);
    }


    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }

    @Override
    public boolean canDrag() {
        return false;
    }

    @Override
    public boolean canSwipe() {
        return false;
    }

    @Override
    public View backgroundView() {
        return null;
    }

    @Override
    public void setTranslationX(float dX, boolean isCurrentlyActive) {

    }

    @Override
    public void clearView() {

    }
}
