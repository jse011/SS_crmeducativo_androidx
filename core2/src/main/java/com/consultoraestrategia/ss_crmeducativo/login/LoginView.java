package com.consultoraestrategia.ss_crmeducativo.login;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.login.preferent.LoginPreferentRepository;

import java.util.List;

/**
 * Created by irvinmarin on 23/02/2017.
 */

public interface LoginView extends BaseView<LoginPresenter> , LoginPreferentRepository {

    void disableInputs();

    void enableInputs();

    void goToActivity(int idUsuario);

    void clearImputs();

    void showServerListDialog();

    void showEdtDialog();


    void showProgressPage();

    void showLoginPage();

    void hideProgressPage();

    void hideLoginPage();

    void showListUsuario(List<PersonaUi> usuarioUiList, boolean elimiarUsuario);

    void removeUsuario(PersonaUi usuarioUi);


    void updateProgress(int progress);

    void updateProgressText(String text);


    void setupLogoAnim();

    void showLoginPageUser();

    void hideLoginPageUser();

    void ShowLoginPagePassword();

    void hideLoginPagePassword();

    void showLoginPageListUser();

    void hideLoginPageListUser();

    void setUrlImgenPassword(String url);

    void setNombrePersona(String nombre);

    void onErroUsuario(String error);

    void onErrorPassword(String error);

    void onCancelarTodosLlamadasHTTPS();

    void onSetTextoBtnQuitarUsuario(String nombre);

    void showAtnAtrasLstUsu();

    void hidebtnAtrasLstUsu();

    void clearFocus();

    void validadoUser(String usuario);

    void showLoginCorreo();

    void showLoginDNI();

    void hideLoginCorreo();

    void onErrorCorreo(String error);

    void showProgressBar();

    void hideProgressBar();

    void hideLoginDNI();

    void onErrorDni(String error);

    void onInvalitedCorreo(String error);

    void onInvalitedPassword(String error);

    void onInvalitedDni(String error);


    void clearFocusPassword();

    void clearFocusDni();

    void clearFocusCorreo();

    void setUserName(String usuario);

    /*void showLoginPasswordUser();

    void hideLoginPasswordUser();*/

    void hideKeyBoard();

    void onCredencialesIncorrectos(String string);

    void onErrorPasswordUsuario(String string);

    void disabledOnClick();

    void enableOnClick();

    void setUrlImgenInstitucion(String imagenUrl);

    void showLoginBienvenida(String nombres, String imagenUrl, String userName);

    void hideLoginBienvenida();

    boolean isInternetAvailable();

    void showMessageNotConnection();

    void showTimeOutConnection();

    void showViewPagerProgressPager(List<LoginProgressPagerUi> loginProgressPagerUiList);

    void changePagerProgressPager(int posicion);

    void hideViewPagerProgressPager();

    void showBtnReintentarProgress();

    void hideBtnReintentarProgress();
}
