package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorlogin;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.UsuarioExternoUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class GetUsuarioExterno implements UseCaseLoginSincrono<GetUsuarioExterno.Request, UsuarioExternoUi> {

    private LoginDataRepository loginDataRepository;

    public GetUsuarioExterno(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    @Override
    public RetrofitCancel execute(Request request, final Callback<UsuarioExternoUi> callback) {
        return loginDataRepository.getUsuarioExterno(request.getUrlAdminServicio(),request.getUsuario(), request.getPassword(), new LoginDataRepository.Callback<UsuarioExternoUi>() {
            @Override
            public void onResponse(boolean success, UsuarioExternoUi value) {
                callback.onResponse(success, value);
            }
        });
    }

    public static class Request{
        private String urlAdminServicio;
        private String usuario;
        private String password;

        public Request(String urlAdminServicio, String usuario, String password) {
            this.urlAdminServicio = urlAdminServicio;
            this.usuario = usuario;
            this.password = password;
        }

        public String getUrlAdminServicio() {
            return urlAdminServicio;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getPassword() {
            return password;
        }
    }

}
