package com.consultoraestrategia.ss_crmeducativo.repositorio.listener;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public interface RepositorioListener {
    void onChangeList(List<RepositorioFileUi> repositorioFileUiList);
}
