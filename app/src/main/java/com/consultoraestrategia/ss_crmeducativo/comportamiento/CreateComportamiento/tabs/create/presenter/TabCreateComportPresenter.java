package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.TabCreateComportView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.dialog.ListaComportamientoView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;

public interface TabCreateComportPresenter extends BaseFragmentPresenter<TabCreateComportView> {
    void seletedTipo(TipoUi tipoUi);
    void selectedAlumno(AlumnoUi alumnoUi);
    ComportamientoUi saveComportamiento(String descripcion);
    void setFecha(long fecha);
    AlumnoUi getAlumnoSelected();
    void attachView(ListaComportamientoView listaComportamientoView);
    void onDestroyViewListaComportamiento();
    void onResumedListaComportamiento();
    void onClickTipoMerito(TipoUi tipoUi);
    void onClickTipoComportamiento(TipoComportamientoUi tipoComportamientoUi);

    void onClickClock();

    void onClickCalendar();

    void seletedUsuario(UsuarioUi usuarioUi);
    DestinoUi getDestinatarios();

    void selectedCheckPadre();

    void selectedCheckApoderado();

    void selectedCheckTutor();
}
