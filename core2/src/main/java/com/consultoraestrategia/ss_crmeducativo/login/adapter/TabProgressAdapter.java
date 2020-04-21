package com.consultoraestrategia.ss_crmeducativo.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.login.adapter.viewHolder.TabProgressViewHolder;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;

import java.util.List;

public class TabProgressAdapter extends LoopingPagerAdapter<LoginProgressPagerUi> {

    public TabProgressAdapter(Context context, List<LoginProgressPagerUi> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        View view =  LayoutInflater.from(context).inflate(R.layout.fragment_tab_progress, container, false);;
        return view;
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        new TabProgressViewHolder(convertView).bind(itemList.get(listPosition));
    }
}
