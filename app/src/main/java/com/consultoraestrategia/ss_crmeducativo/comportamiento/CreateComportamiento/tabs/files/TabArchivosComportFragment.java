package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.files;

import com.consultoraestrategia.ss_crmeducativo.repositorio.BaseRepositoriFragmento;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;

public class TabArchivosComportFragment extends BaseRepositoriFragmento {

    public static TabArchivosComportFragment newInstance(RepositorioTBunble repositorioTBunble) {
        TabArchivosComportFragment fragment = new TabArchivosComportFragment();
        fragment.setArguments(repositorioTBunble.getBundle());
        return fragment;
    }

}
