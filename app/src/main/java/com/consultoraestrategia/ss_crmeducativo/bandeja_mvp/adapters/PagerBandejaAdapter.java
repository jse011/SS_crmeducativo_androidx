package com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.consultoraestrategia.ss_crmeducativo.bandeja_mvp.items_tab.ItemBandejaEnviadoFragment;


/**
 * Created by irvinmarin on 24/02/2017.
 */

public class PagerBandejaAdapter extends FragmentPagerAdapter {
    private static final String TAG = "PagerBandejaAdapter";

    private int idAlumno;
    private int idCargaCurso;

    private int tabCount;

    public PagerBandejaAdapter(FragmentManager fm, int tabCount, int idAlumno, int idCargaCurso) {
        super(fm);
        this.tabCount = tabCount;
        this.idAlumno = idAlumno;
        this.idCargaCurso = idCargaCurso;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = ItemBandejaEnviadoFragment.newInstace(idCargaCurso,idAlumno);
                break;
//            case 1:
//                fragment = ItemBandejaRecibidoFragment.newInstace(idCargaCurso,idAlumno);
//                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }


}