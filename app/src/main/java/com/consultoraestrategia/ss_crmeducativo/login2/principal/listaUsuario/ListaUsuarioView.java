package com.consultoraestrategia.ss_crmeducativo.login2.principal.listaUsuario;

import com.consultoraestrategia.ss_crmeducativo.login2.principal.Login2Presenter;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.PersonaUi;

import java.util.List;

public interface ListaUsuarioView {

    void onAttach(Login2Presenter presenter);

    void listaUsuarioView(List<PersonaUi> usuarioUiList, boolean quitarUsuario);

    void setTextoBtnQuitarUsuario(String texto);
}
