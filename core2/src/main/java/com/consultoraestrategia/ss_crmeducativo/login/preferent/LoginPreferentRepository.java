package com.consultoraestrategia.ss_crmeducativo.login.preferent;

import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;

import java.util.List;

/**
 * Created by Jse on 25/09/2018.
 */

public interface LoginPreferentRepository {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    void getTodosUsuarios(Callback<List<PersonaUi>> usuarioCallback);

    void guardarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback);

    void eliminarUsuario(PersonaUi usuarioUi, Callback<Boolean> usuarioCallback);

    void elimarTodosUsuario(Callback<Boolean> usuarioCallback);
}
