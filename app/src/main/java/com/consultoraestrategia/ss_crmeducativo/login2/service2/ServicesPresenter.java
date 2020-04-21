package com.consultoraestrategia.ss_crmeducativo.login2.service2;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;

public interface ServicesPresenter extends BasePresenter<ServicesView> {
    void changeSwitchNotifiacion(boolean isChecked);

    void changeSwitchProgramaEnvio(boolean isChecked);

    void onClickRevisionDatos();

    void onSelectedStartRevisionDatos();

    void onSelectedStoptRevisionDatos();

    void onClickActualizarItem(ActualizarUi actualizarUi);

    void onClickEnviarItem(ServiceEnvioUi serviceEnvioUi);

    void onSelectedActualizarDatos();

    void onSelectedHoraMinuteTimePicker(int hour, int minute);

    void onSelectedAceptarTimePicker();

    void onSelectedCancelarTimePicker();

    void onViewClickedCardProgEnvio();
}
