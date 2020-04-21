package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

/**
 * Created by @stevecampos on 18/01/2018.
 */

public interface GetUsuarioUseCaseCallback extends UseCase.UseCaseCallback<GetUsuarioUI.ResponseValue> {
    /*Login Callbacks*/
    void onInvalidCredentials();
    void onRedError();
    /*Import Callbacks*/
    void onImportSuccess(Usuario usuario);
    void onImportFailed();
}