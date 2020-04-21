package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.adapters;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.consultoraestrategia.ss_crmeducativo.actividades.ui.ActividadesFragment;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.ui.FragmentAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.RubricaSesionFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;


/**
 * Created by irvinmarin on 24/02/2017.
 */

public class PagerSesionesAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "PagerCursoAlumnoAdapter";
    private Bundle arguments;

    //integer to count number of tabs
    private int tabCount;

    //Constructor to the class

    public PagerSesionesAdapter(FragmentManager fm, int tabCount, Bundle arguments) {
        super(fm);
        this.tabCount = tabCount;
        this.arguments = arguments;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
//        Returning the current tabs

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentAprendizaje();
                fragment.setArguments(arguments);
                break;
            case 1:
                fragment = new ActividadesFragment();
                fragment.setArguments(arguments);
                break;
            case 2:
                fragment = new RubricaSesionFragment();
                fragment.setArguments(arguments);
                break;
            case 3:
                fragment = new FragmentRubroEvalProcesoLista();
                fragment.setArguments(arguments);
                break;
            case 4:
//
//                fragment = FragmentTareas.newInstance(0, 0,);
//                fragment.setArguments(arguments);
                break;
            default:
                break;
        }

        return fragment;

    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }


}