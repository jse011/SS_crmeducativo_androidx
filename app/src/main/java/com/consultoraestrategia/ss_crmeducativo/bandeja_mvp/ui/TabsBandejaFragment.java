package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters.PagerBandejaAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 14/08/2017.
 */

@SuppressLint("ValidFragment")
public class TabsBandejaFragment extends Fragment {
    private static final String TAG = TabsBandejaFragment.class.getSimpleName();
    private static int idCargaCurso;
    View viewPadre;

    @BindView(R.id.tabBandeja)
    TabLayout tabBandeja;
    @BindView(R.id.vpBandeja)
    ViewPager vpBandeja;


    public static TabsBandejaFragment newInstance(int idCargaCurso) {
        Log.d(TAG, "newInstance: " + idCargaCurso);
        TabsBandejaFragment.idCargaCurso = idCargaCurso;
        return new TabsBandejaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.tabitem_fragment_bandeja, container, false);
        ButterKnife.bind(this, viewPadre);

        tabBandeja.addTab(tabBandeja.newTab().setText("Enviados"));
//        tabBandeja.addTab(tabBandeja.newTab().setText("Recibidos"));
        tabBandeja.setTabMode(TabLayout.MODE_SCROLLABLE);


        PagerBandejaAdapter bandejaAdapter = new PagerBandejaAdapter(
                getChildFragmentManager(), tabBandeja.getTabCount(), 0, idCargaCurso);
        vpBandeja.setAdapter(bandejaAdapter);

        vpBandeja.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabBandeja));


        tabBandeja.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpBandeja.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return viewPadre;
    }


}
