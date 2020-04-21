package com.consultoraestrategia.ss_crmeducativo.login;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;

/**
 * Created by irvinmarin on 27/02/2017.
 */

public interface LoginPresenter extends BasePresenter<LoginView> {

    void onBtnIngresarClickedAll(String userName, String userPassword);
    void saveServerUrl(String nombreServidor);
    void onServerMenuClicked();

    void onCustomUrlOk(String s);

    void onBtnSiguienteUser(String usuario, String password);

    void onBtnSiguientePassword(String password);

    void onClickedBtnClosePassword();

    void onClickUsuario(PersonaUi usuarioUi);

    void onClickedBtnQuitarUsuario();

    void onClickedBtnAtrasListUsua();

    void onConfirmacionPassword();


    /*void onBtnSiguientePasswordUsuario(String usuario, String password);*/

    void onBtnSiguienteCorreo(String usuario, String password, String correo);

    void onBtnSiguienteDni(String usuario, String password, String correo, String dni);

    void onBtnIngresarClickedDni(String usuario, String password, String correo, String dni);

    void onClickedBtnAtrasDni();

    void onClickedBtnAtrasCorreo();

    void onClickTimeOutConnectionSalir();

    void onClickBtnReiniciarPageProgress();

    void onClickBtnCancelar();
}
