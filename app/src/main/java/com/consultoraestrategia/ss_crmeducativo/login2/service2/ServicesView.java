package com.consultoraestrategia.ss_crmeducativo.login2.service2;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;

import java.util.List;

public interface ServicesView extends BaseView<ServicesPresenter> {
    void setDescripcionNotificacion(String descripcion);

    void setDescripcionProgramarHorarioEnvio(String descripcion);

    void showListServicioEnvio(List<ServiceEnvioUi> serviceEnvioUiList);

    void showListServicioActualizar(List<ActualizarUi> actualizarUiList);

    void girarBtnRevisionDatos();

    void showNombreCalendario(String nombre);

    void stopGirarBtnRevisionDatos();

    void showStartMensajeRevision();

    void showStopMensajeRevision();

    void showItemDownloadProgress(ActualizarUi actualizarUi);

    void showItemUpaloadProgress(ActualizarUi actualizarUi);

    void updateListaActualizar(ActualizarUi actualizarUiSelected);

    void notifyChangeDataBase();

    void hideNombreCalendario();

    void hideServicioActualizar();

    void hideListServicioEnvio();

    void updateListaEnviar(ServiceEnvioUi serviceEnvioUi);

    void removeListaEnviar(ServiceEnvioUi serviceEnvioUi);

    void showMessageRevision();

    void scroll(int index, boolean animation);

    void showProgramaHorario(AlarmaUi alarmaUi);

    void changeSelectedProgramaHorario(boolean change);

    void finishActivity();
}
