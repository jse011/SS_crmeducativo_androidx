package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.CreateComportamientoView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;

import java.util.List;

public interface CreateComportamientoPresenter extends BaseFragmentPresenter<CreateComportamientoView> {
    void saveComportamiento(ComportamientoUi comportamientoUi, DestinoUi destinatarios, List<RepositorioFileUi> repositorioFileUiList);
}
